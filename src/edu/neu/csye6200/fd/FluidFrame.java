package edu.neu.csye6200.fd;


import edu.neu.csye6200.fd.ParticleCell;

/**
 * @author gaura
 *
 */

public class FluidFrame {


	int frame[][] = null;
	int size = 8; // the current grid size

	/**
	 * Constructor
	 */
	public FluidFrame(int size) {
		this.size = size;
		frame = new int[size][size]; // Dynamically build the grid based on the input size
	}


	/**
	 * How big is this frame
	 * @return the grid size (square)
	 */
	public int getSize() {
		return size;
	}


	/**
	 * get the outbound particle state (direction of eminating particles)
	 * @param x
	 * @param y
	 * @return a FluidCell integer value
	 */
	public int getCellOutValue(int x, int y) {
		//if (x < 0) x = 0;
		//if (x >= size) x = size-1;
		//if (y < 0) y = 0;
		//if (y >= size) y = size-1;

		//return frame[x][y];

		if(x>=0 && x<size && y>=0 && y<size){

			return frame[x][y];
		}

		return 0;                

	}




	/**
	 * Set the outbound particle state after an evaluation
	 * @param x
	 * @param y
	 * @param val
	 */
	public void OutValue(int x, int y, int val) {
		if (x < 0) return;
		if (x >= size) return;
		if (y < 0) return;
		if (y >= size) return;
		frame[x][y] = val;
	}

	/**
	 * For a specified direction, set the output flag, indicating an exiting particle
	 * @param x
	 * @param y
	 * @param direction
	 */
	public void addCellOutParticle(int x, int y, int direction) {
		int curVal = getCellOutValue(x,y);
		curVal = ParticleCell.setFlag(curVal, direction);
		OutValue(x,y,curVal);
	}

	/**
	 * Calculate the inbound particles that are headed towards a cell
	 * Do this by evaluating all neighbor cells, and calculating the input vectors 
	 * @param x
	 * @param y
	 */
	public int getCellInValue(int x, int y) {

		// Walk through all surrounding cells, and get the input value (header towards us or not)
		// Add this to our value
		// return the summarized result
		int inVal = 0;
		
		// Walk through each direction
		for (int dir = 0; dir < 6; dir++) {
			// Get the cell in a given direction from our current cell
			if(x-1<0 || y-1<0 || x+1>=size || y+1>=size ){                            

				if(ParticleCell.hasDirectionFlag(getCellOutValue(x,y), dir) && isPointingOutside(x,y,dir)){
					boolean edge = onEdge(x,y,dir);

					inVal = ParticleCell.setFlag(inVal, ParticleCell.deflected(dir, edge) );

				}

			}

			
			//Does it have a particle in the opposite direction?
			// Get the cell in a given direction from our current cell

			int neighborOutCell = getNeighborCellOutValue(x, y, dir);

			// Does it have a particle in the opposite direction?

			if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(dir)))

				inVal = ParticleCell.setFlag(inVal, dir); // If so, then add that direction to our inValue

		}

		return inVal;

	}


	/**
	 * Based on a given position and direction, locate a neighbor cell using a supplied direction
	 * and return its outbound particle values
	 * @param x input frame x coordinate
	 * @param y input frame y coordinate
	 * @param direction the direction to look in
	 * @return the outbound particle value for the neighbor cell
	 */
	public int getNeighborCellOutValue(int x, int y, int direction) {
		if ((y & 1) == 0) { // y is even
			switch (direction) {
			default:
			case 0: return (getCellOutValue(x-1, y));   // Left
			case 1: return (getCellOutValue(x-1, y-1)); // UL
			case 2: return (getCellOutValue(x  , y-1)); // UR
			case 3: return (getCellOutValue(x+1, y));   // Right
			case 4: return (getCellOutValue(x  , y+1)); // LR
			case 5: return (getCellOutValue(x-1, y+1)); // LL	
			}
		}
		else { // y is odd
			switch (direction) {
			default:
			case 0: return (getCellOutValue(x-1, y));   // Left
			
			case 1: return (getCellOutValue(x, y-1)); // UL
			
			case 2: return (getCellOutValue(x+1, y-1)); // UR
			
			case 3: return (getCellOutValue(x+1, y));   // Right
				
			case 4: return (getCellOutValue(x+1, y+1)); // LR
			
			case 5: return (getCellOutValue(x, y+1));   // LL	

			}
		}
	}

	private boolean isPointingOutside(int x, int y, int direction){

		if ((y & 1) == 0) { // y is even

			switch (direction) {

			default:

			case 0: return (x-1<0);   // Left

			case 1: return (x-1<0 || y-1<0); // UL

			case 2: return (y-1<0); // UR

			case 3: return (x+1>=size);   // Right

			case 4: return (y+1>=size); // LR

			case 5: return (x-1<0 || y+1 >= size);// LL	

			}

		}

		else { // y is odd

			switch (direction) {

			default:

			case 0: return (x-1<0);   // Left

			case 1: return (y-1<0); // UL

			case 2: return (x+1>=size || y-1<0); // UR

			case 3: return (x+1>=size);   // Right

			case 4: return (x+1>=size || y+1>=size); // LR

			case 5: return (y+1>=size);  // LL	

			}

		}

	}
	
	public boolean onEdge(int x, int y, int direction) {
		if((y & 1) == 0 ) {
			if((x+1>=size && y-1<0 || x+1<0 && y+1>=size) && (direction ==2 || direction == 4))
				return false;
			else
				return true;
		}
		
		
		else {
			if((x+1>=size && y-1<0 || x-1<0 && y+1>=size) && (direction ==1 || direction == 5))
				return false;
			else if(x+1>=size)
				return false;
			else 
				return true;
		}
	}

	/**
	 * Fill up the current frame up a a specified percentage (i.e. 100% yields an average of one particle direction per cell)
	 * @param percent
	 */
	public void addRandomParticles(double percent) {
		if (percent > 1.0) percent = 1.0; // Don't allow us to fill all cells

		int total = size * size; // This is the maximum number of cells

		total *= percent;

		for (int i = 0; i < total; i++)
			addRandomParticle();
	}

	/**
	 * Add a single random particle - give it a direction value
	 */
	private void addRandomParticle() {
		double maxSize = size + .99; // Well want a range from 0.00 to size.99. When integerized we'll get 0, 1, 2, ... size
		// Create a random X
		int x = (int) (Math.random() * maxSize);
		int y = (int) (Math.random() * maxSize);
		//System.out.println(Math.random());
		// Create a random y
		// Create a random direction
		int direction = (int) (Math.random() * 6.99);
		//direction = 3; // Test - force all particle to move right
		
		
		
		

		// Add the CellOutParticle
		addCellOutParticle(x,y,direction); // add it, or if the particle already exists, just overlay it
	
			
		for(x = 0 ; x<3 ; x++) {
			for (y = size/2; y < size; y++ ) {
				direction = 6;
				addCellOutParticle(x,y,direction);
			}
		}

	}
	

	/**
	 * Draw the current frame to the console
	 */
	public void drawFrameToConsole() {
		char dispChar = ' ';
		for (int y = 0; y <getSize(); y++) {

			if ((y & 1) > 0) // y is odd if true
				System.out.print(" ");
			for (int x = 0; x < getSize(); x++) {

				int cel = getCellOutValue(x, y);
				if (ParticleCell.hasDirectionFlag(cel, 0)) dispChar = '\u2190';
				else if (ParticleCell.hasDirectionFlag(cel, 1)) dispChar = '\u2196';
				else if (ParticleCell.hasDirectionFlag(cel, 2)) dispChar = '\u2197';
				else if (ParticleCell.hasDirectionFlag(cel, 3)) dispChar = '\u2192';
				else if (ParticleCell.hasDirectionFlag(cel, 4)) dispChar = '\u2198';
				else if (ParticleCell.hasDirectionFlag(cel, 5)) dispChar = '\u2199';
				//else if (ParticleCell.hasDirectionFlag(cel, 6)) dispChar = '|';
				else dispChar = '-';

				System.out.print(dispChar + " ");
			}
			System.out.println(""); // Carriage return
		}
	}
	
	private double avgDirection;
	private double avgMagnitude;
	
public void CalcAvgRegion(int yBegin, int xBegin , int stepsize) {
		
		double Sumx = 0.0;
		double Sumy = 0.0;
		
		int xEnd = xBegin + stepsize;
		int yEnd = yBegin + stepsize;
		
		for (int y=yBegin ; y<yEnd ; y++) {
			for (int x=xBegin ; x<xEnd ; x++) {
				int cellval = getCellInValue(x,y);
				if (ParticleCell.hasDirectionFlag(cellval,0)) {
					Sumx += -1.0;
				}
				if (ParticleCell.hasDirectionFlag(cellval,1)) {
					Sumx += -0.5;
					Sumy += -0.87;
				}
				if (ParticleCell.hasDirectionFlag(cellval,2)) {
					Sumx += 0.5;
					Sumy += -0.87;
				}
				if (ParticleCell.hasDirectionFlag(cellval,3)) {
					Sumx += 1.0;
				}
				if (ParticleCell.hasDirectionFlag(cellval,4)) {
					Sumx += 0.5;
					Sumy += 0.87;
				}
				if (ParticleCell.hasDirectionFlag(cellval,5)) {
					Sumx += -0.5;
					Sumy += 0.87;
				}
			}
		}
		
		double divisor = stepsize * stepsize * 3.0;
		
		double avgx = Sumx / divisor;
		double avgy = Sumy / divisor;
		
		avgMagnitude = Math.sqrt(avgx * avgx + avgy * avgy);
		avgDirection = Math.atan(avgx/avgy);
}
		
		public double getAvgDirection() {
			return avgDirection;
		}
	
		public double getAvgMagnitude() {
			return avgMagnitude;
		}


}






