class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) 
            prefix[i + 1] = prefix[i] + nums[i];

        int[] sum = new int[n - k + 1];
        for (int i = 0; i < sum.length; i++) 
            sum[i] = prefix[i + k] - prefix[i];

        int[] left = new int[sum.length];
        int best = 0;

        for (int i = 0; i < sum.length; i++) {
            if (sum[i] > sum[best]) 
                best = i;
            left[i] = best;
        }
        int[] right = new int[sum.length];
        best = sum.length - 1;
        for (int i = sum.length - 1; i >= 0; i--) {
            if (sum[i] >= sum[best]) 
                best = i;
            right[i] = best;
        }

        int maxTotal = 0;
        int[] ans = new int[3];
        for (int mid = k; mid < sum.length - k; mid++) {
            int leftIndex = left[mid - k];
            int rightIndex = right[mid + k];
            int total =
                    sum[leftIndex]
                    + sum[mid]
                    + sum[rightIndex];
            if (total > maxTotal) {
                maxTotal = total;
                ans[0] = leftIndex;
                ans[1] = mid;
                ans[2] = rightIndex;
            }
        }
        return ans;
    }
}