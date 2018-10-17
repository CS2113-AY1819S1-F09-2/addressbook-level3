package seedu.addressbook.data.employee;

/**
 * A read-only immutable interface for an Employee in the Rms.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyEmployee {

    EmployeeName getName();
    EmployeePhone getPhone();
    EmployeeEmail getEmail();
    EmployeeAddress getAddress();
    EmployeePosition getPosition();


    /**
     * Returns true if the values inside this object is same as those of the other (Note: interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyEmployee other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getPhone().equals(this.getPhone())
                && other.getEmail().equals(this.getEmail())
                && other.getAddress().equals(this.getAddress())
                && other.getPosition().equals(this.getPosition()));
    }


    // Deal with this after creating variable classes
    /**
     * Formats the Employee as text, showing all details.
     * Value of each attribute is trimmed to prevent whitespace errors during tests
     */
    default String getAsTextShowDetails() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName().value.trim())
                .append(" Phone: ");
        builder.append(getPhone().value.trim())
                .append(" Email: ");
        builder.append(getEmail().value.trim())
                .append(" Address: ");
        builder.append(getAddress().value.trim())
                .append(" Position: ");
        builder.append(getPosition().value.trim());
        return builder.toString();
    }


}
