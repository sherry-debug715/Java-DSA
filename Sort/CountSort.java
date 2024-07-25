package Sort;
// leetcode 912 
// Time Complexity: O(n + k), where n is the number of elements in the input array and k is the range of the input (i.e., max - min).
// Space Complexity: O(k) for the count array.
public class CountSort {
    public int[] sortArray(int[] nums) {
        int max = nums[0];
        int min = nums[0];

        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        int n = max - min;
        int[] arr = new int[n + 1];

        for (int num : nums) {
            arr[num - min]++;
        }

        int index = 0;

        for (int i = 0; i <= n; i++) {
            while (arr[i] > 0) {
                nums[index] = min;
                index++;
                arr[i]--;
            }

            min++;
        }

        return nums;
    }
}
