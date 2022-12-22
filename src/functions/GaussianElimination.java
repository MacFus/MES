package functions;

public class GaussianElimination {
    private static final double EPSILON = 1e-10;

    // Gaussian elimination with partial pivoting
    public static double[] lsolve(double[][] A, double[] b) {
        int n = b.length;

        for (int p = 0; p < n; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;

            // singular or nearly singular
            if (Math.abs(A[p][p]) <= EPSILON) {
                throw new ArithmeticException("Matrix is singular or nearly singular");
            }

            // pivot within A and b
            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // back substitution
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }


    // sample client
    public static void main(String[] args) {
        Agregacja agregacja = new Agregacja(4, 3);
        agregacja.oblicz_macierz_agregacji();
        agregacja.oblicz_wektor_P();
//        agregacja.oblicz();
//        agregacja.print(agregacja.macierz_zastepcza_H);
//        agregacja.printWektor(agregacja.wektor_zastepczy_P);


        int n = agregacja.macierz_zastepcza_H.length;
        double[][] A = agregacja.macierz_zastepcza_H;
        double[] b = agregacja.wektor_zastepczy_P;
        double[] x = lsolve(A, b);


        // print results
        for (int i = 0; i < n; i++) {
            System.out.println(x[i]);
        }

    }

}