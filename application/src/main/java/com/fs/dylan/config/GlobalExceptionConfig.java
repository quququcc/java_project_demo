package com.fs.dylan.config;

import cn.hutool.jwt.JWTException;

import com.fs.dylan.constans.EnvironmentConstants;
import com.fs.dylan.enums.ResponseCodeEnums;
import com.fs.dylan.exceptions.BaseException;
import com.fs.dylan.exceptions.TokenNotFoundException;
import com.fs.dylan.exceptions.TokenValidateException;
import com.fs.dylan.utils.response.ResponseUtil;
import com.fs.dylan.utils.response.enums.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jay.li
 * @Title: GlobalExceptionConfig
 * @Package com.fs.ppt.config
 * @Description:
 * @date 2023/8/10
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionConfig {
    @Value("${spring.profiles.active}")
    private String environment;

    public static final String SYSTEM_ERROR_DESCRIPTION = "System Error, Please try again later";

    /**
     * 参数异常
     * 请求body缺失
     **/
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseUtil<String> httpMessageNotReadableException() {
        return ResponseUtil.bodyMiss();
    }

    /**
     * 参数异常
     * 请求参数异常
     **/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseUtil<List<HashMap<String, String>>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<HashMap<String, String>> errors = methodArgumentNotValidExceptionDescribe(exception);

        String message = Optional.ofNullable(errors)
                .orElse(Collections.emptyList())
                .stream()
                .flatMap(s -> s.values().stream())
                .findFirst()
                .orElse(ResponseStatus.HTTP_STATUS_422.getDescription());

        return ResponseUtil.unprocessable(errors, message);
    }

    /**
     * 格式化请求参数异常
     **/
    private List<HashMap<String, String>> methodArgumentNotValidExceptionDescribe(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        return fieldErrors.stream().map(fieldError -> {
            HashMap<String, String> map = new HashMap<>(1);
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
            return map;
        }).collect(Collectors.toList());
    }


    /**
     * 参数缺失异常
     **/
    @ExceptionHandler({MissingServletRequestParameterException.class})
    private ResponseUtil<String> missingServletRequestParameterException(MissingServletRequestParameterException exception) {
        final String message = exception.getParameterName() + " 参数缺失";

        return ResponseUtil.fail(message, ResponseStatus.HTTP_STATUS_422.getResponseCode());
    }

    /**
     * PathVariable 异常
     **/
    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseUtil<String> constraintViolationException(ConstraintViolationException exception) {
        String message = exception
                .getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse(ResponseStatus.HTTP_STATUS_422.getDescription());

        return ResponseUtil.unprocessable(null, message);
    }

    /**
     * 自定义异常
     **/
    @ExceptionHandler(BaseException.class)
    private ResponseUtil<String> customException(BaseException exception) {
        String message = exception.getMessage();
        log.error("Base:", exception);
        return ResponseUtil.fail(message, exception.getResponseCodeEnums().getCode());
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    private ResponseUtil<String> fail(Exception exception) {
        String message = SYSTEM_ERROR_DESCRIPTION;
        if (EnvironmentConstants.DEV.equals(environment)) {
            message = exception.getMessage();
        }
        log.error("SQL:", exception);
        return ResponseUtil.fail(message, ResponseCodeEnums.SQL_PARSE_ERROR.getCode());
    }

    /**
     * 请求方式异常
     **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ResponseUtil<String> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return ResponseUtil.notAccepted();
    }

    /**
     * token异常
     */
    @ExceptionHandler({TokenNotFoundException.class, JWTException.class, TokenValidateException.class})
    private ResponseUtil<String> tokenException(Exception e) {
        return ResponseUtil.unauthorized(getDevMessage(e));
    }

    /**
     * 其他异常
     **/
    @ExceptionHandler(Exception.class)
    private ResponseUtil<String> exception(Exception exception) {
        log.error("", exception);

        return ResponseUtil.fail(getDevMessage(exception));
    }

    private String getDevMessage(Exception exception) {
        String message = SYSTEM_ERROR_DESCRIPTION;
        if (EnvironmentConstants.DEV.equals(environment)) {
            message = exception.getMessage();
            StackTraceElement[] stackTrace = exception.getStackTrace();
            Optional<StackTraceElement> stack = Stream.of(stackTrace).findFirst();
            if (stack.isPresent()) {
                StackTraceElement stackTraceElement = stack.get();
                String msgDetail = "fileName:" + stackTraceElement.getFileName() + " class name:" + stackTraceElement.getClassName() + " method: " + stackTraceElement.getMethodName() + " line: " + stackTraceElement.getLineNumber();
                message = msgDetail + " error detail:" + message;
            }

            message = message + " error type: " + exception.toString();
        }

        return message;
    }
}
