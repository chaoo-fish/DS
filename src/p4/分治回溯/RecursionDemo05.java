package p4.分治回溯;

import java.io.File;

public class RecursionDemo05 {
    public static void main(String[] args) {
        File dir = new File("C:\\Users\\HENG\\Desktop\\DS");
        traversal(dir);
    }

    private static void traversal(File dir) {
        File[] files = dir.listFiles();
        if (files.length == 0) {
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            } else {
                System.out.println("【" + file.getName() + "】");
                traversal(file);
            }
        }
    }
}