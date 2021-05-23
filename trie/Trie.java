package trie;

public class Trie {
  TrieDS trieRoot;

  class TrieDS{
    private char ch;
    boolean isEndOfWord;
    private TrieDS[] trie = new TrieDS[26];

    TrieDS(char ch){
      this.ch = ch;
      isEndOfWord = false;
    }

    public TrieDS getChild(char ch){
      return trie[ch-'a'];
    }

    public int getCount(char ch){
      int ans=0;
      for(int i=0; i<26; i++){
        if(trie[i]!=null) ans++;
      }
      return ans;
    }

    public void add(TrieDS child){
      if(trie[child.ch-'a'] == null) trie[child.ch-'a'] = child;
    }

    public void print(){
      System.out.println(ch+ " "+ isEndOfWord);
      // for(int i=0; i<26; i++){
      //     if(trie[i]!=null) System.out.println(trie[i].ch + " ");
      // }
    }


  }

  /** Initialize your data structure here. */
  public Trie() {
    trieRoot = new TrieDS(' ');
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    TrieDS temp = trieRoot;
    for(int i=0; i<word.length(); i++){
      TrieDS child = temp.getChild(word.charAt(i));
      if(child == null) {
        child = new TrieDS(word.charAt(i));
        temp.add(child);
      }
      temp=child;
    }
    temp.isEndOfWord = true;
    // System.out.println("inserted "+word);
    // print(trieRoot);
  }

  /** Inserts a word into the trie. */
  public void print(TrieDS root) {
    if(root==null) return;
    root.print();
    for(int i=0; i<26; i++){
      if(root.trie[i]!=null) print(root.trie[i]);
    }
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {

    // System.out.println("searching "+word);

    TrieDS temp = trieRoot;
    for(int i=0; i<word.length(); i++){
      TrieDS child = temp.getChild(word.charAt(i));
      if(child == null) {
        // System.out.println("returning false");
        return false;}
      temp=child;
    }
    // System.out.println("temp.isEndOfWord"+temp.isEndOfWord);
    return temp.isEndOfWord;
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    TrieDS temp = trieRoot;
    for(int i=0; i<prefix.length(); i++){
      TrieDS child = temp.getChild(prefix.charAt(i));
      if(child == null) return false;
      temp=child;
    }
    return true;

  }
}
