public class Solution {
  private int count;
  private Integer sample;

  public Solution() {
    count = 0;
  }

  public void read(int value) {
    count++;
    if (Math.random() * count == 0) {
      sample = value;
    }
  }

  public Integer sample() {
    return sample;
  }



}
