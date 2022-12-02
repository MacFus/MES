package functions;

import structures.Element;

public class Agregacja {
    MatrixH macierzH;
    private int ilosc_nodow;
    double[][] macierz_agregacji;

    public Agregacja(int ile_pc) {
        this.macierzH = new MatrixH(ile_pc);
        this.ilosc_nodow = macierzH.globalVariables.getNumberOfNodes();
        macierz_agregacji = new double[ilosc_nodow][ilosc_nodow];
    }

    public void oblicz_macierz_agregacji() {
        for (int kupa = 0; kupa < macierzH.jakobian.elements.size(); kupa++) {
            int[] tab_elem = new int[]{macierzH.jakobian.elements.get(kupa).getNodes()[0].getId() - 1, macierzH.jakobian.elements.get(kupa).getNodes()[1].getId() - 1, macierzH.jakobian.elements.get(kupa).getNodes()[2].getId() - 1, macierzH.jakobian.elements.get(kupa).getNodes()[3].getId() - 1};
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    macierz_agregacji[tab_elem[i]][tab_elem[j]] = macierzH.lista_macierzy_h_elementow.get(kupa)[i][j];
                }
            }
        }
    }

    public static void main(String[] args) {
        Agregacja a = new Agregacja(4);
        a.oblicz_macierz_agregacji();
        System.out.println();
    }

}
