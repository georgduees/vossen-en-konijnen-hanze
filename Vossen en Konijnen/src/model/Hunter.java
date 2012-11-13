package model;

import java.util.Iterator;

import java.util.List;

import view.Field;
import view.Location;
/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 * @version 2012.11.13
 */

public class Hunter implements Actor {
	//int that decides how many kills the hunter makes.
    private  int hunterKillSteps;
    
    // Individual characteristics (instance fields).
    //a boolean that indicates if a Hunter is active.
	public boolean isActive;
    //the location
    private Location location;
    //the field
    private Field field;
    //boolean to check whether the hunter is on grass.
    private boolean onGrass;
	
    public Hunter(Field field, Location location)
    {
    	this.field = field;
        setLocation(location, false);
        isActive = true;
        hunterKillSteps = AnimalNumbers.getHunterKillSteps();
    }
    
    /**
     * Method that makes the hunter act.
     */
	public void act(List<Actor> newActor) 
    {
        if(isAlive()){            
            // Move towards a source of food if found.
            Location location = getLocation();
            Location where = null;
            int i=0;
            while (i<hunterKillSteps)
            {
            	where = findFood(location);
            	i++;
            }
            Location newLocation = where;
            
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(location);
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation, false);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }
    
    /**
     * Indicate that the hunter is no longer alive.
     * It is removed from the field.
     */
    public void setDead()
    {
    	/*
        isActive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
        */
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    public Field getField()
    {
        return field;
    }
    
        
        /**
         * Place the hunter at the new location in the given field.
         * @param newLocation The animal's new location.
         */
    public void setLocation(Location newLocation, boolean rabbit)
    {
    	if(location != null) {
            field.clear(location);
            if(onGrass && !rabbit)
            {
            	new Grass(field, location);
            }
        }
        if (field.isGrass(newLocation) && !rabbit)
        {
        	onGrass = true;
        }
        else
        {
        	onGrass = false;
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    /**
     * Tell the Hunter to look for rabbits & foxes adjacent to its current location.
     * Only the first live rabbit | fox is shot.
     * @param location Where in the field it is located.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood(Location location)
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentTwoLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit || animal instanceof Fox || animal instanceof Weasel) {
                if(animal instanceof Rabbit)
                {
                   Rabbit rabbit = (Rabbit) animal;
                    if(rabbit.isAlive()) 
                    { 
                        rabbit.setDead();
                    	Numbers.setDeathShot();

                        // Remove the dead rabbit from the field.
                        return where;
                    }
                }
                else if(animal instanceof Weasel)
                {
                	Weasel weasel = (Weasel) animal;
                	if(weasel.isAlive())
                	{
                		weasel.setDead();
                    	Numbers.setDeathShot();

                		// Remove the dead weasel from the field.
                		return where;
                    }
                }
                else if(animal instanceof Fox)
                {
                	Fox fox = (Fox) animal;
                	if(fox.isAlive())
                	{
                		fox.setDead();
                    	Numbers.setDeathShot();

                		// Remove the dead fox from the field.
                		return where;
                    }
                }          
            }
        }
        return null;
    }
    
    /**
     * Method that checks if the hunter is still active.
     */
    public boolean isAlive()
    {
    	return isActive;
    }
    
    /**
     * Getter for hunterKillSteps
     */
    public int getHunterKillSteps()
    {
    	return hunterKillSteps;
    }
    
    /**
     * Setter for hunterKillSteps
     */
    public void setHunterKillSteps(int hunterKillSteps)
    {
    	this.hunterKillSteps = hunterKillSteps;
    }
}
