package Sort;

import Sort.SortTheJumbledNumbers.java.ArrayList;

// Lintcode 1160
public class CampusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length, m = bikes.length;
        int[] res = new int[n];
        // use all array to document all distances from workerId to every bikeId
        int[][] all = new int[n * m][3];
        int idx = 0; // references current row of all during iteration 
        for (int w = 0; w < n; w++) { // Time: O(n * m)
            for (int b = 0; b < m; b++) {
                int distance = Math.abs(bikes[b][1] - workers[w][1]) + Math.abs(bikes[b][0] - workers[w][0]);
                all[idx][0] = distance;
                all[idx][1] = w;
                all[idx++][2] = b;
            }
        } 
        // distMap, i: distance, distMap[i]: a list of all array index with MD of i 
        // 2000: data constrain 0≤workers[i][j],bikes[i][j]<1000
        List<Integer>[] distMap = new List[2000];
        for (int i = 0; i < 2000; i++) { // Time: O(2000)
            distMap[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n * m; i++) { // Time: O(n * m)
            distMap[all[i][0]].add(i);
        }
        // use two boolean array to document already placed workers and bikes 
        boolean[] visitedW = new boolean[n];
        boolean[] visitedB = new boolean[m];

        for (int dist = 0; dist < 2000; dist++) { 
            List<Integer> allIdxList = distMap[dist];
            for (int j = 0; j < allIdxList.size(); j++) {
                int curWorker = all[allIdxList.get(j)][1]; 
                int curBike = all[allIdxList.get(j)][2]; 
                if (!visitedW[curWorker] && !visitedB[curBike]) {
                    res[curWorker] = curBike;
                    visitedW[curWorker] = true;
                    visitedB[curBike] = true;
                }
            }
        } 

        return res;
    }
}

/*
                       i
workers[[1,1],[0,0],[2,0]]
                 j
bikes. [[1,0],[2,2],[2,1]]
1. same shortest MD, choose the pair with the smallest worker index;
2. or else choose the pair with the smallest bike index

all = [
    [1,0,0]
    [4,0,1]
    [3,0,2]
    [1,1,0]
    [2,1,1] i
    [1,1,2]
    [1,2,0]
    [2,2,1]
    [1,2,2]
]

map = {1:[0, 3, 5, 6, 8], 4:[1], 3:[2], 2:[4, 7]}
vistw = [t,t,t]
vistb = [t,t,t]
i = 1; i < 2000 
j = 0; j < 5
curworker = 2
curbike = 1
ans = [0,2,0]

*/
