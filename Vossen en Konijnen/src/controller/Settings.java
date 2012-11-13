package controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 *@version 2012.11.13
 */

public class Settings extends JFrame
{
	private static final long serialVersionUID = 671401579777574634L;
	private JFrame frameSettings;
	private JTabbedPane tabbedPane;
    
	//Images for the tabs are given to variables 
	ImageIcon iconFox = new ImageIcon("Images/fox.gif");
	ImageIcon iconHunter = new ImageIcon("Images/hunter.gif");
	ImageIcon iconRabbit = new ImageIcon("Images/rabbit.gif");
	ImageIcon iconWeasel = new ImageIcon("Images/weasel.gif");
	ImageIcon iconFood = new ImageIcon("Images/food.gif");
	ImageIcon iconGrass = new ImageIcon("Images/grass.gif");
	ImageIcon iconField = new ImageIcon("Images/grid.gif");
	ImageIcon iconPopulate = new ImageIcon("Images/populate.gif");
	
	private JPanel center;
	private int previousI = 0;
	
	/**
	 * The constructor
	 */
	public Settings()
	{
		makeFrame();
	}
	
	/**
	 * Method that creates a new frame were all the different tabs are put on.
	 */
	private void makeFrame()
	{
		//create new JFrame called Settings
		frameSettings = new JFrame("Settings");
		//create a new menu bar
		makeMenuBar(frameSettings);
		
		Container contentPane = frameSettings.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		center = makeCenter();
		contentPane.add(center, BorderLayout.CENTER);
		frameSettings.pack();
		frameSettings.setVisible(true);
	}
	
	/**
	 * Menu bar for the Preferences frame. At the moment, it only has a menu file
	 * with the option to quit the program.
	 * @param framePreferences
	 */
	private void makeMenuBar(JFrame frameSettings)
	{
		JMenuBar menuBar = new JMenuBar();
		frameSettings.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { quit(); }
        });
        //Add the quitItem to the fileMenu
        fileMenu.add(quitItem);
	}
	
	/**
	 * Sets the frame visibility to false, so the frame appears to be closed.
	 * Using the "System.exit(0);" exits the entire program.
	 */
	private void quit()
	{
		frameSettings.setVisible(false);
	}
	
	/**
	 * Here is the actual frame made where all the tabs are on
	 * @return the frame is given back to makeFrame()
	 */
	private JPanel makeCenter()
	{
		JPanel centerPanel = new JPanel();
		tabbedPane = new JTabbedPane();

		FoxPanel foxPanel = new FoxPanel();
		tabbedPane.insertTab("Fox", iconFox, foxPanel,"Fox settings",0);

		JPanel rabbitPanel = new RabbitPanel();
		tabbedPane.insertTab("Rabbit", null, rabbitPanel, "Rabbit settings",1);
		
		JComponent weaselPanel = new WeaselPanel();
		tabbedPane.insertTab("Weasel", null, weaselPanel,"Weasel settings",2);
		
		JComponent hunterPanel = new HunterPanel();
		tabbedPane.insertTab("Hunter", null, hunterPanel, "Hunter settings",3);
		
		JComponent grassPanel = new GrassPanel();
		tabbedPane.insertTab("Grass", null, grassPanel, "Grass settings",4);
		
		JComponent foodValuePanel = new FoodValuePanel();
		tabbedPane.insertTab("Food Values", null, foodValuePanel, "Food value settings",5);
		
		JComponent populateFieldPanel = new PopulateFieldPanel();
		tabbedPane.insertTab("Populate field", null, populateFieldPanel, "Population settings",6);
		
		//JComponent fieldPanel = new FieldPanel();
		//tabbedPane.insertTab("Field", null, fieldPanel, "Field settings",7);
		
		tabbedPane.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeOfTab(e);}
		});
		
		tabbedPane.setPreferredSize(new Dimension(650, 300));
		centerPanel.add(tabbedPane);
		return centerPanel;
	}
    	
	/**
	 * MEthod to change tab icons. the active tab will have a icon all others will not have one.
	 * @param e
	 */
	public void changeOfTab(ChangeEvent e)
	{
		
		JTabbedPane source = (JTabbedPane)e.getSource();
			int i = (int)source.getSelectedIndex();
			tabbedPane.setIconAt(previousI, null);
	   		if(tabbedPane.isEnabledAt(i) == true){
	   			switch(i){
	   			case 0: 
	   				tabbedPane.setIconAt(i,iconFox);
	    			break;
	    		case 1:
	    			tabbedPane.setIconAt(i,iconRabbit);
	    			break;
	    		case 2:
	    			tabbedPane.setIconAt(i,iconWeasel);
	    			break;
	    		case 3:
	    			tabbedPane.setIconAt(i,iconHunter);
	    			break;
	    		case 4: 
	    			tabbedPane.setIconAt(i, iconGrass);
	    			break;
	    		case 5: 
	    			tabbedPane.setIconAt(i,iconFood);
	    			break;
	    		case 6:
	    			tabbedPane.setIconAt(i, iconPopulate);
	    			break;
	    		case 7:
	    			tabbedPane.setIconAt(i, iconField);
	    			break;
	    		}
	    	}
	   		previousI = i;
	}
}
