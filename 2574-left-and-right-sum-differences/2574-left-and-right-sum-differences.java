class Solution {
    public int[] leftRightDifference(int[] nums) {
        int[] leftSum = new int[nums.length];
        leftSum[0] = 0;
        int[] rightSum = new int[nums.length];
        rightSum[nums.length-1] = 0;
        int sum =0;
        for(int i=0;i<nums.length-1;i++){
            sum += nums[i];
            leftSum[i+1] = sum; 
        }
        sum =0;
        for(int i=nums.length-1; i>0 ;i--){
            sum += nums[i];
            rightSum[i-1] = sum;
        }

        for(int i=0;i<nums.length;i++){
            nums[i] = Math.abs(leftSum[i] - rightSum[i]);
        }
        return nums;
    }
}