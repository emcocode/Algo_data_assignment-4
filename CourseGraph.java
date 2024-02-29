import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

class CourseGraph extends Graph {
    private String[] courseNames;
    
    public CourseGraph(int v, File file) {
        super(v);
        _addCourses(file, v);
        _addAllEdges(file);
    }

    /*
     * Helper method for adding courses to the graph.
     */
    private void _addCourses(File file, int v) {
        courseNames = new String[v];
        int counter = 0;
        Scanner sc;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()){
                String row = sc.nextLine();
                String[] cs = row.split(";");
                if (!courseExist(cs[1])) {
                    courseNames[counter] = cs[1];
                    counter += 1;
                }
                if (!courseExist(cs[0])) {
                    courseNames[counter] = cs[0];
                    counter += 1;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  
    }

    /*
     * Checks if there already is an existing course with a specific name.
     */
    private boolean courseExist(String name) {
        Iterator<Integer> course = getVertexIterator();
            while (course.hasNext()) {
                int w = course.next();
                if (name.equals(courseNames[w])) {
                    return true;
                }
            }
        return false;
    }

    /*
     * Getter for the course name.
     */
    public String getCourseName(int v) {
        return courseNames[v];
    }

    /*
     * Method for adding edges from the data file.
     */
    private void _addAllEdges(File file) {
        Scanner sc;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()){
                String row = sc.nextLine();
                String[] cs = row.split(";");
                add_edge(cs[0], cs[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  
    }

    /*
     * Method for adding an edge (prerequisite) between vertices (courses).
     * It is a public method so that we can add new prerequisites from elsewhere.
     */
    public void add_edge(String source, String destination) {
        boolean exists = false;
        Iterator<Integer> vertex = getVertexIterator();
        while (vertex.hasNext()) { // Find the source vertex
            int v = vertex.next();
            if (getCourseName(v).equals(source)) {
                Iterator<Edge> adjacency = new EdgeAdjacencyIterator(al[v]);
                while (adjacency.hasNext()) {
                    adjacency.next();
                    if (getCourseName(v).equals(destination)) { // Check if already exists
                        exists = true;
                    }
                }
                if (!exists) { // IF it doesn´t exist, we add
                    Iterator<Integer> vertexDestination = getVertexIterator();
                    while (vertexDestination.hasNext()) { // Find the destination vertex
                        int w = vertexDestination.next();
                        if (getCourseName(w).equals(destination)) {
                            al[v].add(new Edge(v, w, 1.0));
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

    /*
     * Sorting the courses in a topological order.
     */
    public ArrayList<String> topologicalSort() {
        DFS dfs = new DFS(this);
        ArrayList<Integer> postorder = dfs.getPostOrder();
        ArrayList<String> result = new ArrayList<>();
        for (int i = postorder.size() - 1; i >= 0; i--) {
            int v = postorder.get(i);
            result.add(getCourseName(v));
        }
        Collections.reverse(result);
        return result;
    }    
}