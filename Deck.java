import java.util.Random;
//create a full deck with 52 cards and random remove one out then shuffle it.
public class Deck extends CardStack{
    public Deck() {
        super();
        for (int i = Card.CLUBS; i <= Card.SPADES; i++) {
            for (int j = Card.ACE; j <= Card.KING; j++) {
                Card card = new Card(j, i);
                super.addCard(card);
            }

            //System.out.println("The size is " + super.getSize());
        }
        System.out.println("The size of Deck before removing a random card is " + super.getSize());
        System.out.println();
        System.out.println("The card was removed is " + super.randomDeal());
        System.out.println();
        System.out.println("The size of Deck after removing a random card is " + super.getSize());
        System.out.println();
        System.out.println("Shuffle the cards.");
        System.out.println();
        this.shuffle();
    }
    public void shuffle(){
        Random index = new Random();
        for(int i = 0; i < super.getSize(); i++){
            swap(i, index.nextInt(super.getSize()));
        }
    }
}