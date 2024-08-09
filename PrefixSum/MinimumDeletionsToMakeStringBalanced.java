package PrefixSum;
// leetcode 1653 Two Solutions 
// Time: O(n) 38ms 
// Space: O(n) 46.38mb
public class MinimumDeletionsToMakeStringBalanced {
    public int minimumDeletions(String s) {
        char[] sArr = s.toCharArray();
        int n = sArr.length;
        //for every index the number of Bs before it
        int[] prefixBCount = new int[n + 1]; 
        //for every index the number of As after it
        int[] backfixACount = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            if (sArr[i - 1] == 'b') {
                prefixBCount[i] += 1;
            }
            prefixBCount[i] += prefixBCount[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            if (sArr[i] == 'a') {
                backfixACount[i] += 1;
            }
            backfixACount[i] += backfixACount[i + 1];
        } 

        int res = n;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, prefixBCount[i] + backfixACount[i + 1]);
        }
        return res;
    }
}
// Time: O(n) 34ms
// Space: O(1) 45.86mb
class Solution {
    public int minimumDeletions(String s) {
        char[] sArr = s.toCharArray();
        int n = sArr.length;
        int totalA = 0; 
        for (char c : sArr) {
            if (c == 'a') {
                totalA += 1;
            }
        }
        int res = totalA;
        int a = 0, b = 0;
        for (char c : sArr) {
            if (c == 'a') {
                a += 1;
            } else if (c == 'b') {
                b += 1;
            }
            int curDeletion = b + (totalA - a);
            res = Math.min(res, curDeletion);
        }
        return res;
    }
}
