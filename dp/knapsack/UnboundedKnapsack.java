package dp.knapsack;

public class UnboundedKnapsack {

  //https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
  private static int minCoinChange(int [] C, int N){
    int S = C.length;
    int[] dp = new int[N+1];
    for(int i=0; i<S; i++){
      int curr = C[i];
      dp[i]= 1;
      for(int j=curr+1; j<=N; j++){
        if(dp[j]==0) dp[j] = dp[j-curr]+1;
        else dp[j] = Math.min(dp[j-curr]+1, dp[j]);
      }
    }
    if(dp[N]==0) return -1;
    return dp[N];
  }
  //https://www.geeksforgeeks.org/coin-change-dp-7/
  private static int waysOfCoinChange(int [] C, int N){
    int S = C.length;
    int[] dp = new int[N+1];
    dp[0]=1;
    for(int i=0; i<S; i++){
      int curr = C[i];
      for(int j=curr; j<=N; j++){
        dp[j] += dp[j-curr];
      }
    }
    return dp[N];
  }
  public static void main(String[] args) {
    int [] C = {1,2,3,4,2,5,6};
    int ans = minCoinChange(C, 9);
    System.out.println(ans);
  }
}
