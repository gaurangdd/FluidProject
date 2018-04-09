package edu.neu.csye6200.fd;



import edu.neu.csye6200.fd.FluidFrame;
import edu.neu.csye6200.fd.RuleI;
import edu.neu.csye6200.ui.WolfApp;


public class RuleA implements RuleI {

	
	
	
	public RuleA() {
	}


	@Override
	public FluidFrame createNextFrame(FluidFrame inFrame, int Myrule) {
		FluidFrame nxtFrame = new FluidFrame(inFrame.getSize());
		for (int x = 0; x < inFrame.getSize(); x++) {
			for (int y = 0; y < inFrame.getSize(); y++) {
				int inboundVal = inFrame.getCellInValue(x, y); // Read all neighbors and create opposite inbound values from their outbound ones
				int inbound = createval(x,y,inboundVal,Myrule);
				int nextOutCelVal = createNextCell(x,y,inbound,Myrule);
				nxtFrame.OutValue(x, y, nextOutCelVal);
			}
		}
		return nxtFrame;
	}


	@Override
	public int createNextCell(int x,int y,int inVal, int Myrule) {
		
		return inVal;
	}


	@Override
	public int createval(int x, int y, int inVal, int Myrule) {
		
		return inVal;
	}

}
	

