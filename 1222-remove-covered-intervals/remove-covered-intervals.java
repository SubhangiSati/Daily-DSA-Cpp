class Solution {
    public int removeCoveredIntervals(int[][] interval) {
        int p = interval.length;
        int remain = p;

        for(int i=0;i<p;i++){
            boolean cover = false;
            int a  = interval[i][0];
            int b = interval[i][1];

            for(int j=0;j<p;j++){
                if(i==j) continue;

                int c = interval[j][0];
                int d = interval[j][1];

                if(c<=a && b<=d){
                    cover = true;
                    break;
                }
            }
            if (cover){
                remain--;
            }
        }
        return remain;
    }
}