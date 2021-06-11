package dp.knapsack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class ZeroOneKnapsack {
  static String ans = "";

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

  public static String smallestSubsequence1(String s) {
    Stack<Character> st = new Stack<>();
    boolean[] vis = new boolean[26];

    int[] lastPos = new int[26];
    for(int i=0; i<s.length(); i++){
      lastPos[s.charAt(i)-'a']=i;
    }

    for(int i=0; i<s.length(); i++){
      char ch = s.charAt(i);

      // if(vis[ch-'a'] && !st.empty() && st.peek()<=ch) continue;
      while(!st.empty() && st.peek()>ch && lastPos[st.peek()-'a']>i){
        vis[st.peek()-'a'] = false;
        st.pop();
      }
      if(!vis[ch-'a']){
        vis[ch-'a'] = true;
        st.push(ch);
        continue;
      }
      // st.push(ch);
    }

    String ans = "";
    while(!st.empty()){
      ans = st.pop()+ans;
    }
    return ans;

  }


  public static String smallestSubsequence2(String s) {
    Set<Character> distint = new HashSet<>();
    for(int i=0; i<s.length();i++){
      distint.add(s.charAt(i));
    }

    LinkedList<Character> st = new LinkedList<>();
    boolean[] vis = new boolean[26];

    int[] lastPos = new int[26];
    for(int i=0; i<s.length(); i++){
      lastPos[s.charAt(i)-'a']=i;
    }

    for(int i=0; i<s.length(); i++){

      if(st.size()==distint.size() && ans=="") {
        String cur = getString(st);
        ans = cur;
      }
      else if(st.size()==distint.size()) {
        String cur = getString(st);
        if(cur.compareTo(ans)<0) ans = cur;
      }
      char ch = s.charAt(i);

      // if(vis[ch-'a'] && !st.empty() && st.peek()<=ch) continue;
      while(!st.isEmpty() && st.peekLast()>ch && lastPos[st.peekLast()-'a']>i){
        vis[st.peekLast()-'a'] = false;
        st.removeLast();
      }
      if(!vis[ch-'a']){
        vis[ch-'a'] = true;
        st.addLast(ch);
        continue;
      }
      // st.push(ch);
    }
//    String cur = getString(st);
    if(st.size()==distint.size() && ans=="") {
      String cur = getString(st);
      ans = cur;
    }
    else if(st.size()==distint.size()) {
      String cur = getString(st);
      if(cur.compareTo(ans)<0) ans = cur;
    }
    return ans;
  }

  static String getString(LinkedList<Character> st){
    String ans = "";
    for(Character ch : st) {
      ans =  ans + ch;
    }
    return ans;
  }

  public static void main(String[] args) {
//    int [] C = {1,2,3,4,2,5,6};
    ans = smallestSubsequence2("dbbbabadcdcbdaddddbbcbdaaadbdaadcaaabbab");
    System.out.println(ans);
  }

}
