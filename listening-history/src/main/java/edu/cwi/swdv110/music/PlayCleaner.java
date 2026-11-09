package edu.cwi.swdv110.music;

import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Fixes messy rows IN PLACE using the List methods that take lambdas.
 */
public final class PlayCleaner {

    private PlayCleaner() {
    }

    /**
     * " hip-HOP " -> "Hip-Hop", "ELECTRONIC" -> "Electronic" (capitalize after
     * '-' and ' ')
     */
    public static String titleCase(String text) {
        // TODO Part 1 – you wrote one of these in Week 1!
        text = text.trim().toLowerCase();
        String[] parts = text.split("\\|");
        String nameString = "";

        String rawString = parts[0];
        rawString = rawString.trim();
        rawString = rawString.toLowerCase();

        int comma = rawString.indexOf(",");
        String firstName = "";
        String lastName = "";

        if (rawString.contains(",")) {
            firstName = rawString.substring((comma) + 1);
            lastName = rawString.substring(0, comma);//rawString.substring(rawString.lastIndexOf(comma) + 1);
            rawString = firstName + " " + lastName;
        }

        rawString = rawString.trim();
        rawString = rawString.replaceAll("\\s+", " ");
        if (rawString.isEmpty()) {
            nameString = "IllegalArgumentException";
        } else {
            StringBuilder rawStringBuilder = new StringBuilder();
            for (char ch : rawString.toCharArray()) {
                rawStringBuilder.append(Character.toLowerCase(ch));
            }

            try {
                rawStringBuilder.setCharAt(0, Character.toUpperCase(rawStringBuilder.charAt(0)));
                for (int i = 0; i < rawStringBuilder.length() - 1; i++) {
                    if (rawStringBuilder.charAt(i) == ' ' || rawStringBuilder.charAt(i) == '-') {
                        rawStringBuilder.setCharAt(i + 1, Character.toUpperCase(rawStringBuilder.charAt(i + 1)));
                    }
                }

                String upperCaseRaw = rawStringBuilder.toString();
                upperCaseRaw = upperCaseRaw.trim();

                nameString = upperCaseRaw;
            } catch (Exception e) {
                nameString = "IllegalArgumentException";
            }

        }
        return (nameString);
        // throw new UnsupportedOperationException("TODO");
    }

    /**
     * 1. removeIf: delete plays with seconds <= 0 or a blank song title. 2.
     * replaceAll: records are immutable, so replace each Play with a NEW Play
     * whose user is lowercase and whose genre is title-cased. Write the lambda
     * as a UnaryOperator<Play> variable first, then pass it to replaceAll.
     * Returns how many plays were removed.
     */
    //   - `plays.removeIf(p -> ...)` deletes every element that matches.
    //   - `plays.replaceAll(op)` swaps each element for `op.apply(element)`. Because `Play` is a record, you can't *change* a play; you build a corrected copy.
    public static int clean(List<Play> plays) {
        int originalSize = plays.size();
        plays.removeIf(p -> (p.seconds() <= 0 || p.song().isBlank()));
        UnaryOperator<Play> thingOperator = play -> new Play(
                play.user().toLowerCase(),
                play.song(),
                play.artist(),
                titleCase(play.genre()),
                play.date(),
                play.seconds());
        plays.replaceAll(thingOperator);

        int diff = originalSize - plays.size();
        return (diff);

        // plays.replaceAll(operator);
        // String regex = "(?i)cat";
        // System.out.println(myString.replaceAll(regex, "dog"));
        // throw new UnsupportedOperationException("TODO");
    }
}
