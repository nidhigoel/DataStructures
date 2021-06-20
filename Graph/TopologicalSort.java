package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

// Approach 1 : DFS
// Approach 2 : In-degree
public class TopologicalSort {
  static boolean impossible = false;
  // https://leetcode.com/problems/course-schedule-ii
  public static int[] findOrderUsingDfs(int numCourses, int[][] prerequisites) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for(int i=0; i<prerequisites.length; i++){
      graph.computeIfAbsent(prerequisites[i][1],x->new ArrayList<>()).add(prerequisites[i][0]);
    }
    boolean[] visited = new boolean[numCourses];
    boolean[] recStack = new boolean[numCourses];
    Stack<Integer> order = new Stack<>();
    for(int i=0; i<numCourses; i++){
      if(visited[i]) continue;
      dfsTopSort(graph, i, visited, recStack, order);
    }
    if(impossible) return new int[0];
    int[] ans = new int[numCourses];
    int pos=0;
    while(!order.empty()){
      ans[pos++] = order.pop();
    }
    return ans;
  }

  static void dfsTopSort(Map<Integer, List<Integer>> graph,
                         int course, boolean[] visited, boolean[] recStack, Stack<Integer> order) {
    if(impossible){return;}
    if(recStack[course]){impossible = true;return;}
    if(visited[course]){return;}
    visited[course]=true;
    recStack[course]=true;
    for(int child : graph.getOrDefault(course, new ArrayList<>())){
      dfsTopSort(graph, child, visited,recStack, order);
    }
    recStack[course]=false;
    order.push(course);
  }

  // Preferred Approach -- more intuitive
  public static int[] findOrderUsingIndegree(int numCourses, int[][] prerequisites) {
    // Calculate adjacency list and indegree of each node
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int[] indegree = new int[numCourses];
    for(int i=0; i<prerequisites.length; i++){
      int prereq = prerequisites[i][1];
      int depends = prerequisites[i][0];
      graph.computeIfAbsent(prereq,x->new ArrayList<>()).add(depends);
      indegree[depends]++;
    }
    //Take queue and add all nodes with indegree 0
    Queue<Integer> q = new LinkedList<>();
    for(int i=0; i<numCourses; i++){
      if(indegree[i]==0) q.add(i);
    }
    int[] topSort = new int[numCourses];
    int pos = 0;
    // Take vertex from queue
    // remove edges outgoing from this vertex
    // add new neighbours in the queue whose in-degree becomes 0
    while(!q.isEmpty()){
      int course = q.remove();
      topSort[pos++]=course;
      for(int child: graph.getOrDefault(course, new ArrayList<>())){
        indegree[child]--;
        if(indegree[child] == 0) q.add(child);
      }
    }
    // if there is a cycle indegree of certain vertices will never be zero
    // Hence size of topsort will not be equal to total vertices
    if(pos!=numCourses) return new int[0];
    return topSort;
  }

  public static void main(String[] args) {
    int numCourses=4;
    int[][] prerequisites={{1,0},{2,0},{3,1},{3,2}};
    int[] ans = findOrderUsingIndegree(numCourses, prerequisites);
//    int[] ans = findOrderUsingDfs(numCourses, prerequisites);
    System.out.println(Arrays.toString(ans));
  }
}
