package com.softd.test.springboot.web.demo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 功能描述
 *
 * @author cobee
 * @since 2020-10-03
 */
@Slf4j
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.error("", ex);
        // 返回json数据的ModelAndView
        ModelAndView mv = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        mv.setView(view);
        // 参数没有校验通过的处理方式
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder sbuff = new StringBuilder();
            for (int i = 0; i < errors.size(); i++) {
                if (i > 0) {
                    sbuff.append(";");
                }
                sbuff.append(errors.get(i).getDefaultMessage());
            }
            mv.addObject("status", "fail");
            mv.addObject("errCode", "入参校验失败");
            mv.addObject("errMsg", sbuff.toString());
        } else {
            mv.addObject("status", "fail");
            mv.addObject("errMsg", ex.getMessage());
        }
        return mv;
    }
}
