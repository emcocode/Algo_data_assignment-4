import java.util.Iterator;

public class BellmanFord {
    private Edge[] edgeTo;
    private double[] distTo;

    /*
     * Bellman-Ford as the constructor.
     */
    public BellmanFord(Graph g, int source) {
        int vertices = g.nrOfVertices();
        edgeTo = new Edge[vertices];
        distTo = new double[vertices];
        for (int v = 0; v < vertices; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;
        for (int i = 0; i < vertices - 1; i++) { // Use out commented code to check the iterations. Only usable on very small graphs
            // for (int v = 0; v < g.nrOfVertices(); v++) {
            //     System.out.println("Shortest path to " + v + ": " + getDistTo(v));
            // }
            // System.out.println();
            relaxAllEdges(g);
        }

    }

    /*
     * Method for relaxing all edges.
     */
    private boolean relaxAllEdges(Graph g) {
        boolean relaxed = false;
        for (int v = 0; v < g.nrOfVertices(); v++) {
            Iterator<Edge> edgeIterator = new EdgeAdjacencyIterator(g.al[v]);
            while (edgeIterator.hasNext()) {
                if (relax(edgeIterator.next())) {
                    relaxed = true;
                }
            }
        }
        return relaxed;
    }

    /*
     * Relax method for a specific edge, only used in relaxAllEdges().
     */
    private boolean relax(Edge e) {
        int source = e.getSource();
        int destination = e.getDestination();
        double weight = e.getWeight();
        if (distTo[destination] > distTo[source] + weight) {
            distTo[destination] = distTo[source] + weight;
            edgeTo[destination] = e;
            return true;
        }
        return false;
    }

    /*
     * Get the distance to Vertex v.
     */
    public double getDistTo(int v) {
        return distTo[v];
    }

    /*
     * Check if a negative cycle was detected.
     */
    public boolean hasNegativeCycle(Graph g) {
        for (int i = 0; i < g.nrOfVertices() - 1; i++) {
            if (relaxAllEdges(g)) {
                return true;
            }
        }
        return false;
    }
}
