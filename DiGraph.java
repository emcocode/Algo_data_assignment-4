public class DiGraph extends Graph {

    public DiGraph(int v) {
        super(v);
    }

    /*
     * Add edge method without weight argument.
     */
    @Override
    public void add_edge(int source, int destination) {
        if (source < al.length && destination < al.length) {
            boolean exists = false;
            for (Edge ed : al[source]) {
                if (ed.getDestination() == destination) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                Edge e = new Edge(source, destination, 1.0);
                al[source].add(e);
                edges += 1;
            }
        }
    }

    /*
     * Add edge method with weight arugment.
     */
    @Override
    public void add_edge(int source, int destination, double weight) {
        if (source < al.length && destination < al.length) {
            boolean exists = false;
            for (Edge ed : al[source]) {
                if (ed.getDestination() == destination) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                Edge e = new Edge(source, destination, weight);
                al[source].add(e);
                edges += 1;
            }
        }
    }

    /*
     * Remove edge between source and destination.
     */
    @Override
    public void remove_edge(int source, int destination) {
        if (source < al.length && destination < al.length) {
            for (Edge ed : al[source]) {
                if (ed.getDestination() == destination) {
                    al[source].remove(ed);
                    break;
                }
            }
        }
    }   
}
