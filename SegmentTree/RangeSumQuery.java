package SegmentTree;

//https://leetcode.com/problems/range-sum-query-mutable/
//Segment Tree
public class RangeSumQuery {
  SegmentTree st;
  int[] nums;

  class SegmentTree{
    int[] st;

    public SegmentTree(int[] nums) {
      int height = (int)Math.ceil(Math.log(nums.length)/Math.log(2));
      int size = (int)(Math.pow(2,height+1))-1; // GP sum = 2^(r+1)-1
      st = new int[size];
      constructST(0, nums, 0, nums.length-1);
    }

    private void print(){
      System.out.println("####");
      for(int i=0; i< st.length;i++){
        System.out.println(st[i]);
      }
      System.out.println("####");
    }

    private void constructST(int node, int[] nums, int start, int end){
      // System.out.println("building"+node+" "+ start + " "+ end + " ");
      if(start==end){
        st[node]= nums[start];
        return;
      }
      int mid = (start+end)/2;
      constructST(2*node+1, nums, start, mid);
      constructST(2*node+2, nums, mid+1, end);
      st[node] = st[2*node+1] + st[2*node+2];
    }

    private void update(int node, int start, int end, int index, int value){
      if(start==end){
        st[node]+=value;
        return;
      }
      int mid = (start+end)/2;
      if(index>=start && index<=mid){
        update(2*node+1, start, mid, index, value);
      } else {
        update(2*node+2, mid+1, end, index, value);
      }
      st[node] = st[2*node+1] + st[2*node+2];
    }

    // start and end is the range represented by node
    // left and right is the range in which query has to be performed
    // no overlap int two cases:-
    //      left->right->start->end
    //                   start->end->left->right
    // total overlap in one case :-
    //      left->start->end->right
    // else partial overlap :-
    //      start->left->end->right
    //      left->start->right->end
    private int query(int node, int start, int end, int left, int right){
      //no overlap
      if(right<start || end<left){
        return 0;
      }
      //total overlap
      if(left<=start && end<=right){
        return st[node];
      }
      //partial overlap
      int mid = (start+end)/2;
      return query(2*node+1, start, mid, left, right) + query(2*node+2, mid+1, end, left, right);
    }
  }

  public RangeSumQuery(int[] nums) {
    this.st = new SegmentTree(nums);
    this.nums= nums;
  }

  public void update(int index, int val) {
    int delta = val-nums[index];
    nums[index] = val;
    st.update(0, 0, nums.length-1, index, delta);
  }

  public int sumRange(int left, int right) {
    return st.query(0, 0, nums.length-1, left, right);
  }

  public static void main(String[] args) {
    RangeSumQuery rsq = new RangeSumQuery(new int[]{1,2,10});
    rsq.update(0,3);
    int ans = rsq.sumRange(0,1);
    System.out.println(ans);
  }
}
