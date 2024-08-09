package PriorityQueue;

import java.util.Comparator;
import java.util.Queue;

// leetcode 3016 (3) Solutions 
// Initial solution:
// Time: 10ms
// Space: 46.12mb
public class MinimumPushes {
    public int minimumPushes(String word) {
        // base case 
        if (word.length() == 1) {
            return 1;
        }
        // count the frequency of each letter 
        int[] freq = new int[26];
        for (char c : word.toCharArray()) { // O(N)
            freq[c - 'a'] += 1;
        }

        // use a max heap to store each char and it's freq
        Queue<int[]> maxHeap = new PriorityQueue<>(new Comparator<>() { 
            public int compare(int[] a, int[] b) {
                // if freq equal, sort by ascii 
                if (a[1] == b[1]) {
                    return a[0] - b[0];
                }
                // sort descendingly by freq 
                return b[1] - a[1];
            }
        });

        for (int i = 0; i < 26; i++) { // O(log26 -> 1)
            if (freq[i] > 0) {
                maxHeap.offer(new int[] {i + 'a', freq[i]});
            }
        }

        int cost = 0;
        int push = 1;
        while (!maxHeap.isEmpty()){ // O(1) 
            for (int i = 0; i < 8; i++) {
                if (!maxHeap.isEmpty()) {
                    int[] curr = maxHeap.poll(); // O(log 26 -> 1)
                    cost += push * freq[curr[0] - 'a'];
                } else {
                    break;
                }
            }
            if (!maxHeap.isEmpty()) {
                push += 1;
            }
        }
        return cost;
    }
}

// Solution 2 
// Time: 10ms
// Space: 45.50mb
class Solution2 {
    public int minimumPushes(String word) {
        // base case 
        if (word.length() == 1) {
            return 1;
        }
        // count the frequency of each letter 
        int[] freq = new int[26];
        for (char c : word.toCharArray()) { // O(N)
            freq[c - 'a'] += 1;
        }

        List<Integer> freqList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                freqList.add(freq[i]);
            }
        }

        Collections.sort(freqList, Collections.reverseOrder());

        int pushCount = 1;
        int pushes = 0;
        for (int i = 0; i < freqList.size(); i++) {
            pushes += freqList.get(i) * pushCount;
            if ((i + 1) % 8 == 0) {
                pushCount += 1;
            }
        }
        return pushes;
    }
}
