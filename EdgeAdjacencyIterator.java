import java.util.ArrayList;
import java.util.Iterator;

/*
 * This iterators is an adjacency iterator, where we iterate over the edge objects connecting the vertices instead of pointing to the adjacent vertex.
 */
public class EdgeAdjacencyIterator implements Iterator<Edge> {
    private ArrayList<Edge> el;
    private int currentIndex;

    public EdgeAdjacencyIterator(ArrayList<Edge> edgeList) {
        this.el = edgeList;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < el.size();
    }

    @Override
    public Edge next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        Edge edge = el.get(currentIndex);
        currentIndex++;
        return edge;
    }
}
