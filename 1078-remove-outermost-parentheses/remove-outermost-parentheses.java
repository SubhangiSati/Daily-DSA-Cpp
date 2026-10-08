class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder answer = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                if (++count > 1) 
                    answer.append(c);
            } else {
                if (--count > 0) 
                    answer.append(c);
            }
        }
        return answer.toString();
    }
}