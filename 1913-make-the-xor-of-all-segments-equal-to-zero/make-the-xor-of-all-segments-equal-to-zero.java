class Solution {
    public int minChanges(int[] nums, int k) {
        int n = 1 << 10;
        Map<Integer, Integer>[] count = new Map[k];
        int[] size = new int[k];
        for (int i = 0; i < k; ++i) 
            count[i] = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            count[i % k].put(nums[i], count[i % k].getOrDefault(nums[i], 0) + 1);
            size[i % k]++;
        }
        int[] f = new int[n];
        Arrays.fill(f, 0x3f3f3f3f);
        f[0] = 0;
        for (int i = 0; i < k; ++i) {
            int[] g = new int[n];
            Arrays.fill(g, min(f) + size[i]);
            for (int j = 0; j < n; ++j) {
                for (var e : count[i].entrySet()) {
                    int a = e.getKey(), b = e.getValue();
                    g[j] = Math.min(g[j], f[j ^ a] + size[i] - b);
                }
            }
            f = g;
        }
        return f[0];
    }

    private int min(int[] arr) {
        int min = arr[0];
        for (int i : arr) 
            min = Math.min(min, i);
        return min;
    }
}