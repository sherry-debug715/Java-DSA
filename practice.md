public class Solution {
    // 二分查找数组中第一个大于 v 的元素的下标
    public int upperBound(int[] nums, int l, int r, int v) {
        if (v >= nums[r])
            return r + 1;
        while(l < r) {
            int m = (l + r) >> 1;
            if (nums[m] <=v)
                l = m + 1;
            else r = m;
        }
        return l;
    }
    /**
     * @param t: the length of the flight
     * @param dur: the length of movies 
     * @return: output the lengths of two movies
     */
    public int[] aerialMovie(int t, int[] dur) {
        // 对 dur 数组进行排序
        Arrays.sort(dur);
        // 结果数组
        int[] res = new int[2];
        // 对排序后的 dur 数组中的每个元素进行遍历
        for (int i = 0; i + 1 < dur.length; i++) {
            // 二分查找后续元素中能够满足题意的元素。
            int j = upperBound(dur, i + 1, dur.length - 1, t - 30 - dur[i]) - 1;
            // 如果找不到，则跳过。
            int sum = dur[i] + dur[j];
            if (sum > t - 30) continue;
            if (j <= i) continue;
            // 更新结果数组。
            if (sum > res[0] + res[1] || (sum == res[0] + res[1] && dur[j] > res[1])) {
                res[0] = dur[i];
                res[1] = dur[j];
            }
        }
        // 返回最终结果。
        return res;
    }
}