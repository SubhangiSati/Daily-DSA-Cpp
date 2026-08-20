class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int arr1End = 0;
        int arr2Start = n - 1;
        result[0] = nums[0];       // arr1
        result[n - 1] = nums[1];   // arr2
        for (int i = 2; i < n; i++) {
            if (result[arr1End] > result[arr2Start]) {
                result[++arr1End] = nums[i];
            } else {
                result[--arr2Start] = nums[i];
            }
        }
        // arr2 currently goes from right to left,
        // so reverse it into the final position
        int left = arr2Start;
        int right = n - 1;
        while (left < right) {
            int temp = result[left];
            result[left] = result[right];
            result[right] = temp;

            left++;
            right--;
        }
        // Move arr2 next to arr1
        int arr1Length = arr1End + 1;
        int arr2Length = n - arr1Length;
        // Shift arr2 to immediately after arr1
        int[] finalResult = new int[n];
        System.arraycopy(result, 0, finalResult, 0, arr1Length);
        System.arraycopy(result, arr1Length, finalResult, arr1Length, arr2Length);
        return finalResult;
    }
}
