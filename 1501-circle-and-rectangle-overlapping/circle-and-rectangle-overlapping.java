class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int p = f(x1, x2, xCenter);
        int q = f(y1, y2, yCenter);
        return p * p + q * q <= radius * radius;
    }

    private int f(int i, int j, int k) {
        if (i <= k && k <= j) 
            return 0;
        return k < i ? i - k : k - j;
    }
}