class Solution {
    public long maxAlternatingSum(int[] nums, int k) {
        int n = nums.length;
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) 
                maxVal = num;
        }
        
        long[] dp0 = new long[n];
        long[] dp1 = new long[n];
        long[] bit0 = new long[maxVal + 2];
        long[] bit1 = new long[maxVal + 2];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            if (i >= k) {
                int prev = nums[i - k];
                update(bit0, maxVal - prev + 1, dp0[i - k]);
                update(bit1, prev, dp1[i - k]);
            }
            
            int x = nums[i];
            dp0[i] = x + query(bit1, x - 1);
            dp1[i] = x + query(bit0, maxVal - x);
            
            if (dp0[i] > ans) 
                ans = dp0[i];
            if (dp1[i] > ans) 
                ans = dp1[i];
        }
        return ans;
    }

    private void update(long[] bit, int idx, long val) {
        for (; idx < bit.length; idx += idx & -idx) {
            if (val > bit[idx]) 
                bit[idx] = val;
        }
    }

    private long query(long[] bit, int idx) {
        long res = 0;
        for (; idx > 0; idx -= idx & -idx) {
            if (bit[idx] > res) 
                res = bit[idx];
        }
        return res;
    }
}