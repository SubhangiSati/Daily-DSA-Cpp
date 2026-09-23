class Solution {
    public int minOperations(int[] nums, int x) {
        int s = -x;
        for (int v : nums) 
            s += v;
        int maximum = -1, t = 0;
        int n = nums.length;
        for (int i = 0, j = 0; i < n; ++i) {
            t += nums[i];
            while (j <= i && t > s) 
                t -= nums[j++];
            if (t == s) 
                maximum = Math.max(maximum, i - j + 1);
        }
        return maximum == -1 ? -1 : n - maximum;
    }
}