package Functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.MessageBox;

import PreProcessing.Validator;
import PreProcessing.ipData;
import Utilities.IO;
import Utilities.SwtShell;

public class RemoveDuplicates implements SelectionListener {
	
	private static List<Point> localCoordinateList;
	private static List<String> pointNames;

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
		
		if(!doesContainDuplicates(data)) {
			MessageBox warningMsgBox = new MessageBox(SwtShell.getShell(),SWT.ICON_INFORMATION);
			warningMsgBox.setMessage("No Duplicate points found.");
			warningMsgBox.setText("Message: ");
			warningMsgBox.open();
			return;
		}
		String[] strArray = new String[data.getTotalInputPoints()*3];
		//System.out.println("rd.tableItems.length"+tableItems.length);
		
		//convert into string[] 
		int j=0;
		for (int i=0;i<localCoordinateList.size();i++) {
			if(pointNames.get(i) != null && localCoordinateList.get(i) != null) {
			strArray[j] = pointNames.get(i);
			j++;
			strArray[j] = Integer.toString(localCoordinateList.get(i).x);
			j++;
			strArray[j] = Integer.toString(localCoordinateList.get(i).y);
			if(j<strArray.length)
				j++;
			}
		}
		
		//System.out.println("RD: getTotalInputpoints: "+ data.getTotalInputPoints());
		//System.out.println("RD: localCoordinateList.size: " + localCoordinateList.size());
		
		MessageBox warningMsgBox = new MessageBox(SwtShell.getShell(),SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);
		warningMsgBox.setMessage( data.getTotalInputPoints()-localCoordinateList.size() +" duplicate points will be removed from table. Do you want to proceed?");
		warningMsgBox.setText("Message: ");
		int result = warningMsgBox.open();
		
		if(result == SWT.OK) {
			//re-write table
			IO.writeTable(strArray);
		}
		
	}

	public static boolean doesContainDuplicates(ipData data) {
		
		System.out.println("inside doesContainDuplicates() method.");
		pointNames = new ArrayList<String>();
		localCoordinateList = new ArrayList<Point>();
		String str;
		for (int i=0;i<data.getIpPointArray().length;i++) {
			str=data.getIpTableItems()[i].getText(0);
			if (!localCoordinateList.contains(data.getIpPointArray()[i])) {
				localCoordinateList.add(data.getIpPointArray()[i]);
				pointNames.add(str);
			}
		}
		localCoordinateList.removeAll(Collections.singleton(null));
		pointNames.removeAll(Collections.singleton(""));
		
		//System.out.println("RD: localCoordinateList"+ localCoordinateList);
		//System.out.println("RD: getIpPointList" + data.getIpPointList());
		//System.out.println("RD: localCoordinateList.size: "+ localCoordinateList.size());
		//System.out.println("RD: getIpPointList.size(): " + data.getIpPointList().size());
		
		if (localCoordinateList.size()==data.getIpPointList().size())
			return false;
		else
			return true;
	}


}