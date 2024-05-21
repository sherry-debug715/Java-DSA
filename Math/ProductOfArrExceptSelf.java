package Math;
// Leetcode prblem 238: https://leetcode.com/problems/product-of-array-except-self/

public class ProductOfArrExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefixProduct = new int[n];
        int[] suffixProduct = new int[n];
        prefixProduct[0] = 1;
        suffixProduct[n - 1] = 1;
        // prefixProduct is the product of subarray nums[:i]
        for (int i = 1; i < n; i++) {
            prefixProduct[i] = prefixProduct[i - 1] * nums[i - 1];
        }
        
        // suffixProduct is the product of subarray nums[i + 1:]
        for (int i = n - 2; i >= 0; i--) {
            suffixProduct[i] = suffixProduct[i + 1] * nums[i + 1];
        }
        
        int[] output = new int[n];
        for (int i = 0; i < n; i++) {
            output[i] = prefixProduct[i] * suffixProduct[i];
        }
        
        return output;
    }
}
