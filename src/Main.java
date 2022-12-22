import functions.Agregacja;
import structures.GlobalData;

public class Main {
    public static void main(String[] args) {
        Agregacja agregacja = new Agregacja(4,4);
        agregacja.oblicz_macierz_agregacji();
        agregacja.oblicz_wektor_P();
        agregacja.solve();
    }
}
