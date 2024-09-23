package DFS;
// Lintcode 1891
// Time: O(n * (n - 1)!): n is the number of cities, for every city i, the algorithmn explores
// n - 1 cities, leading to all possible permutations of n - 1 cities 
// Space: O(n), where n is the recursive stack and visited array
public class TravelPlan {
    int minDistance = Integer.MAX_VALUE;
    public int travelPlan(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        boolean[] visited = new boolean[n];
        visited[0] = true;  // Start at city 0
        dfs(0, 0, 0, n, visited, arr);
        return minDistance;
    }

    private void dfs(int visitedCityNum, int curCity, int distance, int totalCity, boolean[] visited, int[][] arr) {
        // Base case: if all cities are visited, return to city 0
        if (visitedCityNum == totalCity - 1) {
            // Return to city 0 from the current city
            distance += arr[curCity][0];
            minDistance = Math.min(minDistance, distance);
            return;
        }

        // Visit all other cities
        // Start from city 1, since curCity start from 0, the distance from 0 to i is computed
        for (int i = 1; i < totalCity; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(visitedCityNum + 1, i, distance + arr[curCity][i], totalCity, visited, arr);
                visited[i] = false;  // Backtrack
            }
        }
    }
}
