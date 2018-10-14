package seedu.addressbook.commands.employee;

import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.employee.ReadOnlyEmployee;

import java.util.List;

/**
 * Represents the result of an employee command execution.
 */
public class EmployeeCommandResult extends CommandResult {

    public EmployeeCommandResult(String feedbackToUser, List<? extends ReadOnlyEmployee> relevantEmployees) {
        super(feedbackToUser, null, null, null, null, relevantEmployees);
    }
}
