package ro.mycode.Controller;

import ro.mycode.Models.Card;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerCard {

    private ArrayList<Card> cards;

    public ControllerCard (){
        cards = new ArrayList<>();
        this.load();
    }
    public ControllerCard(String text){
        cards = new ArrayList<>();
    }
    public void load (){
        try {
            File file = new File("D:\\mycode\\incapsulare\\Proiecte\\product-management\\src\\ro\\mycode\\Data\\card.text");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String text = scanner.nextLine();
                Card card = new Card(text);
                this.cards.add(card);
            }
            scanner.close();

        }catch (Exception e){

        }
    }
    public void save (){
        try{
            File file = new File("D:\\mycode\\incapsulare\\Proiecte\\product-management\\src\\ro\\mycode\\Data\\card.text");
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
        for (i = 0; i < cards.size() - 1; i++){
            text += this.cards.get(i).toSave() + "\n";
        }
        text += this.cards.get(i).toSave();
        return text;
    }

    public void afisare (){
        for (int i = 0; i < cards.size(); i++){
            System.out.println(cards.get(i).descriere());
        }
    }
    public Card getCardByCvv (int cvv){
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i).getCvv() == cvv){
                return cards.get(i);
            }
        }
        return null;
    }
    public Card getCardById (int id){
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i).getId() == id){
                return cards.get(i);
            }
        }
        return null;
    }
    public void addCard (Card card){
        if (getCardByCvv(card.getCvv()) == null){
            this.cards.add(card);
        }
    }
    public int pozitie (int cvv){
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i).getCvv() == cvv){
                return i;
            }
        }
        return -1;
    }
    public void removeCard (int cvv){
        int poz = pozitie(cvv);
        if (poz != -1){
            this.cards.remove(poz);
        }

    }
    public void upDateCard(Card c){
        int poz = pozitie(c.getCvv());
        Card card = this.cards.get(poz);
        if (c.getSold() != 0){
            card.setSold(c.getSold());
        }
    }
    public int generareId (){
        if (this.cards.size() > 0){
            return cards.get(cards.size() - 1).getId() + 1;
        }
        return 1;
    }
    public boolean isCardNumber (long cardNumber){
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i).getCardNumber() == cardNumber){
                return true;
            }
        }
        return false;
    }
}
