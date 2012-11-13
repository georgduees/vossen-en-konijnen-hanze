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
public class WeaselPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1255551957370653213L;
	private JLabel breedingAgeLabel;
	private JLabel maxAgeLabel;
	private JLabel breedingProbabilityLabel;
	private JLabel maxLitterSizeLabel;
	private JLabel maxFoodLevelLabel;
	private JLabel weaselEatingFoxAgeLabel;
	
	/**
	 * The constructor
	 */
	public WeaselPanel()
	{
		makeWeaselPanel();
	}
	
	/**
	 * This method is used to implement the settings that you can use to
	 * change how the Weasel works in the simulation.
	 * @return
	 */
	public void makeWeaselPanel()
	{
		this.setLayout(new GridLayout(6,2));
		
		//Make breedingAgeLabel
		breedingAgeLabel = new JLabel("Breeding Age (" + AnimalNumbers.getWeaselBreedingAge() + ")");
		//Make breedingAgeSlider
		int breedingAge = AnimalNumbers.getWeaselBreedingAge();
		JSlider breedingAgeSlider = new JSlider(JSlider.HORIZONTAL,0,300,breedingAge);
		breedingAgeSlider.setMajorTickSpacing(100);
		breedingAgeSlider.setMinorTickSpacing(10);
		breedingAgeSlider.setPaintTicks(true);
		breedingAgeSlider.setPaintLabels(true);
		breedingAgeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeWeaselBreedingAge(e);}
		});
		
		//Make maxAgeLabel
		maxAgeLabel = new JLabel("Maximum age(" + AnimalNumbers.getWeaselMaxAge() + ")");
		//Make maxAgeSlider
		int maxAge = AnimalNumbers.getWeaselMaxAge();
		JSlider maxAgeSlider = new JSlider(JSlider.HORIZONTAL,0,300,maxAge);
		maxAgeSlider.setMajorTickSpacing(100);
		maxAgeSlider.setMinorTickSpacing(10);
		maxAgeSlider.setPaintTicks(true);
		maxAgeSlider.setPaintLabels(true);
		maxAgeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeWeaselMaxAge(e);}
		});
		
		//Make breedingProbabilityLabel
		breedingProbabilityLabel = new JLabel("Breeding probability(" + AnimalNumbers.getWeaselBreedingProbability() + " %)");
		//Make breedingProbabilitySlider
		int breedingProbability = AnimalNumbers.getWeaselBreedingProbability();
		JSlider breedingProbabilitySlider = new JSlider(JSlider.HORIZONTAL,0,100,breedingProbability);
		breedingProbabilitySlider.setMajorTickSpacing(10);
		breedingProbabilitySlider.setMinorTickSpacing(1);
		breedingProbabilitySlider.setPaintTicks(true);
		breedingProbabilitySlider.setPaintLabels(true);
		breedingProbabilitySlider.setSnapToTicks(true);
		breedingProbabilitySlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeWeaselBreedingProbability(e);}
		});
		
		//Make maxLitterSizeLabel
		maxLitterSizeLabel = new JLabel("Maximum litter size(" + AnimalNumbers.getWeaselMaxLitterSize() + ")");
		//Make maxLitterSizeSlider
		int maxLitterSize = AnimalNumbers.getWeaselMaxLitterSize();
		JSlider maxLitterSizeSlider = new JSlider(JSlider.HORIZONTAL,0,8,maxLitterSize);
		maxLitterSizeSlider.setMajorTickSpacing(4);
		maxLitterSizeSlider.setMinorTickSpacing(1);
		maxLitterSizeSlider.setPaintTicks(true);
		maxLitterSizeSlider.setPaintLabels(true);
		maxLitterSizeSlider.setSnapToTicks(true);
		maxLitterSizeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeWeaselMaxLitterSize(e);}
		});
		
		//make maxFoodLevelLabel 
		maxFoodLevelLabel = new JLabel("Maximum food level(" + AnimalNumbers.getWeaselMaxFoodLevel() + ")");
		//make maxfoodLevelSlider
		int maxFoodLevel = AnimalNumbers.getWeaselMaxFoodLevel();
		JSlider maxFoodLevelSlider = new JSlider(JSlider.HORIZONTAL,0,30,maxFoodLevel);
		maxFoodLevelSlider.setMajorTickSpacing(10);
		maxFoodLevelSlider.setMinorTickSpacing(1);
		maxFoodLevelSlider.setPaintTicks(true);
		maxFoodLevelSlider.setPaintLabels(true);
		maxFoodLevelSlider.setSnapToTicks(true);
		maxFoodLevelSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeWeaselMaxFoodLevel(e);}
		});
		
		//Make weaselEatingFoxAgeLabel
		weaselEatingFoxAgeLabel = new JLabel("Weasel eating fox age(" + AnimalNumbers.getWeaselEatingFoxAge() + ")");
		//Make weaselEatingFoxAgeSlider
		int weaselEatingFoxAge = AnimalNumbers.getWeaselEatingFoxAge();
		JSlider weaselEatingFoxAgeSlider = new JSlider(JSlider.HORIZONTAL,0,300,weaselEatingFoxAge);
		weaselEatingFoxAgeSlider.setMajorTickSpacing(100);
		weaselEatingFoxAgeSlider.setMinorTickSpacing(10);
		weaselEatingFoxAgeSlider.setPaintTicks(true);
		weaselEatingFoxAgeSlider.setPaintLabels(true);
		weaselEatingFoxAgeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeWeaselEatingFoxAge(e);}
		});
		
		//Put the Weasel panel together
		this.add(breedingAgeLabel);
		this.add(breedingAgeSlider);
		
		this.add(maxAgeLabel);
		this.add(maxAgeSlider);
		
		this.add(breedingProbabilityLabel);
		this.add(breedingProbabilitySlider);
		
		this.add(maxLitterSizeLabel);
		this.add(maxLitterSizeSlider);
		
		this.add(maxFoodLevelLabel);
		this.add(maxFoodLevelSlider);
		
		this.add(weaselEatingFoxAgeLabel);
		this.add(weaselEatingFoxAgeSlider);
	}

	/**
	 * Change the breeding age of the new Weasels with the slider
	 */
	public void changeWeaselBreedingAge(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
        if (source.getValueIsAdjusting()) {
            int breedingAge = (int)source.getValue();
            AnimalNumbers.setWeaselBreedingAge(breedingAge);
            breedingAgeLabel.setText(("Breeding Age (" + AnimalNumbers.getWeaselBreedingAge() + ")"));
        }
	}
	
	/**
	 * Change the maximum age of new Weasels with the slider.
	 */
	public void changeWeaselMaxAge(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int maxAge = (int)source.getValue();
			AnimalNumbers.setWeaselMaxAge(maxAge);
			maxAgeLabel.setText("Maximum age(" + AnimalNumbers.getWeaselMaxAge() + ")");
		}
	}
	
	/**
	 * Change the breeding probability of the Weasels with the slider
	 */
	public void changeWeaselBreedingProbability(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int breedingProbability = (int)source.getValue();
			AnimalNumbers.setWeaselBreedingProbability(breedingProbability);
			breedingProbabilityLabel.setText("Breeding probability(" + AnimalNumbers.getWeaselBreedingProbability() + " %)");
		}
	}
	
	/**
	 * Change the maximum litter size of the Weasels with a slider
	 */
	public void changeWeaselMaxLitterSize(ChangeEvent e)
	{
		JSlider source = (JSlider) e.getSource();
		if(source.getValueIsAdjusting()){
			int maxLitterSize = (int)source.getValue();
			AnimalNumbers.setWeaselMaxLitterSize(maxLitterSize);
			maxLitterSizeLabel.setText("Maximum litter size(" + AnimalNumbers.getWeaselMaxLitterSize() + ")");
		}
	}
	
	/**
	 * Change the maximum food level of new Weasels with the slider.
	 */
	public void changeWeaselMaxFoodLevel(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
        if (source.getValueIsAdjusting()) {
            int maxFoodLevel = (int)source.getValue();
            AnimalNumbers.setWeaselMaxFoodLevel(maxFoodLevel);
            maxFoodLevelLabel.setText("Maximum food level(" + AnimalNumbers.getWeaselMaxFoodLevel() + ")");
        }	
	}
	
	/**
	 * Change the age at which a fox can be eaten by a weasel.
	 */
	public void changeWeaselEatingFoxAge(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		int weaselEatingFoxAge = (int)source.getValue();
        AnimalNumbers.setWeaselEatingFoxAge(weaselEatingFoxAge);
        weaselEatingFoxAgeLabel.setText("Weasel eating fox age(" + AnimalNumbers.getWeaselEatingFoxAge() + ")");
	}
}
