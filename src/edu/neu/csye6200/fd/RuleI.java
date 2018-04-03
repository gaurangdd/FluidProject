package edu.neu.csye6200.fd;



import edu.neu.csye6200.fd.FluidFrame;

public interface RuleI {

	public FluidFrame createNextFrame(FluidFrame inFrame);
	public int createNextCell(int x, int y, int inVal);
	public int createval(int x, int y, int inVal,int a);
	
}


