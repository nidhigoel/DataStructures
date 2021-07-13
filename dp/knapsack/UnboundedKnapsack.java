package dp.knapsack;

import java.util.Arrays;

public class UnboundedKnapsack {

  //https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
  //https://leetcode.com/problems/coin-change/
  private static int minCoinChange(int [] coins, int amount){
    if(amount==0)return 0;
    int[] dp = new int[amount+1];
    Arrays.fill(dp,Integer.MAX_VALUE);
    dp[0]=0;
    for(int coin : coins){
      for(int j=coin; j<=amount; j++){
        if(dp[j-coin]!=Integer.MAX_VALUE && dp[j-coin]+1<dp[j])
          dp[j]=1+dp[j-coin];
      }
    }
    return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
  }
  //https://www.geeksforgeeks.org/coin-change-dp-7/
  // https://leetcode.com/problems/coin-change-2/
  private static int waysOfCoinChange(int[] coins, int amount){
    int[] dp = new int[amount+1];
    dp[0]=1;
    for(int coin : coins){
      for(int j=coin; j<=amount; j++){
        dp[j] += dp[j-coin];
      }
    }
    return dp[amount];
  }
  public static void main(String[] args) {
    int [] C = {1,2,3,4,2,5,6};
//    int ans = minCoinChange(C, 9);
    int ans = waysOfCoinChange(C, 9);
    System.out.println(ans);
  }
}
