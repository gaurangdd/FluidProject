package edu.neu.csye6200.fd;



import edu.neu.csye6200.fd.FluidFrame;
import edu.neu.csye6200.fd.RuleI;

public class RuleA implements RuleI {

	public RuleA() {
	}


	@Override
	public FluidFrame createNextFrame(FluidFrame inFrame) {
		FluidFrame nxtFrame = new FluidFrame(inFrame.getSize());

		for (int x = 0; x < inFrame.getSize(); x++) {
			for (int y = 0; y < inFrame.getSize(); y++) {
				int inboundVal = inFrame.getCellInValue(x, y); // Read all neighbors and create opposite inbound values from their outbound ones
				int nextOutCelVal = createNextCell(x,y,inboundVal);
				nxtFrame.OutValue(x, y, nextOutCelVal);
			}
		}
		return nxtFrame;
	}


	@Override
	public int createNextCell(int x,int y,int inVal) {
		if (inVal != 1 ||inVal !=2||inVal !=4||inVal !=8||inVal !=16||inVal !=32) {
			return inVal;
		}

		else

			for (int dir = 0 ; dir < 6 ; dir ++) {
				boolean value1 = ParticleCell.hasDirectionFlag(inVal,dir);



				return inVal;


			}
		return inVal;
	}
}
	

