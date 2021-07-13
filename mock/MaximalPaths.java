package mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/*  ----------------------------------------
      |            |            |            |
      |            |            |            |
      |     ==============      |            |
      |            |     ॥      |            |
      |            |     ॥      |            |
      -------------------॥--------------------
      |            |     ॥      |            |
      |            |     ॥      |            |
      |            |     ॥      |            |
      |            |     ॥      |            |
      |            |     ॥      |            |
      -------------------॥--------------------
      |            |     ॥      |            |
      |            |     ॥      |            |
      |      =============      |            |
      |            |            |            |
      |            |            |            |
      ----------------------------------------


      ----------------------------------------
      |            |            |            |
      |            |            |            |
      |     ==============      |            |
      |            |     ॥      |            |
      |            |     ॥      |            |
      -------------------॥--------------------
      |            |     ॥      |            |
      |            |     ॥      |            |
      |      ॥     |     ॥      |            |
      |      ॥     |     ॥      |            |
      |      ॥     |     ॥      |            |
      -------॥-----------॥--------------------
      |      ॥     |     ॥      |            |
      |      ॥     |     ॥      |            |
      |      =============      |            |
      |            |            |            |
      |            |            |            |
      ----------------------------------------
  /**
   1. interface - return type, how to represent path
   2. implementation

   positive integers
   Set<int[][]> paths =  [[0,0],[0,1][1,1],[2,1],[2,0],[1,0]]


   always start at top left

   DFS
   - visited
   - break condition
   - curPath
   - List<Path>
   - updating visited while backtracking

   **/

/*0,0,[00,00],[],{}

// 0 0 0
// 0 0 0
// 0 0 0

// Tc :- O(n^2)

*-*-*-
    -  |
    | | |
    - |
    [
    [0,0][0,1][0,2][1,2][2,2][2,1][1,1][0,1][0,2]

    ]

    0,1
    0,2
    1,2
*/

public class MaximalPaths {


  public class Cell {
    int row;
    int col;

    Cell(int row, int col){
      this.row=row;
      this.col=col;
    }
    @Override
    public String toString() {
      return row+":"+col;
    }
  }

  private int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

  Set<List<Cell>> getAllMaxPaths(int n){
    if(n==0) return new HashSet<>();
    boolean[][] visited = new boolean[n][n];
    Set<List<Cell>> ans = new HashSet<>();
    List<Cell> curPath = new ArrayList<>();
    getAllMaxPathsDFS(0, 0, visited, curPath, ans);
    return ans;

  }

  int cnt =0;
  void getAllMaxPathsDFS(int i, int j, boolean[][] visited, List<Cell> curPath, Set<List<Cell>> ans){
    System.out.println("#"+ cnt++);
    visited[i][j] =true;
    curPath.add(new Cell(i,j));
    boolean isTerminatingCell = true;
    for(int[] d:dir){
      int i1 = i+d[0];
      int j1 = j+d[1];

      if(i1<0 || i1>=visited.length || j1<0 || j1>=visited.length || visited[i1][j1]) continue;
      isTerminatingCell=false;
      getAllMaxPathsDFS(i1, j1, visited, curPath, ans);

    }
    if(isTerminatingCell){
      List<Cell> temp = new ArrayList<>();
      temp.addAll(curPath);
      ans.add(temp);
    }
    curPath.remove(curPath.size()-1); // remove last index here
    visited[i][j] =false;

  }

  public static void main(String[] args) {
    MaximalPaths mp = new MaximalPaths();
    Set<List<Cell>> ans = mp.getAllMaxPaths(5);
    System.out.println(ans);
    System.out.println(ans.size());
  }
}
