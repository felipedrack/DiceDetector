
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class HolaMundo {
public static void main(String[] args){
		
		Mat src=Imgcodecs.imread("C:\\Users\\Sala306\\Desktop\\img\\d3.png");
		Mat gray=new Mat();
		Mat small=new Mat();
		
	    //src1.convertTo(src, -1, 1, -50); //decrease the brightness by 100
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		//Imgproc.morphologyEx(gray,gray, Imgproc.MORPH_OPEN, new Mat());

		Imgproc.GaussianBlur(gray, gray, new Size(5,5), 0);
		
		Imgproc.threshold(gray, gray, 150, 255, Imgproc.THRESH_OTSU);
		int erosion_size = 5;
        int dilation_size = 5;
        
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(2*erosion_size + 1, 2*erosion_size+1));
        Imgproc.erode(gray, gray, element);

        Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(2*dilation_size + 1, 2*dilation_size+1));
        Imgproc.dilate(gray, gray, element1);
        //Imgproc.Canny(gray, gray, 2, 2*2,3,false);
		
        List<MatOfPoint> contours = new ArrayList<>();
        List<MatOfPoint> contours1 = new ArrayList<>();
        Mat hierarchy = new Mat();

        // find contours
        Imgproc.findContours(gray, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
        
        int count=0;
        // if any contour exist...
        if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
        {
        	
                // for each contour, display it in blue
                for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
                {
                       Imgproc.drawContours(src, contours, idx, new Scalar(0, 255, 0));
                       count++;
                }
        }
        
        
        
        System.out.println("dados "+count);

		//Imgproc.findContours(gray,contours,new Mat(),Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_SIMPLE);

		Size sz = new Size(600,600);
		Imgproc.resize( src, small, sz );

		//HighGui.namedWindow("test", HighGui.WINDOW_AUTOSIZE);
		//HighGui.imshow("test", src);
		HighGui.imshow("test2", small);
		HighGui.waitKey();
		
 }
		
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
}
