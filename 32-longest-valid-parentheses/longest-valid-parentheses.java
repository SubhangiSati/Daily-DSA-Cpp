class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        int answer = 0;
        for (int i = 2; i <= n; ++i) {
            if (s.charAt(i - 1) == ')') {
                if (s.charAt(i - 2) == '(') 
                    f[i] = f[i - 2] + 2;
                else {
                    int j = i - f[i - 1] - 1;
                    if (j > 0 && s.charAt(j - 1) == '(') 
                        f[i] = f[i - 1] + 2 + f[j - 1];
                }
                answer = Math.max(answer, f[i]);
            }
        }
        return answer;
    }
}