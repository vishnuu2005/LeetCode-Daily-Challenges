class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];

        for(int num : nums){
            if(num > max) max = num;
        }

        int totalmoves = 0;

        for(int num : nums){
            totalmoves += max - num;
        }

        return totalmoves;
    }
}