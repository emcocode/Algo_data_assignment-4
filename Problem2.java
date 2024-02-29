public class Problem2 {
    public static void main(String[] args) {
        Graph g = new DiGraph(12); // Digraph for directed graph, UnDiGraph for undirected graph

        g.add_edge(1, 4);
        g.add_edge(4, 7);
        g.add_edge(7, 3);
        g.add_edge(7, 11);
        g.add_edge(11, 6);

        SearchAlgo b = new BFS(g); // BFS for bfs, DFS for dfs
        System.out.println(b.isConnected(1, 6));
    }
}
