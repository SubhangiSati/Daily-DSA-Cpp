class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        Deque<Integer> stk = new ArrayDeque<>();
        int[] pair = new int[n];
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                stk.push(i);
            else if (ch == ')') {
                int open = stk.pop();
                pair[open] = i;
                pair[i] = open;
            }
        }
        StringBuilder sb = new StringBuilder();
        int direction = 1;
        for (int i = 0; i < n; i += direction) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == ')') {
                i = pair[i];
                direction = -direction;
            } else
                sb.append(ch);
        }
        return sb.toString();
    }
}