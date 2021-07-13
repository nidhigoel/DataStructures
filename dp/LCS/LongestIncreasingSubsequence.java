package dp.LCS;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
  //https://leetcode.com/problems/longest-increasing-subsequence
  // N^2 approach
  // https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
  public static int lengthOfLIS(int[] nums) {
    int N=nums.length;
    if(N<=1)return N;
    int[] dp = new int[nums.length];
    dp[0]=1;
    int ans =1;
    for(int i=1; i<N; i++){
      int max = 1;
      for(int j=0;j<i;j++){
        if(nums[j]<nums[i]){
          max=Math.max(max,dp[j]+1);
        }
      }
      dp[i]=max;
      ans = Math.max(ans, max);
    }
    return ans;
  }

  // NlogN approach
  // https://www.youtube.com/watch?v=1qD1FLhKrIE&ab_channel=CodeLibrary
  // at index i, store the last element of LIS of length i
  public static int lengthOfLIS1(int[] nums) {
    int N=nums.length;
    if(N<=1)return N;
    int[] dp = new int[nums.length];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0]=nums[0];
    int pos=0;
    for(int i=1; i<N; i++){
      if(nums[i]>dp[pos]){
        dp[++pos]=nums[i];
      } else {
        int lb=getLb(dp,nums[i]);
        dp[lb]=nums[i];
      }
    }
    return pos+1;

  }

  static int getLb(int[] dp, int target){
    int lo=0;
    int hi=dp.length-1;

    while(lo<hi){
      int mid = lo + (hi-lo)/2;
      if(dp[mid]<target){
        lo=mid+1;
      } else {
        hi=mid;
      }
    }
    return lo;
  }

  public static int findNumberOfLIS(int[] nums) {
    int N = nums.length;
    if(N<=1) return N;
    int[][] dp = new int[N][2];
    dp[0][0]=1; // length of LIS till here
    dp[0][1]=1; // number of LIS till here
    int max = 1;
    for(int i=1; i<nums.length; i++){
      dp[i][0]=1;
      dp[i][1]=1;
      for(int j=0; j<i; j++){
        if(nums[j]<nums[i]){
          if(dp[j][0]+1>dp[i][0]){
            dp[i][0]=dp[j][0]+1;
            dp[i][1]=dp[j][1];
            max = Math.max(max,dp[i][0]);
          } else if(dp[j][0]+1==dp[i][0]){
            dp[i][1]+=dp[j][1];
          }
        }
      }
    }
    int ans =0;
    for(int i=0; i<nums.length; i++){
      if(dp[i][0]==max) ans+=dp[i][1];
    }
    return ans;
  }

  public static void main(String[] args) {
//    int ans = lengthOfLIS1(new int[]{0, 1, 0, 3, 2, 3});
    int ans = findNumberOfLIS(new int[]{1,1,2,3});
    System.out.println(ans);
  }
}
