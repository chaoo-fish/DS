package p5.排序算法;

import java.util.Random;

public class ArrayData {
    // 产生数据 完全随机0 大致有序1 大致平稳2
    private int type;
    private int[] arr = new int[10000]; // 产生10000条数据
    Random random = new Random();

    public ArrayData(int type) {
        this.type = type;
    }

    public int[] makeData() {
        if (type == 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(10000);
            }
        } else if (type == 1) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (i + 1) * 100 + random.nextInt(200);
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = 5000 + (i % 2 == 0 ? random.nextInt(500) : -random.nextInt(500));
            }
        }
        return arr;
    }

}
