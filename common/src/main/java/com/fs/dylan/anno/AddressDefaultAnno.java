package com.fs.dylan.anno;

import com.fs.dylan.validate.AddressDefaultValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dylan
 * @Title: AddressDefaultAnno
 * @Package com.fs.dylan.anno
 * @Description:
 * @date 2023/9/1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AddressDefaultValidator.class)
public @interface AddressDefaultAnno {
    String message() default "默认地址只能为1或0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
