import java.util.*;

/**
 * Created by jhu4 on 7/15/17.
 */
public class ClassFive {
//  public int[] kSmallest(int[] array, int k) {
//    if(array == null || array.length ==0 || k == 0) {
//      return new int[0];
//    }
//    if( k >= array.length) {
//      Arrays.sort(array);
//      return array;
//    }
//
//    int counter = 0;
//    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
//    for(; counter < k; counter++) {
//      maxHeap.offer(array[counter]);
//    }
//
//    for(; counter < array.length; counter++) {
//      if(array[counter] < maxHeap.peek().intValue()) {
//        maxHeap.poll();
//        maxHeap.offer(array[counter]);
//      }
//    }
//
//    int[] res = new int[k];
//    for(int i = k - 1; i >= 0; i--) {
//      res[i] = maxHeap.poll();
//    }
//    return res;
//  }

  public int[] kSmallest(int[] array, int k) {
    if(array == null || array.length == 0) {
      return new int[0];
    }

    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    for(int i : array) {
      minHeap.offer(i);
    }

    int[] res = new int[k];
    for(int i = 0; i < k; i++) {
      res[i] = minHeap.poll();
    }
    return res;
  }

  //--------------------------------------------------------------------------------------------------------
  public int kthSmallest(int[][] matrix, int k) {
    int rowSize = matrix.length;
    int colSize = matrix[0].length;

    PriorityQueue<MyInt> pq = new PriorityQueue<>();
    pq.offer(new MyInt(0, 0, matrix[0][0]));

   for(int i = 0; i < k - 1; i++) {
      MyInt curMin = pq.poll();

      int row = curMin.row, col = curMin.col;
      int nextRow = row + 1, nextCol = col + 1;

      if(nextRow < rowSize && matrix[nextRow][col] != Integer.MAX_VALUE) {
        pq.offer(new MyInt(nextRow, col, matrix[nextRow][col]));
        matrix[nextRow][col] = Integer.MAX_VALUE; //mark as visited
      }

      if(col + 1 < colSize && matrix[row][col + 1] != Integer.MAX_VALUE) {
        pq.offer(new MyInt(row, nextCol, matrix[row][nextCol]));
        matrix[row][nextCol] = Integer.MAX_VALUE;
      }
    }

    return pq.poll().value;
  }

  public class MyInt implements Comparable<MyInt> {
    int row;
    int col;
    int value;

    public MyInt(int row, int col, int value) {
      this.row = row;
      this.col = col;
      this.value = value;
    }

    @Override
    public int compareTo(MyInt other) {
      if(this.value < other.value) {
        return -1;
      }
      else if(this.value == other.value) {
        return 0;
      }
      else {
        return 1;
      }
    }
  }

  //--------------------------------------------------------------------------------------------------------
  /**
   * public class GraphNode {
   *   public int key;
   *   public List<GraphNode> neighbors;
   *   public GraphNode(int key) {
   *     this.key = key;
   *     this.neighbors = new ArrayList<GraphNode>();
   *   }
   * }
   */
  public boolean isBipartite(List<GraphNode> graph) {
    if(graph == null || graph.size() <= 2) {
      return true;
    }

    HashMap<GraphNode, Information> map = new HashMap<>();

    for(GraphNode gn : graph) { //outer loop: find the access node in different clouds
      if(gn.neighbors.size() == 0 || map.get(gn) != null) { //we don't care about those independent nodes or nodes in the clouds we visited before
        continue;
      }

      if(!isBipartiteCloud(map, gn)) {
        return false;
      }
    }

    return true;
  }

  private boolean isBipartiteCloud(HashMap<GraphNode, Information> map, GraphNode start) {
    Queue<GraphNode> q = new LinkedList<>();

    map.put(start, new Information(1));
    q.offer(start);

    while(!q.isEmpty()) {
      GraphNode cur = q.poll();
      map.get(cur).expanded = true;

      for(GraphNode neighbor : cur.neighbors) { //check if all neighbors are in the other group
        Information neighborInfo = map.get(neighbor);
        int curGroup = map.get(cur).group;

        if(neighborInfo == null) { // this node is never explored before
          map.put(neighbor, new Information(curGroup == 1 ? 2 : 1)); // we simply put this node to the other group
        }
        else{ //if we explored this node before, this node must have a group, we check whether they are in different groups
          if(neighborInfo.group == curGroup) {
            return false;
          }
        }

        if(!map.get(neighbor).expanded) { //if this node hasn't been expanded, we put it into the queue
          q.offer(neighbor);
        }
      }

    }

    return true;
  }

  public class Information {
    public boolean expanded = false;
    public int group; //1 and 2 represents its group

    public Information(int group) {
      this.group = group;
    }
  }

  //--------------------------------------------------------------------------------------------------------
  public boolean isCompleted(TreeNode root) {
    if(root == null) {
      return true;
    }

    boolean haveSeenNull = false;
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    while(!q.isEmpty()) {
      TreeNode tn = q.poll();

      if(tn == null && !haveSeenNull) { //we see the first null
        haveSeenNull = true;
      }

      if(tn != null) {
        if(haveSeenNull) { //if see node after first null, then it's not a complete tree
          return false;
        }
        q.offer(tn.left);
        q.offer(tn.right);
      }
    }

    return true;
  }
  //--------------------------------------------------------------------------------------------------------
  public List<List<Integer>> layerByLayer(TreeNode root) {
    if(root == null) {
      return new LinkedList<>();
    }

    List<List<Integer>> outerList = new LinkedList<>();

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    while(!q.isEmpty()) {
      int size = q.size();
      List<Integer> innerList = new LinkedList<>();

      for(int i = 0; i < size; i++) {
        TreeNode tn = q.poll();
        innerList.add(tn.key);

        if(tn.left != null) {
          q.offer(tn.left);
        }

        if(tn.right != null) {
          q.offer(tn.right);
        }
      }
      outerList.add(innerList);
    }

    return outerList;
  }



  public static void main(String[] args) {
    ClassFive c5 = new ClassFive();

    Util.print(c5.isCompleted(Util.buildTree(new int[]{1, 2, 3, 3, 3, 4})));
  }
}
