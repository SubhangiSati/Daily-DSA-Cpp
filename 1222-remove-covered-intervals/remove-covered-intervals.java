class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];  
            }
            return a[0] - b[0];  
        });
        int nonCoveredCount = 0;       
        int previousEnd = Integer.MIN_VALUE;
        for (int[] currentInterval : intervals) {
            int currentEnd = currentInterval[1];
            if (currentEnd > previousEnd) {
                nonCoveredCount++;
                previousEnd = currentEnd;
            }        }
        return nonCoveredCount;
    }
}