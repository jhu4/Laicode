/**
 * Created by jhu4 on 7/16/17.
 */
import java.util.*;
public class GraphNode {
  public int key;
  public List<GraphNode> neighbors;
  public GraphNode(int key) {
    this.key = key;
    this.neighbors = new ArrayList<GraphNode>();
  }
}
