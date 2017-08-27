public class ClassEleven {
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
  //---------------------------------------------------------------------------
  public boolean allUnique(String word) {
    int[] bitmap = new int[8]; //bitmap

    for (char c: word.toCharArray()) {
      int row = c / 32; //it's c not c - 'c' because the range is the entire ASCII code
      int column = c % 32;
      int mask = 1 << (column - 1);

      if ((bitmap[row] & mask) != 0) { //if we had this character before
        return false;
      } else {
        bitmap[row] |= mask; //we mark on the bitmap
      }
    }

    return true;
  }

  //---------------------------------------------------------------------------
  public String hex(int number) {
    if (number == 0) {
      return "0x0";
    }

    String prefix = "0x";
    StringBuilder sb = new StringBuilder();

    while(number != 0) {
      int value = number % 16;
      if (value < 10) {
        sb.append((char)(value + '0'));
      } else {
        sb.append((char)(value - 10 + 'A'));
      }
      number >>>= 4;
    }

    return prefix + sb.reverse().toString();
  }

  public static void main(String[] args) {
    ClassEleven c11 = new ClassEleven();
    Util.print(c11.hex(1115));
  }
}
