package functions;

import structures.Element;
import structures.GlobalData;
import structures.Node;

import java.util.ArrayList;

public abstract class Jakobian {

    public static ArrayList<Element> getElements() {
        GlobalData globalData = new GlobalData();
        return globalData.getGrid().getElements();
    }

    public static Node[] getNodes(int elementID) {
        GlobalData globalData = new GlobalData();
        return globalData.getGrid().getElements().get(elementID).getNodes();
    }

    public static ArrayList<double[][]> getJakobian(int elementID, int ile_pc) {
        Node[] nodes = getNodes(elementID);
        double dX_dKsi, dX_dEta, dY_dKsi, dY_dEta;
        ArrayList<double[][]> list = new ArrayList<>();
        dY_dEta = 0;
        dY_dKsi = 0;
        dX_dEta = 0;
        dX_dKsi = 0;
        for (int j = 0; j < Math.pow(ile_pc, 2) ; j++) {
            dY_dEta = 0;
            dY_dKsi = 0;
            dX_dEta = 0;
            dX_dKsi = 0;
            for (int i = 0; i < nodes.length; i++) {
                double x = nodes[i].getX();
                double y = nodes[i].getY();

                if (ile_pc == 2) {
                    dY_dEta += Elem4.dwupunktowy_dN_dEta()[j][i] * y;
                    dY_dKsi += Elem4.dwupunktowy_dN_dKsi()[j][i] * y;
                    dX_dEta += Elem4.dwupunktowy_dN_dEta()[j][i] * x;
                    dX_dKsi += Elem4.dwupunktowy_dN_dKsi()[j][i] * x;
                }
                if (ile_pc == 3) {
                    dY_dEta += Elem4.trojpunktowy_dN_dEta()[j][i] * y;
                    dY_dKsi += Elem4.trojpunktowy_dN_dKsi()[j][i] * y;
                    dX_dEta += Elem4.trojpunktowy_dN_dEta()[j][i] * x;
                    dX_dKsi += Elem4.trojpunktowy_dN_dKsi()[j][i] * x;
                }
                if (ile_pc == 4) {
                    dY_dEta += Elem4.czteropunktowy_dN_dEta()[j][i] * y;
                    dY_dKsi += Elem4.czteropunktowy_dN_dKsi()[j][i] * y;
                    dX_dEta += Elem4.czteropunktowy_dN_dEta()[j][i] * x;
                    dX_dKsi += Elem4.czteropunktowy_dN_dKsi()[j][i] * x;
                }

            }
            double[][] jakob_pc = new double[][]{{dX_dKsi, dY_dKsi}, {dX_dEta, dY_dEta}};
            list.add(jakob_pc);
        }
        return list;
    }

    public static ArrayList<double[][]> getOdwrotnyJakobian(int elementID, int ile_pc) {
        ArrayList<double[][]> jakobian = getJakobian(elementID, ile_pc);
        ArrayList<double[][]> odwroconyJakobian =new ArrayList<>();
        for (double[][] j :jakobian){
            double[][] odwJakobian = {{j[1][1], -j[0][1]},{-j[1][0], j[0][0]}};
            odwroconyJakobian.add(odwJakobian);
        }
        return odwroconyJakobian;
    }

    //ZWRACA 1/detJ * Jakobian danego punktu całkowania
//    public static double[][] getDetJMatrix(double[][] jakobian) {
//        double detJ = (jakobian[0][0] * jakobian[1][1]) - (jakobian[0][1] * jakobian[1][0]);
//        double one_detJ = 1 / detJ;
//
//        double[][] matrix = new double[2][2];
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 2; j++) {
//                matrix[i][j] = jakobian[i][j] * one_detJ;
//            }
//            System.out.println("\t" + matrix[i][0] + "\t" + matrix[i][1]);
//        }
//        return matrix;
//    }

    //Zwraca arrayliste detJ dla pc1,2,3,4
    public static ArrayList<Double> get_1_DetJ(int elementID, int ile_pc) {
        ArrayList<double[][]> list = Jakobian.getJakobian(elementID, ile_pc);
        double detJ = 0;
        ArrayList<Double> listaDetJ = new ArrayList<>();
        for (double[][] j_pc : list) {
            detJ = 1/((j_pc[0][0] * j_pc[1][1]) - (j_pc[0][1] * j_pc[1][0]));
            listaDetJ.add(detJ);
        }
        return listaDetJ;
    }

    // dy/dEta   -dy/dKsi
    // -dx/dEta  dx/dKsi

    public static void print(int ile_pc) {
        ArrayList<double[][]> list = Jakobian.getJakobian(0, ile_pc);
        System.out.println(Jakobian.getElements().get(0));
        int k = 0;
        for (double[][] jakob_pc : list) {

            System.out.println("Dla " + (k + 1) + " punktu całkowania:");
            for (int j = 0; j < 2; j++) {
                System.out.println("\t" + jakob_pc[j][0] + ";\t" + jakob_pc[j][1]);
            }
            k++;
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        Jakobian.getDetJ(0);
        Jakobian.getJakobian(0,2);
//        Jakobian.print(3);
//        Jakobian.print(4);
        System.out.println(Jakobian.get_1_DetJ(0,2).get(0));

//        for (int i = 0; i < Jakobian.getElements().size(); i++) {
//            System.out.println(Jakobian.getElements().get(i));
//            int k = 0;
//            for (double[][] jakob_pc : list) {
//                k++;
//                System.out.println("Dla " + k + " punktu całkowania:");
//                for (int j = 0; j < 2; j++) {
//                    System.out.println("\t" + jakob_pc[j][0] + ";\t" + jakob_pc[j][1]);
//                }
//                System.out.println();
//            }
//        }


        //System.out.println(Jakobian.getElements());
    }
}
