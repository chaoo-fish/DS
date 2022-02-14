package p0.测试;

import p6.树与哈希表.FileOperation;
import p6.树与哈希表.LinkedSet;
import p6.树与哈希表.TreeSet;

import java.util.ArrayList;

public class TestTreeSetAndLinkedSet {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        //把文本文件中所有的英文单词统计在words这个线性表中（所有单词 包含重复）
        FileOperation.readFile("a-tale-of-two-cities.txt", words);

        testTreeSet(words);
        testLinkedSet(words);

    }

    private static void testLinkedSet(ArrayList<String> words) {
        Long startTime = System.currentTimeMillis();
        LinkedSet<String> set = new LinkedSet<>();
        for (String word : words) {
            set.add(word);  //把所有单词统计到集合中 每个单词只出现一次
        }
        Long endTime = System.currentTimeMillis();
        System.out.println(set.size());
        System.out.println(endTime - startTime + "ms");
    }

    private static void testTreeSet(ArrayList<String> words) {
        Long startTime = System.currentTimeMillis();
        TreeSet<String> set = new TreeSet<>();
        for (String word : words) {
            set.add(word);  //把所有单词统计到集合中 每个单词只出现一次
        }
        Long endTime = System.currentTimeMillis();
        System.out.println(set.size());
        System.out.println(endTime - startTime + "ms");
    }
}