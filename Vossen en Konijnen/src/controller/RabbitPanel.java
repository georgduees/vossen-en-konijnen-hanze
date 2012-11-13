package controller;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.AnimalNumbers;


/**
 * This class is used to implement the settings that you can use to
 * change how the rabbit works in the simulation.
 * 
 * @author Tycho Marinus, Cor Sloot, Mark Visscher, Luuk Blom
 */
public class RabbitPanel extends JPanel
{
	// Initialization of JLabel
	private static final long serialVersionUID = 8611642942730076245L;
	private JLabel breedingAgeLabel;
	private JLabel maxAgeLabel;
	private JLabel breedingProbabilityLabel;
	private JLabel maxLitterSizeLabel;
	private JLabel maxFoodLevelLabel;
	
	/**
	 * The constructor
	 */
	public RabbitPanel()
	{
		makeRabbitPanel();
	}
	
	/**
	 * This method is used to implement the settings that you can use to
	 * change how the rabbit works in the simulation.
	 * @return
	 */
	public void makeRabbitPanel()
	{
		this.setLayout(new GridLayout(5,2));

		//Make breedingAgeLabel
		breedingAgeLabel = new JLabel("Breeding Age (" + AnimalNumbers.getRabbitBreedingAge() + ")");
		//Make breedingAgeSlider
		int breedingAge = AnimalNumbers.getRabbitBreedingAge();
		JSlider breedingAgeSlider = new JSlider(JSlider.HORIZONTAL,0,300,breedingAge);
		breedingAgeSlider.setMajorTickSpacing(100);
		breedingAgeSlider.setMinorTickSpacing(10);
		breedingAgeSlider.setPaintTicks(true);
		breedingAgeSlider.setPaintLabels(true);
		breedingAgeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeRabbitBreedingAge(e);}
		});
		
		//Make maxAgeLabel
		maxAgeLabel = new JLabel("Maximum age(" + AnimalNumbers.getRabbitMaxAge() + ")");
		//Make maxAgeSlider
		int maxAge = AnimalNumbers.getRabbitMaxAge();
		JSlider maxAgeSlider = new JSlider(JSlider.HORIZONTAL,0,300,maxAge);
		maxAgeSlider.setMajorTickSpacing(100);
		maxAgeSlider.setMinorTickSpacing(10);
		maxAgeSlider.setPaintTicks(true);
		maxAgeSlider.setPaintLabels(true);
		maxAgeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeRabbitMaxAge(e);}
		});
		
		//Make breedingProbabilityLabel
		breedingProbabilityLabel = new JLabel("Breeding probability(" + AnimalNumbers.getRabbitBreedingProbability() + " %)");
		//Make breedingProbabilitySlider
		int breedingProbability = AnimalNumbers.getRabbitBreedingProbability();
		JSlider breedingProbabilitySlider = new JSlider(JSlider.HORIZONTAL,0,100,breedingProbability);
		breedingProbabilitySlider.setMajorTickSpacing(10);
		breedingProbabilitySlider.setMinorTickSpacing(1);
		breedingProbabilitySlider.setPaintTicks(true);
		breedingProbabilitySlider.setPaintLabels(true);
		breedingProbabilitySlider.setSnapToTicks(true);
		breedingProbabilitySlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeRabbitBreedingProbability(e);}
		});
		
		//Make maxLitterSizeLabel
		maxLitterSizeLabel = new JLabel("Maximum litter size(" + AnimalNumbers.getRabbitMaxLitterSize() + ")");
		//Make maxLitterSizeSlider
		int maxLitterSize = AnimalNumbers.getRabbitMaxLitterSize();
		JSlider maxLitterSizeSlider = new JSlider(JSlider.HORIZONTAL,0,8,maxLitterSize);
		maxLitterSizeSlider.setMajorTickSpacing(4);
		maxLitterSizeSlider.setMinorTickSpacing(1);
		maxLitterSizeSlider.setPaintTicks(true);
		maxLitterSizeSlider.setPaintLabels(true);
		maxLitterSizeSlider.setSnapToTicks(true);
		maxLitterSizeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeRabbitMaxLitterSize(e);}
		});
		
		//make maxFoodLevelLabel 
		maxFoodLevelLabel = new JLabel("Maximum food level(" + AnimalNumbers.getRabbitMaxFoodLevel() + ")");
		//make maxfoodLevelSlider
		int maxFoodLevel = AnimalNumbers.getRabbitMaxFoodLevel();
		JSlider maxFoodLevelSlider = new JSlider(JSlider.HORIZONTAL,0,30,maxFoodLevel);
		maxFoodLevelSlider.setMajorTickSpacing(10);
		maxFoodLevelSlider.setMinorTickSpacing(1);
		maxFoodLevelSlider.setPaintTicks(true);
		maxFoodLevelSlider.setPaintLabels(true);
		maxFoodLevelSlider.setSnapToTicks(true);
		maxFoodLevelSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeRabbitMaxFoodLevel(e);}
		});
		
		//Put the rabbit panel together
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
	 * Change the breeding age of the new rabbits with the slider
	 */
	public void changeRabbitBreedingAge(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
        if (source.getValueIsAdjusting()) {
            int breedingAge = (int)source.getValue();
            AnimalNumbers.setRabbitBreedingAge(breedingAge);
            breedingAgeLabel.setText(("Breeding Age (" + AnimalNumbers.getRabbitBreedingAge() + ")"));
        }
	}
	
	/**
	 * Change the maximum age of new rabbits with the slider.
	 */
	public void changeRabbitMaxAge(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int maxAge = (int)source.getValue();
			AnimalNumbers.setRabbitMaxAge(maxAge);
			maxAgeLabel.setText("Maximum age(" + AnimalNumbers.getRabbitMaxAge() + ")");
		}
	}
	
	/**
	 * Change the breeding probability of the rabbits with the slider
	 */
	public void changeRabbitBreedingProbability(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int breedingProbability = (int)source.getValue();
			AnimalNumbers.setRabbitBreedingProbability(breedingProbability);
			breedingProbabilityLabel.setText("Breeding probability(" + AnimalNumbers.getRabbitBreedingProbability() + " %)");
		}
	}
	
	/**
	 * Change the maximum litter size of the rabbits with a slider
	 */
	public void changeRabbitMaxLitterSize(ChangeEvent e)
	{
		JSlider source = (JSlider) e.getSource();
		if(source.getValueIsAdjusting()){
			int maxLitterSize = (int)source.getValue();
			AnimalNumbers.setRabbitMaxLitterSize(maxLitterSize);
			maxLitterSizeLabel.setText("Maximum litter size(" + AnimalNumbers.getRabbitMaxLitterSize() + ")");
		}
	}
	
	/**
	 * Change the maximum food level of new rabbits with the slider.
	 */
	public void changeRabbitMaxFoodLevel(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
        if (source.getValueIsAdjusting()) {
            int maxFoodLevel = (int)source.getValue();
            AnimalNumbers.setRabbitMaxFoodLevel(maxFoodLevel);
            maxFoodLevelLabel.setText("Maximum food level(" + AnimalNumbers.getRabbitMaxFoodLevel() + ")");
        }	
	}
}
