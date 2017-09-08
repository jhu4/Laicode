/**
 * Created by jhu4 on 8/28/17.
 */
import java.util.*;

public class ClassThirteen {
  public int minJumpMyInitialSolution(int[] array) {
    int[] minjumps = new int[array.length];
    /* M[i] represents the number of min jumps from i to the target
        base case: M[target] = 0
        induction: M[i] = 1 + min(M[i], ..., M[i + jumpdistance])  (any reachable position can jump to the target)
                          0 (otherwise)

    */

    //initializing the array
    minjumps[array.length - 1] = 0;

    for (int i = array.length - 2; i >= 0; i--) {
      minjumps[i] = -1; //initialized as -1
      int min = Integer.MAX_VALUE;
      int exclusiveUpperBound = Math.min(i + array[i] + 1, array.length);
      for (int j = i; j < exclusiveUpperBound; j++) {
        if (minjumps[j] != -1) {
          min = Math.min(min, 1 + minjumps[j]);
        }
      }
      if (min != Integer.MAX_VALUE) {
        minjumps[i] = min;
      }
    }

    return minjumps[0];
  }

  public int minJump(int[] array) {
  /*
    M[n] represents the minimum jumps from 0 t n

    base case: M[0] = 0
    induction: M[n] = min(1 + M[y])  (0 =< y < n AND we can jump from y to n)
                    = -1  otherwise
  */

    int[] minjumps = new int[array.length];
    minjumps[0] = 0; //base case

    for (int i = 1; i < array.length; i++) {
      minjumps[i] = -1;
      for (int j = 0; j < i; j++) {
        if (minjumps[j] != -1 && j + array[j] >= i) {
          if (minjumps[i] == -1 || minjumps[i] > 1 + minjumps[j]) {
            minjumps[i] = 1 + minjumps[j];
          }
        }
      }
    }

    return minjumps[array.length - 1];
  }

  //------------------------------------------------------------------------------------------------

  public boolean canBreak(String input, String[] dict) {
    Set<String> hs = new HashSet<>();
    for (String s : dict) {
      hs.add(s);
    }

    /*
      canBreak[n] represents whether it can be broken down to words provided from 0 to n-th
      canBreak[n] = true (when canBreak[n - i] = true AND string.substring(i + 1) is in the dictionary)
                  = false (otherwise)

    */
    boolean[] canBreak = new boolean[input.length()];
    canBreak[0] = hs.contains(input.substring(0,1));

    for (int i = 1; i < input.length(); i++) {
      canBreak[i] = hs.contains(input.substring(0, i + 1)); //initialized as not broken-down word
      for (int j = 0; j < i; j++) {
        if (canBreak[j] && hs.contains(input.substring(j + 1, i + 1))) {
          canBreak[i] = true;
          break;
        }
      }
    }

    return canBreak[input.length() - 1];
  }


  //------------------------------------------------------------------------------------------------
  public int editDistance(String one, String two) {
    /*
      M[i][j] represent the min steps to transfer from substring of one with first i letters to substring of two with first j letters

      base case: M[0][m] = m, M[n][0] = n, M[0][0] = 0
      induction rule:
                M[i][j] = M[i - 1][j - 1]  case 1: one.charAt(i) == two.charAt(j), do nothing
                          1 + M[i - 1][j - 1]   case 2: replace
                          1 + M[i - 1][j]    case 3: delete
                          1 + M[i][j - 1]   case 4: insert

                        = min (all cases above)
    */

    int len1 = one.length(), len2 = two.length();
    int[][] matrix = new int[len1 + 1][len2 + 1];

    for (int i = 0; i <= len1; i++) {
      matrix[i][0] = i;
    }
    for(int i = 0; i <= len2; i++) {
      matrix[0][i] = i;
    }

    for (int i = 1; i <= len1; i++) {
      for (int j = 1; j <= len2; j++) {
        if (one.charAt(i - 1) == two.charAt(j - 1)) {
          matrix[i][j] = min(matrix[i - 1][j - 1], //do nothing
                  1 + matrix[i - 1][j], //insert
                  1 + matrix[i][j - 1]); //delete
        } else {
          matrix[i][j] = 1 + min(matrix[i - 1][j - 1], //replace
                  matrix[i - 1][j], //insert
                  matrix[i][j - 1]); //delete
        }
      }
    }

    return matrix[len1][len2];
  }

  private int min(int a, int b, int c) {
    return Math.min(Math.min(a, b), c);
  }

  //------------------------------------------------------------------------------------------------
  public int largest(int[][] matrix) {
  /*
    M[i][j] represents the max size of the square with coordinate[i][j] as its bottom right corner

    M[i][j] = 0   if coordinate[i][j] = 0
            = 1 + min(M[i - 1][j - 1], M[i - 1][j], M[i][j - 1])    else
  */

    int row = matrix.length, col = matrix[0].length;
    int max = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (i != 0 && j != 0 && matrix[i][j] != 0) {
          matrix[i][j] =  1 + Math.min(matrix[i - 1][j - 1], matrix[i - 1][j]);
          matrix[i][j] =  Math.min(1 + matrix[i][j - 1], matrix[i][j]);
        }
        max = Math.max(max, matrix[i][j]);
      }
    }

    return max;
  }
  //------------------------------------------------------------------------------------------------
  public static void main(String[] args) {
    ClassThirteen c13 = new ClassThirteen();
    Util.print(c13.largest(new int[][]{{1,1,1,1},{1,1,1,0},{1,1,1,1},{1,1,0,1}}));

  }
}
