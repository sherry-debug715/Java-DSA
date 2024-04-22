package Sort;

public class QuickSort {
    public void sortIntegers2(int[] a) {
        if (a == null) {
            return;
        }

        quickSort(0, a.length - 1, a);
    }

    private void quickSort(int start, int end, int[] a) {
        if (start >= end) return;

        int pivot = a[start + (end - start) / 2];
        // create reference for traversal, without changing start and end references 
        int left = start;
        int right = end;
        // sort by value of pivot 
        while (left <= right) {
            while (left <= right && a[left] < pivot) {
                left += 1;
            }
            while (left <= right && a[right] > pivot) {
                right -= 1;
            }

            // swap left and right
            if (left <= right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                left += 1;
                right -= 1; 
            }
        }

        // keep sorting 
        quickSort(start, right, a);
        quickSort(left, end, a);
    }
}
