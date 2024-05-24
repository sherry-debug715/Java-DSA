package PriorityQueue;
// Lintcode 130: https://www.lintcode.com/problem/130/?fromId=161&_from=collection
// Time: O(N)
// Space: O(1)
public class Heapify {
    public void heapify(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }

        for (int i = a.length / 2; i >= 0; i--) {
            _heapify(i, a);  
        }
    }

    private void _heapify(int idx, int[] a) {
        while (idx * 2 + 1 < a.length) {
            int left = idx * 2 + 1;
            int right = idx * 2 + 2;
            if (right < a.length && a[left] > a[right]) {
                left = right;
            }
            if (a[left] > a[idx]) {
                break;
            }
            swap(a, idx, left);
            idx = left;
        }
    }

    private void swap(int[] a, int idx1, int idx2) {
        int temp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = temp;
    }
}
