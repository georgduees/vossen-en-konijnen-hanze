 package model;

import java.util.List;

import view.Field;
import view.Location;
/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 * @version 2012.11.13
 */

public class Grass implements Actor 
{
    
    // Individual characteristics (instance fields).
    //the location of the grass.
    private Location location;
    //the field where the grass grows.
    private Field field;
    // Whether the grass is alive or not.
    private boolean alive;
    
    // The likelihood of a grass growing in another patch of land.
    private int growingProbability;
    // The maximum number of patches that will spawn grass.
    private int maxLitterSize;
    // The possibility if grass can activate a illness at the rabbits.
	private boolean ill;
	// The probability grass is ill and will affect rabbits.
	private int grassIllnessProbability;
	
	/**
	 * The constructor
	 */
    public Grass(Field field, Location location)
    {
    	alive = true;
    	this.field = field;
        setLocation(location, false);
        grassIllnessProbability = AnimalNumbers.getGrassIllnessProbability();
        if(rand.nextInt(1000) <= grassIllnessProbability)
        {
        	ill = true;
        }
        growingProbability = AnimalNumbers.getGrassGrowingProbability();
        maxLitterSize = AnimalNumbers.getGrassMaxLitterSize();

    }
    
    @Override
	public void act(List<Actor> newGrass) 
    {
    	if(isAlive()){
    		grow(newGrass);
    	}   	
    }
    
    /**
     * Check whether or not this animal is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newAnimals A list to add newly born rabbits to.
     */
    public void grow(List<Actor> newGrass)
    {
        // new patches of grass spawn in adjacent fields.
        // Get a list of adjacent free locations.
        Actor dirt = null;
        List<Location> free = getField().getFreeGrassLocations(getLocation());
        int grown = growPatches();
        int b =0;
        while( b < grown && free.size() > 0){
            Location loc = free.remove(0);
            dirt = new Grass(field, loc);
            newGrass.add(dirt);
            b++;
            }
    }
    
    /**
     * Generate a number representing the number of grass that can be grown.
     * @return The number of grown patches (may be zero).
     */
    protected int growPatches()
    {
        int grown = 0;
        if(rand.nextInt(100) <= growingProbability) {
            grown = rand.nextInt((maxLitterSize==0 ? maxLitterSize +1 : maxLitterSize));
        }
        return grown;
    }
    
    
    /**
     * Getter for maxLitterSize
     */
    public int getMaxLitterSize() 
    {
		return maxLitterSize;
	}

    /**
     * Setter for maxLitterSize
     */
    public void setMaxLitterSize(int maxLitterSize)
    {
    	this.maxLitterSize = maxLitterSize;
    }
	/**
	 * Getter for growingProbability
     */
    public double getGrowingProbability() 
    {
		return growingProbability;
	}

    /**
     * Setter for growingProbability
     */
    public void setGrowingProbability(int growingProbability)
    {
    	this.growingProbability = growingProbability;
    }
    
	/**
	 * Getter for location
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Getter for field
     */
    public Field getField()
    {
        return field;
    }
      
	/**
	* Place the patch of grass at the new location in the given field.
	* @param newLocation The new location of the grass.
    */
    public void setLocation(Location newLocation, boolean rabbit)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    public void setDead()
    {
        alive = false;
    }
    
    /**
     * Check whether the grass is alive or not.
     * @return true if the grass is still alive.
     */
    public boolean isAlive()
    {
        return alive;
    }
    /**
     * a method to activate the illness of rabbits.
     */
    public boolean infect()
    {
    	if(ill)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
