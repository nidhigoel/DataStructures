package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

// Kosaraju Algorithm
// Tarjan Agorithm
// Finding bridge and articulation points
public class StronglyConnecedComponent {

  static int time = 0;
  // Explanation : https://www.youtube.com/watch?v=ZeDNSeilf-Y&list=RDCMUCnxhETjJtTPs37hOZ7vQ88g&index=1
  static List<List<Integer>> tarjanSCC(int n, List<List<Integer>> connections){
    Map<Integer, List<Integer>> adjList = new HashMap<>();
    for(List<Integer> connection : connections){
      int node1 = connection.get(0);
      int node2 = connection.get(1);
      adjList.computeIfAbsent(node1, x->new ArrayList<>()).add(node2);
//      adjList.computeIfAbsent(node2, x->new ArrayList<>()).add(node1);
    }
    List<List<Integer>> ans = new ArrayList<>();
    Stack<Integer> sequence = new Stack<>();
    boolean[] visited = new boolean[n];
    int[] disc = new int[n];
    Arrays.fill(disc,-1);
    int[] low = new int[n];
    Arrays.fill(low,-1);
    for(int i=0; i<n; i++) {
      if(disc[i]!=-1) continue;
      tarjanDFS(adjList, i, sequence, visited, disc, low, ans);
    }
    return ans;
  }

  private static void tarjanDFS(Map<Integer, List<Integer>> adjList, int i, Stack<Integer> sequence, boolean[] visited, int[] disc, int[] low, List<List<Integer>> ans) {
    disc[i]=time;
    low[i]=time;
    time+=1;
    sequence.push(i);
    visited[i]=true;

    for(int nei : adjList.getOrDefault(i, new ArrayList<>())){
      if(disc[nei]==-1) {
        tarjanDFS(adjList, nei, sequence, visited, disc, low, ans);
        low[i] = Math.min(low[i], low[nei]);
      } else if(visited[nei]){ // back edge
        low[i]=Math.min(low[i], disc[nei]);
      }
    }
    if(disc[i]==low[i]){
      List<Integer> scc = new ArrayList<>();
      while(sequence.peek()!=i){
        int node = sequence.pop();
        visited[node]=false;
        scc.add(node);
      }
      int node = sequence.pop();
      visited[node]=false;
      scc.add(node);
      ans.add(scc);
    }
  }

  // create adjacency list for undirected graph
  // disc, low and parent of each node
  // low = min(low_i, low_nei) while backtracking
  // low = min(low_i, dis_nei) if back edge and is not parent
  static List<List<Integer>> findBridge(int n, List<List<Integer>> connections){
    Map<Integer, List<Integer>> adjList = new HashMap<>();
    for(List<Integer> connection : connections){
      int node1 = connection.get(0);
      int node2 = connection.get(1);
      adjList.computeIfAbsent(node1, x->new ArrayList<>()).add(node2);
      adjList.computeIfAbsent(node2, x->new ArrayList<>()).add(node1);
    }
    int[] disc = new int[n];
    Arrays.fill(disc, -1);
    int[] low = new int[n];
    Arrays.fill(low, -1);
    int[] parent = new int[n];
    Arrays.fill(parent, -1);
    List<List<Integer>> ans = new ArrayList<>();
    time = 0;
    for(int i=0; i<n; i++){
      if(disc[i]!=-1) continue;
      bridgeDfs(adjList, i, disc, low, parent, ans);
    }
    return ans;
  }

  private static void bridgeDfs(Map<Integer, List<Integer>> adjList, int i, int[] disc, int[] low, int[] parent, List<List<Integer>> ans) {
    disc[i]=time;
    low[i]=time;
    time+=1;
    for(int nei : adjList.getOrDefault(i, new ArrayList<>())){
      if(disc[nei]==-1){ // not discovered
        parent[nei]=i;
        bridgeDfs(adjList, nei, disc, low, parent, ans);
        low[i]=Math.min(low[i], low[nei]);
        if(low[nei]>disc[i]) {
          ans.add(Arrays.asList(i, nei));
        }
      } else if(parent[i]!=nei) { //back edge
        low[i]=Math.min(low[i], disc[nei]);
      }
    }
  }

  // https://www.youtube.com/watch?v=64KK9K4RpKE&t=0s
  // node if removed will increase number of components in the graph
  // application : reliable network
  private static Set<Integer> findAP(int n, List<List<Integer>> connections) {
    Map<Integer, List<Integer>> adjList = new HashMap<>();
    for(List<Integer> connection : connections){
      int node1 = connection.get(0);
      int node2 = connection.get(1);
      adjList.computeIfAbsent(node1, x->new ArrayList<>()).add(node2);
      adjList.computeIfAbsent(node2, x->new ArrayList<>()).add(node1);
    }
    int[] disc = new int[n];
    Arrays.fill(disc, -1);
    int[] low = new int[n];
    Arrays.fill(low, -1);
    int[] parent = new int[n];
    Arrays.fill(parent, -1);
    Set<Integer> ans = new HashSet<>();
    time = 0;
    for(int i=0; i<n; i++){
      if(disc[i]!=-1) continue;
      apDfs(adjList, i, disc, low, parent, ans);
    }
    return ans;
  }

  private static void apDfs(Map<Integer, List<Integer>> adjList, int i, int[] disc, int[] low, int[] parent, Set<Integer> ans) {
    disc[i]=time;
    low[i]=time;
    time+=1;
    int children=0;

    for(int nei:adjList.getOrDefault(i, new ArrayList<>())){
      if(disc[nei]==-1) {//not explored
        parent[nei]=i;
        children++;
        apDfs(adjList, nei, disc, low, parent, ans);
        low[i]=Math.min(low[i], low[nei]);

        if(parent[i]==-1 && children>=2){// Case 1: root node
          ans.add(i);
        }
        if(parent[i]!=-1 && low[nei]>=disc[i]){//Case 2 : subgraph with no back edge
          ans.add(i);
        }
      } else if(parent[i]!=nei) {//back edge, ignore child to parent edge
        low[i] = Math.min(low[i], disc[nei]);
      }

    }
  }

  public static void main(String[] args) {
    Integer[][] connections = {{0,1},{1,2},{1,3},{3,4},
        {4,5},{4,6},{4,0},{5,2},{5,6},{6,5}};
    Integer[][] connections2 ={{1,0},{2,0},{3,2},{2,4},{4,3},{0,3},{4,0}};
    Integer[][] connections3 ={{1,0},{2,0},{3,0},{2,1},{4,3},{5,3}};
    List<List<Integer>> arr = Arrays.stream(connections3)
        .map(Arrays::asList)
        .collect(Collectors.toList());
//    List<List<Integer>> ans = tarjanSCC(5, arr);
//    List<List<Integer>> ans = findBridge(5, arr);
    Set<Integer> ans = findAP(6, arr);
    System.out.println(ans);
  }
}
