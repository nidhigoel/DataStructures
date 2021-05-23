package algos.dp.problems;

public class EditDistance {

  private static int editDistance(int i, int j, String strA, String strB) {
    if (i == strA.length()) return  strB.length()-j;
    if (j == strB.length()) return  strA.length()-i;

    if (strA.charAt(i) == strB.charAt(j)) return editDistance(i + 1, j + 1, strA, strB);
    else return 1 + Math.min(Math.min(
        //insert
        editDistance(i, j + 1, strA, strB),
        //replace
        editDistance(i + 1, j + 1, strA, strB)),
        //remove
        editDistance(i + 1, j, strA, strB));
  }

  private static int editDistanceDP(String strA, String strB){
    int[][] dp = new int[strA.length()+1][strB.length()+1];
    for(int i=0; i<=strA.length(); i++){
      for(int j=0; j<=strB.length(); j++) {
        if(i==0) {dp[i][j]=j; continue;}
        if(j==0) {dp[i][j]=i; continue;}
        if (strA.charAt(i-1) == strB.charAt(j-1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          int ans = Math.min(Math.min(
              //replace
              dp[i - 1][j - 1],
              //remove
              dp[i - 1][j]),
              //insert
              dp[i][j - 1]);
          dp[i][j] = 1+ans;
        }
      }
    }

    return dp[strA.length()][strB.length()];

  }

  public static void main(String[] args) {
    String a = "Saturday";
    String b = "Sunday";
//    int ans = editDistance(0, 0, a, b);
    int ans = editDistanceDP( a, b);
    System.out.println(ans);
  }

}

