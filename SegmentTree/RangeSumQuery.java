package SegmentTree;

//TODO
public class RangeSumQuery {

  class SegmentTree{
    int noOfElements;
    int [] segTree;

    SegmentTree(int noOfElements){
      this.noOfElements = noOfElements;
      int height = (int) Math.ceil(Math.log(noOfElements)/Math.log(2));
      int nodes = (int) (2*Math.pow(2,height)-1);
      segTree = new int[2*noOfElements+2];
      String x ="";
      x.substring(0,1);
    }
  }
}
