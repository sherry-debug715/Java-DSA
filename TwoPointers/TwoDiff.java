package TwoPointers;

public class TwoDiff {
    public int[] twoSum7(int[] nums, int target) {
        int[] output = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return output;
        }
        int left = 0;
        int right = left + 1;
        target = Math.abs(target);
        while (right < nums.length) { // i -> left pointer 
            right = Math.max(left + 1, right);
            int diff = nums[right] - nums[left];
            if (diff == target) {
                output[0] = nums[left];
                output[1] = nums[right];
                break;
            }
            if (diff < target) {
                right += 1;
            } else {
                left += 1;
                while (left < nums.length - 1 && nums[left] == nums[left - 1]) {
                    left += 1;
                }
            }
        }
        return output;
    }
}
