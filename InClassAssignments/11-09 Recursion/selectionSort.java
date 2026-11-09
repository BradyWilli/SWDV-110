
public class selectionSort {

    public static void main(String[] args) {
        int[] intArray = {1,4,65,23,32,6,7,8,29};
        selectionSort(intArray);
    }

    public static void selectionSort(int[] intArray) {
        for (int i = 0; i < intArray.length - 1; i++) {
            int minIndex = i;
            // find the smallest remaining item
            for (int j = i + 1; j < intArray.length; j++) {
                if (intArray[j] < intArray[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = intArray[i]; // swap into place
            intArray[i] = intArray[minIndex];
            intArray[minIndex] = temp;

            for (int n : intArray){
                System.out.print(n + ", ");
            }
            System.out.println("");
        }
    }
}
