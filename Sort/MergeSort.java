package Sort;
// Time: O(NlogN)
// Space: O(NlogN), N space in heap and logN space in stack.
public class MergeSort {
    public void sortIntegers2(int[] a) {
        // edge case 
        if (a == null) {
            return;
        }

        mergeSort(0, a.length - 1, a);
    }

    private void mergeSort(int start, int end, int[] a) {
        if (start >= end) {
            return;
        }
        // divide
        int mid = start + (end - start) / 2;
        mergeSort(start, mid, a);
        mergeSort(mid + 1, end, a);
        // conquer 
        merge(start, end, a);
    }

    private void merge(int start, int end, int[] a) {
        int[] temp = new int[a.length];
        int mid = start + (end - start) / 2;
        int left = start;
        int index = start;
        int right = mid + 1;

        while (left <= mid && right <= end) {
            if (a[left] <= a[right]) {
                temp[index++] = a[left++];
            } else {
                temp[index++] = a[right++];
            }
        }

        // insert unadded numbers 
        while (left <= mid) {
            temp[index++] = a[left++];
        } 

        while (right <= end) {
            temp[index++] = a[right++];
        }

        // mutate the input array 
        for (int i = start; i <= end; i++) {
            a[i] = temp[i];
        }
    }
}
