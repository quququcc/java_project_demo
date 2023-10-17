package com.fs.dylan.utils;

import com.fs.dylan.enums.LanguageEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author dylan
 * @Title: ServletUtils
 * @Package com.fs.dylan.utils
 * @Description:
 * @date 2023/9/26
 */
public class ServletUtils {

    private final static String LANGUAGE_ID = "language-id";

    /**
      * @Description 获取请求头语言
      * @Author dylan
      * @Date 2023/9/26
      * @param []
      * @return com.fs.dylan.enums.LanguageEnum
      **/
    public static LanguageEnum getLanguageEnum() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String languageId = request.getHeader(LANGUAGE_ID);
        if (StringUtils.isNumeric(languageId)) {
            return LanguageEnum.getLanguageEnumById(Integer.parseInt(languageId));
        }
        return LanguageEnum.EN;
    }

    /**
      * @Description 获取语言
      * @Author dylan
      * @Date 2023/9/26
      * @param []
      * @return java.util.Locale
      **/
    public static Locale getLocale() {
        return getLanguageEnum().getLocale();
    }
}
