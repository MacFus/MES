package loadfile;

import structures.Element;
import structures.Node;

import java.util.*;


public abstract class ReadElement {

    private static final String[] fileContent = ReadFile.readFileContent();

    private static ArrayList<String> readElementList() {
        ArrayList<String> elementList = new ArrayList<>();
        for (int i = 0; i < fileContent.length; i++) {
            if (fileContent[i].contains("*Element")) {
                for (int j = i + 1; j < fileContent.length; j++) {
                    if (fileContent[j].contains("*BC")) {
                        break;
                    }
                    elementList.add(fileContent[j]);
                }
                break;
            }
        }
        return elementList;
    }

    public static <T> ArrayList<Node> removeDuplicates(ArrayList<Node> list)
    {
        ArrayList<Node> noDuplicates = new ArrayList<>();
        for (int i = 0; i < (list.size()); i++) {
                noDuplicates.add(list.get(i));
        }
        // return the list
        return noDuplicates;
    }
    public static ArrayList<Element> createElement( ) {
        ArrayList<Element> elements = new ArrayList<>();
        ArrayList<Node> nodeList = ReadNode.createNodes();
        ArrayList<Node> noDuplicates = removeDuplicates(nodeList);

        for (String line : readElementList()) {
            String[] splitNode = line.split("\\s+");
            Node[] nodesOfElement = new Node[]{
                    noDuplicates.get(Integer.parseInt(String.valueOf(splitNode[2])) - 1),
                    noDuplicates.get(Integer.parseInt(String.valueOf(splitNode[3])) - 1),
                    noDuplicates.get(Integer.parseInt(String.valueOf(splitNode[4])) - 1),
                    noDuplicates.get(Integer.parseInt(String.valueOf(splitNode[5])) - 1)
            };

            int id = Integer.parseInt(String.valueOf(splitNode[1]));
            elements.add(new Element(id, nodesOfElement));
        }
        return elements;
    }

    public static void main(String[] args) {
        ReadElement.createElement();
    }
}
