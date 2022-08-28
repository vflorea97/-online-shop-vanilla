package ro.mycode.Controller;

import org.junit.jupiter.api.Test;
import ro.mycode.Models.OrderDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerOrderDetailsTest {

    @Test
    public void addOrderDetails() {
        ControllerOrderDetails controllerOrderDetails = new ControllerOrderDetails("new");
        OrderDetails orderDetails = new OrderDetails(402,301,200,36,3);

        controllerOrderDetails.addOrderDetails(orderDetails);

        assertEquals(true,controllerOrderDetails.getOrderDetailsByOrderId(orderDetails.getId()) != null);
    }

    @Test
    public void pozitie() {
        ControllerOrderDetails controllerOrderDetails = new ControllerOrderDetails("new");
        OrderDetails orderDetails = new OrderDetails(402,301,200,36,3);
        controllerOrderDetails.addOrderDetails(orderDetails);

        controllerOrderDetails.pozitie(orderDetails.getId());

        assertEquals(0,controllerOrderDetails.pozitie(orderDetails.getOrderId()));
    }

    @Test
    public void removeOrderDetail() {
        ControllerOrderDetails controllerOrderDetails = new ControllerOrderDetails("new");
        OrderDetails orderDetails = new OrderDetails(402,301,200,36,3);
        controllerOrderDetails.addOrderDetails(orderDetails);

        controllerOrderDetails.removeOrderDetail(orderDetails.getOrderId());

        assertEquals(-1,controllerOrderDetails.pozitie(orderDetails.getOrderId()));
    }

    @Test
    public void upDateOrderDetails() {
        ControllerOrderDetails controllerOrderDetails = new ControllerOrderDetails("new");
        OrderDetails orderDetails = new OrderDetails(402,301,200,36,3);
        controllerOrderDetails.addOrderDetails(orderDetails);

        controllerOrderDetails.upDateOrderDetails(new OrderDetails(402,301,206,43,6));

        assertEquals(206,controllerOrderDetails.findOrderOrderId(orderDetails.getOrderId()).getProductId());
        assertEquals(43,controllerOrderDetails.findOrderOrderId(orderDetails.getOrderId()).getPrice());
        assertEquals(6, controllerOrderDetails.findOrderOrderId(orderDetails.getOrderId()).getQuantity());
    }

    @Test
    public void generareId() {
        ControllerOrderDetails controllerOrderDetails = new ControllerOrderDetails("new");
        OrderDetails orderDetails = new OrderDetails(402,301,200,36,3);
        controllerOrderDetails.addOrderDetails(orderDetails);

        controllerOrderDetails.generareId();

        assertEquals(orderDetails.getId() + 1,controllerOrderDetails.generareId());
    }

    @Test
    public void findOrderOrderId() {
        ControllerOrderDetails controllerOrderDetails = new ControllerOrderDetails("new");
        OrderDetails orderDetails = new OrderDetails(402,301,200,36,3);
        controllerOrderDetails.addOrderDetails(orderDetails);

        controllerOrderDetails.findOrderOrderId(orderDetails.getOrderId());

        assertEquals(orderDetails,controllerOrderDetails.findOrderOrderId(orderDetails.getOrderId()));
    }

    @Test
    public void orderDetails() {
        ControllerOrderDetails controllerOrderDetails = new ControllerOrderDetails("new");
        OrderDetails orderDetails = new OrderDetails(402,301,200,36,3);
        OrderDetails orderDetails1 = new OrderDetails(406,301,250,32,6);
        controllerOrderDetails.addOrderDetails(orderDetails);
        controllerOrderDetails.addOrderDetails(orderDetails1);

        ArrayList<OrderDetails> orders = new ArrayList<>();
        orders.add(orderDetails);
        orders.add(orderDetails1);

        assertEquals(orders.get(0),controllerOrderDetails.orderDetails(orderDetails.getOrderId()).get(0));
        assertEquals(orders.get(1),controllerOrderDetails.orderDetails(orderDetails1.getOrderId()).get(1));

    }

    @Test
    public void removeProduct() {
        ControllerOrderDetails controllerOrderDetails = new ControllerOrderDetails("new");
        OrderDetails orderDetails = new OrderDetails(402,301,200,36,3);
        controllerOrderDetails.addOrderDetails(orderDetails);

//        controllerOrderDetails.removeProduct(orderDetails.getProductId());

        assertEquals(-1,controllerOrderDetails.pozitie(orderDetails.getOrderId()));
    }

    @Test
    public void modificareCantitate() {
        ControllerOrderDetails controllerOrderDetails = new ControllerOrderDetails("new");
        OrderDetails orderDetails = new OrderDetails(402,301,200,36,3);
        controllerOrderDetails.addOrderDetails(orderDetails);

        controllerOrderDetails.modificareCantitate(orderDetails.getProductId(), 5);

        assertEquals(5,controllerOrderDetails.getOrderDetailsByOrderId(orderDetails.getId()).getQuantity());
    }

    @Test
    public void removeProdus() {
        ControllerOrderDetails controllerOrderDetails = new ControllerOrderDetails("new");
        OrderDetails orderDetails = new OrderDetails(402,301,200,36,3);
        OrderDetails orderDetails1 = new OrderDetails(412,311,250,31,5);

        ArrayList<OrderDetails> orders = new ArrayList<>();
        orders.add(orderDetails);
        orders.add(orderDetails1);

        controllerOrderDetails.removeProdus(orders,orderDetails.getOrderId());

        assertEquals(-1,controllerOrderDetails.pozitieInArray(orders,orderDetails.getProductId()));

    }

    @Test
    public void pozitieInArray() {
        ControllerOrderDetails controllerOrderDetails = new ControllerOrderDetails("new");
        OrderDetails orderDetails = new OrderDetails(402,301,200,36,3);
        OrderDetails orderDetails1 = new OrderDetails(401,301,206,34,6);

        ArrayList<OrderDetails> orders = new ArrayList<>();
        orders.add(orderDetails);
        orders.add(orderDetails1);

        assertEquals(0,controllerOrderDetails.pozitieInArray(orders,orderDetails.getProductId()));
        assertEquals(1,controllerOrderDetails.pozitieInArray(orders,orderDetails1.getProductId()));
    }
}