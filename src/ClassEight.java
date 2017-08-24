import java.util.*;

public class ClassEight {
	public List<String> permutations(String set) {
		List<String> list = new LinkedList<>();
		if (set == null) {
			return list;
		}

		char[] array = set.toCharArray();

		permutation(array, 0, list);

		return list;
	}

	public void permutation(char[] array, int index, List<String> list) {
		if (index >= array.length - 1) {
			list.add(new String(array));
			return;
		}

		HashSet<Character> hs = new HashSet<>();

		for (int i = index; i < array.length; i++) {
			if (!hs.contains(array[i])) { //make sure we didn't put duplicate characters into i-th index in the array
				hs.add(array[i]);
				swap(array, i, index);
				permutation(array, index + 1, list); //not i + 1 but index + 1 AHHHHHHHH!
				swap(array, i, index);
			}
		}
	}

	private void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	//----------------------------------------------------------------------------------------
	public String reverseWords(String input) {
		if (input == null) {
			return null;
		}
		
		int left = 0, right = input.length() - 1;
		char[] string = input.toCharArray();
		
		//reverse the entire string first
		while (left < right) {
			swap(string, left++, right--);
		}
		
		left = 0;
		right = 0;
		//reverse words individually
		while (right < input.length()) {
			if (string[right] == ' ') { //we found a entire word
				reverse(string, left, right - 1);
				left = right + 1;
			}
			right++;
		}
		
		//reverse the last word
	    reverse(string, left, right - 1);
	
	    return new String(string);
	  }
	
//	private void swap(char[] array, int left, int right) {
//	  	char temp = array[left];
//	  	array[left] = array[right];
//	  	array[right] = temp;
//	}
	
	private void reverse(char[] array, int left, int right) {
	  	while (left < right) {
	  		swap(array, left++, right--);
	  	}
	}
	//----------------------------------------------------------------------------------------
	public String reverse(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}
		char[] array = s.toCharArray();
		
		reverseChar(array, 0, s.length() - 1);
	
		return new String(array);
	}
	
	public void reverseChar(char[] array, int start, int end) {
		if (start >= end) {
			return;
		}
	
		swapChar(array, start, end);
		reverseChar(array, start + 1, end - 1);
	}
	
	public void swapChar(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	//---------------------------------------------------------------------------------------
	public int[] reorder(int[] array) {
		if (array == null) {
			return array;
		}

		reorder(array, 0, array.length % 2 == 0 ? array.length - 1: array.length - 2);
		return array;
	}

	public void reorder(int[] array, int left, int right) {
		if (right - left <= 1) {
			return;
		}

		int size = right - left + 1;
		int leftmid = left + size / 4;
		int mid = left + size / 2;
		int rightmid = mid + size / 4;

		reverseIntArray(array, leftmid, mid - 1);
		reverseIntArray(array, mid, rightmid - 1);
		reverseIntArray(array, leftmid, rightmid - 1);

		reorder(array, left, leftmid + size / 4 - 1);
		reorder(array, leftmid + size / 4, right);
	}

	public void reverseIntArray(int[] array, int start, int end) {
		while (start < end) {
			int temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			start++;
			end--;
		}
	}
	//----------------------------------------------------------------------------------------
	public int longest(String input) {
		Map<Character, Integer> map = new HashMap<>();
		int startIndex = 0;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < input.length(); i++) {
			Integer index = map.get(input.charAt(i));


			if (index != null && index >= startIndex) { //if the character is in the result substring
				startIndex = index + 1; //we update the new start index and exclude the previous character
			} else {
				max = Math.max(max, i + 1 - startIndex);
			}

			map.put(input.charAt(i), i);
		}

		return max;
	}

	//----------------------------------------------------------------------------------------
	public String rightShift(String input, int n) {
		if (input.length() == 0) {
			return input;
		}

		char[] array = input.toCharArray();
		int stringLen = input.length();
		if (n > stringLen) { //it's possible that n >　input.length()
			n %= stringLen;
		}

		reverseArray(array, 0, stringLen - n - 1);
		reverseArray(array, stringLen - n, stringLen - 1);
		reverseArray(array, 0, stringLen - 1);

		return new String(array);
	}

	public void reverseArray(char[] array, int start, int end) {
		while (start < end) {
			char temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			start++;
			end--;
		}
	}


	//----------------------------------------------------------------------------------------
	public static void main(String[] args) {
		ClassEight c8 = new ClassEight();
		HashSet<Integer> hm = new HashSet<>();

		Util.print(c8.longest("a"));
	}
}
