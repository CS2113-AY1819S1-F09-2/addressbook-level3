package seedu.addressbook.logic;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.member.ReadOnlyMember;
import seedu.addressbook.data.menu.ReadOnlyMenus;
import seedu.addressbook.data.order.ReadOnlyOrder;
import seedu.addressbook.data.employee.ReadOnlyEmployee;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.Rms;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.storage.StorageFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents the main Logic of the Rms.
 */
public class Logic {

    private StorageFile storage;
    private Rms rms;

    /** The list of person shown to the user most recently.  */
    private List<? extends ReadOnlyPerson> lastShownList = Collections.emptyList();

    /** The list of menu shown to the user most recently.  */
    private List<? extends ReadOnlyMenus> lastShownMenuList = Collections.emptyList();

    /** The list of member shown to the user most recently.  */
    private List<? extends ReadOnlyMember> lastShownMemberList = Collections.emptyList();

    /** The list of order shown to the user most recently.  */
    private List<? extends ReadOnlyOrder> lastShownOrderList = Collections.emptyList();

    /** The list of employee shown to the user most recently.  */
    private List<? extends ReadOnlyEmployee> lastShownEmployeeList = Collections.emptyList();

    public Logic() throws Exception{
        setStorage(initializeStorage());
        setRms(storage.load());
    }

    Logic(StorageFile storageFile, Rms rms){
        setStorage(storageFile);
        setRms(rms);
    }

    void setStorage(StorageFile storage){
        this.storage = storage;
    }

    void setRms(Rms rms){
        this.rms = rms;
    }

    /**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     * @throws StorageFile.InvalidStorageFilePathException if the target file path is incorrect.
     */
    private StorageFile initializeStorage() throws StorageFile.InvalidStorageFilePathException {
        return new StorageFile();
    }

    public String getStorageFilePath() {
        return storage.getPath();
    }

    /**
     * Unmodifiable view of the current last shown list.
     */
    public List<ReadOnlyPerson> getLastShownList() {
        return Collections.unmodifiableList(lastShownList);
    }

    /**
     * Unmodifiable view of the current last shown menu list.
     */
    public List<ReadOnlyMenus> getLastShownMenuList() {
        return Collections.unmodifiableList(lastShownMenuList);
    }

    /**
     * Unmodifiable view of the current last shown member list.
     */
    public List<ReadOnlyMember> getLastShownMemberList() {
        return Collections.unmodifiableList(lastShownMemberList);
    }

    /**
     * Unmodifiable view of the current last shown order list.
     */
    public List<ReadOnlyEmployee> getLastShownEmployeeList() { return Collections.unmodifiableList(lastShownEmployeeList);
    }

    /**
     * Unmodifiable view of the current last shown order list.
     */
    public List<ReadOnlyOrder> getLastShownOrderList() {
        return Collections.unmodifiableList(lastShownOrderList);
    }

    protected void setLastShownList(List<? extends ReadOnlyPerson> newList) {
        lastShownList = newList;
    }

    protected void setLastShownMenuList(List<? extends ReadOnlyMenus> newList) {
        lastShownMenuList = newList;
    }

    protected void setLastShownOrderList(List<? extends ReadOnlyOrder> newList) {
        lastShownOrderList = newList;
    }

    protected void setLastShownMemberList(List<? extends ReadOnlyMember> newList) {
        lastShownMemberList = newList;
    }

    protected void setLastShownEmployeeList(List<? extends ReadOnlyEmployee> newList) { lastShownEmployeeList = newList;
    }

    /**
     * Parses the user command, executes it, and returns the result.
     * @throws Exception if there was any problem during command execution.
     */
    public CommandResult execute(String userCommandText) throws Exception {
        Command command = new Parser().parseCommand(userCommandText);
        CommandResult result = execute(command);
        recordResult(result);
        return result;
    }

    /**
     * Executes the command, updates storage, and returns the result.
     *
     * @param command user command
     * @return result of the command
     * @throws Exception if there was any problem during command execution.
     */
    private CommandResult execute(Command command) throws Exception {
        command.setData(rms, lastShownList, lastShownMenuList, lastShownOrderList, lastShownMemberList, lastShownEmployeeList);
        CommandResult result = command.execute();
        storage.save(rms);
        return result;
    }

    /** Updates the {@link #lastShownList} if the result contains a list of Persons. */
    private void recordResult(CommandResult result) {
        final Optional<List<? extends ReadOnlyPerson>> personList = result.getRelevantPersons();
        final Optional<List<? extends ReadOnlyMenus>> menuList = result.getRelevantMenus();
        final Optional<List<? extends ReadOnlyOrder>> orderList = result.getRelevantOrders();
        final Optional<List<? extends ReadOnlyMember>> memberList = result.getRelevantMember();
        final Optional<List<? extends ReadOnlyEmployee>> employeeList = result.getRelevantEmployee();
        if (personList.isPresent()) {
            lastShownList = personList.get();
        }
        if (menuList.isPresent()) {
            lastShownMenuList = menuList.get();
        }
        if (orderList.isPresent()) {
            lastShownOrderList = orderList.get();
        }
        if (memberList.isPresent()) {
            lastShownMemberList = memberList.get();
        }
        if (employeeList.isPresent()) {
            lastShownEmployeeList = employeeList.get();
        }
    }
}

