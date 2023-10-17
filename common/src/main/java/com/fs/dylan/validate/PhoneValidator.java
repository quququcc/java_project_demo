package com.fs.dylan.validate;

import cn.hutool.core.util.PhoneUtil;
import com.fs.dylan.anno.PhoneAnno;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author dylan
 * @Title: PhoneValidator
 * @Package com.fs.dylan.validate.group
 * @Description:
 * @date 2023/9/1
 */
public class PhoneValidator implements ConstraintValidator<PhoneAnno, String> {
    @Override
    public void initialize(PhoneAnno constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return PhoneUtil.isPhone(s);
    }
}