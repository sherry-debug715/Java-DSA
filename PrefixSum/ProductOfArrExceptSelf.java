package PrefixSum;
// lintcode 1310: https://www.lintcode.com/problem/1310/?fromId=178&_from=collection
// Time: O(n)
// Space: O(1) except for the output array 
public class ProductOfArrExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        if (nums == null || nums.length == 0) {
            return ans;
        }
        ans[0] = 1;
        // populate ans with prefix product, except for the alst number 
        for (int i = 1; i < nums.length; i++) {
            ans[i] = nums[i - 1] * ans[i - 1];
        }

        int rightProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            ans[i] = ans[i] * rightProduct;
            rightProduct *= nums[i];
        }
        return ans;
    }
}

