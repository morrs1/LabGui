package Programming.FourthSemLab.FifthLab;

import java.util.ArrayList;
import java.util.List;

public class DList<T1, T2> {
  private final List<T1> list1;
  private final List<List<T2>> list2;

  public DList() {
    list1 = new ArrayList<>();
    list2 = new ArrayList<>();
  }

  // Добавление нового значения и списка значений
  public void add(T1 value1, List<T2> values2) {
    list1.add(value1);
    list2.add(values2);
  }

  // Получение значения и списка значений по позиции
  public Pair<T1, List<T2>> get(int index) {
    if (index < 0 || index >= list1.size()) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    return new Pair<>(list1.get(index), list2.get(index));
  }

  // Получение значения и списка значений по значению первого поля
  public Pair<T1, List<T2>> getByElement(T1 value1) {
    int index = list1.indexOf(value1);
    if (index == -1) {
      throw new IllegalArgumentException("Value not found");
    }
    return new Pair<>(list1.get(index), list2.get(index));
  }

  // Удаление значения и списка значений по позиции
  public void remove(int index) {
    if (index < 0 || index >= list1.size()) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    list1.remove(index);
    list2.remove(index);
  }

  // Удаление значения и списка значений по значению первого поля
  public void remove(T1 value1) {
    int index = list1.indexOf(value1);
    if (index == -1) {
      throw new IllegalArgumentException("Value not found");
    }
    list1.remove(index);
    list2.remove(index);
  }

}
