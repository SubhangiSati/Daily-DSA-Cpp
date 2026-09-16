class Solution {
    public int maxProfit(int n, int[][] edges, int[] score) {

        int[] need = new int[n];
        for (int i = 0; i < edges.length; i++) {
            int s = edges[i][0];
            int d = edges[i][1];
            need[d] |= 1 << s;
        }

        int[] dp = new int[1 << n];

        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int mask = 0; mask < (1 << n); mask++) {

            if (dp[mask] == -1)
                continue;
            int rank = Integer.bitCount(mask) + 1;
            for (int i = 0; i < n; i++) {
                if ((mask >> i & 1) == 0 && (need[i] & mask) == need[i]) {

                    int mask2 = mask | (1 << i);

                    dp[mask2] = Math.max(dp[mask2], dp[mask] + rank * score[i]);

                }
            }
        }

        return dp[(1 << n) - 1];

    }
}