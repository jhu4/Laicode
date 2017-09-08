/**
 * Created by jhu4 on 8/29/17.
 */
public class ClassSixteen {
  public void shuffle(int[] array) {
    if (array.length <= 1) {
      return;
    }

    for (int i = array.length; i >= 1; i--) {
      swap(array, i - 1, (int) (Math.random() * i));
    }
  }

  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  //------------------------------------------------------------------------------------------------
  public int random7() {
    int random21;
    do {
      random21 = 5 * RandomFive.random5() + RandomFive.random5();
    }
    while (random21 >= 21);

    return random21 % 7;
  }

  //------------------------------------------------------------------------------------------------
  public int random1000() {
    int random3000;
    do {
      random3000 = 0;
      for (int i = 0; i < 5; i++) {
        random3000 = 5 * random3000 + RandomFive.random5();
      }
    }
    while (random3000 >= 3000);

    return random3000 % 1000;
  }
  //------------------------------------------------------------------------------------------------
  private static class RandomFive {
    public static int random5() {
      return (int) (Math.random() * 5);
    }
  }

  public static void main(String[] args) {
    ClassSixteen c16 = new ClassSixteen();
    Util.print(0.00 == 0);
  }
}
