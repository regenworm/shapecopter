import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.highgui.*;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import org.opencv.imgproc.Imgproc;



public class MotionTracking {
	
	public BufferedImage toHSV(BufferedImage image) {
		// convert buffered image to byte array
		byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		//Mat mat = new Mat();
        //mat.put(0, 0, pixels);
		
		/*Graphics g = image.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();*/
		
        // to hsv
        Mat mat = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC3);
        mat.put(0, 0, pixels);

        Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC3);
        Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2HSV);

        byte[] data1 = new byte[mat1.rows()*mat1.cols()*(int)(mat1.elemSize())];
        mat1.get(0, 0, data1);
        BufferedImage image1 = new BufferedImage(mat1.cols(), mat1.rows(), 5);
        image1.getRaster().setDataElements(0,0,mat1.cols(),mat1.rows(),data1);
        
        // convert mat to bufferedImage
        //BufferedImage out;
        //out = mat2Img(mat);
        
	      
		return image1;
	}
	
	public static BufferedImage mat2Img(Mat in)
    {
        BufferedImage out;
        byte[] data = new byte[320 * 240 * (int)in.elemSize()];
        int type;
        in.get(0, 0, data);

        if(in.channels() == 1)
            type = BufferedImage.TYPE_BYTE_GRAY;
        else
            type = BufferedImage.TYPE_3BYTE_BGR;

        out = new BufferedImage(320, 240, type);

        out.getRaster().setDataElements(0, 0, 320, 240, data);
        return out;
    } 


}
