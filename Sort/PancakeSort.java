package Sort;
// leetcode problem 969: https://leetcode.com/problems/pancake-sorting/
public class PancakeSort {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> output = new ArrayList<>();
        
        for (int i = arr.length - 1; i > 0; i--) {
            int maxIdx = findMaxIdx(i, arr);
            // max num is already sorted at the right pos 
            if (maxIdx == i) {
                continue;
            }
            // flip the max number to the begining 
            if (maxIdx != 0) {
                output.add(maxIdx + 1);
                flip(0, maxIdx, arr);
            }
            // flip the maxnum to the end 
            output.add(i + 1);
            flip(0, i, arr);
        }
        return output;
    }
    
    private void flip(int idx1, int idx2, int[] arr) {
        while (idx1 < idx2) {
            int temp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = temp;
            idx1 += 1;
            idx2 -= 1;
        }
    }
    
    private int findMaxIdx(int endIdx, int[] arr) {
        int max = Integer.MIN_VALUE;
        int maxIdx = arr.length;
        for (int i = 0; i < endIdx + 1; i++) {
            if (arr[i] > max) {
                maxIdx = i;
                max = arr[i];
            }
        }
        return maxIdx;
    }
}
