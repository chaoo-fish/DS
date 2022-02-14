package p0.测试;


import p6.树与哈希表.AVLTreeMap;
import p6.树与哈希表.FileOperation;
import p6.树与哈希表.TreeMap;

import java.util.ArrayList;
import java.util.Collections;

public class TestAVLTreeMap {
    public static void main(String[] args) {
        AVLTreeMap<Integer,String> map = new AVLTreeMap<>();
        map.put(6,"666");
        map.put(4,"444");
        map.put(3,"333");
        map.put(7,"777");
        map.put(5,"555");
        map.put(9,"999");
        map.put(8,"888");
        map.put(1,"111");
        map.put(2,"222");
        map.remove(6);
        System.out.println(map.keySet());
        System.out.println(map.isBST());
        System.out.println(map.isBalanced());
        map.preOrder();

        System.out.println("=============================");
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("a-tale-of-two-cities.txt",words);
//        Collections.sort(words);
        test01(words);
        test02(words);
    }

    private static void test02(ArrayList<String> words) {
        TreeMap<String,Integer> map = new TreeMap<>();
        long startTime = System.currentTimeMillis();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word,map.get(word) + 1);
            } else {
                map.put(word,1);
            }
        }
        System.out.println(map.get("the"));
        System.out.println(map.get("he"));
        System.out.println(map.get("she"));
        System.out.println(map.get("fuck"));
        System.out.println(map.get("is"));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
    }

    private static void test01(ArrayList<String> words) {
        AVLTreeMap<String,Integer> map = new AVLTreeMap<>();
        long startTime = System.currentTimeMillis();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word,map.get(word) + 1);
            } else {
                map.put(word,1);
            }
        }
        System.out.println(map.get("the"));
        System.out.println(map.get("he"));
        System.out.println(map.get("she"));
        System.out.println(map.get("fuck"));
        System.out.println(map.get("is"));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
    }
}