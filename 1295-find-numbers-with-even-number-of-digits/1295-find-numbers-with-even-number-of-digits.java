class Solution {
    public int findNumbers(int[] nums) {

        int total=0;
        int count =0;
        for(int i=0; i<nums.length;i++){
            while(nums[i]>0){
                count++;
                nums[i] /=10;
            }
            if(count %2 ==0){
                total++;
            }
            count =0;
        }
        return total;
    }
}