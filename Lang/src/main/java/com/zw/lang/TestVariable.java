package com.zw.lang;

import org.junit.jupiter.api.Test;

/**
 * <p>Title: Laboratory-com.zw.lang</p>
 * <p>Description: 小心传参的坑</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.zw.com</p>
 *
 * @author zw-cn
 * @version 1.0
 * @date 10/20/2020
 */
public class TestVariable {
    @Test
    public void test() {
        String name = "张三";
        changeName(name,"李四");
        System.out.println(name);
    }
    private void changeName(String originName, String targetName){
        originName = targetName;
        originName = new String("王五");
//        System.out.println(originName+"名字修改为："+targetName);
    }

    @Test
    public void test2(){
        Person person = new Person();
        person.name = "张三";
        changeName(person,"李四");
        System.out.println(person.name);
    }
    private void changeName(Person originPerson,String newName){
        originPerson.name = newName;
    }

    /**
     * @Description:包装类型也是进行值传递
     * @param
     * @return: void
     * @Author: zw-cn
     * @Date: 7:20 PM 10/20/2020
     * @Version: 1.0
     */
    @Test
    public void test3() {
        Integer age = 10;
        changeName(age,25);
        System.out.println(age);
    }
    private void changeName(Integer originAge, Integer targetAge){
        originAge = targetAge;
    }

    @Test
    public void test4() {
        Apples apples = new Apples();
        apples.appleName = "red Apple".toCharArray();

        changeName(apples.appleName, "yellow Apple".toCharArray());
        System.out.println(apples.appleName);
    }
    private void changeName(char[] fromApples, char[] toApples){
        fromApples = toApples;
    }

    @Test
    public void test5() {
        Person p1 = new Person();
        p1.name = "p1";
        Person p2 = new Person();
        p1.name = "p2";
        Person p3 = new Person();
        p1.name = "p3";
        Person p4 = new Person();
        p1.name = "p4";
        Person[] persons = {p1,p2};
        Person[] persons2 = {p3,p4};
        System.out.println(persons);
        System.out.println(persons2);
        changePerson(persons, persons2);
        System.out.println(persons == persons2);
        System.out.println(persons);
        System.out.println(persons2);
        System.out.println(persons[0].name);
    }
    private void changePerson(Person[] persons, Person[] newPersons){
        persons[0].name="p5";
        System.out.println("changePerson"+persons);
        System.out.println("changePerson"+newPersons);
        persons = newPersons;
    }

}
final class Person{
    String name;
}
class Apples{
    char[] appleName;
}