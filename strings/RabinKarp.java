package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO
// https://www.quora.com/Which-is-better-and-efficient-between-the-KMP-algorithm-and-the-Rabin-Karp-algorithm
public class RabinKarp {
  long P = (1 << 31) - 1;
  long R = 256;
  long[] RM;

  // Compute hash for key[0..window-1].
  long getHash(String key, int window){
    long hash = 0;
    for(int i=0; i<window; i++){
      hash = (hash*R+key.charAt(i))%P;
    }
    return hash;
  }

//  private int search(String text, String pat){
//    int n = text.length();
//    int m=pat.length();
//    if(n<m)return -1;
//    long patHash = getHash(pat,m);
//    long textHash = getHash(text,m);
//    if(textHash==patHash && text.substring(0,m).equals(pat)) return 0;
//    for(int i=1; i<n; i++){
//      textHash=textHash+P-
//    }
//  }

  String test(String test, int window){
    //if(test.length()==0 || window==1 || window>=test.length()) return null;
    Map<Long, List<Integer>> seen = new HashMap<>();
    long hash = getHash(test,window);
    seen.computeIfAbsent(hash, x -> new ArrayList<>());
    seen.get(hash).add(0);
    for(int i=1; i<=test.length()-window; i++){
      hash = (hash+P-RM[window-1]*test.charAt(i-1)%P)%P;
      hash = (hash*R+test.charAt(i+window-1))%P;
      if(seen.containsKey(hash)){
        String cur = test.substring(i,window+i);
        for(int offset:seen.get(hash)){
          String toCheck = test.substring(offset,window+offset);
          if(cur.equals(toCheck))return cur;
        }
      }
      seen.computeIfAbsent(hash, x->new ArrayList<>());
      seen.get(hash).add(i);
    }
    return null;
  }

  public String longestDupSubstring(String s) {
    int n=s.length();
    if(n<=1)return "";
    RM=new long[n];
    RM[0]=1;
    for(int i=1;i<n;i++){
      RM[i]=(RM[i-1]*R)%P;
    }
    int lo=1;
    int hi=n-1;
    String ans = null;
    while(lo<hi){
      int mid = lo + (hi-lo+1)/2;
      String repeated = test(s,mid);
      if(repeated!=null){
        ans=repeated;
        lo=mid;
      } else {
        hi=mid-1;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    RabinKarp rk = new RabinKarp();
    String ans = rk.longestDupSubstring("banana");
    System.out.println(ans);
  }

}
