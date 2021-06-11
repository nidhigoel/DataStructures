package dp.LCS;

public class LongestCommonSubstring {
  public static int longestCommonSubstring(String s1, String s2) {
    int N = s1.length();
    int M = s2.length();
    if(s2.equals(s1)) return N;
    if(N<1)return N;
    int[][] dp = new int[N+1][M+1];
    char[] A = s1.toCharArray();
    char[] B = s2.toCharArray();

    int max = 0;
    // 2. if equal char -> 1+dp[i-1][j-1]
    // else Max(dp[i-1][j],dp[i][j-1])
    for(int i=1; i<=N; i++){
      for(int j=1; j<=M; j++){
        if(A[i-1]==B[j-1])

          dp[i][j]=1+dp[i-1][j-1];
          max = Math.max(max, dp[i][j]);
//        else {
//          dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
//        }
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int ans = longestCommonSubstring("abcdcgb", "ambdbcdbsk");
    System.out.println(ans);
  }
}
