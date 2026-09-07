class Solution {
    public String shortestSuperstring(String[] words) {
        int n = words.length;
        int[][] overlap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                int max = Math.min(words[i].length(), words[j].length());
                for (int k = max; k >= 0; k--) {
                    if (words[i].endsWith(words[j].substring(0, k))) {
                        overlap[i][j] = k;
                        break;
                    }
                }
            }
        }
        int size = 1 << n;
        int[][] dp = new int[size][n];
        for (int i = 0; i < size; i++)
            Arrays.fill(dp[i], -1);
        for (int i = 0; i < n; i++)
            dp[1 << i][i] = 0;
        for (int mask = 1; mask < size; mask++) {
            for (int last = 0; last < n; last++) {
                if (dp[mask][last] == -1)
                    continue;

                for (int next = 0; next < n; next++) {
                    if ((mask & (1 << next)) != 0)
                        continue;

                    int nm = mask | (1 << next);
                    dp[nm][next] = Math.max(
                            dp[nm][next],
                            dp[mask][last] + overlap[last][next]);
                }
            }
        }

        int full = size - 1;
        int last = 0;
        for (int i = 1; i < n; i++) {
            if (dp[full][i] > dp[full][last])
                last = i;
        }
        List<Integer> order = new java.util.ArrayList<>();
        int mask = full;
        while (mask != 0) {
            order.add(last);
            int prev = -1;
            for (int p = 0; p < n; p++) {
                if (p == last || (mask & (1 << p)) == 0)
                    continue;
                if (dp[mask ^ (1 << last)][p] != -1 &&
                        dp[mask][last] == dp[mask ^ (1 << last)][p] + overlap[p][last]) {
                    prev = p;
                    break;
                }
            }
            mask ^= 1 << last;
            if (mask == 0)
                break;
            last = prev;
        }
        Collections.reverse(order);
        StringBuilder ans = new StringBuilder(words[order.get(0)]);
        for (int i = 1; i < order.size(); i++) {
            int a = order.get(i - 1);
            int b = order.get(i);
            ans.append(words[b].substring(overlap[a][b]));
        }
        return ans.toString();
    }
}