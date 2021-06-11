package dp.LCS;

import java.util.Arrays;

public class LongestCommonSubsequence {
  //https://leetcode.com/problems/longest-palindromic-subsequence/
  private static int[][] mem;
  // recursive approach + memoise for (i,j)
  public static int longestCommonSubseq2(String s1, String s2) {
    if(s1.equals(s2))return s1.length();
    mem = new int[s1.length()][s2.length()];
    Arrays.stream(mem).forEach(M->Arrays.fill(M,-1));
    return  lcs(s1,s2,0,0);
  }

  // Helper function for recursive approach
  static int lcs(String a, String b, int posA, int posB){
    if(posA==a.length()||posB==b.length()) return 0;
    // String key = posA + ":" + posB;
    if(mem[posA][posB]!=-1) return mem[posA][posB];

    int max = Integer.MIN_VALUE;
    if(a.charAt(posA)==b.charAt(posB))
      max = Math.max(max, 1+lcs(a,b, posA+1, posB+1));
    else{
      max = Math.max(max, lcs(a,b, posA, posB+1));
      max = Math.max(max, lcs(a,b, posA+1, posB));
    }

    mem[posA][posB] = max;
    return max;
  }

  // iterative
  public static int longestCommonSubseq(String s1, String s2) {
    int N = s1.length();
    int M = s2.length();
    if(s2.equals(s1)) return N;
    if(N<1)return N;
    int[][] dp = new int[N+1][M+1];
    char[] A = s1.toCharArray();
    char[] B = s2.toCharArray();

    // 2. if equal char -> 1+dp[i-1][j-1]
    // else Max(dp[i-1][j],dp[i][j-1])
    for(int i=1; i<=N; i++){
      for(int j=1; j<=M; j++){
        if(A[i-1]==B[j-1])
          dp[i][j]=1+dp[i-1][j-1];
        else {
          dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
        }
      }
    }
    return dp[N][N];
  }

  // TODO extract longest common subsequence
  static String longestCommonSubSequence(String A, String B) {
    if(A.isEmpty() || B.isEmpty()) return "";
    if(A.equals(B)) return A;
    int na = A.length();
    int nb = B.length();
    char [] AA = A.toCharArray();
    char [] BB = B.toCharArray();

    int [][] dp = new int[na+1][nb+1];
    for(int i=1; i <= na; ++i) {
      for(int j=1; j <= nb; ++j) {
        if(AA[i-1] == BB[j-1]) {
          dp[i][j] = 1+dp[i-1][j-1];
        } else {
          dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }
      }
    }

    String cs = "";
    int max = dp[na][nb];
    // retrieve now
    //~ start at the end of both string if char do not match then
    // move towards higher value
    int i = na, j=nb;
    while (i > 0 && j > 0) {
      if(AA[i-1] == BB[j-1]) {
        cs = AA[i-1]+cs;
        --i; --j;
      } else if(dp[i-1][j] > dp[i][j-1]) {
        --i;
      } else {
        --j;
      }
    }
    return cs;
  }

  public static void main(String[] args) {
//    int ans = longestCommonSubseq("abcdcgb", "abdcdbsk");
    int ans = longestCommonSubseq2("abcdcgb", "abdcdbsk");
    System.out.println(ans);
  }
}
