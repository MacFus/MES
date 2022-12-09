package structures;

public class Element extends Node{
    private int id;
    private Node[] nodes = new Node[4];
    public double [][] macierz_H_BC;

    public Node[] getNodes() {
        return nodes;
    }
    private String printNodes(){
        return "\n" + nodes[0] + ", " + nodes[1] + ", " + nodes[2] + ", " + nodes[3];
    }
    @Override
    public String toString() {


        String s = "\tElement{" +
                "id=" + id + " Nodes: " + printNodes();
        return s;
    }

    public Element(int id, Node[] nodes) {
        super();
        this.id = id;
        this.nodes = nodes;
        this.macierz_H_BC = new double[4][4];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
