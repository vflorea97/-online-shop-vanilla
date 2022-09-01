package ro.mycode.Controller;

import org.junit.jupiter.api.Test;
import ro.mycode.Models.Card;

import static org.junit.jupiter.api.Assertions.*;

class ControllerCardTest {

    @Test
    public void getCardByCvv() {
        ControllerCard controllerCard = new ControllerCard("new");
        Card card = new Card(500,"Vali",45,5,5,456,3000);
        controllerCard.addCard(card);

        controllerCard.getCardByCvv(card.getCvv());

        assertEquals(true,controllerCard.getCardByCvv(card.getCvv()) != null);

    }

    @Test
    public void getCardById() {
        ControllerCard controllerCard = new ControllerCard("new");
        Card card = new Card(500,"Vali",45,5,5,456,3000);
        controllerCard.addCard(card);

        controllerCard.getCardById(card.getId());

        assertEquals(true,controllerCard.getCardById(card.getId()) != null);
    }

    @Test
    public void addCard() {
        ControllerCard controllerCard = new ControllerCard("new");
        Card card = new Card(500,"Vali",45,5,5,456,3000);

        controllerCard.addCard(card);

        assertEquals(card.descriere(),controllerCard.getCardByCvv(card.getCvv()).descriere());
    }

    @Test
    public void pozitie() {
        ControllerCard controllerCard = new ControllerCard("new");
        Card card = new Card(500,"Vali",45,5,5,456,3000);
        controllerCard.addCard(card);

        controllerCard.pozitie(card.getCvv());

        assertEquals(0,controllerCard.pozitie(card.getCvv()));
    }

    @Test
    public void removeCard() {
        ControllerCard controllerCard = new ControllerCard("new");
        Card card = new Card(500,"Vali",45,5,5,456,3000);
        controllerCard.addCard(card);

        controllerCard.removeCard(card.getCvv());

        assertEquals(-1,controllerCard.pozitie(card.getCvv()));
    }

    @Test
    public void upDateCard() {
        ControllerCard controllerCard = new ControllerCard("new");
        Card card = new Card(500,"Vali",45,5,5,456,3000);
        controllerCard.addCard(card);

        controllerCard.upDateCard(new Card(500,"Vali",45,5,5,456,2000));

        assertEquals(2000,controllerCard.getCardByCvv(card.getCvv()).getSold());
    }

    @Test
    public void generareId() {
        ControllerCard controllerCard = new ControllerCard("new");
        Card card = new Card(500,"Vali",45,5,5,456,3000);
        controllerCard.addCard(card);

        controllerCard.generareId();

        assertEquals(501,controllerCard.generareId());
    }

    @Test
    public void isCardNumber() {
        ControllerCard controllerCard = new ControllerCard("new");
        Card card = new Card(500,"Vali",45,5,5,456,3000);
        controllerCard.addCard(card);

        controllerCard.isCardNumber(card.getCardNumber());

        assertEquals(true,controllerCard.isCardNumber(card.getCardNumber()));
    }
}