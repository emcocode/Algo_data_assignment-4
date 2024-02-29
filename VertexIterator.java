import java.util.Iterator;

public class VertexIterator implements Iterator<Integer> {
    private int currentIndex;
    private int[] vertices;

    public VertexIterator(int[] vertices) {
        this.vertices = vertices;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < vertices.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        return vertices[currentIndex++];
    }
}
