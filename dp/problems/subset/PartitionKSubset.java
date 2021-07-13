package dp.problems.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartitionKSubset {
  static int ans = 0;
  static void partitionKSubsetRec(int[] nums, int i, List<List<Integer>> partitions, int numOfPartitions, int target){
    if(i==nums.length){
      if(numOfPartitions==target){
        ans++;
        System.out.println(partitions);
      }
      return;
    }
    boolean newTeam =true;
    for(int j=0; j<partitions.size(); j++ ){
      List<Integer> partition = partitions.get(j);
      partition.add(nums[i]);
      if(partition.size()==1 && newTeam){
        newTeam=false;
        partitionKSubsetRec(nums, i+1, partitions, numOfPartitions+1, target);
      } else if(partition.size()>=1) {
        partitionKSubsetRec(nums, i+1, partitions, numOfPartitions, target);
      }
      partition.remove(partition.size()-1);
    }
  }

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
    // 1 2 34
    // 1 23 4
    // 1 24 3
    // 12 3 4
    // 13 2 4
    // 14 2 3
//    int ans = partitionKSubset(C, 3);
    List<List<Integer>> partitions = new ArrayList<>();
    for(int i=0; i<3; i++){
      partitions.add(new ArrayList<>());
    }
    partitionKSubsetRec(C, 0, partitions, 0, 3);
    System.out.println(ans);
  }
}
