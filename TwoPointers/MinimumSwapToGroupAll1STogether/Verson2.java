package TwoPointers.MinimumSwapToGroupAll1STogether;
// leetcode 2134
// Time: O(n)
// Space: O(1)
public class Verson2 {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        // Count total number of 1's
        int cntOne = 0;
        for (int num : nums) {
            cntOne += num;
        }
        // Edge cases
        if (cntOne == 0 || cntOne == n) return 0;
        // cnt the number of 1s from the first window 
        int curOne = 0;
        for (int i = 0; i < cntOne; i++) {
            curOne += nums[i];
        }
        int maxOne = curOne;
        for (int i = 0; i < n; i++) {
            curOne -= nums[i];
            curOne += nums[(i + cntOne) % n];
            maxOne = Math.max(curOne, maxOne);
        }
        return cntOne - maxOne;
    }
}
