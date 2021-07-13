package Stacks.problems;

import java.util.LinkedList;
import java.util.Stack;

//https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
// Based on concept of next greater element
public class HistogramMaxRectangle {

    //Function to find largest rectangular area possible in a given histogram.
    public static long getMaxArea(long hist[], long n)
    {
      int N = hist.length;
      // 1. get nearest smaller element to right
      long[] R = getRightBoundary(hist, N);

      // 2. get nearest smaller element to left
      long[] L = getLeftBoundary(hist, N);

      // multiply current height * span
      long max = 0;

      for(int i=0; i<N; i++){
        max = Math.max(max, hist[i]*(R[i]-L[i]+1));
      }
      return max;
    }

    // concept of next greater element
    // https://www.youtube.com/watch?v=NXOOYYwpbg4
    // finding nearest smaller, if exists
    // otherwise end element is the right boundary
    static long[] getRightBoundary(long[] hist, int N){
      long[] ans = new long[N];
      Stack<Integer> st = new Stack<>();
      for(int i=N-1; i>0; i--){
        ans[i]=N-1;
        while(!st.empty()){
          if(hist[st.peek()]<hist[i]){
            ans[i]=st.peek()-1;
            break;
          } else st.pop();
        }
        st.push(i);
      }
      return ans;
    }

    // finding nearest smaller, if exists
    // otherwise 0 th element is the left boundary
    static long[] getLeftBoundary(long[] hist, int N){
      long[] ans = new long[N];
      Stack<Integer> st = new Stack<>();
      for(int i=0; i<N; i++){
        ans[i]=0;
        while(!st.empty()){
          if(hist[st.peek()]<hist[i]){
            ans[i]=st.peek()+1;
            break;
          } else st.pop();
        }
        st.push(i);
      }
      return ans;
    }


}
