package seedu.addressbook.common;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_MENU_ITEM_DISPLAYED_INDEX = "The menu item index provided is invalid";
    public static final String MESSAGE_PERSON_NOT_IN_ADDRESSBOOK = "Person could not be found in address book";
    public static final String MESSAGE_MENU_ITEM_NOT_IN_ADDRESSBOOK = "Menu item could not be found in Rms";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_EMPLOYEES_LISTED_OVERVIEW = "%1$d employees listed!";
    public static final String MESSAGE_MENUS_LISTED_OVERVIEW = "%1$d food items listed!";
    public static final String MESSAGE_MEMBERS_LISTED_OVERVIEW = "%1$d members listed!";
    public static final String MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE = "Launch command format: " +
            "java seedu.addressbook.Main [STORAGE_FILE_PATH]";
    public static final String MESSAGE_WELCOME = "Welcome to Restaurant Management System! \n" +
            "- To access MENU, key in 'menu\n" +
            "- To access ORDER, key in 'order'\n" +
            "- To access CUSTOMER DATABASE, key in 'customer'\n" +
            "- To access EMPLOYEE DATABASE, key in 'employee'\n" +
            "- To access STATISITCS information, key in 'statisitics'\n" +
            "- For further assistance, key in 'help'\n" +
            "----------------------------------------------------------";
    public static final String MESSAGE_USING_STORAGE_FILE = "Using storage file : %1$s";

    public static final String MESSAGE_INVALID_ORDER_DISPLAYED_INDEX = "The order index provided is invalid";
    public static final String MESSAGE_ORDER_NOT_IN_ORDER_LIST = "Order could not be found in order list";
    public static final String MESSAGE_ORDERS_LISTED_OVERVIEW = "%1$d orders listed!";
    public static final String MESSAGE_USING_ORDER_LIST_STORAGE_FILE = "Using order list storage file : %1$s";
}
