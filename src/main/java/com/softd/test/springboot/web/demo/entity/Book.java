package com.softd.test.springboot.web.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 功能描述
 *
 * @author cobee
 * @since 2020-10-19
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book implements Serializable {
    private static final long serialVersionUID = -3094328270201232226L;
    private Long id;
    private String name;
    private Double price;
    private String type;
    private LocalDate publishDate;
}
