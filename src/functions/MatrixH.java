package functions;

import structures.GlobalData;

import java.util.ArrayList;
import java.util.List;

public class MatrixH {
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public GlobalData globalVariables;
    Jakobian jakobian;
    public List<double[][]> lista_macierzy_h_elementow = new ArrayList<>();


    public MatrixH(int ile_pc) {
        globalVariables = new GlobalData();
        this.jakobian = new Jakobian(ile_pc);
        macierzHDlaElementu(ile_pc);

    }

    public void macierzHDlaElementu(int ile_pc) {
//        Jakobian jakubian = new Jakobian(2);
        double[][] macierz_dN_dX = new double[(int) Math.pow(ile_pc, 2)][4];
        double[][] macierz_dN_dY = new double[(int) Math.pow(ile_pc, 2)][4];

        for (int elementID = 0; elementID < jakobian.elements.size(); elementID++) {

            for (int i = 0; i < Math.pow(ile_pc, 2); i++) {

                double jeden_detJ = jakobian.lista_detJ_jakobianow.get(elementID).get(i);
                double[][] odwrotnyJakobian = jakobian.lista_odwrotnych_jakobianow.get(elementID).get(i);
                for (int j = 0; j < 4; j++) {
                    if (ile_pc == 2) {
                        //dN/dX DODAĆ TUTAJ WAGI KURWA MAĆ ======================================================================================================================================!
                        macierz_dN_dX[i][j] = jeden_detJ * (odwrotnyJakobian[0][0] * Elem4.dwupunktowy_dN_dKsi()[i][j] + odwrotnyJakobian[0][1] * Elem4.dwupunktowy_dN_dEta()[i][j]);
                        //dN/dY
                        macierz_dN_dY[i][j] = jeden_detJ * (odwrotnyJakobian[1][0] * Elem4.dwupunktowy_dN_dKsi()[i][j] + odwrotnyJakobian[1][1] * Elem4.dwupunktowy_dN_dEta()[i][j]);
                    }
                    if (ile_pc == 3) {
                        //dN/dX
                        macierz_dN_dX[i][j] = jeden_detJ * (odwrotnyJakobian[0][0] * Elem4.trojpunktowy_dN_dKsi()[i][j] + odwrotnyJakobian[0][1] * Elem4.trojpunktowy_dN_dEta()[i][j]);
                        //dN/dY
                        macierz_dN_dY[i][j] = jeden_detJ * (odwrotnyJakobian[1][0] * Elem4.trojpunktowy_dN_dKsi()[i][j] + odwrotnyJakobian[1][1] * Elem4.trojpunktowy_dN_dEta()[i][j]);
                    }
                    if (ile_pc == 4) {
                        //dN/dX
                        macierz_dN_dX[i][j] = jeden_detJ * (odwrotnyJakobian[0][0] * Elem4.czteropunktowy_dN_dKsi()[i][j] + odwrotnyJakobian[0][1] * Elem4.czteropunktowy_dN_dEta()[i][j]);
                        //dN/dY
                        macierz_dN_dY[i][j] = jeden_detJ * (odwrotnyJakobian[1][0] * Elem4.czteropunktowy_dN_dKsi()[i][j] + odwrotnyJakobian[1][1] * Elem4.czteropunktowy_dN_dEta()[i][j]);
                    }
                }
            }
            List<double[][]> macierzHElementu = multiply(macierz_dN_dX, macierz_dN_dY, elementID);
            double[][] finalMacierz = new double[4][4];

            int w_pc = 0;
            double[] wagi = new double[0];
            if (ile_pc == 2) {
                wagi = Elem4.w_2;
            } else if (ile_pc == 3) {
                wagi = Elem4.w_3;
            } else if (ile_pc == 4) {
                wagi = Elem4.w_4;
            }

            for (double[][] macierz : macierzHElementu) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        finalMacierz[i][j] += macierz[i][j] * wagi[w_pc];
                    }
                }
                w_pc++;
            }

            lista_macierzy_h_elementow.add(finalMacierz);
        }

    }

    private List<double[][]> multiply(double[][] macierz_dN_dX, double[][] macierz_dN_dY, int elementID) {
        double[][] macierzH = new double[macierz_dN_dX.length][4];
//        int wsp_konwekcji = 30;
        int wsp_konwekcji = globalVariables.getConductivity();
        List<double[][]> macierz_h_pc = new ArrayList<>();
        List<Double> detyJoty = jakobian.lista_detJ_jakobianow.get(elementID);

        // dla każdego pkt całkowania
        for (int i = 0; i < macierz_dN_dX.length; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    double suma_x = macierz_dN_dX[i][j] * macierz_dN_dX[i][k];
                    double suma_y = macierz_dN_dY[i][j] * macierz_dN_dY[i][k];
                    double suma = suma_x + suma_y;
                    macierzH[j][k] = suma * (1 / detyJoty.get(j)) * wsp_konwekcji;
                    //lista_macierzy_h_bc_elementow.get(elemencikMoj)[j][k]
                }
            }
            macierz_h_pc.add(macierzH);
            macierzH = new double[macierz_dN_dX.length][4];
        }
        return macierz_h_pc;
    }

    public void print() {
        for (double[][] macierzH : lista_macierzy_h_elementow) {
            System.out.println(ANSI_WHITE + "Macierz H elementu " + lista_macierzy_h_elementow.indexOf(macierzH));
            for (int i = 0; i < macierzH.length; i++) {
                for (int j = 0; j < macierzH.length; j++) {
                    if (macierzH[i][j] != 0) {
                        if (Double.toString(macierzH[i][j]).contains("-")) {
                            System.out.format(ANSI_RED + " %.2f  ", macierzH[i][j]);
                        } else if (Double.toString(macierzH[i][j]).charAt(2) == '.') {
                            System.out.format(ANSI_RED + " %.2f  ", macierzH[i][j]);
                        }
                    } else
                        System.out.format(ANSI_BLUE + " %.3f  ", macierzH[i][j]);
                }
                System.out.println();
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        MatrixH matrixH = new MatrixH(2);
        matrixH.print();
    }
}
