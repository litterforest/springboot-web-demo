package com.softd.test.springboot.web.demo;

import com.softd.test.springboot.web.demo.entity.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述
 *
 * @author cobee
 * @since 2020-10-11
 */
public class ListJoinTest {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
//        userList.add(new User("张三"));
//        userList.add(new User(null));
//        userList.add(new User("王五"));
        String result = userList.stream().map(user -> {
            if (StringUtils.isNotBlank(user.getName())) {
                return user.getName();
            } else {
                return "";
            }
        }).collect(Collectors.joining(","));
        System.out.println(result);
    }
}
