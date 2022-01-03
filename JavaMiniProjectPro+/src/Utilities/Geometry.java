package Utilities;

import org.eclipse.swt.graphics.Point;

public class Geometry {
	
	//~ Method - find the centroid given the array of points.
	
	public static Point findCentroid(Point[] p) {
		
		int x = 0;
		int y = 0;
		for (Point p1 : p) {
			x += p1.x;
			y += p1.y;
		}
		Point center = new Point(0, 0);
		center.x = x / p.length;
		center.y = y / p.length;
		return center;
	}
	
	//~ Method - calculate area given the sorted array of points. Points must not include duplicates.
	
	public static double findArea(Point[] p) {
		
		double area = 0;
		int j = p.length - 1;
		for (int i = 0; i < p.length; i++) 
		{ 
			area += (p[j].x + p[i].x) * (p[j].y - p[i].y); 
			j=i;
		}
		area = Math.abs(area/2);
		return area;
	}
	
	public static double[] findSlopes(Point[] p) {
		double[] m = new double[p.length];
		int k =0;
		for (int i=0;i<p.length;i++) {
			int j=i+1;
			m[k] = (p[j].y-p[i].y)/(p[j].x-p[i].x);
			//System.out.println("Slope.m["+k+"]"+m[k]);
			k++;
		}
		return m;
	}
	
	
	public static double[] findLengths(Point[] p) {
		
		double[] l = new double[p.length];
		int k=0;
		for(int i=0;i<p.length-1;i++) {
			int j=i+1;
			double a = Math.pow((p[j].x-p[i].x),2);
			double b = Math.pow((p[j].y-p[i].y),2);
			l[k]=Math.sqrt( a + b );
			k++;
		}
		double c =Math.pow(p[0].x-p[p.length-1].x,2);
		double d =Math.pow(p[0].y-p[p.length-1].y,2);
		l[p.length-1] = Math.sqrt(c+d);
		
		return l;
	}
	
	public static double[] findAngles(Point[] p) {
		double[] a = new double[p.length];
		//Point[] f = finalPoints.clone();
		int j = p.length - 1;
		Point Q = p[j];
		Point R = p[j-1];
		
		for (int i=0;i<p.length;i++) {
			Point P = p[i];
			double phi1 =  Math.atan2(P.y-Q.y,P.x-Q.x);
			double phi2 =  Math.atan2(Q.y-R.y,Q.x-R.x);
			//System.out.println(phi1+","+phi2);
			//System.out.println("j: "+j+" P: "+P+" Q: "+Q+" R: "+R);
			//m[j]= Math.abs((180-(Math.abs(Math.toDegrees(phi2-phi1)))));
			a[j]=Math.toDegrees(phi2-phi1);
			if (a[j]<0) a[j] += 360;
			a[j] = 180-a[j];
			
			j=i;
			Point temp = Q;
			Q=P;
			R=temp;
			
		}
		
		return a;
	}

}
