package com.bocft.bocpet.webapi.common.annotation;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = StrNullOrNotBlankValidator.class)
public @interface NullOrNotBlank {
    String message() default "Filed must be null or not blank.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


class StrNullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ObjectUtil.isNull(s) || StrUtil.isNotBlank(s);
    }
}