package p4.分治回溯;

import java.io.*;
//数独
public class Sudoku {
    private static int i = 0;
    private static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        readFile("sudoku_data_01.txt");
        solve(0, 0);
    }

    //求解x-y格子的解 再继续向下递归求解下一个格子
    //本质求多个解 但实际 数独问题只能有一个解 如果没解 程序啥也不输出！
    private static void solve(int row, int col) {
        if (row == 9) {
            i++;
            System.out.println("===========" + i + "==========");
            printBoard();
            //System.exit(0);
        } else {
            if (board[row][col] == 0) {
                //需要填数字1~9
                for (int num = 1; num <= 9; num++) {
                    if (!isExist(row, col, num)) {
                        board[row][col] = num; //8
                        //解决下一个格子
                        solve(row + (col + 1) / 9, (col + 1) % 9);
                    }
                    //如果此处没解 必须清零
                    board[row][col] = 0;
                }
            } else {
                //已经存在一个已知数字 直接跳过去解决下一个格子
                solve(row + (col + 1) / 9, (col + 1) % 9);
            }
        }
    }

    private static boolean isExist(int row, int col, int num) {
        //同行
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == num) {
                return true;
            }
        }

        //同列
        for (int r = 0; r < 9; r++) {
            if (board[r][col] == num) {
                return true;
            }
        }

        //同九宫 3*3
        int rowMin = 0;
        int colMin = 0;

        int rowMax = 0;
        int colMax = 0;

        if (row >= 0 && row <= 2) {
            rowMin = 0;
            rowMax = 2;
        }
        if (row >= 3 && row <= 5) {
            rowMin = 3;
            rowMax = 5;
        }
        if (row >= 6 && row <= 8) {
            rowMin = 6;
            rowMax = 8;
        }
        if (col >= 0 && col <= 2) {
            colMin = 0;
            colMax = 2;
        }
        if (col >= 3 && col <= 5) {
            colMin = 3;
            colMax = 5;
        }
        if (col >= 6 && col <= 8) {
            colMin = 6;
            colMax = 8;
        }

        for (int r = rowMin; r <= rowMax; r++) {
            for (int c = colMin; c <= colMax; c++) {
                if (board[r][c] == num) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void readFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        int row = 0;
        while ((line = br.readLine()) != null) {
            for (int col = 0; col < 9; col++) {
                board[row][col] = Integer.parseInt(line.charAt(col) + "");
            }
            row++;
        }
    }

    private static void printBoard() {
        for (int i = 0 ; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}