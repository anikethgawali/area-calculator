package PreProcessing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.MessageBox;

import Utilities.SwtShell;

public class Validator extends Preprocessor{
	
	private static int totalItems;
	private static int columnIndex;
	private static int rowIndex;
	
	public Validator() {
		super();
		totalItems = ipTableItems.length;
	}
	
	public static boolean isInteger() {
		
		columnIndex=1;
		try {
			for(rowIndex=0;rowIndex<totalItems;rowIndex++) {
				if(ipTableItems[rowIndex].getText(0)!="") {
					@SuppressWarnings("unused")
					int i1 = Integer.parseInt(ipTableItems[rowIndex].getText(columnIndex));
					@SuppressWarnings("unused")
					int i2 = Integer.parseInt(ipTableItems[rowIndex].getText(columnIndex+1));
				}
			}
		}catch(NumberFormatException e) {
				MessageBox warningMsgBox = new MessageBox(SwtShell.getShell(),SWT.ICON_INFORMATION);
				warningMsgBox.setMessage("Coordinates entered are not integers. Please enter integer at row "+(rowIndex+1));
				warningMsgBox.setText("Error: ");
				warningMsgBox.open();
				return false;
		}catch(NullPointerException e) {
				MessageBox warningMsgBox = new MessageBox(SwtShell.getShell(),SWT.ICON_INFORMATION);
				warningMsgBox.setMessage("Some coordinates entered are null. Please specify coordinates.");
				warningMsgBox.setText("Error: ");
				warningMsgBox.open();
				return false;
		}
		return true;
	}
	
	public static boolean isEmpty() {
		String str = "";
		columnIndex=0;
		for(rowIndex=0;rowIndex<totalItems;rowIndex++) {
			str += ipTableItems[rowIndex].getText(columnIndex);
			str += ipTableItems[rowIndex].getText(columnIndex+1);
			str += ipTableItems[rowIndex].getText(columnIndex+2);
		}
		//System.out.println(str.length());
		
		try {
			if (str.isBlank()) throw new Exception();
		}catch(Exception e) {
				MessageBox warningMsgBox = new MessageBox(SwtShell.getShell(),SWT.ICON_INFORMATION);
				warningMsgBox.setMessage("Table is Empty. Please enter coordinates");
				warningMsgBox.setText("Error: ");
				warningMsgBox.open();
				return true;		
		}
		return false;
	}
	
	public static boolean isLabled() {
		
		columnIndex=1;
		try {
			for(rowIndex=0;rowIndex<totalItems;rowIndex++) {
				if(ipTableItems[rowIndex].getText(0).isBlank() && (!ipTableItems[rowIndex].getText(1).isBlank() || !ipTableItems[rowIndex].getText(2).isBlank())) 
					throw new Exception();
			}
		}catch (Exception e) {
			MessageBox warningMsgBox = new MessageBox(SwtShell.getShell(),SWT.ICON_INFORMATION);
			warningMsgBox.setMessage("Please lable point at Row Number "+(rowIndex+1));
			warningMsgBox.setText("Error: ");
			warningMsgBox.open();
			return false;
		}
		return true;
		
	}
	
	
	public boolean isValid() {
		
		if(!isEmpty() && isInteger() && isLabled() )
			return true;
		else 
			return false;
	}
	
}
