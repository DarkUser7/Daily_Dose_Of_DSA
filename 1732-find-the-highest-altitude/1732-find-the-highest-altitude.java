class Solution {
    public int largestAltitude(int[] gain) {
        int[] high = new int[gain.length+1];
        high[0] = 0;
        int sum=0;
        for(int i= 0 ; i< gain.length;i++){
            sum += gain[i];
            high[i+1] = sum;
        }
        int max = high[0];
        for(int i=1 ; i< high.length;i++){
            if(high[i]>=max){
                max = high[i];
            }
        }
        return max;
    }
}