package BFS&DFS;
// Lintcode problem 121: https://www.lintcode.com/problem/121/?fromId=161&_from=collection
// Description
// Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end.
// The question ask to return ALL shortest paths, the word ALL is a indication that the method to solve the problem is dfs, however, bfs is the best way to find shortest path. Therefore, the apporach is to use bfs to find the shortest distance from END to Start using bfs, then use dfs to find all valid paths.

public class WordLadder2 {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // add start and end to dict 
        dict.add(start);
        dict.add(end);
        List<List<String>> output = new ArrayList<>();
        // form adjcency list 
        Map<String, Set<String>> graph = formGraph(dict);
        Map<String, Integer> distanceMap = new HashMap<>(); // key: word from dict, value: distance to start.
        // traverse through graph from end and compute the shortest distance to start. 
        bfs(end, graph, distanceMap);
        List<String> curSet = new ArrayList<String>();
        curSet.add(start);
        dfs(graph, distanceMap, output, curSet, start, end);
        return output;
    }

    private void dfs(Map<String, Set<String>> graph,
                     Map<String, Integer> distanceMap,
                     List<List<String>> output,
                     List<String> curSet,
                     String curWord,
                     String end) {
        // exit 
        if (curWord.equals(end)) {
            output.add(new ArrayList<>(curSet));
            return;
        }

        // dfs starting from start 
        for (int i = 0; i < curWord.length(); i++) {
            String curStr = curWord.substring(0, i) + '*' + curWord.substring(i + 1);
            // check if any of the neibors is one level below curWord 
            for (String n : graph.get(curStr)) {
                if (distanceMap.get(n) == distanceMap.get(curWord) - 1) {
                    curSet.add(n);
                    dfs(graph, distanceMap, output, curSet, n, end);
                    curSet.remove(curSet.size() - 1);
                }
            }
        }
    }

    private void bfs(String end,
                     Map<String, Set<String>> graph,
                     Map<String, Integer> distanceMap) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(end);
        distanceMap.put(end, 0);

        while (!queue.isEmpty()) {
            String curWord = queue.poll();
            for (int i = 0; i < curWord.length(); i++) {
                String curStr = curWord.substring(0, i) + '*' + curWord.substring(i + 1);
                // iterate over neighbor set of curStr 
                for (String n : graph.get(curStr)) {
                    if (!distanceMap.containsKey(n)) {
                        distanceMap.put(n, distanceMap.get(curWord) + 1);
                        queue.offer(n);
                    }
                }
            }
        }
    }

    private Map<String, Set<String>> formGraph(Set<String> dict) {
        Map<String, Set<String>> graph = new HashMap<>();

        for (String word : dict) {
            for (int i = 0; i < word.length(); i++) {
                String curStr = word.substring(0, i) + '*' + word.substring(i + 1);
                graph.putIfAbsent(curStr, new HashSet<>());
                graph.get(curStr).add(word);
            }
        }

        return graph;
    }
}
