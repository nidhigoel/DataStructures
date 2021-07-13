package dp.LCS;

public class LongestPalindromicSubstring {
  public static int minCut(String a) {
    int N = a.length();
    if(N<=1)return 0;
    boolean[][] dp = new boolean[N][N];
    if(isPal(a)) return 0;
    int max = 0;
    String ans = "";
    int start = 0;
    int end = 0;
    char[] A = a.toCharArray();
    for(int l=1; l<=N; l++){
      for(int i=0, j=i+l-1; i<N&&j<N; i++,j++){
        // char a = a.charAt(i);
        // char b = a.charAt(i);
        if(A[i]==A[j]) {
          if(l<=2) {
            dp[i][j]=true;
            start=i;
            end = j+1;
          }
          else if(dp[i+1][j-1]){
            dp[i][j]=true;
            start=i;
            end = j+1;
          }
        }
      }
    }
    // abc
    //     a start:0 end:1
    //     c start:2 end:3
    if(start==0) return 1 + minCut(a.substring(end,N));
    if(end==N) return 1 + minCut(a.substring(0,start));
    return 2 + minCut(a.substring(0,start)) + minCut(a.substring(end,N));
  }

  // https://leetcode.com/problems/longest-palindromic-substring/
  public static String longestPalindrome(String a) {
    int N = a.length();
    if(N<1)return a;
    boolean[][] dp = new boolean[N][N];
    if(isPal(a)) return a;
    int max = 0;
    String ans = "";
    int start = 0;
    int end = 0;
    char[] A = a.toCharArray();
    for(int l=1; l<=N; l++){ // gap strategy
      for(int i=0, j=i+l-1; i<N&&j<N; i++,j++){
        // char a = a.charAt(i);
        // char b = a.charAt(i);
        if(A[i]==A[j]) {
          if(l<=2) {
            dp[i][j]=true;
            start=i;
            end = j+1;
          }
          else if(dp[i+1][j-1]){
            dp[i][j]=true;
            start=i;
            end = j+1;
          }
        }
      }
    }
    return a.substring(start,end);
  }

  static boolean isPal(String temp){
    return temp.equals(new StringBuilder(temp).reverse().toString());
  }

  public static void main(String[] args) {
    System.out.println(minCut("adabdcaebdcebdcacaaaadbbcadabcbeabaadcbcaaddebdbddcbdacdbbaedbdaaecabdceddccbdeeddccdaabbabbdedaaabcdadbdabeacbeadbaddcbaacdbabcccbaceedbcccedbeecbccaecadccbdbdccbcbaacccbddcccbaedbacdbcaccdcaadcbaebebcceabbdcdeaabdbabadeaaaaedbdbcebcbddebccacacddebecabccbbdcbecbaeedcdacdcbdbebbacddddaabaedabbaaabaddcdaadcccdeebcabacdadbaacdccbeceddeebbbdbaaaaabaeecccaebdeabddacbedededebdebabdbcbdcbadbeeceecdcdbbdcbdbeeebcdcabdeeacabdeaedebbcaacdadaecbccbededceceabdcabdeabbcdecdedadcaebaababeedcaacdbdacbccdbcece"));
  }
}
