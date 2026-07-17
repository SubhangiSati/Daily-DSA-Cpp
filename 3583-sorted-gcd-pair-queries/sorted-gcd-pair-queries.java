class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = Arrays.stream(nums).max().getAsInt();
        int[] cnt = new int[max + 1];
        long[] cntG = new long[max + 1];
        for (int x : nums) {
            ++cnt[x];
        }
        for (int i = max; i > 0; --i) {
            int v = 0;
            for (int j = i; j <= max; j += i) {
                v += cnt[j];
                cntG[i] -= cntG[j];
            }
            cntG[i] += 1L * v * (v - 1) / 2;
        }
        for (int i = 2; i <= max; ++i) {
            cntG[i] += cntG[i - 1];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            ans[i] = search(cntG, queries[i]);
        }
        return ans;
    }

    private int search(long[] nums, long x) {
        int n = nums.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
