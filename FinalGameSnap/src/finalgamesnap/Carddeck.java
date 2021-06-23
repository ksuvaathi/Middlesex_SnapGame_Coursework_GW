
package finalgamesnap;


import java.util.ArrayList;

public class Carddeck {
    final static String[] deck = null;
    final static ArrayList<String> p1cards =null; 
    final static ArrayList<String> p2cards =null; 
  public static String[] deck() {
        String[] SUITS = {
            "Clubs", "Diamonds", "Hearts", "Spades"
        };

        String[] order = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"
        };

        // initialize deck
        int n = SUITS.length * order.length;
        String[] deck = new String[n];
        for (int i = 0; i < order.length; i++) {
            for (int j = 0; j < SUITS.length; j++) {
                deck[SUITS.length*i + j] = order[i] + " of " + SUITS[j];
            }
        }

        // shuffle
        for (int i = 0; i < n; i++) {
            int r = i + (int) (Math.random() * (n-i));
            String temp = deck[r];
            deck[r] = deck[i];
            deck[i] = temp;
        }
        
        return deck;

    }
}

