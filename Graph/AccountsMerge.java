package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// Approach 1 : Using DSU
// Approach 2 : Using DFS
public class AccountsMerge {

    public List<List<String>> accountsMerge1(List<List<String>> accounts) {

      Map<String,String> emailToName = new HashMap<>();
      Map<String, List<String>> emailGraph = new HashMap<>();

      for(List<String> acc : accounts) {
        String name = acc.get(0);
        String email1 = acc.get(1);
        emailToName.put(email1,name);
        if(!emailGraph.containsKey(email1)) {
          emailGraph.put(email1, new ArrayList<>());
        }
        for(int i=2; i<acc.size(); i++) {
          String pEmail = acc.get(i-1);
          String email = acc.get(i);
          emailToName.put(email,name);
          if(!emailGraph.containsKey(email)) {
            emailGraph.put(email, new ArrayList<>());
          }
          emailGraph.get(email).add(pEmail);
          if(!emailGraph.containsKey(pEmail)) {
            emailGraph.put(pEmail, new ArrayList<>());
          }
          emailGraph.get(pEmail).add(email);
        }

      }
      List<List<String>> ans = new ArrayList<>();

      Set<String> visited = new HashSet<>();
      for(String email : emailGraph.keySet()){
        if(visited.contains(email)) continue;
        Set<String> allEmails = new TreeSet<>();
        dfs(emailGraph, email, visited, allEmails);
        List<String> account = new ArrayList<>();
        account.add(emailToName.get(email));
        account.addAll(allEmails);
        ans.add(account);
      }
      return ans;

    }
    void dfs(Map<String, List<String>> emailGraph, String email, Set<String> visited, Set<String> emails){
      if(visited.contains(email)) return;
      List<String> children = emailGraph.get(email);
      visited.add(email);
      emails.add(email);
      for(String child : children){
        dfs(emailGraph, child, visited, emails);
      }
    }

  static class DSU{
    int components;
    int[] parents;
    int[] size;

    DSU(int N){
      components = N;
      parents = new int[N];
      size = new int[N];
      for(int i=0; i<components; i++){
        parents[i]=i;
        size[i]=1;
      }
    }

    public int find(int x){
      while(parents[x]!=x){
        parents[x]=parents[parents[x]];
        x = parents[x];
      }
      return x;
    }

    boolean union(int i, int j){
      int rootX = find(i);
      int rootY = find(j);

      if(rootX == rootY) return false;

      if(size[rootX]>size[rootY]){
        parents[rootY]=rootX;
        size[rootX]+=size[rootY];
      } else {
        parents[rootX]=rootY;
        size[rootY]+=size[rootX];
      }
      components--;
      return true;
    }
  }
  public static List<List<String>> accountsMerge(List<List<String>> accounts) {
    Map<String,Integer> emailToIndex = new HashMap<>();
    Map<String,String> emailToName = new HashMap<>();
    int N=0;
    for(List<String> acc :  accounts){
      String name = acc.get(0);
      for(int i =1; i<acc.size(); i++){
        String email = acc.get(i);
        if(emailToIndex.containsKey(email)) continue;
        emailToIndex.put(email,N);
        emailToName.put(email,name);
        N++;
      }
    }

    DSU dsu = new DSU(N);
    for(List<String> acc :  accounts){
      for(int i =2; i<acc.size(); i++){
        dsu.union(emailToIndex.get(acc.get(i)),emailToIndex.get(acc.get(1)));
      }
    }

    Map<Integer, List<String>> mergedEmails = new HashMap<>();

    for(Map.Entry<String,Integer> entry :  emailToIndex.entrySet()){

      String email = entry.getKey();
      int index = entry.getValue();

      int parent = dsu.find(index);

      if(!mergedEmails.containsKey(parent))mergedEmails.put(parent, new ArrayList<>());
      mergedEmails.get(parent).add(email);

    }

    List<List<String>> ans = new ArrayList<>();
    for(List<String> emails :  mergedEmails.values()){
      Collections.sort(emails);
      emails.add(0,emailToName.get(emails.get(0)));
      ans.add(emails);
    }
    return ans;

  }

  public static void main(String[] args) {
    List<List<String>> accounts = new ArrayList<>();
    accounts.add(Arrays.asList(new String[]{"John", "johnsmith@mail.com", "john_newyork@mail.com"}));
    accounts.add(Arrays.asList(new String[]{"Mary","mary@mail.com"}));
    accounts.add(Arrays.asList(new String[]{"John","johnnybravo@mail.com"}));
    accounts.add(Arrays.asList(new String[]{"John","johnnybravo@mail.com"}));
    List<List<String>> ans = accountsMerge(accounts);
    System.out.println(ans);
  }
}
