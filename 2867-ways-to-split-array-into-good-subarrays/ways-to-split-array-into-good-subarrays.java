class Solution {
    public int numberOfGoodSubarraySplits(int[] nums) {
      int MOD = 1_000_000_007;
      int count = 0;
      int ways = 1;
      int n = nums.length;
      int e = 0, f = n - 1;
      while (nums[e] == 0) {
        e++;
        if (e == n) return 0;
      }  
      while (nums[f] == 0) 
        f--;
        for (int i = e + 1; i < f; i++) {
            if (nums[i] == 0) count++;
                else {
                    if (count > 0) {
                        ways = (int) ((long) ways * (count + 1) % MOD);
                        count = 0;
                    }
                }
        }
        if (count > 0)
            ways = (int) ((long) ways * (count + 1) % MOD);
        return ways;
    }
}