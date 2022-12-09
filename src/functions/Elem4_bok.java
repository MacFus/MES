package functions;

import structures.Element;
import structures.GlobalData;
import structures.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class Elem4_bok {
    static GlobalData globalData = new GlobalData();
    public static ArrayList<Element> getElements() {
        GlobalData globalData = new GlobalData();
        return globalData.getGrid().getElements();
    }

    public static List<Element> elements = getElements();

    static double[][] pc_2 = new double[][]{
            {(-1 / Math.sqrt(3)), -1}, {(1 / Math.sqrt(3)), -1},
            {1, (-1 / Math.sqrt(3))}, {1, (1 / Math.sqrt(3))},
            {(-1 / Math.sqrt(3)), 1}, {(1 / Math.sqrt(3)), 1},
            {-1, (-1 / Math.sqrt(3))}, {-1, (1 / Math.sqrt(3))}};
    static double[][] pc_3 = {
            {-Math.sqrt(3.0 / 5), -1}, {0, -1}, {Math.sqrt(3.0 / 5), -1},
            {1, -Math.sqrt(3.0 / 5)}, {1, 0}, {1, Math.sqrt(3.0 / 5), -1},
            {-Math.sqrt(3.0 / 5), 1}, {0, 1}, {Math.sqrt(3.0 / 5), 1},
            {-1, -Math.sqrt(3.0 / 5)}, {-1, 0}, {-1, Math.sqrt(3.0 / 5), -1}};
    static double[][] pc_4 = {
            {-Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -1},
            {-Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -1},
            {Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -1},
            {Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -1},
            {1, -Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0))},
            {1, -Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0))},
            {1, Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0))},
            {1, Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0))},
            {-Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), 1},
            {-Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), 1},
            {Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), 1},
            {Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), 1},
            {-1, -Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0))},
            {-1, -Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0))},
            {-1, Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0))},
            {-1, Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0))},

    };

    static double dN1(double ksi, double eta) {
        return 0.25 * (1 - ksi) * (1 - eta);
    }

    static double dN2(double ksi, double eta) {
        return 0.25 * (1 + ksi) * (1 - eta);
    }

    static double dN3(double ksi, double eta) {
        return 0.25 * (1 + ksi) * (1 + eta);
    }

    static double dN4(double ksi, double eta) {
        return 0.25 * (1 - ksi) * (1 + eta);
    }

    public static void uzupelnij_H_bc_kazdego_elementu(int ile_pc) {
        ArrayList<double[]> funkcja_n_dla_pc;
        int iterator_elementu = 0;
        for (Element e : elements) {
            int t = 0;
            Node[] nodes = elements.get(iterator_elementu).getNodes();
            Node node1, node2;

            //ITEROWANIE PO ŚCIANACH ELEMENTU
            for (int i = 0; i < 4; i++) { //bo 4 ściany
                funkcja_n_dla_pc = new ArrayList<>();
                if (i != 3) {
                    node1 = nodes[i];
                    node2 = nodes[i + 1];
                } else {
                    node1 = nodes[3];
                    node2 = nodes[0];
                }

                //SPRAWDZANIE WARUNKU CZY BC ŚCIANY == 1
                if (node1.getBc() == 1 && node2.getBc() == 1) {

                    //OBLICZANIE DET_J
                    double dl_boku = Math.pow((node2.getX() - node1.getX()), 2) + Math.pow((node2.getY() - node1.getY()), 2);
                    double detJ = Math.sqrt(dl_boku) / 2.0;

                    //DLA 2 PUNKTÓW CAŁKOWANIA
                    if (ile_pc == 2) {
                        double ksi_1 = pc_2[t][0];
                        double eta_1 = pc_2[t][1];
                        double ksi_2 = pc_2[t + 1][0];
                        double eta_2 = pc_2[t + 1][1];
                        //Zapisuje dla 2 punktów całkowania funkcje kształtu
                        funkcja_n_dla_pc.add(new double[]{dN1(ksi_1, eta_1), dN2(ksi_1, eta_1), dN3(ksi_1, eta_1), dN4(ksi_1, eta_1)});
                        funkcja_n_dla_pc.add(new double[]{dN1(ksi_2, eta_2), dN2(ksi_2, eta_2), dN3(ksi_2, eta_2), dN4(ksi_2, eta_2)});
                        
                        
                        //Dodawanie do macierzy H_BC elementu
                        //Ściana dla funkcji N1 - N2
                        if (funkcja_n_dla_pc.get(0)[0] != 0 && funkcja_n_dla_pc.get(0)[1] != 0) {
                            for (int k = 0; k < 4; k++) {
                                e.macierz_H_BC[0][k] += funkcja_n_dla_pc.get(0)[k];
                                e.macierz_H_BC[1][k] += funkcja_n_dla_pc.get(1)[k];
                            }
//                            System.out.println("ściana 1");
                        }
                        //Ściana dla funkcji N2 - N3
                        if (funkcja_n_dla_pc.get(0)[1] != 0 && funkcja_n_dla_pc.get(0)[2] != 0) {
                            for (int k = 0; k < 4; k++) {
                                e.macierz_H_BC[1][k] += funkcja_n_dla_pc.get(0)[k];
                                e.macierz_H_BC[2][k] += funkcja_n_dla_pc.get(1)[k];
                            }
//                            System.out.println("ściana 2");
                        }
                        //Ściana dla funkcji N3 - N4
                        if (funkcja_n_dla_pc.get(0)[2] != 0 && funkcja_n_dla_pc.get(0)[3] != 0) {
                            for (int k = 0; k < 4; k++) {
                                e.macierz_H_BC[2][k] += funkcja_n_dla_pc.get(0)[k];
                                e.macierz_H_BC[3][k] += funkcja_n_dla_pc.get(1)[k];
                            }
//                            System.out.println("ściana 3");
                        }
                        //Ściana dla funkcji N4 - N1
                        if (funkcja_n_dla_pc.get(0)[0] != 0 && funkcja_n_dla_pc.get(0)[3] != 0) {
                            for (int k = 0; k < 4; k++) {
                                e.macierz_H_BC[0][k] += funkcja_n_dla_pc.get(0)[k];
                                e.macierz_H_BC[3][k] += funkcja_n_dla_pc.get(1)[k];
                            }
//                            System.out.println("ściana 4");
                        }
                    }
                }
                t += 2;
            }
            iterator_elementu++;
        }
    }

    private static double[][] multiply(double[][] macierz_pc1, double[][] macierz_pc2, double detJ) {
        double[][] macierzHBC = new double[4][4];
        int wsp_konwekcji = globalData.getConductivity();
        // dla każdego pkt całkowania
        for (int i = 0; i < macierz_pc1.length; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    double suma_pc1 = macierz_pc1[i][j] * macierz_pc1[i][k];
                    double suma_pc2 = macierz_pc2[i][j] * macierz_pc2[i][k];
                    double suma = suma_pc1 + suma_pc2;
                    macierzHBC[j][k] = suma * detJ * wsp_konwekcji;
                }
            }
        }
        return macierzHBC;
    }

    public static void print() {
        for (Element element : elements) {
            System.out.println("Element: " + element.getId());
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(element.macierz_H_BC[j][k] + ";\t ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
//        Elem4_bok elem4_bok = new Elem4_bok();
        Elem4_bok.uzupelnij_H_bc_kazdego_elementu(2);
        Elem4_bok.print();
    }


}
