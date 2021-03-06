package Arrays;

import java.util.HashMap;
import java.util.Map;

// important concept
// 0
// 1 -> if sum%k = x
// 2
// 3
// 4 -> if sum%k = x
// it implies sum between 1 to 4 (exclusive) was multiple of k
public class SubarraySum {
  //https://leetcode.com/problems/continuous-subarray-sum/
  public boolean checkSubarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    int sum=0;
    for(int i=0; i<nums.length; i++){
      sum+=nums[i];
      if(i>0 && sum%k==0) return true;
      if(i>0 && nums[i]==0 && nums[i-1]==0) return true;
      if(nums[i]==0) continue;
      if(map.containsKey(sum%k) && map.get(sum%k)!=i-1) return true;
      map.put(sum%k,i);
    }
    return false;
  }
}
