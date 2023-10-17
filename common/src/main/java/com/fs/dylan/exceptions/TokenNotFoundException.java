package com.fs.dylan.exceptions;

/**
 * @author jay.li
 * @Title: TokenNotFoundException
 * @Package com.fs.ppt.exceptions
 * @Description:
 * @date 2023/8/17
 */
public class TokenNotFoundException extends BaseException {

    private static final String MESSAGE = "header头重Token不存在";

    public TokenNotFoundException() {
        super();
    }
}
