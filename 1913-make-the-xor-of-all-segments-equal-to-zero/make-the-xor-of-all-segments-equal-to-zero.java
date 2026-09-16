class Solution {
    public int minChanges(int[] nums, int k) {

        int n = nums.length;
        int MAX = 1024;
        int INF = 1_000_000_000;
        int[][] freq = new int[k][MAX];
        int[] size = new int[k];

        for (int i = 0; i < n; i++) {
            int group = i % k;
            freq[group][nums[i]]++;
            size[group]++;
        }
        int[] dp = new int[MAX];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int group = 0; group < k; group++) {
            int[] next = new int[MAX];
            int minPrevious = INF;
            for (int x : dp)
                minPrevious = Math.min(minPrevious, x);
            Arrays.fill(
                    next,
                    minPrevious + size[group]);

            for (int value = 0; value < MAX; value++) {
                int count = freq[group][value];
                if (count == 0)
                    continue;
                int changeCost = size[group] - count;
                for (int xor = 0; xor < MAX; xor++) {
                    if (dp[xor] == INF)
                        continue;
                    int newXor = xor ^ value;
                    next[newXor] = Math.min(
                            next[newXor],
                            dp[xor] + changeCost);
                }
            }
            dp = next;
        }
        return dp[0];
    }
}