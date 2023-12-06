package com.laboration2.validation;

import com.vdurmont.emoji.EmojiManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.function.IntPredicate;

public class EmojiSymbolValidator implements ConstraintValidator<EmojiSymbol, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext  constraintValidatorContext) {
        return Objects.nonNull(s) || EmojiManager.isEmoji(s) && EmojiManager.containsEmoji(s);
    }
}
