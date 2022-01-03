package Functions;

import java.io.File;

import javax.swing.JFileChooser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.MessageBox;

import GUI.UI;
import Utilities.IO;
import Utilities.SwtShell;

public class Browse implements SelectionListener {

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void widgetSelected(SelectionEvent arg0) {
		
		int result = IO.getJFileChooser("Open");
		
		if (result==JFileChooser.APPROVE_OPTION) {
			File sourceFile = IO.getSelectedFile();			
		    UI.getFileLocationText().setText(sourceFile.getAbsolutePath());
		    String[] str =IO.importTextToStringArray(sourceFile);
		    IO.writeTable(str);
		}
		
		// a warning message is shown to the user if operation is not completed.
		else if (result==JFileChooser.CANCEL_OPTION) {
			MessageBox warningMsgBox = new MessageBox(SwtShell.getShell(),SWT.ICON_INFORMATION);
			warningMsgBox.setMessage("No file selected. Points will not be entered in table.");
			warningMsgBox.setText("File Not Selected");
			warningMsgBox.open();
		}	
	}
}
