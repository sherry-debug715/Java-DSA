```java
public class Solution {
    
    char[][] patterns = { {'(', ')'}, {')', '('} };
    
    public List<String> removeInvalidParentheses(String s) {
        List<String> ret = new ArrayList<>();
       
        dfs(s, 0, 0, patterns[0], ret);
        return ret;
    }
    
    private void dfs(String s, int start, int lastRemove, char[] pattern, List<String> ret) {
        int count = 0, n = s.length();
        for (int i = start; i < n; i ++) {
            if (s.charAt(i) == pattern[0]) 
            { 
                count ++;
            }
            if (s.charAt(i) == pattern[1]) 
            { 
                count --;
            }
            if (count < 0) 
            {
                for (int j = lastRemove; j <= i; j ++) 
                {
                    if (s.charAt(j) == pattern[1] && (j == lastRemove || s.charAt(j) != s.charAt(j - 1))) 
                    {
                        dfs(s.substring(0, j) + s.substring(j + 1), i, j, pattern, ret);
                    }
                }
                return;
            }
        }


        s = new StringBuilder(s).reverse().toString();
        if (pattern[0] == patterns[0][0]) 
        {
            dfs(s, 0, 0, patterns[1], ret);
        } 
        else 
        { 
            ret.add(s); 
        }
    }
}
```