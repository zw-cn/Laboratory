package com.zw.lang;


/**
 * <p>Title: Laboratory-com.zw.lang</p>
 * <p>Description: 测试final在方法声明变量的作用</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.zw.com</p>
 *
 * @author zw-cn
 * @version 1.0
 * @date 10/13/2020
 */
public class Testfinal {
    public void testFinal(final Data data) {
        System.out.println(data);
        data.setAge(99);
//        data = new Data(5,"CC");


    }
    public void test(){
        Data et = new Data(18, "ET");
        testFinal(et);
        et.setAge(30);
        et = new Data(80,"NET");
        testFinal(et);
    }
}
class Data{
    private int age;
    private String name;

    public Data() {
    }

    public Data(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Data{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
