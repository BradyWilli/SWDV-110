package edu.cwi.swdv110.diskusage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Recursive folder walking. Files.walk() and other built-in walkers are
 * off-limits.
 */


      public final class FileScanner {

        private static final String[] UNITS = {"B", "KB", "MB", "GB", "TB"};

        private FileScanner() {
        }

        /**
         * GIVEN: the children of a folder sorted by name, or an empty array if
         * the folder can't be read (listFiles() returns null for protected
         * folders – this prevents a crash).
         */
        static File[] sortedChildren(File dir) {
            File[] kids = dir.listFiles();
            if (kids == null) {
                return new File[0];
            }
            Arrays.sort(kids, Comparator.comparing(File::getName));
            return kids;
        }
        // `totalSize`, `countFiles` and `maxDepth` all have the same shape:
        //      *if it's a file, answer directly;
        //      otherwise loop over `sortedChildren(f)` and combine the answers from the recursive calls.*
        //  What changes is how you **combine** them: sum, count, or max.
        // > Each folder makes one recursive call **per child**, so the calls form a tree, not a straight line. 
        // This is the kind of problem where recursion is much easier than a loop.

        // ---------------- Part 1 ----------------
        /**
         * Base case: a file's size is its length(). Recursive case: a folder's
         * size is the sum of the totalSize of each child.
         */
        public static long totalSize(File f) {
            //base return f.length
            if (f.isFile()) {
                return f.length();
            }
            //recursive return f = f + file until there are no files left
            long filesize = 0;

            File[] children = f.listFiles();

            if (children != null) {
                for (File child : children) {
                    filesize += totalSize(child);
                }
            }

            return filesize;
        }

        /**
         * Number of FILES (not folders) in f and everything under it. A single
         * file counts as 1.
         */
        public static int countFiles(File f) {
            //base return counter
            int fileCount = 0;
            if (f.isFile()) {
                return 1;
            }

            //recursive add 1 to counter for every file
            File[] children = f.listFiles();

            if (children != null) {
                for (File child : children) {
                    fileCount += countFiles(child);
                    // fileCount = fileCount +1;
                }
            }
            return fileCount;
        }

        /**
         * How many levels deep the nesting goes. a file, or an empty folder ->
         * 0 a folder that only contains files -> 1 otherwise -> 1 + the deepest
         * child
         */
        public static int maxDepth(File f) {
            //base return 0
            if (f.isFile()) {
                return 0;
            }

            int depth = 0;

            File[] children = f.listFiles();

            //recursive find deepest folder and return steps
            if (children != null) {
                for (File child : children) {
                    int childDepth = maxDepth(child);
                    depth = Math.max(depth, childDepth + 1);
                }
            }

            return depth;
        }

    // ---------------- Part 2 ----------------
    /**
     * Every file under root, in name order at each level, with paths relative
     * to root: "a.txt", "docs/img/cat.png", "docs/notes.txt" Do the work in the
     * private recursive helper below; this method just starts it.
     */
    public static List<FileInfo> listAll(File root) {
        List<FileInfo> result = new ArrayList<>();
        // TODO call collect(...) to fill result
        return result;
    }

    /**
     * Recursive helper: adds the files in dir to out. prefix is the path so
     * far, e.g. "docs/img/".
     */
    private static void collect(File dir, String prefix, List<FileInfo> out) {
        // TODO
    }

    /**
     * Files whose name ends with "." + ext (ignore case), in listAll order.
     */
    public static List<FileInfo> findByExtension(File root, String ext) {
        // TODO
        return new ArrayList<>();
    }

    /**
     * Human-readable size, 1024 bytes per KB: 0 -> "0 B" 999 -> "999 B" 1536 ->
     * "1.5 KB" 1048576 -> "1.0 MB" Write it RECURSIVELY with the helper below:
     * if the value is under 1024 (or you've run out of units) format it;
     * otherwise call yourself with value / 1024 and the next unit. Use
     * String.format(Locale.US, ...) so the decimal point is '.' on every
     * computer.
     */
    public static String humanSize(long bytes) {
        return humanSize(bytes, 0);
    }

    private static String humanSize(double value, int unit) {
        // TODO – "%.0f %s" for bytes, "%.1f %s" for every other unit
        return "";
    }

    // ---------------- Part 3 ----------------
    /**
     * An indented tree, two spaces per level, folders end with '/', each line
     * shows the size: myfolder/ (2.4 KB) a.txt (100 B) docs/ (2.2 KB) notes.txt
     * (300 B) Folders deeper than maxDepth are listed but their contents are
     * not (their size still includes everything inside).
     */
    public static String tree(File root, int maxDepth) {
        StringBuilder sb = new StringBuilder();
        // TODO call the recursive helper
        return sb.toString();
    }

    private static void tree(File f, int depth, int maxDepth, StringBuilder sb) {
        // TODO – use System.lineSeparator() at the end of each line
    }
}
