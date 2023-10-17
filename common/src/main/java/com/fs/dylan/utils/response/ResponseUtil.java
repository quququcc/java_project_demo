package com.fs.dylan.utils.response;

import com.fs.dylan.enums.ResponseCodeEnums;
import com.fs.dylan.utils.response.enums.ResponseStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * response result wrapper.
 *
 * @param <T> type of data class
 * @author yaowei
 */
@Data
@Builder
@ApiModel("通用返回对象")
public class ResponseUtil<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * response timestamp.
     */
    @ApiModelProperty(value = "响应时间戳", dataType = "String", required = true, example = "1669369247829")
    private Long timestamp;

    /**
     * response code, 200 -> OK.
     */
    @ApiModelProperty(value = "返回码", dataType = "String", required = true, example = "200")
    private String status;

    @ApiModelProperty(value = "返回码错误标识", dataType = "String", required = true, example = "0")
    private Integer code;

    /**
     * response message.
     */
    @ApiModelProperty(value = "提示信息", dataType = "String", required = true, example = "success")
    private String message;

    /**
     * response data.
     */
    @ApiModelProperty(value = "返回值", required = true)
    private T data;


    /**
     * response success result wrapper.
     */
    public static <T> ResponseUtil<T> success() {
        return success(null);
    }

    /**
     * response success result wrapper.
     */
    public static <T> ResponseUtil<T> success(T data) {
        return ResponseUtil.<T>builder().data(data)
                .message(ResponseStatus.SUCCESS.getDescription())
                .status(ResponseStatus.SUCCESS.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .code(Integer.parseInt(ResponseStatus.SUCCESS.getResponseCode()))
                .build();
    }

    /**
     * response error result wrapper.
     */
    public static <T extends Serializable> ResponseUtil<T> fail(String message) {
        return fail(null, message);
    }


    /**
     * response error result wrapper.
     */
    public static <T extends Serializable> ResponseUtil<T> fail(String message, Integer code) {
        return fail(null, message, code);
    }

    /**
     * response error result wrapper.
     */
    public static <T> ResponseUtil<T> fail(T data, String message) {
        return ResponseUtil.<T>builder().data(data)
                .message(message)
                .status(ResponseStatus.HTTP_STATUS_500.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .code(ResponseCodeEnums.UNKNOWN_ERROR.getCode())
                .build();
    }

    /**
     * response error result wrapper.
     */
    public static <T> ResponseUtil<T> fail(T data, String message, Integer code) {
        return ResponseUtil.<T>builder().data(data)
                .message(message)
                .status(ResponseStatus.HTTP_STATUS_500.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .code(code)
                .build();
    }

    /**
     * request 参数验证失败 422
     **/
    public static <T> ResponseUtil<T> unprocessable(T data, String message) {
        return ResponseUtil.<T>builder().data(data)
                .message(message)
                .status(ResponseStatus.HTTP_STATUS_422.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .code(Integer.parseInt(ResponseStatus.SUCCESS.getResponseCode()))
                .build();
    }

    /**
     * request 权限验证失败
     **/
    public static <T> ResponseUtil<T> unauthorized(String message) {
        return ResponseUtil.<T>builder()
                .message(message)
                .status(ResponseStatus.HTTP_STATUS_401.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .code(Integer.parseInt(ResponseStatus.SUCCESS.getResponseCode()))
                .build();
    }

    /**
     * request 权限验证失败
     **/
    public static <T> ResponseUtil<T> unauthorized() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_401.getDescription())
                .status(ResponseStatus.HTTP_STATUS_401.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .code(Integer.parseInt(ResponseStatus.SUCCESS.getResponseCode()))
                .build();
    }

    /**
     * request 请求方法不支持
     *
     **/
    public static <T> ResponseUtil<T> methodNotAllowed() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_405.getDescription())
                .status(ResponseStatus.HTTP_STATUS_405.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .code(Integer.parseInt(ResponseStatus.SUCCESS.getResponseCode()))
                .build();
    }

    /**
     * request 请求不被接受
     **/
    public static <T> ResponseUtil<T> notAccepted() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_406.getDescription())
                .status(ResponseStatus.HTTP_STATUS_406.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .code(Integer.parseInt(ResponseStatus.SUCCESS.getResponseCode()))
                .build();
    }

    /**
     * request 请求不被接受
     **/
    public static <T> ResponseUtil<T> notAccepted(String message) {
        return ResponseUtil.<T>builder()
                .message(message)
                .status(ResponseStatus.HTTP_STATUS_406.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .code(Integer.parseInt(ResponseStatus.SUCCESS.getResponseCode()))
                .build();
    }


    /**
     * 请求失败
     **/
    public static <T> ResponseUtil<T> badRequest() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_400.getDescription())
                .status(ResponseStatus.HTTP_STATUS_400.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .code(Integer.parseInt(ResponseStatus.SUCCESS.getResponseCode()))
                .build();
    }

    /**
     * 请求次数过多
     **/
    public static <T> ResponseUtil<T> limitRequest() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_429.getDescription())
                .status(ResponseStatus.HTTP_STATUS_429.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .code(Integer.parseInt(ResponseStatus.SUCCESS.getResponseCode()))
                .build();
    }

    /**
     * body参数缺失
     **/
    public static <T> ResponseUtil<T> bodyMiss() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_402.getDescription())
                .status(ResponseStatus.HTTP_STATUS_402.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .code(Integer.parseInt(ResponseStatus.SUCCESS.getResponseCode()))
                .build();
    }
}