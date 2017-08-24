public class ClassTen {
  public boolean isPowerOfTwo(int number) {
    if (number <= 0) {
      return false;
    }

    int count = 0;
    while (number > 0) {
      count += number & 1;
      number >>= 1;
    }
    return count == 1;
  }

  /*
  public boolean isPowerOfTwo(int number) {
    //if assume that the input is a positive number
    return number != 0 && (number & (number - 1)) == 0;
  }
  */

  //---------------------------------------------------------------------------
  public int diffBits(int a, int b) {
    int count = 0;
    for (int c = a ^ b; c != 0; c >>>= 1) { //>>> is a logical right shift, writing 0s into the leftmost bit
      count += c & 1;
    }

    return count;
  }

  public static void main(String[] args) {
    ClassTen c10 = new ClassTen();
    Util.print(c10.diffBits(2147483647,-2147483648));
  }
}
