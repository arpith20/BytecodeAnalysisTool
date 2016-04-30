package bytecode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import soot.Body;
import soot.BodyTransformer;
import soot.G;
import soot.PackManager;
import soot.PhaseOptions;
import soot.Scene;
import soot.SootMethod;
import soot.Transform;
import soot.Unit;
import soot.baf.Inst;
import soot.jimple.JimpleBody;
import soot.options.Options;
import soot.tagkit.Tag;
import soot.util.Chain;
import soot.util.cfgcmd.AltClassLoader;
import soot.util.cfgcmd.CFGGraphType;
import soot.util.cfgcmd.CFGIntermediateRep;
import soot.util.cfgcmd.CFGToDotGraph;

/*
 * Arpith K
 * April 29th 2016
 * Ununtu 16.04
 * Java 8
 */

public class Bytecode extends BodyTransformer {

	private static final String packToJoin = "jtp";
	private static final String phaseSubname = "printcfg";
	private static final String phaseFullname = packToJoin + '.' + phaseSubname;
	private static final String altClassPathOptionName = "alt-class-path";
	private static final String graphTypeOptionName = "graph-type";
	private static final String defaultGraph = "BriefUnitGraph";
	private static final String irOptionName = "ir";
	private static final String defaultIR = "baf";
	private static final String multipageOptionName = "multipages";
	private static final String briefLabelOptionName = "brief";
	private CFGGraphType graphtype;
	private CFGIntermediateRep ir;
	private CFGToDotGraph drawer;
	private Map methodsToPrint;

	boolean jasminFileGenerated = false;

	class baftoJasmin {
		Inst baf;
		String jasmin;

		baftoJasmin() {
			baf = null;
			jasmin = null;
		}
	}

	protected void internalTransform(Body b, String phaseName, Map options) {
		// initialize various mapping required for CFGViewer
		initialize(options);

		synchronized (this) {

			// method currently under observation
			SootMethod meth = b.getMethod();

			if ((methodsToPrint == null)
					|| (meth.getDeclaringClass().getName() == methodsToPrint.get(meth.getName()))) {

				String cur_class = meth.getDeclaringClass().toString();
				Body body = ir.getBody((JimpleBody) b);

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

				// System.out.println("******IR******");
				// System.out.println(body.toString());
				// System.out.println("******/IR******");
				String bytecodeMethod = meth.getBytecodeSignature().split(" ")[1];
				bytecodeMethod = bytecodeMethod.substring(0, bytecodeMethod.length() - 1);

				System.out.println("******" + bytecodeMethod + " IR******");
				Chain<Unit> units = body.getUnits();
				Iterator<Unit> stmtIt = units.snapshotIterator();

				int i = 0;
				while (stmtIt.hasNext()) {
					Inst stmt = (Inst) stmtIt.next();

					List<Tag> tags = stmt.getTags();
					for (Tag t : tags) {
						System.out.println(t.getName());
					}
					System.out.println("Stmt " + i++ + ": " + stmt.toString());
				}
				System.out.println("******/Statements in this IR******");

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
			 * Required for functioning of MyCounter.java
			 */
			Scene.v().addBasicClass("xyz.arpith.pathprofiler.MyCounter");
			Scene.v().addBasicClass("java.util.HashMap");
			Scene.v().addBasicClass("java.util.HashMap$Node");
			Scene.v().addBasicClass("java.util.function.Function");
			Scene.v().addBasicClass("java.util.function.BiFunction");
			Scene.v().addBasicClass("java.util.function.BiConsumer");
			Scene.v().addBasicClass("java.util.HashMap$TreeNode");
			Scene.v().addBasicClass("java.lang.invoke.SerializedLambda");
			Scene.v().addBasicClass("java.util.function.Predicate");
			Scene.v().addBasicClass("java.util.stream.Stream");
			Scene.v().addBasicClass("java.util.Iterator");
			Scene.v().addBasicClass("java.util.function.Consumer");
			Scene.v().addBasicClass("java.io.OutputStreamWriter");
			Scene.v().addBasicClass("java.io.BufferedWriter");
			Scene.v().addBasicClass("java.io.FileNotFoundException");
			Scene.v().addBasicClass("java.util.Formatter");

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
