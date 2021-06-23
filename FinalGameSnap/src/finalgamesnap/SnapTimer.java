/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgamesnap;

import static finalgamesnap.Server.out1;
import static finalgamesnap.Server.out2;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SnapTimer {

    Timer timer;

    public SnapTimer(int seconds) {
        timer = new Timer();
        timer.schedule(new SnapTimerTask(), seconds * 1000);
    }

    class SnapTimerTask extends TimerTask {

        public void run() {
            try {
                out1.writeObject("Time's up");
                out2.writeObject("Time's up");
                System.out.println("Time's up!");
                //Server.t1.interrupt();
                //Server.t2.interrupt();
                timer.cancel();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }

    
}
}
