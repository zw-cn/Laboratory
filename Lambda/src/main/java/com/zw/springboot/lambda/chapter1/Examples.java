package com.zw.springboot.lambda.chapter1;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

/**
 * <p>Title: Laboratory-com.zw.lambda.chapter1</p>
 * <p>Description: lambda入门</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.zw.com</p>
 *
 * @author zw-cn
 * @version 1.0
 * @date 12/6/2020
 */
public class Examples {
    /**
     * @param
     * @Description:优化Swing Button的监听
     * @return: void
     * @Author: zw-cn
     * @Date: 7:37 PM 12/6/2020
     * @Version: 1.0
     */
    @Test
    public void test2_1() {
        Button button = new Button();
        //原始做法
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button click!");
            }
        });
        //lambda
        button.addActionListener(e -> System.out.println("Button click lambda!"));
    }

    /**
     * @param
     * @Description:Lambda的几种变体
     * @return: void
     * @Author: zw-cn
     * @Date: 7:44 PM 12/6/2020
     * @Version: 1.0
     */
    public void test2_2() {
        //无参数
        Runnable noArguments = () -> System.out.println("Hello World!");
        //一个参数
        ActionListener oneArgument = event -> System.out.println("Button click!");
        //语句块
        Runnable multiStatement = () -> {
            System.out.print("Hello");
            System.out.println(" World!");
        };
        //两个参数
        BinaryOperator<Long> add = (x, y) -> x + y;
        //显式声明参数
        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
    }

}
