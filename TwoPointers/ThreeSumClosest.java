package TwoPointers;

public class ThreeSumClosest {
    public int threeSumClosest(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        Arrays.sort(numbers);
        int minDiff = Integer.MAX_VALUE;
        int output = 0;

        for (int l = 0; l < numbers.length - 2; l++) { // l is left pointer 
            if (l > 0 && numbers[l] == numbers[l - 1]) { // pruning
                continue;
            }
            int mid = l + 1, right = numbers.length - 1;
            while (mid < right) {
                int curSum = numbers[l] + numbers[mid] + numbers[right];
                int curDiff = Math.abs(target - curSum);
                if (curDiff < minDiff) {
                    minDiff = curDiff;
                    output = curSum;
                }
                if (curSum == target) {
                    return curSum;
                }
                if (curSum < target) {
                    mid += 1;
                    while (mid < right && numbers[mid] == numbers[mid - 1]) { // pruning 
                        mid += 1;
                    }
                } else {
                    right -= 1;
                    while (mid < right && numbers[right] == numbers[right + 1]) {// pruning
                        right -= 1;
                    }
                }
            }
        }
        return output;
    }
}
