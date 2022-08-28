package ro.mycode.View;

import ro.mycode.Controller.ControllerCustomer;
import ro.mycode.Controller.ControllerOrder;
import ro.mycode.Controller.ControllerOrderDetails;
import ro.mycode.Models.Customer;
import ro.mycode.Models.Order;
import ro.mycode.Models.OrderDetails;
import ro.mycode.Models.Product;
import ro.mycode.Controller.ControllerProducts;
import ro.mycode.exemple.ViewLogin;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewUser {


      private Customer customer;
      private ControllerOrderDetails controllerOrderDetails;
      private ControllerOrder controllerOrder;
      private ControllerProducts controllerProducts;
      private Order order;
      private Scanner scanner;

      public ViewUser (){
          customer = new Customer(102,"aaa@gmail.com","mail1mail2","Mihai Ionescu");
          controllerOrder = new ControllerOrder();
          controllerOrderDetails = new ControllerOrderDetails();
          controllerProducts = new ControllerProducts();
          scanner = new Scanner(System.in);
          order= new Order(controllerOrder.generareId(),customer.getId(),0);
      }

      public void meniu(){
          System.out.println("Bun venit " + customer.getFullName() +" !");
          System.out.println("Apasa 1 pentru a afisa datele personale");
          System.out.println("Apasa 2 pentru a afisa produsele");
          System.out.println("Apasa 3 pentru a adauga in cos");
          System.out.println("Apasa 4 pentru a afisa cosul");
          System.out.println("Apasa 5 pentru a sterge un produs din comanda");
      }
    public void customerRun (){
        boolean run = true;
        meniu();
        while (run){
            int buton = Integer.parseInt(scanner.nextLine());
            switch (buton){
                case 1:
                    String text = customer.descriere();
                    System.out.println(text);
                    break;
                case 2:
                    controllerProducts.afisare();
                    break;
                case 3:
                    adaugareProduseInCos();
                    break;
                case 4:
                    afisareComanda();
                    break;
                case 5:
                    removeProduct();
                    break;
                case 0:
                    run = false;
                    System.out.println("La revedere!!!");
                    break;
                default:
                    meniu();
                    break;
            }
        }
    }

    public  void adaugareProduseInCos (){
        System.out.println("Introdu numele produsului pe care vrei sa il adaugi in cos: ");
        String nume = scanner.nextLine();
        System.out.println("Ce cantitate: ");
        int cantitate = Integer.parseInt(scanner.nextLine());
        Product p=controllerProducts.getProductByName(nume);

        if(p!=null&&p.getStock()>cantitate){

            OrderDetails details = new OrderDetails(controllerOrderDetails.generareId(),
                    order.getId(),
                    p.getId(),cantitate*p.getPrice(),
                    cantitate);

            controllerOrderDetails.addOrderDetails(details);
            System.out.println("Ai adaugat cu succesc un produs!");

            p.setStock(p.getStock()-cantitate);
        }else if(p==null){

            System.out.println("Produsul nu exista");
        }else if(p.getStock()<cantitate){
            System.out.println("Stoc insuficient");
        }


        //ce: daca exista produsul  si daca avem cantitatea dorita

    }
    public void afisareComanda (){
        ArrayList<OrderDetails> orderDetails=controllerOrderDetails.orderDetails(order.getId());
        int total=0;
        for(OrderDetails oi:orderDetails){
            total+=oi.getPrice();
             Product p =controllerProducts.getProductById(oi.getProductId());
             String text=p.getName()+" nr buc "+oi.getQuantity()+" pret "+oi.getPrice();
             System.out.println(text);
        }
        System.out.println("Total comanda este "+total);
    }

    public void removeProduct (){
        System.out.println("Introdu numele produsului: ");
        String nume = scanner.nextLine();
        //etapa 1 verificam daca are in cos produsul introdus

        Product product=controllerProducts.getProductByName(nume);
        if(product!=null&&controllerOrderDetails.verificareProdusComanda(product.getId(), order.getId())==true){
             controllerOrderDetails.removeProduct(product.getId(), order.getId());
             product.setStock(product.getStock()+controllerOrderDetails.orderDetails(product.getId(), order.getId()).getQuantity());
            System.out.println("Ai sters cu suuces produsul!");

        }else if(product == null){
            System.out.println("Produsul nu exista");
        }else if (!controllerOrderDetails.verificareProdusComanda(product.getId(), order.getId())){
            System.out.println("Produsul nu exista in cos");
        }
    }
}
