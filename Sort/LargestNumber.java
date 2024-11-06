package Sort;
// Leetcode 179 
// Time: O(nlogn)
// Space: O(n + m) where m is the total length of number strings from numsStr
import java.util.Arrays;

public class LargestNumber {
    public String largestNumber(int[] nums) {
        String[] numsStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsStr[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(numsStr, (a, b) -> {
            String str1 = a + b;
            String str2 = b + a;
            return str2.compareTo(str1);
        });
        // System.out.println(Arrays.toString(numsStr));
        String res = "";
        for (int i = 0; i < nums.length; i++) {
            res = res.concat(numsStr[i]);
        }
        if (res.charAt(0) == '0') {
            return "0";
        }
        return res;
    }
}
