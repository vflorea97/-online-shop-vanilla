package ro.mycode.Models;

public class Card {
    private int id;
    private String cardHolderName;
    private long cardNumber;
    private int day;
    private int month;
    private int cvv;
    private int sold;

    public Card (){
        this.id = 0;
        this.cardHolderName = "";
        this.cardNumber = 0;
        this.day = 0;
        this.month = 0;
        this.cvv = 0;
        this.sold = 0;

    }
    public Card (int id,String cardHolderName,long cardNumber,int day,int month,int cvv,int sold){
        this.id = id;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.day = day;
        this.month = month;
        this.cvv = cvv;
        this.sold = sold;
    }
    public Card (String text){
        String [] prop = text.split(",");
        this.id = Integer.parseInt(prop[0]);
        this.cardHolderName = prop[1];
        this.cardNumber = Long.parseLong(prop[2]);
        this.day = Integer.parseInt(prop[3]);
        this.month = Integer.parseInt(prop[4]);
        this.cvv = Integer.parseInt(prop[5]);
        this.sold = Integer.parseInt(prop[6]);
    }
    public String descriere (){
        String text = "";
        text += "Nume proprietar: " + this.cardHolderName + "\n";
        text += "Numar card: " + this.cardNumber + "\n";
        text += "Ziua: " + this.day + "\n";
        text += "Luna: " + this.month + "\n";
        text += "Sold: " + this.sold + "\n";
        return text;
    }
    public String toSave (){
        return this.id+","+this.cardHolderName+","+this.cardNumber+","+this.day+","+this.month+","+this.cvv+","+this.sold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getSold(){ return sold; }

    public void setSold(int sold) {this.sold = sold; }
}
