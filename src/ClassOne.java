/**
 * Created by jhu4 on 6/28/17.
 */
public class ClassOne {
    public int[] mergeSort(int[] array) {
        if(array == null || array.length <= 1) return array;
        return mergeSort(array, 0, array.length - 1);
    }

    private int[] mergeSort(int[] array, int start, int end){
        if(start >= end) return new int[] {array[start]};

        int[] leftSorted = mergeSort(array, start, start + (end - start) / 2);
        int[] rightSorted = mergeSort(array, start + (end - start) / 2 + 1, end);

        return merge(leftSorted, rightSorted);
    }

    private int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int leftCounter = 0;
        int rightCounter = 0;
        int resCounter = 0;

        while(leftCounter < left.length || rightCounter < right.length) {
            if(leftCounter >= left.length) { //there are only elements left in the right array
                res[resCounter++] = right[rightCounter++];
            }
            else if(rightCounter >= right.length) { //there are only elemnts left in the left array
                res[resCounter++] = left[leftCounter++];
            }
            else if(left[leftCounter] < right[rightCounter]) { //element in the left is smaller than current element in the right
                res[resCounter++] = left[leftCounter++];
            }
            else {
                res[resCounter++] = right[rightCounter++];
            }
        }

        return res;
    }

    public int[] quickSort(int[] array) {
        if(array == null || array.length == 0) {
            return new int[0];
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int start, int end) {
        if(start >= end) return;

        int mid = array[start + (end - start) / 2];
        array[start + (end - start) / 2] = array[end];
        array[end] = mid;

        int left = start, right = end - 1;

        while(left <= right) {
            while(left <= right && array[left] <= mid) left++;
            while(left <= right && array[right] > mid) right--;

            if(left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }

        array[end] = array[left];
        array[left] = mid;


        quickSort(array, start, left - 1);
        quickSort(array, left + 1, end);
    }


    public int[] selectionSort(int[] array) {
        if(array == null || array.length <= 1) return array;
        int minIndex;
        int temp;

        for(int i = 0; i < array.length - 1; i++) { //outer loop counts the starting point
            minIndex = i;
            for(int j = i; j < array.length; j++) { //inner loop finds the index of the minimum number
                if(array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            temp = array[i]; // swapping the minimum element with the start element
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        return array;
    }

    public int[] moveZero(int[] array) {
        if(array.length == 1) return array;

        int left = 0 , right = array.length - 1;
        while(left < right) {
            while(left < right && array[left] != 0) left++;
            while(left < right && array[right] == 0) right--;

            if(left < right) {
                array[left] = array[right];
                array[right] = 0;
            }
        }
        return array;
    }

    public int[] rainbowSort(int[] array) {
        if(array == null || array.length == 0) {
            return new int[0];
        }

        int a = 0, b = 0, c = array.length - 1;
        while(b < c) {
            while(a < c && array[a] == -1) a++;
            if(b < a)
                b = a;
            while(b < c && array[b] == 0) b++;
            while(b < c && array[c] == 1) c--;

            if(b <= c) {
                if(array[b] == -1) {
                    array[b] = array[a];
                    array[a] = -1;
                    a++;
                }
                else if(array[b] == 1) {
                    array[b] = array[c];
                    array[c] = 1;
                    c--;
                }
            }
        }
        return array;
    }



    public static void main(String[] args) {
        ClassOne sol = new ClassOne();

        int[] one = new int[]{1,0,0};
        int[] two = new int[]{};



        Util.print(sol.rainbowSort(one));
    }
}

