package ro.mycode.Controller;

import ro.mycode.Models.Card;
import ro.mycode.Models.Plata;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerPlata {

    private ArrayList<Plata> plati;

    public ControllerPlata (){
        plati = new ArrayList<>();
        this.load();
    }
    public ControllerPlata (String text){
        plati = new ArrayList<>();
    }
    public void load (){
        try {
            File file = new File("D:\\mycode\\incapsulare\\Proiecte\\product-management\\src\\ro\\mycode\\Data\\plata.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String text = scanner.nextLine();
                Plata plata = new Plata(text);
                this.plati.add(plata);
            }
            scanner.close();

        }catch (Exception e){

        }
    }
    public void save (){
        try{
            File file = new File("D:\\mycode\\incapsulare\\Proiecte\\product-management\\src\\ro\\mycode\\Data\\plata.txt");
            FileWriter writer = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.print(this.toSave());
            printWriter.close();


        }catch (Exception e){

        }
    }
    public String toSave(){
        String text = "";
        int i;
        for (i = 0; i < plati.size() - 1; i++){
            text += this.plati.get(i).toSave() + "\n";
        }
        text += this.plati.get(i).toSave();
        return text;
    }

    public void afisare (){
        for (int i = 0; i < plati.size(); i++){
            System.out.println(plati.get(i).descriere());
        }
    } public Plata getPlataByOrderId (int orderId){
        for (int i = 0; i < plati.size(); i++){
            if (plati.get(i).getOrderId() == orderId){
                return plati.get(i);
            }
        }
        return null;
    }
    public void addPlata (Plata plata){
        if (getPlataByOrderId(plata.getOrderId()) == null){
            this.plati.add(plata);
        }
    }
    public int pozitie (int orderId){
        for (int i = 0; i < plati.size(); i++){
            if (plati.get(i).getOrderId() == orderId){
                return i;
            }
        }
        return -1;
    }
    public void removePlata (int orderId){
        int poz = pozitie(orderId);
        if (poz != -1){
            this.plati.remove(poz);
        }

    }
    public int generareId (){
        if (this.plati.size() > 0){
            return plati.get(plati.size() - 1).getId() + 1;
        }
        return 1;
    }
}
