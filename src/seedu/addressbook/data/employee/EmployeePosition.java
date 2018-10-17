package seedu.addressbook.data.employee;

import java.util.Arrays;
import java.util.List;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents an Employee's position in the Rms.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public class EmployeePosition {


    public static final String EXAMPLE = "Cashier";
    public static final String MESSAGE_POSITION_CONSTRAINTS = "Employee position should be spaces or alphanumeric characters";
    public static final String POSITION_VALIDATION_REGEX = "[\\p{Alnum} ]+";

    public final String value;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public EmployeePosition(String value) throws IllegalValueException {
        value = value.trim();
        if (!isValidPosition(value)) {
            throw new IllegalValueException(MESSAGE_POSITION_CONSTRAINTS);
        }
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid employee name.
     */
    public static boolean isValidPosition(String test) {
        return test.matches(POSITION_VALIDATION_REGEX);
    }

    /**
     * Retrieves a listing of every word in the name, in order.
     */
    public List<String> getWordsInName() {
        return Arrays.asList(value.split("\\s+"));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmployeePosition // instanceof handles nulls
                && this.value.equals(((EmployeePosition) other).value)); // state check
    }

    @Override
    public int hashCode() { return value.hashCode();
    }
}
