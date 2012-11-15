package controller;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.*;

import java.awt.event.*;
import view.SimulatorView;

/**
 *@author Bastiaan Vreijsen, Christian Hilbrands, Georg Duees
 *@version 2012.11.13
 */
public class HeadFrame extends JFrame
{ 
    // Set System L&F



	// Initialization of all the variables
	private static final long serialVersionUID = 3550050864011322216L;
	private JFrame frame;
	private static Simulator sim;
	private SimulatorView view;
	private Diagram diagram; 
	private JPanel center;
	private JTabbedPane tabbedPanel;
	private JComponent simulatorContainer;
	private Settings settings;
	private int previousI=0;

	/**
	 * Contructor it gets a view and a simulator sets them for this headframe,
	 * allowing the buttons to interact with the simulator.
	 * @param view
	 * @param sim
	 */
	public HeadFrame(SimulatorView view, Simulator sim)
	{ 
		this.diagram = new Diagram();
		this.sim = sim;
		this.settings = new Settings();
		this.view = view;
		makeFrame();
		
	}

	/**
	 * Method that creates a new frame it adds a menubar, 
	 * fieldview, legenda and button panel to it. 
	 */
	private void makeFrame()
	{this.simulatorContainer = new JPanel();
	this.simulatorContainer.setLayout(new BorderLayout());
	
		frame = new JFrame("Foxes and Rabbits simulation");
		Dimension dim = new Dimension(400, 300);
		frame.setMinimumSize(dim);
		makeMenuBar(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
contentPane.setLayout(new BorderLayout());
		
		simulatorContainer.getPreferredSize();
		
		center = makeCenter();
		contentPane.add(center,BorderLayout.CENTER);
		simulatorContainer.add(view.getViewPanel(), BorderLayout.CENTER);
		
		JPanel westPanel = makeWestPanel();
		JPanel eastPanel = makeEastPanel();
		simulatorContainer.add(westPanel, BorderLayout.WEST);
		simulatorContainer.add(eastPanel, BorderLayout.EAST);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	/*
	 * Methode that creates the Tabmenu in the MainFrame
	 */
	private JPanel makeCenter()
	{
		JPanel centerPanel = new JPanel();
		tabbedPanel = new JTabbedPane();

	
		//tabbedPanel.insertTab("Simulator", null, simulatorContainer, "Simulator",0);
		
		
		tabbedPanel.insertTab("Simulator", null,simulatorContainer, "SimulatorView",0);
		Container DiagramPanel1 = this.getDiagram().getChart1();
		Container DiagramPanel2 = this.getDiagram().getChart2();
				Container DiagramPanel3 = this.getDiagram().getChart3();
		tabbedPanel.insertTab("Settings", null,this.settings.getContainer(), "Settings",1);
		tabbedPanel.insertTab("Alive Chart", null,DiagramPanel1, "Alive Chart",2);
		tabbedPanel.insertTab("Death Chart", null,DiagramPanel2, "Death Chart",3);
		tabbedPanel.insertTab("Pie Chart", null,DiagramPanel3, "Pie Chart",4);
		tabbedPanel.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeOfTab(e);}
		});
		
		centerPanel.add(tabbedPanel);
		return centerPanel;
	}
	/**
	 * Method that creates the west panel of the simulator.
	 * @return the compiled JPanel
	 */
	private JPanel makeEastPanel()
	{
		JPanel eastPanel = new JPanel(new GridLayout(5, 2, 5, 0));
		
		
		

JLabel fox = new JLabel("Fox");
fox.setForeground(Color.blue);
		JLabel rabbit = new JLabel("Rabbit");
		rabbit.setForeground(Color.orange);

		JLabel weasel = new JLabel("Weasel ");
		weasel.setForeground(Color.red);

		JLabel hunter = new JLabel("Hunter");
		hunter.setForeground(Color.black);

		
		JLabel grass = new JLabel("Grass");
		grass.setForeground(Color.green);

		eastPanel.add(fox);
		
		eastPanel.add(rabbit);
		
		eastPanel.add(weasel);
		
		eastPanel.add(hunter);
		
		eastPanel.add(grass);
		
		return eastPanel;
		
	}

	/**
	 * Method that creates the west panel of the simulator.
	 * @return the compiled JPanel
	 */
	private JPanel makeWestPanel()
	{
		JPanel westPanel = new JPanel(new GridLayout(10, 1));

		JButton oneStep = new JButton("One step");
		oneStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {oneStep(); }
		});
		JButton hundredStep = new JButton("100 steps");
		hundredStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				hundredStep();
			}
		});
	
		JButton startPause = new JButton("Start/Pause");
		startPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { startPause(); }
		});
	
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {reset(); }
		});
		
	/*JButton diagram = new JButton("Diagram");
		diagram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {diagram(); }
		});*/
		
		final JButton speed = new JButton("Speed: \n" + sim.getRunSpeed());
		speed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { runSpeed(speed); }
		});
		
		JButton keepGoing = new JButton("Keep Going");
		keepGoing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { keepGoing(); }
		});
	
		JButton freeSteps = new JButton("Choose steps");
		freeSteps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { freeSteps(); }
		});
		
		
		
		westPanel.add(speed);
		westPanel.add(oneStep);
		westPanel.add(hundredStep);
		westPanel.add(startPause);
		westPanel.add(reset);
	//	westPanel.add(diagram);
		westPanel.add(keepGoing);
		westPanel.add(freeSteps);

		
		return westPanel;
	}
	
	/**
	 * Method that puts the menuBar together.
	 * @param frame
	 */
	private void makeMenuBar(JFrame frameDiagram)
	{
		JMenuBar menuBar = new JMenuBar();
		frameDiagram.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK));
        quitItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { quit(); }
        });
       
        
        JMenuItem start = new JMenuItem("Start");
        start.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
        start.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { keepGoing(); }
        });
        
        JMenuItem steps = new JMenuItem("Choose steps");
        steps.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.Event.CTRL_MASK));
        steps.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { freeSteps(); }
        });
        
       /* JMenuItem mute = new JMenuItem("Mute / Unmute");
        mute.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.Event.CTRL_MASK));
        mute.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { mute(); }
        });*/
        
        JMenuItem reset = new JMenuItem("Reset");
        reset.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.Event.CTRL_MASK));
        reset.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { reset(); }
        });
        
      /*  JMenuItem diagram = new JMenuItem("Diagram");
        diagram.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.Event.CTRL_MASK));
        diagram.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { diagram();}
        });
       */
        
        JMenuItem startPause = new JMenuItem("Start / Pauze");
        startPause.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.Event.CTRL_MASK));
        startPause.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { startPause(); }
        });
        
        
        
        //Add the quitItem to the fileMenu
        fileMenu.add(start);
        fileMenu.addSeparator();
        fileMenu.add(steps);
        //fileMenu.add(diagram);
        fileMenu.add(reset);
        fileMenu.add(startPause);
        fileMenu.addSeparator();
        fileMenu.add(quitItem);
        
        //JMenu preferencesMenu = new JMenu("Preferences");
        JMenu about = new JMenu("About");
        JMenu help = new JMenu("Help");
		//menuBar.add(preferencesMenu);
		menuBar.add(help);
		menuBar.add(about);
		
		
		JMenuItem preferencesItem = new JMenuItem("Actor Settings");
		preferencesItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.Event.CTRL_MASK));
		preferencesItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { settings(); }
		});
		
		JMenuItem aboutt = new JMenuItem("About");
		aboutt.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.Event.CTRL_MASK));
		aboutt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { about(); }
		});
		
		JMenuItem helpp = new JMenuItem("Help");
		helpp.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.Event.CTRL_MASK));
		helpp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { help(); }
		});
		
		//Add the preferencesItem to the preferencesMenu
		//preferencesMenu.add(preferencesItem);
		about.add(aboutt);
		help.add(helpp);
	}
	
	/**
	 * Method to be called when the about menu item is pressed. 
	 */
	public void about()
	{
		JOptionPane.showMessageDialog(this, "Simulatie Vossen en Konijnen. \n\ngemaakt door:\n \nBastiaan Vreijsen\n Christian Hilbrands\n Georg Duees");
	}
	
	/**
	 * Method to be called when the help menu item is call.
	 */
	public void help()
	{
		JOptionPane.showMessageDialog(this, "Starting the simulator:\n\n If you just want to start the simulator click the start button in the filemenu.\n Or if you want to simulate specific amount of steps then you can always\n press one step, it will then simulate only one step. 100 steps obviously\n simulates 100 steps and then stops and keep going will start the simulator\n without stopping. You can also choose your own amount of steps with the\n choose steps button. \n\n Advanced simulation: \n\n You can choose the simulation speed with the speed button, the closer to 0\n the faster it will go. If you want to reset the simulation press the reset button.\n Diagrams are also available of your simulation, just click the diagram button.\n Further customizing the simulation can be done in the preferences menu. \n\n Have fun simulating!");
	}
	
	/**
	 * Method that gives you the option to choose the amount of steps you\
	 * want to run the simulation.
	 */
	public void freeSteps()
	{
		
		String str = JOptionPane.showInputDialog("Give amount of steps.");
		try {
			sim.simulate(Integer.parseInt(str));
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(this, "Steps should be a number.");
		}
	}
	
	/**
	 * Method that makes the simulation run one step.
	 */
	public void oneStep()
	{
		
		sim.simulate(1);
	}
	
	/**
	 * Method that makes the simulation run for 100 steps.
	 */
	public void hundredStep()
	{
		
		sim.simulate(100);
	}
	
	/**
	 * Method to continui or start the simulator 
	 */
	public void startPause()
	{
		
		sim.startPause();
	}
	
	/**
	 * Method to mute the simulator
	 */
	
	
	/**
	 * Method to reset the entire simulator
	 */
	public void reset()
	{
		
		sim.reset();
	}
	
	/**
	 * Method to open the diagram frame
	 */
	/*public void diagram()
	{
		
		this.diagram.getFrame().setVisible(true);

	}*/
	
	/**
	 * Getter for the simulator variable
	 * @return Simulator
	 */
	public static Simulator getSim()
	{
		return sim;
	}
	
	/**
	 * Setter for the run speed
	 * @param speed
	 */
	public void runSpeed(JButton speed)
	{
		
		String str = JOptionPane.showInputDialog("Give new runspeed.");
		try {
			
			sim.setRunSpeed(Integer.parseInt(str));
			speed.setText("Speed: \n" + sim.getRunSpeed());
		} catch (Exception e) {
	    	
			JOptionPane.showMessageDialog(this, "Speed has to be a number.");
		}
	}
	
	/**
	 * Getter for the diagram
	 * @return Diagram
	 */
	public Diagram getDiagram()
	{
		return this.diagram;
	}
	
	/**
	 * Button method to start method keepgoing in simulator
	 */
	public void keepGoing()
	{
		
		sim.keepGoing();
	}
	
	/**
	 * Method that closes the diagram.
	 */
	private void quit()
	{
		
		System.exit(0);
	}
	
	/**
	 * Method to make the settings
	 */
	private void settings()
	{
		new Settings();
	}
	public void changeOfTab(ChangeEvent e)
	{
		
		JTabbedPane source = (JTabbedPane)e.getSource();
			int i = (int)source.getSelectedIndex();
			tabbedPanel.setIconAt(previousI, null);
	   		if(tabbedPanel.isEnabledAt(i) == true){
	   			switch(i){
	   			case 0: 
	    			break;
	    		case 1:
	    			break;
	    		case 2:
	    			break;
	    		case 3:
	    			break;
	    		case 4: 
	    			break;
	    		case 5: 
	    			break;
	    		case 6:
	    			break;
	    		case 7:
	    			break;
	    		}
	    	}
	   		previousI = i;
	}
}
