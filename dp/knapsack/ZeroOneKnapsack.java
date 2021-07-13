package dp.knapsack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

//https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
// zero one knapsack approach - not accepted
public class ZeroOneKnapsack {
  static String ans = "";

  // using zero one knapsack
  // Time complexity 2^n - TLE
  public static String smallestSubsequence(String s) {
    Set<Character> distint = new HashSet<>();
    for(int i=0; i<s.length();i++){
      distint.add(s.charAt(i));
    }
    backtrack(s, 0, "", new boolean[26], distint.size());
    return ans;
  }

  public static void backtrack(String s, int pos, String tillNow, boolean[] visited, int remaining){
    if(remaining==0 && isSmall(tillNow,ans)){
      ans = tillNow;
      return;
    }
    if(pos==s.length()) return;

    char ch = s.charAt(pos);
    //1. take it
    if(!visited[ch-'a']){
      visited[ch-'a'] = true;
      remaining--;
      backtrack(s, pos+1, tillNow+ch, visited, remaining);
      visited[ch-'a'] = false;
      remaining++;
    }
    //2. not take it
    backtrack(s, pos+1, tillNow, visited, remaining);
  }

  static boolean isSmall(String tillNow, String ans){
    if(ans == "") return true;
    for(int i=0; i<ans.length();i++){
      if(tillNow.charAt(i)<ans.charAt(i)) return true;
      if(tillNow.charAt(i)>ans.charAt(i)) return false;
    }
    return true;
  }

  // greedy approach using stack
  public static String smallestSubsequence2(String s) {
    Stack<Character> st = new Stack<>();
    boolean[] vis = new boolean[26];

    int[] lastPos = new int[26];
    for(int i=0; i<s.length(); i++){
      lastPos[s.charAt(i)-'a']=i;
    }

    for(int i=0; i<s.length(); i++){
      char ch = s.charAt(i);
      if(vis[ch-'a']) continue;
      while(!st.empty() && st.peek()>ch && lastPos[st.peek()-'a']>i){
        vis[st.peek()-'a'] = false;
        st.pop();
      }
      vis[ch-'a'] = true;
      st.push(ch);
    }

    String ans = "";
    while(!st.empty()){
      ans = st.pop()+ans;
    }
    return ans;
  }

  public static void main(String[] args) {
//    int [] C = {1,2,3,4,2,5,6};
    ans = smallestSubsequence2("dbbbabadcdcbdaddddbbcbdaaadbdaadcaaabbab");
//    ans = smallestSubsequence("dbbbabadcdcbdaddddbbcbdaaadbdaadcaaabbab");
    System.out.println(ans);
  }

}
