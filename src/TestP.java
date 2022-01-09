import org.junit.Test;

import java.util.Arrays;

public class TestP {
    @Test
    public void test01() {
        int[] a = new int[]{1, 2, 3};
//        int[] b = new int[5];
//
//        b = a;
//        a[2] = 555;
//
//        b[1] = 666;
//        Arrays.copyOf()
        System.out.println(Arrays.toString(a));
//        for (int mm:
//             b) {
//            System.out.println(mm);
//        }

    }

    @Test
    public void test02() {
        Integer a1 = 1;
        Integer a2 = 1;
        Integer a3 = 128;
        Integer a4 = 128;

        int b1 = 1;

        System.out.println(a1 == a2); // true
        System.out.println(a3 == a4); // false
        System.out.println(a1 == b1);
        System.out.println(a1.equals(b1));
    }

    @Test
    public void test03() {
//        A a = new A();
//        a.num=1;
//        A a1 = a;
//        System.out.println(a.equals(a1));
    }

    @Test
    public void test04() {
        Integer a = 0;
        System.out.println(a);
    }
}
