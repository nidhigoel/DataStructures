package DSU;

import java.util.PriorityQueue;

// https://www.hackerearth.com/practice/data-structures/disjoint-data-strutures/basics-of-disjoint-data-structures/tutorial/
// https://leetcode.com/problems/min-cost-to-connect-all-points/
// finiding minimum spanning tree using kruskal's algorithm
// using disjoint set union to find cycle in graph
public class MinSpanningTree {
  static class Edge{
    int i;
    int j;
    int length;

    Edge(int i, int j, int length){
      this.i=i;
      this.j=j;
      this.length=length;
    }

    public int getCost(){
      return length;
    }
  }

  public static int minCostConnectPoints(int[][] points) {
    int n = points.length;
    PriorityQueue<Edge> heap = new PriorityQueue<>((a,b)->b.getCost()-a.getCost());
    for(int i=0 ; i<n; i++){
      for(int j=i+1; j<n; j++){
        heap.add(new Edge(i,j, getDistance(points, i, j)));
      }
    }

    DSU dsu = new DSU(n);
    int cost = 0;
    while(dsu.numOfComp!=1){
      Edge edge = heap.poll();
      boolean union = dsu.union(edge.i,edge.j);
      if(union) cost+=edge.length;
    }
    return cost;
  }

  public static void main(String[] args) {
//    int[][] points = {{3,12},{-2,5},{-4,1}};
    int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
    int ans = minCostConnectPoints(points);
    System.out.println(ans);
  }

  private static int getDistance(int[][] points, int i, int j){
    int[] pointA = points[i];
    int[] pointB = points[j];
    return Math.abs(pointB[1]-pointA[1])+Math.abs(pointB[0]-pointA[0]);
  }
}
