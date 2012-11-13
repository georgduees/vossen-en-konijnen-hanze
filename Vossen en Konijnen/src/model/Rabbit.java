package model;

import java.util.Iterator;
import java.util.List;

import view.Field;
import view.Location;


/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 * @version 2012.11.13
 */

public class Rabbit extends Animal
{
    //to indicate if the rabbit can get ill or not.
	private boolean illnessGen;
	//to indicate if the rabbit is ill.
    private boolean isIll;
    //A counter for the illness
	private int counter;
	/**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Rabbit(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        maxAge = AnimalNumbers.getRabbitMaxAge();
        breedingAge = AnimalNumbers.getRabbitBreedingAge();
        breedingProbability = AnimalNumbers.getRabbitBreedingProbability();
        maxLitterSize = AnimalNumbers.getRabbitMaxLitterSize();
        maxFoodLevel = AnimalNumbers.getRabbitMaxFoodLevel();
        foodLevel = rabbitFoodValue;
        if(randomAge && maxAge != 0) {
            age = rand.nextInt(maxAge);
        }
        else {
            age = 0;
        }
        if(rand.nextDouble()<0.9)
        {
        	illnessGen = true;
        }
        maxLitterSize = 5;
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to add newly born rabbits to.
     */
    public void act(List<Actor> newRabbits)
    {
        if(isIll && illnessGen && isAlive()){spreadIllness();}
    	incrementAge();
        incrementHunger();
        counter++;
        if(isAlive()) {
            giveBirth(newRabbits);            
            // Move towards a source of food if found.
            Location location = getLocation();
            Location newLocation = findFood(location);
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(location);
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation, true);
            }
            else {
                // Overcrowding.
                setDead();
                Numbers.setDeathCrowded();
            }
        }
    }
    
    /**
     * Method to find food surounding the given location
     * @param location
     * @return location that is free
     */
    private Location findFood(Location location)
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object actor = field.getObjectAt(where);
            if(actor instanceof Grass) {
                Grass grass = (Grass) actor;
                if(grass.isAlive()) { 
                	setIll(grass.infect());
                    grass.setDead();
                    foodLevel += grassFoodValue;
                    if(foodLevel > maxFoodLevel){
                    	foodLevel = maxFoodLevel;
                    }
                    // Remove the dead patch of grass from the field.
                    return where;
                }
            }
        }
        return null;
    }
    /**
     * a method to spread the Illness if it has the illness.
     */
    private void spreadIllness()
    {
    	if(isIll) {
	    	if(counter <= 1) {
		    	Field field = getField();
		    	List<Location> adjacent = field.adjacentTwoLocations(getLocation());
		        Iterator<Location> it = adjacent.iterator();
		        while(it.hasNext()) {
		            Location where = it.next();
		            Object actor = field.getObjectAt(where);
		            if(actor instanceof Rabbit) {
		                Rabbit rabbit = (Rabbit) actor;
		                if(rabbit.isAlive()) { 
		                	rabbit.setIll(true);
		                    }
		                }
		            }
		    	}
		    else {
		    	setDead();
		    	Numbers.setDeathIll();
		    	counter = 0;
		    }
    	}
    }
    /**
     * a method to turn the rabbit ill if it is possible.
     */
    public void setIll(boolean isIll)
    {
    	if(!this.isIll)
    	{
    		this.isIll = isIll;
    	}
    }
}

