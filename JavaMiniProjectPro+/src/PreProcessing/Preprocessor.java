package PreProcessing;

import org.eclipse.swt.widgets.TableItem;

import GUI.UI;

abstract class Preprocessor {
	
	protected static TableItem[] ipTableItems;
	
	public Preprocessor() {
		ipTableItems = UI.getTable().getItems();
	}
	
	protected static void ipUpdateTableItem() {
		ipTableItems = UI.getTable().getItems();
		//System.out.println("Table Items updated.");
	}
	
	public TableItem[] getIpTableItems() {
		return ipTableItems;
	}
	

}
