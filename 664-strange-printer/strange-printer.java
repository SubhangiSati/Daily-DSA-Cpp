class Solution {
    public int strangePrinter(String s) {
        int len = s.length();
        int[] compressed = new int[len];
        int index = 0;
        char prev = s.charAt(0);
        for (char ch : s.toCharArray()) {
            if (prev != ch) {
                compressed[index++] = prev - 'a';
                prev = ch;
            }
        }
        compressed[index] = prev - 'a';

        Integer[][] dp = new Integer[index + 1][index + 1];
        return helper(compressed, 0, index, dp);
    }

    private int helper(int[] arr, int s, int e, Integer[][] dp) {
        if (s > e)
            return 0;
        if (dp[s][e] != null)
            return dp[s][e];
        int minPts = 1 + helper(arr, s + 1, e, dp);
        for (int m = s + 1; m <= e; m++) {
            if (arr[s] == arr[m]) {
                int currPts = helper(arr, s + 1, m - 1, dp) + helper(arr, m, e, dp);
                minPts = Math.min(minPts, currPts);
            }
        }

        return dp[s][e] = minPts;
    }
}