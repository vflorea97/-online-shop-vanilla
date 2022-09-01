package ro.mycode.Controller;

import org.junit.jupiter.api.Test;
import ro.mycode.Models.Card;
import ro.mycode.Models.Plata;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPlataTest {

    @Test
    public void getPlataByOrderId() {
        ControllerPlata controllerPlata = new ControllerPlata("new");
        Plata plata = new Plata(100,201,301);
        controllerPlata.addPlata(plata);

        controllerPlata.getPlataByOrderId(plata.getOrderId());

        assertEquals(true,controllerPlata.getPlataByOrderId(plata.getOrderId()) != null);
    }

    @Test
    public void addPlata() {
        ControllerPlata controllerPlata = new ControllerPlata("new");
        Plata plata = new Plata(100,201,301);

        controllerPlata.addPlata(plata);

        assertEquals(plata.descriere(),controllerPlata.getPlataByOrderId(plata.getOrderId()).descriere());

    }

    @Test
    public void pozitie() {
        ControllerPlata controllerPlata = new ControllerPlata("new");
        Plata plata = new Plata(100,201,301);
        controllerPlata.addPlata(plata);

        assertEquals(0,controllerPlata.pozitie(plata.getOrderId()));
    }

    @Test
    public void removePlata() {
        ControllerPlata controllerPlata = new ControllerPlata("new");
        Plata plata = new Plata(100,201,301);
        controllerPlata.addPlata(plata);

        controllerPlata.removePlata(plata.getOrderId());

        assertEquals(-1,controllerPlata.pozitie(plata.getOrderId()));
    }

    @Test
    public void generareId() {
        ControllerPlata controllerPlata = new ControllerPlata("new");
        Plata plata = new Plata(100,201,301);
        controllerPlata.addPlata(plata);

        controllerPlata.generareId();

        assertEquals(101,controllerPlata.generareId());
    }
}