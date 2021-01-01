package chapter2._01;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * <p>Title: Laboratory-chapter2._01</p>
 * <p>Description: 见识一下Lambda</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.zw.com</p>
 *
 * @author zw-cn
 * @version 1.0
 * @date 12/27/2020
 */
public class Lambda {
    /***
     * @Description: Lambda与匿名内部类的比较
     * @return: void
     * @Author: zw-cn
     * @Date: 10:40 PM 12/27/2020
     * @Version: 1.0
     */
    @Test
    public void test1() {
        //匿名内部类
        Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(integerComparator);
    }
}
