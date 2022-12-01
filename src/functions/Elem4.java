package functions;

public abstract class Elem4 {
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
