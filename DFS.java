import java.util.ArrayList;
import java.util.Iterator;

public class DFS extends SearchAlgo {
    private ArrayList<Integer> postorder;
        
    public DFS(Graph graph) {
        super(graph);
        postorder = new ArrayList<>();
    }

    /*
     * The protected recursive DFS method, which uses the adjacency iterator to check connections.
     * Returns back to the public method in the superclass.
     */
    @Override
    protected boolean _isConnected(int source, int target) {
        System.out.println("DFS called on " + source);
        if (source == target) {
            return true;
        }
        if (marked.get(source) == false) {
            marked.set(source, true);
            Iterator<Integer> v = graph.getAdjacencyIterator(source);
            while (v.hasNext()) {
                int e = v.next();
                if (_isConnected(e, target)) {
                    return true;
                }
            }
        }
        return false;  
    }

    /*
     * Method for determining connected components.
     */
    public ArrayList<ArrayList<Integer>> getConnectedComponents() {
        ArrayList<ArrayList<Integer>> trees = new ArrayList<>();
        Integer[] vertices = new Integer[graph.nrOfVertices()];
        ArrayList<Boolean> marked = new ArrayList<>();
        for (int i = 0; i < graph.nrOfVertices(); i++) {
            marked.add(false);
        }
        for (int i = 0; i < graph.nrOfVertices(); i++) {
            if (!marked.get(i)) {
                ArrayList<Integer> conns = findConnectedVertices(i);
                trees.add(conns);
                for (int j = 0; j < conns.size(); j++) {
                    marked.set(conns.get(j), true);
                }
                vertices[i] = i;
            }
        }
        return trees;
    }

    /*
     * Method to find all vertices connected to the source vertex.
     */
    private ArrayList<Integer> findConnectedVertices(int source) {
        ArrayList<Integer> connectedVertices = new ArrayList<>();
        boolean[] visited = new boolean[graph.nrOfVertices()];
        _DFS(source, visited, connectedVertices, false);
        return connectedVertices;
    }

    /*
     * Recursively identify connected vertices. We can also collect postorder of the vertices.
     */
    private void _DFS(int source, boolean[] visited, ArrayList<Integer> connectedVertices, boolean collectPostorder) {
        visited[source] = true;
        connectedVertices.add(source);
        Iterator<Integer> v = graph.getAdjacencyIterator(source);
        while (v.hasNext()) {
            int destination = v.next();
            if (!visited[destination]) {
                _DFS(destination, visited, connectedVertices, collectPostorder);
            }
        }
        if (collectPostorder) {
            postorder.add(source);
        }
    }

    /*
     * Get the postorder of the vertices.
     */
    public ArrayList<Integer> getPostOrder() {
        boolean[] visited = new boolean[graph.nrOfVertices()];
        for (int i = 0; i < graph.nrOfVertices(); i++) {
            if (!visited[i]) {
                _DFS(i, visited, new ArrayList<>(), true); // Start DFS from unvisited vertices
            }
        }
        return postorder;
    }
}
