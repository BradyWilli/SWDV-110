1. Pick one method in `MusicStats` and rewrite it with a `for` loop in your README. Which version is easier to read? Which is easier to change?

public static long totalMinutes(List<Play> plays, Predicate<Play> filter){
    int totalSecondsInt = 0;

    for (Play play : plays){
        if(filter.test(play)){
            totalSecondsInt= totalSecondsInt + play.seconds();
        }
    }

    return (totalSecondsInt / 60);
}

2. `PlayFormatter` and `Function<Play, String>` have the same shape. Why might a team still create its own functional interface?
A team would want to use its own functional interface for increased control, maintainability and readability.

3. Why must the list passed to `PlayCleaner.clean` be an `ArrayList` and not the result of `List.of(...)` or `stream().toList()`?
PlayCleaner.clean needs an array list because of the data types. An array list is more malleable than an immutable string produced by a list of or to list.

4. A stream does nothing until a *terminal* operation runs. Name three terminal operations you used.
Tolist, collect and count are three terminal operations I used.