public class UnionFind {

    private int[] list;

    public UnionFind(int length) {
        list = new int[length];
        for (int i = 0; i < length; i++) {
            list[i] = i;
        }
    }
    
    public boolean connected(int a, int b) {
        return (list[a] == list[b]);
    }

    public void union(int a, int b) {
        int a_id = list[a];
        int b_id = list[b];

        for (int i = 0; i < list.length; i++) {
            if (list[i] == a_id) {
                list[i] = b_id;
            }
        }
    }    

}