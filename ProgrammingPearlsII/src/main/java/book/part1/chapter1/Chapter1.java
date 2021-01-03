package book.part1.chapter1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Title: Laboratory-book.part1.chapter1</p>
 * <p>Description: 第一章节练习</p>
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: www.zw.com</p>
 *
 * @author zw-cn
 * @version 1.0
 * @date 1/2/2021
 */
public class Chapter1 {
    public static final int SIZE = 10_000_000;
    public static final int ROW_COUNT = 2_000;
    public static final boolean PRINT = false;
    public static Integer[] random = new Integer[SIZE];

    public Chapter1() {
        List<Integer> list = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        random = list.toArray(random);
    }

    @Test
    public void MemTest() {
        Runtime r = Runtime.getRuntime();
//        r.gc();//计算内存前先垃圾回收一次
        long start = System.currentTimeMillis();//开始Time
        long startMem = r.freeMemory(); // 开始Memory
//        test1();
        test3_1();
        long endMem = r.freeMemory(); // 末尾Memory
        long end = System.currentTimeMillis();//末尾Time
        //输出
        System.out.println("TimeCost: " + String.valueOf(end - start) + "ms");
        System.out.println("MemoryCost: " + String.valueOf((endMem - startMem) / 1024 / 1024) + "MB");
    }

    /**
     * @param
     * @Description:如果不缺内存，如何使用一个具有库的语言来实现一种排序算法以表示和排序集合？
     * @return: void
     * @Author: zw-cn
     * @Date: 7:49 AM 1/2/2021
     * @Version: 1.0
     */
    @Test
    public void test1() {
//        Chapter1.random.length;
//        extracted();
        System.out.println("***************排序开始****************");
        LocalTime before = LocalTime.now();
        Sort.quickSort(random, 0, random.length - 1);
        LocalTime after = LocalTime.now();
        System.out.println("耗时：" + Duration.between(before, after));
//        extracted();
    }

    /**
     * @param
     * @Description:打印结果
     * @return: void
     * @Author: zw-cn
     * @Date: 8:01 AM 1/3/2021
     * @Version: 1.0
     */
    private void extracted() {
        if (!PRINT) {
            return;
        }
        int count = 0;
        for (Integer integer : random) {
            System.out.print(integer + ",");
            count++;
            if (count % ROW_COUNT == 0) {
                System.out.println("");
            }
        }
    }

    static class Sort {
        //快速排序
        public static void quickSort(Integer[] arr, int startIndex, int endIndex) {
            //结束条件：startIndex 大于等于 endIndex
            if (startIndex >= endIndex) {
                return;
            }
            //得到基准元素位置
            int pivotIndex = partitionSingle(arr, startIndex, endIndex);
            quickSort(arr, startIndex, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, endIndex);
        }

        //单边循环法
        private static int partitionSingle(Integer[] arr, int startIndex, int endIndex) {
            //取第1个位置，也可以是随机位置的元素作为基准
            int pivot = arr[startIndex];
            int mark = startIndex;

            for (int i = startIndex + 1; i <= endIndex; i++) {
                if (arr[i] < pivot) {
                    mark++;
                    int p = arr[mark];
                    arr[mark] = arr[i];
                    arr[i] = p;
                }
            }
            //最后将第1个位置元素与mark位置元素交换
            arr[startIndex] = arr[mark];
            arr[mark] = pivot;
            return mark;
        }
    }

    /* ********************** 2.如何使用位逻辑运算（如与、或、移位）来实现位向量 ********************** */

    /**
     * @param
     * @Description: 如何使用位逻辑运算（如与、或、移位）来实现位向量
     * @return: void
     * @Author: zw-cn
     * @Date: 8:07 AM 1/3/2021
     * @Version: 1.0
     */
    public void test2() {
        //暂未有想法
    }

    /* ********************** 3.运行时效率是设计目标的一个重要组成部分，所得到的的程序需要足够高效。
    在你自己的系统上实现位图排序并度量其运行时间。该时间与系统排序的时间以及习题1中排序的时间相比如何？
    假设n为10_000_000,且输入文件包含1_000_000个整数 ********************** */
    @Test
    /**
     * @Description: 在你自己的系统上实现位图排序并度量其运行时间
     * 位图排序：仅需944毫秒,占用28(-10)M空间
     * 系统排序：5331ms，占用243(-38)M空间
     * @param
     * @return: void
     * @Author: zw-cn
     * @Date: 8:39 AM 1/3/2021
     * @Version: 1.0
     */
    public void test3() {
        //位图排序
        //1.创建一个位数组,并初始化
        byte[] bytes = new byte[SIZE];
        for (byte aByte : bytes) {
            aByte = 0;
        }
        //2.初始化这个数组
        for (Integer index : random) {
            bytes[index] = 1;
        }
        //3.输出结果
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 1) {
//                System.out.println(i);
            }
        }
    }

    /**
     * @param
     * @Description:使用系统排序
     * @return: void
     * @Author: zw-cn
     * @Date: 10:09 AM 1/3/2021
     * @Version: 1.0
     */
    @Test
    public void test3_1() {
        List<Integer> list = Arrays.asList(random);
        Collections.sort(list);
    }

    /* ********************** 4.如果认真考虑了习题3，你将会面对生成小于n且没有重复的k个整数的问题。
    最简单的方法就是使用前k个正整数。这个极端的数据集合将不会明显的改变位图方法的运行时间，但是可能会歪曲系统排序的运行时间。
    如何生成位于0至n-1之间的k个不同的随机顺序的随机整数？尽量使你的程序简单高效. ********************** */
    /**
     * @Description:
     * @param k k个随机正整数
     * @param n
     * @return: void
     * @Author: zw-cn
     * @Date: 10:35 AM 1/3/2021
     * @Version: 1.0
     */
    @ParameterizedTest //无需@Test注解
    @CsvSource({"10, 10000"})
    public void test4(int k, int n) {
        //使用JDK
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            integers.add(i);
        }
        Collections.shuffle(integers);
        List<Integer> list = integers.subList(0, k);
        System.out.println(list.size());
        System.out.println(list);
    }

}
