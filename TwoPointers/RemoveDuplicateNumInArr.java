package TwoPointers;

import java.util.Arrays;

public class RemoveDuplicateNumInArr {
    public int deduplication(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int j = 1; // j - 1 is the last unique number 
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j += 1;
            }
        }
        return j;
    }
}
