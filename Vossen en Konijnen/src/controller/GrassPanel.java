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
public class GrassPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8267198772352729108L;
	private JLabel growingProbabilityLabel;
	private JLabel maxLitterSizeLabel;
	private JLabel grassIllnessProbabilityLabel;
	
	/**
	 * The constructor
	 */
	public GrassPanel()
	{
		makeGrassPanel();
	}
	
	/**
	 * The method makeGrassPanel creates a panel with all the JSliders 
	 * attached to it.
	 */
	public void makeGrassPanel()
	{
		this.setLayout(new GridLayout(3,2));
		
		//Make growingProbabilityLabel
		growingProbabilityLabel = new JLabel("Growing probability (" + AnimalNumbers.getGrassGrowingProbability() + " %)");
		//Make growingProbabilitySlider
		int growingProbability = AnimalNumbers.getGrassGrowingProbability();
		JSlider growingProbabilitySlider = new JSlider(JSlider.HORIZONTAL,0,100,growingProbability);
		growingProbabilitySlider.setMajorTickSpacing(10);
		growingProbabilitySlider.setMinorTickSpacing(1);
		growingProbabilitySlider.setPaintTicks(true);
		growingProbabilitySlider.setPaintLabels(true);
		growingProbabilitySlider.setSnapToTicks(true);
		growingProbabilitySlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeGrassGrowingProbability(e);}
		});
		
		//Make maxLitterSizeLabel
		maxLitterSizeLabel = new JLabel("Maximum grow of new patches (" + AnimalNumbers.getGrassMaxLitterSize() + ")");
		//Make maxLitterSizeSlider
		int maxLitterSize = AnimalNumbers.getGrassMaxLitterSize();
		JSlider maxLitterSizeSlider = new JSlider(JSlider.HORIZONTAL,0,8,maxLitterSize);
		maxLitterSizeSlider.setMajorTickSpacing(4);
		maxLitterSizeSlider.setMinorTickSpacing(1);
		maxLitterSizeSlider.setPaintTicks(true);
		maxLitterSizeSlider.setPaintLabels(true);
		maxLitterSizeSlider.setSnapToTicks(true);
		maxLitterSizeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeGrassMaxLitterSize(e);}
		});
		
		//Make grassIllnessProbabilityLabel
		grassIllnessProbabilityLabel = new JLabel("Grass decease probability (0." + AnimalNumbers.getGrassIllnessProbability() + " %)");
		//Make grassIllnessProbabilitySlider
		int grassIllnessProbability = AnimalNumbers.getGrassIllnessProbability();
		JSlider grassIllnessProbabilitySlider = new JSlider(JSlider.HORIZONTAL,0,100,grassIllnessProbability);
		grassIllnessProbabilitySlider.setMajorTickSpacing(10);
		grassIllnessProbabilitySlider.setMinorTickSpacing(1);
		grassIllnessProbabilitySlider.setPaintTicks(true);
		grassIllnessProbabilitySlider.setPaintLabels(true);
		grassIllnessProbabilitySlider.setSnapToTicks(true);
		grassIllnessProbabilitySlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeGrassIllnessProbability(e);}
		});
		
		//Put the grass panel together
		this.add(growingProbabilityLabel);
		this.add(growingProbabilitySlider);
		
		this.add(maxLitterSizeLabel);
		this.add(maxLitterSizeSlider);
		
		this.add(grassIllnessProbabilityLabel);
		this.add(grassIllnessProbabilitySlider);
	}
	
	/**
	 * If the slider of growingProbability is changed, this method is called and it 
	 * will change the simulation that way.
	 */
	public void changeGrassGrowingProbability(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int growingProbability = (int)source.getValue();
			AnimalNumbers.setGrassGrowingProbability(growingProbability);
			growingProbabilityLabel.setText("Growing probability(" + AnimalNumbers.getGrassGrowingProbability() + " %)");
		}
	}
	
	/**
	 * If the slider of maxLitterSize is changed, this method is called and it will
	 * change the simulation that way.
	 */
	public void changeGrassMaxLitterSize(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int maxLitterSize = (int)source.getValue();
			AnimalNumbers.setGrassMaxLitterSize(maxLitterSize);
			maxLitterSizeLabel.setText("Maximum grow of new patches(" + AnimalNumbers.getGrassMaxLitterSize() + ")");
		}
	}
	
	/**
	 * If the slider of grassIllnessProbability is changed, this method is called and it will
	 * change the simulation that way.
	 */
	public void changeGrassIllnessProbability(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int grassIllnessProbability = (int)source.getValue();
			AnimalNumbers.setGrassIllnessProbability(grassIllnessProbability);
			if(AnimalNumbers.getGrassIllnessProbability() == 100){
				grassIllnessProbabilityLabel.setText("Grass decease probability (1.00 %)");
			}
			else{
				grassIllnessProbabilityLabel.setText("Grass decease probability (0." + AnimalNumbers.getGrassIllnessProbability() + " %)");
			}
		}
	}
}
