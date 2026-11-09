package edu.cwi.swdv110.music;

import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Every method is a stream pipeline – no for or while loops in this class!
 */
public final class MusicStats {

    private MusicStats() {
    }
    // Work through `MusicStats` one method at a time, running `mvn test` after each. Tips:

    // - Read each pipeline aloud as a sentence: *"From the plays, **keep** maya's, **turn each into** its seconds, **add them up**."*
    // - When you need "the top N of something," it's almost always two steps: **group & count** into a `Map`, then **stream the entries** and sort them.
    // - `topGenre` needs a tie-breaker. `max` keeps the *greatest* element, so think about which key ordering makes `"Country"` greater than `"Rock"`.
    // - Remember that **no loops** are allowed in this class.
    /**
     * Newest date first; same date -> artist A–Z; same artist -> song A–Z. TODO
     * Part 3: build it with
     * Comparator.comparing(...).reversed().thenComparing(...)
     *
     * roster.sort(Comparator.comparing(Student::name) .reversed()
     * .thenComparing(Student::major) .thenComparing(Student::gpa));
     * roster.removeIf(student -> student.gpa() < 2.0);
     */
    public static final Comparator<Play> NEWEST_FIRST = Comparator.comparing(Play::date)
            .reversed()
            .thenComparing(Play::artist)
            .thenComparing(Play::song);

    /**
     * Total seconds of the plays matching `filter`, divided by 60 (whole
     * minutes).
     */
    public static long totalMinutes(List<Play> plays, Predicate<Play> filter) {
        // TODO – filter, mapToInt, sum
        // plays.sort(Comparator.comparing(plays::seconds)
        int total = plays.stream()
                .filter(filter)
                .mapToInt(Play::seconds)
                .sum() / 60;
        // .divided(60);
        return (total);
        // throw new UnsupportedOperationException("TODO");
    }

    /**
     * genre -> number of plays, sorted by genre.
     */
    public static Map<String, Long> playsPerGenre(List<Play> plays) {
        // TODO – groupingBy(classifier, TreeMap::new, counting())
        Map<String, Long> playsMap = plays.stream()
                .collect(Collectors.groupingBy(Play::genre, TreeMap::new, Collectors.counting()));
        return (playsMap);
        // throw new UnsupportedOperationException("TODO");
    }

    /**
     * user -> total seconds, sorted by user.
     */
    public static Map<String, Integer> secondsPerUser(List<Play> plays) {
        // TODO – summingInt

        Map<String, Integer> secondsMap = plays.stream()
                .collect(Collectors.groupingBy(Play::user, TreeMap::new, Collectors.summingInt(Play::seconds)));
        // .summingInt(secondsPerUser(plays));
        return (secondsMap);
        // throw new UnsupportedOperationException("TODO");
    }

    /**
     * The n artists with the most plays, most first. Ties: artist name A–Z.
     * Hint: group & count into a Map, then stream its entrySet() and sort with
     * Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey())
     */
    public static List<String> topArtists(List<Play> plays, int n) {
        // TODO
        Map<String, Long> topArtistString = plays.stream()
                .collect(Collectors.groupingBy(Play::artist, TreeMap::new, Collectors.counting()));

        List<String> interpret = topArtistString.entrySet().stream()
                // .entrySet()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .map(Map.Entry::getKey)
                .limit(n)
                .toList();

        return (interpret);
        // throw new UnsupportedOperationException("TODO");
    }

    /**
     * The single longest play, or Optional.empty() for an empty list.
     */
    public static Optional<Play> longestPlay(List<Play> plays) {
        // TODO – max(Comparator.comparingInt(...))
        Optional<Play> longestPlayOptional = plays.stream()
                .max(Comparator.comparing(Play::seconds));
        return (longestPlayOptional);
        // throw new UnsupportedOperationException("TODO");
    }

    /**
     * Distinct song titles by this artist (ignore case), sorted A–Z.
     */
    public static List<String> songsBy(List<Play> plays, String artist) {
        // TODO
        List<String> songsByString = plays.stream()
                .filter(play -> play.artist().equalsIgnoreCase(artist))
                .map(Play::song)
                .distinct()
                .sorted()
                .toList();
        // .collect(Collector.groupingBy(Play::artist, TreeMap::new, Collectors.equalsIgnoreCase(artist)));
        return (songsByString);
        // throw new UnsupportedOperationException("TODO");
    }

    /**
     * The n most recent plays, ordered by NEWEST_FIRST.
     */
    public static List<Play> recent(List<Play> plays, int n) {
        // TODO
        List<Play> recentString = plays.stream()
                .sorted(NEWEST_FIRST)
                .limit(n)
                .toList();
        return (recentString);
        // throw new UnsupportedOperationException("TODO");
    }

    /**
     * MONDAY..SUNDAY -> number of plays (only days that have plays). Uses Week
     * 3's java.time!
     */
    public static Map<DayOfWeek, Long> playsByDay(List<Play> plays) {
        // TODO
        Map<DayOfWeek, Long> dayMap = plays.stream()
                .collect(Collectors.groupingBy(play -> play.date().getDayOfWeek(), () -> new EnumMap<>(DayOfWeek.class), Collectors.counting()));
        return (dayMap);
        // throw new UnsupportedOperationException("TODO");
    }

    /**
     * Fraction (0.0–1.0) of this user's plays that were skips. 0.0 if the user
     * has no plays.
     */
    public static double skipRate(List<Play> plays, String user) {
        // TODO – reuse PlayFilters.byUser and PlayFilters.skipped

        List<Play> userPlays = plays.stream()
                .filter(PlayFilters.byUser(user)) //(s -> s.user().equalsIgnoreCase(user));
                .toList();

        if (userPlays.isEmpty()) {
            return 0.0;
        }

        long skips = userPlays.stream()
                .filter(PlayFilters.skipped())//(s -> s.seconds()<=30);
                .count();
        double skippedrate = (double) skips / userPlays.size();
        return (skippedrate);
        // throw new UnsupportedOperationException("TODO");
    }

    /**
     * The genre this user played most. Ties go to the genre that comes first
     * A–Z. Optional.empty() if the user has no plays.
     */
    public static Optional<String> topGenre(List<Play> plays, String user) {
        // TODO
        Optional<String> tipTopGenreOptional = plays.stream()
                .filter(PlayFilters.byUser(user))
                .collect(Collectors.groupingBy(Play::genre, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.<String, Long>comparingByValue().thenComparing(Map.Entry.comparingByKey(Comparator.reverseOrder())))
                .map(Map.Entry::getKey);

        return (tipTopGenreOptional);
        // throw new UnsupportedOperationException("TODO");
    }
}
