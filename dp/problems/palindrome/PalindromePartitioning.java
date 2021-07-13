package dp.problems.palindrome;

public class PalindromePartitioning {
  static boolean[][] dp;

  public static int minCut(String a){
    int N = a.length();
    dp = new boolean[N][N];
    initDP(a);


    int[] strg = new int[N];
    strg[0]=0;
    for(int j=1; j<N; j++){
      if(dp[0][j]){
        strg[j]=0;
      } else {
        int min = Integer.MAX_VALUE;
        for(int i=j; i>=1; i--){
          if(dp[i][j])
            min=Math.min(min, strg[i-1]+1);
        }
        strg[j]=min;
      }
    }
    return strg[N-1];
  }


  static void initDP(String a){
    int N = a.length();
    char[] A = a.toCharArray();
    for(int l=1; l<=N; l++){
      for(int i=0, j=i+l-1; i<N&&j<N; i++,j++){
        if(A[i]==A[j]) {
          if(l<=2) {
            dp[i][j]=true;
          }
          else if(dp[i+1][j-1]){
            dp[i][j]=true;
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(minCut("abcb"));
  }
}
