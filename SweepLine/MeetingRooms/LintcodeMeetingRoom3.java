package SweepLine.MeetingRooms;
// Lintcode 1897
public class LintcodeMeetingRoom3 {
    // Time: O(m + n)
    // Space: O(N)
    public boolean[] meetingRoomIII(int[][] intervals, int rooms, int[][] ask) {
        // roomSum document intervals start and end times
        int[] roomSum = new int[50001];
        int maxHour = 1;
        for (int[] interval : intervals) {
            roomSum[interval[0]] += 1;
            roomSum[interval[1]] -= 1;
            maxHour = Math.max(maxHour, interval[1]);
        }
        // meetingSlots[i] = true meaning at ith hour, there isn't any available rooms 
        int[] meetingSlots = new int[50001];
        int temp = 0;
        for (int i = 1; i <= maxHour; i++) {
            temp += roomSum[i];
            if (temp == rooms) {
                meetingSlots[i] = 1;
            }
        }
        // compute the prefix sum of meetingSlots;
        for (int i = 2; i <= maxHour; i++) {
            meetingSlots[i] += meetingSlots[i - 1];
        }

        boolean[] res = new boolean[ask.length];
        for (int i = 0; i < ask.length; i++) {
            int start = ask[i][0], end = ask[i][1];
            // meetingSlots[end - 1] - meetingSlots[start - 1] <= 0 because when computing prefix sum 
            // iteration stops at max end hour from intervals, but there might be a end hour that's > max end hour
            // from intervals 
            // meetingSlots[end - 1] because end is the start of new meeting.
            if (meetingSlots[end - 1] - meetingSlots[start - 1] <= 0) {
                res[i] = true;
            } else {
                res[i] = false;
            }
        }
        return res;
    }
}
