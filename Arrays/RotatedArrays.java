package Arrays;

public class RotatedArrays {
  //https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
  public int findMin(int[] nums) {
    int lo=0;
    int hi=nums.length-1;
    // three elements
    while(lo+1<hi){
      int mid = lo+(hi-lo)/2;
      if(nums[lo]<nums[mid] && nums[mid]<nums[hi]){ // sorted array
        hi=lo;
      } else if(nums[lo]<nums[mid]){
        // sorted in first half means minimum is present in second half
        lo=mid+1;
      } else {
        hi=mid;
      }
    }
    return Math.min(nums[lo],nums[hi]);

  }

  //https://leetcode.com/problems/search-in-rotated-sorted-array/
  public int search(int[] nums, int target) {
    int lo=0;
    int hi=nums.length-1;
    if(hi==-1) return -1;
    if(lo==hi) return nums[lo]==target?lo:-1;
    int pivotIndex = getPivotIndex(nums);
    if(pivotIndex!=0 && nums[0]<=target && target<=nums[pivotIndex-1]) return search(nums, 0,pivotIndex-1,target);
    else if(nums[pivotIndex]<=target && target<=nums[hi]) return search(nums, pivotIndex, hi, target);
    return -1;
  }

  int search(int[] nums, int lo, int hi, int target){
    while(lo<=hi){
      int mid=lo+(hi-lo)/2;
      if(nums[mid]==target) return mid;
      if(nums[mid]<target) lo=mid+1;
      else hi=mid-1;
    }
    return -1;
  }

  int getPivotIndex(int[] nums){
    int lo=0;
    int hi=nums.length-1;
    if(nums[lo]<nums[hi]) return lo;
    // 3 elements
    while(lo+1<hi){
      int mid = lo + (hi-lo)/2;
      if(nums[lo]<nums[mid] && nums[mid]<nums[hi]){
        hi=lo;
      } else if(nums[lo]<nums[mid]){
        lo=mid+1;
      } else {
        hi=mid;
      }
    }
    return nums[lo]<nums[hi]?lo:hi;
  }
}
