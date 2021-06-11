import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utils {

  String reverseString(){
    return new StringBuilder().append("abc").reverse().toString();
  }

  List<Integer> sort(List<Integer> arr){
    Collections.sort(arr, (a, b)->a-b);
    return arr;
  }

  Integer[] sort(Integer[] arr){
    Arrays.sort(arr, (a, b)->a-b);
    return arr;
  }

  Integer[] sort2(Integer[] arr){
    Arrays.sort(arr, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1-o2;
      }
    });
    return arr;
  }
}
