package com.softd.test.springboot.web.demo.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * 功能描述
 *
 * @author cobee
 * @since 2020-10-06
 */
public abstract class WebParamValidUtils {
    private static Validator validator = null;
    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static String valid(Object obj) {
        //开始校验
        Set<ConstraintViolation<Object>> result = validator.validate(obj);
        Iterator<ConstraintViolation<Object>> iterator = result.iterator();
        StringBuilder sbuff = new StringBuilder();
        int idx = 0;
        while (iterator.hasNext()) {
            if (idx > 0) {
                sbuff.append(";");
            }
            ConstraintViolation<Object> next = iterator.next();
            sbuff.append(next.getMessage());
            idx++;
        }
        return sbuff.toString();
    }

    public static String valid01(Object obj) {
        //开始校验
        Set<ConstraintViolation<Object>> result = validator.validate(obj);
        String resultStr = result.stream().map(t -> t.getMessage()).
                collect(Collectors.joining(";"));
        Runnable r = () -> System.out.println("");
        Callable<String> c = () -> "";
        return resultStr;
    }

}
