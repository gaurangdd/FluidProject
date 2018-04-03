package edu.neu.csye6200.fd;


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
	
	public int createval (int x, int y, int inVal, int a) {
		if (a == 0 || a == 1 || a == 2 || a == 4 || a == 8 || a == 16 || a == 32) {
			return inVal;
		}
		else
		inVal = 0;
		for (int dir = 0 ; dir < 6 ; dir ++) {
			if (ParticleCell.hasDirectionFlag(a, dir))
				inVal = ParticleCell.setFlag(inVal, ParticleCell.getOppositeDirection(dir));
					
		}
		return inVal;
	}
	

}


