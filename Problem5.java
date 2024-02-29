import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Problem5 {
    public static void main(String[] args) {
        File file = new File("data.txt");
        Problem5 p5 = new Problem5();
        int numberOfCourses = p5.getLength(file);
        CourseGraph graph = new CourseGraph(numberOfCourses, file);

        System.out.println("Courses:");
        Iterator<Integer> iter = graph.iterator();
        while (iter.hasNext()) {
            int v = iter.next();
            System.out.println(graph.getCourseName(v));
        }
        ArrayList<String> topologicalOrder = graph.topologicalSort();
        System.out.println("Topologically sorted. That is, take them in this order:\n" + topologicalOrder);
    }

    /*
     * Get the size of the graph we are about to make by counting unique courses.
     */
    public int getLength(File file) {
        ArrayList<String> courses = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                String[] cs = row.split(";");
                if (!courses.contains(cs[0])) {
                    courses.add(cs[0]);
                }
                if (!courses.contains(cs[1])) {
                    courses.add(cs[1]);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }        
        return courses.size();
    }
}
