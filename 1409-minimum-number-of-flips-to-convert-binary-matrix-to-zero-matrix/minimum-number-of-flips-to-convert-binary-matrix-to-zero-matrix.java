class Solution {
    int n,m;
    public int minFlips(int[][] mat) {
        n=mat.length;m=mat[0].length;
        int ans=sol(mat,0,0,0);
        return ans==Integer.MAX_VALUE?-1:ans;
    }
    public int sol(int[][] mat,int i,int j,int c){
        if(j==m){
            j=0;
            i++;
        }
        if(i==n){
            return isValid(mat)?c:Integer.MAX_VALUE;
        }
        int x=sol(mat,i,j+1,c);
        flip(mat,i,j);
        int y=sol(mat,i,j+1,c+1);
        flip(mat,i,j);
        return Math.min(x,y);
    }
    public boolean isValid(int[][] mat){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(mat[i][j]==1) return false;
            }
        }
        return true;
    }
    public void flip(int[][] mat,int i,int j){
        mat[i][j]^=1;
        if(i>0) mat[i-1][j]^=1;
        if(i<n-1) mat[i+1][j]^=1;
        if(j>0) mat[i][j-1]^=1;
        if(j<m-1) mat[i][j+1]^=1;
    }
}