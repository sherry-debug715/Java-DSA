package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] numbers) {
        List<List<Integer>> res = new ArrayList<>();
        // edge case 
        if (numbers == null || numbers.length == 0) {
            return res;
        }

        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length - 2; i++) {
            // pruning 
            if (numbers[i] > 0) {
                break;
            }
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }

            int left = i + 1, right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];
                if (sum == 0) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(numbers[i]);
                    triplet.add(numbers[left]);
                    triplet.add(numbers[right]);
                    res.add(triplet);
                    left += 1;
                    while (left < right && numbers[left] == numbers[left - 1]) {
                        left += 1;
                    }
                } else if (sum > 0) {
                    right -= 1;
                } else {
                    left += 1;
                }
            }
        }
        return res;
    }
}
