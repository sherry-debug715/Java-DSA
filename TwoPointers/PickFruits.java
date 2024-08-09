package TwoPointers;
// lintcode 1643

import java.util.Map;

public class PickFruits {
    public int pickFruits(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int maxFruit = 0;
        Map<Integer, Integer> fruitMap = new HashMap<>();
        int right = 0, unique = 0;
        for (int left = 0; left < arr.length; left ++) {
            while (right < arr.length && unique <= 2) {
                int fruit = arr[right];
                fruitMap.put(fruit, fruitMap.getOrDefault(fruit, 0) + 1);
                if (fruitMap.get(fruit) == 1) {
                    unique += 1;
                }
                if (unique <= 2) {
                    // [left, right] 
                    maxFruit = Math.max(maxFruit, right - left + 1);
                }
                right += 1; 
            }

            fruitMap.put(arr[left], fruitMap.get(arr[left]) - 1);
            if (fruitMap.get(arr[left]) == 0) {
                unique -= 1;
            }
        }
    }
}
