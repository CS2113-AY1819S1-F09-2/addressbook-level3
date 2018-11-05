package seedu.addressbook.commands.member;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.member.Member;
import seedu.addressbook.data.member.MemberName;
import seedu.addressbook.data.member.ReadOnlyMember;
import seedu.addressbook.data.member.UniqueMemberList;

/**
 * Adds a person to the address book.
 */
public class MemberAddCommand extends Command {

    public static final String COMMAND_WORD = "addmember";
    public static final String EMPTY_NAME_STRING = "baLpcbImfjsHuIhCnEKM";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Adds a member to the address book. "
            + "Contact details can be marked private by prepending 'p' to the prefix.\n\t"
            + "Parameters: NAME \n\t"
            + "Example: " + COMMAND_WORD
            + " John Doe ";

    public static final String MESSAGE_SUCCESS = "New member added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEMBER = "This member already exists in the address book";
    public static final String MESSAGE_NAME_CANNOT_BE_EMPTY_NAME_STRING =
            "Member name cannot be the same as the empty name string.";

    private final Member toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public MemberAddCommand(String name) throws IllegalValueException {
        this.toAdd = new Member(
                new MemberName(name)
        );
    }

    public ReadOnlyMember getMember() {
        return toAdd;
    }

    @Override
    public CommandResult execute() {
        try {
            final MemberName name = toAdd.getName();
            if (name.equals(new MemberName(EMPTY_NAME_STRING))) {
                throw new IllegalValueException(MESSAGE_NAME_CANNOT_BE_EMPTY_NAME_STRING);
            }
            rms.addMember(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueMemberList.DuplicateMemberException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_MEMBER);
        } catch (IllegalValueException e) {
            return new CommandResult(MESSAGE_NAME_CANNOT_BE_EMPTY_NAME_STRING);
        }
    }

}
