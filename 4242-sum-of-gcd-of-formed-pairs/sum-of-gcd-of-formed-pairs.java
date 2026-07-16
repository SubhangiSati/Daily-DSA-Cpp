class Solution {
    public long gcdSum(int[] arr){
        int [] prefix = new int[arr.length];
        int max = 0;
        for (int i = 0; i< arr.length; i++) {
            if ( arr[i] > max) {
                max = arr[i];
            }
            prefix[i] = gcd(max, arr[i]);
        }
        Arrays.sort(prefix);
        int i = 0;
        int j = arr.length - 1;
        long sum = 0;
        while (i < j){
            sum += gcd(prefix[i], prefix[j]);
            i++;
            j--;
        }
        return sum;
    }
    public int gcd(int a, int b) {
        while(b!=0){
            int temp = b;
            b = a % b;
            a = temp;

        }
        return a;
    }
}