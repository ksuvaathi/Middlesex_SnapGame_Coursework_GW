package finalgamesnap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SnapLogic {

    // Timer
    static Timer timer = new Timer();

    // Time tracking 
    static boolean timeRunning = false;// to check if timer is running
    static int time = 0;// keep track of seconds elapsed
    static String[] deckarray = Carddeck.deck();

    // Card Deck
    final static ArrayList<String> middledeck = new ArrayList<String>();
    final static List<String> cardstack = new ArrayList<String>(Arrays.asList(deckarray));

    final static ArrayList<Integer> playerspoints = new ArrayList<Integer>();

    static int ppoints = 0; //points that need to incremented
    static String deckcard;

    public static String move() {
        String card = null;

        Iterator<String> i = cardstack.iterator();
        if (i.hasNext()) {
            card = i.next();
            i.remove();
            return card;

        } else {
            return card;
        }
        // return card;
    }

    public static String prevcard() {
        if (middledeck != null) {
            String prevcard = middledeck.get(0);
            return prevcard;
        } else {
            return null;
        }
    }


    public static boolean Snap(String topcard, String previous) {

        previous = prevcard();

        topcard = middledeck.get(0);
        if (topcard.equals(previous)) {
            ppoints++;
            return true;
        } else {
            ppoints--;
            return false;
        }

    }


    // get winner of more than one player
    public static String getWinner(int p1,int p2) {
        String winner;
        if(p1>p2){
            winner= "Player 1 is the winner";
            return winner;
        }else{
            winner ="Player 2 is the winner";
            return winner;
        }
    }

}
