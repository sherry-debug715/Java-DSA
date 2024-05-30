package TwoPointers;
// Lintcode 1045
// Time: O(N)
// Space: O(1) because the space is max 26 
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> output = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return output;
        }
        // charIdx stores the farest index of each char of s 
        int[] charIdx = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charIdx[s.charAt(i) - 'a'] = i;
        }
        int left = 0, right = 0;

        for (int i = 0; i < s.length(); i++) {
            right = Math.max(right, charIdx[s.charAt(i) - 'a']);
            if (i == right) {
                output.add(right - left + 1);
                left = right + 1;
            }
        }
        return output; 
    }
}
