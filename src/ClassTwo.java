/**
 * Created by jhu4 on 7/2/17.
 */
public class ClassTwo {
  public long fibonacci(int k) { //ACed
    if (k <= 0) return 0;
    if (k == 1) return 1;

    long prepre = 0, pre = 1;
    for (int i = 2; i <= k; i++) {
      long cur = prepre + pre;
      prepre = pre;
      pre = cur;
    }
    return pre;
  }

//  public long power(int a, int b) { //ACed
//    if(b == 1) return a; // I almost forget this base case
//    if(b == 0) return 1;
//    if(a == 0) return 0;
//
//    long breakdown = power(a * a, b / 2);
//
//    return b % 2 == 0? breakdown : breakdown * a;
//  }

  public long power(int a, int b) { //ACed
    if (b == 0) return 1;
    if (a == 0) return 0;

    long base = a, result = 1;
    while (b > 1) {
      if (b % 2 != 0) {
        result *= base;
      }
      base *= base;
      b /= 2;
    }
    return result * base;
  }

  public int binarySearch(int[] array, int target) {
    if (array == null || array.length == 0) return -1;
    int left = 0, right = array.length - 1, mid = left + (right - left) / 2; //right should be len - 1

    while (left <= right) {
      if (array[mid] == target) return mid;
      else if (array[mid] < target) { //don't f**king use mid instead of array[mid]
        left = mid + 1;
      } else {
        right = mid - 1;
      }
      mid = left + (right - left) / 2; //don't f**king forget to calculate mid
    }

    return -1;
  }

  //classic binary search version
  public int firstOccur(int[] array, int target) { //ACed
    if(array == null || array.length == 0) return -1;
    int left = 0, right = array.length - 1, mid = left + (right - left) / 2;

    while(left < right) {
      if(array[mid] == target){
        right = mid;
      }
      else if(array[mid] > target) {
        right = mid - 1;
      }
      else{
        left = mid + 1;
      }
      mid = left + (right - left) / 2;
    }
    return array[mid] == target ? mid : -1;
  }

  public int lastOccur(int[] array, int target) {
    if(array == null || array.length == 0) return -1;
    int left = 0, right = array.length - 1, mid = left + (right - left) / 2;

    while(left <= right) {
      if(array[mid] <= target) {
        left = mid + 1; //left is always point to the closet larger number
      }
      else {
        right = mid - 1;
      }
      mid = left + (right - left) / 2;
    }

    return (left - 1 < 0 || array[left - 1] != target) ? -1 : left - 1; // left could be zero
  }

  public int closest(int[] array, int target) {
    if(array == null || array.length == 0) return -1;

    int left = 0, right = array.length - 1, mid = left + (right - left) / 2;
    while(left < right - 1) { // stop at left = right - 1... also covers the edge case where array.length == 1
      if(array[mid] <= target) {
        left = mid; // mid - 1 is wrong because mid could be the possible candidate
      }
      else{
        right = mid; // mid +1 is wrong because mid could be the possible candidate
      }
      mid = left + (right - left) / 2;
    }
    return Math.abs(target - array[left]) < Math.abs(target - array[right]) ? left : right;
  }

  public int[] kClosest(int[] array, int target, int k) {
    int left = 0, right = array.length - 1, mid = left + (right - left) / 2;
    int[] res = new int[k];

    while(left < right - 1) {
      if(array[mid] <= target) {
        left = mid;
      }
      else {
        right = mid;
      }
      mid = left + (right - left) / 2;
    }

    for(int i = 0; i < k; i++) {
      if(left < 0) { //prevent IndexOutOfBound case
        res[i] = array[right++];
      }
      else if(right >= array.length) { //prevent IndexOutOfBound case
        res[i] = array[left--];
      }
      else if(Math.abs(target - array[left]) < Math.abs(target - array[right])) {
        res[i] = array[left--];
      }
      else {
        res[i] = array[right++];
      }
    }

    return res;
  }

  public static void main(String[] args) {
    ClassTwo c2 = new ClassTwo();

    Util.print(c2.kClosest(new int[]{0,3,3,4,5,6,8}, 8, 2));
  }
}
