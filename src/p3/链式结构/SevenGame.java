package p3.链式结构;

import java.util.ArrayList;
import java.util.Scanner;

//逢七过游戏
/*
输入玩家的个数
输入从哪个玩家开始
输入该玩家从哪个数字开始
输入一共玩几个数字
打印出每个玩家将要报出的所有数字
*/
public class SevenGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print(">>>请输入玩家的个数：");
        int playerCount = input.nextInt();

        System.out.print(">>>请输入从哪个玩家开始：");
        int beginPlayer = input.nextInt();

        System.out.print(">>>请输入从哪个数字开始：");
        int beginNumber = input.nextInt();

        System.out.print(">>>请输入数字的最大值：");
        int maxNumber = input.nextInt();

        //创建玩家的集合
        LinkedSinglyCircularList<ArrayList<String>> list = new LinkedSinglyCircularList<>();
        //分别创建玩家的对象
        for (int i = 0; i < playerCount; i++) {
            list.add(new ArrayList<>());
        }

        //开始的玩家的角标
        int index = beginPlayer - 1;

        //将数字 依次分给每一个玩家
        for (int num = beginNumber; num <= maxNumber; num++) {
            list.get(index++ % playerCount).add(getAnswer(num));
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println("第" + (i + 1) + "位玩家：" + list.get(i));
        }
    }

    private static String getAnswer(int num) {
        if (num % 7 == 0 || (num + "").contains("7")) {
            return "过";
        }
        return num + "";
    }
}