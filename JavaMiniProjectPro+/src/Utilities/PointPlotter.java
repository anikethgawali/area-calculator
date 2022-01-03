package Utilities;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;

import Functions.Calculate;
import GUI.UI;
import PreProcessing.ipData;

public class PointPlotter {
	
	private static int count=0;
	private static Point[] p;
	private static String[] str;
	private static PaintListener pl;
	
  public static void drawCanvas() {
	  
	UI.getDiagram().dispose();
	p = Calculate.coordinate.clone();
    count = p.length*2;
    //System.out.println("count: "+count);
    //System.out.println("PointPlotter: inside drawCanvas() method.");
    
    UI.getDiagram().addPaintListener(pl = new PaintListener() {
      public void paintControl(PaintEvent e) {
    	  
    	 //System.out.println("PointPlotter: inside paint control");
        //canvas.isAutoScalable();
    	 
        
        // can not draw polygon using Point[]. Hence converting Point[] to int[].
        int[] drawPoints= new int[count];
        int i =0;
        for(int j=0;j<count/2;j++) {
	        	drawPoints[i]=p[j].x;
	        	//System.out.println("PointPlotter(before scaling): drawPoints["+i+"]: "+drawPoints[i]);
	        	i++;
	        	drawPoints[i]=p[j].y;
	        	//System.out.println("PointPlotter(before scaling): drawPoints["+i+"]: "+drawPoints[i]);
	        	if (i<count)
	        		i++;
        }
        
        //getting labels from HashMap
        str = new String[count/2];
        for (int j=0;j<p.length;j++) {
        	str[j]=ipData.getMap().get(p[j]);
        }
        
        
        //sort the array to get minimum and maximum points
        int sorted[] = drawPoints.clone();
        Arrays.sort(sorted);
        
        
        //if there is -ve point, make it zero so entire geometry will shift accordingly
        if (sorted[0]<0) {
        	int translate = Math.abs(sorted[0]);
        	for (int k=0;k<drawPoints.length;k++) {
        		drawPoints[k]=drawPoints[k]+translate;
        		sorted[k]=sorted[k]+translate;
        	}
        }
        
        int maxPoint = sorted[sorted.length-1];
        
        //scale the entire array to fit 85% of area.
        int maxX = UI.getDiagram().getSize().x;
        double scale = (0.85*maxX)/maxPoint;
        //System.out.println("PointPlotter: scale: "+scale);
        for (int k = 0;k<drawPoints.length;k++) {
        	drawPoints[k] = ((int) (drawPoints[k]*(scale)));
        	//System.out.println("PointPlotter(after scaling): drawPoints["+k+"]: "+drawPoints[k]);
        }
        
        
        
        // Set the line color and draw a polygon
        e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLACK));
        e.gc.drawPolygon(drawPoints);
        
        //draw labels
        i=0;
        for(int j=0;j<count;) {
	        e.gc.drawString(str[i], drawPoints[j]+10, drawPoints[j+1]+10);
	        	i++;
	        	if(j<count)
	        		j+=2;
        }
        
        //highlight vertices
        e.gc.setBackground(new Color(e.display, 50, 50, 200));
        for(int j=0;j<count;) {
        	e.gc.fillOval(drawPoints[j], drawPoints[j+1], 5, 5);
        	if(j<count)
        		j+=2;
        }
        e.gc.dispose();
      }
    });
    UI.getDiagram().redraw();
    
  }
}
