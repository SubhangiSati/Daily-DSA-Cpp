class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int zero = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        int finalZero = zero;

        IntPredicate check = step -> {
            for (int i = 1; i < n; i++) {
                int prev = (finalZero + (i - 1) * step + n) % n;
                int curr = (finalZero + i * step + n) % n;

                if (nums[prev] > nums[curr]) 
                    return false;
            }

            return true;
        };
        int answer = Integer.MAX_VALUE;
        if (check.test(1)) {
            answer = Math.min(answer, zero);
            answer = Math.min(answer, n - zero + 2);
        }

        if (check.test(-1)) {
            answer = Math.min(answer, zero + 2);
            answer = Math.min(answer, n - zero);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
