package ro.mycode.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import ro.mycode.Models.OrderDetails;
import ro.mycode.Models.Product;

public class ControllerProducts {

    private ArrayList<Product> products;

    public ControllerProducts() {
        products = new ArrayList<>();
        this.load();
    }

    public ControllerProducts(String text) {
        products = new ArrayList<>();
    }

    public void load() {
        try {
            File file = new File("D:\\mycode\\incapsulare\\Proiecte\\product-management\\src\\ro\\mycode\\Data\\products.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String text = scanner.nextLine();
                Product product = new Product(text);
                this.products.add(product);
            }
            scanner.close();

        } catch (Exception e) {

        }
    }

    public void save() {
        try {
            File file = new File("D:\\mycode\\incapsulare\\Proiecte\\product-management\\src\\ro\\mycode\\Data\\products.txt");
            FileWriter writer = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.print(this.toSave());
            printWriter.close();

        } catch (Exception e) {

        }
    }

    public String toSave() {
        String text = "";
        int i;
        for (i = 0; i < products.size() - 1; i++) {
            text += this.products.get(i).toSave() + "\n";
        }
        text += this.products.get(i).toSave();
        return text;
    }

    public void afisare() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).descriere());
        }
    }

    public Product getProductById(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return products.get(i);
            }
        }
        return null;
    }
    public Product getProductByName (String nume){
        for (int i = 0; i < products.size(); i++){
            if (products.get(i).getName().equals(nume)){
                return products.get(i);
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        if (getProductById(product.getId()) == null) {
            this.products.add(product);
        }
    }
    public int pozitie(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    public void removeProducts(int id) {
        int poz = pozitie(id);
        if (poz != -1) {
            this.products.remove(poz);
        }
    }
    public void upDate(Product p) {
        int poz = pozitie(p.getId());
        Product product = this.products.get(poz);
        if (!p.getName().equals("")) {
            product.setName(p.getName());
        }
        if (p.getPrice() != 0) {
            product.setPrice(p.getPrice());
        }
        if (p.getStock() != 0) {
            product.setStock(p.getStock());
        }
    }
    public int generareId() {
        if (products.size() > 0) {
            return products.get(products.size() - 1).getId() + 1;
        }
        return 1;
    }
    public Product produse (String nume){
        for (int i = 0; i < products.size(); i++){
            if (products.get(i).getName().equals(nume)) {
                    return products.get(i);
            }
        }
        return null;
    }
    public boolean verificareProducts(String nume) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(nume)) {
                return true;
            }
        }
        return false;
    }
    public void diminuareCantitate (String produs,int cantitate){
        for (int i = 0; i < products.size(); i++){
            if (products.get(i).getId() == produse(produs).getId()){
                products.get(i).setStock(products.get(i).getStock() - cantitate);
                save();
            }
        }
    }

}



