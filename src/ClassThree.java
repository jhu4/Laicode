import java.util.List;

/**
 * Created by jhu4 on 7/5/17.
 */
public class ClassThree {
  public ListNode middleNode1(ListNode head) {
    if(head == null || head.next == null) {
      return head;
    }

    ListNode slow = head, fast = head;

    while(fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  public ListNode middleNode2(ListNode head) {
    if(head == null || head.next == null) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode slow = dummy, fast = dummy;

    while(fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

//  public ListNode reverse(ListNode head) {
//    if(head == null || head.next == null) return head;
//
//    ListNode reversedTail = head.next;
//    ListNode reversedHead = reverse(head.next);
//    reversedTail.next = head;
//    head.next = null;
//
//    return reversedHead;
//  }

  public ListNode reverse(ListNode head) {
    if(head == null || head.next == null) return head;

    ListNode reversed = null;
    ListNode temp;

    while(head != null) {
      temp = head;
      head = head.next; //take out the current Node from the orginal list
      temp.next = reversed; //add the current Node to the head of reversed list
      reversed = temp; //update the head Node of the reversed list
    }
    return reversed;
  }

  public ListNode insert(ListNode head, int value) {
    ListNode dummy = new ListNode(0), insertedNode = new ListNode(value), cur = dummy;
    dummy.next = head;

    while(cur.next != null && value > cur.next.value) { //this loop finds the insertion place
      cur = cur.next;
    }

    insertedNode.next = cur.next;
    cur.next = insertedNode;

    return dummy.next;
  }

  public ListNode merge(ListNode one, ListNode two) {
    if(one == null && two == null) {
      return null;
    }
    else if(one == null) {
      return two;
    }
    else if(two == null) {
      return one;
    }

    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;

    while(one != null || two != null) {
      if(two == null) {
        cur.next = one;
        one = one.next;
      }
      else if(one == null) {
        cur.next = two;
        two = two.next;
      }
      else if(one.value <= two.value) {
        cur.next = one;
        one = one.next;
      }
      else{
        cur.next = two;
        two = two.next;
      }
      cur = cur.next;
    }
    return dummy.next;
  }

  public boolean hasCycle(ListNode head) {
    if(head == null || head.next == null) {
      return false;
    }

    ListNode slow = head, fast = head.next;

    while(fast != null && fast.next != null) {
      if(slow.equals(fast)) { //equals for checking objects
        return true;
      }
      slow = slow.next;
      fast = fast.next.next;
    }

    return false;
  }

  public int[] search(int[][] matrix, int target) {
    if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
      return new int[]{-1, -1};
    }

    int left = 0, right = matrix.length - 1, mid = left + (right - left) / 2;

    //binary search to find the closet smaller start element of rows to the target
    while(left < right - 1) {
      int rowElement = matrix[mid][0];
      if(rowElement < target) {
        left = mid;
      }
      else if(rowElement == target) {
        return new int[]{mid, 0};
      }
      else {
        right = mid - 1;
      }
      mid = left + (right - left) / 2;
    }

    int rowIndex;
    if(left == right) {
      rowIndex = left;
    }
    else if(matrix[right][0] < target) { //case 2: right is the largest smaller element to the target
      rowIndex = right;
    }
    else if(matrix[right][0] == target){
      return new int[]{right, 0};
    }
    else { //case 3: left is the largest smaller element to the target
      rowIndex = left;
    }


    left = 0;
    right = matrix[rowIndex].length - 1;
    mid = left + (right - left) / 2;

    //binary search in the row to find the position of the target
    while(left <= right) {
      int middleNum = matrix[rowIndex][mid];
      if(middleNum == target) {
        return new int[]{rowIndex, mid};
      }
      else if(middleNum < target) {
        left = mid + 1;
      }
      else {
        right = mid - 1;
      }
      mid = left + (right - left) / 2;
    }

    return new int[]{-1, -1};
  }

  public ListNode partition(ListNode head, int target) {
    if(head == null || head.next == null) {
      return head;
    }

    ListNode smaller = new ListNode(0), smallerIterator = smaller;
    ListNode other = new ListNode(0), otherIterator = other;

    while(head != null) {
      if(head.value < target) {
        smallerIterator.next = head;
        smallerIterator = smallerIterator.next;
      }
      else {
        otherIterator.next = head;
        otherIterator = otherIterator.next;
      }
      head = head.next;
    }


    smallerIterator.next = other.next;
    otherIterator.next = null;

    return smaller.next;
  }

  public ListNode reorder(ListNode head) {
    if(head == null || head.next == null) {
      return head;
    }

    ListNode mid = findMidNode(head), reversed = reverseLinkedList(mid.next);
    mid.next = null;
    ListNode it = head, temp;

    while(reversed != null) {
      temp = reversed.next;
      reversed.next = it.next;
      it.next = reversed;
      it = it.next.next; //update the iterator
      reversed = temp; //update the head of the reversed half of the LinkedList
    }

    return head;
  }

  private ListNode findMidNode(ListNode head) {
    ListNode slow = head, fast = head.next;

    while(fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  private ListNode reverseLinkedList(ListNode head) {
    if(head == null || head.next == null) {
      return head;
    }

    ListNode reversed = null, temp;

    while(head != null) {
      temp = head; //take off the current node
      head = head.next;
      temp.next = reversed;
      reversed = temp;
    }

    return reversed;
  }

  public static void main(String[] args) {
    ClassThree c3 = new ClassThree();
    Util util = new Util();
    ListNode node = util.buildLinkedlist(new int[]{1,2,3,4});


    util.print(c3.reorder(node));
  }
}
