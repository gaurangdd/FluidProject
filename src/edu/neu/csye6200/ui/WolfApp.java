package edu.neu.csye6200.ui;

import static java.lang.Integer.parseInt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.neu.csye6200.ui.BGCanvas;
import edu.neu.csye6200.fd.FluidFrameSim;

/**
 * A Test application
 * @author gaura
 */
public class WolfApp extends FDApp {

	private static Logger log = Logger.getLogger(WolfApp.class.getName());

	protected JPanel mainPanel;
	protected JPanel northPanel;

	public static JTextField txtGenerationNum = null;
	
	public JComboBox<String> combo = null;
	private JLabel label1 = null;
	public static int genNum = 0;
	public static double no = 0;

	public static int Myrule = 0;
	protected static JButton startBtn = null;
	protected static JButton stopBtn = null;
	protected static JButton pauseBtn = null;

	public static boolean stopValue = false;
	public static boolean pauseValue = false;

	
	FluidFrameSim sim = null;
	private Thread thread = new Thread();
	FDApp FD = null;
	private FDApp application;


	/**
	 * Sample app constructor
	 */
	public WolfApp() {
		frame.setSize(1000,500); // initial Frame size
		frame.setTitle("WolfApp");
		frame.setResizable(true);

		menuMgr.createDefaultActions(); // Set up default menu items

		sim = new FluidFrameSim(); 


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

		return mainPanel;
	}

	/**
	 * Create a top panel that will hold control buttons
	 * @return
	 */
	public JPanel getNorthPanel() {
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());

		label1=new JLabel("No Of Generations : ");
		txtGenerationNum=new  JTextField("10",5);
		northPanel.add(label1);
		northPanel.add(txtGenerationNum);

		String[] Option ={"Select Rule","Rule 1","Rule 2","Rule 3"};
		combo=new JComboBox(Option);
		combo.setPreferredSize(new Dimension(200,27));
		combo.setLightWeightPopupEnabled(false);
		combo.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				comboactionperformed(evt);
			}
		});
		northPanel.add(combo);

		startBtn = new JButton("Start");
		startBtn.addActionListener(this); 	// Allow the app to hear about button pushes
		northPanel.add(startBtn);

		pauseBtn = new JButton("Pause");
		pauseBtn.addActionListener(this); 	// Allow the app to hear about button pushes
		northPanel.add(pauseBtn);

		stopBtn = new JButton("Stop"); 		// Allow the app to hear about button pushes
		stopBtn.addActionListener(this);
		northPanel.add(stopBtn);

		return northPanel;
	}


	public String getTxtGenerationNum(){
		return txtGenerationNum.getText();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		log.info( ae.getActionCommand());
		if(ae.getActionCommand().equals("Start")) {



			genNum = parseInt(txtGenerationNum.getText());

			System.out.println("Start pressed");
			stopValue = false;
			pauseValue = false;

			sim.setDone(false);
			BGCanvas FDPanel = new BGCanvas();
			FDPanel = new BGCanvas();
			sim.addObserver(FDPanel);
			mainPanel.add(BorderLayout.CENTER, FDPanel);  // Adds canvas at the center
			frame.setVisible(true);		//Making it visible

			thread = new Thread (sim);	//Thread 

			System.out.println("Strating a thread");
			thread.start();


		}




		if (ae.getActionCommand().equals("Stop")) {

			System.out.println("Stop pressed");

			thread.stop();
			stopValue = true;
			sim.setDone(true);
			exit();
			
			

		}
		if (ae.getActionCommand().equals("Pause")) {

			pauseValue = true;

			System.out.println("Pause pressed");

		}

	}	

	public void comboactionperformed(ActionEvent e){

		JComboBox combo = (JComboBox)e.getSource();
		String Name = (String)combo.getSelectedItem();

	
		if(Name=="Rule 1")
		{Myrule = 1;
		System.out.println("Rule 1 is selected");}

		else if(Name=="Rule 2")
		{Myrule = 2;
		System.out.println("Rule 2 is selected");}

		else if(Name=="Rule 3")
		{
			Myrule = 3;
			System.out.println("Rule 3 is selected");
			}

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

