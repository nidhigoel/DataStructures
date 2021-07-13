package strings;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/minimum-window-substring/
//Sliding Window Concept
public class MinimumWindowSubstring {
  public static String minWindow(String s, String t) {
    int i=0;
    int j=-1;
    int n=s.length();
    Map<Character,Integer> map = new HashMap<>();
    for(Character c:t.toCharArray()){
      map.put(c, map.getOrDefault(c,0)+1);
    }
    int count=map.size();
    int posA = -1;
    int posB = -1;
    char[] arr = s.toCharArray();
    int ans = Integer.MAX_VALUE;

    while(i<n && j<n){
      while(count>0){
        j++;
        if(j>=n) break;
        char ch = arr[j];
        if(map.containsKey(ch)){
          int newCount= map.get(ch)-1;
          map.put(ch,newCount);
          if(newCount==0) count--;
        }
        if(count==0 && j-i+1<ans){
          ans = j-i+1;
          posA = i;
          posB = j;
        }
      }
      while(count==0){
        i++;
        if(i>=n) break;
        char ch = arr[i-1];
        if(map.containsKey(ch)){
          int newCount= map.get(ch)+1;
          map.put(ch,newCount);
          if(newCount>0) count++;
        }
        if(count==0 && j-i+1<ans){
          ans = j-i+1;
          posA = i;
          posB = j;
        }
      }

    }

    if(posA==-1||posB==-1)return "";
    return s.substring(posA,posB+1);
  }

  // different style of coding
  public String minWindow2(String s, String t) {
    int n = s.length();
    char[] ar = s.toCharArray();
    //window
    int left=0;
    int right=0;
    //answer
    int minLen = Integer.MAX_VALUE;
    int start = -1;
    //store
    Map<Character,Integer> map = new HashMap<>();

    for(Character c : t.toCharArray()){
      map.put(c,map.getOrDefault(c,0)+1);
    }

    int count = map.size();

    //start processing window
    while(right<n){
      char ch = ar[right];
      if(map.containsKey(ch)){
        int update = map.get(ch)-1;
        map.put(ch,update);
        if(update==0)count--;
      }

      while(count==0){
        if(right-left+1<minLen){
          minLen = right-left+1;
          start=left;
        }
        left++;
        char chToRemove = ar[left-1];
        if(map.containsKey(chToRemove)){
          int update = map.get(chToRemove)+1;
          map.put(chToRemove,update);
          if(update>0)count++;
        }
      }
      right++;

    }
    if(start == -1)return "";
    return s.substring(start, start+minLen);

  }

  public static void main(String[] args) {
    String ans = minWindow("ADOBECODEBANC", "ABC");
    System.out.println(ans);
  }
}
