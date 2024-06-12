package TwoPointers;
// Lintcode problem 3552: https://www.lintcode.com/course/101/learn/3552?chapterId=535&sectionId=4314&ac=true
// Time: O(N)
// Space: O(N)
public class InterestingTriples {
    public boolean getFunTuple(int[] nums) {
        int n = nums.length;
        boolean[] rightGreater = new boolean[n];
        // greaterStack is used to find nums[i] with numbers greater than itself on the right side 
        Stack<Integer> greaterStack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!greaterStack.isEmpty() && nums[i] > nums[greaterStack.peek()]) {
                rightGreater[greaterStack.pop()] = true;
            }
            greaterStack.push(i);
        }
        boolean[] leftLess = new boolean[n];
        // LessStack is used to find nums[i] with numbers less than itself on the left side 
        Stack<Integer> lessStack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!lessStack.isEmpty() && nums[i] <= nums[lessStack.peek()]) {
                lessStack.pop();
            }
            lessStack.push(i);
            if (lessStack.size() != 1) {
                leftLess[i] = true;
            }
        }

        // iterate through nums, for each element, see if left is less is true and 
        // right is greater is true 
        for (int i = 0; i < n; i++) {
            if (leftLess[i] && rightGreater[i]) {
                return true;
            }
        }
        return false;
    }
}

// Solution 2 
class Solution {
    /**
     * @param nums: 
     * @return: Returns the existence of an interesting triplet
     */
    public boolean getFunTuple(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        for (int n : nums) {
            while(!stack.isEmpty() && n <= stack.peek()) {
                stack.pop();
            }
            stack.push(n);
            if (stack.size() >= 3) {
                return true;
            }
        }
        return false;
    }
}