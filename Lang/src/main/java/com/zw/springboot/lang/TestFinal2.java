package com.zw.springboot.lang;

import org.junit.jupiter.api.Test;

/**
 * <p>Title: Laboratory-com.zw.lang</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.zw.com</p>
 *
 * @author zw-cn
 * @version 1.0
 * @date 12/9/2020
 */
public class TestFinal2 {
    public final int ITY = 8;
    public final Datas datas = new Datas("A");

    @Test
    public void test(){
        TestFinal2 final2 = new TestFinal2();
        final Datas datas;
        datas = new Datas("A");
//        final2.ITY = 123;
//        final2.datas = new Datas("B");
        final2.datas.name = "B";
        System.out.println(final2.datas.name);
    }
}
class Datas{
    protected String name;

    public Datas(String name) {
        this.name = name;
    }
}
