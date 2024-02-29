import java.util.Iterator;
import java.util.Random;

public class Problem4 {
    public static void main(String[] args) {
        int vertices = 5000; // Set nr of vertices
        int edges = 10000; // Set nr of edges
        int iterations = 20;
        boolean negatives = false; // Set whether to allow negative edge weights or not
        Graph g = new DiGraph(vertices);
        Problem4 p4 = new Problem4();

        p4.runRandomGraphs(g, vertices, edges, negatives, iterations); // This is the method for comparing performance
        // p4.runCertainGraphs(); // This method is for verifying functionality
    }

    /*
     * Runs the random graphs for the comparison of performance.
     */
    public void runRandomGraphs(Graph g, int vertices, int edges, boolean negatives, int iterations) {
        TimeMeasuring t = new TimeMeasuring();
        int totalDijkstraTime = 0, totalBFTime = 0;

        // Start by creating random edges in the graph
        Random random = new Random();
        for (int i = 0; i < edges; i++) {
            int source = random.nextInt(vertices);
            int destination = random.nextInt(vertices);
            while (source == destination) {
                destination = random.nextInt(vertices);
            }
            int weight = random.nextInt(vertices * 2) + 1; // Set a max weight. I use int for simplicity
            if (negatives) {
                if (i % 12 == 0) { // Every x:th edge, we make negative, given that the boolean "negatives" is set to true.
                    weight = weight * (- 1);
                }
            }
            g.add_edge(source, destination, weight);
        }

        for (int it = 0; it < iterations; it++) {
            int source = 0; // Reset to 0

            // Dijkstra´s printout
            if (!negatives) { // We only run Dijkstra´s if there are non negative edge weights
                t.startTimer();
                new Dijkstra(g, source);
                long dTime = t.stopTimer() / 1_000_000;
                totalDijkstraTime += dTime;
            }
            
            // Bellman-Ford printout
            t.startTimer();
            BellmanFord bf = new BellmanFord(g, source);
            long bfTime = t.stopTimer() / 1_000_000;
            if (bf.hasNegativeCycle(g)) {
                System.out.println("There was a negative cycle in this graph."); // Prints if there is a negative cycle in the graph
            }
            totalBFTime += bfTime;
        }
        System.out.println("Average time: \nDijkstra: " + totalDijkstraTime / iterations + "ms \nBellman-Ford: " + totalBFTime / iterations + " ms");
    }

    /*
     * This method is for viewing the result of shortest path, testing its functionality.
     * The graphs are manually created here.
     */
    public void runCertainGraphs() {
        Graph g = new DiGraph(8);
        int source = 0;
        boolean negatives = false;

        // Case for testing Dijkstra (and Bellman-Ford).
        // NOTE: Make sure negatives is set to false before running these, otherwise Dijkstra doesn´t run.
        g.add_edge(0, 1, 4);
        g.add_edge(0, 2, 2);
        g.add_edge(1, 3, 2);
        g.add_edge(1, 2, 3);
        g.add_edge(1, 4, 3);
        g.add_edge(2, 1, 1);
        g.add_edge(2, 3, 4);
        g.add_edge(2, 4, 5);
        g.add_edge(4, 3, 1);

        // Case for testing Bellman-ford
        // g.add_edge(0, 1, 10);
        // g.add_edge(0, 5, 8);
        // g.add_edge(5, 4, 1);
        // g.add_edge(4, 1, -4);
        // g.add_edge(4, 3, -1);
        // g.add_edge(1, 3, 2);
        // g.add_edge(3, 2, -2);
        // g.add_edge(2, 1, 1);

        // Check the graph we created by iterating over edges
        System.out.println("\nEdges:");
        Iterator<Edge> edgeIter = g.getEdgeIterator();
        while (edgeIter.hasNext()) {
            Edge edge = edgeIter.next();
            System.out.println(edge.getSource() + " - " + edge.getDestination() + " with weight " + edge.getWeight());
        }

        // Dijkstra´s printout
        if (!negatives) { // We only run Dijkstra´s if there are nog negative edge weights
            Dijkstra dijkstra = new Dijkstra(g, source);
            System.out.println("\nDijkstra: ");
            for (int v = 0; v < g.nrOfVertices(); v++) {
                System.out.println("Shortest path to " + v + ": " + dijkstra.getDistTo(v));
            }
        }
        
        // Bellman-Ford printout
        BellmanFord bf = new BellmanFord(g, source);
        System.out.println("\nBellman-Ford: ");
        if (bf.hasNegativeCycle(g)) {
            System.out.println("There was a negative cycle in this graph.");
        }
        for (int v = 0; v < g.nrOfVertices(); v++) {
            System.out.println("Shortest path to " + v + ": " + bf.getDistTo(v));
        }
    }
}
