package MiscProb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//TODO
public class AccountsMerge {
  public static List<List<String>> accountsMerge(List<List<String>> accounts) {
    Map<String, List<Set<String>> > mem= new HashMap<>();
    for(List<String> account : accounts){
      if(!mem.containsKey(account.get(0))) mem.put(account.get(0), new ArrayList<>());
      boolean isDifferent = true;
      Set<String> set = new HashSet<>();
      int ind = -1;
      for(int j=1; j<account.size(); j++){
        String cur = account.get(j);
        set.add(cur);
        List<Set<String>> emails = mem.get(account.get(0));
        int pos=0;
        for(Set<String> email : emails){
          if(email.contains(cur)) {
            isDifferent=false;
            ind = pos;
            break;
          }
          pos++;
        }

      }
      if(isDifferent){
        mem.get(account.get(0)).add(set);
      } else {
        mem.get(account.get(0)).get(ind).addAll(set);
      }
    }
    List<List<String>> ans = new ArrayList<>();
    for(Map.Entry<String, List<Set<String>>> entry:mem.entrySet()){
      List<Set<String>> val = entry.getValue();
      for(Set<String> emails : val){
        List<String> cur = new ArrayList<>();
        cur.add(entry.getKey());
        List<String> temp = new ArrayList<>();
        temp.addAll(emails);
        Collections.sort(temp);
        cur.addAll(temp);
        ans.add(cur);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    //[["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
    List<String> accounts = new ArrayList<>();

    //accountsMerge();
  }
}
