import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.opencv.core.Point;

import com.codeminders.ardrone.ARDrone;
import com.codeminders.ardrone.ARDrone.VideoChannel;

public class MyDrone
{

    private static final long CONNECT_TIMEOUT = 3000;
    private static final VideoPanel video = new VideoPanel();
    private static javax.swing.JPanel videoPanel;
    
    // variables for move function with new coordinates
    // private static final int drone_Max_Ang = 100;
    // private static final int drone_Max_Spd = 5;
    
    // special move function to update coordinates
    // alternative to this function could be to manually update x,y,z and dir
    /*private void coordmove(float lr,float fb,float vert,float ang,ARDrone drone,double time, double x, double y, double z, double dir) {
    	 try {
    		double time2 = System.currentTimeMillis() + time;
	    	while ( System.currentTimeMillis()  < time2 ) {
				drone.move(lr,fb,vert,ang);
				Thread.sleep(10);
	   		}
	        
	   		x = x + Math.sin(Math.toRadians(ang*drone_Max_Ang))*fb*drone_Max_Spd*time+Math.cos(Math.toRadians(ang*drone_Max_Ang))*lr*drone_Max_Spd*time;
	   		y = y + Math.cos(Math.toRadians(ang*drone_Max_Ang))*fb*drone_Max_Spd*time+Math.sin(Math.toRadians(ang*drone_Max_Ang))*lr*drone_Max_Spd*time;
	   		z = z + vert*drone_Max_Spd*time;
    	 } catch(Throwable e)
         {
             e.printStackTrace();
         }
    }
    */
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ARDrone drone;
        try
        {
            // Create ARDrone object,
            // connect to drone and initialize it.
            drone = new ARDrone();
            drone.connect();
            drone.clearEmergencySignal();

            // Wait until drone is ready
            drone.waitForReady(CONNECT_TIMEOUT);
            Thread.sleep(2000);
            
            // do TRIM operation
            drone.trim();
            
            // select bottom camera
            drone.selectVideoChannel(VideoChannel.VERTICAL_ONLY);
            
            video.setDrone(drone);
            
            // display video from drone
            videoPanel = new javax.swing.JPanel();
            videoPanel.setBackground(new java.awt.Color(102, 102, 102));
            videoPanel.setPreferredSize(new java.awt.Dimension(320, 240));
            videoPanel.setLayout(new javax.swing.BoxLayout(videoPanel, javax.swing.BoxLayout.LINE_AXIS));
            videoPanel.add(video);
            
            JFrame frame = new JFrame("FrameDemo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(videoPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
            
            
            // set 0 coordinates
            // double x,y,z,dir = 0
            
           // move(lr,fb,vert,ang,drone)
            drone.takeOff();
            Thread.sleep(5000);
            
            Thread.sleep(500);
            drone.land();
            
            // sleep for 3 seconds
            Thread.sleep(3000);
            
            // Disconnect from the done
            drone.disconnect();
            
        } catch(Throwable e)
        {
            e.printStackTrace();
        }
    }
}
