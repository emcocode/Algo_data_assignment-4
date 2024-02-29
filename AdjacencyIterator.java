import java.util.ArrayList;
import java.util.Iterator;

public class AdjacencyIterator implements Iterator<Integer> {
    private ArrayList<Edge> el;
    private int currentIndex;

    public AdjacencyIterator(ArrayList<Edge> edgeList) {
        this.el = edgeList;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < el.size();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        Edge edge = el.get(currentIndex);
        currentIndex++;
        return edge.getDestination();
    }
}
