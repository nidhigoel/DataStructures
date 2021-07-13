package trie;

import java.util.LinkedList;
import java.util.Queue;

// https://www.youtube.com/watch?v=0k79LqIaHyQ
// https://leetcode.com/problems/implement-trie-prefix-tree/
public class Trie {
  static TrieNode root;
  static class TrieNode{
    char ch;
    int wc;
    TrieNode[] children;

    TrieNode(char ch){
      this.ch=ch;
      wc=0;
      children = new TrieNode[26];
    }
  }

  static void insert(String str){
    TrieNode node = root;
    char[] ar = str.toCharArray();
    for(int i=0;i<ar.length;i++){
      Character ch = ar[i];
      TrieNode cur = node.children[ch-'a'];
      if(cur==null){
        cur = new TrieNode(ch);
        node.children[ch-'a'] = cur;
      }
      node=cur;
    }
    node.wc++;
  }

  static boolean search(String str){
    TrieNode node = root;
    char[] ar = str.toCharArray();
    for(int i=0;i<ar.length;i++){
      Character ch = ar[i];
      TrieNode cur = node.children[ch-'a'];
      if(cur==null) return false;
      node=cur;
    }
    return node.wc>0;
  }

  static boolean isPrefix(String str){
    TrieNode node = root;
    char[] ar = str.toCharArray();
    for(int i=0;i<ar.length;i++){
      Character ch = ar[i];
      TrieNode cur = node.children[ch-'a'];
      if(cur==null) return false;
      node=cur;
    }
    return true;
  }

  public int countWordsEqualTo(String word) {
    TrieNode node = root;
    char[] ar = word.toCharArray();
    for(int i=0;i<ar.length;i++){
      Character ch = ar[i];
      TrieNode cur = node.children[ch-'a'];
      if(cur==null) return 0;
      node=cur;
    }
    return node.wc;
  }

  public int countWordsStartingWith(String prefix) {
    TrieNode node = root;
    char[] ar = prefix.toCharArray();
    for(int i=0;i<ar.length;i++){
      Character ch = ar[i];
      TrieNode cur = node.children[ch-'a'];
      if(cur==null) return 0;
      node=cur;
    }
    int ans=0;
    Queue<TrieNode> nodes = new LinkedList<>();
    nodes.add(node);

    while (!nodes.isEmpty()){
      TrieNode cur = nodes.remove();
      for(int i=0;i<26;i++){
        TrieNode child = cur.children[i];
        if(child!=null) {
          nodes.add(child);
          ans+=child.wc;
        }
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    root = new TrieNode(' ');
    insert("abc");
    System.out.println(search("ab"));
    System.out.println(isPrefix("ab"));
  }
}
