package functions;

import structures.Element;
import structures.GlobalData;
import structures.Node;

import java.util.ArrayList;
import java.util.List;

public class Jakobian {
    public List<Element> elements;
    public List<ArrayList<double[][]>> lista_jakobianow = new ArrayList<>();
    public List<ArrayList<double[][]>> lista_odwrotnych_jakobianow = new ArrayList<>();
    public List<ArrayList<Double>> lista_1_detJ_jakobianow = new ArrayList<>();

    public Jakobian(int ile_pc) {
        this.elements = getElements();
        getJakobian(ile_pc);
        get_1_DetJ();
        Elem4_bok.uzupelnij_H_bc_kazdego_elementu(ile_pc);
    }

    public static ArrayList<Element> getElements() {
        GlobalData globalData = new GlobalData();
        return globalData.getGrid().getElements();
    }


    public void getJakobian(int ile_pc) {

        ArrayList<double[][]> listaJakobianowElementu = new ArrayList<>();
        ArrayList<double[][]> listaOdwroconychJakobianowElementu = new ArrayList<>();

        for (Element e : this.elements) {
            Node[] nodes = e.getNodes();
            double dX_dKsi, dX_dEta, dY_dKsi, dY_dEta;

            listaJakobianowElementu = new ArrayList<>();
            listaOdwroconychJakobianowElementu = new ArrayList<>();

            dY_dEta = 0;
            dY_dKsi = 0;
            dX_dEta = 0;
            dX_dKsi = 0;
            for (int j = 0; j < Math.pow(ile_pc, 2); j++) {
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
                double[][] odwrocony_jakob_pc = new double[][]{{dY_dEta, -dY_dKsi}, {-dX_dEta, dX_dKsi}};
                listaJakobianowElementu.add(jakob_pc);
                listaOdwroconychJakobianowElementu.add(odwrocony_jakob_pc);
            }

            lista_jakobianow.add(listaJakobianowElementu);
            lista_odwrotnych_jakobianow.add(listaOdwroconychJakobianowElementu);
        }
    }

    public void get_1_DetJ() {
        double detJ = 0;
        ArrayList<Double> listaDetJ = new ArrayList<>();

        for(ArrayList<double[][]> innerList : lista_jakobianow) {
            listaDetJ = new ArrayList<>();
            for(double[][] j_pc : innerList) {
                detJ = 1 / ((j_pc[0][0] * j_pc[1][1]) - (j_pc[0][1] * j_pc[1][0]));
                listaDetJ.add(detJ);
            }
            this.lista_1_detJ_jakobianow.add(listaDetJ);
        }
    }

    public static void main(String[] args) {
        Jakobian jakobian = new Jakobian(2);
//        jakobian.getJakobian(2);

        System.out.println();
    }
}
