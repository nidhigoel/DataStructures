package Arrays;

import java.util.TreeMap;

public class RangeModule {
  //start as key end as value
  final TreeMap<Integer, Integer> ranges = new TreeMap<>();

  public RangeModule() {

  }

  public void addRange(int left, int right) {
    //System.out.println("addRange ("+left+","+right+")");
    if (left > right) return;
    Integer start = ranges.floorKey(left);
    Integer end = ranges.floorKey(right);

    //System.out.println("addRange (start="+start+",end="+end+")");

    //start <= left
    if (start != null && ranges.get(start) >= left) left = start;
    //~ end <= right
    if (end != null && ranges.get(end) >= right) right = ranges.get(end);

    ranges.subMap(left, true, right, true).clear();
    ranges.put(left, right);
    //System.out.println(ranges);
  }

  public boolean queryRange(int left, int right) {
    if (left > right) return false;
    Integer start = ranges.floorKey(left);
    if (start == null) return false;
    return ranges.get(start) >= right;
  }

  public void removeRange(int left, int right) {
    //System.out.println("removeRange ("+left+","+right+")");
    if (left > right) return;
    Integer start = ranges.floorKey(left);
    Integer end = ranges.floorKey(right);

    //System.out.println("removeRange (start="+start+",end="+end+")");

    //~ end <= right
    if (end != null && ranges.get(end) > right) {
      ranges.put(right, ranges.get(end));
    }

    //start <= left
    //~ do after spliting on right because left overrides the floorKey
    if (start != null && ranges.get(start) > left) {
      ranges.put(start, left);
    }


    //System.out.println(ranges);
    ranges.subMap(left, true, right, false).clear();
    //System.out.println(ranges);
  }
}
