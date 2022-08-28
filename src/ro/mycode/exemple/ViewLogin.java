package ro.mycode.exemple;

import ro.mycode.Controller.ControllerCustomer;
import ro.mycode.Models.Customer;

import java.util.Scanner;

public class ViewLogin {

    private ControllerCustomer controllerCustomer;
    private Scanner scanner;

    public ViewLogin (){
        controllerCustomer = new ControllerCustomer();
        scanner = new Scanner(System.in);
    }

    public void meniu (){
        System.out.println("Apasa tasta 1 pentru login");
        System.out.println("Apasa tasta 2 pentru a te inregistra");
        System.out.println("Apasa tasta 3 pentru a a reafisa meniul");
        System.out.println("Apasa tasta 0 pentru a inchide meniul");
    }

    public void start (){
        boolean run = true;
        meniu();
        while (run){
            int buton = Integer.parseInt(scanner.nextLine());
            switch (buton){
                case 1:
                    loginCustomer();
                    break;
                case 2:
                    inregistrare();
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    meniu();
                    break;
            }
        }
    }
    public void loginCustomer (){
        System.out.println("Introdu adresa de email si parola pentru a te conecata!");
        boolean run = true;
        while (run){
            System.out.println("Email: ");
            String email = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();
            Customer customer = controllerCustomer.passCustomer(email,password);
            if (customer != null) {
                ViewCustomer viewCustomer = new ViewCustomer(customer);
                viewCustomer.customerRun();
                run = false;
            }else{
                System.out.println("Credentiale incorecte" + "\n" + "Reintrodu email-ul si parola");
            }
        }
    }
    public void inregistrare (){
        int id = controllerCustomer.generareId();
        System.out.println("Introdu adresa de email:");
        String email = scanner.nextLine();
        while(controllerCustomer.isEmail(email)){
            System.out.println("Exista deja un cont cu aceasta adresa! Foloseste alt email");
            email = scanner.nextLine();;
        }
        System.out.println("Introdu numele si prenumele:");
        String nume = scanner.nextLine();
        System.out.println("Introdu o parola: ");
        String password = scanner.nextLine();
        Customer customer = new Customer(id,email,nume,password);
        controllerCustomer.addCustomer(customer);
        controllerCustomer.save();

        ViewCustomer viewCustomer = new ViewCustomer(customer);
        viewCustomer.customerRun();

    }
}
