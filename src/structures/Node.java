package structures;

public class Node {
    private int id;
    private double x;
    private double y;
    private int bc = 0;

    @Override
    public String toString() {
        return "\t\tNode{" +
                "ID=" + id +
                ", X=" + x +
                ", Y=" + y +
                ", BC=" + bc +
                '}' + "\n";
    }

    public Node() {
    }

    public Node(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Node(int id, double x, double y, int bc) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.bc = bc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getBc() {
        return bc;
    }

    public void setBc(int bc) {
        this.bc = bc;
    }
}
