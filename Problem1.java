import java.util.Iterator;

public class Problem1 {

    public static void main(String[] args) {
        Graph g = new DiGraph(5); // Digraph for directed graph, UnDiGraph for undirected graph
        g.add_edge(1, 2);
        g.add_edge(1, 3);
        g.add_edge(1, 4, 5.9);
        g.add_edge(4, 2);
        g.add_edge(4, 0);

        // Iterate over all vertices
        System.out.println("Vertices:");
        Iterator<Integer> vertexIter = g.getVertexIterator();
        while (vertexIter.hasNext()) {
            System.out.println(vertexIter.next());
        }

        // Iterate over all edges
        System.out.println("\nEdges:");
        Iterator<Edge> edgeIter = g.getEdgeIterator();
        while (edgeIter.hasNext()) {
            Edge edge = edgeIter.next();
            if (g instanceof DiGraph) {
                System.out.println(edge.getSource() + " - " + edge.getDestination() + " with weight " + edge.getWeight());
            } else if (g instanceof UnDiGraph) {
                if (edge.getDestination() > edge.getSource()) {
                System.out.println(edge.getSource() + " - " + edge.getDestination() + " with weight " + edge.getWeight());
                }
            }
            
        }

        // Iterate over all adjacent vertices of a provided vertex v.
        int ver = 4; // Enter desired vertex to check for adjacencies
        System.out.println("\nAdjacencies to " + ver + ":");
        Iterator<Integer> adjacentIter = g.getAdjacencyIterator(ver);
        while (adjacentIter.hasNext()) {
            System.out.println(ver + " is adjacent to " + adjacentIter.next());
        }
    }
}