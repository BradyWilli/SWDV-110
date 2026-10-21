import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        String paragraphString =  "Own a musket for home defense, since that's what the founding fathers intended. Four ruffians break into my house. What the devil? As I grab my powdered wig and Kentucky rifle. Blow a golf ball sized hole through the first man, he's dead on the spot. Draw my pistol on the second man, miss him entirely because it is smoothbore and nails the neighbors dog. I have to resort to the cannon mounted at the top of the stairs loaded with grape shot, Tally ho lads! the grape shot shreds two men in the blast, the sound and extra shrapnel set off car alarms. Fix bayonet and charge the last terrified rapscallion. He Bleeds out waiting on the police to arrive since triangular bayonet wounds are impossible to stitch up. Just as the founding fathers intended.";
        paragraphString = paragraphString.toLowerCase();
        paragraphString = paragraphString.replaceAll("[^a-z]", " ");
        
        Map<String, Integer> counts = new TreeMap<>();
        for (String word : paragraphString.split(" ")) {
            int current = counts.getOrDefault(word, 0);
            counts.put(word, current + 1);
        }
        for (Map.Entry<String, Integer> e : counts.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
        
        int maxValueInMap = (Collections.max(counts.values()));
        System.out.println(maxValueInMap);
        
        Set<String> uniqueWordSet = new TreeSet<>(
        List.of(paragraphString.split(" ")));
        System.out.println(uniqueWordSet);
        

    }
}
