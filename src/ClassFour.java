/**
 * Created by jhu4 on 7/9/17.
 */
import apple.laf.JRSUIUtils;

import java.util.*;
public class ClassFour {
//  public List<Integer> inOrder(TreeNode root) {
//    if(root == null) {
//      return new LinkedList<Integer>();
//    }
//
//    List<Integer> res = inOrder(root.left);
//
//    res.add(root.key);
//
//    res.addAll(inOrder(root.right));
//
//    return res;
//  }
//
//  public List<Integer> preOrder(TreeNode root) {
//    if(root == null) {
//      return new LinkedList<Integer>();
//    }
//
//    List<Integer> res = preOrder(root.left);
//    res.add(0, root.key);
//    res.addAll(preOrder(root.right));
//
//
//    return res;
//  }
//
//  public List<Integer> postOrder(TreeNode root) {
//    if(root == null) {
//      return new LinkedList<Integer>();
//    }
//
//    List<Integer> res = postOrder(root.left);
//    res.addAll(postOrder(root.right));
//    res.add(root.key);
//
//
//    return res;
//  }

  public List<Integer> inOrder(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    if(root == null) {
      return res;
    }


    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    root = root.left;

    while(!stack.isEmpty() || root != null) {
      while(root != null) { //adding all left sub-trees' nodes
        stack.push(root);
        root = root.left;
      }

      TreeNode tn = stack.pop();
      res.add(tn.key);
      root = tn.right;
    }

    return res;
  }

  public List<Integer> preOrder(TreeNode root) {
    List<Integer> res = new LinkedList();
    Deque<TreeNode> stack = new ArrayDeque<>();
    if(root == null) {
      return res;
    }

    stack.push(root);

    while(!stack.isEmpty()) {
      TreeNode tn = stack.pop();
      res.add(tn.key);

      if(tn.right != null) {
        stack.push(tn.right);
      }
      if(tn.left != null) {
        stack.push(tn.left);
      }
    }
    return res;
  }

  public List<Integer> postOrder(TreeNode root) {
    List<Integer> res = new LinkedList();
    Deque<TreeNode> stack = new ArrayDeque<>();
    Deque<TreeNode> visited = new ArrayDeque<>();
    TreeNode visitedSign = new TreeNode(0);
    if(root == null) {
      return res;
    }
    stack.push(root);
    root = root.left;

    while(!stack.isEmpty() || !visited.isEmpty()) {
      while(root != null) {
        stack.push(root);
        root = root.left;
      }

      TreeNode tn = stack.pop();
      if(tn.equals(visitedSign)) {
        res.add(visited.pop().key);
      }
      else{
        visited.push(tn);
        stack.push(visitedSign);
        root = tn.right;
      }
    }

    return res;
  }

  public boolean isBalanced(TreeNode root) {
    return isBalancedTree(root) >= 0;
  }

  private int isBalancedTree(TreeNode root) {
    if(root == null) {
      return 0;
    }

    int leftHeight = isBalancedTree(root.left);
    int rightHeight = isBalancedTree(root.right);

    if(leftHeight < 0 || rightHeight < 0) {
      return -1;
    }
    else if(Math.abs(leftHeight - rightHeight) <= 1){
      return 1 + Math.max(leftHeight, rightHeight);
    }
    else {
      return -1;
    }
  }

  public boolean isSymmetric(TreeNode root) {
    if(root == null) {
      return true;
    }
    return isSymmetric(root.left, root.right);
  }

  private boolean isSymmetric(TreeNode left, TreeNode right) {
    if(left == null && right == null) {
      return true;
    }
    else if(left == null || right == null) {
      return false;
    }
    else if(left.key != right.key) { //don't forget this step!!!
      return false;
    }

    return isSymmetric(left.left, right.right)
            && isSymmetric(left.right, right.left);
  }

  public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
    if(one == null && two == null) {
      return true;
    }
    else if(one == null || two == null) {
      return false;
    }
    else if(one.key != two.key) {
      return false;
    }

    return isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right)
            || isTweakedIdentical(one.right, two.left) && isTweakedIdentical(one.left, two.right);
  }

  public boolean isBST(TreeNode root) {
    return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private boolean isValid(TreeNode root, int leftBound, int rightBound) {
    if(root == null) {
      return true;
    }

    if(root.key < rightBound && root.key > leftBound) {
      return isValid(root.left, leftBound, root.key) && isValid(root.right, root.key, rightBound);
    }

    return false;
  }

  public List<Integer> getRange(TreeNode root, int min, int max) {
    if(root == null) {
      return new LinkedList<Integer>();
    }

    if(root.key > max) {
      return getRange(root.left, min, max);
    }
    else if(root.key < min) {
      return getRange(root.right, min, max);
    }
    else {
      List<Integer> res = getRange(root.left, min, max);
      res.add(root.key); //don't forget to add it's own value
      res.addAll(getRange(root.right, min, max));
      return res;
    }
  }

  public static void main(String[] args) {
    ClassFour c4 = new ClassFour();
    TreeNode t1 = Util.buildTree(new int[]{10,5,17,3,7,15,18,1,4,6,-1,11});

    TreeNode t2 = Util.buildTree(new int[]{9,-1,8});
    Util.print(c4.postOrder(t1));
  }
}
