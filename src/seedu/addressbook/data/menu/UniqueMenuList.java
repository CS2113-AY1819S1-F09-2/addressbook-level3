package seedu.addressbook.data.menu;

import seedu.addressbook.common.Utils;
import seedu.addressbook.data.exception.DuplicateDataException;

import java.util.*;

/**
 * A list of menus. Does not allow null elements or duplicates.
 *
 * @see Menu#equals(Object)
 * @see Utils#elementsAreUnique(Collection)
 */
public class UniqueMenuList implements Iterable<Menu> {

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateMenuException extends DuplicateDataException {
        protected DuplicateMenuException() {
            super("Operation would result in duplicate menu items");
        }
    }

    /**
     * Signals that an operation targeting a specified menu item in the list would fail because
     * there is no such matching person in the list.
     */
    public static class MenuNotFoundException extends Exception {}

    private final List<Menu> internalList = new ArrayList<>();

    /**
     * Constructs empty menu list.
     */
    public UniqueMenuList() {}

    /**
     * Constructs a menu list with the given menus.
     */
    public UniqueMenuList(Menu... menus) throws DuplicateMenuException {
        final List<Menu> initialTags = Arrays.asList(menus);
        if (!Utils.elementsAreUnique(initialTags)) {
            throw new DuplicateMenuException();
        }
        internalList.addAll(initialTags);
    }

    /**
     * Constructs a list from the items in the given collection.
     * @param menus a collection of menus
     * @throws DuplicateMenuException if the {@code menus} contains duplicate menus
     */
    public UniqueMenuList(Collection<Menu> menus) throws DuplicateMenuException {
        if (!Utils.elementsAreUnique(menus)) {
            throw new DuplicateMenuException();
        }
        internalList.addAll(menus);
    }

    /**
     * Constructs a shallow copy of the list.
     */
    public UniqueMenuList(UniqueMenuList source) {
        internalList.addAll(source.internalList);
    }

    /**
     * Unmodifiable java List view with elements cast as immutable {@link ReadOnlyMenus}s.
     * For use with other methods/libraries.
     * Any changes to the internal list/elements are immediately visible in the returned list.
     */
    public List<ReadOnlyMenus> immutableListView() {
        return Collections.unmodifiableList(internalList);
    }


    /**
     * Checks if the list contains an equivalent menu item as the given argument.
     */
    public boolean contains(ReadOnlyMenus toCheck) {
        return internalList.contains(toCheck);
    }

    /**
     * Adds a menu item to the list.
     *
     * @throws DuplicateMenuException if the menu item to add is a duplicate of an existing menu item in the list.
     */
    public void add(Menu toAdd) throws DuplicateMenuException {
        if (contains(toAdd)) {
            throw new DuplicateMenuException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent menu item from the list.
     *
     * @throws MenuNotFoundException if no such menu item could be found in the list.
     */
    public void remove(ReadOnlyMenus toRemove) throws MenuNotFoundException {
        final boolean menuFoundAndDeleted = internalList.remove(toRemove);
        if (!menuFoundAndDeleted) {
            throw new MenuNotFoundException();
        }
    }

    /**
     * Clears all persons in list.
     */
    public void clear() {
        internalList.clear();
    }

    @Override
    public Iterator<Menu> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueMenuList // instanceof handles nulls
                && this.internalList.equals(
                        ((UniqueMenuList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
