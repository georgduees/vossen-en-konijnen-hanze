package model;

import java.util.List;
import java.util.Iterator;

import view.Field;
import view.Location;


/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 * @version 2012.11.13
 */

public class Fox extends Animal
{
    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Fox(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        breedingAge = AnimalNumbers.getFoxBreedingAge();
        maxAge = AnimalNumbers.getFoxMaxAge();
        breedingProbability = AnimalNumbers.getFoxBreedingProbability();
        maxLitterSize = AnimalNumbers.getFoxMaxLitterSize();
        maxFoodLevel = AnimalNumbers.getFoxMaxFoodLevel();
        foodLevel = rabbitFoodValue;
        if(randomAge && maxAge != 0) {
            age = rand.nextInt(maxAge);
        }
        else {
            age = 0;
        }
    }
    
    /**
     * This is what the fox does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newFoxes A list to add newly born foxes to.
     */
    public void act(List<Actor> newFoxes)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newFoxes);            
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
     * Tell the fox to look for rabbits adjacent to its current location.
     * Only the first live rabbit is eaten.
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
                    foodLevel = rabbitFoodValue;
                    if(foodLevel > maxFoodLevel){
                    	foodLevel = maxFoodLevel;
                    }
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
        }
        return null;
    }
}

