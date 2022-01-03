package Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import GUI.UI;

public class IO {
	
	private static JFileChooser fileChooser;
	
	
//~	Method ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public static int getJFileChooser(String str) {
		
		int result;
		String defaultPath = "H:\\\\Siemens\\\\Eclipse Workspace\\\\Test points";
		
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(defaultPath));

		//choose only .txt files. No other types allowed.
		
		fileChooser.setAcceptAllFileFilterUsed(false); 
		FileNameExtensionFilter restrict = new FileNameExtensionFilter(".txt", "txt"); 
        fileChooser.addChoosableFileFilter(restrict); 
        
        //open the dialog box.
        if (str == "Open")
        	result = fileChooser.showOpenDialog(fileChooser);
        else if (str == "Save")
        	result = fileChooser.showSaveDialog(fileChooser);
        else
        	result = JFileChooser.ERROR_OPTION;
        
        return result;
		
	}
	
	
//~ Method ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
	
	public static File getSelectedFile() {
		File sourceFile = fileChooser.getSelectedFile();
		return sourceFile;
	}
	
	
//~ Method ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public static String[] importTextToStringArray(File sourceFile) {
		
		// create token1
	    String token1 = "";

	    // create Scanner inFile1
	    Scanner inFile1 = null;
		try {
			inFile1 = new Scanner(new File(sourceFile.getAbsolutePath()));
			inFile1.useDelimiter(",\\s*");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	    List<String> temps = new ArrayList<String>();

	    // while loop
	    while (inFile1.hasNext()) {
	      token1 = inFile1.next();
	      temps.add(token1);
	    }
	    inFile1.close();
	    
	    String[] tempsArray = temps.toArray(new String[0]);
	    //System.out.println(tempsArray.length);
	    //System.out.println(UI.table.getColumnCount()); 
	    return tempsArray;
	}
	

//~ Method +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


	public static void exportToTextFile(File destinationFile, TableItem[] items) {
		
		PrintWriter exportToText = null;
		try {
			exportToText = new PrintWriter(destinationFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String string = "";
		
        for (int i=0;i<items.length;i++) {
			for (int k=0;k<3;k++) {
				if(items[i].getText(k) != "" ) {
					string += items[i].getText(k)+", ";
					//System.out.format("%d,/n", items[i].getText(k));
				}
			}
        }
		//System.out.println("SaveOnClick.String: "+string);
		exportToText.write(string);
		exportToText.close();
		
	}
	
//~ Method +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
public static void writeTable(String[] coordinateString) {
		
		UI.getTable().removeAll();
	    for (int i=0;i<coordinateString.length;i+=UI.getTable().getColumnCount()) {
	    	if(coordinateString[i]!=null) {
	    	TableItem item =new TableItem(UI.getTable(), SWT.NONE);
			item.setText(new String[] {coordinateString[i],coordinateString[i+1],coordinateString[i+2]});
			//System.out.println(coordinateString[i]+","+coordinateString[i+1]+","+coordinateString[i+2]);
	    	}
	    }
	    
	    //to add editable empty rows. Max total rows 10. (30 TableTtems)
	    //to extend max capacity of rows, change limit of i. newLimit = maxLimit * numberOfColumns
	    
	    for (int i=0;i<(30-coordinateString.length);i++) {
	    	new TableItem(UI.getTable(), SWT.NONE);
	    }
	}
}

