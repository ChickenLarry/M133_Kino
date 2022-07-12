package ch.bzz.kino.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * @Author: Noel
 *
 * @Since 1.0.0-SNAPSHOT
 * @description registration for the isbn13 valdation
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)

@Documented
@Constraint(validatedBy = {ISBN13Validator.class})
public @interface ISBN13 {

    String message() default "must be a valid ISBN13 character.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
