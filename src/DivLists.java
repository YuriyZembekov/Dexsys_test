import java.util.*;

/**
 * Класс DivLists реализует следующее задание:
 * 1) Написать приложение на вход которого приходит массив чисел необходимо вывести на консоль три списка
 * числа которые можно поделить без остатка на 3
 * числа которые можно поделить без остатка на 7
 * числа которые можно поделить без остатка на 21
 * <p>
 * 2) необходимо реализовать команды
 * init array - инициализация списков набором значений array
 * print - печать всех списков
 * print type - печать конкретного списка, где type принимает значения X,S,M
 * anyMore - выводит на экран были ли значения не вошедшие ни в один список, возможные значения true, false
 * clear type - чистка списка , где type принимает значения X,S,M
 * merge - слить все списки в один вывести на экран и очистить все списки
 * help - вывод справки по командам
 * для каждого набора значения должы быть отсортированы в порядке возрастания
 * если какой то из списков пустой необходимо напечатать сообщение "Список type пуст" при выводе списка с помощью команд Print
 * Исходный код направить в виде архива или выложить на GitHub
 */

public class DivLists {
    private ArrayList<Integer> arrayThree = new ArrayList<>();
    private ArrayList<Integer> arraySeven = new ArrayList<>();
    private ArrayList<Integer> arrayTwentyOne = new ArrayList<>();
    private boolean anyMoreElement = false;
    private int[] arrayNumber;
    // в переменной содержится длина массива по умолчанию
    private final int defaultNumberArray = 20;
    //нижняя граница диапазона случайных чисел
    private final int lowerLimit = 0;
    //ширина диапазона случайных чисел
    private final int rangeWidth = 1000;
    private HashMap<Character, ArrayList> nameList;

    {
        nameList = new HashMap<>();
        nameList.put('X', arrayThree);
        nameList.put('S', arraySeven);
        nameList.put('M', arrayTwentyOne);
    }

    // Если создаётся объект класса и не передаётся массив
    // создаётся массив со случайными числами
    public DivLists() {
        arrayNumber = new int[defaultNumberArray];
        randomArray(arrayNumber);
    }

    public DivLists(int[] inputArray) {
        arrayNumber = inputArray;
    }

    private void messageOfComplete() {
        System.out.println("Команда выполнена, введите следующую");
    }

    //Метод инициализирует массив случайными числами
    private void randomArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) (Math.random() * (1 + rangeWidth) + lowerLimit);
            }
        }
    }

    private void initArray(int[] arrayIn) {
        if (arrayIn == null || arrayIn.length == 0) {
            System.out.println("Входной массив пуст");
        } else {
            boolean inList = false;
            // создаём списки содержащие числа делящиеся на 3 и на 7
            for (int i = 0; i < arrayIn.length; i++) {
                if (arrayIn[i] % 3 == 0) {
                    arrayThree.add(arrayIn[i]);
                    inList = true;
                }
                if (arrayIn[i] % 7 == 0) {
                    arraySeven.add(arrayIn[i]);
                    // Если inList уже true значит число делится на 3
                    // если сработало условие деления на 7 делаем
                    // вывод что это число делится на 21
                    if (inList) {
                        arrayTwentyOne.add(arrayIn[i]);
                    }
                    inList = true;
                }
                // если элемент вошёл в какой-либо список
                // сбрасываем значение inList
                // иначе помечаем что элемент не вошёл ни в один из списков
                if (inList) {
                    inList = false;
                } else {
                    anyMoreElement = true;
                }
            }
            Collections.sort(arrayThree);
            Collections.sort(arraySeven);
            Collections.sort(arrayTwentyOne);
        }
    }

    private void print() {
        print('X');
        print('S');
        print('M');
    }

    private void print(Character character) {
        if (character != null) {
            character = Character.toUpperCase(character);
            if (nameList.get(character).size() != 0) {
                System.out.print("Список " + character + ": ");
                System.out.println(nameList.get(character).toString());
            } else {
                System.out.println("Список " + character + " пуст ");
            }
        } else {
            System.out.println("Error: Character is null");
        }
    }

    private void anyMore() {
        System.out.println(anyMoreElement);
    }

    private void clear(Character character) {
        nameList.get(character).clear();
    }


    private void merge() {
        arrayThree.addAll(arraySeven);
        arrayThree.addAll(arrayTwentyOne);
        Collections.sort(arrayThree);
        if (arrayThree.size() != 0) {
            System.out.println(arrayThree.toString());
            clear('X');
            clear('S');
            clear('M');
        } else {
            System.out.println("Общий список пуст");
        }
    }

    private void help() {
        System.out.println("init array - инициализация списков набором значений array");
        System.out.println("print - печать всех списков");
        System.out.println("print type - печать конкретного списка, где type принимает значения X,S,M");
        System.out.println("anyMore - выводит на экран были ли значения" +
                " не вошедшие ни в один список, возможные значения true, false");
        System.out.println("clear type - чистка списка , где type принимает значения X,S,M");
        System.out.println("merge - слить все списки в один вывести на экран и очистить все списки");
        System.out.println("help - вывод справки по командам");
        System.out.println("exit - выход из программы");
        System.out.println("Команды не чувствительны к регистру.");
        System.out.println("Список Х - числа делящиеся на 3");
        System.out.println("Список S - числа делящиеся на 7");
        System.out.println("Список M - числа делящиеся на 21");
    }

    // метод для считывания команд из консоли и их выполнения
    public void readAndRunCommand() {
        Scanner scanner = new Scanner(System.in);
        String in;
        System.out.println("Введите команду. Для справки введите help");
        System.out.println("Команды не чувствительны к регистру.");
        do {
            in = scanner.nextLine().trim().toLowerCase();
            switch (in) {
                case "init array":
                    initArray(arrayNumber);
                    messageOfComplete();
                    break;
                case "print":
                    print();
                    messageOfComplete();
                    break;
                case "print x":
                    print('x');
                    messageOfComplete();
                    break;
                case "print s":
                    print('s');
                    messageOfComplete();
                    break;
                case "print m":
                    print('m');
                    messageOfComplete();
                    break;
                case "anymore":
                    anyMore();
                    messageOfComplete();
                    break;
                case "clear x":
                    clear('X');
                    messageOfComplete();
                    break;
                case "clear s":
                    clear('S');
                    messageOfComplete();
                    break;
                case "clear m":
                    clear('M');
                    messageOfComplete();
                    break;
                case "merge":
                    merge();
                    messageOfComplete();
                    break;
                case "help":
                    help();
                    messageOfComplete();
                    break;
                case "exit":
                    System.out.println("Завершение работы программы");
                    break;
                default:
                    System.out.println("Неверная команда. Для справки введите команду help");
            }
        } while (!in.equals("exit"));
    }

    public static void main(String[] args) {
        //Для передачи в программу внешнего массива нужно использовать
        // конструктор с параметром: DivLists object = new DivLists(int[] array);
        DivLists object = new DivLists();
        object.readAndRunCommand();
    }
}