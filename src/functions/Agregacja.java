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
        for (int element = 0; element < macierzH.jakobian.elements.size(); element++) {
            int[] tab_elem = new int[]{
                    macierzH.jakobian.elements.get(element).getNodes()[0].getId() - 1,
                    macierzH.jakobian.elements.get(element).getNodes()[1].getId() - 1,
                    macierzH.jakobian.elements.get(element).getNodes()[2].getId() - 1,
                    macierzH.jakobian.elements.get(element).getNodes()[3].getId() - 1};
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    macierz_agregacji[tab_elem[i]][tab_elem[j]] += macierzH.lista_macierzy_h_elementow.get(element)[i][j];
                }
            }
        }
    }

    public static void main(String[] args) {
        Agregacja agregacja = new Agregacja(2);
        agregacja.oblicz_macierz_agregacji();
        System.out.println();
    }

}
