package controller;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.AnimalNumbers;


/**
 *@author Bastiaan Vreijsen, Christian Hilbrands, Georg Duees
 *@version 2012.11.13
 */
public class FoxPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3371982620049870521L;
	private JLabel breedingAgeLabel;
	private JLabel maxAgeLabel;
	private JLabel breedingProbabilityLabel;
	private JLabel maxLitterSizeLabel;
	private JLabel maxFoodLevelLabel;
	
	/**
	 * The Constructor
	 */
	public FoxPanel()
	{
		makeFoxPanel();
	}
	
	/**
	 * This is the method that puts all the sliders on the screen.
	 */
	public void makeFoxPanel()
	{
		this.setLayout(new GridLayout(5,2));
		
		
		//Make breedingAgeLabel
		breedingAgeLabel = new JLabel("Breeding Age (" + AnimalNumbers.getFoxBreedingAge() + ")");
		//Make breedingAgeSlider
		int breedingAge = AnimalNumbers.getFoxBreedingAge();
		JSlider breedingAgeSlider = new JSlider(JSlider.HORIZONTAL,0,300,breedingAge);
		breedingAgeSlider.setMajorTickSpacing(100);
		breedingAgeSlider.setMinorTickSpacing(10);
		breedingAgeSlider.setPaintTicks(true);
		breedingAgeSlider.setPaintLabels(true);
		breedingAgeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeFoxBreedingAge(e);}
		});
		
		//Make maxAgeLabel
		maxAgeLabel = new JLabel("Maximum age(" + AnimalNumbers.getFoxMaxAge() + ")");
		//Make maxAgeSlider
		int maxAge = AnimalNumbers.getFoxMaxAge();
		JSlider maxAgeSlider = new JSlider(JSlider.HORIZONTAL,0,300,maxAge);
		maxAgeSlider.setMajorTickSpacing(100);
		maxAgeSlider.setMinorTickSpacing(10);
		maxAgeSlider.setPaintTicks(true);
		maxAgeSlider.setPaintLabels(true);
		maxAgeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeFoxMaxAge(e);}
		});
		
		//Make breedingProbabilityLabel
		breedingProbabilityLabel = new JLabel("Breeding probability(" + AnimalNumbers.getFoxBreedingProbability() + " %)");
		//Make breedingProbabilitySlider
		int breedingProbability = AnimalNumbers.getFoxBreedingProbability();
		JSlider breedingProbabilitySlider = new JSlider(JSlider.HORIZONTAL,0,100,breedingProbability);
		breedingProbabilitySlider.setMajorTickSpacing(10);
		breedingProbabilitySlider.setMinorTickSpacing(1);
		breedingProbabilitySlider.setPaintTicks(true);
		breedingProbabilitySlider.setPaintLabels(true);
		breedingProbabilitySlider.setSnapToTicks(true);
		breedingProbabilitySlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeFoxBreedingProbability(e);}
		});
		
		//Make maxLitterSizeLabel
		maxLitterSizeLabel = new JLabel("Maximum litter size(" + AnimalNumbers.getFoxMaxLitterSize() + ")");
		//Make maxLitterSizeSlider
		int maxLitterSize = AnimalNumbers.getFoxMaxLitterSize();
		JSlider maxLitterSizeSlider = new JSlider(JSlider.HORIZONTAL,0,8,maxLitterSize);
		maxLitterSizeSlider.setMajorTickSpacing(4);
		maxLitterSizeSlider.setMinorTickSpacing(1);
		maxLitterSizeSlider.setPaintTicks(true);
		maxLitterSizeSlider.setPaintLabels(true);
		maxLitterSizeSlider.setSnapToTicks(true);
		maxLitterSizeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeFoxMaxLitterSize(e);}
		});
		
		//make maxFoodLevelLabel 
		maxFoodLevelLabel = new JLabel("Maximum food level(" + AnimalNumbers.getFoxMaxFoodLevel() + ")");
		//make maxfoodLevelSlider
		int maxFoodLevel = AnimalNumbers.getFoxMaxFoodLevel();
		JSlider maxFoodLevelSlider = new JSlider(JSlider.HORIZONTAL,0,30,maxFoodLevel);
		maxFoodLevelSlider.setMajorTickSpacing(10);
		maxFoodLevelSlider.setMinorTickSpacing(1);
		maxFoodLevelSlider.setPaintTicks(true);
		maxFoodLevelSlider.setPaintLabels(true);
		maxFoodLevelSlider.setSnapToTicks(true);
		maxFoodLevelSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeFoxMaxFoodLevel(e);}
		});
		
		//Put the fox panel together
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
	}

	/**
	 * Change the breeding age of the new foxes with the slider
	 */
	public void changeFoxBreedingAge(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
        if (source.getValueIsAdjusting()) {
            int breedingAge = (int)source.getValue();
            AnimalNumbers.setFoxBreedingAge(breedingAge);
            breedingAgeLabel.setText(("Breeding Age (" + AnimalNumbers.getFoxBreedingAge() + ")"));
        }
	}
	
	/**
	 * Change the maximum age of new foxes with the slider.
	 */
	public void changeFoxMaxAge(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int maxAge = (int)source.getValue();
			AnimalNumbers.setFoxMaxAge(maxAge);
			maxAgeLabel.setText("Maximum age(" + AnimalNumbers.getFoxMaxAge() + ")");
		}
	}
	
	/**
	 * Change the breeding probability of the fox with the slider
	 */
	public void changeFoxBreedingProbability(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int breedingProbability = (int)source.getValue();
			AnimalNumbers.setFoxBreedingProbability(breedingProbability);
			breedingProbabilityLabel.setText("Breeding probability(" + AnimalNumbers.getFoxBreedingProbability() + " %)");
		}
	}
	
	/**
	 * Change the maximum litter size of the fox with a slider
	 */
	public void changeFoxMaxLitterSize(ChangeEvent e)
	{
		JSlider source = (JSlider) e.getSource();
		if(source.getValueIsAdjusting()){
			int maxLitterSize = (int)source.getValue();
			AnimalNumbers.setFoxMaxLitterSize(maxLitterSize);
			maxLitterSizeLabel.setText("Maximum litter size(" + AnimalNumbers.getFoxMaxLitterSize() + ")");
		}
	}
	
	/**
	 * Change the maximum food level of new foxes with the slider.
	 */
	public void changeFoxMaxFoodLevel(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
        if (source.getValueIsAdjusting()) {
            int maxFoodLevel = (int)source.getValue();
            AnimalNumbers.setFoxMaxFoodLevel(maxFoodLevel);
            maxFoodLevelLabel.setText("Maximum food level(" + AnimalNumbers.getFoxMaxFoodLevel() + ")");
        }	
	}
}
