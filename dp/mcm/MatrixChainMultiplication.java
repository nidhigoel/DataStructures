package dp.mcm;

import java.util.Map;
import java.util.function.BiFunction;

public class MatrixChainMultiplication {

  static int mcmRecursive(int start, int end, int[] matrices, Map<Integer, Integer> cache){
    if(start==end) return 0;
    if(start+1==end) return matrices[start]*matrices[start+1]*matrices[end+1];

    int min = Integer.MAX_VALUE;
    for(int k=start+1; k<end; k++){
      int ans = mcmRecursive(start,k, matrices, cache)
          + mcmRecursive(k+1, end, matrices, cache)
          + matrices[start]*matrices[k+1]*matrices[end+1];
      min = Math.min(ans, min);
    }

    return min;
  }

  private static boolean isScramble(String s1, String s2, int start, int end){
    if(s1.equals(s2)) return true;
    if(start==end) return false;

    for(int k=start+1; k<=end; k++){
      String part11 = s1.substring(start,k);
      String part21 = s1.substring(k,end+1);
      String part12 = s2.substring(start,k);
      String part22 = s2.substring(k,end+1);
      if(isScramble(part11,part12,0,part11.length()-1) && isScramble(part21,part22,0,part21.length()-1)) return true;
      String part13 = s2.substring(start,start+part21.length());
      String part23 = s2.substring(start+part21.length(),end+1);
      if(isScramble(part21,part13,0,part21.length()-1) && isScramble(part11,part23,0,part11.length()-1)) return true;
    }

    BiFunction<Integer, Integer, Integer> z = (Integer x, Integer y)->x+y;
    return false;
  }

  public static boolean isScramble(String s1, String s2) {
    return isScramble(s1,s2,0,s1.length()-1);
  }

  public static int findMax(int[] nums, int start, int end, boolean[] visited, int remaining){
    if(remaining==0) return 0;
    if(remaining==1) return getCoins1(nums, visited);
    if(remaining==2) return getCoins2(nums, visited);

    int max = Integer.MIN_VALUE;
    for(int k=start+1; k<end; k++){
      if(visited[k]) continue;
      visited[k]=true;
      remaining--;

      int leftCoin = getLeftNonBursted(nums, visited, k);
      int rightCoin = getRightNonBursted(nums, visited, k);

      int temp = findMax(nums, start, end, visited, remaining)
          + leftCoin*nums[k]*rightCoin;
      visited[k]=false;
      remaining++;
      max = Math.max(max, temp);
    }
    return max;
  }

  static int getCoins1(int[] nums, boolean[] visited){
    for(int k=0; k<nums.length; k++){
      if(!visited[k]) return nums[k];
    }
    return 1;
  }

  static int getCoins2(int[] nums, boolean[] visited){
    int cost1 =-1;
    int cost2 =-1;
    for(int k=0; k<nums.length; k++){
      if(!visited[k]) {
        if(cost1==-1) cost1=nums[k];
        else {
          cost2=nums[k];
          break;
        }
      }
    }
    return Math.max(cost1+cost1*cost2, cost2+cost1*cost2);
  }

  static int getLeftNonBursted(int[] nums, boolean[] visited, int pos){
    for(int k=pos-1; k>=0; k--){
      if(!visited[k]) return nums[k];
    }
    return 1;
  }

  static int getRightNonBursted(int[] nums, boolean[] visited, int pos){
    for(int k=pos+1; k<nums.length; k++){
      if(!visited[k]) return nums[k];
    }
    return 1;
  }

  public static int maxCoinsBurst(int[] nums) {
//    int[] destArray = ArrayUtils.(2, srcArray, 77);
    return findMax(nums, 0, nums.length-1, new boolean[nums.length], nums.length);
  }

  public static void main(String[] args) {
    int [] M = {2, 3, 7, 9, 1};
//    int ans = mcmRecursive(0, M.length-2, M, new HashMap<>());
//    boolean ans = isScramble("great", "rgeat");
    int ans = maxCoinsBurst(M);
    System.out.println(ans);
  }

}
