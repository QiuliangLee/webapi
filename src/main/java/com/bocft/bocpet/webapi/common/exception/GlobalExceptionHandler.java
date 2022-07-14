package com.bocft.bocpet.webapi.common.exception;


import cn.hutool.core.util.ReUtil;
import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.common.util.UserUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一捕捉shiro的异常，返回给前台一个json信息，前台根据这个信息显示对应的提示，或者做页面的跳转。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxUploadFileSize;

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ShiroException.class)
    public Result handleShiroException(ShiroException e) {
        String eName = e.getClass().getSimpleName();
        log.error("shiro执行出错：{}", eName);
        return Result.err(ResultCodeEnum.INTERNAL_ERR);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthenticatedException.class)
    public Result unAuthenticated() {
        return Result.err(ResultCodeEnum.UNAUTHENTICATED);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthorizedException.class)
    public Result unAuthorized() {
        return Result.err(ResultCodeEnum.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DuplicateKeyException.class)
    public Result duplicateKeyException() {
        return Result.err(ResultCodeEnum.DATA_DUPLICATION_EXCEPTION);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result dataIntegrityViolationException() {
        return Result.err(ResultCodeEnum.DATA_DUPLICATION_EXCEPTION);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result handleUploadSizeExceededException(MaxUploadSizeExceededException e) {
        String uname = UserUtils.getCurrentUserName();
        log.error("上传文件过大, 用户名:{}", uname, e);
        return Result.err(-1, "上传文件过大, 不能超过" + maxUploadFileSize);
    }

    /**
     * 处理数据校验异常
     * 处理配合@requestBody产生的数据校验异常
     **/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public Result bindExceptionHandler(Exception e) {
        BindingResult result;
        if (e instanceof BindException) {
            result = ((BindException) e).getBindingResult();
        } else {
            result = ((MethodArgumentNotValidException) e).getBindingResult();
        }
        Map<String, Object> messages = new HashMap<>(16);
        // 遍历所有字段的异常信息
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                FieldError fieldError = (FieldError) error;
                // 判断是否是某个字段转换失败
                if (fieldError.isBindingFailure()) {
                    messages.put(fieldError.getField(), "数据格式非法！");
                } else {
                    messages.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
            }
        }
        return Result.err(ResultCodeEnum.PARAM_VALIDATE_FAILED, messages);
    }

    /**
     * 参数类型转换错误
     *
     * @param e 错误
     * @return 错误信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageConversionException.class)
    public Result parameterTypeException(HttpMessageConversionException e) {
        String localMsg = e.getCause().getLocalizedMessage();
        String filed = ReUtil.get("\\[\"(.*?)\"]", localMsg, 1);
        return Result.err(ResultCodeEnum.PARAM_TYPE_ERR).putData("filed", filed);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BocpetBizException.class)
    public Result bocpetBizException(BocpetBizException e) {
        return Result.err(-1, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        String uname = UserUtils.getCurrentUserName();
        log.error("执行出错, 用户名:{}", uname, e);
        return Result.err(ResultCodeEnum.INTERNAL_ERR);
    }
}