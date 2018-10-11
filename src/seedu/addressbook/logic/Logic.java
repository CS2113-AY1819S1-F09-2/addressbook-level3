package seedu.addressbook.logic;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.member.ReadOnlyMember;
import seedu.addressbook.data.RMS;
import seedu.addressbook.data.menu.ReadOnlyMenus;
import seedu.addressbook.data.order.ReadOnlyOrder;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.storage.StorageFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents the main Logic of the RMS.
 */
public class Logic {


    private StorageFile storage;
    private RMS rms;

    /** The list of person shown to the user most recently.  */
    private List<? extends ReadOnlyPerson> lastShownList = Collections.emptyList();


    /** The list of menu shown to the user most recently.  */
    private List<? extends ReadOnlyMenus> lastShownMenuList = Collections.emptyList();

    /** The list of member shown to the user most recently.  */
    private List<? extends ReadOnlyMember> lastShownMemberList = Collections.emptyList();

    /** The list of order shown to the user most recently.  */
    private List<? extends ReadOnlyOrder> lastShownOrderList = Collections.emptyList();

    public Logic() throws Exception{
        setStorage(initializeStorage());
        setRms(storage.load());
    }

    Logic(StorageFile storageFile, RMS rms){
        setStorage(storageFile);
        setRms(rms);
    }

    void setStorage(StorageFile storage){
        this.storage = storage;
    }

    void setRms(RMS rms){
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
    public List<ReadOnlyMenus> getLastShownMenuList() {
        return Collections.unmodifiableList(lastShownMenuList);
    }

    /**
     * Unmodifiable view of the current last shown list.
     */
    public List<ReadOnlyMember> getLastShownMemberList() {
        return Collections.unmodifiableList(lastShownMemberList);
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

    protected void setLastShownMemberList(List<? extends ReadOnlyMember> newList) { lastShownMemberList = newList; }

    /**
     * Parses the user command, executes it, and returns the result.
     * @throws Exception if there was any problem during command execution.
     */
    public CommandResult execute(String userCommandText) throws Exception {
        Command command = new Parser().parseCommand(userCommandText);
        CommandResult result = execute(command);
        recordResult(result);
        recordOrderResult(result);
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
        command.setData(rms, lastShownList, lastShownMenuList, lastShownOrderList, lastShownMemberList);
        CommandResult result = command.execute();
        storage.save(rms);
        return result;
    }

    /** Updates the {@link #lastShownList} if the result contains a list of Persons. */
    private void recordResult(CommandResult result) {
        final Optional<List<? extends ReadOnlyPerson>> personList = result.getRelevantPersons();
        if (personList.isPresent()) {
            lastShownList = personList.get();
        }
    }

    /** Updates the {@link #lastShownMenuList} if the result contains a list of Menus. */
    private void recordMenuResult(CommandResult result) {
        final Optional<List<? extends ReadOnlyMenus>> menuList = result.getRelevantMenus();
        if (menuList.isPresent()) {
            lastShownMenuList = menuList.get();
        }
    }


    private void recordOrderResult(CommandResult result) {
        final Optional<List<? extends ReadOnlyOrder>> orderList = result.getRelevantOrders();
        if (orderList.isPresent()) {
            lastShownOrderList = orderList.get();
        }
    }
}

