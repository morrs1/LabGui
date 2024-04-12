package Architectures;

import java.util.Scanner;

public class ConversionNumberSystem {

  private static final Scanner scanner = new Scanner(System.in);


  private static String convert(String firstSystem, String secondSystem, String number) {
    return convertFrom10System(Integer.parseInt(secondSystem), convertTo10System(firstSystem, number)).toString();
  }

  private static Integer convertTo10System(String System, String number) {
    int base = Integer.parseInt(System);
    char[] charArr = number.toCharArray();
    int res = 0;
    int count = 0;

    for (int i = charArr.length - 1; i >= 0; i--) {
      int digitValue;
      char currentChar = charArr[i];

      if (Character.isDigit(currentChar)) {
        digitValue = Character.getNumericValue(currentChar);
      } else if (Character.isLetter(currentChar)) {
        digitValue = Character.toUpperCase(currentChar) - 'A' + 10;
      } else {
        throw new IllegalArgumentException("Недопустимый символ: " + currentChar);
      }

      if (digitValue >= base) {
        throw new IllegalArgumentException("Значение " + digitValue + " больше или равно основанию системы счисления " + base);
      }

      res += (int) (digitValue * Math.pow(base, count));
      count += 1;
    }

    return res;
  }

  private static String convertFrom10System(Integer system, Integer number) {
    if (system < 2 || system > 36) {
      throw new IllegalArgumentException("Основание системы счисления должно быть в диапазоне от 2 до 36");
    }

    StringBuilder result = new StringBuilder();
    while (number > 0) {
      int remainder = number % system;
      if (remainder < 10) {
        result.insert(0, remainder); // Добавляем остаток в начало строки
      } else {
        // Используем буквы для представления чисел от 10 до 35
        result.insert(0, (char) ('A' + (remainder - 10)));
      }
      number /= system; // Делим число на основание системы счисления
    }

    return result.toString();
  }

  public static void main(String[] args) {
    System.out.println("Введите основание системы счисления из который происходит перевод: ");
    var str1 = scanner.nextLine();
    System.out.println("Введите основание системы счисления в которую происходит перевод: ");
    var str2 = scanner.nextLine();
    System.out.println("Введите число: ");
    var str3 = scanner.nextLine();
    System.out.println(convert(str1, str2, str3));
  }
}
