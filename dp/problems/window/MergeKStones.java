package dp.problems.window;

//https://leetcode.com/problems/minimum-cost-to-merge-stones/
public class MergeKStones {

  public int mergeStones(int[] stones, int k) {
    int N = stones.length;
    if((N-1)%(k-1)!=0) return -1;
    int[][] dp = new int[N][N];

    int[] cumsum = new int[N];
    cumsum[0]=stones[0];
    for(int i=1; i<N; i++) cumsum[i]=cumsum[i-1]+stones[i];

    // l is the window size
    for(int l=k; l<=N; l++){
      // x and y are the left and right boundary of window
      for(int x = 0, y=x+l-1 ; x<N && y<N; x++,y++){
        if(l==k) {
          dp[x][y]=cumsum[y]-(x!=0?cumsum[x-1]:0);
          continue;
        }
        int min = Integer.MAX_VALUE;
        //divide into left pile and right pile at k-1 jumps
        for(int mid = x; mid<y; mid+=k-1){
          min=Math.min(min,dp[x][mid]+dp[mid+1][y]);
        }
        dp[x][y]=min;
        // if it is full pile add more cost
        if((l-1)%(k-1)==0) dp[x][y]+=cumsum[y]-(x!=0?cumsum[x-1]:0);

      }
    }
    return dp[0][N-1];
  }
}
