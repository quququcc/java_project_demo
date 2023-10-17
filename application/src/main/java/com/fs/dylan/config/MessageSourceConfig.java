package com.fs.dylan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author dylan
 * @Title: MessageSourceConfig
 * @Package com.fs.dylan.config
 * @Description:
 * @date 2023/9/26
 */
@Configuration
public class MessageSourceConfig {
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // 设置国际化资源文件的基础名字
        messageSource.setBasenames("i18n/messages");
        // 设置资源文件的编码
        messageSource.setDefaultEncoding("UTF-8");
        // 设置资源文件的缓存时间
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }
}
