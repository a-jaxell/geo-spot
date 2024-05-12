package com.laboration2.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmojiSymbolValidator.class)
@Target({ElementType.FIELD})
public @interface EmojiSymbol {
    String message() default "Invalid Emoji";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
