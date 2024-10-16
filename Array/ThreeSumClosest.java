package Array;

import java.util.Arrays;

// Lintcode 59
// Time: O(N^2)
// Space: O(1)
public class ThreeSumClosest {
        public int threeSumClosest(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        // sort numbers to handle duplicates 
        Arrays.sort(numbers);
        int diff = Integer.MAX_VALUE, sum = 0;
        for (int l = 0; l < numbers.length - 2; l++) {
            if (l > 0 && numbers[l] == numbers[l - 1]) {
                continue;
            }
            int mid = l + 1, right = numbers.length - 1;
            while (mid < right) {
                int curSum = numbers[l] + numbers[mid] + numbers[right];
                int curDiff = Math.abs(target - curSum);
                if (curDiff < diff) {
                    diff = curDiff;
                    sum = curSum; 
                }
                if (curDiff == 0) {
                    return target;
                }
                if (curSum < target) {
                    mid += 1;
                    while (numbers[mid] == numbers[mid - 1]) {
                        mid += 1;
                    }
                } else {
                    right -= 1;
                    while (numbers[right] == numbers[right + 1]) {
                        right -= 1;
                    }
                }
            }
        }
        return sum;
    }
}
