import java.util.ArrayList;
import java.util.Iterator;

public abstract class Graph implements Iterable <Integer> {

    protected ArrayList<Edge>[] al;
    protected int edges;
    
    public Graph(int v) {
        if (v < 1) {
            throw new IllegalArgumentException("To few vertices.");
        }
        al = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            al[i] = new ArrayList<>();
        }
    }

    public int nrOfEdges() {
        return edges;
    }

    public int nrOfVertices() {
        return al.length;
    }

    /*
     * Add edge method without weight argument.
     */
    public void add_edge(int source, int destination) {}
    
    /*
     * Add edge method without weight argument.
     */
    public void add_edge(String source, String destination) {}

    /*
     * Add edge method with weight arugment.
     */
    public void add_edge(int source, int destination, double weight) {}
    
    /*
     * Remove edge between a and b.
     */
    public void remove_edge(int source, int destination) {}

    /*
     * Degree method. Undirected counts both in and out, while directed only counts outgoing.
     */
    public int degree(int v) {
        if (v < al.length) {
            return al[v].size();
        }
        return 0;
    }

    /*
     * Iterator method for iterating over the vertices in the graph.
     */
    @Override
    public Iterator<Integer> iterator() {
        return getVertexIterator();
    }

    // Iterator for vertices
    public Iterator<Integer> getVertexIterator() {
        int[] vertices = new int[al.length];
        for (int i = 0; i < al.length; i++) {
            vertices[i] = i;
        }
        return new VertexIterator(vertices);
    }

    // Iterator for edges
    public Iterator<Edge> getEdgeIterator() {
        return new EdgeIterator(al);
    }

    // Iterator for adjacency of a provided vertex
    public Iterator<Integer> getAdjacencyIterator(int v) {
        if (v >= 0 && v < al.length) {
            return new AdjacencyIterator(al[v]);
        }
        return null;
    }

}
