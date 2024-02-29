public class MinHeap {

    private Edge[] heap;
    private int size;

    public MinHeap(int cap) {
        heap = new Edge[cap];
        size = 0;
    }

    public void insert(Edge edge) {
        if (size < heap.length) {
            heap[size] = edge;
            _swim(size);
            size++;
        }
    }

    public Edge getMin() {
        if (size == 0) {
            throw new IllegalStateException("The heap is already empty!");
        }
        Edge minValue = heap[0];
        heap[0] = heap[size - 1];
        size--;
        _sink(0);
        return minValue;
    }

    /*
     * Swim method for moving nodes.
     */
    private void _swim(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap[index].getWeight() < heap[parentIndex].getWeight()) {
            Edge tmp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = tmp;
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    /*
     * Sink method for moving nodes.
     */
    private void _sink(int index) {
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        int smallest = index;
        if (leftIndex < size && heap[leftIndex].getWeight() < heap[smallest].getWeight()) {
            smallest = leftIndex;
        }
        if (rightIndex < size && heap[rightIndex].getWeight() < heap[smallest].getWeight()) {
            smallest = rightIndex;
        }
        if (smallest != index) {
            Edge tmp = heap[index];
            heap[index] = heap[smallest];
            heap[smallest] = tmp;
            _sink(smallest);
        }
    }

    public boolean isEmpty() {
        return (size == 0);
    }
}
