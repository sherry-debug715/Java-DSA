package SweepLine.MeetingRooms;

import Sort.SortTheJumbledNumbers.java.PriorityQueue;

// Leetcode 2402 
// Time: O(NlogN)
// Space: O(N)
public class LeetcodeMeetingRoom3 {
       public int mostBooked(int n, int[][] meetings) {
        // Step 1: sort meetings by start time, if start time equals, sort by end time
        Arrays.sort(meetings, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });
        // Step 2: use a minHeap to store endtime and room number
        int[] meetingCounter = new int[n]; // count number of meeting going on in each room
        int res = 0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                } else {
                    return a[0] - b[0];
                }
            }
        });
        // populate minHeap with n rooms each has a initial end time of 0
        for (int i = 0; i < n; i++) {
            minHeap.offer(new int[]{0, i});
        }

        for (int[] meeting : meetings) {
            int startTime = meeting[0];
            while (minHeap.peek()[0] < startTime) {
                minHeap.offer(new int[] {startTime, minHeap.poll()[1]});
            }
            int[] curr = minHeap.poll();
            int endTime = curr[0] + (meeting[1] - meeting[0]);
            int curRoom = curr[1];
            minHeap.offer(new int[] {endTime, curRoom});
            meetingCounter[curRoom] += 1;
            if (meetingCounter[curRoom] > meetingCounter[res]) {
                res = curRoom;
            } else if (meetingCounter[curRoom] == meetingCounter[res]) {
                res = Math.min(curRoom, res);
            }
        }

        return res;
    }
}

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        int[] res = new int[n];
        long[] times = new long[n];
        Arrays.sort(meetings, (a,b)->Integer.compare(a[0],b[0]));
        for(int i=0; i<meetings.length; i++){
            int s = meetings[i][0], e = meetings[i][1];
            boolean flag = false;
            int minIdx = -1;
            long val = Long.MAX_VALUE;
            for(int j=0; j<n; j++){
                if(times[j]<val){
                    val = times[j];
                    minIdx = j;
                }
                if(times[j]<=s){
                    flag = true;
                    res[j]++;
                    times[j] = e;
                    break;
                }
            }
            if(!flag){
                res[minIdx]++;
                times[minIdx] += (e-s);
            }
        }
        int maxI = -1, id = -1;
        for(int i=0; i<n; i++){
            if(res[i]>maxI){
                maxI = res[i];
                id = i;
            }
        }
        return id;
    }
}
