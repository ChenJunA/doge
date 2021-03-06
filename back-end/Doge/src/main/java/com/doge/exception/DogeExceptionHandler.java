package com.doge.exception;

import com.doge.enums.StatusCode;
import com.doge.util.RespUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description: 统一异常处理
 * @Author: chenjun
 * @Date: 2018/04/12
 */
/*
    @ControllerAdvice是一个@Component，用于定义@ExceptionHandler，@InitBinder和@ModelAttribute方法，适用于所有使用@RequestMapping方法。
    把@ControllerAdvice注解内部使用@ExceptionHandler、@InitBinder、@ModelAttribute注解的方法应用到所有的 @RequestMapping注解的方法。
*/
@ControllerAdvice
public class DogeExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(DogeExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RespUtil handle(Exception exception) {
        if (exception instanceof DogeException) {
            DogeException dogeException = (DogeException) exception;
            logger.error(dogeException.getMessage(), exception);
            return RespUtil.fail(StatusCode.CONTENT_ERROR);
        } else {
            logger.info("系统错误", exception);
            return RespUtil.fail(StatusCode.SYSTEM_ERROR);
        }
    }
}
