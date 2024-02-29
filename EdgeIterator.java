import java.util.ArrayList;
import java.util.Iterator;

public class EdgeIterator implements Iterator<Edge> {
    private int currentIndex;
    private int currentVertex;
    private ArrayList<Edge>[] adjacencyList;

    public EdgeIterator(ArrayList<Edge>[] al) {
        currentIndex = 0;
        currentVertex = 0;
        this.adjacencyList = al;
    }

    /*
     * Hasnext method basically checks a 2D space for edges.
     */
    @Override
    public boolean hasNext() {
        while (currentVertex < adjacencyList.length) {
            if (currentIndex < adjacencyList[currentVertex].size()) {
                return true;
            }
            currentIndex = 0;
            currentVertex += 1;
        }
        return false;
    }

    @Override
    public Edge next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        return adjacencyList[currentVertex].get(currentIndex++);
    }
}
