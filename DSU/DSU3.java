package DSU;

public class DSU3 {

  int[] parents;
  int numOfComp;

  public DSU3(int numOfComp) {
    this.numOfComp = numOfComp;
    parents = new int[numOfComp];
    for (int i = 0; i < numOfComp; i++) {
      parents[i] = -1;
    }
  }

  int find(int a) {
    while (parents[a] >= 0) {
      if (parents[parents[a]] >= 0) parents[a] = parents[parents[a]];
      a = parents[a];
    }
    return a;
  }

  public boolean union(int a, int b) {
    int pa = find(a);
    int pb = find(b);

    if (pa == pb) return false;

    if (parents[pa] < parents[pb]) {
      parents[pa] += parents[pb];
      parents[pb] = pa;
    } else {
      parents[pb] += parents[pa];
      parents[pa] = pb;
    }
    numOfComp--;
    return true;
  }
}
