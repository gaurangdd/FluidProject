package edu.neu.csye6200.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.neu.csye6200.fd.FluidFrameSim;

/**
 * A Test application for the Wolfram Biological Growth application
 * @author MMUNSON
 */
public class WolfApp extends FDApp {

	private static Logger log = Logger.getLogger(WolfApp.class.getName());

	protected JPanel mainPanel;
	protected JPanel northPanel;
	
	//protected JTextField masterSimField;
	
	protected JButton startBtn;
	protected JButton stopBtn;
	
	//protected JButton pauseBtn;
	
    private BGCanvas bgPanel;
	FluidFrameSim sim = null;
	Thread thread = null;
    /**
     * Sample app constructor
     */
    public WolfApp() {
    	frame.setSize(500, 400); // initial Frame size
		frame.setTitle("WolfApp");
		
		menuMgr.createDefaultActions(); // Set up default menu items
		
		sim = new FluidFrameSim(); 
		sim.addObserver(bgPanel);
		
    	showUI(); // Cause the Swing Dispatch thread to display the JFrame
    }
   
    /**
     * Create a main panel that will hold the bulk of our application display
     */
	@Override
	public JPanel getMainPanel() {
	
		mainPanel = new JPanel();
    	mainPanel.setLayout(new BorderLayout());
    	mainPanel.add(BorderLayout.NORTH, getNorthPanel());
    	
    	bgPanel = new BGCanvas();
    	mainPanel.add(BorderLayout.CENTER, bgPanel);
    	
    	return mainPanel;
	}
    
	/**
	 * Create a top panel that will hold control buttons
	 * @return
	 */
    public JPanel getNorthPanel() {
    	northPanel = new JPanel();
    	northPanel.setLayout(new FlowLayout());
    	
    	startBtn = new JButton("Start");
    	startBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			startSim();
    		}
    	}); // Allow the app to hear about button pushes
    	northPanel.add(startBtn);
    	
    	stopBtn = new JButton("Stop"); // Allow the app to hear about button pushes
    	stopBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			stopSim();
    		}
    	});
    	northPanel.add(stopBtn);

    	return northPanel;
    }
    
    private void startSim() {
    	if (sim.isRunning()) return;
    	System.out.println("Strating a thread");
    	if (thread == null)
    		thread = new Thread(sim);
    	
    	thread.start();
    	startBtn.setEnabled(false);
    	
    	
    }
    
    private void stopSim() {
    	System.out.println("Stop Pressed");
    	thread.stop();
    }
    
	@Override
	public void actionPerformed(ActionEvent ae) {
		log.info("We received an ActionEvent " + ae);
		if (ae.getSource() == startBtn)
			System.out.println("Start pressed");
		else if (ae.getSource() == stopBtn)
			System.out.println("Stop pressed");
	}

	@Override
	public void windowOpened(WindowEvent e) {
		log.info("Window opened");
	}

	@Override
	public void windowClosing(WindowEvent e) {	
		log.info("Window closing");
	}



	@Override
	public void windowClosed(WindowEvent e) {
		log.info("Window closed");
	}



	@Override
	public void windowIconified(WindowEvent e) {
		log.info("Window iconified");
	}



	@Override
	public void windowDeiconified(WindowEvent e) {	
		log.info("Window deiconified");
	}



	@Override
	public void windowActivated(WindowEvent e) {
		log.info("Window activated");
	}



	@Override
	public void windowDeactivated(WindowEvent e) {	
		log.info("Window deactivated");
	}
	
	/**
	 * Sample Wolf application starting point
	 * @param args
	 */
	public static void main(String[] args) {
		WolfApp wapp = new WolfApp();
		log.info("WolfApp started");
	}


}

