package p2.线性结构;

import java.io.File;

// 文件夹遍历
public class DiretoryTraversal {
    public static void main(String[] args) {
        File dir = new File("D:\\A_Java\\S-DataStructures\\DS\\src");
//        ArrayQueue<File> queue = new ArrayQueue<>();
        ArrayLoopQueue<File> queue = new ArrayLoopQueue<>();
        queue.offer(dir);

        /**
         * 只要队列不为空 则出队一个目录对象
         * 将该目录对象展开 开始遍历 遇到文件则打印名称 遇到其他目录 则进队
         */
        while (!queue.isEmpty()) {
            File file = queue.poll();
            System.out.println("[" + file.getName() + "]");
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    System.out.println(f.getName());
                } else {
                    queue.offer(f);
                }
            }
        }
    }
}
