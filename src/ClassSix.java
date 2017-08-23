import java.util.*;

public class ClassSix {
	public List<String> subSets(String set) {
		List<String> res = new LinkedList<>();
		if (set == null) {
			return res;
		}
		
		subSets(set, 0, new StringBuilder(), res);
		return res;
	}
	
	private void subSets(String set, int i, StringBuilder sb, List<String> res) {
		if (i >= set.length()) {
			res.add(sb.toString());
			return;
		}
		
		sb.append(set.charAt(i));
		subSets(set, i + 1, sb, res);
		sb.deleteCharAt(sb.length() - 1);
		
		subSets(set, i + 1, sb, res);
	}
	
	//-----------------------------------------------------------------------------------------------
	public List<String> permutations(String set) {
		List<String> res = new LinkedList<>();

		if (set == null) {
			return res;
		}

		int setlen = set.length();
		permutations(set, new boolean[setlen], setlen, res, new StringBuilder());

		return res;
	}

	private void permutations(String set, boolean[] used, int left, List<String> res, StringBuilder sb) {
		if (left == 0) {
			res.add(sb.toString());
			return;
		}

		for(int i = 0; i < set.length(); i++) {
			if(!used[i]) {
				used[i] = true;
				sb.append(set.charAt(i));
				permutations(set, used, left - 1, res, sb); //DO NOT use left-- OR --left here because the loop still
				sb.deleteCharAt(sb.length() - 1);	   		//need to used left
				used[i] = false;
			}
		}
	}
	
	//-----------------------------------------------------------------------------------------------
	public List<String> validParentheses(int n) {
		List<String> res = new LinkedList<>();
		
		validParentheses(0, 0, n, new StringBuilder(), res);
		return res;
	}
	
	public void validParentheses(int left, int right, int n, StringBuilder sb, List<String> res) {
		if (left == n && right == n) {
			res.add(sb.toString());
			return;
		}
		
		if(left < n) {
			sb.append("(");
			validParentheses(left + 1, right, n, sb, res);
			sb.deleteCharAt(sb.length() - 1);
		}
		
		if (left > right && right < n) { //we can only add the right parentheses if there are more left parentheses
			sb.append(")");
			validParentheses(left, right + 1, n, sb, res);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	
	//-----------------------------------------------------------------------------------------------
	public List<List<Integer>> combinations(int target, int[] coins) {
		List<List<Integer>> res = new LinkedList<>();

		combinations(target, coins, 0, new LinkedList<>(), res);
		
		return res;	
	}

	private void combinations(int left, int[]coins, int index, List<Integer> list, List<List<Integer>> res) {
		if (index == coins.length - 1) { //base case: last layer, we do some special operation in the last recursion
			if (left % coins[index] == 0) { //if the target can be formed by several last coins, then we add the list 
				/** The code in the comment below is wrong. 
				 	Since we add the last amount to the list but didn't remove it
					
					list.add(left / coins[index]);
					List<Integer> clone = new LinkedList<>();
					clone.addAll(list);
					res.add(clone);
				**/
				List<Integer> clone = new LinkedList<>();
				clone.addAll(list);
				clone.add(left / coins[index]);
				res.add(clone); //make sure to clone the list
			}
			return;
		}

		int maxAmount = left / coins[index];
		for (int i = 0; i <= maxAmount; i++) {
			list.add(i);
			combinations(left - i * coins[index], coins, index + 1, list, res);
			list.remove(list.size() - 1);
		}
	}
	

	//----------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		ClassSix c6 = new ClassSix();
	}
	

	
}
