//********************************************************************
//  CardStack.java       
//
//  
//********************************************************************

import java.util.*;

public class CardStack
{
    private ArrayList<Card> stack;
    private int size;

    //-----------------------------------------------------------------
    //  Creates a full deck with the cards initially "in order".
    //-----------------------------------------------------------------
    public CardStack()
    {
        stack = new ArrayList<Card>();
        size = 0;
    }

    //-----------------------------------------------------------------
    //  Adds a card to the collection.
    //-----------------------------------------------------------------
    public void addCard(Card c)
    {
        stack.add(c);
        size++;
    }
    //-----------------------------------------------------------------
    //  Deals the first card from the collection.
    //-----------------------------------------------------------------
    public Card deal()
    {
        if (size > 0) {
            size--;
            return stack.remove(0);
        } else {
            return null;
        }
    }

    //-----------------------------------------------------------------
    //  Deals a random card from the collection.
    //-----------------------------------------------------------------
    public Card randomDeal()
    {
        Random generator = new Random();
        int index = generator.nextInt(size);

        if (size > 0) {
            size--;
            return stack.remove(index);
        } else {
            return null;
        }       
    }

    //-----------------------------------------------------------------
    //  Removes a card from the stack of cards.
    //-----------------------------------------------------------------
    public boolean removeCard(Card c)
    {
        System.out.println("removing from stack ... "+c);
        if(stack.contains(c))
            size--;
        return stack.remove(c);
    }

    //-----------------------------------------------------------------
    //  Returns the ith card from the stack of cards.
    //-----------------------------------------------------------------
    public Card getCard(int i)
    {
        if(size>i) {
            return stack.get(i);
        }
        return null;
    }

    //-----------------------------------------------------------------
    //  Returns the number of cards left in the deck.
    //-----------------------------------------------------------------
    public int getSize()
    {
        return size;
    }

    //-----------------------------------------------------------------
    //  Returns true is the deck has cards in it, else false.
    //-----------------------------------------------------------------
    public boolean hasMoreCards()
    {
        return (size > 0);
    }

    //-----------------------------------------------------------------
    //  Swaps two cards in the Stack of Cards
    //-----------------------------------------------------------------
    public void swap(int position1, int position2)
    {
        Card temp;  
        temp = stack.get(position1);
        stack.set(position1, stack.get(position2));
        stack.set(position2, temp);
    }

    //-----------------------------------------------------------------
    //  String representation of the Stack of Cards
    //-----------------------------------------------------------------
    public String toString()
    {
        String result = "";
        for(int i=0; i < size; i++)
        {
            result+="["+stack.get(i).toString()+"] ";
        }
        return result;
    }

}
