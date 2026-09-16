class Solution {
  public int maxProfit(int n, int[][] edges, int[] score) {
    final int maxMask = 1 << n;
    int[] need = new int[n];
    int[] dp = new int[maxMask];
    Arrays.fill(dp, -1);
    dp[0] = 0;

    for (int[] edge : edges) {
      final int u = edge[0];
      final int v = edge[1];
      need[v] |= 1 << u;
    }

    for (int mask = 0; mask < maxMask; ++mask) {
      if (dp[mask] == -1)
        continue;
      int position = Integer.bitCount(mask) + 1;
      for (int i = 0; i < n; ++i) {
        if ((mask >> i & 1) == 1)
          continue;
        if ((mask & need[i]) == need[i]) {
          final int newMask = mask | 1 << i; 
          dp[newMask] = Math.max(dp[newMask], dp[mask] + score[i] * position);
        }
      }
    }

    return dp[maxMask - 1];
  }
}