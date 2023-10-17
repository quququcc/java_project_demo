package com.fs.dylan.utils;

/**
 * @author jay.li
 * @Title: TokenLocalUtils
 * @Package com.fs.ppt.utils
 * @Description:
 * @date 2023/8/17
 */
public class TokenLocalUtils {
    private static final ThreadLocal<Integer> THREAD_LOCAL_TOKEN = new ThreadLocal<>();

    /**
     * 把当前Token验证通过后的id放入ThreadLocal
     * @Description setId
     * @Author jay.li
     * @Time 2023/8/17
     * @param id
     */
    public static void setId(Integer id) {
        THREAD_LOCAL_TOKEN.set(id);
    }

    /**
     * 从ThreadLocal中获取存入的id
     * @Description getId
     * @Author jay.li
     * @Time 2023/8/17
     * @return Integer
     */
    public static Integer getId() {
        return THREAD_LOCAL_TOKEN.get();
    }

    /**
     * 请求结束，删除ThreadLocal中的id
     * @Description remove
     * @Author jay.li
     * @Time 2023/8/17
     */
    public static void remove() {
        THREAD_LOCAL_TOKEN.remove();
    }
}
