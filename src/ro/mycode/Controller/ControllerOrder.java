package ro.mycode.Controller;

import ro.mycode.Models.Order;
import ro.mycode.Models.OrderDetails;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerOrder {

    private ArrayList<Order> orders;

    public ControllerOrder (){
        orders = new ArrayList<>();
        this.load();
    }
    public ControllerOrder (String text){
        orders = new ArrayList<>();
    }

    public void load (){
        try{
            File file = new File("D:\\mycode\\incapsulare\\Proiecte\\product-management\\src\\ro\\mycode\\Data\\order.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String text = scanner.nextLine();
                Order order= new Order(text);
                this.orders.add(order);
            }
            scanner.close();

        }catch (Exception e){

        }
    }
    public void save (){
        try{
            File file = new File("D:\\mycode\\incapsulare\\Proiecte\\product-management\\src\\ro\\mycode\\Data\\order.txt");
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
        for (i = 0; i < orders.size() - 1; i++){
            text += this.orders.get(i).toSave() + "\n";
        }
        text += this.orders.get(i).toSave();
        return text;
    }

    public void afisare (){
        for (int i = 0; i < orders.size(); i++){
            System.out.println(orders.get(i).descriere());
        }
    }

    public Order getOrderByCustomerId (int customerId){
        for (int i = 0; i < orders.size(); i++){
            if (orders.get(i).getCustomerId() == customerId){
                return orders.get(i);
            }
        }
        return null;
    }

    public void addOrder (Order order){
        if (getOrderByCustomerId(order.getId()) == null){
            this.orders.add(order);

        }
    }
    public int pozitie (int orderId){
        for (int i = 0; i < orders.size(); i++){
            if (orders.get(i).getId() == orderId){
                return i;
            }
        }
        return -1;
    }
    public void removeOrder (int orderId){
        int poz = pozitie(orderId);
        if (poz != -1){
            this.orders.remove(poz);
        }
    }
    public void upDateOrder (Order od){
        int poz = pozitie(od.getId());
        if(poz!=-1){
            Order order = this.orders.get(poz);
            if (od.getAmmount() != 0){
                order.setAmmount(od.getAmmount());
            }
        }

    }
    public int generareId (){
        if (this.orders.size() > 0){
            return orders.get(orders.size() -1 ).getId() + 1;
        }
        return 1;
    }

}
