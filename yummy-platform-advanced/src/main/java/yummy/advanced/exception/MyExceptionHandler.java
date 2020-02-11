package yummy.advanced.exception;

import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：tsl
 * @date ：Created in 2020/2/11 18:05
 * @description：global exception handler
 */

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = ShiroException.class)
    @ResponseBody
    public String handleShiroException(ShiroException e) {
        return "没有权限";
    }

}
