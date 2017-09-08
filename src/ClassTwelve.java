/**
 * Created by jhu4 on 8/27/17.
 */
public class ClassTwelve {
  public int longest(int[] array) {
    if (array.length == 0) {
      return 0;
    }

    int max = 1;
    int cur = 1; //cur represent the ascending sub array including the i-th element (dp[i])
    //dp[i] = 1   (array[i] <= array[i - 1])
    //        dp[i - 1] + 1 (array[i] > array[i - 1])
    for (int i = 1; i < array.length; i++) {
      if (array[i] > array[i - 1]) {
        cur++;
        max = Math.max(cur,max);
      } else {
        cur = 1;
      }
    }

    return max;
  }
  //------------------------------------------------------------------------------------------------
  public int maxProduct(int length) {
  /* M[n] represents the max product of a cutting rope with length n
      M[1] = 0
      M[2] = 1
      M[n] = max {
                    max(M[1], 1) * (n - 1),
                    max(M[2], 2) * (n - 2),
                    ...
                    map(M[n-1], n - 1) * 1
      }
  */

    if (length <= 1) {
      return 0;
    }
    if (length == 2) {
      return 1;
    }


    int[] dp = new int[length + 1];
    dp[1] = 0;
    dp[2] = 1;

    for (int i = 3; i <= length; i++) {
      for (int j = 1; j < i; j++) { //Question here: why can the terminated condition be <= i/2
        dp[i] = Math.max(Math.max(j, dp[j]) * (i - j), dp[i]); // should be * (i - j) instead of * (length - j) because this is the subproblem
      }
    }

    return dp[length];
  }
  //------------------------------------------------------------------------------------------------
  public boolean canJump(int[] array) {
  /*
    base case:
    dp[end] = true

    dp[n] represent whether we can jump from n-th position to the end
    dp[n] = true (dp[n + 1] ~ dp[n + jumpStep] had one true)
             false (otherwise)

  */
    boolean[] dp = new boolean[array.length];
    dp[array.length - 1] = true;

    for (int i = array.length - 2; i >= 0; i--) {
      int upperBound = Math.min(array.length - 1, i + array[i]);
      for (int j = i + 1; j <= upperBound; j++) {
        if (dp[j]) {
          dp[i] = true;
        }
      }
    }

    return dp[0];
  }

  public static void main(String[] args) {
    ClassTwelve c12 = new ClassTwelve();
    Util.print(c12.canJump(new int[]{1, 3, 2, 0, 3}));
  }
}
