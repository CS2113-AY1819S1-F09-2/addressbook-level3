package seedu.addressbook.ui;

import seedu.addressbook.data.member.ReadOnlyMember;
import seedu.addressbook.data.menu.ReadOnlyMenus;
import seedu.addressbook.data.order.ReadOnlyOrder;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.employee.ReadOnlyEmployee;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for formatting text for display. e.g. for adding text decorations.
 */
public class Formatter {

    /** A decorative prefix added to the beginning of lines printed by Rms */
    private static final String LINE_PREFIX = " ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();


    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";


    /** Offset required to convert between 1-indexing and 0-indexing.  */
    private static final int DISPLAYED_INDEX_OFFSET = 1;


    /** Formats the given strings for displaying to the user. */
    public String format(String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String m : messages) {
            sb.append(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX) + LS);
        }
        return sb.toString();
    }

    /** Formats the given list of persons for displaying to the user. */
    public String format(List<? extends ReadOnlyPerson> persons) {
        final List<String> formattedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : persons) {
            formattedPersons.add(person.getAsTextHidePrivate());
        }
        return format(asIndexedList(formattedPersons));
    }

    /** Formats the given list of menus for displaying to the user. */
    public String formatMenuResult(List<? extends ReadOnlyMenus> menus) {
        final List<String> formattedMenus = new ArrayList<>();
        for (ReadOnlyMenus menu : menus) {
            formattedMenus.add(menu.getAsTextHidePrivate());
        }
        return format(asIndexedList(formattedMenus));
    }

    /** Formats the given list of orders for displaying to the user. */
    public String formatOrderResult(List<? extends ReadOnlyOrder> orders) {
        final List<String> formattedOrders = new ArrayList<>();
        for (ReadOnlyOrder order : orders) {
            formattedOrders.add(order.getAsTextHidePrivate());
        }
        return format(asIndexedList(formattedOrders));
    }

    /** Formats the given list of members for displaying to the user. */
    public String formatMemberResult(List<? extends ReadOnlyMember> members) {
        final List<String> formattedOrders = new ArrayList<>();
        for (ReadOnlyMember member : members) {
            formattedOrders.add(member.getAsTextHidePrivate());
        }
        return format(asIndexedList(formattedOrders));
    }

    /** Formats the given list of employees for displaying to the user. */
    public String formatEmployeeResult(List<? extends ReadOnlyEmployee> employees) {
        final List<String> formattedOrders = new ArrayList<>();
        for (ReadOnlyEmployee employee : employees) {
            formattedOrders.add(employee.getAsTextShowDetails());
        }
        return format(asIndexedList(formattedOrders));
    }

    /** Formats a list of strings as an indexed list. */
    private static String asIndexedList(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    /**
     * Formats a string as an indexed list item.
     *
     * @param visibleIndex index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }

}
