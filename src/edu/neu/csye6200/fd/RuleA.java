package edu.neu.csye6200.fd;



import edu.neu.csye6200.fd.FluidFrame;
import edu.neu.csye6200.fd.RuleI;
import edu.neu.csye6200.ui.WolfApp;


public class RuleA implements RuleI {

	
	
	
	public RuleA() {
	}


	@Override
	public FluidFrame createNextFrame(FluidFrame inFrame) {
		FluidFrame nxtFrame = new FluidFrame(inFrame.getSize());

		for (int x = 0; x < inFrame.getSize(); x++) {
			for (int y = 0; y < inFrame.getSize(); y++) {
				int inboundVal = inFrame.getCellInValue(x, y); // Read all neighbors and create opposite inbound values from their outbound ones
				int inbound = createval(x,y,inboundVal);
				int nextOutCelVal = createNextCell(x,y,inbound);
				nxtFrame.OutValue(x, y, nextOutCelVal);
			}
		}
		return nxtFrame;
	}


	@Override
	public int createNextCell(int x,int y,int inVal) {
		
		return inVal;
	}


	@Override
	public int createval(int x, int y, int inVal) {
		
		return inVal;
	}
	
	@Override
	public FluidFrame  ruleselected (FluidFrame inFrame) {
		
		FluidFrame nxtFrame = new FluidFrame(inFrame.getSize());

		for (int x = 0; x < inFrame.getSize(); x++) {
			for (int y = 0; y < inFrame.getSize(); y++) {
				int inboundVal = inFrame.getCellInValue(x, y); // Read all neighbors and create opposite inbound values from their outbound ones
				int inbound = rule1(x,y,inboundVal);
				int nextOutCelVal = createNextrule1(x,y,inbound);
				nxtFrame.OutValue(x, y, nextOutCelVal);
			}
		}
		return nxtFrame;
	}


	private int createNextrule1(int x, int y, int inVal) {
		

		int outdir = 0;
		for (int i = 0; i < 6; i++) {
			if (ParticleCell.hasDirectionFlag(inVal, i))
				outdir = ParticleCell.setFlag(outdir, ParticleCell.ruleselected1(i)); 
		}
		
		return outdir;
	}


	private int rule1(int x, int y, int inVal) {
		
		if (inVal == 0 || inVal == 1 || inVal == 2 || inVal == 4 || inVal == 8 || inVal == 16 || inVal == 32) {
			return inVal;
		}
		else
		{
				inVal = ParticleCell.getNewDirection(inVal);
					
		}
		return inVal;
	}


	


	
}
	

