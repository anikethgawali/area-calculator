package Functions;

import java.io.File;

import javax.swing.JFileChooser;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import PreProcessing.Validator;
import PreProcessing.ipData;
import Utilities.IO;

public class Save implements SelectionListener {

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
		
		int result = IO.getJFileChooser("Save");
		if(result==JFileChooser.APPROVE_OPTION) {
			File destinationFile = IO.getSelectedFile();
			IO.exportToTextFile(destinationFile,data.getIpTableItems());
		}
	}
}
