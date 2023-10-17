package com.fs.dylan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yaowei
 */
@AllArgsConstructor
@Getter
public enum ResponseCodeEnums {
    /**
     * Unknown error
     */
    UNKNOWN_ERROR(10000, "unknown error"),

    /**
     * sql parse error
     */
    SQL_PARSE_ERROR(10002, "sql parse error"),

    /**
     * 黑名单过期时间不存在
     */
    BLACKLIST_EXPIRE_OPTION_NOT_EXIST(10003, "黑名单过期时间不存在"),

    /**
     * 注册用户的邮箱或者手机都为空
     * @Field REGISTER_USER_EMAIL_AND_MOBILE_EMPTY
     */
    REGISTER_USER_EMAIL_AND_MOBILE_EMPTY(10004, "注册用户的邮箱或者手机都为空"),

    MESSAGE_INSERT_PARAMS_ERROR(10005, "消息存储，json object 解析参数缺失"),
    MESSAGE_LIST_PARAMS_ERROR(10006, "消息列表查询，chat Id or group id or userId 全为null"),

    UPLOAD_FILE_INPUT_STREAM_ERROR(10007, "文件上传：检测文件类型，读取文件流出错"),

    UPLOAD_FILE_EXT_ERROR(10008, "文件上传：检测文件类型，文件类型不符合规范"),

    UPLOAD_FILE_SIZE_ERROR(10009, "文件上传：当前文件大小超出规范"),
    UPLOAD_FILE_TO_S3_ERROR(10010, "文件上传：上传到s3异常"),

    MESSAGE_FORMAT(10011, "消息转码失败"),

    SQL_SELECT_NOT_FOUND(10012, "SQL查询结果为空"),

    API_LIMIT_FREQUENCY(10013, "接口请求过于频繁"),
    ADDRESS_NOT_EXIST(10014, "地址信息不存在"),
    ADDRESS_NOT_BELONG(10015, "地址信息不属于当前用户"),
    ADDRESS_HEAD_NOT_BELONG(10016, "地址表头不存在"),


    TOKEN_NOT_EXIST(10016, "token不存在"),
    TOKEN_FAILED(10017, "token生成失败"),

    ERROR_REMIND_EXCEL(10018, "请上传正确的excel文件"),
    ERROR_REMIND_NO_EXCEL(10018, "请上传excel文件"),
    ERROR_UPLOAD_EXCEL_ERROR(10019, "上传excel文件失败"),
    ERROR_EXCEL_HEAD_ERROR(10020, "excel表头错误"),

    EXCEL_UPLOAD_ERROR(10020, "excel上传失败 %s"),

    ;
    /**
     * 状态
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String description;
}

