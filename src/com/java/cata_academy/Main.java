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
        String secondNumString = array[2];

        int firstNum, secondNum;
        if (Check.checkNumberSystem(firstNumString) && Check.checkNumberSystem(secondNumString)) {
            firstNum = Convert.convertRomanToArabic(firstNumString);
            secondNum = Convert.convertRomanToArabic(secondNumString);
            int result = arithmeticOperation(firstNum, secondNum, operation);
            return Convert.convertArabicToRoman(result);
        } else {
            try {
                firstNum = Convert.convertStringToNumber(firstNumString);
                secondNum = Convert.convertStringToNumber(secondNumString);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Используются одновременно разные системы счисления");
            }
            Check.checkRangeArabic(firstNum, secondNum);
            return Integer.toString(arithmeticOperation(firstNum, secondNum, operation));
        }
    }

    private static class Check {
        static String[] checkFormat(String input) {
            String[] arrayResult = new String[3];
            String[] arraySource;
            String[] operations = {"+", "-", "*", "/"};

            for (String operation : operations) {
                if (input.contains(operation)) {
                    arrayResult[1] = operation; // математическая операция
                    break;
                }
            }
            if (arrayResult[1] == null) {
                throw new IllegalArgumentException("Не правильно введено математическое выражение");
            }

            arraySource = input.replaceAll("\\s", "").split("[+\\-/*]");
            arrayResult[0] = arraySource[0]; // первый операнд
            arrayResult[2] = arraySource[1]; // второй операнд

            if (arraySource.length != 2) {
                throw new IllegalArgumentException("Неверный формат математического выражения");
            }

            if (!(checkNumberSystem(arrayResult[0]) || checkStringIsNumeric(arrayResult[0]))) {
                throw new NumberFormatException("Неверно введено первое число");
            }
            if (!(checkNumberSystem(arrayResult[2]) || checkStringIsNumeric(arrayResult[2]))) {
                throw new NumberFormatException("Неверно введено второе число");
            }

            return arrayResult;
        }


        static void checkRangeArabic(int num1, int num2) {
            if (num1 <= 0 || num1 >= 11 || num2 <= 0 || num2 >= 11) {
                throw new IllegalArgumentException("Диапазон введенных чисел должен быть "
                        + "от 1 до 10 в арабском формате");
            }
        }

        static boolean checkNumberSystem(String num) {
            return Arrays.asList(Convert.romanNum).contains(num);
        }

        static boolean checkStringIsNumeric(String input) {
            if (input.equals("")) {
                return false;
            }
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    if (input.charAt(i) == '.') continue;
                    return false;
                }
            }
            return true;
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