package Heap.problems;

import java.util.PriorityQueue;

public class FurthestBuildingReachable {

  public int furthestBuilding(int[] heights, int bricks, int ladders) {
    int N = heights.length;
    int bricksRequired = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for(int i=1;i<N;i++){
      int diff = heights[i]-heights[i-1];
      if(diff<=0)continue;
      if(pq.size()<ladders)pq.add(diff);
      else if(!pq.isEmpty() && pq.peek()<diff){
        bricksRequired+=pq.poll();
        pq.add(diff);
      } else bricksRequired+=diff;
      if(bricksRequired>bricks) return i-1;
    }
    return N-1;
  }

}
