package TwoPointers;
// Time: O(N)
// Space: O(1)
public class SubarraySum2 {
    public int subarraySumII(int[] a, int start, int end) {
        // edge case 
        if (a == null || a.length == 0) {
            return 0;
        }
        // left pointer stops when leftSum >= start 
        int left = 0, leftSum = 0;
        // right pointer stops when rightSum > end
        int right = 0, rightSum = 0;
        int output = 0;

        for (int i = 0; i < a.length; i++) {
            // locate the left pointer 
            left = Math.max(left, i);
            // locate right pointer 
            right = Math.max(right, i);
            while (left < a.length && leftSum + a[left] < start) {
                leftSum += a[left];
                left += 1;
            }
            while (right < a.length && rightSum + a[right] <= end) {
                rightSum += a[right];
                right += 1;
            }
            // 这里计算的是[i,left]这一段有多少个子数组 + [left, right]这一段有多少个子数组。比如说，
            //      L     R
            // a = [1, 2, 3, 4]
            // right - left 计算的是[1], [1, 2]而不是[1], [2], [1,2], [2]是当i = 1时计算的。
            if (left < right) {
                output += right - left;
            }
            // back tracking 
            if (left > i) {
                leftSum -= a[i];
            }
            if (right > i) {
                rightSum -= a[i];
            }
        }
        
        return output;
    }
}
// Example 1:
// i
// l
// [1, 2, 3, 4]  start = 1, end = 3
//   r
// output = 4 [1],[1,2,3,4],[1,2,3],[1,2,4]
// output = 4 + 3 [2],[2,3,4],[2,4]
// output = 7 + 2 [3], [3,4]
// output = 9 + 1 [4]

// Example 2:
// i
// l
// [1, 2, 3, 4] start = 2, end = 3
//    r
// output = 1, [1,2]
// output = 1 + 1, [2]
// output = 2 + 1, [3]
// leftsum = 0
// rightSum = 3
