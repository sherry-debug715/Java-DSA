package TwoPointers;
// Lintcode 438
// Time: O(kLOGN) K: total number of people, N total number of books 
// Space: O(1)
public class CopyBooks2 {
        // if each person only has mid amount of time, how many books can the entire group finish
//in mid amount of time.

public int copyBooksII(int n, int[] times) {
    int start = times[0], end = times[0];
    for (int t : times) {
        start = Math.min(start, t);
        end = Math.max(end, t);
    }
    end *= n; // the slowest person needs end *= n time to finish copy all the books

    while (start + 1 < end) {
        int timeLimit = start + (end - start) / 2;
        if (canBeDone(timeLimit, times, n)) {
            end = timeLimit;
        } else {
            start = timeLimit;
        }
    }
    // System.out.println(start + "---> end" + end);
    if (canBeDone(start, times, n)) {
        return start;
    }
    if (canBeDone(end, times, n)) {
        return end;
    }
    return -1;
}

private boolean canBeDone(int timeLimit, int[] times, int books) {
    // if all books can be copied in timeLimit;
    // System.out.println("timeLimit==============>"+timeLimit);

    int canFinish = 0;
    for (int t : times) {
        canFinish += timeLimit / t;
        if (canFinish >= books) {
            return true;
        }
    }
    return canFinish >= books;
}
}
