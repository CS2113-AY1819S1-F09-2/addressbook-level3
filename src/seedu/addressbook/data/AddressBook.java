package seedu.addressbook.data;

import seedu.addressbook.data.menu.Menu;
import seedu.addressbook.data.menu.ReadOnlyMenus;
import seedu.addressbook.data.menu.UniqueMenuList;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniqueEmployeeList;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/**
 * Represents the entire address book. Contains the data of the address book.
 */
public class AddressBook {

    private final UniquePersonList allPersons;
    private final UniqueEmployeeList allEmployees;
    private final UniqueMenuList allFoodItems;

    public static AddressBook empty() {
        return new AddressBook();
    }

    /**
     * Creates an empty address book.
     */
    // added allEmployees = new UniqueEmployeeList();
    public AddressBook() {

        allPersons = new UniquePersonList();
        allEmployees = new UniqueEmployeeList();
        allFoodItems = new UniqueMenuList();
    }

    /**
     * Constructs an address book with the given data.
     *
     * @param persons external changes to this will not affect this address book
     */
    // Construct address book with persons, employees and food items
    public AddressBook(UniquePersonList persons, UniqueMenuList menus, UniqueEmployeeList employees) {
        this.allPersons = new UniquePersonList(persons);
        this.allEmployees = new UniqueEmployeeList(employees);
        this.allFoodItems = new UniqueMenuList(menus);
    }

    /**
     * Adds a person to the address book.
     *
     * @throws DuplicatePersonException if an equivalent person already exists.
     */
    public void addPerson(Person toAdd) throws DuplicatePersonException {
        allPersons.add(toAdd);
    }

    /**
     * Adds a menu item to the address book.
     *
     * @throws DuplicatePersonException if an equivalent food item already exists.
     */

    public void addMenu(Menu toAddFoodItem) throws UniqueMenuList.DuplicateMenuException {
        allFoodItems.add(toAddFoodItem);
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
    public boolean containsMenus(ReadOnlyMenus key1) {
        return allFoodItems.contains(key1);
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
     * Removes the equivalent food item from the address book.
     *
     * @throws PersonNotFoundException if no such Person could be found.
     */

    public void removeMenuItem(ReadOnlyMenus toRemove1) throws UniqueMenuList.MenuNotFoundException {
        allFoodItems.remove(toRemove1);
    }

    /**
     * Clears all persons from the address book.
     */
    public void clear() {
        allPersons.clear();
    }

    /**
     * Clears all menu items from the address book.
     */
    public void clearmenu() {
        allFoodItems.clear();
    }

    /**
     * Defensively copied UniquePersonList of all persons in the address book at the time of the call.
     */
    public UniquePersonList getAllPersons() {
        return new UniquePersonList(allPersons);
    }

    // this is a copy of getAllPersons for employees
    public UniqueEmployeeList getAllEmployees() {
        return new UniqueEmployeeList(allEmployees);
    }

    /**
     * Defensively copied UniqueMenuList of all menu items in the address book at the time of the call.
     */
    public UniqueMenuList getAllMenus() {
        return new UniqueMenuList(allFoodItems);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && this.allPersons.equals(((AddressBook) other).allPersons));
    }

    @Override
    public int hashCode() {
        return allPersons.hashCode();
    }
}

