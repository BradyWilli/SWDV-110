import java.util.Map;
import java.util.TreeMap;

public class CountingWithMap {
    public static void main(String[] args) {
        String text = "the cat and the hat and the bat";
        Map<String, Integer> counts = new TreeMap<>();
        for (String word : text.split(" ")) {
            int current = counts.getOrDefault(word, 0);
            counts.put(word, current + 1);
        }
        for (Map.Entry<String, Integer> e : counts.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
    }
}
}
