package com.softd.test.springboot.web.demo.controller;

import com.softd.test.springboot.web.demo.entity.User;
import com.softd.test.springboot.web.demo.util.WebParamValidUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Date;
import java.util.TimeZone;

/**
 * 功能描述
 *
 * @author cobee
 * @since 2020-10-02
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        int a = 100/0;
        return "test success66677779998888";
    }

    @DeleteMapping("/delete")
    public void delete() {
    }

    @GetMapping("/listUser")
    public User listUser(User user) {
        return user;
    }

    /**
     * 普通表单提交
     * @param user
     * @return
     */
    @PostMapping("/listUser1")
    public User listUser1(@RequestParam(value = "password", required = false) String password, User user) {
        System.err.println("password:" + password);
        System.out.println(new Date());
        System.out.println(TimeZone.getDefault());
        return user;
    }

    /**
     * post提交json格式参数，并且问号传password参数
     * @param password
     * @param user
     * @return
     */
    @PostMapping("/listUser2")
    public User listUser2(@RequestParam("password") String password, @RequestBody User user) {
        System.err.println("password:" + password);
        System.out.println(user);
        return user;
    }

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody @Validated User user) {
        return user;
    }

    @GetMapping("/testDeferred")
    public DeferredResult<String> testDeferred() {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        deferredResult.setErrorResult("超时返回");
        return deferredResult;
    }

    @GetMapping("/testManuValid")
    public String testManuValid(User user) {
        return WebParamValidUtils.valid(user);
    }

}
