package ro.mycode.Models;

public class Product {

    private int id;
    private String name;
    private int price;
    private int stock;

    public Product(){
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.stock = 0;
    }

    public Product(int id,String name,int price,int stock){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product (String text){
        String [] proprietati = text.split(",");
        this.id = Integer.parseInt(proprietati[0]);
        this.name = proprietati[1];
        this.price = Integer.parseInt(proprietati[2]);
        this.stock = Integer.parseInt(proprietati[3]);

    }

    public String descriere (){
        String text = "";
        text += "ID: " + this.id + "\n";
        text += "Name: " + this.name + "\n";
        text += "Price: " + this.price + " lei" + "\n";
        text += "Stock: " +this.stock + "\n";
        return text;
    }

    public String toSave (){
        return this.id+","+this.name+","+this.price+","+this.stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
