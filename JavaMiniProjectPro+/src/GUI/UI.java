package GUI;



import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import Functions.Browse;
import Functions.Calculate;
import Functions.Close;
import Functions.RemoveDuplicates;
import Functions.View;

public class UI {
	
	       
	private static Text fileLocationText;
	private static Text areaValueText;
	private static Text geometryNameText;
	
	private static Table table;
	public static int maxRowsInTable=10;
	
	private static Canvas diagram;
	public static Canvas showView;
	
	public static void addMenuToShell(Shell shell) {
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		
	//File menu
			MenuItem fileMenuItem = new MenuItem(menu, SWT.CASCADE);
			fileMenuItem.setText("File");
			
			Menu fileMenu = new Menu(shell,SWT.DROP_DOWN);
			fileMenuItem.setMenu(fileMenu);
			
			//browse
			MenuItem browseMenuItem = new MenuItem(fileMenu,SWT.PUSH);
			browseMenuItem.setText("Browse");
			browseMenuItem.addSelectionListener(new Browse());
			
			//exit
			MenuItem exitMenuItem = new MenuItem(fileMenu,SWT.PUSH);
			exitMenuItem.setText("Exit");
			exitMenuItem.addSelectionListener(new Close());
			
			
	//Tool Menu
			MenuItem toolsMenuItem = new MenuItem(menu, SWT.CASCADE);
			toolsMenuItem.setText("Tools");
			
			Menu toolsMenu = new Menu(shell,SWT.DROP_DOWN);
			toolsMenuItem.setMenu(toolsMenu);
			
			//remove duplicates
			MenuItem removeDuplicateMenuItem = new MenuItem(toolsMenu,SWT.PUSH);
			removeDuplicateMenuItem.setText("Remove Duplicates");
			removeDuplicateMenuItem.addSelectionListener(new RemoveDuplicates());
			
			
			//calculate Geometry 
			MenuItem calculateGeometryMenuItem = new MenuItem(toolsMenu,SWT.PUSH);
			calculateGeometryMenuItem.setText("Calculate");
			calculateGeometryMenuItem.addSelectionListener(new Calculate());
			
	//View Menu
			MenuItem viewMenuItem = new MenuItem(menu, SWT.CASCADE);
			viewMenuItem.setText("View");
			
			Menu viewMenu = new Menu(shell,SWT.DROP_DOWN);
			viewMenuItem.setMenu(viewMenu);
			
			//List View
			MenuItem listViewMenuItem  =new MenuItem(viewMenu, SWT.RADIO);
			listViewMenuItem.setText("List View");
			listViewMenuItem.setSelection(true);
			//listViewMenuItem.addSelectionListener(new ListView());
			//Image image = new Image(null,"C:\\Users\\Heartsbane\\Desktop\\Games\\AOE2\\Building\\Archer range.png");
			//listViewMenuItem.setImage(image);
			
			//Tree View
			MenuItem treeViewMenuItem = new MenuItem(viewMenu,SWT.RADIO);
			treeViewMenuItem.setText("Tree View");	
			treeViewMenuItem.getSelection();
			treeViewMenuItem.addSelectionListener(new View());
	
	};
	
//~ Method +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
	
	public static void addTextToShell(Shell shell) {
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 6;
		shell.setLayout(gridLayout);
		
		//Input File Label
		new Label(shell,SWT.CENTER|SWT.BORDER).setText("Input File");
		
		//Input File text. non-editable
		fileLocationText = new Text(shell,SWT.BORDER);
		fileLocationText.setEditable(false);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 5;
		fileLocationText.setLayoutData(gridData);
				
		
		//create table
		table = new Table(shell, SWT.SINGLE|SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.getVerticalBar().isVisible();
		
		TableColumn pointNamesCol = new TableColumn(table, SWT.NONE);
		pointNamesCol.setText("Point Names");
		
		TableColumn xCoordinateCol = new TableColumn(table, SWT.NONE);
		xCoordinateCol.setText("X-Coordinate");
		
		TableColumn yCoordinateCol = new TableColumn(table,SWT.NONE);
		yCoordinateCol.setText("Y-Coordinate");
		
		for (int i = 0; i < maxRowsInTable;i++) {
			new TableItem(table, SWT.NONE);
		}
		
		pointNamesCol.pack();
		xCoordinateCol.pack();
		yCoordinateCol.pack();
		
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 3;
		
		table.setLayoutData(gridData);
		
		//make table editable
		makeTableEditable(table);
		
		/*
		//create canvas
				diagram = new Canvas(shell,SWT.BORDER);
				gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING,GridData.FILL_BOTH);
				gridData.widthHint=300;
				gridData.heightHint=300;
				gridData.horizontalSpan=3;
				gridData.verticalSpan=1;
				diagram.setLayoutData(gridData);
		*/
		
		createCanvas(shell);
		
		//Geometry lable
		new Label(shell,SWT.CENTER|SWT.BORDER).setText("Geometry");
		
		//Geometry text
		geometryNameText  = new Text(shell,SWT.BORDER);
		geometryNameText.setEditable(false);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=2;
		geometryNameText.setLayoutData(gridData);
		
		//view canvas
		showView = new Canvas(shell, SWT.NONE);
		gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING,GridData.FILL_BOTH);
		gridData.widthHint=300;
		gridData.heightHint=100;
		gridData.horizontalSpan=3;
		gridData.verticalSpan=2;
		showView.setLayoutData(gridData);
		
		//Area Label
		new Label(shell,SWT.CENTER|SWT.BORDER).setText("Area");
		
		//Area Text
		areaValueText  = new Text(shell,SWT.BORDER);
		areaValueText.setEditable(false);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=2;
		areaValueText.setLayoutData(gridData);
		
	    Button save = new Button(shell,SWT.PUSH);
	    save.setText("Save");
	    gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
	    gridData.horizontalSpan=5;
	    save.setLayoutData(gridData);
		
		Button close = new Button(shell, SWT.PUSH);
	    close.setText("Close");
	    gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
	    close.setLayoutData(gridData);  	    
	}
	
//~ Method ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
	
	private static void makeTableEditable(Table table) {
		
		TableCursor cursor = new TableCursor(table, SWT.NONE);

	    final ControlEditor editor = new ControlEditor(cursor);
	    editor.grabHorizontal = true;
	    editor.grabVertical = true;

	    cursor.addSelectionListener(new SelectionAdapter() {
	      // This is called as the user navigates around the table
	      public void widgetSelected(SelectionEvent event) {
	        // Select the row in the table where the TableCursor is
	        table.setSelection(new TableItem[] { cursor.getRow() });
	      }

	      // This is called when the user hits Enter
	      public void widgetDefaultSelected(SelectionEvent event) {
	        final Text text = new Text(cursor, SWT.NONE);
	        text.setFocus();
	        // Copy the text from the cell to the Text control
	        text.setText(cursor.getRow().getText(cursor.getColumn()));
	        text.setFocus();
	        // Add a handler to detect key presses
	        text.addKeyListener(new KeyAdapter() {
	          public void keyPressed(KeyEvent event) {
	            switch (event.keyCode) {
	            case SWT.CR:
	            case SWT.KEYPAD_CR:
	            case SWT.ARROW_DOWN:
	            case SWT.ARROW_UP:
	            	cursor.getRow().setText(cursor.getColumn(), text.getText());
	            	getAreaValueText().setText("");
	            	getGeometryNameText().setText("");
	            case SWT.ESC:
	            	text.dispose();
	            	break;
	            }
	          }
	        });
	        editor.setEditor(text);
	      }
	    });	
	}

//~ Getter - Setters ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
	
	
	//FileLocationText
	public static Text getFileLocationText() {
		return fileLocationText;
	}

	public static void setFileLocationText(Text fileLocationText) {
		UI.fileLocationText = fileLocationText;
	}
	
	//GeometryNameText
	public static Text getGeometryNameText() {
		return geometryNameText;
	}

	public static void setGeometryNameText(Text geometryNameText) {
		UI.geometryNameText = geometryNameText;
	}
	
	//AreaValueText
	public static Text getAreaValueText() {
		return areaValueText;
	}

	public static void setAreaValueText(Text areaValueText) {
		UI.areaValueText = areaValueText;
	}
	
	//Canvas for diagram
	public static Canvas getDiagram() {
		return diagram;
	}

	public static void setDiagram(Canvas diagram) {
		UI.diagram = diagram;
	}
	
	//table
	public static Table getTable() {
		return table;
	}

	public static void setTable(Table table) {
		UI.table = table;
	}
	
	public static Canvas createCanvas(Shell shell) {
		diagram = new Canvas(shell,SWT.BORDER);
		GridData gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING,GridData.FILL_BOTH);
		gridData.widthHint=300;
		gridData.heightHint=300;
		gridData.horizontalSpan=3;
		gridData.verticalSpan=1;
		diagram.setLayoutData(gridData);
		return diagram;
	}
	
	


}
