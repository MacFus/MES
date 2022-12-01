package structures;

import loadfile.ReadElement;
import loadfile.ReadNode;

import java.util.ArrayList;



public class Grid{
    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Element> elements = new ArrayList<>();
    private int numberOfNodes, numberOfElements;

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes() {
        this.nodes = ReadNode.createNodes();
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements() {
        this.elements = ReadElement.createElement();
    }

    public Grid() {
        setNodes();
        setElements();
        setNumberOfNodes();
        setNumberOfElements();
    }

    @Override
    public String toString() {
        return "Grid{\n" +
                "\tnodes=\n" + nodes +
                ",\telements=\n" + elements +
                ", numberOfNodes=" + numberOfNodes +
                ", numberOfElements=" + numberOfElements +
                '}';
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes() {
        this.numberOfNodes = nodes.size();
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements() {
        this.numberOfElements = elements.size();
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        System.out.println(grid);
    }
}
