package MiscProb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SingleThreadedCPU {
  class Task{
    int start;
    int time;
    int ind;

    Task(int start, int time, int ind){
      this.start = start;
      this.time = time;
      this.ind = ind;
    }

    int getTime(){
      return time;
    }

    int getInd(){
      return ind;
    }

    @Override
    public String toString(){
      return start+" "+time+" "+ind;
    }
  }
  public int[] getOrder(int[][] T) {
    List<Task> tasks = new ArrayList<>();
    int ind =0;
    for(int[] task:T){
      Task t = new Task(task[0], task[1], ind++);
      tasks.add(t);
    }
    Collections.sort(tasks, new Comparator<Task>(){
      @Override
      public int compare(Task t1, Task t2){
        if(t1.start<t2.start) return -1;
        else if(t1.start == t2.start) return 0;
        else return 1;
      }
    });
    System.out.println(tasks);

    int time = tasks.get(0).start;
    PriorityQueue<Task> pq = new PriorityQueue<>(
        Comparator.comparingInt(Task::getTime).thenComparingInt(Task::getInd));
    pq.add(tasks.get(0));

    int pos=1;
    int N = tasks.size();
    int[] ans = new int[N];
    ind = 0;
    while(!pq.isEmpty()){

      // fill priority queue;
      while(pos<N && tasks.get(pos).start<=time){
        pq.add(tasks.get(pos));
        pos++;
      }
      Task cur = pq.poll();
      ans[ind++] = cur.time;
      time = time+cur.time;
    }
    return ans;
  }
}
