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
public class PopulateFieldPanel extends JPanel
{
	// Initialization of the JLabels
	private JLabel weaselCreationProbabilityLabel;
	private JLabel foxCreationProbabilityLabel;
	private JLabel rabbitCreationProbabilityLabel;
	private JLabel grassCreationProbabilityLabel;
	private JLabel hunterCreationProbabilityLabel;
	
	/**
	 * The constructor
	 */
	public PopulateFieldPanel()
	{
		makePopulateFieldPanel();
	}
	
	/**
	 * The method makePopulateFieldPanel creates a panel with all the JSliders 
	 * attached to it.
	 */
	public void makePopulateFieldPanel()
	{
		this.setLayout(new GridLayout(5,2));
		
		//Make weaselCreationProbabilityLabel
		weaselCreationProbabilityLabel = new JLabel("Weasel creation probability (" + AnimalNumbers.getWeaselCreationProbability() + " %)");
		//Make weaselCreationProbabilitySlider
		int weaselCreationProbability = AnimalNumbers.getWeaselCreationProbability();
		JSlider weaselCreationProbabilitySlider = new JSlider(JSlider.HORIZONTAL,0,30,weaselCreationProbability);
		weaselCreationProbabilitySlider.setMajorTickSpacing(10);
		weaselCreationProbabilitySlider.setMinorTickSpacing(1);
		weaselCreationProbabilitySlider.setPaintTicks(true);
		weaselCreationProbabilitySlider.setPaintLabels(true);
		weaselCreationProbabilitySlider.setSnapToTicks(true);
		weaselCreationProbabilitySlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeWeaselCreationProbability(e);}
		});
		
		//Make foxCreationProbabilityLabel
		foxCreationProbabilityLabel = new JLabel("Fox creation probability (" + AnimalNumbers.getFoxCreationProbability() + " %)");
		//Make foxCreationProbabilitySlider
		int foxCreationProbability = AnimalNumbers.getFoxCreationProbability();
		JSlider foxCreationProbabilitySlider = new JSlider(JSlider.HORIZONTAL,0,30,foxCreationProbability);
		foxCreationProbabilitySlider.setMajorTickSpacing(10);
		foxCreationProbabilitySlider.setMinorTickSpacing(1);
		foxCreationProbabilitySlider.setPaintTicks(true);
		foxCreationProbabilitySlider.setPaintLabels(true);
		foxCreationProbabilitySlider.setSnapToTicks(true);
		foxCreationProbabilitySlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeFoxCreationProbability(e);}
		});
		
		//Make rabbitCreationProbabilityLabel
		rabbitCreationProbabilityLabel = new JLabel("Rabbit creation probability (" + AnimalNumbers.getRabbitCreationProbability() + " %)");
		//Make rabbitCreationProbabilitySlider
		int rabbitCreationProbability = AnimalNumbers.getRabbitCreationProbability();
		JSlider rabbitCreationProbabilitySlider = new JSlider(JSlider.HORIZONTAL,0,30,rabbitCreationProbability);
		rabbitCreationProbabilitySlider.setMajorTickSpacing(10);
		rabbitCreationProbabilitySlider.setMinorTickSpacing(1);
		rabbitCreationProbabilitySlider.setPaintTicks(true);
		rabbitCreationProbabilitySlider.setPaintLabels(true);
		rabbitCreationProbabilitySlider.setSnapToTicks(true);
		rabbitCreationProbabilitySlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeRabbitCreationProbability(e);}
		});
		
		//Make grassCreationProbabilityLabel
		grassCreationProbabilityLabel = new JLabel("Grass creation probability (" + AnimalNumbers.getGrassCreationProbability() + " %)");
		//Make grassCreationProbabilitySlider
		int grassCreationProbability = AnimalNumbers.getGrassCreationProbability();
		JSlider grassCreationProbabilitySlider = new JSlider(JSlider.HORIZONTAL,0,30,grassCreationProbability);
		grassCreationProbabilitySlider.setMajorTickSpacing(10);
		grassCreationProbabilitySlider.setMinorTickSpacing(1);
		grassCreationProbabilitySlider.setPaintTicks(true);
		grassCreationProbabilitySlider.setPaintLabels(true);
		grassCreationProbabilitySlider.setSnapToTicks(true);
		grassCreationProbabilitySlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeGrassCreationProbability(e);}
		});
		
		//Make hunterCreationProbabilityLabel
		hunterCreationProbabilityLabel = new JLabel("Hunter creation probability (" + AnimalNumbers.getHunterCreationProbability() + " %)");
		//Make hunterCreationProbabilitySlider
		int hunterCreationProbability = AnimalNumbers.getHunterCreationProbability();
		JSlider hunterCreationProbabilitySlider = new JSlider(JSlider.HORIZONTAL,0,30,hunterCreationProbability);
		hunterCreationProbabilitySlider.setMajorTickSpacing(10);
		hunterCreationProbabilitySlider.setMinorTickSpacing(1);
		hunterCreationProbabilitySlider.setPaintTicks(true);
		hunterCreationProbabilitySlider.setPaintLabels(true);
		hunterCreationProbabilitySlider.setSnapToTicks(true);
		hunterCreationProbabilitySlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeHunterCreationProbability(e);}
		});
		
		//Put the population panel together
		this.add(weaselCreationProbabilityLabel);
		this.add(weaselCreationProbabilitySlider);
		
		this.add(foxCreationProbabilityLabel);
		this.add(foxCreationProbabilitySlider);
		
		this.add(rabbitCreationProbabilityLabel);
		this.add(rabbitCreationProbabilitySlider);
		
		this.add(grassCreationProbabilityLabel);
		this.add(grassCreationProbabilitySlider);
		
		this.add(hunterCreationProbabilityLabel);
		this.add(hunterCreationProbabilitySlider);
	}
	
	/**
	 * If the slider of weaselCreationProbability is changed, this method 
	 * is called and it will change the simulation in that way.
	 */
	public void changeWeaselCreationProbability(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int weaselCreationProbability = (int)source.getValue();
			AnimalNumbers.setWeaselCreationProbability(weaselCreationProbability);
			weaselCreationProbabilityLabel.setText("Weasel creation probability (" + AnimalNumbers.getWeaselCreationProbability() + " %)");
		}
	}
	
	/**
	 * If the slider of foxCreationProbability is changed, this method 
	 * is called and it will change the simulation in that way.
	 */
	public void changeFoxCreationProbability(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int foxCreationProbability = (int)source.getValue();
			AnimalNumbers.setFoxCreationProbability(foxCreationProbability);
			foxCreationProbabilityLabel.setText("Fox creation probability (" + AnimalNumbers.getFoxCreationProbability() + " %)");
		}
	}
	
	/**
	 * If the slider of rabbitCreationProbability is changed, this method 
	 * is called and it will change the simulation in that way.
	 */
	public void changeRabbitCreationProbability(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int rabbitCreationProbability = (int)source.getValue();
			AnimalNumbers.setRabbitCreationProbability(rabbitCreationProbability);
			rabbitCreationProbabilityLabel.setText("Rabbit creation probability (" + AnimalNumbers.getRabbitCreationProbability() + " %)");
		}
	}
	
	/**
	 * If the slider of grassCreationProbability is changed, this method 
	 * is called and it will change the simulation in that way.
	 */
	public void changeGrassCreationProbability(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int grassCreationProbability = (int)source.getValue();
			AnimalNumbers.setGrassCreationProbability(grassCreationProbability);
			grassCreationProbabilityLabel.setText("Grass creation probability (" + AnimalNumbers.getGrassCreationProbability() + " %)");
		}
	}
	
	/**
	 * If the slider of hunterCreationProbability is changed, this method 
	 * is called and it will change the simulation in that way.
	 */
	public void changeHunterCreationProbability(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int hunterCreationProbability = (int)source.getValue();
			AnimalNumbers.setHunterCreationProbability(hunterCreationProbability);
			hunterCreationProbabilityLabel.setText("Hunter creation probability (" + AnimalNumbers.getHunterCreationProbability() + " %)");
		}
	}
}
