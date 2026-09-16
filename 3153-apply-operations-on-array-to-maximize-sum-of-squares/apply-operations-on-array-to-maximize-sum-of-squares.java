class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final int mod = (int) 1e9 + 7;
        int[] count = new int[31];
        for (int a : nums) {
            for (int i = 0; i < 31; ++i) {
                if ((a >> i & 1) == 1) 
                    ++count[i];
            }
        }
        long ans = 0;
        while (k-- > 0) {
            int a = 0;
            for (int i = 0; i < 31; ++i) {
                if (count[i] > 0) {
                    a |= 1 << i;
                    --count[i];
                }
            }
            ans = (ans + 1L * a * a) % mod;
        }
        return (int) ans;
    }
}