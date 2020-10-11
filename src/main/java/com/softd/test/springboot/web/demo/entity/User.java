package com.softd.test.springboot.web.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述
 *
 * @author cobee
 * @since 2020-10-03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -295302701871986004L;

    public User(String name) {
        this.name = name;
    }

    private Long userId;
    @NotBlank(message = "名称不能为空")
    private String name;
    @NotNull(message = "年龄不能为空")
    @Range(min = 0, max = 1, message = "年龄值只能是0或1")
    private Integer age;
    @NotNull(message = "出生日期不能为空")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
}
