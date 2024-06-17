package Sort;

import java.util.Arrays;
import java.util.Comparator;
// leetcode 937 
// Time: (mnlogn), m: length of logs. n: length of longest log.
// Space: (mnlogn)

public class ReorderDataInLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> compareLogs = new Comparator<String>() {
            public int compare(String log1, String log2) {
                // Split each log into an array with length of 2, to separate identifier and  remaining 
                String[] log1Arr = log1.split(" ", 2);
                String[] log2Arr = log2.split(" ", 2);
                // check digits or letter 
                boolean log1Digits = Character.isDigit(log1Arr[1].charAt(0));
                boolean log2Digits = Character.isDigit(log2Arr[1].charAt(0));
                if (!log1Digits && !log2Digits) { // both letters 
                    int content = log1Arr[1].compareTo(log2Arr[1]);
                    if (content != 0) {
                        return content;
                    }
                    // sort by identifier 
                    return log1Arr[0].compareTo(log2Arr[0]);
                } else if (log1Digits && log2Digits) { // both digits 
                    return 0;
                } else { // letter first 
                    if (!log1Digits) {
                        return -1;
                    }
                    return 1;
                }
            }
        };
        Arrays.sort(logs, compareLogs);
        return logs;
    }
}
