package TwoPointers;
// Lintcode 692 
// Time: O(n)
// Space: O(n)
public class SlidingWindowUniqueElementsSum {
    public int slidingWindowUniqueElementsSum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0;
        int n = nums.length;
        if (k > n) {
            k = n;
        }
        // use a hashmap to store occurence of number 
        Map<Integer, Integer> counter = new HashMap<>();
        int left = 0, right = 0; 
        int curUnique = 0; // cur unique count in k window 

        while (left <= n - k) {
            while (right < n && right - left < k) {
                if (!counter.containsKey(nums[right]) || counter.get(nums[right]) == 0) {
                    curUnique += 1;
                } else if (counter.get(nums[right]) == 1) {
                    curUnique -= 1;
                }
                counter.put(nums[right], counter.getOrDefault(nums[right], 0) + 1);
                right += 1;
            }
            res += curUnique;
            // move left pointer 
            counter.put(nums[left], counter.get(nums[left]) - 1);
            int leftCount = counter.get(nums[left]);
            if (leftCount == 0) {
                curUnique -= 1;
            } else if (leftCount == 1) {
                curUnique += 1;
            }
            left += 1;
        }
        return res;
    }
}
