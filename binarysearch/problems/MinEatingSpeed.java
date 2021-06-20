package binarysearch.problems;

//https://leetcode.com/problems/koko-eating-bananas/
public class MinEatingSpeed {
  public int minEatingSpeed(int[] piles, int h) {
    int lo = 1;
    int hi = 1000000000;

    while(lo<hi){
      int mid = lo+(hi-lo)/2;
      if(possible(piles,mid,h)){
        hi=mid;
      } else {
        lo = mid+1;
      }

    }
    return hi;
  }

  boolean possible(int[] piles, int speed, int h){
    int hours = 0;
    for(int i=0; i< piles.length; i++){
      hours+=piles[i]/speed + (piles[i]%speed==0?0:1) ;
      if(hours>h)return false;
    }
    return true;
  }
}
