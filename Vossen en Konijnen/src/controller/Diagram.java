package controller;
/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 * @version 2012.11.13
 */

// all the imports
import model.Numbers;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Diagram 
{	
	
	// initialization of frame.
	
	private JFrame frame;
	
	
	// Initialization of all the series
	
	private XYSeries seriesTotal;
	private XYSeries seriesFoxes;
	private XYSeries seriesRabbits;
	private XYSeries seriesWeasels;
	private XYSeries seriesHunters;
	private XYSeries seriesTotalAlive;
	private XYSeries seriesFoxesAlive;
	private XYSeries seriesRabbitsAlive;
	private XYSeries seriesWeaselsAlive;
	private XYSeries seriesHuntersAlive;
	
	
	// Initialazation of the animals string array, this is used for reason chart
	 
    private final String[] animals = new String[] {"Starvation", "Eaten", "Overcrowded", "Shot", "Sickness"};
	
	//  Initialization of the charts
	
	private JFreeChart chart;
	private JFreeChart chart2;
	private JFreeChart chart3;
	private JFreeChart chart4;
	private JFreeChart chart5;
	
	//  Initialization of the data collections 
	 
	private DefaultPieDataset dataPercentage;
	private XYSeriesCollection datasetAlive;
	private XYSeriesCollection datasetDeaths;
	private CategoryDataset datasetReasonDeaths;
	private double[][] deathsArray;
	private String[] stepArray;
	private DefaultCategoryDataset averageDataset;
	
	// Initialization of all the chartpanels
	private ChartPanel deathsChartPanel;
	private ChartPanel aliveChartPanel;
	private ChartPanel percentageChartPanel;
	private ChartPanel deathReasonChart;
	private ChartPanel avereageChartPanel;
	private JPanel chartPanel;
	private JPanel settingsPanel;
	
	
    // Initialaztion of booleans for showing charts
	private boolean chartAlive = true;
	private boolean chartDeaths = true;
	private boolean chartPercentage = false;
	private boolean chartReason = false;
	private boolean chartAverage = false;
	// Settings Strings
    private final String rabbitString = "Rabbits";
    private final String foxesString = "Foxes";
    private final String weaselsString = "Weasels";
    private final String totalString = "Total animals";
    private String stepsString;

    // Settings checkboxes
    private JCheckBox reasonCheck;
    private JCheckBox averageCheck;
	/**
	 * The constructor for diagram. It only calls the makeFrame method that makes
	 * the chart frame.
	 */
	public Diagram()
	{
		makeFrame();
	}
	
	/**
	 * Method that puts the diagram frame together.
	 * It first calls two methods to make the deaths and percentage charts.
	 * Then it makes a new frame in which it puts the charts. 
	 */
	private void makeFrame()
	{
		makeDeathsCharts();
		makePercentageChart();
		

		frame = new JFrame("Diagrams");
		makeMenuBar(frame);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());

		chartPanel = makeChartPanel();
		settingsPanel = makeSettingsPanel();
		      
	    //contentPane.add(percentageChartPanel);
        //contentPane.add(aliveChartPanel);
		//contentPane.add(deathsChartPanel);
		
		contentPane.add(settingsPanel, BorderLayout.WEST);
		contentPane.add(chartPanel, BorderLayout.CENTER);

	
		frame.pack();
	}
	
	/**
	 * This method makes the Deathscharts. This chart will be updated 
	 * even if its not visible.
	 */
	private void makeDeathsCharts()
	{
		// Make the first chart (Animal deaths chart)
		datasetDeaths = new XYSeriesCollection();
		seriesTotal = new XYSeries("Animals death");
		seriesFoxes = new XYSeries("Foxes deaths");
		seriesRabbits = new XYSeries("Rabbits deaths");
		seriesWeasels = new XYSeries("Weasels deaths");
		seriesHunters = new XYSeries("Hunters deaths");
		
		datasetDeaths.addSeries(seriesTotal);
		datasetDeaths.addSeries(seriesFoxes);
		datasetDeaths.addSeries(seriesRabbits);
		datasetDeaths.addSeries(seriesWeasels);
		datasetDeaths.addSeries(seriesHunters);
		
        chart = ChartFactory.createXYLineChart("Total animals death chart", // Title
                "steps", // x-axis Label
                "Deaths", // y-axis Label
                datasetDeaths, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
            );
		
       deathsChartPanel = new ChartPanel(chart);
    
       // Make the alive chart


	}
	/**
	 * Method to make the percentage chart and the pie chart.
	 */
	public void makePercentageChart()
	{
	       
		datasetAlive = new XYSeriesCollection();
		
		seriesTotalAlive = new XYSeries("Animals Alive");
		seriesFoxesAlive = new XYSeries("Foxes Alive");
		seriesRabbitsAlive = new XYSeries("Rabbits Alive");
		seriesWeaselsAlive = new XYSeries("Weasels Alive");
		seriesHuntersAlive = new XYSeries("Hunters Alive");
		
		datasetAlive.addSeries(seriesTotalAlive);
		datasetAlive.addSeries(seriesFoxesAlive);
		datasetAlive.addSeries(seriesRabbitsAlive);
		datasetAlive.addSeries(seriesWeaselsAlive);
		datasetAlive.addSeries(seriesHuntersAlive);
		chart2 = ChartFactory.createXYLineChart(
	            "Alive",
	            "Steps", "Alive",
	            datasetAlive,
	            PlotOrientation.VERTICAL,
	            true,  // legend
	            true,  // tool tips
	            false  // URLs
	        );
        aliveChartPanel = new ChartPanel(chart2);
        
        // Makes the piechart with percentage animals
        dataPercentage = new DefaultPieDataset();
        chart3 = ChartFactory.createPieChart3D(
                "Pie Chart 3D Demo 2",  // chart title
                dataPercentage,                   // data
                true,                   // include legend
                true,
                false
            );
        percentageChartPanel = new ChartPanel(chart3);
	}
	
	/**
	 * Method to make the death chart. It removes the old chart if it is there.
	 */
	public void makeReasonChart()
	{
		try {
			chartPanel.remove(deathReasonChart);
		} catch (Exception e) {
			
		}
		getStepArray();
		getDeathArray();
		
        datasetReasonDeaths = DatasetUtilities.createCategoryDataset(stepArray, animals, deathsArray);
        chart4 = ChartFactory.createBarChart3D(
                "Reason of death chart",       // chart title
                "Steps",               // domain axis label
                "Deaths",                  // range axis label
                datasetReasonDeaths,                  // data
                PlotOrientation.VERTICAL, // the plot orientation
                true,                    // include legend
                true,
                false
            );
        deathReasonChart = new ChartPanel(chart4);
        if (chartReason){
			chartPanel.add(deathReasonChart);
			chartPanel.validate();
			frame.repaint();
        }

	}
	
	/**
	 * Method to make the average chart .
	 * Begin and end steps need to be given. It makes a chart of the averages
	 * between those steps. 
	 * @param int beginstep int endsteps
	 */
	private void makeAverageChart(int begin, int end)
	{
		if (begin >= end && begin < 0 && end > Numbers.getSteps()){
			JOptionPane.showMessageDialog(frame, "First number should be smaller then second, \n " +
					"First number cannot be smaller then 0, second number cannot be higher then steps taken.");
		}
		averageDataset = new DefaultCategoryDataset();
        stepsString = "Average between " + Integer.toString(begin) + " and " + Integer.toString(end);
        averageDataset.addValue(getRabbitAverage(begin, end), rabbitString, stepsString + " steps");
        averageDataset.addValue(getFoxAverage(begin, end), foxesString, stepsString + " steps");
        averageDataset.addValue(getWeaselAverage(begin, end), weaselsString, stepsString + " steps");
        averageDataset.addValue(getTotalAverage(begin, end), totalString, stepsString + " steps");



		
        chart5 = ChartFactory.createBarChart(
                "Average animals",         // chart title
                "Animals",               // domain axis label
                "Avereage",                  // range axis label
                averageDataset,                  // data
                PlotOrientation.VERTICAL, // orientation
                true,                     // include legend
                true,                     // tooltips?
                false                     // URLs?
            );
        avereageChartPanel = new ChartPanel(chart5);
        
		chartPanel.add(avereageChartPanel);
		chartPanel.validate();
		frame.repaint();

	}
	
	/**
	 * Method to get average of rabbit between begin and end
	 * @param begin
	 * @param end
	 */
	private double getRabbitAverage(int start, int end)
	{
		double total = 0;
		int intstart = start;
		double[][] temp = seriesRabbitsAlive.toArray();
		while (start <= end){
			total += temp[1][start];
			start++;
		}
		return total / (end - intstart);
	}
	/**
	 * Method to get average of fox between begin and end
	 * @param begin
	 * @param end
	 */
	private double getFoxAverage(int start, int end)
	{
		double total = 0;
		int intstart = start;
		double[][] temp = seriesFoxesAlive.toArray();
		while (start <= end){
			total += temp[1][start];
			start++;
		}
		return total / (end - intstart);
	}
	/**
	 * Method to get average of weasels between begin and end
	 * @param begin
	 * @param end
	 */
	private double getWeaselAverage(int start, int end)
	{
		double total = 0;
		int intstart = start;
		double[][] temp = seriesWeaselsAlive.toArray();
		while (start <= end){
			total += temp[1][start];
			start++;
		}
		return total / (end - intstart);
	}
	/**
	 * Method to get average of total animals between begin and end
	 * @param begin
	 * @param end
	 */
	private double getTotalAverage(int start, int end)
	{
		double total = 0;
		int intstart = start;
		double[][] temp = seriesTotalAlive.toArray();
		while (start <= end){
			total += temp[1][start];
			start++;
		}
		return total / (end - intstart);
	}
	
	/**
	 * Method to make the chart panel, it returns the JPanel.
	 * It checks the booleans of charts to see if it should include that chart
	 * in the panel.
	 * @return JPanel
	 */

	private JPanel makeChartPanel()
	{
		JPanel chartPanel = new JPanel(new GridLayout(0, 2));
		if (chartAlive){
			chartPanel.add(aliveChartPanel);
		}
		if (chartDeaths){
			chartPanel.add(deathsChartPanel);
		}
		if (chartPercentage){
			chartPanel.add(percentageChartPanel);
		}
		if (chartAverage){
			chartPanel.add(avereageChartPanel);
		}
		if (chartReason){
			makeReasonChart();
			chartPanel.add(deathsChartPanel);
		}
		
		return chartPanel;
	}
	/**
	 * Method to make the settings Panel
	 * @return JPanel
	 */
	private JPanel makeSettingsPanel()
	{
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(8, 0));
		
		JButton averageButton = new JButton("Select steps for average chart");
		averageButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent arg0) { averageButton(); }
		});
		
		JCheckBox aliveCheck = new JCheckBox("Show alive chart", chartAlive);
		aliveCheck.addItemListener(new ItemListener() { public void itemStateChanged(ItemEvent e) { setAliveChart(); }});
		JCheckBox deathsCheck = new JCheckBox("Show deaths chart", chartDeaths);
		deathsCheck.addItemListener(new ItemListener() { public void itemStateChanged(ItemEvent e) { setDeathsChart(); }});
		JCheckBox percentageCheck = new JCheckBox("Show percentage chart", chartPercentage);
		percentageCheck.addItemListener(new ItemListener() { public void itemStateChanged(ItemEvent e) { setPercentageChart(); }});
		averageCheck = new JCheckBox("Show average chart", chartAverage);
		averageCheck.addItemListener(new ItemListener() { public void itemStateChanged(ItemEvent e) { setAverageChart(); }});
		reasonCheck = new JCheckBox("Show death reason chart", chartReason);
		reasonCheck.addItemListener(new ItemListener() { public void itemStateChanged(ItemEvent e) { setReasonChart(); }});
		

		settingsPanel.add(averageButton);
		settingsPanel.add(aliveCheck);
		settingsPanel.add(deathsCheck);
		settingsPanel.add(percentageCheck);
		settingsPanel.add(averageCheck);
		settingsPanel.add(reasonCheck);

		

		
		return settingsPanel;
	}
	
	/**
	 * Method to be called when averagebutton is pressed, this allows the 
	 * user to choose begin and end steps for average chart.
	 */
	private void averageButton()
	{
		JLabel firstText = new JLabel("First step: ");
		final JTextField beginField = new JTextField();
		JLabel endText = new JLabel("Last step: ");
		final JTextField endField = new JTextField(); 

		JPanel popup = new JPanel();
		popup.setLayout(new GridLayout(2, 2));
		popup.add(firstText);
		popup.add(beginField);
		popup.add(endText);
		popup.add(endField);
		JPanel pop = new JPanel(new BorderLayout());
		pop.add(popup, BorderLayout.NORTH);
		JButton ok = new JButton("Ok");
		pop.add(ok, BorderLayout.SOUTH);
        
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
		
		final JDialog dialog = new JDialog(frame, "Give values for average chart.");
		dialog.add(pop);
		dialog.setLocation(center);
		dialog.setSize(300, 110);
		dialog.setVisible(true);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					makeAverageChart(Integer.parseInt(beginField.getText()), Integer.parseInt(endField.getText()));
					dialog.dispose();

					} 
					catch (Exception ee) {
						JOptionPane.showMessageDialog(frame, "First and last should be numbers. \n" +
								"The first step should be lower then the last step and both shoudn't be out \n" +
								"of range with the amount of steps the simulator has taken.");
					}
				
			}
		});


	}
	
	/**
	 * Method to enable or disable showing the alive chart.
	 */
	private void setAliveChart()
	{
		if (chartAlive){

			chartPanel.remove(aliveChartPanel);
			chartAlive = false;
			chartPanel.validate();
			frame.repaint();
		}
		
		else {
			if (chartPanel.getComponentCount() > 4){
				JOptionPane.showMessageDialog(frame, "Not able to show more then 4 charts, deselect one first.");
			}
			else {
				chartPanel.add(aliveChartPanel);
				chartPanel.validate();
				chartAlive = true;
				frame.repaint();
			}
		}
	}
	/**
	 * Method to enable or disable showing the alive chart.
	 */
	private void setReasonChart()
	{
		if (chartReason && deathReasonChart != null){
			chartPanel.remove(deathReasonChart);
			chartReason = false;
			chartPanel.validate();
			frame.repaint();
		}
		
		else if (deathReasonChart != null){
			if (chartPanel.getComponentCount() > 4){
				JOptionPane.showMessageDialog(frame, "Not able to show more then 4 charts, deselect one first.");
			}
			else {
				chartPanel.add(deathReasonChart);
				chartPanel.validate();
				chartReason = true;
				frame.repaint();
			}
		}
		else {
			if (reasonCheck.getSelectedObjects() != null){
			JOptionPane.showMessageDialog(frame, "Cannot show Reason chart untill atleast 50 steps are taken.");
			}
			reasonCheck.setSelected(false);

		}
	}
	/**
	 * Method to enable or disable showing the alive chart.
	 */
	private void setAverageChart()
	{
		if (chartAverage){
			chartPanel.remove(avereageChartPanel);
			chartAverage = false;
			chartPanel.validate();
			frame.repaint();
		}
		else {

			if (avereageChartPanel != null){
				chartPanel.add(avereageChartPanel);
				chartPanel.validate();
				chartAverage = true;
				frame.repaint();
			}
			else {
				if (averageCheck.getSelectedObjects() != null){
					JOptionPane.showMessageDialog(frame, "Cannot show average chart untill average chart is created.");
				}
				averageCheck.setSelected(false);
			}
			
		}
	}
	/**
	 * Method to enable or disable showing the deaths chart.
	 */
	private void setDeathsChart()
	{
		if (chartDeaths){
			chartPanel.remove(deathsChartPanel);
			chartDeaths = false;
			chartPanel.validate();
			frame.repaint();
		}
		else {
			if (chartPanel.getComponentCount() > 4){
				JOptionPane.showMessageDialog(frame, "Not able to show more then 4 charts, deselect one first.");
			}
			else {
				chartPanel.add(deathsChartPanel);
				chartPanel.validate();
				chartDeaths = true;
				frame.repaint();
			}
		}
	}

	/**
	 * Method to enable or disable showing the deaths chart.
	 */
	private void setPercentageChart()
	{
		if (chartPercentage){
			chartPanel.remove(percentageChartPanel);
			chartPercentage = false;
			chartPanel.validate();
			frame.repaint();
		}
		else {
			if (chartPanel.getComponentCount() > 4){
				JOptionPane.showMessageDialog(frame, "Not able to show more then 4 charts, deselect one first.");
			}
			else {
				chartPanel.add(percentageChartPanel);
				chartPanel.validate();
				chartPercentage = true;
				frame.repaint();
			}
		}
	}
	
	/**
	 * Method to add one step to all the series. This automatically also 
	 * updates the diagrams
	 */
	public void add()
	{
		int tempStep = Numbers.getSteps();

		
		seriesTotalAlive.add(tempStep, Numbers.getTotalAlive());
		seriesFoxesAlive.add(tempStep, Numbers.getFoxes());
		seriesRabbitsAlive.add(tempStep, Numbers.getRabbits());
		seriesWeaselsAlive.add(tempStep, Numbers.getWeasels());
		
		seriesTotal.add(tempStep, Numbers.getTotalDeaths());
		seriesFoxes.add(tempStep, Numbers.getFoxesDeaths());
		seriesRabbits.add(tempStep, Numbers.getRabbitsDeaths());
		seriesWeasels.add(tempStep, Numbers.getWeaselsDeaths());
		if (tempStep % 100 == 0){
			dataPercentage.clear();
	        dataPercentage.setValue("Rabbits", Numbers.getRabbitPercentage());
	        dataPercentage.setValue("Foxes", Numbers.getFoxPercentage());
	        dataPercentage.setValue("Weasels", Numbers.getWeaselPercentage());
	        dataPercentage.setValue("Grass", Numbers.getGrassPercentage());
		}
		if (tempStep != 0 && tempStep % 50 == 0){
			makeReasonChart();
		}
	}
	/**
	 * Method to set the deathsArray variable.
	 */
	private void getDeathArray()
	{
		if (deathsArray == null){
			deathsArray = new double[][]{
					{Numbers.getDeathStarvation(), Numbers.getDeathEaten(), Numbers.getDeathCrowded(),
						Numbers.getDeathShot(),	Numbers.getDeathIll()}
			};
		}
		else {
			double[][] tempArray = new double[deathsArray.length + 1][5];
			for (int i = 0; i < deathsArray.length; i++){
				System.arraycopy(deathsArray[i], 0, tempArray[i], 0, deathsArray[i].length);
			}
			tempArray[deathsArray.length] = new double[]{
					Numbers.getDeathStarvation(), Numbers.getDeathEaten(), Numbers.getDeathCrowded(),
					Numbers.getDeathShot(),	Numbers.getDeathIll()
			};
			deathsArray = tempArray;
		}
	}
	/**
	 * Method to set the stepsArray. 
	 */
	private void getStepArray()
	{
		if (stepArray == null){
			stepArray = new String[]{ Integer.toString(Numbers.getSteps())};
		}
		else {
			String[] tempArray = new String[stepArray.length + 1];
			System.arraycopy(stepArray, 0, tempArray, 0, stepArray.length);
			tempArray[stepArray.length] = Integer.toString(Numbers.getSteps());
			stepArray = tempArray;
		}
	}
	/**
	 * Method that puts the menuBar together.
	 * @param frame
	 */
	private void makeMenuBar(JFrame frameDiagram)
	{
		JMenuBar menuBar = new JMenuBar();
		frameDiagram.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
                           public void actionPerformed(ActionEvent e) { quit(); }
                       });
		
        fileMenu.add(quitItem);
	}
	
	/**
	 * Method that closes the diagram. This doesn't remove the frame 
	 * just sets it to invisible, so that
	 * the diagrams are still updated.
	 */
	private void quit()
	{
		frame.setVisible(false);
	}
	/**
	 * Returns the frame.
	 * @return JFrame
	 */
	public JFrame getFrame()
	{
		return frame;
	}
	

	
	/**
	 * Resets all the series and diagrams.
	 */
	public void reset()
	{
		deathsArray = null;
		stepArray = null;
		dataPercentage.clear();
		seriesTotalAlive.clear();
		seriesFoxesAlive.clear();
		seriesRabbitsAlive.clear();
		seriesWeaselsAlive.clear();
		seriesTotal.clear();
		seriesFoxes.clear();
		seriesRabbits.clear();
		seriesWeasels.clear();
	}
	


	/**
	 * Inner class that makes a new frame for the old chart.
	 */
		
	@SuppressWarnings("serial")
	private class oldDiagram extends JFrame
	{
		
		// Initialization of all the series
		 
		private XYSeries seriesOldTotal;
		private XYSeries seriesOldFoxes;
		private XYSeries seriesOldRabbits;
		private XYSeries seriesOldWeasels;
		private XYSeries seriesOldTotalAlive;
		private XYSeries seriesOldFoxesAlive;
		private XYSeries seriesOldRabbitsAlive;
		private XYSeries seriesOldWeaselsAlive;
		
		// Initialization of the charts
		
		private JFreeChart chart;
		private JFreeChart chart2;
		private XYSeriesCollection datasetDeaths;
		
		//  Initialization of all the chartpanels
		 
		private ChartPanel deathsChartPanel;
		private JPanel chartPanel;
		
		
		

		/**
		 * Method to make the alive chart in a panel.
		 * @return ChartPanel
		 */
		public ChartPanel makeAliveChart()
		{
		    ChartPanel chartPanel;
		    XYSeriesCollection datasetOldAlive = new XYSeriesCollection();

			datasetOldAlive.addSeries(seriesOldTotalAlive);
			datasetOldAlive.addSeries(seriesOldFoxesAlive);
			datasetOldAlive.addSeries(seriesOldRabbitsAlive);
			datasetOldAlive.addSeries(seriesOldWeaselsAlive);
			chart2 = ChartFactory.createXYLineChart(
		            "Alive",
		            "Steps", "Alive",
		            datasetOldAlive,
		            PlotOrientation.VERTICAL,
		            true,  // legend
		            true,  // tool tips
		            false  // URLs
		        );
	        chartPanel = new ChartPanel(chart2);
			return chartPanel;
		}
		
		/**
		 * Method to make the deaths chart its returned in a panel
		 * @return ChartPanel
		 */
		public ChartPanel makeDeathsChart()
		{
			// Make the first chart (Animal deaths chart)
			datasetDeaths = new XYSeriesCollection();
			
			datasetDeaths.addSeries(seriesOldTotal);
			datasetDeaths.addSeries(seriesOldFoxes);
			datasetDeaths.addSeries(seriesOldRabbits);
			datasetDeaths.addSeries(seriesOldWeasels);
			
	        chart = ChartFactory.createXYLineChart("Total animals death chart", // Title
	                "steps", // x-axis Label
	                "Deaths", // y-axis Label
	                datasetDeaths, // Dataset
	                PlotOrientation.VERTICAL, // Plot Orientation
	                true, // Show Legend
	                true, // Use tooltips
	                false // Configure chart to generate URLs?
	            );
			
	       deathsChartPanel = new ChartPanel(chart);
			
			return deathsChartPanel;
		}
	}
}