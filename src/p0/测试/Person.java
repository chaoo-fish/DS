package p0.测试;

public class Person implements Comparable<Person> {
    private int age;
    private String name;
    public Person(int age,String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ":" + age;
    }

    @Override
    public int compareTo(Person o) {
        //按年龄大小比 谁小谁靠前
//        return this.age - o.age;
        //按年龄大小比 谁大谁靠前
        return o.age - this.age;
        //按姓名字母前后比 谁字母ASCII小谁靠前
//        return this.name.compareTo(o.name);
        //按姓名字母前后比 谁字母ASCII大谁靠前
//        return o.name.compareTo(this.name);
    }
}