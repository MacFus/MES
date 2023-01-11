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

    public static ArrayList<double[][]> uzupelnij_H_bc_kazdego_elementu(int ile_pc) {
        int alfa = globalData.getAlfa();
        ArrayList<double[]> funkcja_n_dla_pc;
        ArrayList<double[][]> macierze_H_BC_dla_kazdego_elementu = new ArrayList<>();
        int iterator_elementu = 0;
        double[][] macierzHBC;

        for (Element e : elements) {
            int t = 0;
            Node[] nodes = elements.get(iterator_elementu).getNodes();
            Node node1, node2;
            macierzHBC = new double[4][4];
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

                    //pc ksi        eta   N1         N2            N3       N4
                    //1 -0,7746     -1    0,887298   0,112702      0        0
                    for (int a = t; a < t + ile_pc; a++) {
                        if (ile_pc == 2) {
                            double ksi = pc_2[a][0];
                            double eta = pc_2[a][1];
                            funkcja_n_dla_pc.add(new double[]{dN1(ksi, eta), dN2(ksi, eta), dN3(ksi, eta), dN4(ksi, eta)});
                        }
                        if (ile_pc == 3) {
                            double ksi = pc_3[a][0];
                            double eta = pc_3[a][1];
                            funkcja_n_dla_pc.add(new double[]{dN1(ksi, eta), dN2(ksi, eta), dN3(ksi, eta), dN4(ksi, eta)});
                        }
                        if (ile_pc == 4) {
                            double ksi = pc_4[a][0];
                            double eta = pc_4[a][1];
                            funkcja_n_dla_pc.add(new double[]{dN1(ksi, eta), dN2(ksi, eta), dN3(ksi, eta), dN4(ksi, eta)});
                        }
                    }


                    //Dodaje macierze każdego punktu całkowania do jednej
                    double[] wagi = new double[0];
                    if (ile_pc == 2) {
                        wagi = Elem4.wagi_2;
                    } else if (ile_pc == 3) {
                        wagi = Elem4.wagi_3;
                    } else if (ile_pc == 4) {
                        wagi = Elem4.wagi_4;
                    }
                    int m = 0;
                    for (double[] fun_pc : funkcja_n_dla_pc) {

                        // dla każdego pkt całkowania
                        for (int k = 0; k < fun_pc.length; k++) {
                            for (int l = 0; l < fun_pc.length; l++) {
                                double suma = fun_pc[k] * fun_pc[l] * detJ * alfa;
                                macierzHBC[k][l] += suma * wagi[m];
                            }
                        }
                        m++;
                    }
                }
                t += ile_pc;

            }
            macierze_H_BC_dla_kazdego_elementu.add(macierzHBC);
            iterator_elementu++;
        }
        return macierze_H_BC_dla_kazdego_elementu;
    }

    private static double[][] multiply(ArrayList<double[]> macierz_N_pc, double detJ) {
        double[][] macierzHBC = new double[4][4];
        // dla każdego pkt całkowania
        for (int i = 0; i < macierz_N_pc.size(); i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    double suma = macierz_N_pc.get(i)[j] * macierz_N_pc.get(i)[k];

                    macierzHBC[j][k] = suma * detJ;
                }
            }
        }
        return macierzHBC;
    }

    public static ArrayList<double[]> oblicz_wektor_P_kazdego_elementu(int ile_pc) {
        int alfa = globalData.getAlfa();
        ArrayList<double[]> funkcja_n_dla_pc;
        ArrayList<double[]> wektor_P_dla_kazdego_elementu = new ArrayList<>();
        int iterator_elementu = 0;
        double[] wektorP;

        for (Element e : elements) {
            int t = 0;
            Node[] nodes = elements.get(iterator_elementu).getNodes();
            Node node1, node2;
            wektorP = new double[4];
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

                    //pc ksi        eta   N1         N2            N3       N4
                    //1 -0,7746     -1    0,887298   0,112702      0        0
                    for (int a = t; a < t + ile_pc; a++) {
                        if (ile_pc == 2) {
                            double ksi = pc_2[a][0];
                            double eta = pc_2[a][1];
                            funkcja_n_dla_pc.add(new double[]{dN1(ksi, eta), dN2(ksi, eta), dN3(ksi, eta), dN4(ksi, eta)});
                        }
                        if (ile_pc == 3) {
                            double ksi = pc_3[a][0];
                            double eta = pc_3[a][1];
                            funkcja_n_dla_pc.add(new double[]{dN1(ksi, eta), dN2(ksi, eta), dN3(ksi, eta), dN4(ksi, eta)});
                        }
                        if (ile_pc == 4) {
                            double ksi = pc_4[a][0];
                            double eta = pc_4[a][1];
                            funkcja_n_dla_pc.add(new double[]{dN1(ksi, eta), dN2(ksi, eta), dN3(ksi, eta), dN4(ksi, eta)});
                        }
                    }
                    int w_pc = 0;
                    double[] wagi = new double[0];
                    if (ile_pc == 2) {
                        wagi = Elem4.wagi_2;
                    } else if (ile_pc == 3) {
                        wagi = Elem4.wagi_3;
                    } else if (ile_pc == 4) {
                        wagi = Elem4.wagi_4;
                    }
                    int m = 0;

                    //Dodaje macierze każdego punktu całkowania do jednej
                    for (double[] fun_pc : funkcja_n_dla_pc) {
                        // dla każdego pkt całkowania
                        for (int k = 0; k < fun_pc.length; k++) {
                            double suma = fun_pc[k] * detJ * alfa * globalData.getTot();
                            wektorP[k] += suma * wagi[m];
                        }
                        m++;
                    }
                }
                t += ile_pc;
            }
            wektor_P_dla_kazdego_elementu.add(wektorP);

            iterator_elementu++;
        }
        return wektor_P_dla_kazdego_elementu;
    }

    public static void print_wektor_p(int ile_pc){
        int e = 0;
        for (double[] p: oblicz_wektor_P_kazdego_elementu(ile_pc)) {
            System.out.println("Wektor elementu: " + e);
            for (int i = 0; i < 4; i++) {
                System.out.println(p[i]);
            }
            e++;
            System.out.println();
        }
    }


    public static void main(String[] args) {
//        Elem4_bok elem4_bok = new Elem4_bok();
        Elem4_bok.uzupelnij_H_bc_kazdego_elementu(2);
//        Elem4_bok.oblicz_wektor_P_kazdego_elementu(2);
//        Elem4_bok.print_wektor_p(4);
//        Elem4_bok.print();
        System.out.println();
    }


}
