import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends SearchAlgo {
    
    public BFS(Graph graph) {
        super(graph);
    }

    /*
    * The protected iterative BFS method, here we use a queue to check every adjacency at each depth before 
    * moving on to new depths.
    * Returns back to the public method above.
    */
    protected boolean _isConnected(int source, int target) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        marked.set(source, true);
        while (!queue.isEmpty()) {
            int current = queue.poll(); // pop
            System.out.println("BFS called on " + current);
            if (current == target) {
                return true;
            }
            Iterator<Integer> v = graph.getAdjacencyIterator(current);
            while (v.hasNext()) {
                int vertex = v.next();
                if (!marked.get(vertex)) {
                    marked.set(vertex, true);
                    queue.add(vertex);
                }
            }
        }
        return false;
    }
}
