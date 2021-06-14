package binarysearch.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// concept : apply binary search on solution space
// N log N sorting
// logN times call
// each call takes O(N) time
// instead of O(N*N) reduced to O(NLogN)
public class FurthestBuildingReachable {
  public int furthestBuilding(int[] heights, int bricks, int ladders) {
    List< int[] > diff=new ArrayList<>();
    for(int i=1;i<heights.length;i++){
      int curr = heights[i]-heights[i-1];
      if(curr>0){
        int[] temp = new int[2];
        temp[0]=curr;
        temp[1]=i;
        diff.add(temp);
      }
    }
    Collections.sort(diff, (a, b)->a[0]-b[0]);

    int hi = heights.length-1;
    int lo = 0;
    int ans = 0;
    while(lo<hi){
      int mid = lo + (hi-lo+1)/2;
      if(canReach(diff, mid, bricks, ladders)){
        ans = mid;
        lo=mid;
      } else {
        hi=mid-1;
      }
    }
    return ans;
  }

  boolean canReach(List< int[] > diff, int building, int bricks, int ladders){
    for(int i=0; i<diff.size(); i++){
      int b = diff.get(i)[1];
      if(b>building)continue;
      int curDiff = diff.get(i)[0];
      if(bricks>=curDiff) bricks-=curDiff;
      else if(ladders>=1) ladders--;
      else return false;
    }
    return true;
  }
}
