package DSU.problems;

public class RedundantConnection {
  class DSU{
    int components;
    int[] parents;
    int[] size;

    DSU(int N){
      components = N;
      parents = new int[N];
      size = new int[N];
      for(int i=0; i<components; i++){
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

    boolean union(int i, int j){
      int rootX = find(i);
      int rootY = find(j);

      if(rootX == rootY) return false;

      if(size[rootX]>size[rootY]){
        parents[rootY]=rootX;
        size[rootX]+=size[rootY];
      } else {
        parents[rootX]=rootY;
        size[rootY]+=size[rootX];
      }
      components--;
      return true;
    }
  }
  public int[] findRedundantConnection(int[][] edges) {
    DSU dsu = new DSU(edges.length);
    for(int[] E : edges){
      boolean canUnion = dsu.union(E[0]-1,E[1]-1);
      if(!canUnion) return E;
    }
    return null;

  }
}
