package com.softd.test.springboot.web.demo;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * 功能描述
 *
 * @author cobee
 * @since 2020-10-16
 */
public class LambdaTest {
    public static void main(String[] args) throws Exception {
//        Callable<String> c = () -> {
//            if (1 == 1) {
//                throw new Exception("lambda表达式抛出异常");
//            }
//            return "123456";
//        };
//        System.out.println(c.call());
        test1();
    }

    /**
     * 静态方法引用，打印参数
     */
    private static void test1() {
        Consumer<String> c1 = System.out::println;
        c1.accept("hello");
    }
}
