package seedu.addressbook.logic;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.commands.*;
import seedu.addressbook.commands.employee.*;
import seedu.addressbook.commands.menu.MenuViewAllCommand;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.Rms;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.employee.*;
import seedu.addressbook.data.menu.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.storage.StorageFile;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static seedu.addressbook.common.Messages.*;


public class LogicTest {

    /**
     * See https://github.com/junit-team/junit4/wiki/rules#temporaryfolder-rule
     */
    @Rule
    public TemporaryFolder saveFolder = new TemporaryFolder();

    private StorageFile saveFile;
    private Rms rms;
    private Logic logic;

    @Before
    public void setup() throws Exception {
        saveFile = new StorageFile(saveFolder.newFile("testSaveFile.txt").getPath());
        rms = new Rms();
        saveFile.save(rms);
        logic = new Logic(saveFile, rms);
    }

    @Test
    public void constructor() {
        //Constructor is called in the setup() method which executes before every test, no need to call it here again.

        //Confirm the last shown list is empty
        assertEquals(Collections.emptyList(), logic.getLastShownList());
        assertEquals(Collections.emptyList(), logic.getLastShownMenuList());
        assertEquals(Collections.emptyList(), logic.getLastShownEmployeeList());
    }

    @Test
    public void execute_invalid() throws Exception {
        String invalidCommand = "       ";
        assertCommandBehavior(invalidCommand,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

    /**
     * Executes the command and confirms that the result message is correct.
     * Both the 'address book' and the 'last shown list' are expected to be empty.
     * @see #assertCommandBehavior(String, String, Rms, boolean, List)
     */
    private void assertCommandBehavior(String inputCommand, String expectedMessage) throws Exception {
        assertCommandBehavior(inputCommand, expectedMessage, Rms.empty(),false, Collections.emptyList());
    }
    /**
     * Executes the Employee command and confirms that the result message is correct.
     * Both the 'address book' and the 'last shown list' are expected to be empty.
     * @see #assertEmployeeCommandBehavior(String, String, Rms, boolean, List)
     */
    private void assertEmployeeCommandBehavior(String inputCommand, String expectedMessage) throws Exception {
        assertEmployeeCommandBehavior(inputCommand, expectedMessage, Rms.empty(),false, Collections.emptyList());
    }

    /**
     * Executes the command and confirms that the result message is correct and
     * also confirms that the following three parts of the Logic object's state are as expected:<br>
     *      - the internal address book data are same as those in the {@code expectedRms} <br>
     *      - the internal 'last shown list' matches the {@code expectedLastList} <br>
     *      - the storage file content matches data in {@code expectedRms} <br>
     */
    private void assertCommandBehavior(String inputCommand,
                                       String expectedMessage,
                                       Rms expectedRms,
                                       boolean isRelevantPersonsExpected,
                                       List<? extends ReadOnlyPerson> lastShownList) throws Exception {

        //Execute the command
        CommandResult r = logic.execute(inputCommand);

        //Confirm the result contains the right data
        assertEquals(expectedMessage, r.feedbackToUser);
        assertEquals(r.getRelevantPersons().isPresent(), isRelevantPersonsExpected);
        if(isRelevantPersonsExpected){
            assertEquals(lastShownList, r.getRelevantPersons().get());
        }

        //Confirm the state of data is as expected
        assertEquals(expectedRms, rms);
        assertEquals(lastShownList, logic.getLastShownList());
        assertEquals(rms, saveFile.load());
    }

    /**
     * Executes the command and confirms that the result message is correct and
     * also confirms that the following three parts of the Logic object's state are as expected:<br>
     *      - the internal address book data are same as those in the {@code expectedRms} <br>
     *      - the internal 'last shown list' matches the {@code expectedLastList} <br>
     *      - the storage file content matches data in {@code expectedRms} <br>
     */
    private void assertEmployeeCommandBehavior(String inputCommand,
                                               String expectedMessage,
                                               Rms expectedRms,
                                               boolean isRelevantEmployeesExpected,
                                               List<? extends ReadOnlyEmployee> lastShownList) throws Exception {

        //Execute the command
        CommandResult r = logic.execute(inputCommand);

        //Confirm the result contains the right data
        assertEquals(expectedMessage, r.feedbackToUser);
        assertEquals(r.getRelevantEmployee().isPresent(), isRelevantEmployeesExpected);
        if(isRelevantEmployeesExpected){
            assertEquals(lastShownList, r.getRelevantEmployee().get());
        }

        //Confirm the state of data is as expected
        assertEquals(expectedRms, rms);
        assertEquals(lastShownList, logic.getLastShownEmployeeList());
        assertEquals(rms, saveFile.load());
    }

    /**
     * Executes the menu command and confirms that the result message is correct.
     * Both the 'address book' and the 'last shown menu list' are expected to be empty.
     * @see #assertMenuCommandBehavior(String, String, Rms, boolean, List)
     */
    private void assertMenuCommandBehavior(String inputCommand, String expectedMessage) throws Exception {
        assertMenuCommandBehavior(inputCommand, expectedMessage, Rms.empty(), false, Collections.emptyList());
    }


    /**
     * Executes the menu command and confirms that the result message is correct and
     * also confirms that the following three parts of the Logic object's state are as expected:<br>
     *      - the internal address book data are same as those in the {@code expectedRms} <br>
     *      - the internal 'last shown menu list' matches the {@code expectedLastList} <br>
     *      - the storage file content matches data in {@code expectedRms} <br>
     */
    private void assertMenuCommandBehavior(String inputCommand,
                                           String expectedMessage,
                                           Rms expectedRms,
                                           boolean isRelevantMenuItemsExpected,
                                           List<? extends ReadOnlyMenus> lastShownMenuList) throws Exception {

        //Execute the command
        CommandResult r = logic.execute(inputCommand);

        //Confirm the result contains the right data
        assertEquals(expectedMessage, r.feedbackToUser);
        assertEquals(r.getRelevantMenus().isPresent(), isRelevantMenuItemsExpected);
        if(isRelevantMenuItemsExpected){
            assertEquals(lastShownMenuList, r.getRelevantMenus().get());
        }


        //Confirm the state of data is as expected
        assertEquals(expectedRms, rms);
        assertEquals(lastShownMenuList, logic.getLastShownMenuList());
        assertEquals(rms, saveFile.load());
    }

    @Test
    public void execute_unknownCommandWord() throws Exception {
        String unknownCommand = "uicfhmowqewca";
        assertCommandBehavior(unknownCommand, HelpCommand.MESSAGE_ALL_USAGES);
    }

    @Test
    public void execute_help() throws Exception {
        assertCommandBehavior("help", HelpCommand.MESSAGE_ALL_USAGES);
    }

    @Test
    public void execute_exit() throws Exception {
        assertCommandBehavior("exit", ExitCommand.MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    @Test
    public void execute_clear() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        rms.addPerson(helper.generatePerson(1, true));
        rms.addPerson(helper.generatePerson(2, true));
        rms.addPerson(helper.generatePerson(3, true));

        assertCommandBehavior("clear", ClearCommand.MESSAGE_SUCCESS, Rms.empty(), false, Collections.emptyList());
    }

    @Test
    public void execute_add_invalidArgsFormat() throws Exception {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
        assertCommandBehavior(
                "add wrong args wrong args", expectedMessage);
        assertCommandBehavior(
                "add Valid Name 12345 e/valid@email.butNoPhonePrefix a/valid, address", expectedMessage);
        assertCommandBehavior(
                "add Valid Name p/12345 valid@email.butNoPrefix a/valid, address", expectedMessage);
        assertCommandBehavior(
                "add Valid Name p/12345 e/valid@email.butNoAddressPrefix valid, address", expectedMessage);
    }

    @Test
    public void execute_addemp_invalidArgsFormat() throws Exception {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmployeeAddCommand.MESSAGE_USAGE);
        assertEmployeeCommandBehavior(
                "addemp wrong args wrong args", expectedMessage);
        assertEmployeeCommandBehavior(
                "addemp Valid Name 12345 e/valid@email.butNoPhonePrefix a/valid, address pos/validPos", expectedMessage);
        assertEmployeeCommandBehavior(
                "addemp Valid Name p/12345 valid@email.butNoPrefix a/valid, address pos/validPos", expectedMessage);
        assertEmployeeCommandBehavior(
                "addemp Valid Name p/12345 e/valid@email.butNoAddressPrefix valid, address pos/validPos", expectedMessage);
        assertCommandBehavior(
                "addemp Valid Name p/12345 e/valid@email a/butNoAddressPrefix valid, address butNoPositionPrefix", expectedMessage);
    }

    @Test
    public void execute_add_invalidPersonData() throws Exception {
        assertCommandBehavior(
                "add []\\[;] p/12345 e/valid@e.mail a/valid, address", Name.MESSAGE_NAME_CONSTRAINTS);
        assertCommandBehavior(
                "add Valid Name p/not_numbers e/valid@e.mail a/valid, address", Phone.MESSAGE_PHONE_CONSTRAINTS);
        assertCommandBehavior(
                "add Valid Name p/12345 e/notAnEmail a/valid, address", Email.MESSAGE_EMAIL_CONSTRAINTS);
        assertCommandBehavior(
                "add Valid Name p/12345 e/valid@e.mail a/valid, address t/invalid_-[.tag", Tag.MESSAGE_TAG_CONSTRAINTS);

    }

    @Test
    public void execute_addemp_invalidPersonData() throws Exception {
        assertEmployeeCommandBehavior(
                "addemp []\\[;] p/12345 e/valid@e.mail a/valid, address pos/validPos", EmployeeName.MESSAGE_NAME_CONSTRAINTS);
        assertEmployeeCommandBehavior(
                "addemp Valid Name p/not_numbers e/valid@e.mail a/valid, address pos/validPos", EmployeePhone.MESSAGE_PHONE_CONSTRAINTS);
        assertEmployeeCommandBehavior(
                "addemp Valid Name p/12345 e/notAnEmail a/valid, address pos/validPos", EmployeeEmail.MESSAGE_EMAIL_CONSTRAINTS);
        assertEmployeeCommandBehavior(
                "addemp Valid Name p/12345 e/valid@e.mail a/valid, address pos/@#%&%", EmployeePosition.MESSAGE_POSITION_CONSTRAINTS);

    }

    @Test
    public void execute_add_successful() throws Exception {
        // setup expectations
        TestDataHelper helper = new TestDataHelper();
        Person toBeAdded = helper.adam();
        Rms expectedAB = new Rms();
        expectedAB.addPerson(toBeAdded);

        // execute command and verify result
        assertCommandBehavior(helper.generateAddCommand(toBeAdded),
                String.format(AddCommand.MESSAGE_SUCCESS, toBeAdded),
                expectedAB,
                false,
                Collections.emptyList());

    }

    @Test
    public void execute_addemp_successful() throws Exception {
        // setup expectations
        TestDataHelper helper = new TestDataHelper();
        Employee toBeAdded = helper.peter();
        Rms expectedAB = new Rms();
        expectedAB.addEmployee(toBeAdded);

        // execute command and verify result
        assertEmployeeCommandBehavior(helper.generateAddEmpCommand(toBeAdded),
                String.format(EmployeeAddCommand.MESSAGE_SUCCESS, toBeAdded),
                expectedAB,
                false,
                Collections.emptyList());

    }

    @Test
    public void execute_addDuplicate_notAllowed() throws Exception {
        // setup expectations
        TestDataHelper helper = new TestDataHelper();
        Person toBeAdded = helper.adam();
        Rms expectedAB = new Rms();
        expectedAB.addPerson(toBeAdded);

        // setup starting state
        rms.addPerson(toBeAdded); // person already in internal address book

        // execute command and verify result
        assertCommandBehavior(
                helper.generateAddCommand(toBeAdded),
                AddCommand.MESSAGE_DUPLICATE_PERSON,
                expectedAB,
                false,
                Collections.emptyList());

    }

    @Test
    public void execute_addempDuplicate_notAllowed() throws Exception {
        // setup expectations
        TestDataHelper helper = new TestDataHelper();
        Employee toBeAdded = helper.peter();
        Rms expectedAB = new Rms();
        expectedAB.addEmployee(toBeAdded);

        // setup starting state
        logic.execute(helper.generateAddEmpCommand(toBeAdded)); //employee already in Rms

        // execute command and verify result
        assertEmployeeCommandBehavior(
                helper.generateAddEmpCommand(toBeAdded),
                EmployeeAddCommand.MESSAGE_DUPLICATE_EMPLOYEE,
                expectedAB,
                false,
                Collections.emptyList());
    }

    @Test
    public void execute_list_showsAllPersons() throws Exception {
        // prepare expectations
        TestDataHelper helper = new TestDataHelper();
        Rms expectedAB = helper.generateRms(false, true);
        List<? extends ReadOnlyPerson> expectedList = expectedAB.getAllPersons().immutableListView();

        // prepare address book state
        helper.addToRms(rms, false, true);

        assertCommandBehavior("list",
                Command.getMessageForPersonListShownSummary(expectedList),
                expectedAB,
                true,
                expectedList);
    }

    //test for MenuListCommand
    @Test
    public void execute_list_showsAllMenuItems() throws Exception {
        // prepare expectations
        // TestDataHelper helper = new TestDataHelper();
        Rms expectedRMS = new Rms();
        List<? extends ReadOnlyMenus> expectedRMSList = expectedRMS.getAllMenus().immutableListView();

        // prepare address book state
        //helper.addToRMS(rms, expectedRMSList);

        assertMenuCommandBehavior("listmenu",
                Command.getMessageForMenuListShownSummary(expectedRMSList),
                expectedRMS,
                true,
                expectedRMSList);
    }

    @Test
    public void execute_listemp_successful() throws Exception {
        // prepare expectations
        TestDataHelper helper = new TestDataHelper();

        Employee e1 = helper.generateEmployee(1);
        Employee e2 = helper.generateEmployee(2);
        List<Employee> lastShownList = helper.generateEmployeeList(e1, e2);

        Rms expectedRms = helper.generateRmsEmployees(lastShownList);
        List<? extends ReadOnlyEmployee> expectedList = expectedRms.getAllEmployees().immutableListView();

        // prepare address book state
        helper.addEmployeesToRms(rms, lastShownList);

        assertEmployeeCommandBehavior("listemp",
                Command.getMessageForEmployeeListShownSummary(expectedList),
                expectedRms,
                true,
                expectedList);
    }

    @Test
    public void execute_view_invalidArgsFormat() throws Exception {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE);
        assertCommandBehavior("view ", expectedMessage);
        assertCommandBehavior("view arg not number", expectedMessage);
    }

    @Test
    public void execute_view_invalidIndex() throws Exception {
        assertInvalidIndexBehaviorForCommand("view");
    }

    /**
     * Confirms the 'invalid argument index number behaviour' for the given command
     * targeting a single person in the last shown list, using visible index.
     * @param commandWord to test assuming it targets a single person in the last shown list based on visible index.
     */
    private void assertInvalidIndexBehaviorForCommand(String commandWord) throws Exception {
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        TestDataHelper helper = new TestDataHelper();
        List<Person> lastShownList = helper.generatePersonList(false, true);

        logic.setLastShownList(lastShownList);

        assertCommandBehavior(commandWord + " -1", expectedMessage, Rms.empty(), false, lastShownList);
        assertCommandBehavior(commandWord + " 0", expectedMessage, Rms.empty(), false, lastShownList);
        assertCommandBehavior(commandWord + " 3", expectedMessage, Rms.empty(), false, lastShownList);

    }

    /**
     * Confirms the 'invalid argument index number behaviour' for the given command
     * targeting a single employee in the last shown list, using visible index.
     * @param commandWord to test assuming it targets a single employee in the last shown list based on visible index.
     */
    private void assertInvalidIndexBehaviorForEmployeeCommand(String commandWord) throws Exception {
        String expectedMessage = Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX;
        TestDataHelper helper = new TestDataHelper();

        Employee e1 = helper.generateEmployee(1);
        Employee e2 = helper.generateEmployee(2);
        List<Employee> lastShownList = helper.generateEmployeeList(e1, e2);

        logic.setLastShownEmployeeList(lastShownList);

        assertEmployeeCommandBehavior(commandWord + " -1", expectedMessage, Rms.empty(), false, lastShownList);
        assertEmployeeCommandBehavior(commandWord + " 0", expectedMessage, Rms.empty(), false, lastShownList);
        assertEmployeeCommandBehavior(commandWord + " 3", expectedMessage, Rms.empty(), false, lastShownList);
    }

    @Test
    public void execute_view_onlyShowsNonPrivate() throws Exception {

        TestDataHelper helper = new TestDataHelper();
        Person p1 = helper.generatePerson(1, true);
        Person p2 = helper.generatePerson(2, false);
        List<Person> lastShownList = helper.generatePersonList(p1, p2);
        Rms expectedAB = helper.generateRms(lastShownList);
        helper.addToRms(rms, lastShownList);

        logic.setLastShownList(lastShownList);

        assertCommandBehavior("view 1",
                String.format(ViewCommand.MESSAGE_VIEW_PERSON_DETAILS, p1.getAsTextHidePrivate()),
                expectedAB,
                false,
                lastShownList);

        assertCommandBehavior("view 2",
                String.format(ViewCommand.MESSAGE_VIEW_PERSON_DETAILS, p2.getAsTextHidePrivate()),
                expectedAB,
                false,
                lastShownList);
    }

    @Test
    public void execute_tryToViewMissingPerson_errorMessage() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Person p1 = helper.generatePerson(1, false);
        Person p2 = helper.generatePerson(2, false);
        List<Person> lastShownList = helper.generatePersonList(p1, p2);

        Rms expectedAB = new Rms();
        expectedAB.addPerson(p2);

        rms.addPerson(p2);
        logic.setLastShownList(lastShownList);

        assertCommandBehavior("view 1",
                Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK,
                expectedAB,
                false,
                lastShownList);
    }

    @Test
    public void execute_viewAll_invalidArgsFormat() throws Exception {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewAllCommand.MESSAGE_USAGE);
        assertCommandBehavior("viewall ", expectedMessage);
        assertCommandBehavior("viewall arg not number", expectedMessage);
    }

    //test for MenuViewAll Command testing for valid arguments
    @Test
    public void execute_MenuviewAll_invalidArgsFormat() throws Exception {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MenuViewAllCommand.MESSAGE_USAGE);
        assertMenuCommandBehavior("viewallmenu ", expectedMessage);
        assertMenuCommandBehavior("viewallmenu arg not number", expectedMessage);
    }

    @Test
    public void execute_viewAll_invalidIndex() throws Exception {
        assertInvalidIndexBehaviorForCommand("viewall");
    }

    @Test
    public void execute_viewAll_alsoShowsPrivate() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Person p1 = helper.generatePerson(1, true);
        Person p2 = helper.generatePerson(2, false);
        List<Person> lastShownList = helper.generatePersonList(p1, p2);
        Rms expectedAB = helper.generateRms(lastShownList);
        helper.addToRms(rms, lastShownList);

        logic.setLastShownList(lastShownList);

        assertCommandBehavior("viewall 1",
                String.format(ViewCommand.MESSAGE_VIEW_PERSON_DETAILS, p1.getAsTextShowAll()),
                expectedAB,
                false,
                lastShownList);

        assertCommandBehavior("viewall 2",
                String.format(ViewCommand.MESSAGE_VIEW_PERSON_DETAILS, p2.getAsTextShowAll()),
                expectedAB,
                false,
                lastShownList);
    }

    @Test
    public void execute_tryToViewAllPersonMissingInAddressBook_errorMessage() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Person p1 = helper.generatePerson(1, false);
        Person p2 = helper.generatePerson(2, false);
        List<Person> lastShownList = helper.generatePersonList(p1, p2);

        Rms expectedAB = new Rms();
        expectedAB.addPerson(p1);

        rms.addPerson(p1);
        logic.setLastShownList(lastShownList);

        assertCommandBehavior("viewall 2",
                Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK,
                expectedAB,
                false,
                lastShownList);
    }

    @Test
    public void execute_delete_invalidArgsFormat() throws Exception {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);
        assertCommandBehavior("delete ", expectedMessage);
        assertCommandBehavior("delete arg not number", expectedMessage);
    }

    @Test
    public void execute_delemp_invalidArgsFormat() throws Exception {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmployeeDeleteCommand.MESSAGE_USAGE);
        assertEmployeeCommandBehavior("delemp ", expectedMessage);
        assertEmployeeCommandBehavior("delemp arg not number", expectedMessage);
    }

    @Test
    public void execute_delete_invalidIndex() throws Exception {
        assertInvalidIndexBehaviorForCommand("delete");
    }

    @Test
    public void execute_delemp_invalidIndex() throws Exception {
        assertInvalidIndexBehaviorForEmployeeCommand("delemp");
    }

    @Test
    public void execute_delete_removesCorrectPerson() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Person p1 = helper.generatePerson(1, false);
        Person p2 = helper.generatePerson(2, true);
        Person p3 = helper.generatePerson(3, true);

        List<Person> threePersons = helper.generatePersonList(p1, p2, p3);

        Rms expectedAB = helper.generateRms(threePersons);
        expectedAB.removePerson(p2);


        helper.addToRms(rms, threePersons);
        logic.setLastShownList(threePersons);

        assertCommandBehavior("delete 2",
                String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, p2),
                expectedAB,
                false,
                threePersons);
    }

    @Test
    public void execute_delemp_removesCorrectEmployee() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Employee e1 = helper.generateEmployee(1);
        Employee e2 = helper.generateEmployee(2);
        Employee e3 = helper.generateEmployee(3);

        List<Employee> threeEmployees = helper.generateEmployeeList(e1, e2, e3);

        Rms expectedRms = helper.generateRmsEmployees(threeEmployees);
        expectedRms.removeEmployee(e2);


        helper.addEmployeesToRms(rms, threeEmployees);
        logic.setLastShownEmployeeList(threeEmployees);

        assertEmployeeCommandBehavior("delemp 2",
                String.format(EmployeeDeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS, e2),
                expectedRms,
                false,
                threeEmployees);
    }

    @Test
    public void execute_delete_missingInAddressBook() throws Exception {

        TestDataHelper helper = new TestDataHelper();
        Person p1 = helper.generatePerson(1, false);
        Person p2 = helper.generatePerson(2, true);
        Person p3 = helper.generatePerson(3, true);

        List<Person> threePersons = helper.generatePersonList(p1, p2, p3);

        Rms expectedAB = helper.generateRms(threePersons);
        expectedAB.removePerson(p2);

        helper.addToRms(rms, threePersons);
        rms.removePerson(p2);
        logic.setLastShownList(threePersons);

        assertCommandBehavior("delete 2",
                Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK,
                expectedAB,
                false,
                threePersons);
    }

    @Test
    public void execute_delemp_missingInRms() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Employee e1 = helper.generateEmployee(1);
        Employee e2 = helper.generateEmployee(2);
        Employee e3 = helper.generateEmployee(3);

        List<Employee> threeEmployees = helper.generateEmployeeList(e1, e2, e3);

        Rms expectedRms = helper.generateRmsEmployees(threeEmployees);
        expectedRms.removeEmployee(e2);


        helper.addEmployeesToRms(rms, threeEmployees);
        rms.removeEmployee(e2);
        logic.setLastShownEmployeeList(threeEmployees);

        assertEmployeeCommandBehavior("delemp 2",
                Messages.MESSAGE_EMPLOYEE_NOT_IN_RMS,
                expectedRms,
                false,
                threeEmployees);
    }

    @Test
    public void execute_find_invalidArgsFormat() throws Exception {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);
        assertCommandBehavior("find ", expectedMessage);
    }

    @Test
    public void execute_find_onlyMatchesFullWordsInNames() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Person pTarget1 = helper.generatePersonWithName("bla bla KEY bla");
        Person pTarget2 = helper.generatePersonWithName("bla KEY bla bceofeia");
        Person p1 = helper.generatePersonWithName("KE Y");
        Person p2 = helper.generatePersonWithName("KEYKEYKEY sduauo");

        List<Person> fourPersons = helper.generatePersonList(p1, pTarget1, p2, pTarget2);
        Rms expectedAB = helper.generateRms(fourPersons);
        List<Person> expectedList = helper.generatePersonList(pTarget1, pTarget2);
        helper.addToRms(rms, fourPersons);

        assertCommandBehavior("find KEY",
                Command.getMessageForPersonListShownSummary(expectedList),
                expectedAB,
                true,
                expectedList);
    }

    @Test
    public void execute_find_isCaseSensitive() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Person pTarget1 = helper.generatePersonWithName("bla bla KEY bla");
        Person pTarget2 = helper.generatePersonWithName("bla KEY bla bceofeia");
        Person p1 = helper.generatePersonWithName("key key");
        Person p2 = helper.generatePersonWithName("KEy sduauo");

        List<Person> fourPersons = helper.generatePersonList(p1, pTarget1, p2, pTarget2);
        Rms expectedAB = helper.generateRms(fourPersons);
        List<Person> expectedList = helper.generatePersonList(pTarget1, pTarget2);
        helper.addToRms(rms, fourPersons);

        assertCommandBehavior("find KEY",
                Command.getMessageForPersonListShownSummary(expectedList),
                expectedAB,
                true,
                expectedList);
    }

    @Test
    public void execute_find_matchesIfAnyKeywordPresent() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Person pTarget1 = helper.generatePersonWithName("bla bla KEY bla");
        Person pTarget2 = helper.generatePersonWithName("bla rAnDoM bla bceofeia");
        Person p1 = helper.generatePersonWithName("key key");
        Person p2 = helper.generatePersonWithName("KEy sduauo");

        List<Person> fourPersons = helper.generatePersonList(p1, pTarget1, p2, pTarget2);
        Rms expectedAB = helper.generateRms(fourPersons);
        List<Person> expectedList = helper.generatePersonList(pTarget1, pTarget2);
        helper.addToRms(rms, fourPersons);

        assertCommandBehavior("find KEY rAnDoM",
                Command.getMessageForPersonListShownSummary(expectedList),
                expectedAB,
                true,
                expectedList);
    }

    /**
     * A utility class to generate test data.
     */
    class TestDataHelper{

        Person adam() throws Exception {
            Name name = new Name("Adam Brown");
            Phone privatePhone = new Phone("111111", true);
            Email email = new Email("adam@gmail.com", false);
            Address privateAddress = new Address("111, alpha street", true);
            Tag tag1 = new Tag("tag1");
            Tag tag2 = new Tag("tag2");
            Set<Tag> tags = new HashSet<>(Arrays.asList(tag1, tag2));
            return new Person(name, privatePhone, email, privateAddress, tags);
        }

        Employee peter() throws Exception {
            EmployeeName name = new EmployeeName("Peter Lee");
            EmployeePhone phone = new EmployeePhone("91234567");
            EmployeeEmail email = new EmployeeEmail("PeterLee89@rms.com");
            EmployeeAddress address = new EmployeeAddress("Clementi Ave 2, Blk 543 #13-12");
            EmployeePosition position = new EmployeePosition("Cashier");
            return new Employee(name, phone, email, address, position);
        }

        Menu burger() throws Exception {
            MenuName name = new MenuName("Cheese Burger");
            Price price = new Price("5");
            Tag tag1 = new Tag("tag1");
            Tag tag2 = new Tag("tag2");
            Set<Tag> tags = new HashSet<>(Arrays.asList(tag1, tag2));
            return new Menu(name, price, tags);
        }

        /**
         * Generates a valid person using the given seed.
         * Running this function with the same parameter values guarantees the returned person will have the same state.
         * Each unique seed will generate a unique Person object.
         *
         * @param seed used to generate the person data field values
         * @param isAllFieldsPrivate determines if private-able fields (phone, email, address) will be private
         */
        Person generatePerson(int seed, boolean isAllFieldsPrivate) throws Exception {
            return new Person(
                    new Name("Person " + seed),
                    new Phone("" + Math.abs(seed), isAllFieldsPrivate),
                    new Email(seed + "@email", isAllFieldsPrivate),
                    new Address("House of " + seed, isAllFieldsPrivate),
                    new HashSet<>(Arrays.asList(new Tag("tag" + Math.abs(seed)), new Tag("tag" + Math.abs(seed + 1))))
            );
        }

        /**
         * Generates a valid employee using the given seed.
         * Running this function with the same parameter values guarantees the returned employee will have the same state.
         * Each unique seed will generate a unique Employee object.
         *
         * @param seed used to generate the employee data field values
         */
        Employee generateEmployee(int seed) throws Exception {
            return new Employee(
                    new EmployeeName("Employee " + seed),
                    new EmployeePhone("" + Math.abs(seed)),
                    new EmployeeEmail(seed + "@email"),
                    new EmployeeAddress("House of " + seed),
                    new EmployeePosition("Position "+ seed)
            );
        }

        /**
         * Generates a valid menu item using the given seed.
         * Running this function with the same parameter values guarantees the returned menu item will have the same state.
         * Each unique seed will generate a unique Person object.
         *
         * @param seed used to generate the person data field values
         * @param isAllFieldsPrivate determines if private-able fields (phone, email, address) will be private
         */
        Menu generateMenuItem(int seed, boolean isAllFieldsPrivate) throws Exception {
            return new Menu(
                    new MenuName("Person " + seed),
                    new Price("" + Math.abs(seed)),
                    new HashSet<>(Arrays.asList(new Tag("tag" + Math.abs(seed)), new Tag("tag" + Math.abs(seed + 1))))
            );
        }

        /** Generates the correct add command based on the person given */
        String generateAddCommand(Person p) {
            StringJoiner cmd = new StringJoiner(" ");

            cmd.add("add");

            cmd.add(p.getName().toString());
            cmd.add((p.getPhone().isPrivate() ? "pp/" : "p/") + p.getPhone());
            cmd.add((p.getEmail().isPrivate() ? "pe/" : "e/") + p.getEmail());
            cmd.add((p.getAddress().isPrivate() ? "pa/" : "a/") + p.getAddress());

            Set<Tag> tags = p.getTags();
            for(Tag t: tags){
                cmd.add("t/" + t.tagName);
            }

            return cmd.toString();
        }

        /** Generates the correct add command based on the person given */
        String generateAddEmpCommand(Employee e) {
            StringJoiner cmd = new StringJoiner(" ");

            cmd.add("addemp");

            cmd.add(e.getName().toString());
            cmd.add("p/" + e.getPhone().toString());
            cmd.add("e/" + e.getEmail().toString());
            cmd.add("a/" + e.getAddress().toString());
            cmd.add("pos/" + e.getPosition().toString());

            return cmd.toString();
        }

        /**
         * Generates an Rms with auto-generated persons.
         * @param isPrivateStatuses flags to indicate if all contact details of respective persons should be set to
         *                          private.
         */
        Rms generateRms(Boolean... isPrivateStatuses) throws Exception{
            Rms rms = new Rms();
            addToRms(rms, isPrivateStatuses);
            return rms;
        }

        /**
         * Generates an Rms based on the list of Persons given.
         */
        Rms generateRms(List<Person> persons) throws Exception{
            Rms rms = new Rms();
            addToRms(rms, persons);
            return rms;
        }

        /**
         * Generates an Rms based on the list of Employees given.
         */
        Rms generateRmsEmployees(List<Employee> employees) throws Exception{
            Rms rms = new Rms();
            addEmployeesToRms(rms, employees);
            return rms;
        }

        /**
         * Generates an Rms based on the list of Menu given.
         */
        Rms generateRmsMenu(List<Menu> menus) throws Exception{
            Rms rms = new Rms();
            addToRmsMenu(rms, menus);
            return rms;
        }

        /**
         * Adds auto-generated Person objects to the given Rms
         * @param rms The Rms to which the Persons will be added
         * @param isPrivateStatuses flags to indicate if all contact details of generated persons should be set to
         *                          private.
         */
        void addToRms(Rms rms, Boolean... isPrivateStatuses) throws Exception{
            addToRms(rms, generatePersonList(isPrivateStatuses));
        }

        /**
         * Adds the given list of Persons to the given Rms
         */
        void addToRms(Rms rms, List<Person> personsToAdd) throws Exception{
            for(Person p: personsToAdd){
                rms.addPerson(p);
            }
        }

        /**
         * Adds auto-generated Menu objects to the given Rms
         * @param rms The Rms to which the Menus will be added
         * @param isPrivateStatuses flags to indicate if details of generated persons should be set to
         *                          private.
         */
         /*void addToRMS(Rms rms, Boolean... isPrivateStatuses) throws Exception{
             addToRMS(rms, generatePersonList(isPrivateStatuses));
         }*/

        /**
         * Adds the given list of Menus to the given Rms
         */
        void addToRmsMenu(Rms rms, List<Menu> menusToAdd) throws Exception{
            for(Menu m: menusToAdd){
                rms.addMenu(m);
            }
        }

        /**
         * Adds the given list of Persons to the given Rms
         */
        void addEmployeesToRms(Rms rms, List<Employee> employeesToAdd) throws Exception{
            for(Employee e: employeesToAdd){
                rms.addEmployee(e);
            }
        }

        /**
         * Creates a list of Persons based on the give Person objects.
         */
        List<Person> generatePersonList(Person... persons) throws Exception{
            List<Person> personList = new ArrayList<>();
            for(Person p: persons){
                personList.add(p);
            }
            return personList;
        }

        /**
         * Creates a list of Employees based on the give Employee objects.
         */
        List<Employee> generateEmployeeList(Employee... employees) throws Exception{
            List<Employee> employeeList = new ArrayList<>();
            for(Employee e: employees){
                employeeList.add(e);
            }
            return employeeList;
        }

        /**
         * Creates a list of Menu Items based on the give Menu objects.
         */
        List<Menu> generateMenuList(Menu... menus) throws Exception{
            List<Menu> menuList = new ArrayList<>();
            for(Menu m: menus){
                menuList.add(m);
            }
            return menuList;
        }

        /**
         * Generates a list of Persons based on the flags.
         * @param isPrivateStatuses flags to indicate if all contact details of respective persons should be set to
         *                          private.
         */
        List<Person> generatePersonList(Boolean... isPrivateStatuses) throws Exception{
            List<Person> persons = new ArrayList<>();
            int i = 1;
            for(Boolean p: isPrivateStatuses){
                persons.add(generatePerson(i++, p));
            }
            return persons;
        }

        /**
         * Generates a Person object with given name. Other fields will have some dummy values.
         */
        Person generatePersonWithName(String name) throws Exception {
            return new Person(
                    new Name(name),
                    new Phone("1", false),
                    new Email("1@email", false),
                    new Address("House of 1", false),
                    Collections.singleton(new Tag("tag"))
            );
        }

        /**
         * Generates a Menu object with given name. Other fields will have some dummy values.
         */
        Menu generateMenuWithName(String name) throws Exception {
            return new Menu(
                    new MenuName(name),
                    new Price("5"),
                    Collections.singleton(new Tag("tag"))
            );
        }
    }
}

