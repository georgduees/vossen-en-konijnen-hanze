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
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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
	
	//  Initialization of the data collections 
	 
	private DefaultPieDataset dataPercentage;
	private XYSeriesCollection datasetAlive;
	private XYSeriesCollection datasetDeaths;
	private CategoryDataset datasetReasonDeaths;
	private double[][] deathsArray;
	private String[] stepArray;
	
	// Initialization of all the chartpanels
	private ChartPanel deathsChartPanel;
	private ChartPanel aliveChartPanel;
	private ChartPanel percentageChartPanel;
	private ChartPanel deathReasonChart;
	private JPanel chartPanel;
	private boolean chartReason = false;

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
		

		Container contentPane = new Container();
		contentPane.setLayout(new BorderLayout());

		chartPanel = makeChartPanel();
		contentPane.add(chartPanel, BorderLayout.CENTER);

	
	}
	public Container getcontentPane()
	{
		return chartPanel;
		
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
	 * Method to make the chart panel, it returns the JPanel.
	 * It checks the booleans of charts to see if it should include that chart
	 * in the panel.
	 * @return JPanel
	 */

	private JPanel makeChartPanel()
	{
		JPanel chartPanel = new JPanel(new GridLayout(0, 2));
			chartPanel.add(aliveChartPanel);
			chartPanel.add(deathsChartPanel);
			chartPanel.add(percentageChartPanel);
			makeReasonChart();
			chartPanel.add(deathsChartPanel);
		return chartPanel;
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
	}


