package seedu.addressbook.data.menu;

import seedu.addressbook.data.menu.Price;
import seedu.addressbook.data.menu.ReadOnlyMenus;
import seedu.addressbook.data.tag.Tag;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a Menu in the RMS.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Menu implements ReadOnlyMenus {

    private MenuName name;
    private Price price;

    private final Set<Tag> tags = new HashSet<>();
    /**
     * Assumption: Every field must be present and not null.
     */
    public Menu(MenuName name, Price price, Set<Tag> tags) {
        this.name = name;
        this.price = price;
        this.tags.addAll(tags);
    }

    /**
     * Copy constructor.
     */
    public Menu(ReadOnlyMenus source) {
        this(source.getName(), source.getPrice(), source.getTags());
    }

    @Override
    public MenuName getName() {
        return name;
    }

    @Override
    public Price getPrice() {
        return price;
    }


    @Override
    public Set<Tag> getTags() {
        return new HashSet<>(tags);
    }

    /**
     * Replaces this menu item's tags with the tags in {@code replacement}.
     */
    public void setTags(Set<Tag> replacement) {
        tags.clear();
        tags.addAll(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyMenus // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyMenus) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, price, tags);
    }

    @Override
    public String toString() {
        return getAsTextShowAll();
    }

}
//random comment