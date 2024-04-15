package SweepLine;

public class TakeCoins {
    public int takeCoins(int[] list, int k) {
        int n = list.length;
        // preSum of 1st element is 0;
        int[] preSum = new int[n + 1];

        for (int i = 0; i < list.length; i++) {
            preSum[i + 1] = preSum[i] + list[i];
        }
        int ans = 0;
        for (int i = 0; i <= k; i++) {
            int left = i;
            int right = k - left;
            int cur = preSum[n] - preSum[n - right] + preSum[left];
            ans = Math.max(cur, ans);
        }

        return ans;
    }
}
