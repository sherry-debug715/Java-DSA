package Sort;
// Lintcode problem 532: https://www.lintcode.com/problem/532/

// use merge sort 的先局部有序的思想来计算当右指针的值>左指针的时候，左右指针之间有多少个数字即是答案的一部分
// 因为升序归并排序中，右指针左边的数字群必然小于右边的数字群。
public class ReversePairs {
    public long reversePairs(int[] a) {
        // edge case 
        if (a == null) {
            return 0;
        }

        return mergeSort(0, a.length - 1, a);
    }

    private int mergeSort(int start,
                      int end,
                      int[] a) {
        if (start >= end) {
            return 0;
        }
        int sum = 0;
        int mid = start + (end - start) / 2;
        // divide 
        sum += mergeSort(start, mid, a);
        sum += mergeSort(mid + 1, end, a);
        // conquer 
        sum += merge(start, mid, end, a);

        return sum;
    }

    private int merge(int start, int mid, int end, int[] a) {
        int[] temp = new int[a.length];
        int left = start;
        int index = start;
        int right = mid + 1;
        int sum = 0;

        while (left <= mid && right <= end) {
            if (a[left] <= a[right]) {
                temp[index++] = a[left++];
            } else {
                temp[index++] = a[right++];
                sum += mid - left + 1;
            } 
        }

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
        return sum;
    }
}
