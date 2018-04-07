package edu.neu.csye6200.fd;

import edu.neu.csye6200.ui.WolfApp;

/**
 * @author gaura
 *
 */



public class BasicRule extends RuleA {


	public BasicRule() {
		
	}



	@Override
	public int createNextCell(int x, int y,int inVal) {
		
		int outdir = 0;
		for (int i = 0; i < 6; i++) {
			if (ParticleCell.hasDirectionFlag(inVal, i))
				outdir = ParticleCell.setFlag(outdir, ParticleCell.getOppositeDirection(i)); 
		}
		
		return outdir;
	}
	
	public int createval (int x, int y, int inVal) {
		if (inVal == 0 || inVal == 1 || inVal == 2 || inVal == 4 || inVal == 8 || inVal == 16 || inVal == 32) {
			return inVal;
		}
		else
		{
				inVal = ParticleCell.getNewDirection(inVal);
					
		}
		return inVal;
	}
	
	public int rule1 (int x, int y, int inVal) {
		if (inVal == 0 || inVal == 1 || inVal == 2 || inVal == 4 || inVal == 8 || inVal == 16 || inVal == 32) {
			return inVal;
		}
		else
		{
				inVal = ParticleCell.getNewDirection(inVal);
					
		}
		return inVal;
	}
	
public int createNextrule1(int x, int y,int inVal) {
		
		int outdir = 0;
		for (int i = 0; i < 6; i++) {
			if (ParticleCell.hasDirectionFlag(inVal, i))
				outdir = ParticleCell.setFlag(outdir, ParticleCell.ruleselected1(i)); 
		}
		
		return outdir;
	}
	

}


