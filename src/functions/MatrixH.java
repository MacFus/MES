package functions;

import java.util.ArrayList;
import java.util.List;

public class MatrixH {
    public List<double[][]> macierzHDlaPunktuCalkowania(int elementID, int ile_pc) {
        double[][] macierz_dN_dX = new double[(int) Math.pow(ile_pc, 2)][4];
        double[][] macierz_dN_dY = new double[(int) Math.pow(ile_pc, 2)][4];


        for (int i = 0; i < Math.pow(ile_pc, 2); i++) {
            //zmienić get... nwm jeszcze jak
            double jeden_detJ = Jakobian.get_1_DetJ(elementID, ile_pc).get(i);
            double[][] odwrotnyJakobian = Jakobian.getOdwrotnyJakobian(elementID, ile_pc).get(i);
            for (int j = 0; j < 4; j++) {
                if (ile_pc == 2) {
                    //dN/dX
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
        multiply(macierz_dN_dX, macierz_dN_dY);
        return null;
    }

    private double[][] multiply(double[][] macierz_dN_dX, double[][] macierz_dN_dY) {
        double[][] macierzH = new double[macierz_dN_dX.length][4];
        List<double[][]> macierzeChujaWdupie = new ArrayList<>();
        List<double> detyJoty = Jakobian.get_1_DetJ()
        // dla każdego pkt całkowania
        for (int i = 0; i < macierz_dN_dX.length; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    double dupa_x = macierz_dN_dX[i][j] * macierz_dN_dX[i][k];
                    double dupa_y = macierz_dN_dY[i][j] * macierz_dN_dY[i][k];
                    double suma_dup = dupa_x + dupa_y;
                    macierzH[i][]
                }
            }

        }
        return null;
    }

    //    private double[][] multiply(double[][] m1, double[][] m2, int iteracja) {
//        double[][] answer = new double[m1.length][m1.length];
//        double[][] answer2 = new double[m1.length][m1.length];
//        double[][] answer3 = new double[m1.length][m1.length];
//        double[][] answer4 = new double[m1.length][m1.length];
//        int row = 4, column = 4;
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < column; j++) {
//                if (iteracja == 0)
//                    answer[i][j] = ((m1[0][i] * m1[0][j]) + (m2[0][i] * m2[0][j])) * 30;
//                if (iteracja == 1)
//                    answer[i][j] += ((m1[i][1] * m1[i][j]) + (m2[i][1] * m2[i][j])) * 30;
//                if (iteracja == 2)
//                    answer[i][j] += ((m1[i][2] * m1[i][j]) + (m2[i][2] * m2[i][j])) * 30;
//                if (iteracja == 3)
//                    answer[i][j] += ((m1[i][1] * m1[i][j]) + (m2[i][1] * m2[i][j])) * 30;
//                // dla 1 punktu całkowania
////                System.out.println(((m1[0][i] * m1[0][j]) + (m2[0][i] * m2[0][j])) * 30 * 0.000156);
//                // dla 2 punktu całkowania
////                answer2[i][j] += ((m1[i][1] * m1[i][j]) + (m2[i][1] * m2[i][j])) * 30 * det_J_2;
////                // dla 3 punktu całkowania
////                answer3[i][j] += ((m1[i][2] * m1[i][j]) + (m2[i][2] * m2[i][j])) * 30 * det_J_3;
////                // dla 4 punktu całkowania
////                answer4[i][j] += ((m1[i][3] * m1[i][j]) + (m2[i][3] * m2[i][j])) * 30 * det_J_4;
//            }
//        }
//        return answer;
//    }


//    public ArrayList<double[][]> createHMatrix(int elementID, int ile_pc) {  //ArrayList<ArrayList<double[][]>>
//        List<double[][]> HMatrixListForEachPC = new ArrayList<>();
//        for (int i = 0; i < Math.pow(ile_pc, 2); i++) {
//
//        }
//
//        List<double[][]> arrayListOfHMatrix = new ArrayList<>();
//        ArrayList<Double> listaDetJElementu = Jakobian.getDetJ(elementID, ile_pc);
//        for (int h = 0; h < Jakobian.getNodes(elementID).length; h++) {
//            double[][] detJ = Jakobian.getDetJMatrix(Jakobian.getJakobian(elementID).get(h));
//            double[][] detJ = Jakobian.getJakobian(elementID).get(h);
//
//            for (int i = 0; i < 4; i++) {
//                for (int j = 0; j < 4; j++) {
//                    matrixHdX[i][j] = detJ[0][0] * Elem4.dwupunktowy_dN_dKsi()[i][j] + detJ[0][1] * Elem4.dwupunktowy_dN_dEta()[i][j];
//                    matrixHdY[i][j] = detJ[1][0] * Elem4.dwupunktowy_dN_dKsi()[i][j] + detJ[1][1] * Elem4.dwupunktowy_dN_dEta()[i][j];
//                }
//            }
//            matrixH = multiply(matrixHdX, matrixHdY, h);
//
//            for (int i = 0; i < 4; i++) {
//                for (int j = 0; j < 4; j++) {
//                    matrixH[i][j] = matrixH[i][j] * listaDetJElementu.get(h);
//                    System.out.println(Jakobian.getDetJ(0).get(h));
//                }
//            }
//            arrayListOfHMatrix.add(matrixH);
//        }
//        // pc   dN1/dx  dN2/dx  dN3/dx  dN4/dx
//        // 1       -       -       -       -
//
//        return (ArrayList<double[][]>) arrayListOfHMatrix;
//    }
//
//    private double[][] multiply(double[][] m1, double[][] m2, int iteracja) {
//        double[][] answer = new double[m1.length][m1.length];
//        double[][] answer2 = new double[m1.length][m1.length];
//        double[][] answer3 = new double[m1.length][m1.length];
//        double[][] answer4 = new double[m1.length][m1.length];
//        int row = 4, column = 4;
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < column; j++) {
//                if (iteracja == 0)
//                    answer[i][j] = ((m1[0][i] * m1[0][j]) + (m2[0][i] * m2[0][j])) * 30;
//                if (iteracja == 1)
//                    answer[i][j] += ((m1[i][1] * m1[i][j]) + (m2[i][1] * m2[i][j])) * 30;
//                if (iteracja == 2)
//                    answer[i][j] += ((m1[i][2] * m1[i][j]) + (m2[i][2] * m2[i][j])) * 30;
//                if (iteracja == 3)
//                    answer[i][j] += ((m1[i][1] * m1[i][j]) + (m2[i][1] * m2[i][j])) * 30;
//                // dla 1 punktu całkowania
////                System.out.println(((m1[0][i] * m1[0][j]) + (m2[0][i] * m2[0][j])) * 30 * 0.000156);
//                // dla 2 punktu całkowania
////                answer2[i][j] += ((m1[i][1] * m1[i][j]) + (m2[i][1] * m2[i][j])) * 30 * det_J_2;
////                // dla 3 punktu całkowania
////                answer3[i][j] += ((m1[i][2] * m1[i][j]) + (m2[i][2] * m2[i][j])) * 30 * det_J_3;
////                // dla 4 punktu całkowania
////                answer4[i][j] += ((m1[i][3] * m1[i][j]) + (m2[i][3] * m2[i][j])) * 30 * det_J_4;
//            }
//        }
//        return answer;
//    }

    public static void main(String[] args) {
        MatrixH matrixH = new MatrixH();
        matrixH.macierzHDlaPunktuCalkowania(0, 2);
    }
}
