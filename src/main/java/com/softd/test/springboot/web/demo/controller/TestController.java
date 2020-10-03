package com.softd.test.springboot.web.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
