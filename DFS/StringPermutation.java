package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Lintcode problem 10: https://www.lintcode.com/problem/10/?fromId=161&_from=collection
public class StringPermutation {
    public List<String> stringPermutation2(String str) {
        ArrayList<String> output = new ArrayList<>();
        // convert str into a charArray 
        char[] strArr = str.toCharArray();
        Arrays.sort(strArr);
        boolean[] visited = new boolean[str.length()];
        dfs(strArr, visited, output, new ArrayList<>());
        return output;
    }

    private String listToString(ArrayList<Character> arr) {
        StringBuilder str = new StringBuilder();
        for (char c : arr) {
            str.append(c);
        }
        return str.toString();
    }

    private void dfs(char[] strArr, 
                     boolean[] visited,
                     ArrayList<String> output,
                     ArrayList<Character> curPer) {
        // exit
        if (curPer.size() == strArr.length) {
            output.add(listToString(curPer));
            return;
        }

        for (int i = 0; i < strArr.length; i++) {
            if (visited[i]) {
                continue;
            }
            // handling dup
            if (i > 0 && strArr[i] == strArr[i - 1] && !visited[i - 1]) {
                continue;
            } 

            visited[i] = true;
            curPer.add(strArr[i]);
            dfs(strArr, visited, output, curPer);
            visited[i] = false;
            curPer.remove(curPer.size() - 1);
        }
    }
}
