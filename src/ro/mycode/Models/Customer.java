package ro.mycode.Models;

public class Customer {

    private int id;
    private String email;
    private String password;
    private String fullName;

    public Customer (){
        this.id = 0;
        this.email = "";
        this.password = "";
        this.fullName = "";
    }

    public Customer(int id,String email,String password,String fullName){
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public Customer(String text){
        String [] proprietati = text.split(",");
        this.id = Integer.parseInt(proprietati[0]);
        this.email = proprietati[1];
        this.password = proprietati[2];
        this.fullName = proprietati[3];
    }

    public String descriere (){
        String text = "";
        text += "ID: " + this.id + "\n";
        text += "Email: " + this.email +"\n";
        text += "Password: " + this.password + "\n";
        text += "Full Name: " + this.fullName + "\n";
        return text;
    }

    public String toSave (){
        return this.id+","+this.email+","+this.password+","+this.fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
