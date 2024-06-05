package BinarySearch;
// Lintcode 437
// Time: O(NlogSum)
// Space: O(1)
public class CopyBooks {
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0) {
            return 0;
        }
        int start = pages[0], end = 0;
        for (int p : pages) { // O(N)
            start = Math.max(p, start);
            end += p;
        }

        while (start + 1 < end) { // O(Nlog(Sum))
            int mid = start + (end - start) / 2;
            if (peopleCount(pages, mid) <= k) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (peopleCount(pages, start) <= k) {
            return start;
        }
        if (peopleCount(pages, end) <= k) {
            return end;
        }
        return 0;
    }

    private int peopleCount(int[] pages, int timeLimit) {
        int peopleNeeded = 1;
        int timeSpend = 0;
        for (int p : pages) {
            if (timeSpend + p > timeLimit) {
                peopleNeeded += 1;
                timeSpend = 0;
            }
            timeSpend += p;
        }
        return peopleNeeded;
    }
}
