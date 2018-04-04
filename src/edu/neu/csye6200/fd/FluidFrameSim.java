package edu.neu.csye6200.fd;



import java.util.Observable;

import edu.neu.csye6200.fd.BasicRule;
import edu.neu.csye6200.fd.FluidFrame;
import edu.neu.csye6200.fd.FluidFrameSim;
import edu.neu.csye6200.fd.RuleI;
import edu.neu.csye6200.ui.BGCanvas;

public class FluidFrameSim extends Observable implements Runnable {
	
	private boolean done = false; // Set true to exit (i.e. stop) the simulation
	private boolean paused = false; // Set true to pause the simulation
	private boolean running = false;
	
	private int MAX_FRAME_SIZE = 16; // How big is the simulation frame
	private int MAX_GENERATION = 5; // How many generations will we calculate before we're through?
	private int genCount = 0; // the count of the most recent generation

	private FluidFrame currentFrame = null;
	private FluidFrameAvg avgFrame = null;
	private RuleI rule = null;
	
	
	
	/**
	 * 
	 */
	public FluidFrameSim() {
		currentFrame = new FluidFrame(MAX_FRAME_SIZE);
		currentFrame.addRandomParticles(0.50); // Only 20% of the cells should have a particle
		avgFrame = new FluidFrameAvg(MAX_FRAME_SIZE/10);
		rule = new BasicRule();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		running = true;
		System.out.println("FluidFrame: 0" );
		currentFrame.drawFrameToConsole();
		
		while(!done) {
			
			if (!paused) {
				runSim();
				sleep(3000L);
			}
			else {
				sleep(5000L);
			}
			if (genCount > MAX_GENERATION) done = true;
		}
		running = false;
		
	}

	public void runSim() {
		
		
		
		
			
			// Move target if needed
			
			FluidFrame nextFrame = rule.createNextFrame(currentFrame);
			
			// Average the results to create a lower-res display frame
			
			// Store the low-res picture and make it available for display
			avgFrame.setFluidFrame(nextFrame);
			
			// Advertise that we have a display displayable average FluidFrame and let it be drawn
			setChanged();
			notifyObservers(nextFrame);
			
			
			genCount++; // Keep track of how many frames have been calculated
			System.out.println("\nFluidFrame: " + genCount);
			nextFrame.drawFrameToConsole();
			
			currentFrame = nextFrame;

			
		
		
	}
	
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args)  {
		FluidFrameSim ffSim = new FluidFrameSim();
		ffSim.run(); // Perform a test run of the simulation
		BGCanvas bg = new BGCanvas();  //Make the subscription
		ffSim.addObserver(bg);
		
		

	}

	

	private void sleep(long l) {
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean isRunning() {
		return running;
	}

}


