package com.mercadolivro.validation

import com.mercadolivro.service.CustomerService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvaliableValidator(val customerService: CustomerService): ConstraintValidator<EmailAvaliable, String> {

    override fun isValid(value: String?, constraint: ConstraintValidatorContext?): Boolean {

        if(value.isNullOrEmpty()) {
            return false;
        }
        return customerService.emailAvaliable(value);
    }

}