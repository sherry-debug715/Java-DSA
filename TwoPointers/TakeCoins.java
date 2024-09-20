package TwoPointers;
// Lintcode 1399 
public class TakeCoins {
        // Time: O(k)
    // Space: O(1)
    public int takeCoins(int[] list, int k) {
        // one of the extreme situation is all k coins are taken from left 
        int leftSum = 0;
        int left = 0;
        while (left < k) {
            leftSum += list[left++];
        }
        // decrement left by one so left point at the kth number from list 
        left -= 1;
        int rightSum = 0;
        int res = leftSum;
        for (int right = list.length - 1; right >= 0 && left >= 0; right --) {
            // take a coin from right 
            rightSum += list[right];
            // remove a coin from leftSum 
            leftSum -= list[left --];
            res = Math.max(res, leftSum + rightSum);
        }
        return res;

    }
}
