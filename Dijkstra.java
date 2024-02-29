import java.util.Iterator;

public class Dijkstra {
    private Edge[] edgeTo;
    private double[] distTo;
    private MinHeap minHeap;

    /*
     * Dijkstra´s as the constructor. 
     */
    public Dijkstra(Graph g, int source) {
        int vertices = g.nrOfVertices();
        edgeTo = new Edge[vertices];
        distTo = new double[vertices];
        for (int v = 0; v < vertices; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;
        minHeap = new MinHeap(vertices);
        minHeap.insert(new Edge(source, source, 0.0));

        while (!minHeap.isEmpty()) {
            Edge minEdge = minHeap.getMin();
            int v = minEdge.getDestination();
            Iterator<Edge> edgeIterator = new EdgeAdjacencyIterator(g.al[v]);
            while (edgeIterator.hasNext()) {
                relax(edgeIterator.next());
            }
        }
    }

    /*
     * Relax an edge.
     */
    private void relax(Edge e) {
        int source = e.getSource();
        int destination = e.getDestination();
        double weight = e.getWeight();
        if (distTo[destination] > distTo[source] + weight) {
            distTo[destination] = distTo[source] + weight;
            edgeTo[destination] = e;
            minHeap.insert(new Edge(source, destination, distTo[destination]));
        }
    }

    /*
     * Get the distance to Vertex v.
     */
    public double getDistTo(int v) {
        return distTo[v];
    }
}