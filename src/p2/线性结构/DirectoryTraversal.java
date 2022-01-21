package p2.线性结构;

import p3.链式结构.LinkedList;

import java.io.File;

//文件夹遍历
public class DirectoryTraversal {
    public static void main(String[] args) {
        /*
         * 只要队列不为空 则出队一个目录对象
         * 将该目录对象展开 开始遍历 遇到文件则打印名称 遇到其他目录 则进队
         * */
        File dir = new File("C:\\Users\\HENG\\Desktop\\DS");
        LinkedList<File> queue = new LinkedList<>();
        queue.offer(dir);
        while (!queue.isEmpty()) {
            File file = queue.poll();
            System.out.println(file == null);
            System.out.println("【" + file.getName() + "】");
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