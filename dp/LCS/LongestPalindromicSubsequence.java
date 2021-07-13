package dp.LCS;

import java.util.Arrays;

//https://leetcode.com/problems/longest-palindromic-subsequence/
public class LongestPalindromicSubsequence {
  private int[][] mem;
  // recursive approach
  // invoke recursion with string, reverse of string
  // memoise for i,j
  public int longestPalindromeSubseq2(String s) {
    String y = "";
    for(int i=0; i<s.length(); i++){
      y = s.charAt(i) + y;
    }
    // if(s.equals(y))return s.length();
    mem = new int[s.length()][s.length()];
    Arrays.stream(mem).forEach(M->Arrays.fill(M,-1));

    return  lcs(s,y,0,0);
  }

  // Helper function for recursive approach
  int lcs(String a, String b, int posA, int posB){
    if(posA==a.length()||posB==b.length()) return 0;
    // String key = posA + ":" + posB;
    if(mem[posA][posB]!=-1) return mem[posA][posB];

    int max = Integer.MIN_VALUE;
    if(a.charAt(posA)==b.charAt(posB))
      max = Math.max(max, 1+lcs(a,b, posA+1, posB+1));
      // else
      //     max = Math.max(max, lcs(a,b, posA+1, posB+1));
    else{
      max = Math.max(max, lcs(a,b, posA, posB+1));
      max = Math.max(max, lcs(a,b, posA+1, posB));
    }

    mem[posA][posB] = max;
    return max;
  }

  // iterative
  public static int longestPalindromeSubseq(String s) {
    int N = s.length();
    if(N<1)return N;
    int[][] dp = new int[N+1][N+1];
    char[] A = s.toCharArray();

    // 1. reverse the string
    String y = "";
    for(int i=0; i<s.length(); i++){
      y = s.charAt(i) + y;
    }
    if(s.equals(y))return N;
    char[] B = y.toCharArray();

    // 2. if equal char -> 1+dp[i-1][j-1]
    // else Max(dp[i-1][j],dp[i][j-1])
    for(int i=1; i<=N; i++){
      for(int j=1; j<=N; j++){
        if(A[i-1]==B[j-1])
          dp[i][j]=1+dp[i-1][j-1];
        else {
          dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
        }
      }
    }
    return dp[N][N];
  }

  public static void main(String[] args) {
    int ans = longestPalindromeSubseq("abcdcgb");
    System.out.println(ans);
  }

}
