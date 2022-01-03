package PreProcessing;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Point;

import GUI.UI;

	

public class ipData extends Preprocessor {
	
	
	public static Point[] ipPointArray;
	public static List<Point> ipPointList;
	public static String ipString;
	public static Map<Point, String> lpPair;
	
	
	public ipData() {
		super();
		
		ipUpdate();
	}
	
	
//~ Method  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
	
	public Integer getTotalInputPoints() {
		return ipPointList.size();
	}
	
	
//~ New Methods ========================================================================================================================================	
	
	private static void ipUpdateString() {
		 for (int rowIndex=0;rowIndex<ipTableItems.length;rowIndex++) {
				for (int colIndex=0;colIndex<UI.getTable().getColumnCount();colIndex++) {
					if(!ipTableItems[rowIndex].getText(0).isBlank()) {
						ipString += ipTableItems[rowIndex].getText(colIndex);
						//System.out.format("%d,/n", items[i].getText(k));
					}
				}
	     }
		 //System.out.println("Input string updated.");
	}
		
	private static void ipUpdatePointList() {
		ipPointList = null;
		ipPointList = new ArrayList<Point>();
		for (int i=0;i<ipTableItems.length;i++) {
				if(!ipTableItems[i].getText().isBlank()) {
					Point p=new Point(0, 0);
					p.x=Integer.parseInt(ipTableItems[i].getText(1));
					p.y=Integer.parseInt(ipTableItems[i].getText(2));
					ipPointList.add(p);
				}
		}
		System.out.println(ipPointList);
		//System.out.println("Point List updated.");
	}

	private static void ipUpdatePointArray() {
		ipUpdatePointList();
		ipPointArray = null;
		ipPointArray = new Point[ipPointList.size()];

		for(int j=0;j<ipPointList.size();j++) {
			ipPointArray[j] = ipPointList.get(j);
		}
		//System.out.println("Point Array Updated.");
	}
	
	private static void ipUpdateLPPair() {
		lpPair = null;
		lpPair = new HashMap<Point, String>();
		for(int i=0;i<ipPointArray.length;i++) {
			lpPair.put(ipPointArray[i], ipTableItems[i].getText(0));
		}
	}
	
	
//~ Getters +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	

	public Point[] getIpPointArray() {
		return ipPointArray;
	}

	public List<Point> getIpPointList() {
		return ipPointList;
	}

	public String getIpString() {
		return ipString;
	}
	
	
	
	public static Map<Point, String> getMap() {
		return lpPair;
	}


	public void ipUpdate() {
		ipUpdatePointArray();
		ipUpdateString();
		ipUpdateLPPair();
	}
		
	
}

