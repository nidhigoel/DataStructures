package algos.bitmanipulation.problems;

import java.util.Arrays;

//https://leetcode.com/problems/find-longest-awesome-substring
public class LongestAwesomeSubstring {
  // 324542
  // 000001000,0
  // 000001100,1
  // 000011100,2
  // 000111100,3
  // 000101100,4
  // 000101000,5
  // 350844
  // 0000001000
  // 0000101000
  // 0000101001
  // 0100101001
  // 0100111001
  // 0100101001
  public int longestAwesome(String s) {
    int N = s.length();
    int mask = 0;

    int ans = 1;
    int[] mem = new int[1024];
    Arrays.fill(mem,N);
    mem[mask]=-1;

    for(int i=0; i<s.length(); i++){
      int d = s.charAt(i)-'0';
      mask = mask ^ (1<<d);

      for(int j=0; j<=9; j++){
        int flip = mask ^ (1<<j);
        if(mem[flip]!=N) {
          ans = Math.max(ans,i-mem[flip]);
        }
      }
      if(mem[mask]!=N){
        ans = Math.max(ans,i-mem[mask]);
      } else mem[mask]=i;
    }
    return ans;

  }
}
