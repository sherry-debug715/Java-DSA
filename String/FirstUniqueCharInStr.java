package String;
// lintcode 209: https://www.lintcode.com/problem/209/?fromId=161&_from=collection
// Time: O(n);
// Space: O(N);
public class FirstUniqueCharInStr {
    public char firstUniqChar(String str) {
        if (str == null || str.length() == 0) {
            return '\0';
        }

        Deque<Character> deque = new ArrayDeque<>();
        Map<Character, Boolean> charMap = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (!charMap.containsKey(cur)) {
                charMap.put(cur, false);
                deque.addLast(cur);
            } else {
                charMap.put(cur, true);
                while (!deque.isEmpty() && charMap.get(deque.peekFirst())) {
                    deque.pollFirst();
                }
            }
        }
        return deque.peekFirst();
    }
}
