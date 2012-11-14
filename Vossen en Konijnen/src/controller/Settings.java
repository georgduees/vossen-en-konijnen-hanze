package controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *@author Bastiaan Vreijsen, Christian Hilbrands, Georg Duees
 *@version 2012.11.13
 */

public class Settings extends JFrame
{
	//rivate static final long serialVersionUID = 671401579777574634L;
	private JFrame frameSettings;
	private JTabbedPane tabbedPane;
    
	
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
	
		Container contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		
		center = makeCenter();
		contentPane.add(center, BorderLayout.CENTER);
		
	}
	public Container getContainer(){
		return center;
		
	}
	
	/**
	 * Menu bar for the Preferences frame. At the moment, it only has a menu file
	 * with the option to quit the program.
	 * @param framePreferences
	 */
	
	private JPanel makeCenter()
	{
		JPanel centerPanel = new JPanel();
		tabbedPane = new JTabbedPane();

		FoxPanel foxPanel = new FoxPanel();
		tabbedPane.insertTab("Fox", null, foxPanel,"Fox settings",0);

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
