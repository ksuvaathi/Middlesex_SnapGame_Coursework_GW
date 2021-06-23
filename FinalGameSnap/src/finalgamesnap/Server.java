package finalgamesnap;

import static finalgamesnap.Server.t1;
import static finalgamesnap.Server.t2;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class Server {

    static ObjectInputStream in1;
    static ObjectInputStream in2;
    static ObjectOutputStream out1;
    static ObjectOutputStream out2;
    
    static String topcard;
    static String prevcard;
    static Timer timer;

    static Thread t1;
    static Thread t2;

    public static void main(String[] args) throws IOException, InterruptedException {
    
    Socket s1 = null;
    Socket s2 = null;
        ServerSocket ss = new ServerSocket(1000);

        try {

            s1 = ss.accept();
            System.out.println("Player 1 connected : " + s1);
             
            
          

            out1 = new ObjectOutputStream(s1.getOutputStream());
            System.out.println("Checking server 1");
            in1 = new ObjectInputStream(s1.getInputStream());
            System.out.println("Checking server 2");
           
           
            s2 = ss.accept();
             
              
               out2 = new ObjectOutputStream(s2.getOutputStream());
               System.out.println("Checking server 3");
               in2 = new ObjectInputStream(s2.getInputStream());
            System.out.println("Player 2 connected : " + s2);

        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
        System.out.println("Connected to clients!!");

        timer = new Timer();

        //creates a score for player 1 and 2
        int pp1 = SnapLogic.ppoints;
        int pp2 = SnapLogic.ppoints;

        ArrayList<String> arr = new ArrayList<String>();
        
        t1= new Clienthandler(s1, out1, in1,pp1);
        t2 = new Clienthandler(s2, out2, in2,pp2);

        String card1 = SnapLogic.move();
        arr.add(card1);
        topcard = card1;
        out1.writeObject(topcard);
        out2.writeObject(topcard);

        while (topcard != null) {

            String c = SnapLogic.move();
            arr.add(c);
            prevcard = arr.get(0);
            arr.remove(0);
            topcard = arr.get(0);
            out1.writeObject(topcard);
            out2.writeObject(topcard);

            out1.writeObject(prevcard);
            out2.writeObject(prevcard);
            TimeUnit.SECONDS.sleep(5);
             

            
        }
            
        System.out.println("End of Game");
        out1.writeObject("End of Game");
        out2.writeObject("End of Game");
        
        int p1 = in1.readInt();
        int p2 = in2.readInt();

        String winner = SnapLogic.getWinner(p1, p2);
        out1.writeObject(winner);
        out2.writeObject(winner);
        
        

        

    
}

    private static void snapcheck() {
        SnapTimer t =new SnapTimer(5);
             System.out.println("Task scheduled.");

               
    }
    

}

