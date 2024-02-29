import java.util.ArrayList;

public abstract class SearchAlgo {
    protected Graph graph;
    protected int source;
    protected int target;
    protected ArrayList<Boolean> marked;
    
    public SearchAlgo(Graph newGraph) {
        graph = newGraph;
    }

    /*
     * Public common method, returning whether source is connected to the target.
     */
    public boolean isConnected(int source, int target) {
        if (source < 0 || source >= graph.nrOfVertices() || target < 0 || target >= graph.nrOfVertices()) {
            return false;
        }
        marked = new ArrayList<>();
        for (int i = 0; i < graph.nrOfVertices(); i++) {
            marked.add(false);
        }
        return _isConnected(source, target);
    }

    /*
     * Protected method, here DFS and BFS differ so we use them override it in subclass.
     */
    protected abstract boolean _isConnected(int source, int target);
}
