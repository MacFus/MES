package functions;

public class Function {
    double[] threePoints = new double[]{-Math.sqrt(3 / 5), 0, Math.sqrt(3 / 5)};
    double[] twoPoints = new double[]{-Math.sqrt(1 / 3), Math.sqrt(1 / 3)};

    double[] threeWeights = new double[]{5 / 9, 8 / 9, 5 / 9};
    double[] twoWeights = new double[]{1, 1};
    double sum = 0;

    double funTwoDimension(double x, double y) {
        return (-5 * Math.pow(x, 2) * y) + (2 * x * Math.pow(y, 2)) + 10;
    }

    double funOneDimension(double x) {
        return (2 * Math.pow(x, 2) + (3 * x) - 8);
    }

    public void twoDimension() {
        for (int i = 0; i < twoPoints.length; i++) {
            for (int j = 0; j < twoWeights.length; j++) {
                sum += funTwoDimension(twoPoints[i], twoPoints[j]) * twoWeights[i] * twoWeights[j];
            }
        }
        System.out.println(sum);
    }

    public void twoDimension2() {
        for (int i = 0; i < threePoints.length; i++) {
            for (int j = 0; j < threeWeights.length; j++) {
                sum += funTwoDimension(threePoints[i], threePoints[j]) * threeWeights[i] * threeWeights[j];
            }
        }
        System.out.println(sum);
    }

    public void oneDimension() {
        for (int i = 0; i < twoPoints.length; i++) {
            sum += funOneDimension(twoPoints[i]) * twoWeights[i];
        }
        System.out.println(sum);
    }

    public void oneDimension2() {
        for (int i = 0; i < threePoints.length; i++) {
            sum += funOneDimension(threePoints[i]) * threeWeights[i];
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        Function function = new Function();
        function.twoDimension();
        function.twoDimension2();
        function.oneDimension();
        function.oneDimension2();

    }
}
