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
 * change how big the field is in the simulation.
 * 
 * @author Tycho Marinus, Cor Sloot, Mark Visscher, Luuk Blom
 */
public class FieldPanel extends JPanel
{	

	private static final long serialVersionUID = -7392461243807674126L;
	// Initialization of JLabels
	private JLabel fieldWidthLabel;
	private JLabel fieldDepthLabel;
	
	/**
	 * The constructor, only call the makefieldpanel method
	 */
	public FieldPanel()
	{
		makeFieldPanel();
	}
	
	/**
	 * The method makeFieldPanel creates a panel with all the JSliders 
	 * attached to it.
	 */
	public void makeFieldPanel()
	{
		this.setLayout(new GridLayout(2,2));
		
		//Make fieldWidthLabel
		fieldWidthLabel = new JLabel("Field width (" + AnimalNumbers.getFieldWidth() + ")");
		//Make fieldWidthSlider
		int fieldWidth = AnimalNumbers.getFieldWidth();
		JSlider fieldWidthSlider = new JSlider(JSlider.HORIZONTAL,0,100,fieldWidth);
		fieldWidthSlider.setMajorTickSpacing(10);
		fieldWidthSlider.setMinorTickSpacing(1);
		fieldWidthSlider.setPaintTicks(true);
		fieldWidthSlider.setPaintLabels(true);
		fieldWidthSlider.setSnapToTicks(true);
		fieldWidthSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeFieldWidth(e);}
		});
		
		//Make fieldDepthLabel
		fieldDepthLabel = new JLabel("Field depth (" + AnimalNumbers.getFieldDepth() + ")");
		//Make fieldDepthSlider
		int fieldDepth = AnimalNumbers.getFieldDepth();
		JSlider fieldDepthSlider = new JSlider(JSlider.HORIZONTAL,0,100,fieldDepth);
		fieldDepthSlider.setMajorTickSpacing(10);
		fieldDepthSlider.setMinorTickSpacing(1);
		fieldDepthSlider.setPaintTicks(true);
		fieldDepthSlider.setPaintLabels(true);
		fieldDepthSlider.setSnapToTicks(true);
		fieldDepthSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){changeFieldDepth(e);}
		});
		
		//Put the hunter panel together
		this.add(fieldWidthLabel);
		this.add(fieldWidthSlider);
		
		this.add(fieldDepthLabel);
		this.add(fieldDepthSlider);
	}
	
	/**
	 * Method that is called when the width of the field is changed. 
	 * This method will apply the changes.
	 */
	public void changeFieldWidth(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int fieldWidth = (int)source.getValue();
			AnimalNumbers.setFieldWidth(fieldWidth);
			fieldWidthLabel.setText("Field width (" + AnimalNumbers.getFieldWidth() + ")");
		}
	}
	
	/**
	 * Method that is called when the depth of the field is changed. 
	 * This method will apply the changes.
	 */
	public void changeFieldDepth(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();
		if(source.getValueIsAdjusting()){
			int fieldDepth = (int)source.getValue();
			AnimalNumbers.setFieldDepth(fieldDepth);
			fieldDepthLabel.setText("Field depth (" + AnimalNumbers.getFieldDepth() + ")");
		}
	}
}
