package Functions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import GUI.UI;
import PreProcessing.ipData;
import Utilities.SwtShell;

public class View implements SelectionListener {

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		
		ipData data = new ipData();
		
		Shell shell=SwtShell.getShell();
		final Tree tree = new Tree(shell,  SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		tree.setBounds(100, 10, 200, 500);
		shell.setSize(450, 650);
		
		for (int rowIndex=0;rowIndex<data.getIpTableItems().length;rowIndex++) {
			if(data.getIpTableItems()[rowIndex].getText() != "" ) {
				TreeItem treeItem0 = new TreeItem(tree, 0);
				treeItem0.setText(data.getIpTableItems()[rowIndex].getText(0));
				for (int columnIndex=1;columnIndex<UI.getTable().getColumnCount();columnIndex++) {
					TreeItem treeItem1 = new TreeItem(treeItem0, 0);
					treeItem1.setText(data.getIpTableItems()[rowIndex].getText(columnIndex));
				}
			}
		}
		
		shell.open();
		
		
	}
}

