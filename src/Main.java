import functions.Agregacja;

public class Main {
    public static void main(String[] args) {
        Agregacja agregacja = new Agregacja(2, 4);

        agregacja.solve();
        agregacja.print(agregacja.macierz_agregacji_C);
        agregacja.print(agregacja.macierz_agregacji_H);
        System.out.println();
    }
}
