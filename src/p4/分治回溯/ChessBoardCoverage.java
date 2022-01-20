package p4.分治回溯;

import java.util.Scanner;

//棋盘覆盖问题
public class ChessBoardCoverage {
    private static int BOARD_SIZE = 8;
    private static int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    //代表颜色 同一组L骨牌 编号应该是一样的
    private static int title = 0;   // 0就是特殊方格的存在

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print(">>>请输入特殊方格的角标信息：");
        //dr dc 指的是特殊方格的坐标
        int dr = input.nextInt();
        int dc = input.nextInt();

        chessBoard(0, 0, dr, dc,BOARD_SIZE);
        printBoard();
    }

    private static void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //在size*size的矩阵中 以tr tc为四部分子矩阵的基点 dr dc是特殊矩阵的位置 进行填充
    private static void chessBoard(int tr, int tc, int dr, int dc, int size) {
        //判断递归结束 如果尺寸为1 则不可继续拆分 则返回 归
        if (size == 1) {
            return;
        }

        //该层要填充L型骨牌 编号是一致的
        int num = ++title;
        //该层要继续分四个部分 每个部分的尺寸是多少
        int s = size / 2;

        //判断特殊方格在四个部分中 那个部分里

        //左上
        if (dr < tr + s && dc < tc + s) {
            chessBoard(tr,tc,dr,dc,s);
        } else {
            board[tr + s - 1][tc + s - 1] = num;
            chessBoard(tr,tc,tr + s - 1,tc + s - 1,s);
        }

        //右上
        if (dr < tr + s && dc >= tc + s) {
            chessBoard(tr,tc + s,dr,dc,s);
        } else {
            board[tr + s - 1][tc + s] = num;
            chessBoard(tr,tc + s,tr + s - 1,tc + s,s);
        }

        //左下
        if (dr >= tr + s && dc < tc + s) {
            chessBoard(tr + s,tc,dr,dc,s);
        } else {
            board[tr + s][tc + s - 1] = num;
            chessBoard(tr + s,tc,tr + s,tc + s - 1,s);
        }

        //右下
        if (dr >= tr + s && dc >= tc + s) {
            chessBoard(tr + s,tc + s,dr,dc,s);
        } else {
            board[tr + s][tc + s] = num;
            chessBoard(tr + s,tc + s,tr + s,tc + s,s);
        }
    }

}