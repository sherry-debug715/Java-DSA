package PriorityQueue;

import java.util.Comparator;

// lintcode 931: https://www.lintcode.com/problem/931/description?fromId=161&_from=collection
public class MedianOfKSortedArrays {
    class Pos {
        int val;
        int row;
        int col;
        public Pos(int _val, int _row, int _col) {
            val = _val;
            row = _row;
            col = _col;
        }
    }

    private Comparator<Pos> valComparator = new Comparator<Pos>() {
        public int compare(Pos p1, Pos p2) {
            return p1.val - p2.val;
        }
    };

    public double findMedian(int[][] nums) {
        if (nums == null || nums.length == 0 || nums[0].length == 0 && nums.length == 1) {
            return 0.0;
        }
        // count the total numbers stored in nums 
        int total = 0;
        // add the first col to a min heap 
        Queue<Pos> minHeap = new PriorityQueue<>(valComparator);
        for (int r = 0; r < nums.length; r++) {
            total += nums[r].length;
            if (nums[r].length == 0) {
                continue;
            }
            minHeap.add(new Pos(nums[r][0], r, 0));
        }

        boolean isEven = total % 2 == 0; 
        long L = 0, R = 0;
        int half = total / 2;
        for (int i = 0; i < half + 1; i++) {
            L = R;
            Pos curPos = minHeap.remove();
            R = nums[curPos.row][curPos.col];
            // add next pos 
            int newCol = curPos.col + 1;
            if (newCol < nums[curPos.row].length) {
                minHeap.add(new Pos(nums[curPos.row][newCol], curPos.row, newCol));
            }
        }
        if (isEven) {
            return (R + L) / 2.0;
        }
        return R;

    }
}
