package DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Lintcode problem 425: https://www.lintcode.com/problem/425/?fromId=161&_from=collection
public class LetterCombOfPhoneNumber {
    private Map<Character, char[]> createPhoneNum() {
        Map<Character, char[]> phoneNum = new HashMap<>();
        phoneNum.put('2', new char[] {'a', 'b', 'c'});
        phoneNum.put('3', new char[] {'d', 'e', 'f'});
        phoneNum.put('4', new char[] {'g', 'h', 'i'});
        phoneNum.put('5', new char[] {'j', 'k', 'l'});
        phoneNum.put('6', new char[] {'m', 'n', 'o'});
        phoneNum.put('7', new char[] {'p', 'q', 'r', 's'});
        phoneNum.put('8', new char[] {'t', 'u', 'v'});
        phoneNum.put('9', new char[] {'w', 'x', 'y', 'z'});
        return phoneNum;
    }

    public List<String> letterCombinations(String digits) {
        List<String> output = new ArrayList<>();
        // edge case 
        if (digits == null || digits.length() == 0) {
            return output;
        }

        Map<Character, char[]> phoneNum = createPhoneNum();
        dfs(digits, phoneNum, new StringBuilder(), 0, output);
        return output;
    }

    private void dfs(String digits,
                      Map<Character, char[]> phoneNum,
                      StringBuilder curComb,
                      int startIdx,
                      List<String> output) {
        if (curComb.length() == digits.length()) {
            output.add(curComb.toString());
            return;
        }

        if (startIdx > digits.length()) {
            return;
        }

        for (char c : phoneNum.get(digits.charAt(startIdx))) {
            curComb.append(c);
            dfs(digits, phoneNum, curComb, startIdx + 1, output);
            curComb.deleteCharAt(curComb.length() - 1);
        }
    }
}
