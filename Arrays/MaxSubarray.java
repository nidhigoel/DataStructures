package Arrays;

public class MaxSubarray {
  // Known as Kadane's algo
  // Keep adding in sum until sum till here becomes negative
  // if sum till here becomes negative,
  // start afresh as the sumTillHere will not add any benefit to the max sum obtainable
  public int maxSubArray(int[] nums) {
    int n=nums.length;
    if(n==1)return nums[0];
    int maxTillHere = nums[0];
    int sumTillHere = 0;
    for(int num:nums){
      sumTillHere+=num;
      maxTillHere=Math.max(maxTillHere,sumTillHere);
      if(sumTillHere<0)sumTillHere=0;
    }
    return maxTillHere;
  }
}
