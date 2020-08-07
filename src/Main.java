import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    ArrayList<Integer> arrayThree = new ArrayList<>();
    ArrayList<Integer> arraySeven = new ArrayList<>();
    ArrayList<Integer> arrayTwentyOne = new ArrayList<>();
    boolean anyMoreElement = false;
    int[] arrayNumber;

    //Если создаётся объект класса и не передаётся массив
    // создаётся массив с 20ю числами от 0 до 1000
    Main() {
        arrayNumber = new int[20];
        randomArray(arrayNumber);
    }

    Main(int[] inputArray) {
        arrayNumber = inputArray;
    }

    //Метод инициализирует массив случайными числами от 0 до 1000
    void randomArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) (Math.random() * 1001);
            }
        }
    }

    void initArray(int[] arrayIn) {
        if (arrayIn == null || arrayIn.length == 0) {
            System.out.println("Входной массив пуст");
            return;
        }
        boolean inList = false;
        // создаём списки содержащие числа делящиеся на 3 и на 7
        for (int i = 0; i < arrayIn.length; i++) {
            if (arrayIn[i] % 3 == 0) {
                arrayThree.add(arrayIn[i]);
                inList = true;
            }
            if (arrayIn[i] % 7 == 0) {
                arraySeven.add(arrayIn[i]);
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

        // т.к. числа делящиеся на 21 делятся и на 3 и на 7,
        // при этом список чисел делящихся на 7 ожидаемо меньше
        // поэтому перебираем именно его
        for (Integer i : arraySeven) {
            if (i.intValue() % 21 == 0) {
                arrayTwentyOne.add(i);
            }
        }
    }

    void print() {
        print('x');
        print('s');
        print('m');
    }

    void print(char type) {
        if (type == 'x') {
            if (arrayThree.size() != 0) {
                System.out.print("Числа, делящиеся на 3 (Список X): ");
                System.out.println(arrayThree.toString());
            } else {
                System.out.println("Список X пуст");
            }
        }
        if (type == 's') {
            if (arraySeven.size() != 0) {
                System.out.print("Числа, делящиеся на 7 (Список S): ");
                System.out.println(arraySeven.toString());
            } else {
                System.out.println("Список S пуст");
            }
        }
        if (type == 'm') {
            if (arraySeven.size() != 0) {
                System.out.print("Числа, делящиеся на 21 (Список M): ");
                System.out.println(arrayTwentyOne.toString());
            } else {
                System.out.println("Список M пуст");
            }
        }
    }

    void anyMore() {
        System.out.println(anyMoreElement);
    }

    void clear(char type) {
        if (type == 'x' || type == 'X') {
            arrayThree.clear();
        }
        if (type == 's' || type == 'S') {
            arraySeven.clear();
        }
        if (type == 'm' || type == 'M') {
            arrayTwentyOne.clear();
        }
    }

    void merge() {
        arrayThree.addAll(arraySeven);
        arrayThree.addAll(arrayTwentyOne);
        Collections.sort(arrayThree);
        if (arrayThree.size() != 0) {
            System.out.println(arrayThree.toString());
        } else {
            System.out.println("Общий список пуст");
        }
        clear('x');
        clear('s');
        clear('m');
    }

    void help() {
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
    }

    void readAndRunCommand() {
        Scanner scanner = new Scanner(System.in);
        String in;
        System.out.println("Введите команду. Для справки введите help");
        System.out.println("Команды не чувствительны к регистру.");
        do {
            in = scanner.nextLine().trim().toLowerCase();
            switch (in) {
                case "init array":
                    initArray(arrayNumber);
                    System.out.println("Команда выполнена, введите следующую");
                    break;
                case "print":
                    print();
                    System.out.println("Команда выполнена, введите следующую");
                    break;
                case "print x":
                    print('x');
                    System.out.println("Команда выполнена, введите следующую");
                    break;
                case "print s":
                    print('s');
                    System.out.println("Команда выполнена, введите следующую");
                    break;
                case "print m":
                    print('m');
                    System.out.println("Команда выполнена, введите следующую");
                    break;
                case "anymore":
                    anyMore();
                    System.out.println("Команда выполнена, введите следующую");
                    break;
                case "clear x":
                    clear('x');
                    System.out.println("Команда выполнена, введите следующую");
                    break;
                case "clear s":
                    clear('s');
                    System.out.println("Команда выполнена, введите следующую");
                    break;
                case "clear m":
                    clear('m');
                    System.out.println("Команда выполнена, введите следующую");
                    break;
                case "merge":
                    merge();
                    System.out.println("Команда выполнена, введите следующую");
                    break;
                case "help":
                    help();
                    System.out.println("Команда выполнена, введите следующую");
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
        // конструктор с параметром: Main object = new Main(int[] array);
        Main object = new Main();
        object.readAndRunCommand();
    }
}