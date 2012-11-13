package model;

import java.util.*;
/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 * @version 2012.11.13
 */

public final class Numbers {

	private static int rabbits;
	private static int weasels;
	private static int hunters;
	private static int foxes;
	private static int steps;
	private static int grass;
	private static ArrayList<ArrayList<Integer>> stepAliveList;
	private static int rabbitsDeaths;
	private static int weaselsDeaths;
	private static int huntersDeaths;
	private static int foxesDeaths;
	private static int grassDeaths;
	private static int deathCrowded;
	private static int deathEaten;
	private static int deathStarvation;
	private static int deathShot;
	private static int deathIll;
	
	/**
	 * Constructor
	 */
	public Numbers()
	{
		stepAliveList = new ArrayList<ArrayList<Integer>>();
	}
	
	/**
	 * Method to set steps to 0
	 */
	public static void startLists()
	{
		steps = 0;
	}

	/**
	 * Method to reset all the variables and clear all the arrays
	 */
	public static void reset()
	{
		stepAliveList.clear();
		rabbits = 0;
		weasels = 0;
		hunters = 0;
 		foxes = 0;
		steps = 0;
		grass = 0;
		rabbitsDeaths = 0;
		weaselsDeaths = 0;
		huntersDeaths = 0;
 		foxesDeaths = 0;
		grassDeaths = 0;
		deathCrowded = 0;
		deathEaten = 0;
		deathShot = 0;
		deathStarvation = 0;
		deathIll = 0;
	}
	
	/**
	 * Method that adds one step
	 */
	public static void addStep()
	{
		steps++;
	}

	/**
	 * Method to increase rabbits count by a number. (mostly 1)
	 * @param rabbit
	 */
	public static void setRabbits(int rabbit) {
		rabbits += rabbit;
	}

	/**
	 * Method to get rabbits alive
	 * @return rabbits alive
	 */
	public static int getRabbits() {
		return rabbits - rabbitsDeaths;
	}

	/**
	 * Setter for weasels alive adds weasel int
	 * @param weasel
	 */
	public static void setWeasels(int weasel) {
		weasels += weasel;
	}

	/**
	 * getter for weasels alive
	 * @return weasels alive
	 */
	public static int getWeasels() {
		return weasels - weaselsDeaths;
	}

	/**
	 * Setter for hunters alive, add hunters int.
	 * @param hunter
	 */
	public static void setHunters(int hunter) {
		hunters += hunter;
	}

	/**
	 * Getter for hunters alive.
	 * @return hunters alive
	 */
	public static int getHunters() {
		return hunters - huntersDeaths;
	}

	/**
	 * Setter for foxes alive, adds foxe int.
	 * @param foxe
	 */
	public static void setFoxes(int foxe) {
		foxes += foxe;
	}

	/**
	 * getter for foxes alive
	 * @return foxes alive
	 */
	public static int getFoxes() {
		return foxes - foxesDeaths;
	}

	/**
	 * Setter for grass, adds gras int
	 * @param gras
	 */
	public static void setGrass(int gras) {
		grass += gras;
	}

	/**
	 * getter for grass
	 * @return grass alive
	 */
	public static int getGrass() {
		return grass - grassDeaths;
	}

	/**
	 * Setter for rabbits death, adds rabbitsdeath int
	 * @param rabbitsDeaths
	 */
	public static void setRabbitsDeaths(int rabbitsDeaths) {
		Numbers.rabbitsDeaths += rabbitsDeaths;
	}
	
	/**
	 * getter for steps
	 * @return steps
	 */
	public static int getSteps()
	{
		return steps;
	}

	/**
	 * getter for rabbits death
	 * @return rabbitsdeath
	 */
	public static int getRabbitsDeaths() {
		return rabbitsDeaths;
	}

	/**
	 * Setter for weasels death, adds weaselsdeath int.
	 * @param weaselsDeaths
	 */
	public static void setWeaselsDeaths(int weaselsDeaths) {
		Numbers.weaselsDeaths += weaselsDeaths;
	}

	/**
	 * Getter for weasels death
	 * @return weasels death
	 */
	public static int getWeaselsDeaths() {
		return weaselsDeaths;
	}

	/**
	 * setter for hunters death, adds huntersdeath int
	 * @param huntersDeaths 
	 */
	public static void setHuntersDeaths(int huntersDeaths) {
		Numbers.huntersDeaths += huntersDeaths;
	}

	/**
	 * getter for hunters death
	 * @return huntersDeath
	 */
	public static int getHuntersDeaths() {
		return huntersDeaths;
	}

	/**
	 * Setter for foxes death, adds foxesdeath int
	 * @param foxesDeaths
	 */
	public static void setFoxesDeaths(int foxesDeaths) {
		Numbers.foxesDeaths += foxesDeaths;
	}

	/**
	 * getter for foxes death
	 * @return foxes death
	 */
	public static int getFoxesDeaths() {
		return foxesDeaths;
	}

	/**
	 * setter for grass deaths, adds grassdeath int
	 * @param grassDeaths
	 */
	public static void setGrassDeaths(int grassDeaths) {
		Numbers.grassDeaths += grassDeaths;
	}

	/**
	 * getter for grass deaths
	 * @return grassdeaths
	 */
	public static int getGrassDeaths() {
		return grassDeaths;
	}
	
	/**
	 * getter for total deaths
	 * @return total deaths int
	 */
	public static int getTotalDeaths()
	{
		return foxesDeaths + weaselsDeaths + rabbitsDeaths + grassDeaths;
	}
	
	/**
	 * getter for total alive
	 * @return total alive int
	 */
	public static int getTotalAlive()
	{
		return foxes + weasels + rabbits  - foxesDeaths - weaselsDeaths - rabbitsDeaths;
	}
	
	/**
	 * Method to get percentage of Rabbits alive (100% is all animals, hunters not included)
	 */
	public static double getRabbitPercentage()
	{
		if (getRabbits() != 0 && getTotalAlive() != getRabbits()) {
			return getRabbits() / (getTotalAlive() / 100);
		}
		return 0;
	}
	/**
	 * Method to get percentage of Foxes alive (100% is all animals, hunters not included)
	 */
	public static double getFoxPercentage()
	{
		if (getFoxes() != 0 && getTotalAlive() != getFoxes()){
			return getFoxes() / (getTotalAlive() / 100);
		}
		return 0;
	}
	/**
	 * Method to get percentage of Weasels alive (100% is all animals, hunters not included)
	 */
	public static double getWeaselPercentage()
	{
		if(getWeasels() != 0 && getTotalAlive() != getWeasels()){
			return getWeasels() / (getTotalAlive() / 100);
		}
		return 0;
	}
	/**
	 * Method to get percentage of grass
	 * @return double
	 */
	public static double getGrassPercentage()
	{
		if(getGrass() != 0 && getTotalAlive() != getGrass()){
			return getGrass() / (getTotalAlive() / 100);
		}
		return 0;
	}

	/**
	 * method to add one animal that dieded from overcrowdnes
	 */
	public static void setDeathCrowded() {
		Numbers.deathCrowded++;
	}

	/**
	 * method to see how many animals died from overcrowded
	 * @return
	 */
	public static int getDeathCrowded() {
		return deathCrowded;
	}

	/**
	 * add one to deathEaten counter
	 */
	public static void setDeathEaten() {
		Numbers.deathEaten++;
	}

	/**
	 * @return the deathEaten
	 */
	public static int getDeathEaten() {
		return deathEaten;
	}

	/**
	 * add one to starvation counter
	 */
	public static void setDeathStarvation() {
		Numbers.deathStarvation++;
	}

	/**
	 * @return the deathStarvation
	 */
	public static int getDeathStarvation() {
		return deathStarvation;
	}

	/**
	 * add one to shot by hunter
	 */
	public static void setDeathShot() {
		Numbers.deathShot++;
	}

	/**
	 * @return the deathShot
	 */
	public static int getDeathShot() {
		return deathShot;
	}

	/**
	 * Add one to illness death
	 */
	public static void setDeathIll()
	{
		deathIll++;
	}
	/**
	 * method to get how many deaths by illness
	 */
	public static int getDeathIll()
	{
		return deathIll;
	}

	
}
