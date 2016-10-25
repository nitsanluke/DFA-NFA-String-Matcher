package finiteautomata;

/**
 *
 * @author Nitish
 */
import java.util.Random;
import javax.swing.JOptionPane;
import com.mxgraph.view.mxGraph;
import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;
import java.util.*;

public class convertedDfaGraph extends JFrame {

    private static final long serialVersionUID = -2707712944901661771L;
    static ArrayList<String> inputEdge;
    static ArrayList<String> sourceEdge;
    static ArrayList<String> targetEdge;
    static ArrayList<String> finalState;
    static ArrayList<String> states;
    static String initialState;
    JFrame frame2;

    public convertedDfaGraph() {
        super("Converted Transition Graph");
    }

    @SuppressWarnings("static-access")
    public void genGraph() {


        mxGraph graph = new mxGraph();

        Object parent = graph.getDefaultParent();


        graph.getModel().beginUpdate();

        Object vertex[] = new Object[this.states.size()];

        try {

            for (int z1 = 0; z1 < vertex.length; z1++) {

                Random gen = new Random();
                int posX = gen.nextInt(500);
                int posY = gen.nextInt(500);

                if (this.initialState.equalsIgnoreCase(this.states.get(z1))) {
                    vertex[z1] = graph.insertVertex(parent, null, this.states.get(z1), posX, posY, 80, 80, "fillColor=white;shape=ellipse;perimeter=ellipsePerimeter");

                }
                if (this.finalState.contains(this.states.get(z1))) {
                    vertex[z1] = graph.insertVertex(parent, null, this.states.get(z1), posX, posY, 80, 80, "strokeColor=black;fillColor=green;shape=ellipse;perimeter=ellipsePerimeter");

                }

                if (vertex[z1] == null) {

                    vertex[z1] = graph.insertVertex(parent, null, this.states.get(z1), posX, posY, 80, 60, "strokeColor=yellow;fillColor=yellow;shape=ellipse;perimeter=ellipsePerimeter");
                }

            }
            for (int z2 = 0; z2 < this.sourceEdge.size(); z2++) {

                int v1 = 0;
                int v2 = 0;
                v1 = this.states.indexOf(this.sourceEdge.get(z2));
                v2 = this.states.indexOf(this.targetEdge.get(z2));
                System.out.println("v1 " + v1 + " v2 " + v2);
                if (v1 != -1 && v2 != -1) {
                    graph.insertEdge(parent, null, this.inputEdge.get(z2).toString(), vertex[v1], vertex[v2]);

                }
            }

        } finally {

            graph.getModel().endUpdate();

        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);

        getContentPane().add(graphComponent);
        graph.setEnabled(false);


        draw();

    }

    public void sendData(ArrayList<String> dfaStates, ArrayList<String> sourceEdge, ArrayList<String> targetEdge, ArrayList<String> inputEdge, ArrayList<String> finalState, String initialState) {

        this.states = dfaStates;
        this.sourceEdge = sourceEdge;
        this.targetEdge = targetEdge;
        this.inputEdge = inputEdge;
        this.finalState = finalState;
        this.initialState = initialState;

        for (int z2 = 0; z2 < this.targetEdge.size(); z2++) {

            System.out.println("--" + this.inputEdge.get(z2).toString() + "--" + this.sourceEdge.get(z2).toString() + "==" + this.targetEdge.get(z2).toString());

        }

    }

    public void draw() {
        //tGraph frame = new tGraph();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }
}
