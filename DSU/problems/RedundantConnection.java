package DSU.problems;

import DSU.DSU;
import java.util.Arrays;

public class RedundantConnection {

  public static int[] findRedundantConnection(int[][] edges) {
    DSU dsu = new DSU(edges.length);
    for(int[] E : edges){
      boolean canUnion = dsu.union(E[0]-1,E[1]-1);
      if(!canUnion) return E;
    }
    return null;
  }

  public static void main(String[] args) {
    int[][] edges = {{1,2},{2,3},{3,4},{4,5},{1,5}};
    int[] ans = findRedundantConnection(edges);
    System.out.println(Arrays.toString(ans));
  }
}
