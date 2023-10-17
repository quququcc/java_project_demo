package com.fs.dylan.anno;

import com.fs.dylan.validate.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dylan
 * @Title: PhoneAnno
 * @Package com.fs.dylan.anno
 * @Description:
 * @date 2023/9/1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PhoneValidator.class)
public @interface PhoneAnno {
    String message() default "手机号码填写错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
