package Graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlternatingColor {
  static class Node{
    int vert;
    int distance;
    int colour; //1 for red 2 for blue

    Node(int vert, int distance, int colour){
      this.vert = vert;
      this.distance = distance;
      this.colour = colour;
    }
  }

  public static int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
    Map<Integer, Set<Integer>> redGraph = new HashMap<>();
    Map<Integer, Set<Integer>> blueGraph = new HashMap<>();

    initGraph(red_edges, redGraph);
    initGraph(blue_edges, blueGraph);

    Queue<Node> queue = new LinkedList<>();
    boolean[] visited = new boolean[n];

    queue.add(new Node(0,0,1));
    queue.add(new Node(0,0,2));
    visited[0]=true;

    int[] ans = new int[n];
    Arrays.fill(ans, -1);

    while (!queue.isEmpty()){
      Node node = queue.poll();
      ans[node.vert] = (ans[node.vert] == -1) ? node.distance : Math.min(ans[node.vert], node.distance);
      if(node.colour==1){
        addNodes(queue, blueGraph.get(node.vert), visited, 2, node.distance+1);
      } else {
        addNodes(queue, redGraph.get(node.vert), visited, 1, node.distance+1);
      }
    }

    return ans;
  }

  private static void addNodes(Queue<Node> queue, Set<Integer> integers, boolean[] visited, int color, int dist) {
    if(integers==null) return;
    integers.stream().filter(vert -> !visited[vert])
        .forEach(vert ->{
          visited[vert] = true;
          queue.add(new Node(vert, dist, color));
        });
  }

  public static void initGraph(int[][] edges, Map<Integer, Set<Integer>> graph){
    for(int[] edge: edges){
      int source = edge[0];
      int dest = edge[1];

      if(graph.containsKey(source)){
        graph.get(source).add(dest);
      } else {
        graph.put(source,new HashSet<Integer>(){{add(dest);}});
      }
    }
    String s = "acs";

  }

  public static void main(String[] args) {
    int n = 5;
    int[][] red_edges = {{0,1},{1,2},{2,3},{3,4}};
    int[][] blue_edges = {{1,2},{2,3},{3,1}};
    int[] ans = shortestAlternatingPaths(n, red_edges, blue_edges);
    System.out.println(Arrays.toString(ans));
  }
}
