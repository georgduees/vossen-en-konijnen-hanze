package controller;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.AnimalNumbers;


/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 *@version 2012.11.13
 */
public class HunterPanel extends JPanel
{
	private static final long serialVersionUID = 2823335996269702025L;
	// Initialization of JLabels
	private JLabel killStepsLabel;
	private JLabel maxHuntersAllowedLabel;
	private JLabel hunterRangeLabel;
	
	/**
	 * The constructor
	 */
	public HunterPanel()
	{
		makeHunterPanel();
	}
	
	/**
	 * The method makeHunterPanel creates a panel with all the JSliders 
	 * attached to it.
	 */
	public void makeHunterPanel()
	{
		this.setLayout(new GridLayout(3,2));
		
		//Make killStepsLabel
		killStepsLabel = new JLabel("Animals killed every step (" + AnimalNumbers.getHunterKillSteps() + ")");
		//Make killStepsSlider
		int killSteps = AnimalNumbers.getHunterKillSteps();
		JSlider killStepsSlider = new JSlider(JSlider.HORIZONTAL,0,30,killSteps);
		killStepsSlider.setMajorTickSpacing(10);
		killStepsSlider.setMinorTickSpacing(1);
		killStepsSlider.setPaintTicks(true);
		killStepsSlider.setPaintLabels(true);
		killStepsSlider.setSnapToTicks(true);
		killStepsSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeHunterKillSteps(e);}
		});
		
		//Make maxHuntersAllowedLabel
		maxHuntersAllowedLabel = new JLabel("Maximum hunters allowed (" + AnimalNumbers.getMaxHuntersAllowed() + ")");
		//Make maxHuntersAllowedSlider
		int maxHuntersAllowed = AnimalNumbers.getMaxHuntersAllowed();
		JSlider maxHuntersAllowedSlider = new JSlider(JSlider.HORIZONTAL,0,30,maxHuntersAllowed);
		maxHuntersAllowedSlider.setMajorTickSpacing(10);
		maxHuntersAllowedSlider.setMinorTickSpacing(1);
		maxHuntersAllowedSlider.setPaintTicks(true);
		maxHuntersAllowedSlider.setPaintLabels(true);
		maxHuntersAllowedSlider.setSnapToTicks(true);
		maxHuntersAllowedSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeMaxHuntersAllowed(e);}
		});
		
		//Make hunterRangeLabel
		hunterRangeLabel = new JLabel("Hunter range (" + AnimalNumbers.getHunterRange() + ")");
		//Make hunterRangeSlider
		int hunterRange = AnimalNumbers.getHunterRange();
		JSlider hunterRangeSlider = new JSlider(JSlider.HORIZONTAL,0,10,hunterRange);
		hunterRangeSlider.setMajorTickSpacing(5);
		hunterRangeSlider.setMinorTickSpacing(1);
		hunterRangeSlider.setPaintTicks(true);
		hunterRangeSlider.setPaintLabels(true);
		hunterRangeSlider.setSnapToTicks(true);
		hunterRangeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeHunterRange(e);}
		});
		
		//Put the hunter panel together
		this.add(killStepsLabel);
		this.add(killStepsSlider);
		
		this.add(maxHuntersAllowedLabel);
		this.add(maxHuntersAllowedSlider);
		
		this.add(hunterRangeLabel);
		this.add(hunterRangeSlider);
	}
	
	/**
	 * Method that is called when hunterKillSteps is changed. This method will 
	 * apply the changes.
	 */
	public void changeHunterKillSteps(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int hunterKillSteps = (int)source.getValue();
			AnimalNumbers.setHunterKillSteps(hunterKillSteps);
			killStepsLabel.setText("Animals killed every step (" + AnimalNumbers.getHunterKillSteps() + ")");
		}
	}
	
	/**
	 * Method that is called when the maximum number of hunters in the field
	 * is changed. This method will apply the changes.
	 */
	public void changeMaxHuntersAllowed(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int maxHunterAllowed = (int)source.getValue();
			AnimalNumbers.setMaxHuntersAllowed(maxHunterAllowed);
			maxHuntersAllowedLabel.setText("Maximum hunters allowed (" + AnimalNumbers.getMaxHuntersAllowed() + ")");
		}
	}
	
	/**
	 * Method that is called when the maximum number of hunters in the field
	 * is changed. This method will apply the changes.
	 */
	public void changeHunterRange(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int hunterRange = (int)source.getValue();
			AnimalNumbers.setHunterRange(hunterRange);
			hunterRangeLabel.setText("Hunter range (" + AnimalNumbers.getHunterRange() + ")");
		}
	}
}
