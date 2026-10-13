class Solution {
    public int[] replaceElements(int[] nums) {
        int max =-1;
        for(int i=nums.length-1;i>=0;i--){
            int curr = nums[i];
            nums[i] = max;
            if(curr>max){
                max = curr;
            }
        }
        return nums;
    }
}
