package com.habin.payhere_task.house_hold.validation.validator;

import com.habin.payhere_task.house_hold.dto.HouseHoldListRequestDto;
import com.habin.payhere_task.house_hold.validation.annotation.AmountRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AmountRangeValidator implements ConstraintValidator<AmountRange, HouseHoldListRequestDto> {
    @Override
    public boolean isValid(HouseHoldListRequestDto value, ConstraintValidatorContext context) {
        if (value.getMinAmount() != null && value.getMaxAmount() != null) {
            return value.getMinAmount() <= value.getMaxAmount();
        }

        return true;
    }
}
