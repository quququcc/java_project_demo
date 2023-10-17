package com.fs.dylan.exceptions;

import com.fs.dylan.enums.ResponseCodeEnums;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jay.li
 * @Title: BaseException
 * @Package com.fs.ppt.exceptions
 * @Description:
 * @date 2023/8/10
 */
@Getter
@Setter
public class BaseException extends RuntimeException {
    private static final String MESSAGE = "系统异常： ";

    @Autowired
    private final ResponseCodeEnums responseCodeEnums;


    public BaseException() {
        super(MESSAGE);
        responseCodeEnums = ResponseCodeEnums.UNKNOWN_ERROR;
    }

    public BaseException(ResponseCodeEnums codeEnums) {
        super(StringUtils.isEmpty(codeEnums.getDescription()) ? MESSAGE : codeEnums.getDescription());
        responseCodeEnums = codeEnums;
    }

    public BaseException(ResponseCodeEnums codeEnums, String... messages) {
        super(StringUtils.isEmpty(codeEnums.getDescription()) ? MESSAGE : String.format(codeEnums.getDescription(), messages));
        responseCodeEnums = codeEnums;
    }

    public BaseException(ResponseCodeEnums codeEnums, Exception e) {
        super(StringUtils.isEmpty(codeEnums.getDescription()) ? MESSAGE : codeEnums.getDescription(), e);
        responseCodeEnums = codeEnums;
    }

    public BaseException(String message, ResponseCodeEnums responseCodeEnums) {
        super(message);
        this.responseCodeEnums = responseCodeEnums;
    }
}
