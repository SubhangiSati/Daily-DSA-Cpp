class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] t = new int[n][2];
        for (int i = 0; i < n; ++i) 
            t[i] = new int[] { speed[i], efficiency[i] };
        Arrays.sort(t, (a, b) -> b[1] - a[1]);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        long total = 0;
        long answer = 0;
        for (var x : t) {
            int s = x[0], e = x[1];
            total += s;
            answer = Math.max(answer, total * e);
            q.offer(s);
            if (q.size() == k) 
                total -= q.poll();
        }
        return (int) (answer % MOD);
    }
}