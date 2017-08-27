/**
 * Created by jhu4 on 8/25/17.
 */
import java.util.*;

public class ClassTen {
  //------------------------------------------------------------------------------------------------------
  public List<List<Integer>> nqueens(int n) {
    List<List<Integer>> result = new LinkedList<List<Integer>>();
    nqueens(result, new ArrayList<>(), n);
    return result;
  }

  public void nqueens(List<List<Integer>> result, List<Integer> prev, int n) {
    if (prev.size() == n) {
      result.add(new ArrayList<>(prev));
      return;
    }

    for (int i = 0; i < n; i++) { //this loop tries different column in this layer
      if (nqueensValidate(prev, i)) {
        prev.add(i);
        nqueens(result, prev, n);
        prev.remove(prev.size() - 1);
      }
    }
  }

  private boolean nqueensValidate(List<Integer> prev, int column) {
    int row = prev.size();
    for (int i = 0; i < row; i++) {
      int col = prev.get(i);
      //two queens cannot be in the same column OR two queens cannot be in a diagonal line
      if (col == column || Math.abs(col - column) == row - i) {
        return false;
      }
    }
    return true;
  }
  //------------------------------------------------------------------------------------------------------
  public ListNode reverseInPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    //e.g. A -> B -> C
    ListNode temp = head.next.next; // save C
    head.next.next = head; // A -> B -> A
    head = head.next; // B -> A

    head.next.next = reverseInPairs(temp); //B -> A -> reverse(C)

    return head;
  }

  //------------------------------------------------------------------------------------------------------
  public static void main(String[] args) {
    ClassTen c10 = new ClassTen();
    ListNode head = Util.buildLinkedlist(new int[]{1,2,3});
    Util.print(c10.reverseInPairs(head));

  }
}
