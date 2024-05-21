package BFS;
// Lintcode problem 624: https://www.lintcode.com/problem/624/?fromId=161&_from=collection
public class RemoveSubstrings {
    public int minLength(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        visited.add(s);
        int minLen = s.length();

        while (!queue.isEmpty()) {
            String curStr = queue.poll();
            // find index of each substr from curStr 
            for (String str : dict) {
                int pos = curStr.indexOf(str);
                while (pos != -1) {
                    if (curStr.equals(str)) {
                        return 0;
                    }
                    String nextS = curStr.substring(0, pos) + curStr.substring(pos + str.length(), curStr.length()); 
                    if (!visited.contains(nextS)) {
                        minLen = Math.min(minLen, nextS.length());
                        queue.offer(nextS);
                        visited.add(nextS);
                    }
                    pos = curStr.indexOf(str, pos + 1);
                }
            }
        }

        return minLen;
    }
}
