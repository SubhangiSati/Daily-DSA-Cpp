class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        boolean sorted = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                sorted = false;
                break;
            }
        }
        if (sorted) 
            return 0;

        int result = (1 << Math.max(1, 32 - Integer.numberOfLeadingZeros(n - 1))) - 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) 
                result &= (i & nums[i]);
        }

        return result;
    }
}