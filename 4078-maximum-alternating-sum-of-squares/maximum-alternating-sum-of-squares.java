class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i)
            nums[i] *= nums[i];
        Arrays.sort(nums);
        long answer = 0;
        int m = n / 2;
        for (int i = 0; i < m; ++i)
            answer -= nums[i];
        for (int i = m; i < n; ++i)
            answer += nums[i];
        return answer;
    }
}