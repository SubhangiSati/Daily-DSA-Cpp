class Solution {
    static class State {
        long score;
        int[] idx = new int[4];
        int size;
    }

    private boolean isBetter(State p, State q) {
        if (p.score != q.score)
            return p.score > q.score;

        for (int i = 0; i < Math.min(p.size, q.size); i++) {
            if (p.idx[i] != q.idx[i])
                return p.idx[i] < q.idx[i];
        }

        return p.size < q.size;
    }

    private State copyState(State state) {
        State copy = new State();
        copy.score = state.score;
        copy.size = state.size;
        copy.idx = state.idx.clone();

        return copy;
    }

    private State addIndex(State state, int index) {
        State res = copyState(state);
        int pos = res.size;
        while (pos > 0 && res.idx[pos - 1] > index) {
            res.idx[pos] = res.idx[pos - 1];
            pos--;
        }
        res.idx[pos] = index;
        res.size++;
        return res;
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        long[][] arr = new long[n][4];
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }
        Arrays.sort(arr, Comparator.comparingLong(a -> a[1]));
        long[] ends = new long[n];
        for (int i = 0; i < n; i++)
            ends[i] = arr[i][1];
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = i;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (ends[mid] < arr[i][0]) 
                    left = mid + 1;
                else 
                    right = mid;
            }

            prev[i] = left - 1;
        }

        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 4; j++) 
                dp[i][j] = new State();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 4; j++) {
                State skip = dp[i - 1][j];
                State take = addIndex(dp[prev[i - 1] + 1][j - 1], (int) arr[i - 1][3]);
                take.score += arr[i - 1][2];
                dp[i][j] = isBetter(take, skip) ? take : copyState(skip);
            }
        }

        State ans = dp[n][4];
        return Arrays.copyOf(ans.idx, ans.size);
    }
}
