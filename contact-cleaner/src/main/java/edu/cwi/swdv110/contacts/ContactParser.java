package edu.cwi.swdv110.contacts;

/**
 * Static helper methods that clean up one piece of a messy contact line.
 */
public final class ContactParser {

    private ContactParser() {
    }

    /**
     * Capitalizes the first letter of the word and every letter that follows a
     * hyphen. All other letters become lowercase. "jOHN" -> "John"
     * "garcia-LOPEZ" -> "Garcia-Lopez" "" -> ""
     */
    public static String titleCase(String word) {
        if (word.isEmpty()) {
            return "";
        }

        StringBuilder titleCase = new StringBuilder();
        for (char ch : word.toCharArray()) {
            titleCase.append(Character.toLowerCase(ch));
        }
        titleCase.setCharAt(0, Character.toUpperCase(titleCase.charAt(0)));
        for (int i = 0; i < titleCase.length(); i++) {
            if (titleCase.charAt(i) == '-') {
                titleCase.setCharAt(i + 1,
                        Character.toUpperCase(titleCase.charAt(i + 1)));
            }
        }

        String useTitleCase = titleCase.toString();

        return (useTitleCase);
    }

    /**
     * Cleans a raw name. - trims it and collapses runs of spaces to one space -
     * "Last, First Middle" is reordered to "First Middle Last" - every word is
     * title-cased Returns "" for a blank name. " smith, JOHN " -> "John Smith"
     * "DE LA CRUZ, ana" -> "Ana De La Cruz" "maria garcia-lopez" -> "Maria
     * Garcia-Lopez"
     */
    public static String normalizeName(String raw) {
// TODO Part 1 – hint: indexOf(','), substring, split("\\s+"), String.join
        String rawString = raw;
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
            return "";
        }
        StringBuilder rawStringBuilder = new StringBuilder();
        for (char ch : rawString.toCharArray()) {
            rawStringBuilder.append(Character.toLowerCase(ch));
        }

        rawStringBuilder.setCharAt(0, Character.toUpperCase(rawStringBuilder.charAt(0)));
        for (int i = 0; i < rawStringBuilder.length() - 1; i++) {
            if (rawStringBuilder.charAt(i) == ' ' || rawStringBuilder.charAt(i) == '-') {
                rawStringBuilder.setCharAt(i + 1, Character.toUpperCase(rawStringBuilder.charAt(i + 1)));
            }
        }

        String upperCaseRaw = rawStringBuilder.toString();
        upperCaseRaw = upperCaseRaw.trim();

        return (upperCaseRaw);
        //throw new UnsupportedOperationException("TODO");
    }

    /**
     * Returns true when the (trimmed) email: 1. has no spaces, 2. has exactly
     * one '@' with at least one character before it, and 3. has a domain (the
     * part after '@') that contains a '.' that is not its first character, does
     * not end with '.', and never has two dots in a row.
     */
    public static boolean isValidEmail(String email) {
// TODO Part 2 – no regular expressions; use indexOf / lastIndexOf / substring
        boolean validBool = false;
        boolean spacesValid = false;
        boolean atSignValid = false;
        boolean domainValid = false;

        email = email.trim();
        int atSign = email.indexOf("@");
        //String oneAtSign = email.substring(atSign - 1);
        String domainAfterAt = email.substring(atSign + 1);
        int dot = domainAfterAt.indexOf(".");

        //nospaces
        if (!email.contains(" ")) {
            spacesValid = true;
        }

        //1@
        //1 char before the @
        if (atSign > 0 && atSign == email.lastIndexOf("@")) {
            atSignValid = true;
        }

        //has a domain
        //1 char after @ 
        //doesn't end with .
        //doesn't have ..
        if (atSign >= 0) {
            if (!domainAfterAt.isEmpty() && dot > 0 && dot < domainAfterAt.length() - 1 && !domainAfterAt.contains("..")) {
                domainValid = true;
            }
        }

        //if everything good return true
        if (spacesValid == true && atSignValid == true && domainValid == true) {
            validBool = true;
        }

        return (validBool);
        //throw new UnsupportedOperationException("TODO");
    }

    /**
     * Trims and lowercases an email: " JSmith@Example.COM " ->
     * "jsmith@example.com"
     */
    public static String normalizeEmail(String email) {
        email = email.trim();
        email = email.toLowerCase();
        return (email);
        //throw new UnsupportedOperationException("TODO");
    }

    /**
     * Hides all but the first character before the '@': "jsmith@example.com" ->
     * "j*****@example.com" If there are fewer than 2 characters before the '@'
     * (or no '@'), return the email unchanged.
     */
    public static String maskEmail(String email) {
// TODO Part 2 – hint: "*".repeat(n)
//one line with `charAt`, `"*".repeat(...)`, and `substring`.
        int atSign = email.indexOf("@");

        // String firstLetter = email.substring(0, 1);
        // String atEmail = email.substring(atSign);
        // String toBeMasked = email.substring(1, atSign);
        // String maskedEmail = toBeMasked.replace(toBeMasked, "*".repeat(toBeMasked.length()));
        // String firstLetter = email.substring(0, 1);
        // String atEmail = email.substring(atSign);
        // String toBeMasked = email.substring(0, atSign);
        // toBeMasked = toBeMasked.replace(toBeMasked, "*".repeat(toBeMasked.length()-1).charAt(1));
        // String maskedEmail = toBeMasked.replace(toBeMasked, "*".repeat(toBeMasked.length()));
        email = email.substring(0, 1) + "*".repeat(atSign - 1) + email.substring(atSign);
        return (email);
        //throw new UnsupportedOperationException("TODO");
    }

    /**
     * Keeps only the digits. If there are 11 digits and the first is '1', drop
     * that leading 1. Exactly 10 digits -> "(208) 555-0199". Anything else ->
     * "INVALID".
     */
    public static String formatPhone(String raw) {
// TODO Part 3 – hint: loop over toCharArray(), Character.isDigit, StringBuilder
//Loop over `raw.toCharArray()` and `append` only the digits to a `StringBuilder`.
//Handle the leading `1`, check the length,
//then build the result with `String.format("(%s) %s-%s", ...)` and three `substring` calls.

        StringBuilder phoneNumberStringBuilder = new StringBuilder();

        for (char ch : raw.toCharArray()) {
            if (Character.isDigit(ch)) {
                phoneNumberStringBuilder.append(Character.toLowerCase(ch));
            }
        }
        if (phoneNumberStringBuilder.length() > 10) {
            if (phoneNumberStringBuilder.charAt(0) == '1') {
                phoneNumberStringBuilder= phoneNumberStringBuilder.delete(0, 1);
            } else {
                return ("INVALID");
            }
        }

        if (phoneNumberStringBuilder.length() <= 9) {
            return ("INVALID");
        }
        
        String phoneString = phoneNumberStringBuilder.toString();
        phoneString = String.format("(%s) %s-%s", phoneString.substring(0, 3), phoneString.substring(3, 6), phoneString.substring(6));
        return (phoneString);
        //throw new UnsupportedOperationException("TODO");
    }

    /**
     * Username = first letter of the first word + the whole LAST word of a
     * cleaned name, lowercase, letters only, at most 8 characters. "John Smith"
     * -> "jsmith" "Maria Garcia-Lopez" -> "mgarcial" "Ana De La Cruz" ->
     * "acruz" A one-word name uses just that word: "Bob" -> "bob"
     */
    public static String makeUsername(String fullName) {
// TODO Part 4
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Parses one line of the file: "name | email | phone". Returns a Contact
     * with a normalized name, normalized email and formatted phone. Throws
     * IllegalArgumentException if the line does not have exactly 3 fields or
     * the name is blank. (An invalid email or phone is NOT an exception – the
     * report flags those.)
     */
    public static Contact parseLine(String line) {
// TODO Part 4 – careful: '|' means something special to split(), so escape it: "\\|"
        throw new UnsupportedOperationException("TODO");
    }
}
