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
