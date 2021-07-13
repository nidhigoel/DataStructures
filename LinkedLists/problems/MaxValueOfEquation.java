package LinkedLists.problems;

import java.util.LinkedList;

//https://leetcode.com/problems/max-value-of-equation/
public class MaxValueOfEquation {
  // one might think of using a heap of size k
  // but since the points are already sorted we dont need a heap
  // we can maintain a linked list in increasing order
  // and get the max value in O(1)
  // and remove all smaller elements when inserting new element
  // Thus overall complexity reduces to O(N) instead of O(NlogN)
  public int findMaxValueOfEquation(int[][] points, int k) {
    int n = points.length;
    if(n<2) return 0;
    LinkedList<int[]> ll = new LinkedList<>();
    ll.addLast(new int[]{points[0][0], points[0][1]-points[0][0]});
    int ans = Integer.MIN_VALUE;
    for(int i=1;i<n;i++){
      int[] cur = points[i];

      while(!ll.isEmpty() && ll.peekFirst()[0]+k<cur[0]){
        ll.removeFirst();
      }
      if(!ll.isEmpty()){
        int[] maxTillNow = ll.peekFirst();
        ans = Math.max(ans,maxTillNow[1]+cur[1]+cur[0]);
      }
      while(!ll.isEmpty() && ll.peekLast()[1]<=cur[1]-cur[0]){
        ll.removeLast();
      }
      ll.addLast(new int[]{cur[0], cur[1]-cur[0]});

    }
    return ans;
  }
}
