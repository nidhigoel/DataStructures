package dp.problems.subset;

import java.util.ArrayList;
import java.util.List;

public class EqualSumKSubset {
  public static boolean canPartitionKSubsets(int[] nums, int k) {

    int sum = sum(nums);
    if(sum%k!=0) return false;
    int target = sum/k;

    List<int[]> partitions = new ArrayList<>();
    int[] partition = new int[k];
    partitions.add(partition);
    int N = nums.length;

    for(int i=0; i<N; i ++){
      int cur = nums[i];

      for(int[] p : partitions){
        sum(p);
      }
      System.out.println("adding "+ cur);
      partitions = add(cur, partitions);

    }

    for(int[] p : partitions){

      if(isValid(p, target)) return true;
    }

    return false;

  }

  private static boolean isValid(int[] arr, int target) {
    for(int i=0; i<arr.length; i++){
      if(arr[i]!= target) return false;
    }
    return true;
  }

  private static int sum(int[] arr){
    int sum = 0;
    for(int i=0; i<arr.length; i++){
      System.out.print(arr[i]);
      sum += arr[i];
    }
    System.out.println();
    return sum;
  }

  private static List<int[]> add(int ele, List<int[]> partitions){
    List<int[]> newPartitions = new ArrayList<>();
    for( int[] partition : partitions){
      boolean startNewTeam = true;
      for(int i=0; i<partition.length; i++){

        if(partition[i]==0 && startNewTeam) {
          newPartitions.add(getNewPartition(partition, i, ele));
          startNewTeam = false;
        } else if(partition[i]!=0) {
          newPartitions.add(getNewPartition(partition, i, ele));
        }
      }

    }
    return newPartitions;
  }

  private static int[] getNewPartition(int[] partition, int pos, int ele){
    int[] newPart = new int[partition.length];
    for(int i=0; i<partition.length; i++){
      newPart[i] = partition[i];
    }
    newPart[pos] += ele;
    return newPart;
  }

  public static void main(String[] args) {
    int [] C = {4,3,2,3,5,2,1};
    boolean ans = canPartitionKSubsets(C, 4);
    System.out.println(ans);
  }
}
