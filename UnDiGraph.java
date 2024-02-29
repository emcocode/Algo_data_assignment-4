public class UnDiGraph extends Graph {

    public UnDiGraph(int v) {
        super(v);
    }

     /*
     * Add edge method without weight argument.
     */
    @Override
    public void add_edge(int a, int b) {
        if (a < al.length && b < al.length) {
            boolean exists = false;
            for (Edge ed : al[a]) {
                if (ed.getDestination() == b) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                Edge eb = new Edge(a, b, 1.0);
                Edge ea = new Edge(b, a, 1.0);
                al[a].add(eb);
                al[b].add(ea);
                edges += 1;
            }
        }
    }

    /*
     * Add edge method with weight arugment.
     */
    @Override
    public void add_edge(int a, int b, double c) {
        if (a < al.length && b < al.length) {
            boolean exists = false;
            for (Edge ed : al[a]) {
                if (ed.getDestination() == b) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                Edge eb = new Edge(a, b, c);
                Edge ea = new Edge(b, a, c);
                al[a].add(eb);
                al[b].add(ea);
                edges += 1;
            }
        }
    }

    /*
     * Remove edge between a and b.
     */
    @Override
    public void remove_edge(int a, int b) {
        if (a < al.length && b < al.length) {
            boolean exists = false;
            for (Edge ed : al[a]) {
                if (ed.getDestination() == b) {
                    exists = true;
                    al[a].remove(ed);
                    break;
                }
            }
            if (exists) {
                for (Edge ed : al[b]) {
                    if (ed.getDestination() == a) {
                        al[b].remove(ed);
                        break;
                    }
                }
            }
        }
    }
}
