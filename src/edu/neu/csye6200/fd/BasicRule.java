package edu.neu.csye6200.fd;



/**
 * @author gaura
 *
 */



public class BasicRule extends RuleA {


	public BasicRule() {
		
	}



	@Override
	public int createNextCell(int x, int y,int inVal,int Myrule) {
		
		int outdir = 0;
		for (int i = 0; i < 6; i++) {
			if (ParticleCell.hasDirectionFlag(inVal, i))
				if(Myrule == 1) {
					outdir = ParticleCell.setFlag(outdir, ParticleCell.getOppositeDirection(i));
				}
			if(Myrule == 2) {
				outdir = ParticleCell.setFlag(outdir, ParticleCell.ruleselected2(inVal)); 
			}
			else if(Myrule == 3) {
				outdir = ParticleCell.setFlag(outdir, ParticleCell.ruleselected3(inVal)); 
			}
				 
		}
		
		return outdir;
	}
	
	public int createval (int x, int y, int inVal, int Myrule) {
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


