package seedu.addressbook.commands;

import seedu.addressbook.data.employee.ReadOnlyEmployee;
import seedu.addressbook.data.menu.ReadOnlyMenus;
import seedu.addressbook.data.member.ReadOnlyMember;
import seedu.addressbook.data.order.ReadOnlyOrder;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /** The feedback message to be shown to the user. Contains a description of the execution result */
    public final String feedbackToUser;

    /** The list of persons that was produced by the command */
    private final List<? extends ReadOnlyPerson> relevantPersons;

    /** The list of persons that was produced by the command */
    private final List<? extends ReadOnlyEmployee> relevantEmployees;

    /** The menu list produced by the menu command*/
    private final List<? extends ReadOnlyMenus> relevantMenus;

    /** The list of members that was produced by the command */
    private final List<? extends ReadOnlyMember> relevantMembers;

    /** The list of orders that was produced by the order command */
    private final List<? extends ReadOnlyOrder> relevantOrders;

    /** Old AB3 command result constructor for result which do not return person list*/
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantPersons = null;
        relevantMenus = null;
        relevantOrders = null;
        relevantMembers = null;
        relevantEmployees = null;
    }

    /** Old AB3 command result constructor for result which return person list*/
    public CommandResult(String feedbackToUser, List<? extends ReadOnlyPerson> relevantPersons) {
        this.feedbackToUser = feedbackToUser;
        this.relevantPersons = relevantPersons;
        this.relevantMenus = null;
        this.relevantOrders = null;
        this.relevantMembers = null;
        this.relevantEmployees = null;
    }

    /** Command result constructor used by child classes for Rms commands*/
    public CommandResult(String feedbackToUser,
                         List<? extends ReadOnlyPerson> relevantPersons,
                         List<? extends ReadOnlyMenus> relevantMenus,
                         List<? extends ReadOnlyOrder> relevantOrders,
                         List<? extends ReadOnlyMember> relevantMembers,
                         List<? extends ReadOnlyEmployee> relevantEmployees) {
        this.feedbackToUser = feedbackToUser;
        this.relevantPersons = relevantPersons;
        this.relevantMenus = relevantMenus;
        this.relevantOrders = relevantOrders;
        this.relevantMembers = relevantMembers;
        this.relevantEmployees = relevantEmployees;
    }

    /**
     * Returns list of persons relevant to the command result, if any.
     */
    public Optional<List<? extends ReadOnlyPerson>> getRelevantPersons() {
        return Optional.ofNullable(relevantPersons);
    }

    /**
     * Returns list of menu items relevant to the command result, if any.
     */
    public Optional<List<? extends ReadOnlyMenus>> getRelevantMenus() {
        return Optional.ofNullable(relevantMenus);
    }

    /**
     * Returns list of orders relevant to the command result, if any.
     */
    public Optional<List<? extends ReadOnlyOrder>> getRelevantOrders() {
        return Optional.ofNullable(relevantOrders);
    }

    /**
     * Returns list of members relevant to the command result, if any.
     */
    public Optional<List<? extends ReadOnlyMember>> getRelevantMember() {
        return Optional.ofNullable(relevantMembers);
    }

    /**
     * Returns list of employees relevant to the command result, if any.
     */
    public Optional<List<? extends ReadOnlyEmployee>> getRelevantEmployee() { return Optional.ofNullable(relevantEmployees);
    }

}
