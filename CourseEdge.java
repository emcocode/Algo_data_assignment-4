public class CourseEdge {

    private int src;
    private int dst;
    private double weight;

    public CourseEdge(int s, int d, double w){
        src = s;
        dst = d;
        weight = w;
    }

    public int getSource() {
        return src;
    }

    public int getDestination() {
        return dst;
    }

    public double getWeight() {
        return weight;
    }
}
