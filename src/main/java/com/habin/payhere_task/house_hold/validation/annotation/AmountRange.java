package com.habin.payhere_task.house_hold.validation.annotation;

import com.habin.payhere_task.house_hold.validation.validator.AmountRangeValidator;
import jakarta.validation.Constraint;

import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AmountRangeValidator.class)
public @interface AmountRange {

    String message() default "최소 금액은 최대 금액보다 클 수 없습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
