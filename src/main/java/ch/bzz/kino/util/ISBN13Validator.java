package ch.bzz.kino.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * @Author: Noel
 *
 * @Since 1.0.0-SNAPSHOT
 *
 * @description validator for ISBN-13
 */
public class ISBN13Validator implements ConstraintValidator<ISBN13, String> {


    /**
     * initializes the validator
     * @param isbn13  the object with the ISBN13 number
     */
    @Override
    public void initialize(ISBN13 isbn13) {

    }

    /**
     * validates a isbn13
     * @param isbn13  the number to be validated
     * @param constraintValidatorContext  the context this is called from
     * @return  true/false
     */
    @Override
    public boolean isValid(String isbn13, ConstraintValidatorContext constraintValidatorContext) {
        if (isbn13 == null || isbn13.isEmpty())
            return false;

        String pattern = "(?=[0-9]{13}|[- 0-9]{17})97[89](-[0-9]{1,5}){3}-[0-9]";
        if ( isbn13.matches(pattern) ) {
            String number = isbn13.replace("-", "").replace(" ", "");
            int factor = 1;
            int sum = 0;

            for (int i = 0; i < 12; i++) {
                byte digit = (byte) (number.charAt(i) - '0');

                sum += digit * factor;
                factor = 4 - factor;
            }
            sum = 10 - (sum % 10);
            int check = (isbn13.charAt(16) - '0');

            return sum == check;
        }
        return false;
    }
}
