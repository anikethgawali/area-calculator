package Functions;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.MessageBox;

import GUI.UI;
import PreProcessing.Validator;
import PreProcessing.ipData;
import Shapes.Test;
import Utilities.Geometry;
import Utilities.PointPlotter;
import Utilities.Points;
import Utilities.SwtShell;

public class Calculate implements SelectionListener {
	
	/*
	 * @param Point O represents coordinate of the input point array 
	 * 
	 * @param Point[] coordinate is the array of the input points
	 * 
	 * @param Point[] finalCoordinate is the sorted array of the coordinates after removing collinear points.
	 * 
	 */
	long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
	long startTime = System.nanoTime();
	
	public static Point[] coordinate;
	public static Point[] finalCoordinate;

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		
		Validator validator = new Validator();
		if(!validator.isValid())
			return;
		
		ipData data = new ipData();
		
		if(RemoveDuplicates.doesContainDuplicates(data)) {
			MessageBox warningMsgBox = new MessageBox(SwtShell.getShell(),SWT.ICON_WARNING);
			warningMsgBox.setMessage("Please remove duplicate before processing");
			warningMsgBox.setText("Error ");
			warningMsgBox.open();
			return;
		}
		
		Point O;
		coordinate = data.getIpPointArray().clone();

		
		//find centroid of the point array
		O = Geometry.findCentroid(coordinate);
		
		//sort the points in clockwise manner
		Points.sort(coordinate, O);

		//remove collinear points from point array. 
		if(coordinate.length>2) {
			List<Point> finalCoordinateList = Points.removeCollinear(coordinate);
			// initialize point array.
			finalCoordinate = new Point[finalCoordinateList.size()];
			
			//insert the list in final points' array
			for (int i=0;i<finalCoordinate.length;i++) {
				finalCoordinate[i]=finalCoordinateList.get(i);
				//System.out.println("finalPoints["+i+"] : "+finalCoordinate[i]);
			}
		}else
			finalCoordinate = coordinate;
			
		double area = Geometry.findArea(finalCoordinate);
		UI.getAreaValueText().setText(Double.toString(area));
		
		Test.runTestForShapes(finalCoordinate.length);
		UI.getGeometryNameText().setText(Test.getName());
		
		//UI.diagram.update();
		//System.out.println("Point plotter is called from Calculate");
		PointPlotter.drawCanvas();
		
		long endTime = System.nanoTime();
		long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		
		long duration = (endTime - startTime)/1000000;
		long actualMemUsed=afterUsedMem-beforeUsedMem;
		
		System.out.println("Duration to run Calculate(milliseconds): " + duration);
		System.out.println("Memory used by program: " + actualMemUsed);
		
	}
	
}
