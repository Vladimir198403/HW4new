import java.util.Random;
import java.util.Scanner;

public class XO_1 {

    static final int size = 5;
    static final int dotsToWin = 4;

    static final char dot_x = 'X';
    static final char dot_o = 'O';
    static final char dot_empty = '.';

    static int count = 0;

    static char[][] map;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();

            if (checkWinMan()) {
                System.out.println("Вы победили!");
                break;
            }

            if (isFull()) {
                System.out.println("Конец");
                break;
            }

            aiTurn();
            printMap();

            if (checkWinAI()) {
                System.out.println("Компьютер победил!");
                break;
            }
            if (isFull()) {
                System.out.println("Конец");
                break;
            }
        }
    }

    public static void initMap() {//инициализация поля
        map = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = dot_empty;
                //map[0][4] = '9';
            }
        }
    }

    public static void printMap() {//вывод поля в консоль
        System.out.print("  ");
        for (int i = 0; i < size; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void humanTurn() {//ход человека
        int x, y;
        do {
            System.out.println("Введите координаты вашего хода X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));
        map[y][x] = dot_x;
    }

    public static boolean isCellValid(int y, int x) { //проверка ячейки на возможность хода
        if (x < 0 || y < 0 || x >= size || y >= size) {
            return false;
        }
        return map[y][x] == dot_empty;
    }

    public static void aiTurn() {
        int x, y;

        do {
            x = random.nextInt(size);
            y = random.nextInt(size);
        } while (!isCellValid(y, x));
        map[y][x] = dot_o;
    }

    public static boolean isFull() {// проберка поля на заполненность
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == dot_empty) {
                    return false;
                }
            }
        }
        return true;
    }

    /*public static boolean leterWin(){ хотел во всех методах заменить, что бы сократить код, но выдает ошибку. Не разобрался почему
        if (count == dotsToWin - 1) {
            return true;
        }*/

    public static boolean checkWinLine(char c) {// проверка победы по строкам
        //int count = 0;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (map[i][j] == c && map[i][j] == map[i][j + 1]) {
                    count++;
                    if (count == dotsToWin - 1) {
                        return true;
                    }
                }
                if (map[i][j] != map[i][j + 1]) {
                    count = 0;
                }
            }
        }
        return false;
    }

    public static boolean checkWinCol(char c) {//проверка победы по столбцам
        //int count = 0;
        for (int j = 0; j < size - 1; j++) {
            for (int i = 0; i < size - 1; i++) {
                if (map[i][j] == c && map[i + 1][j] == map[i][j]) {
                    count++;
                    if (count == dotsToWin - 1) {
                        return true;
                    }
                }
                if (map[i][j] != map[i + 1][j]) {
                    count = 0;
                }
            }
        }
        return false;
    }

    public static boolean checkWinMajorDiagonal0(char c) { //проверка победы по главной диагонали от 0,0
        //int count = 0;
        for (int i = 0, j = 0; i < size - 1; i++, j++) {
            if (map[i][j] == c && map[i + 1][j + 1] == map[i][j]) {
                count++;
                if (count == dotsToWin - 1) {
                    return true;
                }

            }
            if (map[i][j] != map[i + 1][j + 1]) {
                count = 0;
            }
        }
        return false;
    }

    public static boolean checkWinMajorDiagonalPlus(char c) { //проверка победы по главной диагонали от 1,0
        //int count = 0;
        for (int i = 1, j = 0; i < size - 1; i++, j++) {
            if (map[i][j] == c && map[i + 1][j + 1] == map[i][j]) {
                count++;
                if (count == dotsToWin - 1) {
                    return true;
                }
            }
            if (map[i][j] != map[i + 1][j + 1]) {
                count = 0;
            }
        }
        return false;
    }

    public static boolean checkWinMajorDiagonalMinus(char c) { //проверка победы по главной диагонали от 0,1
        //int count = 0;
        for (int i = 0, j = 1; j < size - 1; i++, j++) {
            if (map[i][j] == c && map[i + 1][j + 1] == map[i][j]) {
                count++;
                if (count == dotsToWin - 1) {
                    return true;
                }
            }
            if (map[i][j] != map[i + 1][j + 1]) {
                count = 0;
            }
        }
        return false;
    }

    public static boolean checkMinorDiagonal0(char c) { // проверка победы по побочной диагонали от 0,4
        //int count = 0;
        for (int i = 0, j = 4; i < size - 1; i++, j--) {
            if (map[i][j] == c && map[i + 1][j - 1] == map[i][j]) {
                count++;
                if (count == dotsToWin - 1) {
                    return true;
                }
            }
            if (map[i][j] != map[i + 1][j - 1]) {
                count = 0;
            }
        }
        return false;
    }

    public static boolean checkWinMinorDiagonalPlus(char c) { //проверка победы по побочной диагонали от 0.3
        //int count = 0;
        for (int i = 0, j = 3; i < size - 2; i++, j--) {
            if (map[i][j] == c && map[i + 1][j - 1] == map[i][j]) {
                count++;
                if (count == dotsToWin - 1) {
                    return true;
                }
            }
            if (map[i][j] != map[i + 1][j - 1]) {
                count = 0;
            }
        }
        return false;
    }

    public static boolean checkWinMinorDiagonalMinus(char c) { //проверка победы по побочной диагонали от 1,4
        //int count = 0;
        for (int i = 1, j = 4; i < size - 1; i++, j--) {
            if (map[i][j] == c && map[i + 1][j - 1] == map[i][j]) {
                count++;
                if (count == dotsToWin - 1) {
                    return true;
                }
            }
            if (map[i][j] != map[i + 1][j - 1]) {
                count = 0;
            }
        }
        return false;
    }

    public static boolean checkWinMan() {
        if (checkWinLine(dot_x) == true || checkWinCol(dot_x) == true || checkWinMajorDiagonal0(dot_x) == true || checkWinMajorDiagonalPlus(dot_x) == true
                || checkWinMajorDiagonalMinus(dot_x) == true || checkMinorDiagonal0(dot_x) == true || checkWinMinorDiagonalPlus(dot_x) == true
                || checkWinMinorDiagonalMinus(dot_x) == true) {
            return true;
        }
        return false;
    }

    public static boolean checkWinAI() {
        if (checkWinLine(dot_o) == true || checkWinCol(dot_o) == true || checkWinMajorDiagonal0(dot_o) == true || checkWinMajorDiagonalPlus(dot_o) == true
                || checkWinMajorDiagonalMinus(dot_o) == true || checkMinorDiagonal0(dot_o) == true || checkWinMinorDiagonalPlus(dot_o) == true
                || checkWinMinorDiagonalMinus(dot_o) == true) {
            return true;
        }
        return false;
    }
}

// Итого, работа, вероятно топорная, но этот код, полностью доработан самостоятельно.
// Есть идеи по ИИ для хода Ai. Из спортивного интереса буду писать в свободное от работы время
