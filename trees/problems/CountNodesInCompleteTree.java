package trees.problems;

//https://leetcode.com/problems/count-complete-tree-nodes/
public class CountNodesInCompleteTree {
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

  public static int getHeight(TreeNode root){
    if(root==null) return 0;
    return 1+getHeight(root.left);
  }
  public static int countNodes(TreeNode root) {
    if(root==null) return 0;
    int height=getHeight(root);
    if(height==1) return 1;
    int left = 1<<(height-1);
    int right = (1<<height)-1;
    int lo=left;
    int hi=right;
    while(lo<hi){
      int mid=lo+(hi-lo+1)/2;
      if(exists(root,mid,left,right,height)){
        lo=mid;
      } else{
        hi=mid-1;
      }
    }
    int starting = 1<<(height-1);
    int lastLevel = lo-starting+1;
    return (starting-1)+lastLevel;
  }
  static boolean exists(TreeNode root, int node, int left, int right, int height){
    if(root==null) return false;
    if(height==1) return true;
    int mid = left+(right-left)/2;
    if(node>mid) return exists(root.right,node,mid,right,height-1);
    else return exists(root.left,node,left,mid,height-1);
  }

  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(10);
    TreeNode node3 = new TreeNode(20);
    TreeNode node4 = new TreeNode(30);
    TreeNode node5 = new TreeNode(31);
    TreeNode node6 = new TreeNode(32);
    node1.left=node2;
    node1.right=node3;
    node2.left=node4;
    node2.right=node5;
    node3.left=node6;
    int ans = countNodes(node1);
    System.out.println(ans);
  }
}
