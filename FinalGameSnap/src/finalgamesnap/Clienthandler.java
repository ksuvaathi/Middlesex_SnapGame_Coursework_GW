
package finalgamesnap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author suvaa
 */
public class Clienthandler extends Thread {

    final Socket s;
    final ObjectOutputStream out;
    final ObjectInputStream in;
    int p = SnapLogic.ppoints;

    Clienthandler(Socket s, ObjectOutputStream out, ObjectInputStream in,int p) {
        this.s = s;
        this.out = out;
        this.in = in;
        this.p= p;
    }

   
    public void run() {
        try {
            if (in.readObject().equals("snap")) {
                Boolean sp = SnapLogic.Snap(Server.topcard, Server.prevcard);
                if (sp == true) {  
                    out.writeObject("Congrats");              
                } else {
                    out.writeObject("Unlucky");
                } 
                out.writeObject("points" + p);
            }
        } catch (IOException ex) {
            Logger.getLogger(Clienthandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Clienthandler.class.getName()).log(Level.SEVERE, null, ex);
      
}

    }

   

  

}

