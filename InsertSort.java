import java.util.ArrayList;

public class InsertSort {

    /*
     * Insert sort method for array.
     */
    public void edgeSort(ArrayList<Edge> el) {
        for (int i = 0; i < el.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (el.get(j).getWeight() < el.get(j - 1).getWeight()) {
                    Edge tmp = el.get(j);
                    el.set(j, el.get(j - 1));
                    el.set(j - 1, tmp);
                } else {
                    break;
                }
            }
        }
    }

    /*
     * Check if array is sorted.
     */
    public boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
}