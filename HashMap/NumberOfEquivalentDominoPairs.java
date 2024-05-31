package HashMap;
// leetcode 1128
// Time: O(N)
// Space: O(N)
public class NumberOfEquivalentDominoPairs {
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<String, Integer> encodedStrMap = new HashMap<>();
        int counter = 0;
        for (int[] d : dominoes) {
            int minVal = Math.min(d[0], d[1]);
            int maxVal = Math.max(d[0], d[1]);
            String key = minVal + " " + maxVal;
            if (!encodedStrMap.containsKey(key)) {
                encodedStrMap.put(key, 1);
            } else {
                // the current domino could pair with previously found dominoes with the same key 
                counter += encodedStrMap.get(key);
                encodedStrMap.put(key, encodedStrMap.get(key) + 1);
            }
        }
        // end for 
        return counter;
    }
}
