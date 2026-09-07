class Solution {
    public int numPermsDISequence(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            long[] newDp = new long[n + 1];
            if (s.charAt(i) == 'I') {
                long sum = 0;
                for (int j = 0; j <= i + 1; j++) {
                    newDp[j] = sum;
                    if (j <= i) 
                        sum = (sum + dp[j]) % MOD;
                }
            } 
            else {
                long sum = 0;
                for (int j = i; j >= 0; j--) {
                    sum = (sum + dp[j]) % MOD;
                    newDp[j] = sum;
                }
            }
            dp = newDp;
        }
        long answer = 0;
        for (int i = 0; i <= n; i++) 
            answer = (answer + dp[i]) % MOD;
        return (int) answer;
    }
}