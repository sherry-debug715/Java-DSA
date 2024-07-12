package HashMap.ArrayQuadruplet;

import java.util.Arrays;

// exponent: https://www.tryexponent.com/practice/prepare/array-quadruplet
// Time: O(n^3)
// Space: O(1)
public class TwoPointer {
      static int[] findArrayQuadruplet(int[] arr, int s) {
    int n = arr.length;
    if (n < 4) {
      return new int[0];
    }

    Arrays.sort(arr);
    for (int i = 0; i < n - 3; i++) {
      for (int j = i + 1; j < n - 2; j++) {
        int twoSum = arr[i] + arr[j];
        int[] other = findTwoSum(arr, j + 1, n - 1, s - twoSum);
        if (other != null) {
          int[] result = {arr[i], arr[j], other[0], other[1]};
          Arrays.sort(result);
          return result;
        }
      }
    }
    return new int[0];
  }

  static int[] findTwoSum(int[] arr, int left, int right, int target) {
    while (left < right) {
      int twoSum = arr[left] + arr[right];
      if (twoSum < target) {
        left += 1;
      } else if (twoSum > target) {
        right -= 1;
      } else {
        return new int[]{arr[left], arr[right]};
      }
    }
    return null;
  }
}
