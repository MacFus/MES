package functions;

import structures.Element;
import structures.GlobalData;

import java.util.ArrayList;
import java.util.List;

public abstract class Elem4 extends Elem4_bok {
    static GlobalData globalData = new GlobalData();
    static double[] wagi_2 = {1, 1};
    static double[] wagi_3 = {5.0 / 9.0, 8.0 / 9.0, 5.0 / 9.0};
    static double[] wagi_4 = {(18 - Math.sqrt(30)) / 36, (18 + Math.sqrt(30)) / 36, (18 + Math.sqrt(30)) / 36, (18 - Math.sqrt(30)) / 36};

    static double[] calculate_wagi(int ile_pc) {
        List<Double> w = new ArrayList<>();
        for (int i = 0; i < ile_pc; i++) {
            for (int j = 0; j < ile_pc; j++) {
                if (ile_pc == 2)
                    w.add(wagi_2[i] * wagi_2[j]);
                if (ile_pc == 3)
                    w.add(wagi_3[i] * wagi_3[j]);
                if (ile_pc == 4)
                    w.add(wagi_4[i] * wagi_4[j]);
            }
        }
        double[] wagi = new double[(int) Math.pow(ile_pc, 2)];
        int i = 0;
        for (Double waga : w) {
            wagi[i] = waga;
            i++;
        }
        return wagi;
    }

    static double[] w_2 = calculate_wagi(2);
    static double[] w_3 = calculate_wagi(3);
    static double[] w_4 = calculate_wagi(4);

    static double[] pc_2 = new double[]{(-1 / Math.sqrt(3)), (1 / Math.sqrt(3))};
    static double[] pc_3 = new double[]{-Math.sqrt(3.0 / 5), 0, Math.sqrt(3.0 / 5)};
    static double[] pc_4 = new double[]{-Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0))};
    static double[] pc_2_ksi = new double[]{(-1 / Math.sqrt(3)), (1 / Math.sqrt(3)), -1 / Math.sqrt(3), 1 / Math.sqrt(3)};
    static double[] pc_2_eta = new double[]{(-1 / Math.sqrt(3)), (-1 / Math.sqrt(3)), 1 / Math.sqrt(3), 1 / Math.sqrt(3)};
    static double[] pc_3_ksi = {-Math.sqrt(3.0 / 5), 0, Math.sqrt(3.0 / 5), -Math.sqrt(3.0 / 5), 0, Math.sqrt(3.0 / 5), -Math.sqrt(3.0 / 5), 0, Math.sqrt(3.0 / 5)};
    static double[] pc_3_eta = {-Math.sqrt(3.0 / 5), -Math.sqrt(3.0 / 5), -Math.sqrt(3.0 / 5), 0, 0, 0, Math.sqrt(3.0 / 5), Math.sqrt(3.0 / 5), Math.sqrt(3.0 / 5)};
    static double[] pc_4_ksi = {-Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0))};
    static double[] pc_4_eta = {-Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)),
            -Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), -Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)),
            Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) - (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)),
            Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0)), Math.sqrt((3.0 / 7) + (2.0 / 7.0) * Math.sqrt(6.0 / 5.0))};

    static double[] dN_dKsi(double x) {
        double[] n = new double[]{(-1.0 / 4) * (1.0 - x), (1.0 / 4) * (1.0 - x), (1.0 / 4) * (1.0 + x), (-1.0 / 4) * (1.0 + x)};
        return n;
    }

    static double[] dN_dEta(double x) {
        double[] n = new double[]{(-1.0 / 4) * (1.0 - x), (-1.0 / 4) * (1.0 + x), (1.0 / 4) * (1.0 + x), (1.0 / 4) * (1.0 - x)};
        return n;
    }

    public static double[][] dwupunktowy_dN_dKsi() {
        double[][] table = new double[pc_2_eta.length][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                table[i][j] = dN_dKsi(pc_2_eta[i])[j];
            }
        }
        return table;
    }

    public static double[][] dwupunktowy_dN_dEta() {
        double[][] table = new double[pc_2_ksi.length][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                table[i][j] = dN_dEta(pc_2_ksi[i])[j];
            }
        }
        return table;
    }

    public static double[][] trojpunktowy_dN_dKsi() {
        double[][] table = new double[pc_3_eta.length][4];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                table[i][j] = dN_dKsi(pc_3_eta[i])[j];
            }
        }
        return table;
    }

    public static double[][] trojpunktowy_dN_dEta() {
        double[][] table = new double[pc_3_ksi.length][4];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                table[i][j] = dN_dEta(pc_3_ksi[i])[j];
            }
        }
        return table;
    }

    public static double[][] czteropunktowy_dN_dEta() {
        double[][] table = new double[pc_4_ksi.length][4];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                table[i][j] = dN_dEta(pc_4_ksi[i])[j];
            }
        }
        return table;
    }

    public static double[][] czteropunktowy_dN_dKsi() {
        double[][] table = new double[pc_4_eta.length][4];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                table[i][j] = dN_dKsi(pc_4_eta[i])[j];
            }
        }
        return table;
    }

    public static void print(double[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(table[i][j] + " ; ");
            }
            System.out.println();
        }
        System.out.println();
    }

//    public static void oblicz_macierz_c_dla_kazdego_elementu(int ile_pc) {
//        double gestosc = globalData.getDensity();
//        double cieplo_wlasciwe = globalData.getSpecificHeat();
//        ArrayList<double[]> funkcja_n_dla_pc;
//
//        for (Element element : Jakobian.getElements()) {
//            funkcja_n_dla_pc = new ArrayList<>();
//
//            for (int i = 0; i < ile_pc; i++) {
//                for (int j = 0; j < ile_pc; j++) {
//                    if (ile_pc == 2) {
//                        double ksi = pc_2[j];
//                        double eta = pc_2[i];
//                        funkcja_n_dla_pc.add(new double[]{dN1(ksi, eta), dN2(ksi, eta), dN3(ksi, eta), dN4(ksi, eta)});
//                    }
//                    if (ile_pc == 3) {
//                        double ksi = pc_3[j];
//                        double eta = pc_3[i];
//                        funkcja_n_dla_pc.add(new double[]{dN1(ksi, eta), dN2(ksi, eta), dN3(ksi, eta), dN4(ksi, eta)});
//                    }
//                    if (ile_pc == 4) {
//                        double ksi = pc_4[j];
//                        double eta = pc_4[i];
//                        funkcja_n_dla_pc.add(new double[]{dN1(ksi, eta), dN2(ksi, eta), dN3(ksi, eta), dN4(ksi, eta)});
//                    }
//                }
//            }
//            double[] wagi = new double[0];
//            if (ile_pc == 2) {
//                wagi = Elem4.wagi_2;
//            } else if (ile_pc == 3) {
//                wagi = Elem4.wagi_3;
//            } else if (ile_pc == 4) {
//                wagi = Elem4.wagi_4;
//            }
//            int m = 0;
//            for (double[] fun_pc : funkcja_n_dla_pc) {
//
//                // dla każdego pkt całkowania
//                for (int k = 0; k < fun_pc.length; k++) {
//                    for (int l = 0; l < fun_pc.length; l++) {
//                        double suma = fun_pc[k] * fun_pc[l] * Jakobian. * alfa;
//                        macierzHBC[k][l] += suma * wagi[m];
//                    }
//                }
//                m++;
//            }
//        }
//
//    }


    public static void main(String[] args) {

//        Elem4 Elem4 = new Elem4();
        System.out.println("4pkt Eta");
        Elem4.print(Elem4.dwupunktowy_dN_dKsi());
        System.out.println("4pkt Ksi");
        Elem4.print(Elem4.dwupunktowy_dN_dEta());
        System.out.println("9pkt Eta");
        Elem4.print(Elem4.trojpunktowy_dN_dKsi());
        System.out.println("9pkt Ksi");
        Elem4.print(Elem4.trojpunktowy_dN_dEta());
        System.out.println("16pkt Eta");
        Elem4.print(Elem4.czteropunktowy_dN_dKsi());
        System.out.println("16pkt Ksi");
        Elem4.print(Elem4.czteropunktowy_dN_dEta());
//        Elem4.func_ksi(Elem4.pc_2[0]);
//        Elem4.iterate();
    }


}
