package Utilities;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SwtShell {
	
	private static Display display = new Display();
	
	public static Shell getShell() {
		Shell shell = new Shell(display,SWT.SHELL_TRIM);
		return shell;
	}
	
	public static void openShell(Shell shell) {
		shell.pack();
		shell.open();
		
		//this loop keeps the shell open constantly listening for events
		
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	public static Display getDisplay() {
		return display;
	}

}
