package org.loop.troop.event.web.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * The type Enum validator.
 */
@Component
class EnumValidator implements ConstraintValidator<ValidEnum, String> {

	private Class<? extends Enum<?>> enumClass;

	@Override
	public void initialize(ValidEnum constraintAnnotation) {
		this.enumClass = constraintAnnotation.enumClass();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}

		return Arrays.stream(enumClass.getEnumConstants()).anyMatch(enumConstant -> enumConstant.name().equals(value));
	}

}
