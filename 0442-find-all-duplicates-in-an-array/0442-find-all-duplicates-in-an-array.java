class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        List<Integer> result  =new ArrayList<>();
        for(int num: nums){
            if(seen.contains(num)){
                result.add(num);
            }else{
                seen.add(num);
            }
        }
        return result;
    }
}