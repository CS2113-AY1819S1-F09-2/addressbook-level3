package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.Rms;
import seedu.addressbook.data.member.ReadOnlyMember;
import seedu.addressbook.data.menu.ReadOnlyMenus;
import seedu.addressbook.data.order.ReadOnlyOrder;
import seedu.addressbook.data.employee.ReadOnlyEmployee;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

import static seedu.addressbook.ui.Gui.DISPLAYED_INDEX_OFFSET;

/**
 * Represents an executable command.
 */
public abstract class Command {


    //protected List<? extends ReadOnlyPerson> relevantPersons;

    protected Rms rms;
    protected List<? extends ReadOnlyPerson> relevantPersons;
    protected List<? extends ReadOnlyMenus> relevantMenus;
    protected List<? extends ReadOnlyMember> relevantMembers;
    protected List<? extends ReadOnlyOrder> relevantOrders;
    protected List<? extends ReadOnlyEmployee> relevantEmployees;

    private int targetIndex = -1;

    /**
     * @param targetIndex last visible listing index of the target person
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of persons.
     *
     * @param personsDisplayed used to generate summary
     * @return summary message for persons displayed
     */
    public static String getMessageForPersonListShownSummary(List<? extends ReadOnlyPerson> personsDisplayed) {
        return String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, personsDisplayed.size());
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of employees.
     *
     * @param employeesDisplayed used to generate summary
     * @return summary message for employees displayed
     */
    public static String getMessageForEmployeeListShownSummary(List<? extends ReadOnlyEmployee> employeesDisplayed) {
        return String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, employeesDisplayed.size());
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of menu.
     *
     * @param menusDisplayed used to generate summary
     * @return summary message for menus displayed
     */
    public static String getMessageForMenuListShownSummary(List<? extends ReadOnlyMenus> menusDisplayed) {
        return String.format(Messages.MESSAGE_MENUS_LISTED_OVERVIEW, menusDisplayed.size());
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of members.
     *
     * @param membersDisplayed used to generate summary
     * @return summary message for members displayed
     */
    public static String getMessageForMemberListShownSummary(List<? extends ReadOnlyMember> membersDisplayed) {
        return String.format(Messages.MESSAGE_MEMBERS_LISTED_OVERVIEW, membersDisplayed.size());
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of orders.
     *
     * @param ordersDisplayed used to generate summary
     * @return summary message for orders displayed
     */
    public static String getMessageForOrderListShownSummary(List<? extends ReadOnlyOrder> ordersDisplayed) {
        return String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, ordersDisplayed.size());
    }

    /**
     * Executes the command and returns the result.
     */
    public abstract CommandResult execute();

    //Note: it is better to make the execute() method abstract, by replacing the above method with the line below:
    //public abstract CommandResult execute();

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(Rms rms,
                        List<? extends ReadOnlyPerson> relevantPersons,
                        List<? extends ReadOnlyMenus> relevantMenus,
                        List<? extends ReadOnlyOrder> relevantOrders,
                        List<? extends ReadOnlyMember> relevantMembers,
                        List<? extends ReadOnlyEmployee> relevantEmployees) {
        this.rms = rms;
        this.relevantPersons = relevantPersons;
        this.relevantMenus = relevantMenus;
        this.relevantOrders = relevantOrders;
        this.relevantMembers = relevantMembers;
        this.relevantEmployees = relevantEmployees;
    }

    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected ReadOnlyPerson getTargetPerson() throws IndexOutOfBoundsException {
        return relevantPersons.get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    /**
     * Extracts the the target menu item in the last shown menu list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected ReadOnlyMenus getTargetMenu() throws IndexOutOfBoundsException {
        return relevantMenus.get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    /**
     * Extracts the the target member in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected ReadOnlyMember getTargetMember() throws IndexOutOfBoundsException {
        return relevantMembers.get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    /**
     * Extracts the the target order in the last shown order list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected ReadOnlyOrder getTargetOrder() throws IndexOutOfBoundsException {
        return relevantOrders.get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    /**
     * Extracts the the target order in the last shown employee list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected ReadOnlyEmployee getTargetEmployee() throws IndexOutOfBoundsException {
        return relevantEmployees.get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
}
