class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int[] answer = new int[seq.length()];
        int depth = 1;
        for (int i = 0; i < seq.length(); ++i)
            if (seq.charAt(i) == '(')
                answer[i] = ++depth % 2;
            else
                answer[i] = depth-- % 2;

        return answer;
    }
}