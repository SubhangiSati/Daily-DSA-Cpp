class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (num * 9 < sum) {
            return "";
        }
        int k = sum / 9;
        sum %= 9;
        StringBuilder answer = new StringBuilder("9".repeat(k));
        if (sum > 0) {
            answer.append(sum);
        }
        answer.append("0".repeat(num - answer.length()));
        return answer.toString();
    }
}