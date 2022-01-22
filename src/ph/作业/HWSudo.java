package ph.作业;


import java.io.*;

public class HWSudo {
    // 9 * 9
    private static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        readFile("sudoku_data_01.txt");
        solve(0, 0);
    }

    private static void solve(int row, int col) {
        // 从左至右 从上向下  row,col[0 - 8]
        if (row == 9) {
            printBoard();
        } else {
            if (board[row][col] == 0) {
                for (int num = 1; num <= 9; num++) {
                    if (!isExist(row,col,num)) {
                        board[row][col] = num;
                        solve(row + (col + 1) / 9,(col + 1) % 9 );
                    }
                    board[row][col] = 0;
                }
            } else {
                solve(row + (col + 1) / 9,(col + 1) % 9 );
            }
        }
    }

    private static boolean isExist(int row, int col, int num) {
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == num) {
                return true;
            }
        }

        for (int r = 0; r < 9; r++) {
            if (board[r][col] == num) {
                return true;
            }
        }

        /*
            0-2
            3-5
            6-8
         */
        // 九宫格
        int rowMIn = (row / 3) * 3; // 4
        int rowMax = rowMIn + 2;

        int colMin = (col / 3) * 3;
        int colMax = colMin + 2;

        for (int r = rowMIn; r <= rowMax ; r++) {
            for (int c = colMin; c <= colMax; c++) {
                if (board[r][c] == num) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + "");
            }
            System.out.println();
        }
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
}
