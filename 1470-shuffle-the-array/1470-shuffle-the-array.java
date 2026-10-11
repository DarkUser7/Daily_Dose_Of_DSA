class Solution {
    public int[] shuffle(int[] nums, int n) {
        int k=0;
        int j=n;
        int[] arr = new int[nums.length];
        for(int i=0 ;i< nums.length ;i+=2){
            arr[i] = nums[k];
            arr[i+1] = nums[j];
            k++;
            j++;
        }
        return arr;
    }
}