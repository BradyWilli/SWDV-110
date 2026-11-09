public class binarySearch {
    public static void main(String[] args) {
        // binarySearch(a, target, low, high);
    }

    public static int binarySearch(int[]a, int target, int low, int high) {
        low = 0;
        high = a.length-1;
        while (low <= high) {
            int mid = low+high/2;
            if (a[mid] == target) {
                return mid; //found case
                // counter +1;
            }else if (a[mid] < target) {
                low= mid +1;
                // return updated binary search, counter + 1
            }else high = mid -1;
                // return updated binary search, counter +1
        }
        return target;
    }

    
}
