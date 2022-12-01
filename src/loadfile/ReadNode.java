package loadfile;

import structures.Node;

import java.util.ArrayList;


public abstract class ReadNode {
    private static final String[] fileContent = ReadFile.readFileContent();

    private static ArrayList<String> readNodeList() {
        ArrayList<String> nodeList = new ArrayList<>();
        for (int i = 0; i < fileContent.length; i++) {
            if (fileContent[i].contains("*Node")) {
                for (int j = i + 1; j < fileContent.length; j++) {
                    if (fileContent[j].contains("*Element")) {
                        break;
                    }
                    nodeList.add(fileContent[j]);
                }

            }

        }
//        for (String s : nodeList) {
//            System.out.println(s);
//        }

        return nodeList;
    }

    private static String[] readBCList() {
        String bcList = null;
        bcList = fileContent[(fileContent.length - 1)];

        String[] bc = bcList.split("\\s+");
        return bc;
    }

    public static ArrayList<Node> createNodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        String[] bc = readBCList();
        ArrayList<String> nodeList = readNodeList();


        for (String line : nodeList) {
            String[] splitNode = line.split("\\s+");
            nodes.add(new Node(Integer.parseInt(String.valueOf(splitNode[1])), Double.parseDouble(String.valueOf(splitNode[2])), Double.parseDouble(String.valueOf(splitNode[3]))));
        }
        for (String index : bc) {
            nodes.get(Integer.parseInt(index) - 1).setBc(1);
        }
//        for (Node node : nodes) {
//            System.out.println(node);
//        }

        return nodes;
    }

    public static void main(String[] args) {
        ReadNode.createNodes();


    }
}
