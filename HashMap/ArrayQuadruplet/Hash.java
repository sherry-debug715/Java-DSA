package HashMap.ArrayQuadruplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Hash {
    static int[] findArrayQuadruplet(int[] arr, int s) {
    int n = arr.length;
    if (n < 4) {
      return new int[0];
    }
    Arrays.sort(arr);
    Map<Integer, List<int[]>> sumMap = new HashMap<>();

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j ++) {
        int twoSum = arr[i] + arr[j];
        sumMap.put(twoSum, new ArrayList<>());
        sumMap.get(twoSum).add(new int[]{i, j});
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int diff = s - (arr[i] + arr[j]);
        if (!sumMap.containsKey(diff)) {
          continue;
        }
        List<int[]> indexList = sumMap.get(diff);
        for (int[] pair : indexList) {
          if (i != pair[0] && i != pair[1] && j != pair[0] && j != pair[1]) {
            int[] res = new int[] {arr[pair[0]], arr[pair[1]], arr[i], arr[j]};
            Arrays.sort(res);
            return res;
          }
        }
      }
    }
    return new int[0];
  }
}
