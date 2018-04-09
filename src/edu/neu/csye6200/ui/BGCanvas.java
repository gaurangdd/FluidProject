package edu.neu.csye6200.ui;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import static edu.neu.csye6200.ui.WolfApp.genNum;
import static edu.neu.csye6200.ui.WolfApp.no;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


import edu.neu.csye6200.fd.FluidFrameAvg;


public class BGCanvas extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(BGCanvas.class.getName());
	private int lineSize = 20;
	private Color col = null;
	public double num=no;
	public int gnum = genNum;
	private long counter = 0L;
	private FluidFrameAvg ffa = null;

	/**
	 *  constructor
	 */
	public BGCanvas() {
		col = Color.BLACK;

	}

	/**
	 * The UI thread calls this method when the screen changes, or in response
	 * to a user initiated call to repaint();
	 */
	public void paint(Graphics g) {
		if (ffa == null)
			drawBG(g); //When nothing to display
		else 
			drawFD(g); //Our frames
	}

	/**
	 * Draw the CA graphics panel
	 * @param g
	 * 
	 */

	public void drawBG(Graphics g) {
		log.info("Drawing BG " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();

		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, size.width, size.height);
	}




	public void drawFD(Graphics g) {
		log.info("Drawing FD: " + counter++);

		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();

		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, size.width, size.height);


		int maxRows = size.height / lineSize;
		int maxCols = size.width / lineSize;

		int ffaSize = ffa.getSize();
		if (maxRows > ffaSize) maxRows = ffaSize;
		if (maxCols > ffaSize) maxCols = ffaSize;

		for (int j = 0; j < maxRows; j++) {
			for (int i = 0 ; i < maxCols ; i++) {
				

				double dirVal = ffa.AvgDirection(j,i);
				double magVal = ffa.AvgMagnitude(j,i);

				System.out.println(">"+dirVal);

				col = Color.ORANGE;

				// Draw box, one pixel less to create a black outline

				int startx = i*lineSize;
				int starty = j*lineSize;
				magVal = 1.0;
				int endx = startx + (int) (magVal + 15.0 * Math.sin(dirVal));
				int endy = starty + (int) (magVal + 15.0 * Math.cos(dirVal));
				paintLine( g2d, startx, starty, endx, endy, col); 

			}
			System.out.println();

		}
	}

	/*
	 * A local routine to ensure that the color value is in the 0 to 255 range.
	 */
	private int validColor(int colorVal) {
		if (colorVal > 255)
			colorVal = 255;
		if (colorVal < 0)
			colorVal = 0;
		return colorVal;
	}


	/**
	 * A convenience routine to set the color and draw a line
	 * @param g2d the 2D Graphics context
	 * @param startx the line start position on the x-Axis
	 * @param starty the line start position on the y-Axis
	 * @param endx the line end position on the x-Axis
	 * @param endy the line end position on the y-Axis
	 * @param color the line color
	 */
	private void paintLine(Graphics2D g2d, int startx, int starty, int endx, int endy, Color color) {
		g2d.setColor(color);
		g2d.drawLine(startx, starty, endx, endy);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Received an update");

		if (arg instanceof FluidFrameAvg) {
			this.ffa = (FluidFrameAvg )arg;
			this.repaint();// repaint the entire canvas screen
			repaint();
		}




	}

}
