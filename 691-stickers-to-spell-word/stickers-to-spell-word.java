class Solution {
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        boolean[] vis = new boolean[1 << n];
        vis[0] = true;
        for (int ans = 0; !q.isEmpty(); ++ans) {
            for (int m = q.size(); m > 0; --m) {
                int curr = q.poll();
                if (curr == (1 << n) - 1) 
                    return ans;
                for (String s : stickers) {
                    int[] count = new int[26];
                    int next = curr;
                    for (char c : s.toCharArray()) 
                        ++count[c - 'a'];
                    for (int i = 0; i < n; ++i) {
                        int j = target.charAt(i) - 'a';
                        if ((curr >> i & 1) == 0 && count[j] > 0) {
                            --count[j];
                            next |= 1 << i;
                        }
                    }
                    if (!vis[next]) {
                        vis[next] = true;
                        q.offer(next);
                    }
                }
            }
        }
        return -1;
    }
}