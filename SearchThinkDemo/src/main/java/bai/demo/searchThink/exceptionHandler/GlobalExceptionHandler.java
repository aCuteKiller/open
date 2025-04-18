package bai.demo.searchThink.exceptionHandler;


import bai.demo.searchThink.core.AjaxResult;
import bai.demo.searchThink.core.HttpStatus;
import bai.demo.searchThink.exception.BaseException;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Order(1)
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(HandlerMethodValidationException.class)
    public AjaxResult handleHandlerMethodValidationException(HandlerMethodValidationException e) {
        return AjaxResult.errorMsg(HttpStatus.BAD_REQUEST, "参数错误");
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "表单验证失败";
        return AjaxResult.errorMsg(HttpStatus.BAD_REQUEST, errorMessage);
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public AjaxResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return AjaxResult.errorMsg(HttpStatus.BAD_REQUEST, "参数错误");
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public AjaxResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return AjaxResult.errorMsg(HttpStatus.BAD_REQUEST, "参数错误");
    }

    @ResponseBody
    @ExceptionHandler(NoResourceFoundException.class)
    public AjaxResult handleNoResourceFoundException(NoResourceFoundException e) {
        return AjaxResult.errorMsg(HttpStatus.NOT_FOUND, "接口不存在");
    }

    @ResponseBody
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public AjaxResult handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        return AjaxResult.errorMsg(HttpStatus.BAD_REQUEST,"上传文件大小超限");
    }

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public AjaxResult handleBaseException(BaseException e) {
        return AjaxResult.errorMsg(e.code, e.msg);
    }
}
