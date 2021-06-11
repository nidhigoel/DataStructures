package binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxPointVisible {

  public static int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
    int N = points.size();
    List<Double> angles = new ArrayList<>();

    int count = 0;
    int self = 0;

    for(List<Integer> point : points){
      if(point.get(0)==location.get(0) && point.get(1)==location.get(1)){
        self ++;
      } else angles.add(getAngle(point,location));
    }

    Collections.sort(angles);
    for(int i=0; i<angles.size(); i++){
      double target = angles.get(i) + angle;
      int j = upper_bound(angles, angles.get(0)+angle, i);
      if(j==angles.size()) j=j-1;
      while(j<angles.size()-1 && angles.get(j) == target) {
        j++;
      }
      if(angles.get(j) > target) j=j-1;

      int len = j-i+1;
      count = Math.max(count, len);
    }
    return count+self;
  }

  static int upper_bound(List<Double> arr, double X, int start){
    int mid;
    int low = start;
    int high = arr.size()-1;

    // Till low is less than high
    while (low < high) {
      mid = low + (high - low) / 2;

      // If X is less than or equal
      // to arr[mid], then find in
      // left subarray
      if (X <= arr.get(mid)) {
        high = mid;
      }

      // If X is greater arr[mid]
      // then find in right subarray
      else {
        low = mid + 1;
      }
    }

    // if X is greater than arr[n-1]
    if(arr.get(low) < X) {
      low++;
    }

    // Return the lower_bound index
    return  low;
  }

  static int lower_bound(List<Double> arr, double X, int start){
    int mid;
    int low = start;
    int high = arr.size()-1;

    // Till low is less than high
    while (low < high) {
      mid = low + (high - low) / 2;

      // If X is less than or equal
      // to arr[mid], then find in
      // left subarray
      if (X <= arr.get(mid)) {
        high = mid;
      }

      // If X is greater arr[mid]
      // then find in right subarray
      else {
        low = mid + 1;
      }
    }

    // if X is greater than arr[n-1]
    if(arr.get(low) < X) {
      low++;
    }

    // Return the lower_bound index
    return  low;
  }

  static double getAngle(List<Integer> point, List<Integer> location){
    double x1 = point.get(0);
    double x2 = location.get(0);
    double y1 = point.get(1);
    double y2 = location.get(1);

    if(x1-x2==0){
      if(y1>y2) return 90.0;
      else return 270.0;
    }
    double d = (y1-y2)/(x1-x2);
    double degrees = Math.toDegrees(Math.atan(Math.abs(d)));
    if(y1>=y2){
      if(x1>x2) return degrees; // 1st quad
      else return 180-degrees; // 2nd quad
    } else {
      if(x1>x2) return 360-degrees;// 4th quad
      else return 180+degrees;
    }
//    System.out.println(d);
  }

  public static void main(String[] args) {
    List<Integer> point1 = new ArrayList<>();
    point1.add(1);
    point1.add(1);
    List<Integer> point2 = new ArrayList<>();
    point2.add(2);
    point2.add(2);
    List<Integer> point3 = new ArrayList<>();
    point3.add(3);
    point3.add(3);
    List<Integer> point4 = new ArrayList<>();
    point4.add(4);
    point4.add(4);
    List<Integer> point5 = new ArrayList<>();
    point5.add(1);
    point5.add(2);
    List<Integer> point6 = new ArrayList<>();
    point6.add(2);
    point6.add(1);
    List<Integer> location = new ArrayList<>();
    location.add(1);
    location.add(1);
    List<List<Integer>> points= new ArrayList<>();
    points.add(point1);
    points.add(point2);
    points.add(point3);
    points.add(point4);
    points.add(point5);
    points.add(point6);
    int ans = visiblePoints(points, 0, location);
    System.out.println(ans);
  }
}
