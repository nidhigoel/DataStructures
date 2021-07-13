package strings;

import java.util.Arrays;

// https://leetcode.com/problems/number-of-distinct-substrings-in-a-string/
public class SuffixArray {
  int n;
  String text;
  Suffix[] suffixes;
  int[] lcp;
  int lcpLength;
  String repeatingSubstring = "";
  SuffixArray(String text){
    this.text = text;
    this.n=text.length();
    this.suffixes = new Suffix[n];
    for(int i=0;i<n;i++){
      suffixes[i]=new Suffix(i,text);
    }
    Arrays.sort(suffixes);
    lcp = new int[n];
    lcp[0]=0;
    lcpLength = 0;
    for(int i=1;i<n;i++){
      Suffix left = suffixes[i-1];
      Suffix right = suffixes[i];
//      int min = Math.min(left.length(), right.length());
      int len = Math.min(left.length(), right.length());
      int endInd = Math.max(left.index,right.index)-1;
      int min = Math.min(len,endInd);
      int pos=0;
      while(pos<min){
        if(left.charAt(pos)!=right.charAt(pos)) break;
        pos++;
      }
      lcp[i]=pos;
      if(lcp[i]>lcpLength){
        lcpLength=pos;
        repeatingSubstring=left.toString().substring(0,pos);
      }
    }
  }

  private int getDistinctSubstrings(){
    int total = n*(n+1)/2;
    int repeating  = 0;
    for(int i =0;i<n;i++){
      repeating+=lcp[i];
    }
    return total-repeating;
  }

  class Suffix implements Comparable<Suffix>{
    int index;
    String text;

    Suffix(int index, String text) {
      this.index = index;
      this.text = text;
    }

    Character charAt(int i){
      return text.charAt(i+index);
    }

    int length(){
      return text.length()-index;
    }

    @Override
    public int compareTo(Suffix o) {
      int min = Math.min(this.length(),o.length());
      for(int i=0; i<min; i++){
        int ret = Character.compare(this.charAt(i), o.charAt(i));
        if(ret!=0) return ret;
      }
      return Integer.compare(this.length(),o.length());
    }

    @Override
    public String toString(){
      return text.substring(index);
    }
  }

  public static void main(String[] args) {
    String str = "banana";
    SuffixArray obj = new SuffixArray(str);
    System.out.println(obj.repeatingSubstring);
    System.out.println(obj.lcpLength);
    System.out.println(obj.getDistinctSubstrings());
    System.out.println(Arrays.toString(obj.lcp));

  }
}
