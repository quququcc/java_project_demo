package com.fs.dylan.utils;

import com.fs.dylan.enums.LanguageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * @author dylan
 * @Title: MessageUtils
 * @Package com.fs.dylan.utils
 * @Description:
 * @date 2023/9/26
 */
@Service
public class MessageUtils {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, ServletUtils.getLocale());
    }

    public String getMessage(String code, String... args) {
        return messageSource.getMessage(code, args, ServletUtils.getLocale());
    }

    public String getMessage(String code, Integer language_id) {
        return messageSource.getMessage(code, null, LanguageEnum.getLocaleById(language_id));
    }

    public String getMessage(String code, Integer language_id, String... args) {
        return messageSource.getMessage(code, args, LanguageEnum.getLocaleById(language_id));
    }
}
