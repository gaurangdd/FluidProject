package edu.neu.csye6200.fd;

public class FluidFrameAvg {
	private int size;

	private double direction[][];
	private double magnitude[][];


	

	public FluidFrameAvg(int size) {
		this.size=size;
		direction = new double [size][size];
		magnitude = new double [size][size];
		resetArray();

	}

	public void setFluidFrame(FluidFrame inframe) {

		int stepsize = inframe.size/size;
		System.out.println("Step size is: "+stepsize);

		for (int x = 0; x<size;x++) {
			for (int y=0;y<size;y++) {

				inframe.CalcAvgRegion(y*stepsize, x*stepsize, stepsize);
				direction[y][x] = inframe.getAvgDirection();
				magnitude[y][x] = inframe.getAvgMagnitude();
				

			}
		}

	}
	
	

	
	public int getSize() {
		return size;
	}
	


	private void resetArray() {
		for (int x = 0; x<size;x++) {
			for (int y=0;y<size;y++) {
				direction[y][x] = 0.0;
				magnitude[y][x] = 0.0;
			}
		}


	}
	
	public double AvgDirection(int y, int x) {
		return direction[y][x];
	}
	
	public double AvgMagnitude(int y, int x) {
		return magnitude[y][x];
	}
	
}
