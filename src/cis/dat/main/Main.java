package cis.dat.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import cis.dat.io.ReadAutomata;
import cis.dat.io.WriteAutomata;
import cis.dat.object.Alphabet;
import cis.dat.object.MyLink;
import cis.dat.object.Status;
import cis.dat.object.Transform;
import cis.dat.object.TransformFunction;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import sun.misc.Queue;

public class Main {
	static TransformFunction tffOld;
	static TransformFunction tffmNew;
	static Alphabet alphabet;
	static Status initialStatus;
	static String finishStatus;
	static String[] allStatusInString;
	static ArrayList<Status> allStatus;
	static Queue queue;
	static WriteAutomata wa;

	public static void main(String[] args) {
		testOneFile();
		draw();
		// testAllCase();
		// testGraph();
	}

	public static void testAllCase() {
		for (int i = 1; i <= 5; i++) {
			readAutomata("testcase" + i + ".txt");
			allStatus = new ArrayList<>();
			try {
				process();
				wa = new WriteAutomata("testcase" + i + "_out.txt");
				wa.write(tffmNew, alphabet);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void testOneFile() {
		readAutomata("testcase" + 3 + ".txt");
		allStatus = new ArrayList<>();
		try {
			process();
			wa = new WriteAutomata("testcase" + 3 + "_out.txt");
			wa.write(tffmNew, alphabet);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test1() {
		Status end = tffOld.getTransform("p_1", "c");
		System.out.println(end.toString());
	}

	public static void testGraph() {
		DirectedGraph<String, Integer> g = new DirectedSparseMultigraph<>();
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		// g.addEdge(12, "A", "B", EdgeType.DIRECTED); // This method
		System.out.println(g.findEdge("A", "C"));
		Layout<String, Integer> layout = new CircleLayout(g);
		layout.setSize(new Dimension(300, 300));
		BasicVisualizationServer<String, Integer> vv = new BasicVisualizationServer<String, Integer>(layout);
		vv.setPreferredSize(new Dimension(350, 350));
		// Setup up a new vertex to paint transformer...
		Transformer<String, Paint> vertexPaint = new Transformer<String, Paint>() {
			public Paint transform(String i) {
				if (i.compareTo("A") == 0)
					return Color.RED;
				return Color.GREEN;
			}
		};
		// Set up a new stroke Transformer for the edges
		float dash[] = { 10.0f };
		final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash,
				0.0f);
		Transformer<Integer, Stroke> edgeStrokeTransformer = new Transformer<Integer, Stroke>() {
			public Stroke transform(Integer s) {
				return edgeStroke;
			}
		};

		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		JFrame frame = new JFrame("Simple Graph View 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}

	public static void draw() {
		DirectedGraph<String, MyLink> g = new DirectedSparseMultigraph<>();

		for (Status status : allStatus) {
			g.addVertex(status.toString());
		}
//		System.out.println(allStatus.get(0).toString() + " " + allStatus.get(2).toString());
//		System.out.println(tffmNew.getAllAlphabet(allStatus.get(0), allStatus.get(2)));
		for (int i = 0; i < allStatus.size(); i++) {
			for (int j = 0; j < allStatus.size(); j++) {
				Status b = allStatus.get(i);
				Status e = allStatus.get(j);
				String alphabet = tffmNew.getAllAlphabet(b, e);
				if (g.findEdge(b.toString(), e.toString()) == null
						&& alphabet.compareTo("") != 0) {
					g.addEdge(new MyLink(alphabet), b.toString(), e.toString());
				}
			}
		}

		Layout<String, Integer> layout = new CircleLayout(g);
		layout.setSize(new Dimension(600, 600));
		BasicVisualizationServer<String, Integer> vv = new BasicVisualizationServer<String, Integer>(layout);
		vv.setPreferredSize(new Dimension(350, 350));
		// Setup up a new vertex to paint transformer...
		Transformer<String, Paint> vertexPaint = new Transformer<String, Paint>() {
			public Paint transform(String i) {
				if (i.compareTo("null") == 0)
					return Color.GRAY;
				if(i.compareTo(initialStatus.toString()) == 0){
					return Color.GREEN;
				}
				String[] listEndStatusInString = finishStatus.split(" ");
				for (String endStt : listEndStatusInString) {
					if(i.contains(endStt)) return Color.ORANGE;
				}
				return Color.CYAN;
			}
		};
		// Set up a new stroke Transformer for the edges
		float dash[] = { 10.0f };
		final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash,
				0.0f);

		Transformer<String, Shape> vertexSize = new Transformer<String, Shape>() {
			public Shape transform(String s) {
				Ellipse2D circle = new Ellipse2D.Double(-15, -15, 80, 60);
				// in this case, the vertex is twice as large
				return circle;
			}
		};
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setVertexShapeTransformer(vertexSize);
		// vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		JFrame frame = new JFrame("Simple Graph View 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}

	public static void readAutomata(String filePath) {
		ReadAutomata readAutomata = new ReadAutomata(filePath);
		tffOld = readAutomata.getTfOld();
		alphabet = readAutomata.getAlphabet();
		initialStatus = new Status(readAutomata.getInitialStatus());
		initialStatus.setInitialStatus();
		finishStatus = readAutomata.getFinishStatus();
		allStatusInString = readAutomata.getListStatusInString();
	}

	private static Status temp;

	public static void process() throws InterruptedException {
		tffmNew = new TransformFunction();
		queue = new Queue();
		queue.enqueue(initialStatus);
		// initialQueue();
		allStatus.add(initialStatus);
		ArrayList<String> alpha = alphabet.getAlphabe();
		while (!queue.isEmpty()) {
			Status currentStatus = (Status) queue.dequeue();
			ArrayList<String> listCurrentStatus = currentStatus.getListStatus();
			for (String al : alpha) {
				Transform tf = new Transform();
				tf.setBs(currentStatus);
				tf.setAlphabet(al);
				temp = new Status();
				for (String curStt : listCurrentStatus) {
					Status target = tffOld.getTransform(curStt, al);
					combineStatus(target);
				}
				tf.setEs(temp);
				tffmNew.setTransformFunciton(tf);
				if (!checkStatusExist(temp)) {
					allStatus.add(temp);
					queue.enqueue(temp);
				}
			}
		}
		tffmNew.setAllStatus(allStatus);
		setNewFinishStatus();
	}

	public static void setNewFinishStatus() {
		for (Status status : allStatus) {
			ArrayList<String> listStt = status.getListStatus();
			for (String stt : listStt) {
				if (finishStatus.contains(stt)) {
					status.setFinishStatus();
					continue;
				}
			}
		}
	}

	public static void initialQueue() {
		for (String stt : allStatusInString) {
			Status st = new Status(stt);
			queue.enqueue(st);
		}
	}

	public static void combineStatus(Status stt) {
		ArrayList<String> listStt = stt.getListStatus();
		for (String st : listStt) {
			temp.setStatus(st);
		}
	}

	public static void printTransformFunction(TransformFunction tff) {
		System.out.println(tff.toString());
	}

	public static boolean checkStatusExist(Status stt) {
		for (Status status : allStatus) {
			if (status.compareTo(stt) == 0)
				return true;
		}
		return false;
	}
}
