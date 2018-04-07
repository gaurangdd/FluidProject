package edu.neu.csye6200.fd;



import java.util.Observable;

import edu.neu.csye6200.fd.BasicRule;
import edu.neu.csye6200.fd.FluidFrame;
import edu.neu.csye6200.fd.FluidFrameSim;
import edu.neu.csye6200.fd.RuleI;
import edu.neu.csye6200.ui.BGCanvas;

import static edu.neu.csye6200.ui.WolfApp.pauseValue;
import static edu.neu.csye6200.ui.WolfApp.stopValue;
import static edu.neu.csye6200.ui.WolfApp.genNum;
import static edu.neu.csye6200.ui.WolfApp.pauseValue;
import static edu.neu.csye6200.ui.WolfApp.stopValue;
import static edu.neu.csye6200.ui.WolfApp.rule1;


public class FluidFrameSim extends Observable implements Runnable {
	
	private boolean done = false; // Set true to exit (i.e. stop) the simulation
	private boolean paused = false; // Set true to pause the simulation
	private boolean running = false;
	
	private int MAX_FRAME_SIZE = 128; // How big is the simulation frame
	private int MAX_GENERATION = genNum; // How many generations will we calculate before we're through?
	private int genCount = 0; // the count of the most recent generation

	private FluidFrame currentFrame = null;
	private FluidFrameAvg avgFrame;
	
	private RuleI rule = null;
	//private RuleI ruleselected = null;
	int c = 0;
	
	
	
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
		System.out.println(MAX_GENERATION);
		currentFrame.drawFrameToConsole();
		
		
		while(!done) {
			
			
			if (paused) {
				
				try {
		               wait();	// Thread sleeps (i.e do no work)
		           } catch (InterruptedException e) {
	   }
				
				
				
				notifyAll();
			}
			if (!paused && !pauseValue) { //start
				
				MAX_GENERATION = genNum;
				runSim();
				sleep(30L);
			}
			
			 if(stopValue) {
				 try {
		               Thread.sleep(1000000);
		               // Thread sleeps (i.e do no work)
		           } catch (InterruptedException e) {
      	   }
				 
				 
			 }
					
			if (genCount > MAX_GENERATION) done = true;
		}
		//running = false;
		
	}

	public void runSim() {
		if(rule1 == 1) {
		
			
			FluidFrame nextFrame = rule.ruleselected(currentFrame);
			
		
			// Move target if needed
			
			//FluidFrame nextFrame = rule.createNextFrame(currentFrame);
			
			// Average the results to create a lower-res display frame
			
			// Store the low-res picture and make it available for display
			avgFrame.setFluidFrame(nextFrame);
			
			// Advertise that we have a display displayable average FluidFrame and let it be drawn
			setChanged();
			notifyObservers(avgFrame);
			
			
			genCount++; // Keep track of how many frames have been calculated
			System.out.println("\nFluidFrame: " + genCount);
			nextFrame.drawFrameToConsole();
			
			currentFrame = nextFrame;
		}
		
		else if (rule1 == 2)
		{
			FluidFrame nextFrame = rule.createNextFrame(currentFrame);
			
			
			// Move target if needed
			
			//FluidFrame nextFrame = rule.createNextFrame(currentFrame);
			
			// Average the results to create a lower-res display frame
			
			// Store the low-res picture and make it available for display
			avgFrame.setFluidFrame(nextFrame);
			
			// Advertise that we have a display displayable average FluidFrame and let it be drawn
			setChanged();
			notifyObservers(avgFrame);
			
			
			genCount++; // Keep track of how many frames have been calculated
			System.out.println("\nFluidFrame: " + genCount);
			nextFrame.drawFrameToConsole();
			
			currentFrame = nextFrame;
		}
			
		
		
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

	public boolean isPaused() {
		return running;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}


