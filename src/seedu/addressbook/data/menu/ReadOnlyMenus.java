package seedu.addressbook.data.menu;

import seedu.addressbook.data.tag.Tag;

import java.util.Set;

/**
 * A read-only immutable interface for a Menu Item in the RMS.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyMenus {

    MenuName getName();
    Price getPrice();

    /**
     * The returned {@code Set} is a deep copy of the internal {@code Set},
     * changes on the returned list will not affect the menu item's internal tags.
     */
    Set<Tag> getTags();

    /**
     * Returns true if the values inside this object is same as those of the other (Note: interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyMenus other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getPrice().equals(this.getPrice()));
    }

    /**
     * Formats the food item as text, showing all relevant details.
     */
    default String getAsTextShowAll() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Price: ");
        builder.append(getPrice())
                .append(" Tags: ");
        for (Tag tag : getTags()) {
            builder.append(tag);
        }
        return builder.toString();
    }

    /**
     * Formats a menu item as text, showing only non-private contact details.
     */
    default String getAsTextHidePrivate() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName()).append(" Price ").append(getPrice());
        /*if (!getPrice().isPrivate()) {
            builder.append(" Price: ").append(getPrice());
        }*/
        builder.append(" Tags: ");
        for (Tag tag : getTags()) {
            builder.append(tag);
        }
        return builder.toString();
    }
}
