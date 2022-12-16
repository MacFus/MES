package functions;

import structures.Element;

import java.util.ArrayList;
import java.util.List;

public class Agregacja {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";

    MatrixH macierzH;
    List<double[][]> lista_macierzy_h_bc_elementow;
    List<double[]> lista_wektorow_p_elementow;
    private int ilosc_nodow;
    double[][] macierz_agregacji;
    double[] wektorP;

    public Agregacja(int ile_pc, int ile_pc_bc) {
        this.macierzH = new MatrixH(ile_pc);
        this.ilosc_nodow = macierzH.globalVariables.getNumberOfNodes();
        macierz_agregacji = new double[ilosc_nodow][ilosc_nodow];
        wektorP = new double[ilosc_nodow];
        lista_macierzy_h_bc_elementow = Elem4_bok.uzupelnij_H_bc_kazdego_elementu(ile_pc_bc);
        lista_wektorow_p_elementow = Elem4_bok.oblicz_wektor_P_kazdego_elementu(ile_pc_bc);
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
//                    macierz_agregacji[tab_elem[i]][tab_elem[j]] += macierzH.lista_macierzy_h_elementow.get(element)[i][j];
                    macierz_agregacji[tab_elem[i]][tab_elem[j]] += macierzH.lista_macierzy_h_elementow.get(element)[i][j] + lista_macierzy_h_bc_elementow.get(element)[i][j];
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

    public void print() {
        System.out.println(ANSI_WHITE + "Macierz Agregacji: " );
        for (int i = 0; i < macierz_agregacji.length; i++) {
            for (int j = 0; j < macierz_agregacji.length; j++) {
                if (macierz_agregacji[i][j] != 0) {
                    if (Double.toString(macierz_agregacji[i][j]).contains("-")) {
                        System.out.format(ANSI_RED + " %.2f  ", macierz_agregacji[i][j]);
                    } else if (Double.toString(macierz_agregacji[i][j]).charAt(2) == '.') {
                        System.out.format(ANSI_RED + " %.2f  ", macierz_agregacji[i][j]);
                    }
                } else
                    System.out.format(ANSI_BLUE + " %.3f  ", macierz_agregacji[i][j]);
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

    public static void main(String[] args) {
        Agregacja agregacja = new Agregacja(4, 3);
        agregacja.oblicz_macierz_agregacji();
        agregacja.oblicz_wektor_P();
        agregacja.print();
        agregacja.macierzH.print();
        agregacja.printHbc();
        agregacja.printWektorP();
        System.out.println();
    }

}
