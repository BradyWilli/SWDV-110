package edu.cwi.swdv110.music;

import java.time.LocalDate;
import java.util.function.Predicate;

/** Factory methods that build reusable Predicate<Play> objects. Each body is ONE lambda. */
public final class PlayFilters {

    private PlayFilters() { }
// Each method returns a lambda, a piece of behavior stored in a variable.
// The payoff comes in the test `filtersAndCombinations`: small predicates snap together with `and`, `or` and `negate`
// into new questions, without writing any new methods.

    /** Matches plays by this user, ignoring case. */
    public static Predicate<Play> byUser(String user) {
        //return lambda counting the number of times a user is listed
        // String streamString = user.stream();
        // long countUsers = user.stream().filter(s -> s.user.Equals("MAYA")).count();
        
        // Predicate<Play> testPredicate = s -> user.equalsIgnoreCase(user); //(user) or (maya) returns 8
        //Predicate<Play> testPredicate = s -> s.equals("MAYA");
        // return (testPredicate);
        return (s -> s.user().equalsIgnoreCase(user));
     

        
        // throw new UnsupportedOperationException("TODO");
    }

    /** Matches plays in this genre, ignoring case. */
    public static Predicate<Play> byGenre(String genre) {
        //return lambda counting number of times a genre appears
        // Predicate<Play> testPredicate = s -> genre.equalsIgnoreCase(genre); //also gives 8
        // return (testPredicate);

        return (s -> s.genre().equalsIgnoreCase(genre));

        // throw new UnsupportedOperationException("TODO");
    }

    /** Matches plays from `from` through `to`, inclusive on both ends. */
    public static Predicate<Play> between(LocalDate from, LocalDate to) {

        return (s -> !s.date().isBefore(from) && !s.date().isAfter(to));

        //throw new UnsupportedOperationException("TODO");
    }

    /** A "skip" is any play shorter than 30 seconds. */
    public static Predicate<Play> skipped() {
        return (s -> s.seconds()<=30);
        // throw new UnsupportedOperationException("TODO");
    }
}