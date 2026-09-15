class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int ans = 0,
            start = 0;
        for (int i = k - 1; i < n; ++i) {
            int j = i - k + 1;
            if (j >= start && check(s, j, i)) {
                ++ans;
                start = i + 1;
                continue;
            }
            j = i - k;
            if (j >= start && check(s, j, i)) {
                ++ans;
                start = i + 1;
            }
        }
        return ans;
    }

    private boolean check(String s, int j, int i) {
        while (j < i) {
            if (s.charAt(j++) != s.charAt(i--)) 
                return false;
        }
        return true;
    }
}