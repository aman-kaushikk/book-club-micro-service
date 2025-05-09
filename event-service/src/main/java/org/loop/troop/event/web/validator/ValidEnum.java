package org.loop.troop.event.web.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Valid enum.
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface ValidEnum {

	/**
	 * Message string.
	 * @return the string
	 */
	String message() default "Invalid value";

	/**
	 * Groups class [ ].
	 * @return the class [ ]
	 */
	Class<?>[] groups() default {};

	/**
	 * Payload class [ ].
	 * @return the class [ ]
	 */
	Class<? extends Payload>[] payload() default {};

	/**
	 * Enum class class.
	 * @return the class
	 */
	Class<? extends Enum<?>> enumClass();

}
