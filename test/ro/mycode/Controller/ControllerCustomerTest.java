package ro.mycode.Controller;

import org.junit.jupiter.api.Test;
import ro.mycode.Controller.ControllerCustomer;
import ro.mycode.Models.Customer;

import static org.junit.jupiter.api.Assertions.*;
class ControllerCustomerTest {

    @Test
    public void addCustomer() {
        ControllerCustomer controllerCustomer = new ControllerCustomer("new");
        Customer customer = new Customer(101,"bbb@gmail.com","pepene12","Andrei Ion");

        controllerCustomer.addCustomer(customer);

        assertEquals(true,controllerCustomer.getCustomerByEmail(customer.getEmail()) != null);
    }

    @Test
    public void pozitie() {
        ControllerCustomer controllerCustomer = new ControllerCustomer("new");
        Customer customer = new Customer(101,"bbb@gmail.com","pepene12","Andrei Ion");
        controllerCustomer.addCustomer(customer);

        controllerCustomer.pozitie(customer.getEmail());

        assertEquals(0,controllerCustomer.pozitie(customer.getEmail()));
    }

    @Test
    void removeCustomer() {
        ControllerCustomer controllerCustomer = new ControllerCustomer("new");
        Customer customer = new Customer(101,"bbb@gmail.com","pepene12","Andrei Ion");

        controllerCustomer.removeCustomer(customer.getEmail());

        assertEquals(-1,controllerCustomer.pozitie(customer.getEmail()));
    }

    @Test
    void upDateCustomer() {
        ControllerCustomer controllerCustomer = new ControllerCustomer("new");
        Customer customer = new Customer(101,"bbb@gmail.com","pepene12","Andrei Ion");
        controllerCustomer.addCustomer(customer);

        controllerCustomer.upDateCustomer(new Customer(101,"bbb@gmail.com","pepene14","Andrei Vasile"));

        assertEquals("pepene14",controllerCustomer.getCustomerByEmail("bbb@gmail.com").getPassword());
        assertEquals("Andrei Vasile",controllerCustomer.getCustomerByEmail(customer.getEmail()).getFullName());

    }

    @Test
    void passCustomer() {
        ControllerCustomer controllerCustomer = new ControllerCustomer("new");
        Customer customer = new Customer(101,"bbb@gmail.com","pepene12","Andrei Ion");
        controllerCustomer.addCustomer(customer);

        controllerCustomer.passCustomer(customer.getEmail(), customer.getPassword());

        assertEquals(true,controllerCustomer.passCustomer(customer.getEmail(), customer.getPassword()) != null);
    }

    @Test
    void isEmail() {
        ControllerCustomer controllerCustomer = new ControllerCustomer("new");
        Customer customer = new Customer(101,"bbb@gmail.com","pepene12","Andrei Ion");
        controllerCustomer.addCustomer(customer);

        controllerCustomer.isEmail(customer.getEmail());

        assertEquals(true,controllerCustomer.isEmail(customer.getEmail()));
    }

    @Test
    void generareId() {
        ControllerCustomer controllerCustomer = new ControllerCustomer("new");
        Customer customer = new Customer(101,"bbb@gmail.com","pepene12","Andrei Ion");
        controllerCustomer.addCustomer(customer);

        controllerCustomer.generareId();

        assertEquals(customer.getId() + 1, controllerCustomer.generareId());
    }
}