class Solution {
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) 
            arr[i] = new int[] { nums[i], cost[i] };
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        long[] x = new long[n + 1];
        long[] y = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            long a = arr[i - 1][0], b = arr[i - 1][1];
            x[i] = x[i - 1] + a * b;
            y[i] = y[i - 1] + b;
        }
        long ans = Long.MAX_VALUE;
        for (int i = 1; i <= n; ++i) {
            long a = arr[i - 1][0];
            long l = a * y[i - 1] - x[i - 1];
            long r = x[n] - x[i] - a * (y[n] - y[i]);
            ans = Math.min(ans, l + r);
        }
        return ans;
    }
}