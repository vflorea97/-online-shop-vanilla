package ro.mycode.Models;

public class Plata {
    private int id;
    private int orderId;
    private int cardId;

    public Plata (){
        this.id = 0;
        this.orderId = 0;
        this.cardId = 0;
    }
    public Plata (int id,int orderId,int cardId){
        this.id = id;
        this.orderId = orderId;
        this.cardId = cardId;
    }
    public Plata (String text){
        String [] prop = text.split(",");
        this.id = Integer.parseInt(prop[0]);
        this.orderId = Integer.parseInt(prop[1]);
        this.cardId = Integer.parseInt(prop[2]);
    }
    public String descriere (){
        String text = "";
        text += "Order id: " + this.orderId + "\n";
        text += "Card id: " + this.cardId + "\n";
        return text;
    }
    public String toSave (){
        return  this.id+","+this.orderId+","+this.cardId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
