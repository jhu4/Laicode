import java.util.*;

/**
 * Created by jhu4 on 6/29/17.
 */
public class Util {
    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }
    public static <E> void print(E element) {
        System.out.print(element + " ");
    }

    public static TreeNode buildTree(int[] array) {
        TreeNode top = new TreeNode(array[0]);

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(top);

        int i = 1;

        while (i < array.length) {
            TreeNode tn = q.poll();
            if (tn == null) {
                i += 2;
                continue;
            }

            if (array[i] == -1) { //null node
                q.offer(null);
            } else {
                tn.left = new TreeNode(array[i]);
                q.offer(tn.left);
            }
            i++;

            if (i < array.length) {
                if (array[i] == -1) { //null node
                    q.offer(null);
                } else {
                    tn.right = new TreeNode(array[i]);
                    q.offer(tn.right);
                }
                i++;
            }

        }

        return top;
    }

    public static ListNode buildLinkedlist(int[] array) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i : array) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return dummy.next;
    }

    public static <E> void print(List<E> list) {
        for (E e : list) {
            print(e);
        }
    }

    public static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.print("\n");
    }

    public static void print(TreeNode node) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(node);

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode tn;
            for (int i = 0; i < size; i++) {
                tn = q.poll();
                if (tn == null) {
                    System.out.print(-1 + " ");
                    continue;
                }
                System.out.print(tn.key + " ");
                q.offer(tn.left);
                q.offer(tn.right);
            }
        }

    }

    public static void print(GraphNode n) {
        System.out.print(n.key + "  |  ");
        for (GraphNode neighbor : n.neighbors) {
            System.out.print(neighbor.key + " ");
        }
        System.out.print("/n");
    }

    public static void print(int num) {
        System.out.println(num);
    }

    public static void print(long num) {
        System.out.println(num);
    }

    public static void print(char c) {
        System.out.println(c);
    }

    public static void print(boolean b) {
        System.out.println(b);
    }

    public static void print(String s) {
        System.out.println(s);
    }


}
