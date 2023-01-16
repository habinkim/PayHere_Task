package com.habin.payhere_task.house_hold.validation.validator;

import com.habin.payhere_task.house_hold.dto.HouseHoldListRequestDto;
import com.habin.payhere_task.house_hold.validation.annotation.AmountRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AmountRangeValidator implements ConstraintValidator<AmountRange, HouseHoldListRequestDto> {
    @Override
    public boolean isValid(HouseHoldListRequestDto value, ConstraintValidatorContext context) {
        return value.getMinAmount() != null &&
                value.getMaxAmount() != null &&
                value.getMinAmount() <= value.getMaxAmount();
    }
}
