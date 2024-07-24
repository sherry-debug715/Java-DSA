package Sort.SortTheJumbledNumbers.java;
// leetcode 2191 
// Time: O(nlogn)
// Space: O(N)
public class PriorityQueue {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    if (a[1] != b[1]) {
                        return a[1] - b[1];
                    } else {
                        return a[2] - b[2];
                    }
                }
            }
        );

        for (int i = 0; i < nums.length; i++) { // O(nlogn)
            int converted = convertNum(nums[i], mapping);
            minHeap.offer(new int[] {nums[i], converted, i});
        }

        int[] res = new int[nums.length];
        int idx = 0;
        while (!minHeap.isEmpty()) { // O(nlogn)
            int[] curMin = minHeap.poll();
            res[idx++] = curMin[0];
        }
        return res;
    }

    private int convertNum(int n, int[] mapping) {
        int res = 0;
        String numStr = Integer.toString(n);
        for (char c : numStr.toCharArray()) {
            res = (res * 10) + mapping[c - '0'];
        }
        return res;
    }
}
