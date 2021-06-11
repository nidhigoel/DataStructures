package MiscProb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;


public class MaxEventsAttend {


  public static int maxEvents(int[][] events) {
    Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    int idx = 0, N= events.length, day = 0, ans=0;
    while(!pq.isEmpty() || idx<N){
      if(pq.isEmpty()){
        day = events[idx][0];
      }
      while(idx<N && events[idx][0]<=day){
        pq.add(events[idx][1]);
        idx++;
      }
      int x = pq.poll();
      if(x>day) {
        ans++;
        day++;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
//    int[][] events = {{1,5},{1,5},{1,5},{2,3},{2,3}};
    int[][] events = {{1,2},{2,3},{3,4}};
//    int[][] events = {{1,4},{4,4},{2,2},{3,4},{1,1}};
//    int[][] events = {{1,2},{1,2},{3,3},{1,5},{1,5}};
    int ans = maxEvents(events);
    System.out.println(ans);
  }
}
