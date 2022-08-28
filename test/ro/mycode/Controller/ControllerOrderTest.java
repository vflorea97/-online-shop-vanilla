package ro.mycode.Controller;

import org.junit.jupiter.api.Test;
import ro.mycode.Models.Order;

import static org.junit.jupiter.api.Assertions.*;

class ControllerOrderTest {

    @Test
    public void addOrder() {
        ControllerOrder controllerOrder = new ControllerOrder("new");
        Order order = new Order(301,101,6);

        controllerOrder.addOrder(order);

        assertEquals(true, controllerOrder.getOrderByCustomerId(order.getCustomerId()) != null);
    }

    @Test
    public void pozitie() {
        ControllerOrder controllerOrder = new ControllerOrder("new");
        Order order = new Order(301,101,6);
        controllerOrder.addOrder(order);

        controllerOrder.pozitie(order.getId());

        assertEquals(0,controllerOrder.pozitie(order.getId()));
    }

    @Test
    public void removeOrder() {
        ControllerOrder controllerOrder = new ControllerOrder("new");
        Order order = new Order(301,101,6);

        controllerOrder.removeOrder(order.getId());

        assertEquals(-1,controllerOrder.pozitie(order.getId()));
    }

    @Test
    public void upDateOrder() {
        ControllerOrder controllerOrder = new ControllerOrder("new");
        Order order = new Order(301,101,6);
        controllerOrder.addOrder(order);

        controllerOrder.upDateOrder(new Order(301,101,8));

        assertEquals(8,controllerOrder.getOrderByCustomerId(order.getCustomerId()).getAmmount());
    }

    @Test
    public void generareId() {
        ControllerOrder controllerOrder = new ControllerOrder("new");
        Order order = new Order(301,101,6);
        controllerOrder.addOrder(order);

        controllerOrder.generareId();

        assertEquals(order.getId() + 1, controllerOrder.generareId());
    }
}