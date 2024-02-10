package FourSemLab.SecondLab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите номер задания, которое хотите выполнить: ");
            var task = scanner.nextInt();
            switch (task) {
                case 1 -> {
                    task1();
                }
                case 2 -> {
                }
                case 3 -> {
                }
                case 4 -> {
                }
                case 5 -> {
                }
                case 6 -> {
                }
                case 7 -> {
                }
                case 8 -> {
                }
                case 9 -> {
                }
                case 10 -> {
                }
                default -> {
                    return;
                }
            }
        }
    }


    private static void task1(){
        System.out.println("Data Type\tSize\tMin Value\tMax Value");
        System.out.println("Byte\t\t" + Byte.SIZE + "\t\t" + Byte.MIN_VALUE + "\t\t" + Byte.MAX_VALUE);
        System.out.println("Short\t\t" + Short.SIZE + "\t\t" + Short.MIN_VALUE + "\t\t" + Short.MAX_VALUE);
        System.out.println("Int\t\t" + Integer.SIZE + "\t\t" + Integer.MIN_VALUE + "\t\t" + Integer.MAX_VALUE);
        System.out.println("Long\t\t" + Long.SIZE + "\t\t" + Long.MIN_VALUE + "\t\t" + Long.MAX_VALUE);
        System.out.println("Float\t\t" + Float.SIZE + "\t\t" + Float.MIN_VALUE + "\t\t" + Float.MAX_VALUE);
        System.out.println("Double\t\t" + Double.SIZE + "\t\t" + Double.MIN_VALUE + "\t\t" + Double.MAX_VALUE);

    }
    private static void task2(){

    }
}
