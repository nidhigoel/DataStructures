package Graph.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/alien-dictionary
// Topological Sort
public class AlienDictionary {
  public String alienOrder(String[] words) {
    int n = words.length;
    if(n==0) return "";
    if(n==1) return words[0];
    Map<Character, List<Character>> graph = new HashMap<>();
    Set<Character> allChars = new HashSet<>();
    int[] indegree = new int[26];
    for(int i=1; i<n; i++){
      char[] w1=words[i-1].toCharArray();
      char[] w2=words[i].toCharArray();
      for(char c : w1)
        allChars.add(c);
      for(char c : w2)
        allChars.add(c);
      int pos=0;
      for(char c : w1){
        if(pos==w2.length) return ""; // invalid usecase
        if(c == w2[pos]) {
          // System.out.println(c+" "+pos);
          pos++;
          continue;
        }
        break;
      }
      // System.out.println(pos);
      if(pos==w1.length) continue;
      char src = w1[pos];
      char dst = w2[pos];
      // System.out.println(src+" "+dst);
      if(!graph.containsKey(src)) graph.put(src, new ArrayList<>());
      graph.get(src).add(dst);
      indegree[dst-'a']++;
    }

    Queue<Character> queue = new LinkedList<>();
    List<Character> allCharsList = new ArrayList<>();
    allCharsList.addAll(allChars);
    for(Character c : allCharsList){
      if(indegree[c-'a']==0) queue.add(c);
    }
    String ans = "";
    boolean[] visited = new boolean[26];

    while(!queue.isEmpty()){
      Character ch = queue.remove();
      visited[ch-'a']=true;
      ans+=ch;
      List<Character> children = graph.getOrDefault(ch, new ArrayList<>());
      for(Character child: children){
        indegree[child-'a']--;
        if(indegree[child-'a']==0) {
          if(visited[child-'a']) return ""; // cycle found
          queue.add(child);
        }

      }
    }
    // System.out.println(ans);
    return ans.length()==allChars.size()?ans:"";

  }
}
