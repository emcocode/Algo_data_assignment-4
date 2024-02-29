import java.util.ArrayList;
import java.util.Iterator;

public class Kruskal {

    private Graph graph;

    /*
     * The find method performing kruskals and return the MSTs.
     */
    public ArrayList<Graph> findMST(Graph g) {
        graph = g;
        DFS dfs = new DFS(graph);
        ArrayList<ArrayList<Integer>> trees = dfs.getConnectedComponents();
        ArrayList<Graph> msts = new ArrayList<>();

        System.out.println("Connected components:");
        for (ArrayList<Integer> tree : trees) {
            System.out.println(tree);
            Graph mst = new UnDiGraph(graph.nrOfVertices());
            UnionFind uf = new UnionFind(graph.nrOfEdges());
            MinHeap minHeap = new MinHeap(graph.nrOfEdges() * 2); // x2 since we have double amount of edges in an undirected graph

            // Get the min heap
            Iterator<Edge> edge = graph.getEdgeIterator();
            while (edge.hasNext()) {
                Edge ed = edge.next();
                if (tree.contains(ed.getSource()) && tree.contains(ed.getDestination())) {
                    minHeap.insert(ed); 
                }
            }
            // Perform the check if they will form a circle (if already connected) or not, add if don´t.
            while (!minHeap.isEmpty() && mst.nrOfEdges() < graph.nrOfVertices() - 1) {
                Edge e = minHeap.getMin();
                if (!uf.connected(e.getSource(), e.getDestination())) {
                    uf.union(e.getSource(), e.getDestination());
                    mst.add_edge(e.getSource(), e.getDestination(), e.getWeight());
                }
            }
            msts.add(mst);
        }

        // Handle trees without edges (that is, alone vertices)
        // Print that they are alone and remove their tree
        Iterator<Integer> vertexIter = g.getVertexIterator();
        while (vertexIter.hasNext()) {
            int v = vertexIter.next();
            if (graph.degree(v) == 0) {
                System.out.println("Vertex " + v + " has no edges.");
            }
        }
        for (int i = 0; i < msts.size(); i++) {
            if (msts.get(i).nrOfEdges() == 0) {
                msts.remove(msts.get(i));
            }
        }
        return msts;
    }   
}
