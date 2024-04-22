package Sort;

public class SortColors {
    public void sortColors(int[] nums) {
        // edge case
        if (nums.length == 0 || nums == null) {
            return;
        }

        // left pointer always reference 0, mid references 1, right references 2
        int left = 0, mid = left;
        int right = nums.length -1;
        
        while (mid <= right) {
            if (nums[mid] == 0) {
                swap(left, mid, nums);
                left += 1;
                mid += 1;
            } else if (nums[mid] == 2) {
                swap(mid, right, nums);
                right -= 1;
            } else {
                mid += 1;
            }
        }

    }

    private void swap(int idx1, int idx2, int[] nums) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}
