package seedu.addressbook.data.employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import seedu.addressbook.common.Utils;
import seedu.addressbook.data.exception.DuplicateDataException;

//@@author kianhong95
/**
 * A list of employees. Does not allow null elements or duplicates.
 *
 * @see Employee#equals(Object)
 * @see Utils#elementsAreUnique(Collection)
 */
public class UniqueEmployeeList implements Iterable<Employee> {

    private final List<Employee> employeeInternalList = new ArrayList<>();

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateEmployeeException extends DuplicateDataException {
        protected DuplicateEmployeeException() {
            super("Operation would result in duplicate employees");
        }
    }

    /**
     * Signals that an operation targeting a specified employee in the list would fail because
     * there is no such matching employee in the list.
     */
    public static class EmployeeNotFoundException extends Exception {}

    /**
     * Constructs empty employee list.
     */
    public UniqueEmployeeList(){}

    /**
     * Constructs an employee list with the given employees.
     */
    public UniqueEmployeeList(Employee... employees) throws DuplicateEmployeeException {
        final List<Employee> initialTags = Arrays.asList(employees);
        if (!Utils.elementsAreUnique(initialTags)) {
            throw new DuplicateEmployeeException();
        }
        employeeInternalList.addAll(initialTags);
    }

    /**
     * Constructs a list from the items in the given collection.
     * @param employees a collection of employees
     * @throws DuplicateEmployeeException if the {@code employees} contains duplicate employees
     */
    public UniqueEmployeeList(Collection<Employee> employees) throws DuplicateEmployeeException {
        if (!Utils.elementsAreUnique(employees)) {
            throw new DuplicateEmployeeException();
        }
        employeeInternalList.addAll(employees);
    }

    /**
     * Constructs a shallow copy of the list.
     */
    public UniqueEmployeeList(UniqueEmployeeList source) {
        employeeInternalList.addAll(source.employeeInternalList);
    }

    public List<ReadOnlyEmployee> immutableListView() {
        return Collections.unmodifiableList(employeeInternalList);
    }

    /**
     * Checks if the list contains an employee that already exists in Rms.
     */
    public boolean containsDuplicate(ReadOnlyEmployee toCheck) {
        String nameToCheck = toCheck.getName().toString().toLowerCase();
        for (ReadOnlyEmployee employee: employeeInternalList) {
            String employeeName = employee.getName().toString().toLowerCase();
            if (employeeName.equals(nameToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an employee to the list.
     *
     * @throws UniqueEmployeeList.DuplicateEmployeeException
     *     if the employee to add is a duplicate of an existing employee in the list.
     */
    public void add(Employee toAdd) throws UniqueEmployeeList.DuplicateEmployeeException {
        if (containsDuplicate(toAdd)) {
            throw new UniqueEmployeeList.DuplicateEmployeeException();
        }
        employeeInternalList.add(toAdd);
    }


    /**
     * Removes the equivalent employee from the list.
     *
     * @throws EmployeeNotFoundException if no such employee could be found in the list.
     */
    public void remove(ReadOnlyEmployee toRemove) throws EmployeeNotFoundException {
        final boolean employeeFoundAndDeleted = employeeInternalList.remove(toRemove);
        if (!employeeFoundAndDeleted) {
            throw new EmployeeNotFoundException();
        }
    }

    /**
     * Removes the equivalent employee from the list.
     *
     * @throws EmployeeNotFoundException if no such employee could be found in the list.
     */
    public void edit(ReadOnlyEmployee toRemove, Employee toReplace) throws EmployeeNotFoundException {
        final boolean employeeFoundAndDeleted = employeeInternalList.remove(toRemove);
        if (!employeeFoundAndDeleted) {
            throw new EmployeeNotFoundException();
        }
        employeeInternalList.add(toReplace);
    }

    @Override
    public Iterator<Employee> iterator() {
        return employeeInternalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEmployeeList // instanceof handles nulls
                && this.employeeInternalList.equals(((UniqueEmployeeList) other).employeeInternalList));
    }

    @Override
    public int hashCode() {
        return employeeInternalList.hashCode();
    }
}
