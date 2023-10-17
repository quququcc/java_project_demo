package com.fs.dylan.validate;

import com.fs.dylan.anno.AddressDefaultAnno;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author dylan
 * @Title: AddressDefaultValidator
 * @Package com.fs.dylan.validate
 * @Description:
 * @date 2023/9/1
 */
public class AddressDefaultValidator implements ConstraintValidator<AddressDefaultAnno, Integer> {
    @Override
    public void initialize(AddressDefaultAnno constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(Integer s, ConstraintValidatorContext constraintValidatorContext) {
        return s == 0 || s == 1;
    }
}
