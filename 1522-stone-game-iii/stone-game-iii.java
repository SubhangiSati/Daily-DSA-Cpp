public class Solution {
    private Integer[][] dp;
    private int n;

    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;
        dp = new Integer[n][2];

        int result = dfs(0, 1, stoneValue);
        if (result == 0) return "Tie";
        return result > 0 ? "Alice" : "Bob";
    }

    private int dfs(int i, int alice, int[] stoneValue) {
        if (i >= n) return 0;
        if (dp[i][alice] != null) return dp[i][alice];

        int res = alice == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int score = 0;
        for (int j = i; j < Math.min(i + 3, n); j++) {
            if (alice == 1) {
                score += stoneValue[j];
                res = Math.max(res, score + dfs(j + 1, 0, stoneValue));
            } else {
                score -= stoneValue[j];
                res = Math.min(res, score + dfs(j + 1, 1, stoneValue));
            }
        }

        dp[i][alice] = res;
        return res;
    }
}