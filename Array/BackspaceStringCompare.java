package Array;
// leetcode 844
// Time: O(n)
// Space: O(n)
public class BackspaceStringCompare {
    public boolean backspaceCompare(String s, String t) {
        return edit(s).equals(edit(t));
    }
    
    private String edit(String str) {
        int backSpaceCtn = 0;
        char[] arr = str.toCharArray();
        StringBuilder res = new StringBuilder();
        
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == '#') {
                backSpaceCtn += 1;
            } else {
                if (backSpaceCtn > 0) { // if prev char is '#', treat current char as deleted.
                    backSpaceCtn -= 1;
                } else {
                    res.append(arr[i]);
                }
            }
        }
        return res.toString();
    }
}
