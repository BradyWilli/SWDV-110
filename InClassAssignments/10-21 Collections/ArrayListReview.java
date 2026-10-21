
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArrayListReview {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Kai");
        names.add("Ana");
        names.add(0, "Zoe"); //doesn't overwrite element 0, adds it in and moves everything over
        names.add("Ana");
        names.remove("Ana"); //only removes one ana

        System.out.println(names);
        System.out.println(names.size());
        System.out.println(names.contains("Ana"));
        System.out.println(names.indexOf("Kai"));

        Set<String> tags = new HashSet<>();
            boolean added = tags.add("java"); // true
            tags.add("sql");
            tags.add("java"); // false – already there
            tags.add("fx");

            System.out.println(tags.size()); // 3
            System.out.println(tags.contains("sql")); // true – very fast
            System.out.println(tags); // order NOT guaranteed

        Map<String, String> courses = new HashMap<>();
        courses.put("SWDV-110", "Intermediate Programming");
        courses.put("SWDV-280", "Collaborative Development");
        String title = courses.get("SWDV-110"); // "Intermediate Programming
        try {
            courses.put("SWDV-110", "Duplicate Content");
            System.out.println(courses);
        } catch (Exception ex){
            System.out.println("Can't add duplicate courses to the catalog");
        }

        Deque<String> line = new ArrayDeque<>();
            line.offer("Ana"); // join the back
            line.offer("Kai");
            line.offer("Zoe");
            line.poll(); // "Ana" leaves first
            line.peek(); // "Kai" – next up

        Deque<String> undo = new ArrayDeque<>();
            undo.push("type A"); // on top
            undo.push("type B");
            undo.push("delete");
            undo.pop(); // "delete" first
            undo.peek(); // "type B"
    }

}
