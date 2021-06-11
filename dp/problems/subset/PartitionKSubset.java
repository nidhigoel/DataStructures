package dp.problems.subset;

import java.util.Arrays;

public class PartitionKSubset {

  private static int partitionKSubset(int[] nums, int k){
    int [][] dp = new int[nums.length+1][k+1];
    int[] temp = new int[k+1];
    Arrays.fill(temp,0);

    for(int n = 1; n<= nums.length; n++){
      for(int partitions=1; partitions<=k; partitions++){
        if(n==partitions) dp[n][partitions] = 1;
        else if(n<partitions) dp[n][partitions]=0;
        else dp[n][partitions] = dp[n-1][partitions]*(partitions)+dp[n-1][partitions-1];
      }
    }
    return dp[nums.length][k];
  }

  public static void main(String[] args) {
    int [] C = {1,2,3,4};
    int ans = partitionKSubset(C, 3);
    System.out.println(ans);
  }
}
