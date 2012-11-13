package model;

import java.util.Iterator;
import java.util.List;

import view.Field;
import view.Location;
/**
 * A simple model of a weasel.
 * Weasels age, move, breed, and die.
 * 
 * @author Tycho Marinus, Cor Sloot, Mark Visscher, Luuk Blom
 * @version 27-01-2010
 */
public class Weasel extends Animal 
{
    //The age at which a weasel can eat a young fox
    private int weaselEatingFoxAge;
    /**
     * Create a weasel. A weasel can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the weasel will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Weasel(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        maxAge = AnimalNumbers.getWeaselMaxAge();
        breedingAge = AnimalNumbers.getWeaselBreedingAge();
        breedingProbability = AnimalNumbers.getWeaselBreedingProbability();
        maxLitterSize = AnimalNumbers.getWeaselMaxLitterSize();
        maxFoodLevel = AnimalNumbers.getWeaselMaxFoodLevel();
        weaselEatingFoxAge = AnimalNumbers.getWeaselEatingFoxAge();
        foodLevel = rabbitFoodValue;
        if(randomAge && maxAge != 0) {
            age = rand.nextInt(maxAge);
        }
        else {
            age = 0;
        }
    }
    
    /**
     * This is what the weasel does most of the time: it hunts for
     * rabbits or young foxes. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newWeasels A list to add newly born weasels to.
     */
    public void act(List<Actor> newWeasels)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newWeasels);            
            // Move towards a source of food if found.
            Location location = getLocation();
            Location newLocation = findFood(location);
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
                Numbers.setDeathCrowded();
            }
        }
    }

    /**
     * Tell the weasel to look for rabbits or young foxes adjacent to its 
     * current location.
     * Only the first live rabbit or young fox is eaten.
     * @param location Where in the field it is located.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood(Location location)
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
            	Rabbit rabbit = (Rabbit) animal;
            	if(rabbit.isAlive()) { 
            		rabbit.setDead();
            		Numbers.setDeathEaten();
            		foodLevel += rabbitFoodValue;  
            	}
            }
            else if(animal instanceof Fox) {
            	Fox fox = (Fox) animal;
            	if((fox.getAge() <= weaselEatingFoxAge) && (fox.isAlive())) {
            		fox.setDead();
            		Numbers.setDeathEaten();
            		foodLevel += youngFoxFoodValue;
            	}
            }
            if(foodLevel > maxFoodLevel){
            	foodLevel = maxFoodLevel;
            }
        }
        return null;
    }
    
    /**
     * Getter for weaselEatingFoxAge
     */
    public int getWeaselEatingFoxAge()
    {
    	return weaselEatingFoxAge;
    }
    
    /**
     * Setter for weaselEatingFoxAge
     */
    public void setWeaselEatingFoxAge(int weaselEatingFoxAge)
    {
    	this.weaselEatingFoxAge = weaselEatingFoxAge;
    }
}
