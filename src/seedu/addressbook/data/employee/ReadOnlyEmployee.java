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


    /**
     * Returns true if the values inside this object is same as those of the other (Note: interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyEmployee other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getPhone().equals(this.getPhone())
                && other.getEmail().equals(this.getEmail())
                && other.getAddress().equals(this.getAddress()));
    }


    // Deal with this after creating variable classes
    /**
     * Formats the Employee as text, showing all details.
     */
    default String getAsTextShowDetails() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ");
        builder.append(getPhone())
                .append(" Email: ");
        builder.append(getEmail())
                .append(" Address: ");
        builder.append(getAddress());
        return builder.toString();
    }


}
