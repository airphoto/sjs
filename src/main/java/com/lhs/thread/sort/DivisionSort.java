package com.lhs.thread.sort;

/**
 * Created by abel on 16-7-21.
 */
public class DivisionSort {
    public static void main(String[] args){
        int[] a={1,2,3,4,5,6,7,8,9,10};
        System.out.println(division(a,0,a.length-1,5));
    }

    public static int division(int[] a, int left, int right, int target){
        if(left>right) return -1;

        int mid = (right + left ) / 2;
        if(target == a[mid]) return mid;

        else if(target > a[mid]) return division(a,mid+1,right,target);
        else return division(a,left,mid-1,target);
    }
}
