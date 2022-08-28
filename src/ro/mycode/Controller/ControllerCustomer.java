package ro.mycode.Controller;

import ro.mycode.Models.Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerCustomer {

    private ArrayList<Customer> customers;

    public ControllerCustomer(){
        customers = new ArrayList<>();
        this.load();
    }
    public ControllerCustomer(String text){
        customers = new ArrayList<>();
    }

    public void load (){
        try{
            File file = new File("D:\\mycode\\incapsulare\\Proiecte\\product-management\\src\\ro\\mycode\\Data\\customer.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String text = scanner.nextLine();
                Customer customer = new Customer(text);
                this.customers.add(customer);
            }
            scanner.close();

        }catch (Exception e){

        }
    }
    public void save (){
        try{
            File file = new File("D:\\mycode\\incapsulare\\Proiecte\\product-management\\src\\ro\\mycode\\Data\\customer.txt");
            FileWriter writer = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.print(this.toSave());
            printWriter.close();

        }catch (Exception e){

        }
    }
    public String toSave (){
        String text = "";
        int i;
        for (i = 0; i < customers.size() - 1; i++){
            text += this.customers.get(i).toSave() + "\n";
        }
        text += this.customers.get(i).toSave();
        return text;
    }

    public void afisare (){
        for (int i = 0; i < customers.size(); i++){
            System.out.println(customers.get(i).descriere());
        }
    }

    public Customer getCustomerByEmail (String email){
        for (int i = 0; i < customers.size(); i++){
            if (customers.get(i).getEmail().equals(email)){
                return customers.get(i);
            }
        }
        return null;
    }
    public void addCustomer (Customer customer){
        if (getCustomerByEmail(customer.getEmail()) == null){
            this.customers.add(customer);
        }
    }
    public int pozitie (String email){
        for (int i = 0; i < customers.size(); i++){
            if (customers.get(i).getEmail().equals(email)){
                return i;
            }
        }
        return -1;
    }
    public void removeCustomer (String email){
        int poz = pozitie(email);
        if (poz != -1){
            this.customers.remove(poz);
        }
    }
    public void upDateCustomer (Customer c){
        int poz = pozitie(c.getEmail());
        Customer customer = this.customers.get(poz);
        if (!c.getPassword().equals("")){
            customer.setPassword(c.getPassword());
        }
        if (!c.getFullName().equals("")){
            customer.setFullName(c.getFullName());
        }
    }
    public Customer passCustomer (String email,String password){
        for (int i = 0; i < customers.size(); i++){
            if (customers.get(i).getEmail().equals(email) && customers.get(i).getPassword().equals(password)){
                return customers.get(i);
            }
        }
        return null;
    }
    public boolean isEmail (String email){
        for (int i = 0; i < customers.size(); i++){
            if (customers.get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    public int generareId (){
        if (this.customers.size() > 0){
            return customers.get(customers.size() - 1).getId() + 1;
        }
        return 1;
    }
}
