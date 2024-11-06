package String.Parantheses;
// Leetcode 1249
public class MinRemoveToMakeValidParentheses {
    // Time: O(n + m) n: s.length(), m: res.length()
    // Space: O(n + m) n to convert s to array, m: res String 
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        int open = 0;
        for (char c : s.toCharArray()) { // O(n) n: s.length()
            if (Character.isLetter(c)) { // O(1)
                res.append(c);
            } else { // O(1)
                if (c == '(') {
                    open += 1;
                    res.append(c);
                } else if (c == ')') {
                    if (open <= 0) {
                        continue;
                    } else {
                        open -= 1;
                        res.append(c);
                    }
                }
            }
        }

        for (int i = res.length() - 1; i >= 0 && open > 0; i--) { // O(m) m: res.length()
            if (res.charAt(i) == '(') {
                res.deleteCharAt(i);
                open -= 1;
            }
        }

        return new String(res);
    }
}
