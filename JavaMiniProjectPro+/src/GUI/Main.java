package GUI;

import org.eclipse.swt.widgets.Shell;

import Utilities.SwtShell;

public class Main {
	
	public static int shellWidth;
	public static int shellHeight;
	
	public static void main(String[] args) {
		Shell shell = SwtShell.getShell();
		shell.setText("JavaMiniProject Pro+");
		shell.setSize(shellWidth,shellHeight);
		UI.addMenuToShell(shell);
		UI.addTextToShell(shell);
		SwtShell.openShell(shell);
	}
}
