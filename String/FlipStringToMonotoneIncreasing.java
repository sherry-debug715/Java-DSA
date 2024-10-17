package String;

public class FlipStringToMonotoneIncreasing {
        // Time: O(n)
        public int minFlipsMonoIncr(String s) {
            if (s.length() == 0) {
                return 0;
            }
            int n = s.length();
            int[] prefixOnes = new int[n];
    
            // count how many 1s occured in s[:i + 1]
            int totalOnes = 0;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '1') {
                    totalOnes += 1;
                }
                prefixOnes[i] = totalOnes;
            } 
            // iterate over s, for each i, calculate 
            // 1. number of 0s in s[i + 1:], flip them to 1s
            // 2. flip prefixOnes[i] to 0 if prefixOnes[i] is 1s 
            int flip = n - totalOnes; // most flip occurs when flipping every 0s to 1s 
    
            for (int i = 0; i < n; i++) {
                int rightOnes = totalOnes - prefixOnes[i];
                int rightZeros = n - rightOnes - i - 1; // - 1 to exclude s[i] 
                flip = Math.min(flip, rightZeros + prefixOnes[i]);
            }
            return flip;
        }
}
