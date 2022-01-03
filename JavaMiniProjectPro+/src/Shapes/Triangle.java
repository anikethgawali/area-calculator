package Shapes;

public class Triangle extends Shapes {
	
	public Triangle() {
		super();
	}
	
	@Override
	public void checkShape() {
		
		if(isPerpendicular() && areTwoCongruent()) { 
			super.setName("Isosceles Rightangle Triangle");
		}
		else if(isPerpendicular()) {
			super.setName("Rightangle Triangle");
		}
		else if(areTwoCongruent()) {
			super.setName("Isosceles Triangle");
		}
		else if(areAllCongruent()) {
			super.setName("Equilateral Triangle");
		}
		else
			super.setName("Scalene Triangle");
	}
	
	
//~ Method ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	private boolean isPerpendicular() {
		
		boolean b = false;
		for(int i=0;i<super.angles.length;i++) {
			if(super.angles[i]==90) 
				b = true;
		}
		return b;
	}
	
//~ Method ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	private boolean areTwoCongruent() {
		boolean b =false;
		if(super.length[0]==super.length[1] || super.length[1]==super.length[2] || super.length[0]==super.length[2])
			b=true;
		return b;
	}
	
//~ Method ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	private boolean areAllCongruent() {
		boolean b = false;
		double l = super.length[0];
		if(l==super.length[1] && l==super.length[2])
			b=true;
		return b;
	}
	
	

}
