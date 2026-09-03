class Solution {
    public boolean uniformArray(int[] nums1) {
        final int inf = Integer.MAX_VALUE;
        int m = inf;
        for (int i : nums1){
            if( i % 2 == 1){
                m = Math.min(m,i);
            }
        }
        for(int i : nums1){
            if( i % 2 == 0 && m != inf && i < m){
                return false;
            }
        }
        return true;
    }
}