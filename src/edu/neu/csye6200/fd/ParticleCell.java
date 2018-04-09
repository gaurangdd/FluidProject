package edu.neu.csye6200.fd;



public class ParticleCell {

// Binary flag values - these may be overlaid in the same integer
public static final int DIR_0 = 0b000001; //  1 - Left vector
public static final int DIR_1 = 0b000010; //  2 - Up/Left vector
public static final int DIR_2 = 0b000100; //  4 - Up/Right vector
public static final int DIR_3 = 0b001000; //  8 - Right vector
public static final int DIR_4 = 0b010000; // 16 - Down/Right vector
public static final int DIR_5 = 0b100000; // 32 - Down/Left vector


/**
* Determine if a cellValue has a particle moving in a particular direction
* @param cellVal the current cell particle value
* @param direction a direction to check against
* @return true if the cell has a particle moving in the indicated direction
*/
public static boolean hasDirectionFlag(int cellVal, int direction) {

switch (direction) {
default:
case 0: return ((cellVal & DIR_0) > 0); // For direction 0, check to see if the direction 0 flag is set
case 1: return ((cellVal & DIR_1) > 0); 
case 2: return ((cellVal & DIR_2) > 0);
case 3: return ((cellVal & DIR_3) > 0);
case 4: return ((cellVal & DIR_4) > 0);
case 5: return ((cellVal & DIR_5) > 0);

}
}

/**
* Add a direction flag to the supplied cell value
* @param cellVal the current cell particle state
* @param direction a new particle (with direction) to add
* @return the current cell with a new particle overlay
*/
public static int setFlag(int cellVal, int direction) {

switch (direction) {
default:
case 0: cellVal = setDirectionFlag(cellVal, DIR_0); break;
case 1: cellVal = setDirectionFlag(cellVal, DIR_1); break;
case 2: cellVal = setDirectionFlag(cellVal, DIR_2); break;
case 3: cellVal = setDirectionFlag(cellVal, DIR_3); break;
case 4: cellVal = setDirectionFlag(cellVal, DIR_4); break;
case 5: cellVal = setDirectionFlag(cellVal, DIR_5); break;

}
return cellVal;
}

/**
* Add a direction flag to the supplied cell value
* @param cellVal the current cell particle state
* @param direction a new particle (with direction) to add
* @return the current cell with a new particle overlay
*/
public static int setDirectionFlag(int cellVal, int directionFlag) {
	
cellVal |= directionFlag; // Turn on the corresponding bits
return cellVal; // return the result
}

/**
* For a given direction (i.e.0 through 5), return the opposite direction
* @param direction the input direction
* @return the opposite direction number
*/
public static int getOppositeDirection(int direction) {
switch (direction) {
default:
case 0: return 3;
case 1: return 4;
case 2: return 5;
case 3: return 0;
case 4: return 1;
case 5: return 2;

}
}

public static int deflected(int direction, boolean edge) {
	if(edge) {
		switch(direction){
			default:
			case 0: return 3;
			case 1: return 2;
			case 2: return 1;
			case 3: return 0;
			case 4: return 5;
			case 5: return 4;
				
		}
	}
	else {
		switch(direction) {
		default:
		case 0: return 0;
		case 1: return 2;
		case 2: return 1;
		case 3: return 3;
		case 4: return 5;
		case 5: return 4;
		}
	}
}

public static int getNewDirection(int inVal) {
	switch (inVal) {
	default: return inVal;
	case 18: return  9;
	case 19: return 37;
	case 37: return 19;
	case 54: return 27;
	case 21: return 42;
  	case 26: return 37;
	case 44: return 19;
	case 42: return 50;
	case 63: return 63;
	case 35: return 21;
	case 50: return 44;
	case 36: return 10;
	case 9:  return 40;
	case 17: return  9;
	case 57: return 15;
	case 33: return 12;
	case 6:  return 48;
	case 48: return  6;
	case 12: return 33;
	case 20: return 33;
	}
}

//rule 2
public static int ruleselected2(int direction) {
	switch (direction) {
	default: 
	case 0: return 2;
	case 1: return 2;
	case 2: return 2;
	case 3: return 3;
	case 4: return 3;
	case 5: return 3;
	}
}

//rule 3
public static int ruleselected3(int dir) {
	if (dir < 32  ) 
		return 5;

	return dir;
	}
}




