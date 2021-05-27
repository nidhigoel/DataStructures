package algos.binarysearch;

import java.util.Arrays;
import java.util.List;

public class UpperLowerBound {

  /* Returns an iterator pointing to the first element
   * in the range [first, last)
   * that is greater than value,
   * or last if no such element is found.
   */
  static int upper_bound(List<Integer> arr, double X, int start){
    int mid;
    int low = start;
    int high = arr.size()-1;

    // Till low is less than high
    while (low < high) {
      mid = low + (high - low) / 2;
      if (arr.get(mid)<=X) {
        low = mid+1;
      }
      else {
        high = mid;
      }
    }

    if(arr.get(low) > X) return low;
    else if(arr.get(high)>X) return high;
    return  arr.size();
  }

  /*
  * Returns an iterator pointing to the first element in the range [first, last)
  * that is not less than (i.e. greater or equal to) value,
  * or last if no such element is found.
  */
  static int lower_bound(List<Integer> arr, double X, int start){
    int mid;
    int low = start;
    int high = arr.size()-1;

    // Till low is less than high
    while (low+1 < high) {
      mid = low + (high - low) / 2;
      if (arr.get(mid)<X) {
        low = mid+1;
      }
      else {
        high = mid;
      }
    }

    if(arr.get(low) >= X) return low;
    else if(arr.get(high) >= X) return high;
    return arr.size();
  }

  public static void main(String[] args) {
    List<Integer> arr = Arrays.asList(2, 3, 4, 4, 5, 8, 9);
    System.out.println(upper_bound(arr,1,0));
    System.out.println(lower_bound(arr,1,0));
  }
}
