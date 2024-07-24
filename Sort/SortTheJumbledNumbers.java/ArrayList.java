package Sort.SortTheJumbledNumbers.java;
// Time: O(nlogn)
// Space: O(n)
public class ArrayList {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        List<int[]> sortedNums = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) { // O(nlogn)
            int converted = convertNum(nums[i], mapping);
            sortedNums.add(new int[] {nums[i], converted, i});
        }
        Collections.sort(sortedNums, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[2] - b[2];
            }
        });

        int[] res = new int[nums.length];
        int idx = 0;
        for (int[] curMin : sortedNums) { // O(n)
            res[idx++] = curMin[0];
        }
        return res;
    }

    private int convertNum(int n, int[] mapping) {
        int res = 0;
        String numStr = Integer.toString(n);
        for (char c : numStr.toCharArray()) {
            res = (res * 10) + mapping[c - '0'];
        }
        return res;
    }
}
