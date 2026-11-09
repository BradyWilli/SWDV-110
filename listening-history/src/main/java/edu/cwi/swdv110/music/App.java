package edu.cwi.swdv110.music;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws IOException {
        // TODO Part 4a: ONE stream pipeline: read all lines of plays.csv, skip the header,
        //   drop blank lines, map with Play::parse, and collect into an ArrayList
        //   (Collectors.toCollection(ArrayList::new) – clean() needs a list it can modify).
        
        List<Play> plays = Files.lines(Path.of("plays.csv"))
            .skip(1)
            .filter(line -> !line.isBlank())
            .map(Play::parse)
            .collect(Collectors.toCollection(ArrayList::new));


        // TODO Part 4b: clean the list and print "Loaded N plays (M bad rows removed)"
        int removed = PlayCleaner.clean(plays);

        System.out.printf("Loaded %d plays (%d bad rows removed)%n", plays.size(), removed);


        // TODO Part 4c: create two PlayFormatter lambdas:
        //   compact  -> "Afterglow – Lumen"
        //   detailed -> "2026-10-11  maya    Afterglow          Lumen              3:56"
        
        PlayFormatter compact = p
                -> p.song() + " - " + p.artist();

        PlayFormatter detailed = p
                -> String.format(
                        "%-10s %-8s %-18s %-18s %d:%02d",
                        p.date().format(DateTimeFormatter.ISO_LOCAL_DATE),
                        p.user(),
                        p.song(),
                        p.artist(),
                        p.seconds() / 60,
                        p.seconds() % 60);


        // TODO Part 4d: print the reports shown in the instructions, using Map.forEach((k, v) -> ...)
        //   and Optional.ifPresent(...)
        // Minutes per user
        System.out.println("\nMinutes per user:");

        MusicStats.secondsPerUser(plays).forEach((user, seconds) ->
            System.out.printf("  %-8s %d min%n", user, seconds / 60));

        // Plays per genre
        System.out.println("\nPlays per genre:");

        MusicStats.playsPerGenre(plays).forEach((genre, count) ->
            System.out.printf("  %-12s %s%n", genre, "#".repeat(count.intValue())));

        // Plays by day
        System.out.println("\nPlays by day:");

        MusicStats.playsByDay(plays).forEach((day, count) ->
            System.out.printf("  %-10s %d%n", day, count));

        // Top 3 artists
        System.out.println("\nTop 3 artists: " + MusicStats.topArtists(plays, 3));

        // Longest play
        System.out.print("Longest play:  ");

        MusicStats.longestPlay(plays).ifPresent(play ->
            System.out.println(compact.format(play)));

        // Most recent 5 plays
        System.out.println("\nMost recent:");

        MusicStats.recent(plays, 5).forEach(play ->
            System.out.println("  " + detailed.format(play)));


        // TODO Part 4e: loop – ask for a user name (or 'quit') and print their summary
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.print("\nUser to summarize (or 'quit'): ");
            String user = sc.nextLine().trim();

            if (user.equalsIgnoreCase("quit")) {
                break;
            }

            // Get this user's plays
            List<Play> userPlays = plays.stream()
                .filter(PlayFilters.byUser(user))
                .toList();

            // Calculate user statistics
            long minutes = MusicStats.totalMinutes(
                plays,
                PlayFilters.byUser(user));

            double skipRate = MusicStats.skipRate(plays, user);

            var genre = MusicStats.topGenre(plays, user);

            System.out.printf("%s: %d plays, %d min, top genre %s, skip rate %d%%%n", user, userPlays.size(), minutes, genre.orElse("None"), Math.round(skipRate * 100));

            // Minutes in the user's top genre
            genre.ifPresent(topGenre -> {

                long genreMinutes = MusicStats.totalMinutes(plays, PlayFilters.byUser(user)
                    .and(PlayFilters.byGenre(topGenre)));

                System.out.printf("  %s minutes: %d%n", topGenre, genreMinutes);
            });

            // Full listens (not skips), distinct and alphabetical
            List<String> fullListens = userPlays.stream()
                .filter(PlayFilters.skipped().negate())
                .map(compact::format)
                .distinct()
                .sorted()
                .toList();

            System.out.println("  Full listens:  " + String.join("; ", fullListens));
        }

        sc.close();
    }
}
