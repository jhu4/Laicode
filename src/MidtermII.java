/**
 * Created by jhu4 on 9/1/17.
 */
import java.util.*;

public class MidtermII {
  public int minCuts(String input) {
    int len = input.length();
    int[] minCuts = new int[len + 1];
    boolean[][] isPalindrome = new boolean[len + 1][len + 1];

  /* minCuts[i] represents the min cut of making substring(0, i) exclusive of i-th
      character
      base case: minCuts[0] = 0
                 minCuts[1] = 0
      induction: minCuts[i] =   0   (substring(0, i) is palindromic)
                                min(minCuts[n] + 1)  (substring (n, i) is palindromic AND 0 <= n <= i)

    isPalindrome[s][e] represents whether substring (s, e) is a palindrome
    basecase: isPalindrome[s[e + 1] = true
              isPalindrome[s][e] = true   (if char[s] = char[e - 1] and isPalindrome[s + 1][e - 1] = true)
                                   true   (if char[s] = char[e] and e - s = 2)
                                   false  (otherwise)
  */

    for (int end = 1; end < len + 1; end++) { //end index of minCut substring
      minCuts[end] = end - 1; //initialization
      for (int start = end - 1; start >= 0; start--) {
        if(start == end - 1) {
          isPalindrome[start][end] = true;
        } else if (input.charAt(start) == input.charAt(end - 1)) {
          if (end - start == 2 || isPalindrome[start + 1][end - 1]) {
            isPalindrome[start][end] = true;
          }
        }

        if (isPalindrome[start][end]) {
          minCuts[end] = Math.min(minCuts[end], minCuts[start] + 1);
        }
      }
      if (isPalindrome[0][end]) {
        minCuts[end] = 0;
      }
    }

    return minCuts[len];
  }

  public void printAllIfStatement(int n) {
    printAllIfStatement(0,0, new StringBuilder(),n);
  }

  private void printAllIfStatement(int left, int right, StringBuilder s, int n) {
    if (left == n && right == n) {
      printIfStatement(s.toString());
    }


    if (left < n) {
      s.append("(");
      printAllIfStatement(left + 1, right, s, n);
      s.deleteCharAt(s.length() - 1);
    }

    if (right < n && left > right) {
      s.append(")");
      printAllIfStatement(left, right + 1, s, n);
      s.deleteCharAt(s.length() - 1);
    }
  }

  private void printIfStatement(String sb) {
    StringBuilder indent = new StringBuilder();

    for (char c : sb.toCharArray()) {

      if (c == '(') {
        System.out.print(indent);
        System.out.println("if {");
        indent.append("\t");
      } else {
        indent.deleteCharAt(indent.length() - 1);
        System.out.print(indent);
        System.out.println("}");

      }
    }
  }

  public static void main(String[] args) {
    MidtermII m2 = new MidtermII();
    m2.printAllIfStatement(0,0, new StringBuilder(), 3);
  }
}
