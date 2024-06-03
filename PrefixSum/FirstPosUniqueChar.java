package PrefixSum;
// Lintcode 646: https://www.lintcode.com/problem/646/?fromId=178&_from=collection
// Time: O(N)
// Space: O(1)
public class FirstPosUniqueChar {
    public int firstUniqChar(String s) {
        int[] charCounter = new int[256]; // 256 characters 
        // store number of occurance of each char in charCounter 
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i);
            charCounter[idx] += 1;
        }

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i);
            if (charCounter[idx] == 1) {
                return i;
            }
        }
        return -1;
    }
}
