package p4.分治回溯;


import p3.链式结构.LinkedList;

public class Maze {
    private static int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 0, 0, 0, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 0, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
//    //入口信息
//    private static int entryX = 1;
//    private static int entryY = 0;
//    //出口信息
//    private static int exitX = 7;
//    private static int exitY = 8;
//    //路径访问状态表
//    private static boolean[][] vistied = new boolean[9][9];
//    //方向的变化量
//    private static int[][] direction = {
//        {-1, 0}, {0, 1}, {1, 0}, {0, -1}
//    };
//    //存储路径的栈
    private static LinkedList<String> stack = new LinkedList<>();
//
    public static void main(String[] args) {
//        boolean flag = go(entryX, entryY);
//        if (flag) {
//            for (String path : stack) {
//                System.out.println(path);
//            }
//        } else {
//            System.out.println("迷宫不通！");
//        }
    }
//    //以x,y为入口 看是否能够向下找到出口 返回false找不到
//    private static boolean go(int x, int y) {
//        stack.push("(" + x + "," + y + ")");
//        vistied[x][y] = true;
//        if (x == exitX && y == exitY) {
//            return true;
//        }
//        //考虑四个方向 上 右 下 左
//        for (int i = 0; i < direction.length; i++) {
//            int newX = direction[i][0] + x;
//            int newY = direction[i][1] + y;
//            if (isInArea(newX, newY) && isRoad(newX, newY) && !vistied[newX][newY]) {
//                if(go(newX,newY)) {
//                    return true;    //某一个方向能通 则向上返回true 表示此层次x y能通
//                }
//            }
//        }
//        stack.pop();
//        return false;   //四个方向都不通 则向上返回false 表示此次层x y 不通
//    }
//
//    private static boolean isRoad(int x, int y) {
//        return maze[x][y] == 0;
//    }
//
//    private static boolean isInArea(int x, int y) {
//        return x >= 0 && x < 9 && y >=0 && y < 9;
//    }
}