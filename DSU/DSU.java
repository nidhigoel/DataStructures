package DSU;

// sample problems
// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/solution/
public class DSU {

  int[] parents;
  int[] size;
  int numOfComp;

  public DSU(int numOfComp) {
    this.numOfComp = numOfComp;
    parents = new int[numOfComp];
    size = new int[numOfComp];
    for (int i = 0; i < numOfComp; i++) {
      parents[i] = i;
      size[i]=1;
    }
  }

  int find(int a) {
    while (parents[a] != a) {
      parents[a] = parents[parents[a]];
      a = parents[a];
    }
    return a;
  }

  public boolean union(int a, int b) {
    int pa = find(a);
    int pb = find(b);

    if (pa == pb) return false;

    if (size[pa] > size[pb]) {
      size[pa] += size[pb];
      parents[pb] = pa;
    } else {
      size[pb] += size[pa];
      parents[pa] = pb;
    }
    numOfComp--;
    return true;
  }
}
