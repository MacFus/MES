package functions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Agregacja {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    MatrixH macierzH;
    MatrixC macierzC;
    List<double[][]> lista_macierzy_h_bc_elementow;
    List<double[]> lista_wektorow_p_elementow;
    private int ilosc_nodow;
    public double[][] macierz_agregacji_H;
    public double[][] macierz_agregacji_C;
    double[] wektorP;
    Double[] wektorT0;
    double[][] macierz_zastepcza_H;
    double[] wektor_zastepczy_P;

    public Agregacja(int ile_pc, int ile_pc_bc) {
        this.macierzH = new MatrixH(ile_pc);
        this.macierzC = new MatrixC(ile_pc);
        this.ilosc_nodow = macierzH.globalVariables.getNumberOfNodes();
        macierz_agregacji_H = new double[ilosc_nodow][ilosc_nodow];
        macierz_agregacji_C = new double[ilosc_nodow][ilosc_nodow];
        wektorP = new double[ilosc_nodow];
        lista_macierzy_h_bc_elementow = Elem4_bok.uzupelnij_H_bc_kazdego_elementu(ile_pc_bc);
        lista_wektorow_p_elementow = Elem4_bok.oblicz_wektor_P_kazdego_elementu(ile_pc_bc);
        wektorT0 = new Double[wektorP.length];
        for (int i = 0; i < wektorP.length; i++) {
            wektorT0[i] = Double.valueOf(macierzH.globalVariables.getInitialTemp());
        }
//        Arrays.fill(wektorT0, macierzH.globalVariables.getInitialTemp());
    }

    public void oblicz_macierz_agregacji() {
        for (int element = 0; element < macierzH.jakobian.elements.size(); element++) {
            int[] tab_elem = new int[]{
                    macierzH.jakobian.elements.get(element).getNodes()[0].getId() - 1,
                    macierzH.jakobian.elements.get(element).getNodes()[1].getId() - 1,
                    macierzH.jakobian.elements.get(element).getNodes()[2].getId() - 1,
                    macierzH.jakobian.elements.get(element).getNodes()[3].getId() - 1};
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
//                    macierz_agregacji_H[tab_elem[i]][tab_elem[j]] += macierzH.lista_macierzy_h_elementow.get(element)[i][j];
                    macierz_agregacji_H[tab_elem[i]][tab_elem[j]] += macierzH.lista_macierzy_h_elementow.get(element)[i][j] + lista_macierzy_h_bc_elementow.get(element)[i][j];
                    macierz_agregacji_C[tab_elem[i]][tab_elem[j]] += macierzC.lista_macierzy_c_elementow.get(element)[i][j];
                }
            }
        }
    }
    public void oblicz_wektor_P(){
        for(int element = 0; element < lista_wektorow_p_elementow.size(); element++){
            //Wyciągam id węzłów żeby wpisać w układ globalny
            int[] tab_elem = new int[]{
                    macierzH.jakobian.elements.get(element).getNodes()[0].getId() - 1,
                    macierzH.jakobian.elements.get(element).getNodes()[1].getId() - 1,
                    macierzH.jakobian.elements.get(element).getNodes()[2].getId() - 1,
                    macierzH.jakobian.elements.get(element).getNodes()[3].getId() - 1};
            for (int i = 0; i < 4; i++) {
                wektorP[tab_elem[i]] += lista_wektorow_p_elementow.get(element)[i];
            }
        }
    }
    public void oblicz(Double[] wektorT){
        macierz_zastepcza_H = new double[macierz_agregacji_H.length][macierz_agregacji_H.length];
        wektor_zastepczy_P = new double[macierz_agregacji_H.length];

        for (int i = 0; i < macierz_agregacji_H.length; i++) {
            for (int j = 0; j < macierz_agregacji_H.length; j++) {
                macierz_zastepcza_H[i][j] = macierz_agregacji_H[i][j] + macierz_agregacji_C[i][j]/macierzH.globalVariables.getSimulationStepTime();
                wektor_zastepczy_P[i] +=(macierz_agregacji_C[i][j] / macierzH.globalVariables.getSimulationStepTime()) * wektorT[j];
            }
            wektor_zastepczy_P[i] += wektorP[i];
        }
    }
    public void solve(){
        oblicz_macierz_agregacji();
        oblicz_wektor_P();
        int end = macierzH.globalVariables.getSimulationTime()/macierzH.globalVariables.getSimulationStepTime();
        int time = 0;
        oblicz(wektorT0);
        System.out.println(ANSI_WHITE + "Time[s]\t\tMinTemp[s]\t\tMaxTemp[s]");
        for (int i = 0; i < end; i++) {
            Double[] t = GaussianElimination.lsolve(macierz_zastepcza_H, wektor_zastepczy_P);
            time+=macierzH.globalVariables.getSimulationStepTime();
//            System.out.println(ANSI_WHITE + "Simulation time: " + time);
//            for (int j = 0; j < t.length; j++) {
//                System.out.println(ANSI_RED + t[j]);
//            }
            System.out.format(ANSI_RED + "%d\t\t\t%.3f\t\t\t%.3f", time, Collections.min(Arrays.asList(t)), Collections.max(Arrays.asList(t)));
            System.out.println();
            oblicz(t);
        }
    }

    public void print_agregacja_H() {
        System.out.println(ANSI_WHITE + "Macierz Agregacji H: " );
        for (int i = 0; i < macierz_agregacji_H.length; i++) {
            for (int j = 0; j < macierz_agregacji_H.length; j++) {
                if (macierz_agregacji_H[i][j] != 0) {
                    if (Double.toString(macierz_agregacji_H[i][j]).contains("-")) {
                        System.out.format(ANSI_RED + " %.2f  ", macierz_agregacji_H[i][j]);
                    } else if (Double.toString(macierz_agregacji_H[i][j]).charAt(2) == '.') {
                        System.out.format(ANSI_RED + " %.2f  ", macierz_agregacji_H[i][j]);
                    } else if (Double.toString(macierz_agregacji_H[i][j]).charAt(3) == '.') {
                        System.out.format(ANSI_RED + " %.2f ", macierz_agregacji_H[i][j]);
                    }
                } else
                    System.out.format(ANSI_BLUE + " %.3f  ", macierz_agregacji_H[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public void print_agregacja_C() {
        System.out.println(ANSI_WHITE + "Macierz Agregacji C: " );
        for (int i = 0; i < macierz_agregacji_C.length; i++) {
            for (int j = 0; j < macierz_agregacji_C.length; j++) {
                if (macierz_agregacji_C[i][j] != 0) {
                    if (Double.toString(macierz_agregacji_C[i][j]).contains("-")) {
                        System.out.format(ANSI_RED + " %.2f  ", macierz_agregacji_C[i][j]);
                    } else if (Double.toString(macierz_agregacji_C[i][j]).charAt(3) == '.') {
                        System.out.format(ANSI_RED + " %.2f ", macierz_agregacji_C[i][j]);
                    }else if (Double.toString(macierz_agregacji_C[i][j]).charAt(4) == '.') {
                        System.out.format(ANSI_RED + " %.2f", macierz_agregacji_C[i][j]);
                    }else if (Double.toString(macierz_agregacji_C[i][j]).charAt(2) == '.') {
                        System.out.format(ANSI_RED + " %.2f ", macierz_agregacji_C[i][j]);
                    }
                } else
                    System.out.format(ANSI_BLUE + " %.3f  ", macierz_agregacji_C[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public void printHbc() {
        for (double[][] macierzHBC : lista_macierzy_h_bc_elementow) {
            System.out.println(ANSI_WHITE + "Macierz H BC elementu " + lista_macierzy_h_bc_elementow.indexOf(macierzHBC));
            for (int i = 0; i < macierzHBC.length; i++) {
                for (int j = 0; j < macierzHBC.length; j++) {
                    if (macierzHBC[i][j] != 0) {
                        System.out.format(ANSI_RED + " %.2f  ", macierzHBC[i][j]);
                    } else
                        System.out.format(ANSI_BLUE + " %.2f  ", macierzHBC[i][j]);
                }
                System.out.println();
            }
            System.out.println("\n");
        }
    }
    public void printWektorP() {
        System.out.println(ANSI_WHITE + "Wektor P ");
        for (int i = 0; i < wektorP.length; i++) {
            if (wektorP[i] != 0) {
                System.out.format(ANSI_RED + " %.4f\n", wektorP[i]);
            } else
                System.out.format(ANSI_BLUE + " %.4f\n", wektorP[i]);
        }
    }
    public void print(double[][] macierz) {
//        System.out.println(ANSI_WHITE + "Macierz Agregacji H: " );
        for (int i = 0; i < macierz.length; i++) {
            for (int j = 0; j < macierz.length; j++) {
                if (macierz[i][j] != 0) {
                    if (Double.toString(macierz[i][j]).contains("-")) {
                        System.out.format(ANSI_RED + " %.2f  ", macierz[i][j]);
                    } else  {
                        System.out.format(ANSI_RED + " %.2f  ", macierz[i][j]);
                    }
                } else
                    System.out.format(ANSI_BLUE + " %.3f  ", macierz[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public void printWektor(double[] wektor) {
        System.out.println(ANSI_WHITE + "Wektor P ");
        for (int i = 0; i < wektor.length; i++) {
            if (wektor[i] != 0) {
                System.out.format(ANSI_RED + " %.4f\n", wektorP[i]);
            } else
                System.out.format(ANSI_BLUE + " %.4f\n", wektor[i]);
        }
    }



    public static void main(String[] args) {
        Agregacja agregacja = new Agregacja(2, 4);
//        agregacja.oblicz_macierz_agregacji();
//        agregacja.oblicz_wektor_P();

//        agregacja.print();
//        agregacja.macierzH.print();
//        agregacja.printHbc();
//        agregacja.printWektorP();
//        agregacja.print_agregacja_H();
//        agregacja.print_agregacja_C();
//        agregacja.oblicz_zastepcze();

        agregacja.solve();
        agregacja.print(agregacja.macierz_agregacji_C);
        agregacja.print(agregacja.macierz_agregacji_H);
        System.out.println();


    }

}
