package bai.demo.searchThink.exceptionHandler;

import bai.demo.searchThink.core.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Order(2)
@ControllerAdvice
public class BaseExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public AjaxResult handelException(Exception e) {
        logger.error("系统异常：{}", e.getMessage());
        return AjaxResult.errorMsg("系统异常，请重试");
    }
}
