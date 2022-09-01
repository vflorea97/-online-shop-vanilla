package ro.mycode;

import ro.mycode.Models.Customer;
import ro.mycode.View.ViewUser;
import ro.mycode.exemple.ViewCustomer;
import ro.mycode.exemple.ViewLogin;
import ro.mycode.exemple.ViewUser2;

public class Main {

    public static void main(String[] args) {
//        ViewLogin viewLogin = new ViewLogin();
//        viewLogin.start();

//        ControllerOrderDetails controllerOrderDetails = new ControllerOrderDetails();
//
//        System.out.println(controllerOrderDetails.contor("201 202 202 "));

        Customer customer = new Customer(101,"bbb@gmail.com","pepene12","Andrei Ion");
        ViewCustomer viewCustomer = new ViewCustomer(customer);
        viewCustomer.customerRun();

//        ViewUser2 viewUser2 = new ViewUser2();
//        viewUser2.customerRun();

//        ViewUser viewUser = new ViewUser();
//        viewUser.customerRun();

    }
}
