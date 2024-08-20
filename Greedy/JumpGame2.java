package Greedy;
// Leetcode 45
// Time: O(N)
// Space: O(1)
public class JumpGame2 {
    public int jump(int[] nums) {
        if (nums[0] == 0 || nums.length == 1) {
            return 0;
        }
        int start = 0, end = 0, jump = 0, farthest = 0;
        while (farthest < nums.length - 1) {
            jump += 1;
            for (int i = start; i <= end; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }
            start = end + 1;
            end = farthest;
        }
        return jump;
    }
}
