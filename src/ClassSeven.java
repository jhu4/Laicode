import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ClassSeven {
	public String[] topKFrequent(String[] combo, int k) {
		HashMap<String, Integer> map = new HashMap<>();
		PriorityQueue<VKPair> pq = new PriorityQueue<>();
		int counter = 0;
		
		for (String s : combo) {
			if (map.get(s) == null) {
				map.put(s, 1);
			}
			else {
				map.put(s, map.get(s) + 1);
			}
		}
		
		int listSize = Math.min(map.keySet().size(),k);
		String[] res = new String[listSize];
		
		for (String key : map.keySet()) {
			if (counter < k) {
				pq.offer(new VKPair(key, map.get(key)));
				counter++;
			}
			else if (map.get(key) > pq.peek().value) {
				pq.poll();
				pq.offer(new VKPair(key, map.get(key)));
			}
		}
		
		for (int i = listSize - 1; i >= 0; i--) {
			res[i] = pq.poll().key;
		}
		
		return res;
	}
	
	public class VKPair implements Comparable<VKPair> {
		String key;
		int value;
		
		public VKPair(String k, int val) {
			this.value = val;
			this.key = k;
		}
		public int compareTo(VKPair p) {
			return this.value - p.value;
		}
	}
	
	//----------------------------------------------------------------------------------------------
	public int missing(int[] array) {
		int xor = 0;
		for (int i = 1; i <= array.length + 1; i++) {
			xor ^= i;
		}
		for (int i : array) {
			xor ^= i;
		}
		return xor;
	}
	

	//----------------------------------------------------------------------------------------------
	public List<Integer> common(List<Integer> A, List<Integer> B) {
		List<Integer> temp;
		List<Integer> res = new LinkedList<Integer>();
		if (B.size() < A.size()) {
			temp = A;
			A = B;
			B = temp;
		} 

		HashMap<Integer, Integer> map = new HashMap<>();

		for (Integer i : A) {
			if (map.get(i) == null) {
				map.put(i, 1);
			} 
			else {
				map.put(i, map.get(i) + 1);
			}
		}

		for (Integer b : B) {
			Integer value = map.get(b);
			if(value != null && value > 0) {
				res.add(b);
				map.put(b, value - 1); 
			}
		}

		return res;
	}
	//----------------------------------------------------------------------------------------------
	public String deDup(String input) {
		if (input == null || input.length() == 1) {
			return input;
		}

		StringBuilder sb = new StringBuilder();
		char prev = Character.MIN_VALUE;

		for (char c : input.toCharArray()) {
			if (c != prev) {
				sb.append(c);
				prev = c;
			}
		}
		
		return sb.toString();
	}
	//----------------------------------------------------------------------------------------------
	public String deDup2(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}

		StringBuilder sb = new StringBuilder(input);
		int slow = 0, fast = 1;

		while (fast < input.length()) {
			if (slow < 0) { //case where all the leading duplicates were removed
				sb.setCharAt(0, sb.charAt(fast));
				slow = 0;
				fast++;
			}
			else if (sb.charAt(slow) == sb.charAt(fast)) {
				do {
					fast++;
				} 
				while (fast < input.length() && sb.charAt(fast) == sb.charAt(slow)); //don't forget to check whether
				slow--; 													//fast is out of bound
			}
			else {
				slow++;
				sb.setCharAt(slow, sb.charAt(fast));
				fast++;
			}
		}

		return sb.toString().substring(0, slow + 1);
	} 
	//----------------------------------------------------------------------------------------------
	public int strstr(String large, String small) {
		if (small.length() > large.length()) {
			return -1;
		}
		if (small.length() == 0) {
			return 0;
		}
		int hashCode = 0, window = 0;
		int slow = 0, fast = small.length();

		int slen = small.length();

		for (int i = 0; i < small.length(); i++) {
			window += Math.pow(26, slen - 1 - i) * toValue(large.charAt(i));
			hashCode += Math.pow(26, slen - 1 - i) * toValue(small.charAt(i));
		}


		if (window == hashCode) {
			return slow;
		}
		
		while (fast < large.length()) {
			window -= toValue(large.charAt(slow++)) * Math.pow(26, slen - 1);
			window *= 26;
			window += toValue(large.charAt(fast++));
			if (window == hashCode) {
				return slow;
			}
		}

		return - 1;
	}
	
	private int toValue(char c) {
		return c - 'a' + 1;
	}
	//----------------------------------------------------------------------------------------------
	public String removeSpaces(String input) {
		int slow = 0, fast = 0;
		boolean firstWordFound = false;

		StringBuilder sb = new StringBuilder(input);

		while (fast < sb.length()) {
			//skip the leading spaces 
			while (fast < sb.length() && sb.charAt(fast) == ' ') {
				fast++;
			}
			//check whether we are at the end of the string
			if (fast >= sb.length()) {
				return sb.toString().substring(0, slow);
			}

			//adding a white space if the following word is not the first word
			if (firstWordFound) {
				sb.setCharAt(slow++, ' ');

			} else {
				firstWordFound = true;
			}

			//copy the word
			while (fast < sb.length() && sb.charAt(fast) !=' ') {
				sb.setCharAt(slow++, sb.charAt(fast++));
			}
		}

		return sb.toString().substring(0, slow);
	}
	//----------------------------------------------------------------------------------------------
	public String remove(String input, String t) {
		HashSet<Character> hs = new HashSet<>();

		for (int i = 0; i < t.length(); i++) {
			hs.add(t.charAt(i));
		}

		char[] array = input.toCharArray();
		int slow = 0, fast = 0;

		while (fast < array.length) {
			if (!hs.contains(array[fast])) {
				array[slow] = array[fast];
				slow++;
			}

			fast++;
		}

		return new String(array, 0, slow);
	}
	//----------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		ClassSeven c7 = new ClassSeven();
		System.out.print(c7.remove("aaaaaaaaacaaaaaba","ad"));
	}
}
