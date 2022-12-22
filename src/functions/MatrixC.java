package functions;

import structures.GlobalData;

import java.util.ArrayList;
import java.util.List;

public class MatrixC {
    GlobalData globalData;
    static Jakobian jakobian;
    public List<double[][]> lista_macierzy_c_elementow = new ArrayList<>();

    public MatrixC(int ile_pc) {
        globalData = new GlobalData();
        jakobian = new Jakobian(ile_pc);
        macierz_C_dla_kazdego_elementu(ile_pc);
    }

    double[] pc_2 = {(-1 / Math.sqrt(3)), (1 / Math.sqrt(3))};

    double[] pc_3 = {-Math.sqrt(3.0 / 5), 0, Math.sqrt(3.0 / 5)};

    double[] pc_4 = {
            -Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)),
            -Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)),
            Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)),
            Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0))};

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

    public void macierz_C_dla_kazdego_elementu(int ile_pc) {
        ArrayList<double[]> funkcja_n_dla_pc;
        double[][] macierzC;
        double specificHeat = globalData.getSpecificHeat();
        double density = globalData.getDensity();
        for (int i = 0; i < globalData.getNumberOfElements(); i++) {
            funkcja_n_dla_pc = new ArrayList<>();
            macierzC = new double[4][4];
            for (int j = 0; j < ile_pc; j++) {
                for (int k = 0; k < ile_pc; k++) {
                    if (ile_pc == 2) {
                        double ksi = pc_2[k];
                        double eta = pc_2[j];
                        funkcja_n_dla_pc.add(new double[]{dN1(ksi, eta), dN2(ksi, eta), dN3(ksi, eta), dN4(ksi, eta)});
                    }
                    if (ile_pc == 3) {
                        double ksi = pc_3[k];
                        double eta = pc_3[j];
                        funkcja_n_dla_pc.add(new double[]{dN1(ksi, eta), dN2(ksi, eta), dN3(ksi, eta), dN4(ksi, eta)});
                    }
                    if (ile_pc == 4) {
                        double ksi = pc_4[k];
                        double eta = pc_4[j];
                        funkcja_n_dla_pc.add(new double[]{dN1(ksi, eta), dN2(ksi, eta), dN3(ksi, eta), dN4(ksi, eta)});
                    }
                }
            }
            double[] wagi = new double[0];
            if (ile_pc == 2) {
                wagi = Elem4.w_2;
            } else if (ile_pc == 3) {
                wagi = Elem4.w_3;
            } else if (ile_pc == 4) {
                wagi = Elem4.w_4;
            }
            int m = 0, p = 0;
            for (double[] fun_pc : funkcja_n_dla_pc) {
                double detJ = jakobian.lista_detJ_jakobianow.get(i).get(p);
                // dla każdego pkt całkowania
                for (int k = 0; k < fun_pc.length; k++) {
                    for (int l = 0; l < fun_pc.length; l++) {
                        double suma = fun_pc[k] * fun_pc[l] * specificHeat * density * 1/detJ;
                        macierzC[k][l] += suma * wagi[m];
                    }
                }
                p++;
                m++;
            }
            lista_macierzy_c_elementow.add(macierzC);
        }

    }

    public static void main(String[] args) {
        MatrixC matrixC = new MatrixC(2);
        System.out.println();
    }
}
