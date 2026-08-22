class Solution {
    public boolean checkDivisibility(int n) {
        int a = 0, b = 1;
        int x = n;
        while (x != 0) {
            int v = x % 10;
            x /= 10;
            a += v;
            b *= v;
        }
        return n % (a + b) == 0;
    }
}