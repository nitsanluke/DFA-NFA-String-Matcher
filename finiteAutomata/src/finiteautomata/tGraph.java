package finiteautomata;

/**
 *
 * @author Nitish
 */
import java.util.Random;
import javax.swing.JOptionPane;
//import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.view.mxGraph;
import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;



public class tGraph extends JFrame {

    private static final long serialVersionUID = -2707712944901661771L;
    public static Object[][] data;
    public static Object[] vertex;
    public static String[] vertexNames;
    public static String columnNames;
    public static String initialState, finalState, type;
    JFrame frame2;
    

    public tGraph() {
        super("Transition Graph");
    }

    @SuppressWarnings("static-access")
    public void genGraph() {


        mxGraph graph = new mxGraph();

        Object parent = graph.getDefaultParent();
       

        graph.getModel().beginUpdate();



        vertex = new Object[this.data.length];
        vertexNames = new String[this.data.length];
        try {

            for (int i1 = 0; i1 < this.data[0].length; i1++) {
                for (int j1 = 0; j1 < this.data.length; j1++) {


                    if (i1 == 0) {
                        Random gen = new Random();
                        int posX = gen.nextInt(500);
                        int posY = gen.nextInt(500);
                        if (this.initialState.equalsIgnoreCase(this.data[j1][i1].toString())) {
                            vertex[j1] = graph.insertVertex(parent, null, this.data[j1][i1].toString(), posX, posY, 80, 80, "fillColor=white;shape=ellipse;perimeter=ellipsePerimeter");
                        } else {

                            String fstate[] = this.finalState.split(",");

                            for (int q = 0; q < fstate.length; q++) {

                                if (fstate[q].equalsIgnoreCase(this.data[j1][i1].toString())) {
                                    vertex[j1] = graph.insertVertex(parent, null, this.data[j1][i1].toString(), posX, posY, 80, 80, "strokeColor=black;fillColor=green;shape=ellipse;perimeter=ellipsePerimeter");
                                    break;
                                } 
                            }
                   if(vertex[j1] == null){vertex[j1] = graph.insertVertex(parent, null, this.data[j1][i1].toString(), posX, posY, 80, 60, "strokeColor=yellow;fillColor=yellow;shape=ellipse;perimeter=ellipsePerimeter");}
                        }

                        vertexNames[j1] = this.data[j1][i1].toString();

                    } else {

                        if (this.data[j1][i1] != null) {

                            if (this.data[j1][i1].toString().length() > 2) {

                                // for (int m = 0; (m + 1) < this.data[j1][i1].toString().length(); m = m + 2) {

                                //     String state = String.valueOf(this.data[j1][i1].toString().charAt(m))
                                //           + String.valueOf(this.data[j1][i1].toString().charAt(m + 1));

                                // System.out.println(state);

                                int z1, z2;
                                String state[] = this.data[j1][i1].toString().split(",");

                                for (z1 = 0; z1 < state.length; z1++) {

                                    for (z2 = 0; z2 < vertex.length; z2++) {

                                        if (state[z1].equalsIgnoreCase(vertexNames[z2])) {
                                            break;
                                        }
                                    }


                                    if (z2 < vertex.length) {
                                        graph.insertEdge(parent, null, this.columnNames.charAt(i1 - 1), vertex[j1], vertex[z2]);

                                    } else {
                                        JOptionPane.showMessageDialog(frame2, "Exception Occured During Data Entry","ERROR", JOptionPane.ERROR_MESSAGE);
                                        System.exit(1);
                                    }


                                }
                            } else {
                                int z;
                                for (z = 0; z < vertex.length; z++) {

                                    if (this.data[j1][i1].toString().equalsIgnoreCase(vertexNames[z])) {
                                        break;
                                    }
                                }
                                if (z < vertex.length) {
                                    graph.insertEdge(parent, null, this.columnNames.charAt(i1 - 1), vertex[j1], vertex[z]);
                                } else {
                                    JOptionPane.showMessageDialog(frame2,"Exception Occured During Data Entry", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    System.exit(1);
                                }
                            }
                        }
                    }


                }
            }
        } finally {

            graph.getModel().endUpdate();
            
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);

        getContentPane().add(graphComponent);
         graph.setEnabled(false);
        

        draw();
        if (this.type.equalsIgnoreCase("dfa")) {

            stringMatcher matcher = new stringMatcher();
            matcher.sendGraph(this.data, this.initialState, this.finalState, this.columnNames);
            matcher.check();
        }

         if (this.type.equalsIgnoreCase("nfa")) {

            nfaStringMatcher matcher = new nfaStringMatcher();
           matcher.sendGraph(this.data, this.initialState, this.finalState, this.columnNames);
           matcher.check();
        }
    }

    public void sendData(String type, String columns, Object[][] data, String initialState, String finalState) {


        this.columnNames = columns;
        this.type = type;
        this.data = new Object[data.length][data[0].length];


        System.arraycopy(data, 0, this.data, 0, data.length);

        this.initialState = initialState;
        this.finalState = finalState;


        /* for (int i = 0; i < data.length; i++) {
        for (int j = 0; j < data[0].length; j++) {
        System.out.println(transitionGraph.data[i][j]);
        }
        }*/


    }
    


    public void draw() {
        //tGraph frame = new tGraph();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }
}
