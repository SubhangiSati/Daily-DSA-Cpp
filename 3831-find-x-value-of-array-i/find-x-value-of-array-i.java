class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] answer = new long[k];
        long[] dp = new long[k];
        for (final int num : nums) {
            long[] dp2 = new long[k];
            final int numMod = num % k;
            dp2[numMod] = 1;
            for (int i = 0; i < k; ++i) {
                final int mod2 = (int) (1L * i * numMod % k);
                dp2[mod2] += dp[i];
            }
            for (int i = 0; i < k; ++i)
                answer[i] += dp2[i];
            dp = dp2;
        }

        return answer;
    }
}