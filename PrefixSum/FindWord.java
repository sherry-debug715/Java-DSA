package PrefixSum;
// Lintcode 194: https://www.lintcode.com/problem/194/?fromId=178&_from=collection
public class FindWord {
    public class Solution {
        /**
         * @param str: the string
         * @param dict: the dictionary
         * @return: return words which  are subsequences of the string
         */
        public List<String> findWords(String str, List<String> dict) {
            List<String> output = new ArrayList<>();
            if (str == null || str.length() == 0 || dict.size() == 0) {
                return output;
            }
            // map key: each char from str, value: collections of indexes of the char.
            Map<Character, ArrayList<Integer>> charIdxMap = new HashMap<>();
    
            for (int i = 0; i < str.length(); i++) {
                char curChar = str.charAt(i);
                charIdxMap.putIfAbsent(curChar, new ArrayList<Integer>());
                charIdxMap.get(curChar).add(i);
            }
    
            // System.out.println("charIdxMap---->" + charIdxMap);
            
            for (String word : dict) {
                if (isSubsequence(word, charIdxMap, str.length())) {
                    output.add(word);
                }
            }
            return output;
        }
    
        private boolean isSubsequence(String word, 
                                      Map<Character, ArrayList<Integer>> charIdxMap,
                                      int strSize) {
            int nextIdx = -1, j = 0;
            while (nextIdx < strSize && j < word.length()) {
                nextIdx = findNextIdx(nextIdx, word.charAt(j), charIdxMap);
                if (nextIdx == -1) {
                    return false;
                }
                j += 1;
            }
            return j == word.length();
        }
    
        private int findNextIdx(int index, char key, Map<Character, ArrayList<Integer>> charIdxMap) {
            if (!charIdxMap.containsKey(key)) {
                return -1;
            }
            List<Integer> idxList = charIdxMap.get(key);
            // binary search for the first idx > index 
            int left = 0, right = idxList.size() - 1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (idxList.get(mid) < index) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            if (idxList.get(left) > index) {
                return idxList.get(left);
            }
            if (idxList.get(right) > index) {
                return idxList.get(right);
            }
            if (idxList.get(right) == index) {
                if (right == idxList.size() - 1) { // no more char on the right 
                    return -1;
                }
                return idxList.get(right + 1);
            }
            return -1;
        }
    }
    
    // str= "bcogtadsjofisdhklasdj"
    //        i. 
    // dict=["book","code","tag"]
    //  
    // i = 17  
    // soda
    //      j
    
    //  l
    // [6, 13, 19]
    //      m
    //      r
    // {
    //     a=[5, 17], b=[0], c=[1], d=[6, 13, 19], 
    //     f=[10], g=[3], h=[14], i=[11], j=[8, 20], k=[15], l=[16], o=[2, 9], s=[7, 12, 18], t=[4]
    // }
    
    
}
