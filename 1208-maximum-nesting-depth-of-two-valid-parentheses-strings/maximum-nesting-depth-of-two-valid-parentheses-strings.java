class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];
        int open = 0;
        int i = 0;
        for (char ch : seq.toCharArray()) {
            if (ch == '(') {
                open++;
                answer[i] = open % 2;
            } else {
                answer[i] = open % 2;
                open--;
            }
            i++;
        }
        return answer;
    }
}