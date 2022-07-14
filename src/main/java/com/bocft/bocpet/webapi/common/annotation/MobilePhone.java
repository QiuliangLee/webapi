package com.bocft.bocpet.webapi.common.annotation;

import com.bocft.bocpet.webapi.common.constant.SysConfigContextHolder;

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
@Constraint(validatedBy = MobilePhoneValidator.class)
public @interface MobilePhone {
    String message() default "Wrong format of mobile phone number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


class MobilePhoneValidator implements ConstraintValidator<MobilePhone, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String pattern = SysConfigContextHolder.getSysConfig("REGEX_MOBILE_PHONE", String.class);
        return s != null && s.matches(pattern);
    }
}