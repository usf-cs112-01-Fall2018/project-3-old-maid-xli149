import java.util.ArrayList;
import java.util.Random;

public class OldMaidHand extends CardStack {
    private String name;
    public OldMaidHand(String name) {
        super();
        this.name = name;
    }
//remove duplicated cards by traverse the arraylist using two variable to indicated the indice
    public void removeDuplicates() {
        int i = 0, j = 0;
        int removed = 0;
        while(i < super.getSize()){
            j = i + 1;
            while(j < super.getSize()) {
                if (super.getCard(i).getFace() == super.getCard(j).getFace()) {
                    super.removeCard(super.getCard(j));
                    removed++;
                    break;
                }
                j++;
            }
            if(removed == 1) {
                super.removeCard(super.getCard(i));
                removed = 0;
            }
            else{
                i++;
            }
        }
    }
    //display the stack of each player
    public void display(){
        System.out.println(name + " has: " +  super.toString());
        System.out.println();
    }
    // draw a card from one player
    public Card drawCard(){
        Random rand = new Random();
        int index = rand.nextInt(super.getSize());
        System.out.println("Draw a card from " + this.getName() + " stack.");
        System.out.println();
        Card card = super.getCard(index);
        super.removeCard(card);
        return card;
    }
    //shuffle the stack
    public void shuffle(){
        Random index = new Random();
        for(int i = 0; i < super.getSize(); i++){
            swap(i, index.nextInt(super.getSize()));
        }
    }
    public String getName(){
        return name;
    }

}