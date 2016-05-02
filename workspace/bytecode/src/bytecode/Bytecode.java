package bytecode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

import soot.Body;
import soot.BodyTransformer;
import soot.G;
import soot.Local;
import soot.PackManager;
import soot.PhaseOptions;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.Unit;
import soot.baf.Baf;
import soot.baf.BafBody;
import soot.baf.Inst;
import soot.baf.PushInst;
import soot.baf.StaticInvokeInst;
import soot.jimple.InvokeExpr;
import soot.jimple.Jimple;
import soot.jimple.JimpleBody;
import soot.jimple.Stmt;
import soot.jimple.StringConstant;
import soot.options.Options;
import soot.util.Chain;
import soot.util.cfgcmd.AltClassLoader;
import soot.util.cfgcmd.CFGGraphType;
import soot.util.cfgcmd.CFGIntermediateRep;
import soot.util.cfgcmd.CFGToDotGraph;

/*
 * Arpith K
 * April 29th 2016
 * Ununtu 16.04
 * Java 8 is required
 */

public class Bytecode extends BodyTransformer {

	private static final String packToJoin = "jtp";
	private static final String phaseSubname = "printcfg";
	private static final String phaseFullname = packToJoin + '.' + phaseSubname;
	private static final String altClassPathOptionName = "alt-class-path";
	private static final String graphTypeOptionName = "graph-type";
	private static final String defaultGraph = "BriefUnitGraph";
	private static final String irOptionName = "ir";
	private static final String defaultIR = "jimple";
	private static final String multipageOptionName = "multipages";
	private static final String briefLabelOptionName = "brief";
	private CFGGraphType graphtype;
	private CFGIntermediateRep ir;
	private CFGToDotGraph drawer;
	private Map methodsToPrint;

	boolean jasminFileGenerated = false;

	SootClass printClass = null;
	SootMethod printbytecode;

	protected void internalTransform(Body b, String phaseName, Map options) {
		// initialize various mapping required for CFGViewer
		initialize(options);

		synchronized (this) {

			if (printClass == null) {
				printClass = Scene.v().loadClassAndSupport("bytecode.PrintBytecode");
				printbytecode = printClass.getMethod("void printBytecode(java.lang.String)");
			}

			// method currently under observation
			SootMethod meth = b.getMethod();

			if ((methodsToPrint == null)
					|| (meth.getDeclaringClass().getName() == methodsToPrint.get(meth.getName()))) {

				String cur_class = meth.getDeclaringClass().toString();
				Body body = ir.getBody((JimpleBody) b);

				// create a jasmin file. This file will contain bytecodes for
				// all the members of this class
				if (!jasminFileGenerated) {
					Process p;
					try {
						String s;
						p = Runtime.getRuntime()
								.exec("java -cp /home/arpith/iisc/ase/exam/bytecode/workspace/bytecode/lib/soot-trunk.jar:. soot.Main -cp /home/arpith/iisc/ase/exam/bytecode/test1 -pp -f jasmin "
										+ cur_class);
						BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
						while ((s = br.readLine()) != null)
							System.out.println("line: " + s);
						p.waitFor();
						System.out.println("exit: " + p.exitValue());
						p.destroy();
						jasminFileGenerated = true;
					} catch (Exception e) {
						System.out.println("Jasmine failed");
					}
				}

				// determine the name of method under observation
				String bytecodeMethod = meth.getBytecodeSignature().split(" ")[1];
				bytecodeMethod = bytecodeMethod.substring(0, bytecodeMethod.length() - 1);

				// Read the jasmin file into an ArrayList
				String fileName = "sootOutput/" + cur_class + ".jasmin";
				List<String> jasminLines = new ArrayList<>();
				try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

					jasminLines = stream.collect(Collectors.toList());

				} catch (IOException e) {
					e.printStackTrace();
				}

				// determine the satrt and end indexe of the method under
				// analysis in jasmin file
				int startIndex = 0;
				int endIndex = 0;
				int i;
				for (i = 0; i < jasminLines.size(); i++) {
					String cur_line = jasminLines.get(i);
					if (!cur_line.startsWith(".method"))
						continue;
					if (!cur_line.contains(bytecodeMethod))
						continue;
					startIndex = i + 1;
					i = startIndex;
					cur_line = jasminLines.get(i);
					while (!cur_line.equals(".end method")) {
						i++;
						cur_line = jasminLines.get(i);
					}
					endIndex = i - 1;
					break;
				}

				System.out.println("******" + bytecodeMethod + " IR******");
				Chain<Unit> units = body.getUnits();
				Iterator<Unit> stmtIt = units.snapshotIterator();
				List<Unit> bafLines = Lists.newArrayList(stmtIt);

				int i_baf = 0;
				int i_jas = startIndex;
				String toInsert = "";
				boolean again = false;
				while (true) {
					// collect jasmin variables till label is found
					while (true) {
						String jasstmt = jasminLines.get(i_jas);
						i_jas++;
						if (jasstmt.startsWith("label") || jasstmt.trim().startsWith("goto")
								|| jasstmt.trim().startsWith("if_")) {
							if (!jasstmt.startsWith("label"))
								toInsert += jasstmt.trim() + "\n";
							break;
						}
						if (i_jas >= endIndex) {
							toInsert += jasstmt.trim() + "\n";
							break;
						}
						toInsert += jasstmt.trim() + "\n";
					}

					while (true) {
						Stmt bafstmt = (Stmt) bafLines.get(i_baf);
						i_baf++;

						if (bafstmt.branches() || bafstmt.getBoxesPointingToThis().size() != 0
								|| (i_baf == bafLines.size() && !(i_baf > bafLines.size()))) {
							if (bafstmt.toString().contains("@caughtexception"))
								break;

							if (again == false) {
								InvokeExpr incExpr = Jimple.v().newStaticInvokeExpr(printbytecode.makeRef(),
										StringConstant.v(toInsert));
								Stmt incStmt = Jimple.v().newInvokeStmt(incExpr);
								body.getUnits().insertBeforeNoRedirect(incStmt, bafstmt);
							}

							// handles the case where a unit is both target and
							// a branch
							if (bafstmt.branches() && bafstmt.getBoxesPointingToThis().size() != 0) {
								if (again == false) {
									i_baf--;
									again = true;
									break;
								} else
									again = false;
								InvokeExpr incExpr = Jimple.v().newStaticInvokeExpr(printbytecode.makeRef(),
										StringConstant.v(toInsert));
								Stmt incStmt = Jimple.v().newInvokeStmt(incExpr);
								body.getUnits().insertBefore(incStmt, bafstmt);

								break;
							}

							if (bafstmt.branches() || bafstmt.getBoxesPointingToThis().size() != 0)
								break;
						}
						if (i_baf >= bafLines.size())
							break;
					}

					toInsert = "";

					if (i_baf >= bafLines.size())
						break;
					if (i_jas >= endIndex) {
						break;
					}
				}

				System.out.println(b.toString());
				
				// System.out.println("******/Statements in this IR******");

			}
		}

	}

	private void initialize(Map options) {
		if (drawer == null) {
			drawer = new CFGToDotGraph();
			drawer.setBriefLabels(PhaseOptions.getBoolean(options, briefLabelOptionName));
			drawer.setOnePage(!PhaseOptions.getBoolean(options, multipageOptionName));
			drawer.setUnexceptionalControlFlowAttr("color", "black");
			drawer.setExceptionalControlFlowAttr("color", "red");
			drawer.setExceptionEdgeAttr("color", "lightgray");
			drawer.setShowExceptions(Options.v().show_exception_dests());
			ir = CFGIntermediateRep.getIR(PhaseOptions.getString(options, irOptionName));
			graphtype = CFGGraphType.getGraphType(PhaseOptions.getString(options, graphTypeOptionName));

			AltClassLoader.v().setAltClassPath(PhaseOptions.getString(options, altClassPathOptionName));
			AltClassLoader.v()
					.setAltClasses(new String[] { "soot.toolkits.graph.ArrayRefBlockGraph", "soot.toolkits.graph.Block",
							"soot.toolkits.graph.Block$AllMapTo", "soot.toolkits.graph.BlockGraph",
							"soot.toolkits.graph.BriefBlockGraph", "soot.toolkits.graph.BriefUnitGraph",
							"soot.toolkits.graph.CompleteBlockGraph", "soot.toolkits.graph.CompleteUnitGraph",
							"soot.toolkits.graph.TrapUnitGraph", "soot.toolkits.graph.UnitGraph",
							"soot.toolkits.graph.ZonedBlockGraph", });
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		Bytecode profiler = new Bytecode();
		Transform printTransform = new Transform(phaseFullname, profiler);

		// for CFGViewer
		printTransform.setDeclaredOptions("enabled " + altClassPathOptionName + ' ' + graphTypeOptionName + ' '
				+ irOptionName + ' ' + multipageOptionName + ' ' + briefLabelOptionName + ' ');
		printTransform.setDefaultOptions("enabled " + altClassPathOptionName + ": " + graphTypeOptionName + ':'
				+ defaultGraph + ' ' + irOptionName + ':' + defaultIR + ' ' + multipageOptionName + ":false " + ' '
				+ briefLabelOptionName + ":false ");
		PackManager.v().getPack("jtp").add(printTransform);

		// Get arguments from user
		args = profiler.parse_options(args);

		// A random print statement
		System.out.println("in main");

		// if the user does not enter any arguments, print the usage
		if (args.length == 0) {
			usage();
		} else {

			/*
			 * Required for functioning of PrintBytecode.java
			 */
			Scene.v().addBasicClass("bytecode.PrintBytecode");

			// Start analysis
			soot.Main.main(args);
		}
	}

	/*
	 * Parse the command line arguments
	 */
	private String[] parse_options(String[] args) {
		List sootArgs = new ArrayList(args.length);

		for (int i = 0, n = args.length; i < n; i++) {

			int smpos = args[i].indexOf('#');
			if (smpos == -1) {
				sootArgs.add(args[i]);
			} else {
				String clsname = args[i].substring(0, smpos);
				sootArgs.add(clsname);
				String methname = args[i].substring(smpos + 1);
				if (methodsToPrint == null) {
					methodsToPrint = new HashMap();
				}
				methodsToPrint.put(methname, clsname);
			}

		}
		String[] sootArgsArray = new String[sootArgs.size()];
		return (String[]) sootArgs.toArray(sootArgsArray);
	}

	private static void usage() {
		G.v().out.println("Input arguments. ToDo: write usage here");
	}
}
