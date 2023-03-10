package com.java.cata_academy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static String inputLine() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static int arithmeticOperation(int a, int b, String operation) {
        return switch (operation) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Неверная арифметическая операция");
        };
    }

    public static String calc(String input) {
        String[] array = Check.checkFormat(input);
        String firstNumString = array[0];
        String operation = array[1];
        String secondNumString = array[array.length - 1];
        int a, b;
        if (Check.checkNumberSystem(firstNumString) & Check.checkNumberSystem(secondNumString)) {
            a = Convert.convertRomanToArabic(firstNumString);
            b = Convert.convertRomanToArabic(secondNumString);
            int result = arithmeticOperation(a, b, operation);
            return Convert.convertArabicToRoman(result);
        } else {
            try {
                a = Convert.convertStringToNumber(firstNumString);
                b = Convert.convertStringToNumber(secondNumString);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Используются одновременно разные системы счисления");
            }
            Check.checkRange(a, b);
            return Integer.toString(arithmeticOperation(a, b, operation));
        }
    }

    private static class Check {
        static String[] checkFormat(String input) {
            String[] array = input.trim().split(" ");
            int length = array.length;
            if (length != 3) {
                throw new IllegalArgumentException("Неверный формат математического выражения");
            }
            return array;
        }

        static void checkRange(int a, int b) {
            if (a <= 0 || a >= 11 || b <= 0 || b >= 11) {
                throw new IllegalArgumentException("Диапазон введенных чисел должен быть "
                        + "от 1 до 10 в арабском формате");
            }
        }

        static boolean checkNumberSystem(String num) {
            return Arrays.asList(Convert.romanNum).contains(num);
        }
    }

    private static class Convert {
        static String[] romanNum = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII",
                "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV",
                "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV",
                "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV",
                "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        private static String convertArabicToRoman(int input) {
            if (input <= 0) {
                throw new NumberFormatException("Отрицательное число. " +
                        "В римской системе нет отрицательных чисел");
            }
            return romanNum[input];
        }

        private static int convertRomanToArabic(String input) {
            return switch (input) {
                case "I" -> 1;
                case "II" -> 2;
                case "III" -> 3;
                case "IV" -> 4;
                case "V" -> 5;
                case "VI" -> 6;
                case "VII" -> 7;
                case "VIII" -> 8;
                case "IX" -> 9;
                case "X" -> 10;
                default -> throw new IllegalArgumentException("Диапазон введенных чисел должен быть " +
                        "от I до X в римском формате");
            };
        }

        private static int convertStringToNumber(String input) {
            return Math.round(Float.parseFloat(input));
        }
    }

    public static void main(String[] args) {
        String inputConsole = inputLine();
        System.out.println(calc(inputConsole));
    }
}