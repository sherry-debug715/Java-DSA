package Array;
// lintcode 1451 
// Time: O(n)
// Space: O(1)
public class MaximizeDistanceToClosestPerson {
    public int maxDistToClosest(int[] seats) {
        int leftOne = -1, rightOne = 0;
        int ans = 0;
        int n = seats.length;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                leftOne = i;
            } else {
                while (rightOne < n && seats[rightOne] == 0 || rightOne < i) {
                    rightOne += 1;
                } 
                int left = leftOne != -1 ? i - leftOne : n; // distance of closest person to i on the left 
                int right = rightOne != n ? rightOne - i : n; // distance of closest person to i on the right 
                ans = Math.max(ans, Math.min(left, right));
            }
        }
        return ans;
    }
}
