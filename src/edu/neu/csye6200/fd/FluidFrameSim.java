package edu.neu.csye6200.fd;



import edu.neu.csye6200.fd.BasicRule;
import edu.neu.csye6200.fd.FluidFrame;
import edu.neu.csye6200.fd.FluidFrameSim;
import edu.neu.csye6200.fd.RuleI;

public class FluidFrameSim {
	
	
	
	private boolean done = false; // Set true to exit (i.e. stop) the simulation
	//private boolean paused = false; // Set true to pause the simulation
	
	private int MAX_FRAME_SIZE = 32; // How big is the simulation frame
	private int MAX_GENERATION = 2; // How many generations will we calculate before we're through?
	private int genCount = 0; // the count of the most recent generation

	private FluidFrame currentFrame = null;
	
	private RuleI rule = null;
	
	/**
	 * 
	 */
	public FluidFrameSim() {
		currentFrame = new FluidFrame(MAX_FRAME_SIZE);
		currentFrame.addRandomParticles(0.50); // Only 20% of the cells should have a particle
		rule = new BasicRule();
	}

	public void runSim() {
		
		System.out.println("FluidFrame: 0" );
		currentFrame.drawFrameToConsole();
		
		while(!done) {
			
			// Move target if needed
			
			FluidFrame nextFrame = rule.createNextFrame(currentFrame);
			
			// Average the results to create a lower-res display frame
			
			// Store the low-res picture and make it available for display
			
			// Advertise that we have a display displayable average FluidFrame and let it be drawn
			
			genCount++; // Keep track of how many frames have been calculated
			System.out.println("\nFluidFrame: " + genCount);
			nextFrame.drawFrameToConsole();
			
			currentFrame = nextFrame;

			if (genCount > MAX_GENERATION) done = true;
		}
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FluidFrameSim ffSim = new FluidFrameSim();
		ffSim.runSim(); // Perform a test run of the simulation
		

	}

}


