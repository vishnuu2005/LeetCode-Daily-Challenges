class Solution {
    public int sumOfSquares(int[] nums) {

        int square=0;
        for(int i=0;i<nums.length;i++){
            if(nums.length % (i + 1) == 0)square += nums[i] * nums[i];
        }
        return square;
    }
}