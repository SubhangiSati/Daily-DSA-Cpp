class Solution {
    private long cst(int[] nums, int cost[], int md) {
        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += (long) cost[i] * Math.abs(nums[i] - md);
        }
        return result;
    }

    public long minCost(int[] nums, int[] cost) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i : nums) {
            min = Math.min(i, min);
            max = Math.max(i, max);
        }
        long ans = 0;
        while (min <= max) {
            int md = min + (max - min) / 2;
            long v1 = cst(nums, cost, md - 1), v2 = cst(nums, cost, md), v3 = cst(nums, cost, md + 1);
            if (v1 >= v2 && v2 <= v3) {
                ans = v2;
                break;
            } else if (v1 >= v2 && v2 >= v3) 
                min = md + 1;
            else if (v3 >= v2 && v2 >= v1) 
                max = md - 1;
        }
        return ans;
    }
}