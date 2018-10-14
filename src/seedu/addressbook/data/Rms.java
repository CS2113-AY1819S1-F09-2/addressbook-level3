package seedu.addressbook.data;

import seedu.addressbook.data.member.Member;
import seedu.addressbook.data.member.ReadOnlyMember;
import seedu.addressbook.data.member.UniqueMemberList;
import seedu.addressbook.data.member.UniqueMemberList.DuplicateMemberException;
import seedu.addressbook.data.member.UniqueMemberList.MemberNotFoundException;
import seedu.addressbook.data.menu.Menu;
import seedu.addressbook.data.menu.ReadOnlyMenus;
import seedu.addressbook.data.menu.UniqueMenuList;
import seedu.addressbook.data.menu.UniqueMenuList.DuplicateMenuException;
import seedu.addressbook.data.menu.UniqueMenuList.MenuNotFoundException;
import seedu.addressbook.data.order.Order;
import seedu.addressbook.data.order.ReadOnlyOrder;
import seedu.addressbook.data.order.UniqueOrderList;
import seedu.addressbook.data.order.UniqueOrderList.OrderNotFoundException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.employee.Employee;
import seedu.addressbook.data.employee.ReadOnlyEmployee;
import seedu.addressbook.data.employee.UniqueEmployeeList;
import seedu.addressbook.data.employee.UniqueEmployeeList.DuplicateEmployeeException;
import seedu.addressbook.data.employee.UniqueEmployeeList.EmployeeNotFoundException;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/**
 * Represents the entire address book. Contains the data of the address book.
 */
public class Rms {

    private final UniquePersonList allPersons;
    private final UniqueEmployeeList allEmployees;
    private final UniqueMenuList allFoodItems;
    private final UniqueOrderList allOrders;
    private final UniqueMemberList allMembers;

    public static Rms empty() {
        return new Rms();
    }

    /**
     * Creates an empty address book.
     */
    // added allEmployees = new UniqueEmployeeList();
    public Rms() {

        allPersons = new UniquePersonList();
        allEmployees = new UniqueEmployeeList();
        allFoodItems = new UniqueMenuList();
        allOrders = new UniqueOrderList();
        allMembers = new UniqueMemberList();
    }

    /**
     * Constructs an address book with the given data.
     *
     * @param persons external changes to this will not affect this address book
     */
    // Construct address book with persons and employees
    public Rms(UniquePersonList persons,
               UniqueMenuList menus,
               UniqueEmployeeList employees,
               UniqueOrderList orders,
               UniqueMemberList members) {
        this.allPersons = new UniquePersonList(persons);
        this.allEmployees = new UniqueEmployeeList(employees);
        this.allFoodItems = new UniqueMenuList(menus);
        this.allOrders = new UniqueOrderList(orders);
        this.allMembers = new UniqueMemberList(members);
    }

    /**
     * Adds a person to the address book.
     *
     * @throws DuplicatePersonException if an equivalent person already exists.
     */
    public void addPerson(Person toAdd) throws DuplicatePersonException { allPersons.add(toAdd); }

    /**
     * Adds a person to the address book.
     *
     * @throws DuplicateEmployeeException if an equivalent person already exists.
     */
    public void addEmployee(Employee toAdd) throws DuplicateEmployeeException { allEmployees.add(toAdd); }

    /**
     * Adds a menu item to the menu list.
     */
    public void addMenu(Menu toAdd) throws DuplicateMenuException { allFoodItems.add(toAdd); }

    /**
     * Adds an order to the order list.
     */
    public void addOrder(Order toAdd) {
        allOrders.add(toAdd);
    }

    /**
     * Adds a member to the address book.
     *
     * @throws DuplicateMemberException if an equivalent member already exists.
     */

    public void addMember(Member toAdd) throws DuplicateMemberException {
        allMembers.add(toAdd);
    }

    /**
     * Checks if an equivalent person exists in the address book.
     */
    public boolean containsPerson(ReadOnlyPerson key) {
        return allPersons.contains(key);
    }

    /**
     * Checks if an equivalent menu item exists in the address book.
     */
    public boolean containsMenus(ReadOnlyMenus key) {
        return allFoodItems.contains(key);
    }


    /**
     * Checks if an equivalent order exists in the order list.
     */
    public boolean containsOrder(ReadOnlyOrder key) {
        return allOrders.contains(key);
    }

    /**
     * Checks if an equivalent member exists in the address book.
     */
    public boolean containsMember(ReadOnlyMember key) {
        return allMembers.contains(key);
    }

    /**
     * Checks if an equivalent employee exists in the address book.
     */
    public boolean containsEmployee(ReadOnlyEmployee key) {
        return allEmployees.contains(key);
    }


    /**
     * Removes the equivalent person from the address book.
     *
     * @throws PersonNotFoundException if no such Person could be found.
     */
    public void removePerson(ReadOnlyPerson toRemove) throws PersonNotFoundException {
        allPersons.remove(toRemove);
    }

    /**
     * Removes the equivalent menu item from the menu.
     *
     * @throws MenuNotFoundException if no such Order could be found.
     */
    public void removeMenuItem(ReadOnlyMenus toRemove) throws MenuNotFoundException {
        allFoodItems.remove(toRemove);
    }

    /**
     * Removes the equivalent order from the order list.
     *
     * @throws OrderNotFoundException if no such Order could be found.
     */
    public void removeOrder(ReadOnlyOrder toRemove) throws OrderNotFoundException {
        allOrders.remove(toRemove);
    }

    /**
     * Removes the equivalent member from the address book.
     *
     * @throws MemberNotFoundException if no such Member could be found.
     */
    public void removeMember(ReadOnlyMember toRemove) throws MemberNotFoundException {
        allMembers.remove(toRemove);
    }

    /**
     * Removes the equivalent employee from the address book.
     *
     * @throws EmployeeNotFoundException if no such Employee could be found.
     */
    public void removeEmployee(ReadOnlyEmployee toRemove) throws EmployeeNotFoundException {
        allEmployees.remove(toRemove);
    }

    /**
     * Clears all persons from the address book.
     */
    public void clear() {
        allPersons.clear();
    }

    /**
     * Clears all menu items from the menu.
     */
    public void clearMenu() {
        allFoodItems.clear();
    }

    /**
     * Clears all orders from the order list.
     */
    public void clearOrderList() {
        allOrders.clear();
    }

    /**
     * Clears all members from the address book.
     */
    public void clearMembers() {
        allMembers.clear();
    }

    /**
     * Clears all employees from the Rms.
     */
    public void clearEmployee() {
        allEmployees.clear();
    }

    /**
     * Defensively copied UniquePersonList of all persons in the address book at the time of the call.
     */
    public UniquePersonList getAllPersons() {
        return new UniquePersonList(allPersons);
    }

    /**
     * Defensively copied UniqueMemberList of all members in the member list at the time of the call.
     */
    public UniqueMemberList getAllMembers() {
        return new UniqueMemberList(allMembers);
    }

    /**
     * Defensively copied UniqueEmployeeList of all employees in the employee list at the time of the call.
     */
    public UniqueEmployeeList getAllEmployees() {
        return new UniqueEmployeeList(allEmployees);
    }

    /**
     * Defensively copied UniqueMenuList of all menu items in the menu at the time of the call.
     */
    public UniqueMenuList getAllMenus() {
        return new UniqueMenuList(allFoodItems);
    }

    /**
     * Defensively copied UniqueOrderList of all orders in the employee list at the time of the call.
     */
    public UniqueOrderList getAllOrders() { return new UniqueOrderList(allOrders); }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rms // instanceof handles nulls
                && this.allPersons.equals(((Rms) other).allPersons));
    }

    @Override
    public int hashCode() {
        return allPersons.hashCode();
    }
}

