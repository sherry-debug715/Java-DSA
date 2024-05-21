package TwoPointers;

public class MergeSortedArrEasy {
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        if (B == null || A == null) {
            return;
        }
        int aTail = m - 1; // last number in A
        int bTail = n - 1; // last number in B 
        int idx = A.length - 1;
        while (bTail >= 0 && aTail >= 0) {
            if (A[aTail] > B[bTail]) {
                A[idx] = A[aTail];
                aTail -= 1;
            } else {
                A[idx] = B[bTail];
                bTail -= 1;
            }
            idx -= 1;
        } 
        while (bTail >= 0 && idx >= 0) {
            A[idx] = B[bTail];
            bTail -= 1;
            idx -= 1;
        }
    }
}
