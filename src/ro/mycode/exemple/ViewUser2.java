package ro.mycode.exemple;

import ro.mycode.Controller.*;
import ro.mycode.Models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewUser2 {


      private Customer customer;
      private ControllerOrderDetails controllerOrderDetails;
      private ControllerOrder controllerOrder;
      private ControllerProducts controllerProducts;
      private ControllerCard controllerCard;
      private ControllerPlata controllerPlata;
      private Order order;
      private Plata plata;
      private Card card;
      private Scanner scanner;

      public ViewUser2(){
          customer = new Customer(102,"aaa@gmail.com","mail1mail2","Mihai Ionescu");
          controllerOrder = new ControllerOrder();
          controllerOrderDetails = new ControllerOrderDetails();
          controllerProducts = new ControllerProducts();
          controllerCard = new ControllerCard();
          controllerPlata= new ControllerPlata();
          scanner = new Scanner(System.in);
          order= new Order(controllerOrder.generareId(),customer.getId(),0);
          card = new Card();
          plata = new Plata();
      }

      public void meniu(){
          System.out.println("Bun venit " + customer.getFullName() +" !");
          System.out.println("Apasa 1 pentru a afisa datele personale");
          System.out.println("Apasa 2 pentru a afisa produsele");
          System.out.println("Apasa 3 pentru a adauga in cos");
          System.out.println("Apasa 4 pentru a afisa cosul");
          System.out.println("Apasa 5 pentru a sterge un produs din comanda");
          System.out.println("Apasa 6 pentru a adauga un card");
          System.out.println("Apasa 7 pentru a afisa cardul");
          System.out.println("Apasa 8 pentru a achita comanda");
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
                case 6:
                    adugareCard();
                    break;
                case 7:
                    controllerCard.afisare();
                    break;
                case 8:
                    inregistrarePlata();
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

            OrderDetails details = new OrderDetails(controllerOrderDetails.generareId(), order.getId(),
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
        order.setAmmount(total);
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
    public void adugareCard (){
        int id = controllerCard.generareId();
        System.out.println("Introdu numele detinatorului: ");
        String nume = scanner.nextLine();
        System.out.println("Introdu numarul de card: ");
        long cardNumber = Long.parseLong(scanner.nextLine());
        while (controllerCard.isCardNumber(cardNumber)){
            System.out.println("Acest card este deja inregistrat! Introdu alt numar de card: ");
            cardNumber = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("Introdu data de expirare: " + "\n" + "Ziua: ");
        int day = Integer.parseInt(scanner.nextLine());
        System.out.println("Luna: ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.println("CVV: ");
        int cvv = Integer.parseInt(scanner.nextLine());
        card.setCvv(cvv);
        System.out.println("Adauga o suma in cardul virtual:");
        int sold = Integer.parseInt(scanner.nextLine());
        card.setSold(sold);
        Card card = new Card(id,nume,cardNumber,day,month,cvv,sold);
        controllerCard.addCard(card);

        System.out.println("Ai adaugat cardul cu succes!!");


    }
    public void inregistrarePlata (){
          if (card.getSold() > order.getAmmount()){
              int sold = card.getSold() -order.getAmmount();
              Plata plata = new Plata(controllerPlata.generareId(), order.getId(), card.getId());
              controllerCard.upDateCard(new Card(controllerCard.generareId(), customer.getFullName(),
                      card.getCardNumber(),card.getDay(),card.getMonth(),card.getCvv(),sold));
              System.out.println("Ai achitat cu succes comanda " + plata.getId());
          }else{
              System.out.println("Fonduri insuficiente");
          }

    }
}
