public class Solution {
    /**
     * @param workers: workers' location
     * @param bikes: bikes' location
     * @return: assign bikes
     */
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        // write your code here
        int n = workers.length;
        int m = bikes.length;
        int[][] alls = new int[n * m][3];
        boolean[] vistw = new boolean[n];
        boolean[] vistb = new boolean[m];
        List<Integer>[] mapp = new List[2000];
        int[] ans = new int[n];
        for (int i = 0; i < 2000; i++)
            mapp[i] = new ArrayList<Integer>();
        int cnt = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++){
                alls[cnt][0] = Math.abs(workers[i][0]-bikes[j][0])+Math.abs(workers[i][1]-bikes[j][1]);
                alls[cnt][1] = i;
                alls[cnt++][2] = j;
            }
        for (int i = 0; i < n * m; i++)
            mapp[alls[i][0]].add(i);
        for (int i = 0; i < 2000; i++)
            for (int j = 0; j < mapp[i].size(); j++){
                int curworker = alls[mapp[i].get(j)][1];
                int curbike = alls[mapp[i].get(j)][2];
                if (!vistw[curworker] && !vistb[curbike]){
                    ans[curworker] = curbike;
                    vistw[curworker] = true;
                    vistb[curbike] = true;
                }
            }
        return ans;
    }
}