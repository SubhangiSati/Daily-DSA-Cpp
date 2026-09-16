class Solution {
    public int getXORSum(int[] arr1, int[] arr2) {
        int a = help(arr1);
        int b = help(arr2);
        return a & b;
    }
    public int help(int a[]){
        int ans = 0;
        for (int i : a){
            ans ^= i;
        }
        return ans;
    }
}