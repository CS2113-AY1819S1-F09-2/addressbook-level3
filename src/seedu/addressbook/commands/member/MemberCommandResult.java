package seedu.addressbook.commands.member;

import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.member.ReadOnlyMember;

import java.util.List;

/**
 * Represents the result of an order command execution.
 */
public class MemberCommandResult extends CommandResult {

    public MemberCommandResult(String feedbackToUser, List<? extends ReadOnlyMember> relevantMembers) {
        super(feedbackToUser, null, null, null, relevantMembers, null);
    }
}
