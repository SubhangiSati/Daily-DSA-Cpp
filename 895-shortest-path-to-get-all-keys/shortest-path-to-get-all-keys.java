class Solution {
    private static final int[] DIRS = {1, 0, -1, 0, 1};

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        char[][] g = new char[m][];
        for (int i = 0; i < m; i++) g[i] = grid[i].toCharArray();
        int start = 0, k = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = g[r][c];
                if (ch == '@') start = r * n + c;
                else if (ch >= 'a' && ch <= 'f') k = Math.max(k, ch - 'a' + 1);
            }
        }
        int full = (1 << k) - 1;
        if (full == 0) return 0;

        int states = (m * n) << k;
        boolean[] seen = new boolean[states];
        int[] queue = new int[states];         
        int head = 0, tail = 0;

        int s0 = start << k;                    
        seen[s0] = true;
        queue[tail++] = s0;

        for (int steps = 1; head < tail; steps++) {
            int levelEnd = tail;                
            while (head < levelEnd) {
                int sid = queue[head++];
                int cell = sid >>> k, mask = sid & full;
                int r = cell / n, c = cell % n;

                for (int d = 0; d < 4; d++) {
                    int nr = r + DIRS[d], nc = c + DIRS[d + 1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                    char ch = g[nr][nc];
                    if (ch == '#') continue;
                    if (ch >= 'A' && ch <= 'F' && (mask >> (ch - 'A') & 1) == 0) continue;

                    int nmask = mask;
                    if (ch >= 'a' && ch <= 'f') nmask |= 1 << (ch - 'a');
                    if (nmask == full) return steps;

                    int nsid = ((nr * n + nc) << k) | nmask;
                    if (!seen[nsid]) {
                        seen[nsid] = true;
                        queue[tail++] = nsid;
                    }
                }
            }
        }
        return -1;
    }
}