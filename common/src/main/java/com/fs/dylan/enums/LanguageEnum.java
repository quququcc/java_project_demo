package com.fs.dylan.enums;

import java.util.Locale;

/**
 * @author dylan
 * @Title: LanguageEnum
 * @Package com.fs.dylan.enums
 * @Description:
 * @date 2023/9/26
 */
public enum LanguageEnum {
    EN(1, "en", Locale.US),
    ES(2, "es", new Locale("es", "ES")),
    FR(3, "fr", Locale.FRANCE),
    RU(4, "ru", new Locale("ru", "RU")),
    DE(5, "de", Locale.GERMAN),
    CN(6, "cn", Locale.SIMPLIFIED_CHINESE),
    JP(8, "jp", Locale.JAPAN),
    UK(9, "uk", Locale.UK),
    IT(14, "it", Locale.ITALY);

    /**
     *  语言id
     **/
    private final Integer id;

    /**
     * 语言编号
     **/
    private final String code;

    /**
     * Locale
     **/
    private final Locale locale;

    LanguageEnum(Integer id, String code, Locale locale) {
        this.id = id;
        this.code = code;
        this.locale = locale;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Locale getLocale() {
        return locale;
    }

    /** 
      * @Description 根据语言id获取语言
      * @Author dylan
      * @Date 2023/9/26
      * @param [id]
      * @return com.fs.dylan.enums.LanguageEnum
      **/
    public static LanguageEnum getLanguageEnumById(Integer id) {
        for (LanguageEnum value : LanguageEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        return LanguageEnum.EN;
    }

    /** 
      * @Description 根据语言id获取Locale
      * @Author dylan
      * @Date 2023/9/26
      * @param [id]
      * @return java.util.Locale
      **/
    public static Locale getLocaleById(Integer id) {
        for (LanguageEnum value : LanguageEnum.values()) {
            if (value.getId().equals(id)) {
                return value.getLocale();
            }
        }
        return LanguageEnum.EN.getLocale();
    }
}
