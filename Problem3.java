import java.util.ArrayList;
import java.util.Iterator;

public class Problem3 {

    public static void main(String[] args) {
        Graph g = new UnDiGraph(8); // UnDiGraph (undirected graph) for Kruskal´s

        // g.add_edge(0, 1, 7);
        g.add_edge(0, 3, 6);
        g.add_edge(0, 6, 9);
        g.add_edge(1, 2, 5);
        // g.add_edge(1, 3, 2);
        g.add_edge(1, 4, 10);
        g.add_edge(2, 4, 4);
        g.add_edge(2, 7, 13);
        // g.add_edge(3, 5, 1);
        g.add_edge(3, 6, 12);
        g.add_edge(4, 5, 3);
        g.add_edge(4, 7, 8);
        // g.add_edge(5, 6, 14);
        g.add_edge(5, 7, 16);
        // g.add_edge(6, 7, 20);

        Kruskal k = new Kruskal();
        ArrayList<Graph> msts = k.findMST(g);
        System.out.println("\nThere are " + msts.size() + " mst:s in total.");
        int mstCount = 0;
        for (Graph mst : msts) {
            mstCount += 1;
            System.out.println("\nMinimal spanning tree " + mstCount + ":");
            Iterator<Edge> edgeIter = mst.getEdgeIterator();
            while (edgeIter.hasNext()) {
                Edge edge = edgeIter.next();
                if (edge.getDestination() > edge.getSource()) {
                    System.out.println(edge.getSource() + " - " + edge.getDestination() + " with weight " + edge.getWeight());
                    }
            }
        }    
    }
}
