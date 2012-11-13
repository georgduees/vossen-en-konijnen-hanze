/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.jfree.data.xy.XYSeries;

/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 * @version 2012.11.13
 */

public class WriteToFile {

	public WriteToFile()
	{		
	}
	
	/**
	 * Method to write all series from diagram to file
	 */
	public static void writeArray(XYSeries seriesTotal, XYSeries seriesFoxes, 
				XYSeries seriesRabbits, XYSeries seriesWeasels, 
				XYSeries seriesTotalAlive, XYSeries seriesFoxesAlive, 
				XYSeries seriesRabbitsAlive, XYSeries seriesWeaselsAlive)
	{
		String location = "dataset.txt";
		
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
		    File file = fc.getSelectedFile ();
		    location = file.toString();
		    if (file.exists()){
		    	location += "new";
		    }
		}


		
		ArrayList<double[][]> seriesList = new ArrayList<double[][]>();
		seriesList.add(seriesTotal.toArray());
		seriesList.add(seriesFoxes.toArray());
		seriesList.add(seriesRabbits.toArray());
		seriesList.add(seriesWeasels.toArray());
		seriesList.add(seriesTotalAlive.toArray());
		seriesList.add(seriesFoxesAlive.toArray());
		seriesList.add(seriesRabbitsAlive.toArray());
		seriesList.add(seriesWeaselsAlive.toArray());
		
		try {
		FileWriter fstream = new FileWriter(location);
        BufferedWriter out = new BufferedWriter(fstream);
        for (double[][] series : seriesList){
	        for (double dub : series[1]){
	        	out.write(Double.toString(dub));
	        	out.write("#");
	        }
	        out.newLine();
        }
        out.close();

		}
		catch (Exception e) {
			
		}
	}
	/**
	 * Method to get seriesList back from a saved file.
	 */
	public static ArrayList<double[]> readFile()
	{
		ArrayList<double[]> array = new ArrayList<double[]>();
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			String line;
		    File file = fc.getSelectedFile ();		
		    try {
				FileReader reader = new FileReader(file);
				BufferedReader input = new BufferedReader(reader);
				while ( (line = input.readLine()) != null){
					String[] args = line.split("#");
					double[] dub = new double[args.length];
					int i = 0;
					for (String arg : args){
						dub[i] = Double.parseDouble(arg);
						i++;
					}
					array.add(dub);
					
				}
				//array.remove(array.size() - 1);
				//array.remove(array.size() - 1);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return array;
	}
}
