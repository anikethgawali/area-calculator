package Shapes;

public class Test {
	
	private static String name;
	
	public static void runTestForShapes(int count) {
		
		if (count==1) {
			name = "Point";
			
		}else if (count==2) {
			name = "Line";
			
		}else if (count==3) {
			Triangle t = new Triangle();
			t.checkShape();
			name = t.getName();
			
		}else if (count==4) {
			Quadrilateral q = new Quadrilateral();
			q.checkShape();
			name = q.getName();
		}else
			name = "Not yet defined";
	}
	
	public static String getName() {
		return name;
	}

}
