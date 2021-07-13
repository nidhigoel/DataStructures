package mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import static java.util.Collections.binarySearch;

public class SnapShotArray {
  LinkedHashMap<Integer, Integer>[] values;
  int snapId;

  public SnapShotArray(int length) {
    snapId = 0;
    values = new LinkedHashMap[length];
  }

  public void set(int index, int val) {
    if (values[index] == null) {
      LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
      map.put(snapId, val);
      values[index] = map;
    } else {
      LinkedHashMap<Integer, Integer> map = values[index];
      map.put(snapId, val);
    }
  }

  public int snap() {
    snapId++;
    return snapId - 1;
  }

  public int get(int index, int snap_id) {
    LinkedHashMap<Integer, Integer> map = values[index];
    if (map == null) return 0;
    if(map.containsKey(snap_id)) return map.get(snap_id);
    List<Integer> list = new ArrayList<>(values[index].keySet());
    int ind = binarySearch(list, snap_id);

    return list.get((-1*ind)-1);
  }
}
