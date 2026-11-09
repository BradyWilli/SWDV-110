package edu.cwi.swdv110.music;

import java.time.LocalDate;

/**
 * One time a user played a song.
 */
public record Play(String user, String song, String artist, String genre, LocalDate date, int seconds) {

    /**
     * Parses "maya,Paper Satellites,Static Bloom,Indie,2026-09-28,161". Trim
     * every field. Wrong number of fields -> IllegalArgumentException. (An
     * empty song title is allowed here – PlayCleaner removes it later.)
     */
    // - `Play.parse`: `split(",")`, check that there are 6 fields, and `trim()` each one.
    //  `LocalDate.parse` reads `2026-09-28` directly.
    // - `PlayCleaner.clean`:
    //   - `plays.removeIf(p -> ...)` deletes every element that matches.
    //   - `plays.replaceAll(op)` swaps each element for `op.apply(element)`.
    //  Because `Play` is a record, you can't *change* a play; you build a corrected copy.
    public static Play parse(String csvLine) {
        // TODO Part 1
        String comma = ",";
        String[] csvlineArray = csvLine.split(comma);
        if (csvlineArray.length == 6) {
            for (int i = 0; i < csvlineArray.length; i++)
                csvlineArray[i] = csvlineArray[i].trim();
        }else{
            throw new IllegalArgumentException();
        }
        String userString = csvlineArray[0];
        String songString = csvlineArray[1];
        String artisString = csvlineArray[2];
        String genreString = csvlineArray[3];
        LocalDate date = LocalDate.parse(csvlineArray[4]);
        int secondsInt = Integer.parseInt(csvlineArray[5]);
        
        Play newPlay = new Play(userString , songString, artisString, genreString, date, secondsInt);

        return (newPlay);
        
        // throw new UnsupportedOperationException("TODO");
    }
}
