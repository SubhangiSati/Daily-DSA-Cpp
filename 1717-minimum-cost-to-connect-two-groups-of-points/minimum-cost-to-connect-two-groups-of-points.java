class Solution {
    int[][] dp;
    int[] minCost;
    int[][] c;
    int m, n;
    public int connectTwoGroups(List<List<Integer>> cost) {
        m = cost.size();
        n = cost.get(0).size();
        c = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) 
                c[i][j] = cost.get(i).get(j);
        }
        minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) 
                minCost[j] = Math.min(minCost[j], c[i][j]);
        }
        dp = new int[m + 1][1 << n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solve(0, 0);
    }

    int solve(int i, int mask) {
        if (dp[i][mask] != -1)
            return dp[i][mask];
        if (i == m) {
            int ans = 0;
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) == 0)
                    ans += minCost[j];
            }
            return dp[i][mask] = ans;
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.min(ans,
                    c[i][j] + solve(i + 1, mask | (1 << j)));
        }
        return dp[i][mask] = ans;
    }
}