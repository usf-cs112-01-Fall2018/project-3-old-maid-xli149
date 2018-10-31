import java.util.*;
public class OldMaid {
    public static void main(String[] args) {
        boolean done = false;
        Scanner scan = new Scanner(System.in);
        Deck deckOfCard;
        ArrayList<OldMaidPlayer> data = new ArrayList<OldMaidPlayer>();
        ArrayList<CardStack> players = new ArrayList<CardStack>();
        OldMaid game = new OldMaid();
        game.getPlayers(scan, players, data);
        while (!done) {
            //create an arraylist to store the players who has been kicked out from ArrayList players
            //when one won
            ArrayList<CardStack> winnedPlayers = new ArrayList<>();
            deckOfCard = new Deck();
            game.getHands(deckOfCard, players);
            System.out.println("The removed pairs are: ");
            System.out.println();
            for (int z = 0; z < players.size(); z++) {
                ((OldMaidHand) players.get(z)).removeDuplicates();
                System.out.println();
            }
            System.out.println();
            System.out.println("The remaining stack on each hand are:");
            System.out.println();
            for(int i = 0; i < players.size(); i++ ){
                ((OldMaidHand)players.get(i)).display();
            }
            //pick a random player to go first
            Random rand = new Random();
            int i = rand.nextInt(players.size());
            System.out.println();
            System.out.println(((OldMaidHand) players.get(i)).getName() + " plays first.");
            System.out.println();
            int count = 0;
            System.out.println("Game start!");
            System.out.println();
            Card card;
            while (players.size() != 1) {
                if(i >= (players.size())) {
                    i = 0;
                }
                //draw a card from one player and add it to the next one.
                if(i == 0){
                    card = ((OldMaidHand) players.get(players.size() - 1)).drawCard();
                    System.out.println();
                    //update the score and remove the players who doesn't have cards on their hands.
                    if(players.get(players.size() - 1).getSize() == 0){
                        count++;
                        game.update(players.get(0), data, count);
                        winnedPlayers.add(players.get(0));
                        players.remove(players.get(0));
                    }
                    players.get(0).addCard(card);
                    //remove duplicated pairs after adding a card in hand.
                    System.out.println("remove the duplicated cards from " + ((OldMaidHand) players.get(0)).getName()+ "'s stack" +
                                        " and add it to the next player's stack");
                    System.out.println();
                    ((OldMaidHand) players.get(0)).removeDuplicates();
                    if(players.get(0).getSize() != 0){
                        ((OldMaidHand) players.get(0)).shuffle();
                    }
                    if (players.get(0).getSize() == 0) {
                        count++;
                        game.update(players.get(0), data, count);
                        winnedPlayers.add(players.get(0));
                        players.remove(players.get(0));
                    }
                    else{
                        i++;
                    }
                }
                else{
                    card = ((OldMaidHand) players.get(i - 1)).drawCard();
                    System.out.println();
                    if(players.get(i - 1).getSize() == 0){
                        count++;
                        game.update(players.get(i - 1), data, count);
                        winnedPlayers.add(players.get(i - 1));
                        players.remove(players.get(i - 1));
                    }
                    if(i >= players.size()){
                        i = players.size() - 1;
                    }
                    players.get(i).addCard(card);
                    System.out.println("remove the duplicated cards from " + ((OldMaidHand) players.get(i)).getName()+ "'s stack");
                    System.out.println();
                    ((OldMaidHand) players.get(i)).removeDuplicates();
                    if(players.get(i).getSize() != 0){
                        ((OldMaidHand) players.get(i)).shuffle();
                    }
                    if (players.get(i).getSize() == 0) {
                        count++;
                        game.update(players.get(i), data, count);
                        winnedPlayers.add(players.get(i));
                        players.remove(players.get(i));
                    }
                    else{
                        i++;
                    }
                }

            }
            //updating the score of the last player.
            game.update(players.get(0), data);
            System.out.println();
            System.out.println("The scores are: ");
            //play the result of score.
            for(int x = 0; x < data.size(); x++){
                System.out.println(data.get(x));
            }
            System.out.println("Do you want to play again?(y/n)");
            String again = scan.nextLine();
            if(again.equals("n")){
                done = true;
            }
            //add the removed players back to players arraylist
            else{
                for(int h = 0; h < winnedPlayers.size(); h++){
                    players.add(winnedPlayers.get(h));
                }
            }

        }
    }
//add players to my arraylist
    public void getPlayers(Scanner scan, ArrayList<CardStack> players, ArrayList<OldMaidPlayer> data) {
        boolean done  = false;
        int totalPlayers = 0;
        while(!done){
            System.out.println("Enter the number of players from 2-8:");
            System.out.println();
            totalPlayers = scan.nextInt();
            String sp = scan.nextLine();
            if(totalPlayers < 2 || totalPlayers > 8){
                System.out.println("Violation, enter the number of player again");
                continue;
            }
            else {
                break;
            }
        }
        String name;
        for (int i = 0; i < totalPlayers; i++) {
            System.out.println("What's the name of player " + (i + 1));
            name = scan.nextLine();
            players.add(new OldMaidHand(name));
            data.add(new OldMaidPlayer(name));
        }
    }

    //deal Cards to each player
    public void getHands(Deck deckOfCard, ArrayList<CardStack> players) {
        int j = 0;
        while (deckOfCard.getSize() != 0) {
            players.get(j).addCard(deckOfCard.deal());
            j++;

            if (j == players.size()) {
                j = 0;
            }
        }
        for(int i = 0; i < players.size(); i++ ){
            ((OldMaidHand)players.get(i)).display();
        }
    }

    public void update(CardStack player, ArrayList<OldMaidPlayer> data, int count) {
        for (int i = 0; i < data.size(); i++) {
            if (((OldMaidHand) player).getName() == data.get(i).getName() && count == 1) {
                data.get(i).won();
                data.get(i).setPoints(2);
            } else if (((OldMaidHand) player).getName() == data.get(i).getName() && count < data.size()) {
                data.get(i).setPoints(1);
            }
        }
    }
//here I overloaded update method to take different amount of parameters
    public void update(CardStack player, ArrayList<OldMaidPlayer> data) {
        for (int i = 0; i < data.size(); i++) {
            if (((OldMaidHand) player).getName() == data.get(i).getName()) {
                data.get(i).lost();
            }
        }
    }
}