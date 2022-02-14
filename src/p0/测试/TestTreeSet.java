package p0.测试;

import p6.树与哈希表.TreeSet;

public class TestTreeSet {
    public static void main(String[] args) {
        TreeSet<Person> set = new TreeSet<Person>();
        set.add(new Person(18,"momo"));
        set.add(new Person(10,"xixi"));
        set.add(new Person(12,"lala"));
        set.add(new Person(19,"haha"));
        set.add(new Person(17,"kaka"));
        System.out.println(set.toString());
    }
}
