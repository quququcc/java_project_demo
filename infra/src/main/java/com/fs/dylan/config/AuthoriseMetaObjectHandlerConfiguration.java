package com.fs.dylan.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @description
 * @author dylan
 * @param * @param null
 * @date 2023/8/14 12:03
 * @return
 * @return: null
 **/
@Component
public class AuthoriseMetaObjectHandlerConfiguration implements MetaObjectHandler {
    /**
     * 新增数据时插入
     **/
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class,LocalDateTime.now());
    }

    /**
     * 更新数据时插入
     **/
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class,LocalDateTime.now());
    }
}