class Solution {
    public int numberOfSets(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[][] p = new int[n + 1][k + 1];
        int[][] q = new int[n + 1][k + 1];
        p[1][0] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                p[i][j] = (p[i - 1][j] + q[i - 1][j]) % mod;
                q[i][j] = q[i - 1][j];
                if (j > 0) {
                    q[i][j] = (q[i][j] + p[i - 1][j - 1]) % mod;
                    q[i][j] = (q[i][j] + q[i - 1][j - 1]) % mod;
                }
            }
        }
        return (p[n][k] + q[n][k]) % mod;
    }
}