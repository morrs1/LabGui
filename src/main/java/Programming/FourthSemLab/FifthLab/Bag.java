package Programming.FourthSemLab.FifthLab;

import java.util.Random;

public class Bag {

  private Object[] arr;

  public Bag(int size) {
    this.arr = new Object[size];
  }

  public int getCurrentSize() {
    var count = 0;
    for (var o : arr) {
      if (o != null) {
        count += 1;
      }
    }
    return count;
  }

  public boolean add(Object o) {
    Random random = new Random();
    int index = random.nextInt(arr.length);
    var count = 0;
    while (true) {
      if (arr[index] == null) {
        arr[index] = o;
        return true;
      }
      if(getCurrentSize() == arr.length) {
        return false;
      }
      index = random.nextInt(arr.length);
    }
  }

  public Object remove() {
    while (true) {
      int index = new Random().nextInt(arr.length);;
      if (arr[index] != null) {
        Object item = arr[index];
        arr[index] = null;
        return item;
      }
    }
  }

}
