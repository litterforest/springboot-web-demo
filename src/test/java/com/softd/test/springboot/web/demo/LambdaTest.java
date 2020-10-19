package com.softd.test.springboot.web.demo;

import com.softd.test.springboot.web.demo.entity.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能描述
 *
 * @author cobee
 * @since 2020-10-16
 */
public class LambdaTest {
    private static List<Book> bookList = new ArrayList<>();
    static {
        bookList.add(new Book(1L, "tomcat", 70d, "服务器", LocalDate.parse("2014-05-17")));
        bookList.add(new Book(2L, "jetty", 60d, "服务器", LocalDate.parse("2015-12-01")));
        bookList.add(new Book(3L, "nginx", 65d, "服务器", LocalDate.parse("2016-10-17")));
        bookList.add(new Book(4L, "java", 66d, "编程语言", LocalDate.parse("2011-04-09")));
        bookList.add(new Book(5L, "ruby", 80d, "编程语言", LocalDate.parse("2013-05-09")));
        bookList.add(new Book(6L, "php", 40d, "编程语言", LocalDate.parse("2014-08-06")));
        bookList.add(new Book(7L, "html", 44d, "编程语言", LocalDate.parse("2011-01-06")));
        bookList.add(new Book(8L, "oracle", 150d, "数据库", LocalDate.parse("2013-08-09")));
        bookList.add(new Book(9L, "mysql", 66d, "数据库", LocalDate.parse("2015-04-06")));
        bookList.add(new Book(10L, "ssh", 70d, "编程语言", LocalDate.parse("2016-12-04")));
        bookList.add(new Book(11L, "设计模式", 81d, "其他", LocalDate.parse("2017-04-06")));
        bookList.add(new Book(12L, "重构", 62d, "其他", LocalDate.parse("2012-04-09")));
        bookList.add(new Book(13L, "敏捷开发", 72d, "其他", LocalDate.parse("2016-09-07")));
        bookList.add(new Book(14L, "从技术到管理", 42d, "其他", LocalDate.parse("2016-02-19")));
        bookList.add(new Book(15L, "算法导论", 66d, "其他", LocalDate.parse("2010-05-08")));
        bookList.add(new Book(16L, "oracle 12c", 150d, "数据库", LocalDate.parse("2017-05-08")));
    }
    public static void main(String[] args) throws Exception {
//        Callable<String> c = () -> {
//            if (1 == 1) {
//                throw new Exception("lambda表达式抛出异常");
//            }
//            return "123456";
//        };
//        System.out.println(c.call());
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }

    /**
     * 静态方法引用，打印参数
     */
    private static void test1() {
        Consumer<String> c1 = System.out::println;
        c1.accept("hello");
    }

    /**
     * 解释并封装请求参数
     * 例如：index.do?itemId=1&userId=10000&type=20&token=111111111111&key=index
     *
     */
    private static void test2() {
        String paramStr = "itemId=1&userId=10000&type=20&token=111111111111&key=index";
        Map<String, String> stringMap = Stream.of(paramStr.split("&")).map(s -> s.split("=")).
                collect(Collectors.toMap(s -> s[0], s -> s[1]));
        System.out.println(stringMap);
    }

    private static void test3() {
        // 收集id
//        List<Long> idList = null;
//      idList = bookList.stream().map(book -> book.getId()).collect(Collectors.toList());
//        idList = bookList.stream().map(Book::getId).collect(Collectors.toList()); // 实例引用
//        System.out.println(idList);
        String resultStr = bookList.stream().map(book -> String.valueOf(book.getId())).collect(Collectors.joining(",", "(", ")"));
        System.out.println(resultStr);
    }

    /**
     * 排序算法
     */
    private static void test4() {
//        bookList.stream().sorted((book1, book2) -> Double.compare(book1.getPrice(), book2.getPrice())).forEach(System.out::println);
        // 按照多个字段排序
        bookList.stream().sorted(Comparator.comparing(Book::getPrice).reversed().
                thenComparing(Comparator.comparing(Book::getPublishDate).reversed())).forEach(System.out::println);
    }

    /**
     * 统计价格的平均值
     */
    private static void test5() {
        Double averagePrice = bookList.stream().collect(Collectors.averagingDouble(Book::getPrice));
        System.out.println(averagePrice);
    }

    /**
     * 查找价格最高和最低的书
     */
    private static void test6() {
        // 价格最高的书
        Optional<Book> maxOptionalBook = bookList.stream().collect(Collectors.maxBy(Comparator.comparing(Book::getPrice)));
        System.out.println(maxOptionalBook.get());
        // 查找价格最低的书
        Optional<Book> minPriceBook = bookList.stream().collect(Collectors.minBy(Comparator.comparing(Book::getPrice)));
        System.out.println(minPriceBook.get());

        // 价格最高并且最新发布的书
        Optional<Book> optionalBook = bookList.stream().collect(Collectors.maxBy(Comparator.comparing(Book::getPrice).
                thenComparing(Book::getPublishDate)));
        System.out.println(optionalBook.get());
    }

}
