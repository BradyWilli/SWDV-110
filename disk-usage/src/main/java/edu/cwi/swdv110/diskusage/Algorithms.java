package edu.cwi.swdv110.diskusage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Hand-written searching and sorting. Collections.sort / List.sort are
 * off-limits here.
 */
public final class Algorithms {

    /**
     * Largest first; equal sizes ordered by path A–Z.
     */
    public static final Comparator<FileInfo> BY_SIZE_DESC
            = Comparator.comparingLong(FileInfo::size).reversed().thenComparing(FileInfo::path);

    /**
     * Path A–Z (the order binary search needs).
     */
    public static final Comparator<FileInfo> BY_PATH = Comparator.comparing(FileInfo::path);

    private Algorithms() {
    }

    /*
     * About <T>: these methods are GENERIC – they sort a List of any type T, as long as you
     * give them a Comparator<T> that says how to order two T's. Use cmp.compare(a, b)
     * exactly where the in-class version used a < b.
     */
    /**
     * Returns a NEW sorted list (don't modify the input). O(n²).
     */
    public static <T> List<T> selectionSort(List<T> list, Comparator<T> cmp) {
        List<T> a = new ArrayList<>(list);
        for (int i = 0; i < a.size() - 1; i++) {
            int minimumIndex = i;
            for (int j = i + 1; j < a.size(); j++) {
                if (cmp.compare(a.get(j), a.get(minimumIndex)) < 0) {
                    minimumIndex = j;
                }
            }
            T temporary = a.get(i);
            a.set(i, a.get(minimumIndex));
            a.set(minimumIndex, temporary);
        }
        return a;
    }

    /**
     * Returns a NEW sorted list. O(n log n). base case: size 0 or 1 -> a copy
     * of the list recursive case: mergeSort each half (list.subList is handy),
     * then merge them The merge must be STABLE: when two items compare equal,
     * take the one from the left half.
     */
    public static <T> List<T> mergeSort(List<T> list, Comparator<T> cmp) {
        if (list.size() < 2) {
            return new ArrayList<>(list); // base case

        }
        int mid = list.size() / 2;

        List<T> left = new ArrayList<>(list.subList(0, mid));
        List<T> right = new ArrayList<>(list.subList(mid, list.size()));

        // Recursive cases
        left = mergeSort(left, cmp);
        right = mergeSort(right, cmp);

        // return merge(left, right, cmp);
        return merge(left, right, cmp);
    }

    private static <T> List<T> merge(List<T> left, List<T> right, Comparator<T> cmp) {
        List<T> result = new ArrayList<>();

        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {

            if (cmp.compare(left.get(i), right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            result.add(left.get(i++));
        }

        while (j < right.size()) {
            result.add(right.get(j++));
        }

        return result;
    }

    /**
     * RECURSIVE binary search over a list already sorted BY_PATH. Returns the
     * index of the file with this exact path, or -1.
     */
    public static int binarySearchByPath(List<FileInfo> sortedByPath, String path) {
        
        return binarySearch(sortedByPath, path, 0, sortedByPath.size() - 1);
    }

    private static int binarySearch(List<FileInfo> a, String path, int low, int high) {

        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;

        int comparison = a.get(mid).path().compareTo(path);

        if (comparison == 0) {
            return mid;
        } else if (comparison < 0) {
            return binarySearch(a, path, mid + 1, high);
        } else {
            return binarySearch(a, path, low, mid - 1);
        }
    }

    /**
     * The n largest files (fewer if there aren't n), using YOUR mergeSort.
     */
    public static List<FileInfo> largest(List<FileInfo> files, int n) {
    Comparator<FileInfo> bySize = Comparator.comparingLong(FileInfo::size).reversed();

    List<FileInfo> sorted = mergeSort(files, bySize);

    return new ArrayList<>(
        sorted.subList(0, Math.min(n, sorted.size())));
    }
}
