package p0.测试;

import p6.树与哈希表.FileOperation;
import p6.树与哈希表.TreeMap;

import java.util.ArrayList;

public class TestTreeMap {
    public static void main(String[] args) {
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(12,"xixi");
        map.put(10,"haha");
        map.put(9,"baba");
        map.put(13,"lala");
        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println(map.entrySet());

        TreeMap<String,Integer> map2 = new TreeMap<>();
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("a-tale-of-two-cities.txt",words);
        for (String word : words) {
            if (map2.contains(word)) {
                map2.set(word,map2.get(word) + 1);
            } else {
                map2.put(word,1);
            }
        }
        System.out.println(map2.size());
        System.out.println(map2.get("the"));
        System.out.println(map2.get("if"));
    }
}