package seedu.addressbook.data.menu;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Price of a particular menu item in the Restaurant Management System.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Price {

    public static final String EXAMPLE = "440";
    public static final String MESSAGE_PHONE_CONSTRAINTS = "Price should be in SGD and in dollars and cents";
    public static final String PHONE_VALIDATION_REGEX = "\\d+";//"^\\$\\d+([.][0-9]+)?$";

    public final String value;
    //private boolean isPrivate;

    /**
     * Validates given phone number.
     *
     * @throws IllegalValueException if given phone string is invalid.
     */
    public Price(String price/*, boolean isPrivate*/) throws IllegalValueException {
        //this.isPrivate = isPrivate;
        price = price.trim();
        if (!isValidPhone(price)) {
            throw new IllegalValueException(MESSAGE_PHONE_CONSTRAINTS);
        }
        this.value = price;
    }

    /**
     * Checks if a given string is a valid menu item price.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(PHONE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
