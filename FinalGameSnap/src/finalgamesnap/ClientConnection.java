/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgamesnap;


import static finalgamesnap.ClientGUI.cd1;
import static finalgamesnap.ClientGUI.cd2;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class ClientConnection extends Thread {

    protected static Socket s;
    protected static ObjectInputStream in;
    protected static ObjectOutputStream out;
    static Object card1;
    static Object card2;
    String answer;
    static Timer timer = new Timer();

    static String topcard = null;
    static String prevcard=null;


    public ClientConnection(Socket s) {
        this.s = s;
        //this.in = in;
        //this.out = out;

        

    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(this.s.getInputStream());
            System.out.println("Checking client 1");
             out = new ObjectOutputStream(this.s.getOutputStream());
            System.out.println("Checking client 2");
            
        } catch (IOException ex) {
           System.out.println("Error: " + ex);
        }
            
        playgame();
    }

    public static void playgame(){

        try {
            
            topcard = (String) in.readObject();
            //cd2.appendText(topcard);
            System.out.println("fiirst topcard: "+topcard);
System.out.println("Error 1");
            while (topcard != null) {
                topcard = (String) in.readObject();
                prevcard = (String) in.readObject();

             // cd1.appendText(prevcard);
               //cd2.appendText(topcard);
                System.out.println("topcard: " + topcard);
               // System.out.println("Error 2");
                System.out.println("prevcard: " + prevcard);
                //System.out.println("Error 3");
                //
                //
                //String timeup = (String) in.readObject();
                //System.out.println("time up"+timeup);
                //System.out.println("Error 4");
            }
            String end= (String) in.readObject();
            System.out.println(end);
            System.out.println("Error 5");
            
            String winner = (String) in.readObject();
            System.out.println("Winner: "+ winner);

            
        } catch (IOException e) {
            System.out.println("Exception error: " + e);
        } catch (ClassNotFoundException ex) {
            System.out.println("Exception error: " + ex);
        }
    }
    
    
        public static void Snapped() throws ClassNotFoundException{
            try{
          //get info from the server to check it was a match
          String match= (String) in.readObject();
          System.out.println(match);
          
          //get points
          if(match.equals("Congrats")){
              int mypoints = in.readInt();
              System.out.println(mypoints);
              
          }else if(match.equals("Unlucky")){
              int mypoints = in.readInt();
          }else if (match.equals("Other player snapped")){
              int otherplayers= in.readInt();
          }
          
            }catch(IOException ex){
                System.out.println("Error: "+ex);
            } 
        }

 

    public static void close() {
        try {
            
            in.close();
            out.close();
            s.close();
            System.out.println("Player and exited the game");
            
        } catch (IOException e) {
            e.getMessage();

        }

    }

}
