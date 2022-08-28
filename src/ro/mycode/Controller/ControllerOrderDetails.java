package ro.mycode.Controller;

import ro.mycode.Models.Order;
import ro.mycode.Models.OrderDetails;
import ro.mycode.Models.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerOrderDetails {

    private ArrayList<OrderDetails> orderDetails;

    public ControllerOrderDetails (){
        orderDetails = new ArrayList<>();
        this.load();
    }
    public ControllerOrderDetails (String text){
        orderDetails = new ArrayList<>();
    }

    public void load (){
        try{
            File file = new File("D:\\mycode\\incapsulare\\Proiecte\\product-management\\src\\ro\\mycode\\Data\\orderDetails.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String text = scanner.nextLine();
                OrderDetails orderDetails = new OrderDetails(text);
                this.orderDetails.add(orderDetails);
            }
            scanner.close();

        }catch (Exception e){

        }
    }
    public void save (){
        try{
            File file = new File("D:\\mycode\\incapsulare\\Proiecte\\product-management\\src\\ro\\mycode\\Data\\orderDetails.txt");
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
        for (i = 0; i < orderDetails.size() - 1; i++){
            text += this.orderDetails.get(i).toSave() + "\n";
        }
        text += this.orderDetails.get(i).toSave();
        return text;
    }

    public void afisare (){
        for (int i = 0; i < orderDetails.size(); i++){
            System.out.println(orderDetails.get(i).descriere());
        }
    }

    public OrderDetails getOrderDetailsByOrderId (int Id){
        for (int i = 0; i < orderDetails.size(); i++){
            if (orderDetails.get(i).getId() == Id){
                return orderDetails.get(i);
            }
        }
        return null;
    }
    public void addOrderDetails (OrderDetails orderDetails){
        if (getOrderDetailsByOrderId(orderDetails.getId()) == null){
            this.orderDetails.add(orderDetails);
        }
    }
    public int pozitie (int orderId){
        for (int i = 0; i < orderDetails.size(); i++){
            if (orderDetails.get(i).getOrderId() == orderId){
                return i;
            }
        }
        return -1;
    }
    public void removeOrderDetail (int orderId){
        int poz = pozitie(orderId);
        if (poz != -1){
            this.orderDetails.remove(poz);
        }
    }
    public void upDateOrderDetails (OrderDetails o){
        int poz = pozitie(o.getOrderId());
        OrderDetails orderDetails = this.orderDetails.get(poz);
        if (o.getProductId() != 0){
            orderDetails.setProductId(o.getProductId());
        }
        if (o.getPrice() != 0){
            orderDetails.setPrice(o.getPrice());
        }
        if (o.getQuantity() != 0){
            orderDetails.setQuantity(o.getQuantity());
        }
    }
    public int generareId (){
        if (orderDetails.size() > 0){
            return orderDetails.get(orderDetails.size() - 1).getId() + 1;
        }
        return 1;
    }
    public OrderDetails findOrderOrderId(int orderId){
        for (int i =0 ; i < orderDetails.size(); i++){
            if (orderDetails.get(i).getOrderId() == orderId){
                return orderDetails.get(i);
            }
        }
        return null;
    }
    public ArrayList<OrderDetails> orderDetails (int orderId){
        ArrayList<OrderDetails> order = new ArrayList<>();
        for (int i = 0; i < orderDetails.size(); i++){
            if (orderDetails.get(i).getOrderId() == orderId){
                order.add(orderDetails.get(i));
            }
        }
        return order;
    }

    public void removeProduct (int idProduct,int orderId){
        for (int i = 0; i < orderDetails.size(); i++){
            if (orderDetails.get(i).getProductId() == idProduct&&orderDetails.get(i).getOrderId()==orderId){
                this.orderDetails.remove(orderDetails.get(i));
            }
        }
    }
    public void modificareCantitate (int idProdus, int cantitate) {
        for (int i = 0; i < orderDetails.size(); i++) {
            if (orderDetails.get(i).getProductId() == idProdus) {
                orderDetails.get(i).setQuantity(cantitate);
            }
        }
    }
    public void removeProdus (ArrayList<OrderDetails> orderDetails,int idComanda){
        for (int i = 0; i < orderDetails.size(); i++){
            if (orderDetails.get(i).getOrderId() == idComanda){
                this.orderDetails.remove(orderDetails.get(i));
            }
        }
    }
    public int pozitieInArray (ArrayList<OrderDetails> orderDetails, int idProduct){
        for (int i = 0; i < orderDetails.size(); i++){
            if (orderDetails.get(i).getProductId() == idProduct){
                return i;
            }
        }
        return -1;
    }

    //todo:functie ce verifica daca un produs este intro anumita comanda

    public boolean verificareProdusComanda (int productId, int orderId){
        for (int i = 0; i < orderDetails.size(); i++){
            if (orderDetails.get(i).getOrderId() == orderId&& orderDetails.get(i).getProductId()==productId){

                return true;

            }
        }

        return  false;
    }
    public OrderDetails orderDetails(int idProduct,int orderId){
        for (int i = 0; i < orderDetails.size(); i++){
            if (orderDetails.get(i).getOrderId() == orderId && orderDetails.get(i).getProductId() == idProduct){
                return orderDetails.get(i);
            }
        }
        return null;
    }

}
