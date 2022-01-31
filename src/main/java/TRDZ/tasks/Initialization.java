package TRDZ.tasks;

import java.util.Arrays;
import java.util.Scanner;

public class Initialization {

//region Обьявление переменных
    private static final String[] accept = {"Yes","yes","1","true","True","Да","да","+"}; //Задаем перечень согласия
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isCorrect;
//endregion

    public static void main(String[] args) {
        boolean fin=true;
        int chose;
        System.out.println("Добро пожаловать в игру \"Угадай что\"");
        System.out.println("Выберите сложность:");
        System.out.println(" 1 - Угадай число от 0 до 9");
        System.out.println(" 2 - Плод (фрукт, ягода, офощь...) на английском");
        while (fin) {
            System.out.println("\nВвод:");
            isCorrect = scanner.hasNextInt();   //Проверка на некоректный ввод
            if (!isCorrect) {chose = 0;}
            else {chose = scanner.nextInt();}
            scanner.nextLine();
            switch (chose) {
            case 1:                             //Начало программы 1. Угадай число
                System.out.println("Начинаем игру угадай число!");
                guess_number();
                fin=false;
                break;
            case 2:                             //Начало программы 2. Угадай плод
                System.out.println("Начинаем игру угадай плод!");
                String[] guess = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
                guess_food(guess[(int)(Math.random()*(guess.length-1))]);
                fin=false;
                break;
            default:                            //Разбор прочих вариантов ввода
                System.out.println("Ввод не разборчив. Возможно вы хотите выйти?");
                String close = scanner.next();
                boolean result = Arrays.stream(accept).anyMatch(close::equals);
                if (result) {System.out.println("Закрываю."); fin=false;}          //Отмена здесь
                else {scanner.nextLine();}
                }
            }
        scanner.close();
        }

    /**
     * Игра где нужно угадывать числа и можно играть бесконечно
     */
    public static void guess_number() {
    //region Обьявление переменных guess_number
        boolean result = true;
        int number = (int) (Math.random()*9);
        int guess;
    //endregion

        System.out.println("Я загадал число от 0 до 9 ");
        while (result) {
            for (int i=3;i>0;i--){
                System.out.println("\nУ тебя есть "+i+" попытки угадать число.");
                isCorrect = scanner.hasNextInt();   //Проверка на некоректный ввод
                if (!isCorrect) {
                    System.out.println("Но это даже не число... Не считается");
                    scanner.nextLine();
                    i++;
                    }
                else {
                    guess = scanner.nextInt();
                    if (guess==number) {System.out.println("Да. Ты угадал.");
                        i=0;}
                    else if (guess>number) System.out.println("Нет. загаданное число меньше.");
                    else System.out.println("Нет. загаданное число больше.");
                    }
                if (i==1) {System.out.println("Жаль но ты так и не угадал. Это было "+number);}
                }                                           //Конец игры
            System.out.println("\nБудешь еще играть?");
            String close = scanner.next();
            result = Arrays.stream(accept).anyMatch(close::equals);
            number = (int) (Math.random()*9);               //Новое число
            scanner.nextLine();
            }
        System.out.println("Досвидания");                   //Выход здесь
        }

    /**
     * Игра где нужно угадывать плоды
      * @param food загаданый плод
     */
    public static void guess_food(String food) {
    //region Обьявление переменных guess_food
        String guess;
        String[] Help = new String[15];
        boolean win=false;
    //endregion

        System.out.println("Я загадал одно из: ");
        System.out.println("{ apple orange lemon banana apricot avocado broccoli carrot");
        System.out.println("  cherry garlic grape melon leak kiwi mango mushroom nut olive");
        System.out.println("   pea peanut pear pepper pineapple pumpkin potato}");
        System.out.println(" Отгадай какое? И введи без регистров(все буквы слова с маленькой)");
        while (!win) {
            System.out.println("Ввод:");
            guess = scanner.next();
            scanner.nextLine();
            if (guess.equals(food)) {
                System.out.println("Да ты угадал. Это "+food);
                win=true;
                }
            else {
                System.out.println("Нет ты не угадал");
                System.out.println("Вот тебе подсказка. Откройте совпавшие буквы!");
                for (int i=0; i<food.length()&&i<guess.length(); i++) {
                    if (food.charAt(i)==guess.charAt(i)){Help[i]=Character.toString(food.charAt(i));} //Записываем угаданное
                    }
                if (Arrays.stream(Help).anyMatch(x -> x!=null))             //проверяем на полное несовпадение
                    { for (String element : Help) {                         //Выводим подсказку
                        if (element!= null) {System.out.print(element);}
                        else {System.out.print("_");}}}
                else {System.out.print("А нет таких. Играем дальше");}
                System.out.println("\n");
                }
            }
        System.out.println("Досвидания");                       //Выход здесь
        }
    }

