package BinarySearch;
// Lintcode 1492
// Time: O(logN) where N = right - left
// Space: O(1)
public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = h;
        for (int p : piles) {
            left = Math.min(left, p);
            right = Math.max(right, p);
        }
        while (left + 1 < right) {
            int hour = left + (right - left) / 2;
            int hoursFinish = calcuHoursFinish(hour, piles);
            // System.out.println("hoursFinish: " + hoursFinish);
            if (hoursFinish > h) {
                left = hour;
            } else {
                right = hour;
            }
        }
        if (calcuHoursFinish(left, piles) <= h) {
            return left;
        }
        if (calcuHoursFinish(right, piles) <= h) {
            return right;
        }
        return -1;
    }

    private int calcuHoursFinish(int h, int[] piles) {
        int totalHour = 0;
        for (int p : piles) {
            totalHour += Math.ceil((double) p / h);
        }
        return totalHour;
    }
}
