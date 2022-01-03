package Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.graphics.Point;


public class Points {
	
	
//~ Method	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public static void sort(Point[] p, Point O) {
		
		Arrays.sort(p, new Comparator<Point>() {

			@Override
			public int compare(Point a, Point b) {
				double angle1 = Math.atan2(a.y - O.y, a.x - O.x);
		        double angle2 = Math.atan2(b.y - O.y, b.x - O.x);

		        //For counter-clockwise, just reverse the signs of the return values
		        if(angle1 < angle2) return 1;
		        else if (angle2 < angle1) return -1;
		        else return 0;
			}
		});
	}

	
//~ Method  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	public static List<Point> removeCollinear(Point[] p) {
		
		boolean[] del;
		Point P,Q,R;
			del = new boolean[p.length];
			for(int i=0;i<p.length;i++) {
				del[i]=false;
			}

			int j = p.length - 1;
			P = p[j-1];
			Q = p[j];

			for (int i=0;i<p.length;i++) {
				R = p[i];
				double ax = Q.x - P.x;
				double ay = Q.y - P.y;
				double bx = R.x - Q.x;
				double by = R.y - Q.y;

				if (ax*by - ay*bx == 0 && ax*bx + ay*by >0 ) 
					del[j] = true;

				P = Q;
				Q = R;
				j = i;
			}

			List<Point> finalPointsList = new ArrayList<Point>();

			j = 0;
			for (int i=0;i<p.length;i++) {
				if(!del[i]) {
					finalPointsList.add(p[i]);
					j++;
				}
			}
			return finalPointsList;
	}
	
//~ Method  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	

}
