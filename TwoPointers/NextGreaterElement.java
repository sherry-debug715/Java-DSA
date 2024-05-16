package TwoPointers;

public class NextGreaterElement {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int n = nums.length;
        int[] output = new int[n];
        Arrays.fill(output, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * n - 1; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peekLast()]) {
                output[stack.pollLast() % n] = nums[i % n];
            }
            stack.addLast(i % n);
        }

        return output;
    }
}
