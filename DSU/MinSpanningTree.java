package DSU;

import com.sun.javafx.geom.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// https://leetcode.com/problems/min-cost-to-connect-all-points/
// finiding minimum spanning tree using kruskal's algorithm
// using disjoint set union to find cycle in graph
public class MinSpanningTree {
  static class DSU{
    public int noOfComponents;
    public int[] parents;
    public int[] size;

    public static DSU of(int noOfComponents){
      return new DSU(noOfComponents);
    }

    DSU(int noOfComponents){
      this.noOfComponents = noOfComponents;
      parents = new int[noOfComponents];
      size = new int[noOfComponents];
      for(int i=0; i<noOfComponents; i++){
        parents[i]=i;
        size[i]=1;
      }
    }

    public int find(int x){
      while(parents[x]!=x){
        parents[x]=parents[parents[x]];
        x = parents[x];
      }
      return x;
    }

    public boolean union(int x, int y){
      int rootX = find(x);
      int rootY = find(y);

      if(rootX == rootY) return false;

      if(size[rootX]<size[rootY]){
        parents[rootX] = rootY;
        size[rootY]+=size[rootX];
      }else {
        parents[rootY] = rootX;
        size[rootX]+=size[rootY];
      }
      noOfComponents--;
      return true;
    }
  }

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
    PriorityQueue<Edge> heap = new PriorityQueue<>(Comparator.comparing(Edge::getCost));

    for(int i=0 ; i<n; i++){
      for(int j=i+1; j<n; j++){
        heap.add(new Edge(i,j, getDistance(points, i, j)));
      }
    }

    DSU dsu = DSU.of(n);
    int cost = 0;
    while(dsu.noOfComponents!=1){
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
