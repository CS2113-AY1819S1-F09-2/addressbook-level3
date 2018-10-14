package seedu.addressbook.storage.jaxb;

import javax.xml.bind.annotation.XmlElement;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.employee.Employee;
import seedu.addressbook.data.employee.EmployeeName;
import seedu.addressbook.data.employee.EmployeePhone;
import seedu.addressbook.data.employee.EmployeeAddress;
import seedu.addressbook.data.employee.EmployeeEmail;
import seedu.addressbook.data.employee.ReadOnlyEmployee;




public class AdaptedEmployee {

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String phone;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String address;

    /**
     * No-arg constructor for JAXB use.
     */
    public AdaptedEmployee() {}

    public AdaptedEmployee(ReadOnlyEmployee source) {

        name = source.getName().fullName;

        phone= source.getPhone().value;

        email = source.getEmail().value;

        address = source.getAddress().value;

    }

    /**
     * Converts this jaxb-friendly adapted employee object into the Employee object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted employee
     */
    public Employee toModelType() throws IllegalValueException {
        final EmployeeName name = new EmployeeName(this.name);
        final EmployeePhone phone = new EmployeePhone(this.phone);
        final EmployeeEmail email = new EmployeeEmail(this.email);
        final EmployeeAddress address = new EmployeeAddress(this.address);
        return new Employee(name, phone, email, address);
    }
}
