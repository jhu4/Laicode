import java.util.*;
public class Solution {
  Stack<Integer> st;
  Stack<Integer> minStack;

  public Solution() {
    this.st = new Stack<>();
    this.minStack = new Stack<>();
  }

  public int pop() {
    if(st.empty()) {
      return -1;
    }
    else {
      minStack.pop();
      return st.pop();
    }
  }

  public void push(int element) {
    st.push(element);

    if(minStack.empty()) {
      minStack.push(element);
    }
    else {
      minStack.push(Math.min(minStack.peek(), element));
    }
  }

  public int top() {
    if(st.empty()) {
      return -1;
    }
    else {
      return st.peek();
    }
  }

  public int min() {
    if(st.empty()) {
      return -1;
    }
    else {
      return minStack.peek();
    }
  }
}
