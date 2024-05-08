package Stack.MonotonicStack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 元素入栈时，新元素左边第一个>=它自己的元素是旧的栈顶元素
// 元素出栈时，出栈元素右边第一个>它自己的元素是新元素。
public class ExamProgress {
    public int[] getProgress(int[] nums) {
        int[] output = Arrays.copyOf(nums, nums.length);
        if (nums.length == 0) {
            return output;
        }
        // stack is the monotonic stack 
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int curNum = nums[i];
            // stack is empty or curNum < the last number in stack, add curNum to stack
            // stack.peek() is the first number on the left >= curNum
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            // if curNum > last number from stack, curNum is the largest number on the right size
            // for all the number stored in stack  
            while(!stack.isEmpty() && nums[stack.peek()] < curNum) {
                output[stack.pop()] = curNum;
            }
            stack.push(i);
        }
        return output;
    }
}
