package ro.mycode.Models;

public class Order {

    private int id;
    private int customerId;
    private int ammount;

    public Order(){
        this.id = 0;
        this.customerId = 0;
        this.ammount = 0;
    }

    public Order(int id, int customerId, int ammount){
        this.id = id;
        this.customerId = customerId;
        this.ammount = ammount;
    }

    public Order(String text){
        String [] proprietati = text.split(",");
        this.id = Integer.parseInt(proprietati[0]);
        this.customerId = Integer.parseInt(proprietati[1]);
        this.ammount = Integer.parseInt(proprietati[2]);
    }

    public String descriere (){
        String text = "";
        text += "ID: " + this.id + "\n";
        text += "CustomerId: " + this.customerId + "\n";
        text += "Ammount: " + this.ammount + "\n";
        return text;
    }
    public String toSave (){
        return this.id+","+this.customerId+","+this.ammount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }
}
