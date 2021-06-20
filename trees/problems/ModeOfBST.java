package trees.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/
 * inorder traversal of BST result in sorted array
 * keep the track of last element of inOrderTraversal.
 *
 * If it is same as current element, increase count of this element.
 * a. If count is equal to maxCount , than this value is also an mode value.
 * b. If count > maxCount, clear the previous saved modes and initialize mode with this number.
 * If this element is not same as last node, start current node count from zero and make this value as previous value.
 */
public class ModeOfBST {
  public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    static Set<Integer> modes;
    static int cur;
    static int curFreq;
    static int maxFreq;

    static void inOrderTraversal(TreeNode root){
      if(root==null) return;
      inOrderTraversal(root.left);
      if(root.val==cur){
        curFreq++;
      } else{
        cur=root.val;
        curFreq=1;
      }
      if(curFreq>maxFreq){modes.clear();}
      if(curFreq>=maxFreq){modes.add(cur);}
      maxFreq=Math.max(maxFreq, curFreq);
      inOrderTraversal(root.right);
    }

    public static int[] findMode(TreeNode root) {
      if(root==null) return new int[0];
      cur=root.val;
      curFreq=0;
      maxFreq=0;
      modes = new HashSet<>();
      inOrderTraversal(root);
      //Creating an empty integer array
      int[] array = new int[modes.size()];
      //Converting Set object to integer array
      int j = 0;
      for (Integer i: modes) {
        array[j++] = i;
      }
      return array;
    }

  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(4);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(4);
    TreeNode node4 = new TreeNode(1);
    TreeNode node5 = new TreeNode(2);
    TreeNode node6 = new TreeNode(5);
    node1.left=node2;
    node1.right=node3;
    node2.left=node4;
    node2.right=node5;
    node3.right=node6;
    int[] ans = findMode(node1);
    System.out.println(Arrays.toString(ans));
  }
}
