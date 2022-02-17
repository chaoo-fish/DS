package p0.测试;


import p6.树与哈希表.*;

import java.util.ArrayList;

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
        System.out.println("===============");
        test01(words);
        System.out.println("===============");
        test02(words);
        System.out.println("===============");
        test03(words);
        System.out.println("===============");
        test04(words);
    }

    private static void test04(ArrayList<String> words) {
        HashTable<String,Integer> hashTable = new HashTable();
        long startTime = System.currentTimeMillis();
        for (String word : words) {
            if (hashTable.contains(word)) {
                hashTable.set(word,hashTable.get(word) + 1);
            } else {
                hashTable.put(word,1);
            }
        }
        System.out.println(hashTable.get("the"));
        System.out.println(hashTable.get("he"));
        System.out.println(hashTable.get("she"));
        System.out.println(hashTable.get("fuck"));
        System.out.println(hashTable.get("is"));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
    }

    private static void test03(ArrayList<String> words) {
        Trie trie = new Trie();
        long startTime = System.currentTimeMillis();
        for (String word : words) {
            trie.add(word);
        }
        System.out.println(trie.count("the"));
        System.out.println(trie.count("he"));
        System.out.println(trie.count("she"));
        System.out.println(trie.count("fuck"));
        System.out.println(trie.count("is"));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");

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