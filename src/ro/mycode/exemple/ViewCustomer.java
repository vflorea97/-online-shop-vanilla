package ro.mycode.exemple;

import ro.mycode.Controller.ControllerCustomer;
import ro.mycode.Controller.ControllerOrder;
import ro.mycode.Controller.ControllerOrderDetails;
import ro.mycode.Controller.ControllerProducts;
import ro.mycode.Models.Customer;
import ro.mycode.Models.Order;
import ro.mycode.Models.OrderDetails;
import ro.mycode.Models.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewCustomer {

    private ControllerCustomer controllerCustomer;
    private ControllerProducts controllerProducts;
    private ControllerOrderDetails controllerOrderDetails;
    private ControllerOrder controllerOrder;
    private Scanner scanner;
    private Customer customer;
    private Product product;
    private OrderDetails orderDetails;

    public ViewCustomer (Customer customer){
        controllerCustomer = new ControllerCustomer();
        controllerProducts = new ControllerProducts();
        controllerOrderDetails = new ControllerOrderDetails();
        controllerOrder = new ControllerOrder();
        scanner = new Scanner(System.in);
        this.customer = customer;
        this.product = product;
        this.orderDetails = orderDetails;
    }

    public void meniu (){
        System.out.println("Bun venit "+ customer.getFullName() + " !");
        System.out.println("Apasa 1 pentru a afisa datele.");
        System.out.println("Apasa 2 pentru a afisa produsele");
        System.out.println("Apasa 3 pentru a inregistra o comanda");
        System.out.println("Apasa 4 pentru a anula o comanda");
        System.out.println("Apasa 5 pentru a verifica o comanda");
        System.out.println("Apasa 6 pentru a modifica o comanda");
        System.out.println("Apasa 7 pentru a reafisa meniul");
        System.out.println("Apasa 0 pentru a te deconecta");
    }
    public void customerRun (){
        boolean run = true;
        meniu();
        while (run){
            int buton = Integer.parseInt(scanner.nextLine());
            switch (buton){
                case 1:
                    customer.descriere();
                    break;
                case 2:
                    controllerProducts.afisare();
                    break;
                case 3:
                    adaugareCos();
                    break;
                case 4:
                    removeComanda();
                    break;
                case 5:
                    verificareComanda();
                    break;
                case 6:
//                    modificareComanda();
                    break;
                case 0:
                    run = false;
                    System.out.println("La revedere!!!");
                    ViewLogin viewLogin = new ViewLogin();
                    viewLogin.start();
                    break;
                default:
                    meniu();
                    break;
            }
        }
    }
    public void adaugareCos (){
        System.out.println("Introdu numele produselor pe care vrei sa le cumperi si cantitatea!");
        int idOrderDetails1 = controllerOrder.generareId();
        int idOrder = controllerOrder.generareId();
        int ammount = 0;
        int customerId = customer.getId();
        boolean run = true;
        while (run){
            int idOrderDetails = controllerOrderDetails.generareId();
            int bucati = 0;
            int pret = 0;
            int idProdus = 0;
            boolean run2= true;
            String produs = "";
            while (run2) {
                System.out.println("Nume produs:");
                produs = scanner.nextLine();
                if (controllerProducts.produse(produs) != null) {
                    idProdus = controllerProducts.produse(produs).getId();
                    run2 = false;
                } else {
                    System.out.println("Produsul " + produs + " nu exista, sau este scris gresit.");
                }
            }
            System.out.println("Introdu cantitatea:");
            int cantitate = Integer.parseInt(scanner.nextLine());
            if (cantitate > 0){
            bucati = cantitate;
            ammount += cantitate;
            controllerProducts.diminuareCantitate(produs,cantitate);
            }
            pret = (cantitate * controllerProducts.produse(produs).getPrice());
            System.out.println("Vrei sa mai adaugi un produs! Da/Nu");
            String pass = scanner.nextLine();
            if (pass.equals("Nu")){
                run = false;
            }
            OrderDetails orderDetails = new OrderDetails(idOrderDetails,idOrderDetails1,idProdus,pret,bucati);
            controllerOrderDetails.addOrderDetails(orderDetails);
            controllerOrderDetails.save();
        }
        Order order = new Order(idOrder,customerId,ammount);
        controllerOrder.addOrder(order);
        controllerOrder.save();
        System.out.println("Numarul comenzii tale este: " + order.getId() + " !");
        System.out.println();
        customerRun();
    }
    public void verificareComanda (){
        System.out.println("Introdu numarul comenzii:");
        int idComanda = Integer.parseInt(scanner.nextLine());
        boolean run = true;
        while(run){
            if (controllerOrderDetails.findOrderOrderId(idComanda) != null){
                System.out.println("Ai in cos:");
                ArrayList<OrderDetails> orderDetails = controllerOrderDetails.orderDetails(idComanda);
                int pretTotal = 0;
                for (int i = 0; i < orderDetails.size(); i++){
                    System.out.println(controllerProducts.getProductById(orderDetails.get(i).getProductId()).getName());
                    System.out.println(controllerProducts.getProductById(orderDetails.get(i).getProductId()).getPrice() + " lei bucata");
                    System.out.println(orderDetails.get(i).getQuantity() + " bucati");
                    System.out.println(orderDetails.get(i).getPrice() + " lei");
                    System.out.println();
                    pretTotal += orderDetails.get(i).getPrice();
                }
                System.out.println("Pret total: " + pretTotal + " lei");
                System.out.println();
                run = false;
                customerRun();
            }else{
                System.out.println("Comanda cu numarul " + idComanda + " nu exista! Te rog introdu alt numar:");
                idComanda = Integer.parseInt(scanner.nextLine());
            }
        }
    }
    public void removeComanda (){
        System.out.println("Introdul numarul comenzii: ");
        int idComanda = Integer.parseInt(scanner.nextLine());
        boolean run = true;
        while (run){
            if (controllerOrderDetails.findOrderOrderId(idComanda) != null){
                controllerOrder.removeOrder(idComanda);
                controllerOrder.save();
                ArrayList <OrderDetails> orderDetails = controllerOrderDetails.orderDetails(idComanda);
                controllerOrderDetails.removeProdus(orderDetails,idComanda);
                run = false;
                System.out.println("Ai anulat cu succes comanda!");
                System.out.println();
                customerRun();
            }else{
                System.out.println("Comanda cu numarul " + idComanda + " nu exista! Te rog introdu alt numar:");
                idComanda = Integer.parseInt(scanner.nextLine());
            }
        }
    }
    public void meniuModificare(){
        System.out.println("Apasa 1 pentru a sterge un produs");
        System.out.println("Apasa 2 pentru a modifica cantitatea");
        System.out.println("Apasa 0 pentru a reveni la meniul anterior");
    }
//    public void modificareComanda (){
//        System.out.println("Introdu numarul comenzii:");
//        int idComanda = Integer.parseInt(scanner.nextLine());
//        boolean run = true;
//        while (run){
//            if (controllerOrderDetails.findOrderOrderId(idComanda) != null){
//                ArrayList<OrderDetails> orderDetails = controllerOrderDetails.orderDetails(idComanda);
//                meniuModificare();
//                int buton = Integer.parseInt(scanner.nextLine());
//                switch (buton) {
//                    case 1:
//                        System.out.println("Numele produsului:");
//                        boolean run1 = true;
//                        while (run1) {
//                            String nume = scanner.nextLine();
//                            if (controllerProducts.verificareProducts(nume)) {
//                                int idProduct = controllerProducts.getProductByName(nume).getId();
//                                int poz = controllerOrderDetails.pozitieInArray(orderDetails,idProduct);
//                                if (poz != -1) {
//                                    orderDetails.remove(orderDetails.get(poz));
//                                    controllerOrderDetails.removeProduct(idProduct);
//                                    controllerOrderDetails.save();
//                                    System.out.println("Ai stres produsul cu succes!");
//                                    System.out.println();
//                                    run1 = false;
//                                }else{
//                                    System.out.println("Produsul nu exista in cos. Introdu alt nume:");
//                                }
//                            }else{
//                                System.out.println("Produsul nu exista in cos. Introdu alt nume:");
//                            }
//                        }
//                        break;
//                    case 2:
//                        System.out.println("Numele produsului:");
//                        boolean run2 = true;
//                        while (run2) {
//                            String nume = scanner.nextLine();
//                            if (controllerProducts.verificareProducts(nume)) {
//                                int idProduct = controllerProducts.getProductByName(nume).getId();
//                                int poz = controllerOrderDetails.pozitieInArray(orderDetails,idProduct);
//                                if (poz != -1) {
//                                    System.out.println("Introdu noua cantitate:");
//                                    int cantitate = Integer.parseInt(scanner.nextLine());
//                                    controllerOrderDetails.modificareCantitate(idProduct, cantitate);
//                                    controllerOrderDetails.save();
//                                    System.out.println("Vrei sa mai modifici ceva? Da/Nu");
//                                    String pass = scanner.nextLine();
//                                    if (pass.equals("Da")) {
//                                        System.out.println("Numele produsului:");
//                                    } else {
//                                        run2 = false;
//                                        System.out.println("Ai modificat cu succes cantitaile!!");
//                                        System.out.println();
//                                    }
//                                }else{
//                                    System.out.println("Produsul nu exista in cos. Introdu alt nume:");
//                                }
//                            }else{
//                                System.out.println("Produsul nu exista in cos. Introdu alt nume:");
//                            }
//                        }
//                        break;
//                    default:
//                        run = false;
//                        customerRun();
//                }
//            }else{
//                System.out.println("Comanda cu numarul " + idComanda + " nu exista! Te rog introdu alt numar:");
//                idComanda = Integer.parseInt(scanner.nextLine());
//            }
//        }
//    }
}
