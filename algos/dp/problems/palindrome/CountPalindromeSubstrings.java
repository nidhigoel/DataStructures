package algos.dp.problems.palindrome;

public class CountPalindromeSubstrings {
  public int countSubstrings(String s) {
    int N = s.length();
    boolean[][] dp = new boolean[N][N];

    int ans = 0;
    char[] S = s.toCharArray();

    for(int w=0; w<N; w++){
      for(int i=0, j=i+w; j<N; i++,j++){
        if(S[i]==S[j]){
          if(w<2){
            dp[i][j]=true;
            ans++;
          } else if(dp[i+1][j-1]){
            dp[i][j]=true;
            ans++;
          }
        }
      }
    }
    return ans;

  }
}
