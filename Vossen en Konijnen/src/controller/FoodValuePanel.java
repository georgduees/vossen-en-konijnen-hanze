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
public class FoodValuePanel extends JPanel
{
	// Initialization of JLabels
	private static final long serialVersionUID = 915879588262223169L;
	private JLabel youngFoxFoodValueLabel;
	private JLabel rabbitFoodValueLabel;
	private JLabel grassFoodValueLabel;
	
	/**
	 * The constructor only calls the makefoodValuePanel method
	 */
	public FoodValuePanel()
	{
		makeFoodValuePanel();
	}
	
	/**
	 * The method makeFoodValuePanel creates a panel with all the JSliders 
	 * attached to it.
	 */
	public void makeFoodValuePanel()
	{
		this.setLayout(new GridLayout(3,2));
		
		//Make youngFoxFoodValueLabel
		youngFoxFoodValueLabel = new JLabel("Young fox food value (" + AnimalNumbers.getYoungFoxFoodValue() + ")");
		//Make killStepsSlider
		int youngFoxFoodValue = AnimalNumbers.getYoungFoxFoodValue();
		JSlider youngFoxFoodValueSlider = new JSlider(JSlider.HORIZONTAL,0,30,youngFoxFoodValue);
		youngFoxFoodValueSlider.setMajorTickSpacing(10);
		youngFoxFoodValueSlider.setMinorTickSpacing(1);
		youngFoxFoodValueSlider.setPaintTicks(true);
		youngFoxFoodValueSlider.setPaintLabels(true);
		youngFoxFoodValueSlider.setSnapToTicks(true);
		youngFoxFoodValueSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeYoungFoxFoodValue(e);}
		});
		
		//Make youngFoxFoodValueLabel
		rabbitFoodValueLabel = new JLabel("Rabbit food value (" + AnimalNumbers.getRabbitFoodValue() + ")");
		//Make rabbitFoodValueSlider
		int rabbitFoodValue = AnimalNumbers.getRabbitFoodValue();
		JSlider rabbitFoodValueSlider = new JSlider(JSlider.HORIZONTAL,0,30,rabbitFoodValue);
		rabbitFoodValueSlider.setMajorTickSpacing(10);
		rabbitFoodValueSlider.setMinorTickSpacing(1);
		rabbitFoodValueSlider.setPaintTicks(true);
		rabbitFoodValueSlider.setPaintLabels(true);
		rabbitFoodValueSlider.setSnapToTicks(true);
		rabbitFoodValueSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeRabbitFoodValue(e);}
		});
		
		//Make grassFoodValueLabel
		grassFoodValueLabel = new JLabel("Grass food value (" + AnimalNumbers.getGrassFoodValue() + ")");
		//Make grassFoodValueSlider
		int grassFoodValue = AnimalNumbers.getGrassFoodValue();
		JSlider grassFoodValueSlider = new JSlider(JSlider.HORIZONTAL,0,30,grassFoodValue);
		grassFoodValueSlider.setMajorTickSpacing(10);
		grassFoodValueSlider.setMinorTickSpacing(1);
		grassFoodValueSlider.setPaintTicks(true);
		grassFoodValueSlider.setPaintLabels(true);
		grassFoodValueSlider.setSnapToTicks(true);
		grassFoodValueSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeGrassFoodValue(e);}
		});
		
		
		//Put the foodValuePanel together
		this.add(youngFoxFoodValueLabel);
		this.add(youngFoxFoodValueSlider);
		
		this.add(rabbitFoodValueLabel);
		this.add(rabbitFoodValueSlider);
		
		this.add(grassFoodValueLabel);
		this.add(grassFoodValueSlider);
	}
	
	/**
	 * Method that is called when youngFoxFoodValue is changed. This method will 
	 * apply the changes.
	 */
	public void changeYoungFoxFoodValue(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int youngFoxFoodValue = (int)source.getValue();
			AnimalNumbers.setYoungFoxFoodValue(youngFoxFoodValue);
			youngFoxFoodValueLabel.setText("Young fox food value (" + AnimalNumbers.getYoungFoxFoodValue() + ")");
		}
	}
	
	/**
	 * Method that changes the rabbitFoodValue when it is called.
	 */
	public void changeRabbitFoodValue(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int rabbitFoodValue = (int)source.getValue();
			AnimalNumbers.setRabbitFoodValue(rabbitFoodValue);
			rabbitFoodValueLabel.setText("Rabbit food value (" + AnimalNumbers.getRabbitFoodValue() + ")");
		}
	}
	
	/**
	 * Method that is called when grassFoodValue is changed. This method will 
	 * apply the changes.
	 */
	public void changeGrassFoodValue(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int grassFoodValue = (int)source.getValue();
			AnimalNumbers.setGrassFoodValue(grassFoodValue);
			grassFoodValueLabel.setText("Grass food value (" + AnimalNumbers.getGrassFoodValue() + ")");
		}
	}
}
