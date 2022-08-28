package ro.mycode.Models;

public class OrderDetails {

    private int id;
    private int orderId;
    private int productId;
    private int price;
    private int quantity;

    public OrderDetails(){
        this.id = 0;
        this.orderId = 0;
        this.productId = 0;
        this.price = 0;
        this.quantity = 0;
    }

    public OrderDetails(int id, int orderId, int productId, int price, int quantity){
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderDetails(String text){
        String [] proprietati = text.split(",");
        this.id = Integer.parseInt(proprietati[0]);
        this.orderId = Integer.parseInt(proprietati[1]);
        this.productId = Integer.parseInt(proprietati[2]);
        this.price = Integer.parseInt(proprietati[3]);
        this.quantity = Integer.parseInt(proprietati[4]);
    }

    public String descriere (){
        String text = "";
        text += "ID: " + this.id + "\n";
        text += "OrderId: " + this.orderId + "\n";
        text += "ProductId: " + this.productId + "\n";
        text += "Price: " + this.price + "\n";
        text += "Quantity: " + this.quantity + "\n";
        return text;
    }

    public String toSave (){
        return this.id+","+this.orderId+","+this.productId+","+this.price+","+this.quantity;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
