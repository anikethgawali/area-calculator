package Shapes;

public class Quadrilateral extends Shapes {

	public Quadrilateral() {
		super();
	}

	@Override
	public void checkShape() {
		double[] a = super.angles.clone();
		double[] l = super.length.clone();
		
		if(( a[0]==a[2] && a[1]==a[3] && a[0]==a[1] ) && ( l[0] == l[2] && l[1] == l[3] && l[0]==l[1])) {
			super.setName("Sqaure");
		}
		else if(( a[0]==a[2] && a[1]==a[3] && a[0]==a[1] ) && ( l[0] == l[2] && l[1] == l[3] )) {
			super.setName("Rectangle");
		}
		else if((a[0] == a[2] && a[1] == a[3] ) && ( l[0] == l[2] && l[1] == l[3] && l[0]==l[1])){
			super.setName("Rhombus");
		}
		else if((a[0] == a[2] && a[1] == a[3] ) && (  l[0] == l[2] && l[1] == l[3] && l[0]!=l[1])) {
			super.setName("Parallelogram");
		}
		else super.setName("Undefined Quadrilateral");
		
	}


}
