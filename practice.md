```java
public class Solution {
    /**
     * @param s: string s
     * @param minLength: min length for the substring
     * @param maxLength: max length for the substring
     * @param maxUnique: max unique letter allowed in the substring
     * @return: the max occurrences of substring
     */
    public int getMaxOccurrences(String s, int minLength, int maxLength, int maxUnique) {
        // write your code here
        int[] letterCount = new int[26];  /*count letter*/ 
        char[] strs = s.toCharArray();
        Map<String,Integer> stringCount = new HashMap<>();  /*count specific string satisfy requirement.*/
        int start = 0, end = start + minLength - 1, letterTotal = 0, ans = 0; /*letterTotal stores current total number of distinct letter. */
		/*deal with the first string with minLength. */
        for(int i = start; i <= end; i++){
            if(letterCount[strs[i] - 'a'] == 0) letterTotal++;
            letterCount[strs[i] - 'a']++;
        }
        if(letterTotal <= maxUnique){
            stringCount.put(s.substring(start, end + 1), 1);
            ans = 1;
        }
		
		/*slide in one letter, slide out one letter, and check if the substring in the sliding window satisfy the requirement. */
		
        end++;
        while(end < s.length()){
            if(letterCount[strs[end] - 'a']++ == 0) letterTotal++;
            if(letterCount[strs[start] - 'a']-- == 1) letterTotal--;
            start++;
            if(letterTotal <= maxUnique){
                String curStr = s.substring(start, end + 1);
                stringCount.put(curStr, stringCount.getOrDefault(curStr, 0) + 1);
                ans = Math.max(ans, stringCount.get(curStr));
            }
            end++;
        }
        return ans;
    }
}
```