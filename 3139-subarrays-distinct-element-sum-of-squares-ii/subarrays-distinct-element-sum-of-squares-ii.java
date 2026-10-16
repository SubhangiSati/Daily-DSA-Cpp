class Solution {
    private static final int MOD = 1_000_000_007;

    class LazySegmentTree {
        int n;
        long[] lazy;
        long[] sums;
        long[] squaredSums;

        LazySegmentTree(int n) {
            this.n = n;
            lazy = new long[4 * n];
            sums = new long[4 * n];
            squaredSums = new long[4 * n];
        }

        void updateRange(int l, int r) {
            updateRange(0, 0, n - 1, l, r);
        }

        void propagate(int i, int l, int r) {
            long gap = r - l + 1;
            long val = lazy[i];

            // Update sum of squares:
            // (a + val)^2 + (b + val)^2 + ...
            squaredSums[i] = (squaredSums[i]
                    + 2L * val * sums[i] % MOD
                    + val * val % MOD * gap % MOD) % MOD;

            // Update sum:
            sums[i] = (sums[i] + val * gap) % MOD;

            if (l < r) {
                lazy[i * 2 + 1] = (lazy[i * 2 + 1] + val) % MOD;
                lazy[i * 2 + 2] = (lazy[i * 2 + 2] + val) % MOD;
            }

            lazy[i] = 0;
        }

        long getTreeSquaredSums() {
            return squaredSums[0];
        }

        private void updateRange(
                int i, int start, int end, int l, int r) {

            if (lazy[i] > 0) {
                propagate(i, start, end);
            }

            if (end < l || start > r) {
                return;
            }

            if (start >= l && end <= r) {
                lazy[i] = 1;
                propagate(i, start, end);
                return;
            }

            int mid = start + (end - start) / 2;

            updateRange(i * 2 + 1, start, mid, l, r);
            updateRange(i * 2 + 2, mid + 1, end, l, r);

            sums[i] = (sums[i * 2 + 1] + sums[i * 2 + 2]) % MOD;

            squaredSums[i] = (squaredSums[i * 2 + 1]
                    + squaredSums[i * 2 + 2]) % MOD;
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        long ans = 0;

        Map<Integer, Integer> lastSeen = new HashMap<>();
        LazySegmentTree tree = new LazySegmentTree(n);

        for (int r = 0; r < n; r++) {
            int l = lastSeen.containsKey(nums[r])
                    ? lastSeen.get(nums[r]) + 1
                    : 0;

            tree.updateRange(l, r);
            lastSeen.put(nums[r], r);

            ans = (ans + tree.getTreeSquaredSums()) % MOD;
        }

        return (int) ans;
    }
}