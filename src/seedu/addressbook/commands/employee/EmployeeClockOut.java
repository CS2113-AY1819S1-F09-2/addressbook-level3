package seedu.addressbook.commands.employee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.employee.Attendance;
import seedu.addressbook.data.employee.EmployeeName;
import seedu.addressbook.data.employee.Timing;

/**
 * Clocks out for the specified employee based on the current time.
 */
public class EmployeeClockOut extends Command {
    public static final String COMMAND_WORD = "clockOut";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Clocks out with the current time for the specified employee."
            + "Parameters: NAME\n\t"
            + "Example: " + COMMAND_WORD + " "
            + EmployeeName.EXAMPLE;

    public static final String MESSAGE_SUCCESS = "%1$s clocked out on %2$s";

    private final String name;

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    private final String currentTime = timeFormatter.format(date);
    private final String currentDate = dateFormatter.format(date);

    public EmployeeClockOut(String name){
        this.name = name;
    }

    /**
     * Creates and returns an {@code Attendance} with the details of the current time to clock out.
     */
    private Attendance createNewAttendance(Attendance oldAttendance) {
        String name = oldAttendance.getName();
        Set<Timing> updatedTimings = oldAttendance.getTimings();

        Timing currentTiming = new Timing(this.currentTime, this.currentDate,false);
        updatedTimings.add(currentTiming);

        return new Attendance(name, updatedTimings);
    }

    @Override
    public CommandResult execute() {
        int index = rms.findAttendanceIndex(name);

        Attendance oldAttendance = rms.findAttendance(index);
        Attendance newAttendance = createNewAttendance(oldAttendance);

        rms.updateAttendance(oldAttendance, newAttendance);
        return new CommandResult(String.format(MESSAGE_SUCCESS, name, this.currentDate));
    }
}