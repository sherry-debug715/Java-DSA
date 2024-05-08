package Stack.NextGreaterElement;

import java.util.Map;
import java.util.Stack;
// Time: O(N)
// Space: O(N)
public class PartOne {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreaterMap = new HashMapVersion<>();
        Stack<Integer> stack = new Stack<>();
        int[] output = new int[nums1.length];
        // construct nextGreaterMap 
        constructMap(nums2, nextGreaterMap, stack);

        for (int i = 0; i < nums1.length; i++) {
            output[i] = nextGreaterMap.get(nums1[i]);
        }
        return output;
    }

    private void constructMap(int[] nums,
                              Map<Integer, Integer> nextGreaterMap,
                              Stack<Integer> stack) {
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] >= stack.peek()) {
                stack.pop();
            }
            nextGreaterMap.put(nums[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums[i]);
        }
    }
}
