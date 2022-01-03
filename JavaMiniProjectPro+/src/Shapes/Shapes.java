package Shapes;

import Functions.Calculate;
import Utilities.Geometry;

public abstract class Shapes {
	
		private static int count;
		protected double[] angles;
		protected double[] length;
		private static String geometryName;
		
		public Shapes() {
			
			count = Calculate.finalCoordinate.length;
			
			length = new double[count];
			length = Geometry.findLengths(Calculate.finalCoordinate);
			
			angles = new double[count];
			angles = Geometry.findAngles(Calculate.finalCoordinate);
		}
		
		public String getName() {
			return geometryName;
		}
		
		public void setName(String s) {
			geometryName = s;
		}
		
		public abstract void checkShape();
	
}
