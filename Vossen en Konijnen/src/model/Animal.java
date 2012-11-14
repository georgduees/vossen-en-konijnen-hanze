package model;

import java.util.List;

import view.Field;
import view.Location;

/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 * @version 2012.11.13
 */

public abstract class Animal implements Actor
{
    // geeft aan of het dier nog leeft.
    private boolean alive;
    // veld van het dier.
    private Field field;
    // locatie op het veld van het dier.
    private Location location;
    // houd bij of het dier op gras staat.
    private boolean onGrass;
    // de leeftijd waar op het dier kan gaan voortplanten.
    protected int age;
    
    //het voedselnieau van het dier.
    protected int foodLevel;
    //het maximum voedselniveaw van het dier.
    protected int maxFoodLevel;
    // leeftijd van het dier
    protected int breedingAge;
    // de maximale leeftijd van het dier.
    protected int maxAge;
    // voortplantingskans.
    protected int breedingProbability;
    // maximum aantal jongen die een dier kan krijgen.
    protected int maxLitterSize;
    
    //dit zijn de waarden van de verschillende voedellevels, 
    //hier zijn het aantal stappen die een dier moet maken voor het weer moet eten.
    // de eetwaarde van een konijn. 
    protected int rabbitFoodValue = AnimalNumbers.getRabbitFoodValue(); 
    //de eetwaarde van een vos. 
    protected int youngFoxFoodValue = AnimalNumbers.getYoungFoxFoodValue();
    //de voedselwaarde van een stuk gras.
    protected int grassFoodValue = AnimalNumbers.getGrassFoodValue();


    
    /**
     * maakt een nieuw dier aan op een locatie in het veld.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location, false);
    }
    
    /**
     * zorgt er voor dat het dier wat doet
     * @param newActor A list to add newly born animals to.
     */
    abstract public void act(List<Actor> newActor);

    /**
     * kijkt of het dier nog leeft.
     * @return true if the animal is still alive.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    public void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
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
     * Place the animal at the new location in the given field.
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
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * increase the age
     * can result in animals death
     */
    protected void incrementAge()
    {
        this.age++;
        if(this.age > getMaxAge()) {
            setDead();
        }
    }
    
    /**
     * Check whether or not this animal is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newAnimals A list to add newly born rabbits to.
     */
    protected void giveBirth(List<Actor> newAnimals)
    {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        Animal young = null;
        List<Location> free = getField().getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            char type = this.getClass().getName().charAt(6);
            switch (type){
                case 'R' :
                    young = new Rabbit(false, field, loc);
                    Numbers.setRabbits(1);
                    break;
                case 'F' :  
                    young = new Fox(false, field, loc);
                    Numbers.setFoxes(1);
                    break;
                case 'W' :
                	young = new Weasel(false, field, loc);
                	Numbers.setWeasels(1);
                	break;
                default :
                    System.out.println("Unknown animal cant give birth.");
                    break;
            }
            newAnimals.add(young);
        }
    }
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextInt(100) <= getBreedingProbability()) {
            births = rand.nextInt((maxLitterSize==0 ? maxLitterSize +1 : maxLitterSize));
        }
        return births;
    }
    
    /**
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    protected boolean canBreed()
    {
        return getAge() >= getBreedingAge();    
    }
    
    /**
     * Make this animal more hungry. This could result in the animals death.
     */
    protected void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
            Numbers.setDeathStarvation();
        }
    }
    
    /**
     * Getter for foodLevel
     */
    public int getFoodLevel()
    {
    	return foodLevel;
    }
    
    /**
     * Setter for foodLevel
     */
    public void setFoodLevel(int foodLevel)
    {
    	this.foodLevel = foodLevel;
    }
    
    /**
     * Getter for maxFoodLevel.
     */
    public int getMaxFoodLevel()
    {
    	return maxFoodLevel;
    }
    
    /**
     * Setter for maxFoodLevel
     */
    public void setMaxFoodLevel(int maxFoodLevel)
    {
    	this.maxFoodLevel = maxFoodLevel;
    }
    
    /**
     * Getter for age
     */
    public int getAge()
    {
        return this.age;
    }
    
    /**
     * Setter for age
     */
    public void setAge(int age)
    {
    	this.age =age;
    }
    
    /**
     * Getter for breedingAge
     */
    public int getBreedingAge()
    {
        return breedingAge;
    }
    
    /**
     * Setter for breedingAge
     */
    public void setBreedingAge(int breedingAge)
    {
    	this.breedingAge = breedingAge;
    }
    
    /**
     * Getter for maxAge
     */
    public int getMaxAge()
    {
        return maxAge;
    }
    
    /**
     * Setter for maxAge
     */
    public void setMaxAge(int maxAge)
    {
    	this.maxAge = maxAge;
    }
    
    /**
     * Getter for breedingProbability
     */
    public int getBreedingProbability()
    {
        return breedingProbability;
    }
    
    /**
     * Setter for breedingProbability
     */
    public void setBreedingProbability(int breedingProbability)
    {
    	this.breedingProbability = breedingProbability;
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
     * Getter for rabbitFoodValue
     */
    public int getRabbitFoodValue()
    {
    	return rabbitFoodValue;
    }
    
    /**
     * Setter for rabbitFoodValue
     */
    public void setRabbitfoodValue(int rabbitFoodValue)
    {
    	this.rabbitFoodValue = rabbitFoodValue;
    }
    
    /**
     * Getter for youngFoxFoodValue
     */
    public int getYoungFoxFoodValue()
    {
    	return youngFoxFoodValue;
    }
    
    /**
     * Setter for youngFoxFoodValue
     */
    public void setYoungFoxFoodValue(int youngFoxFoodValue)
    {
    	this.youngFoxFoodValue = youngFoxFoodValue;
    }
    
    /**
     * Getter for grassFoodValue
     */
    public int getGrassFoodValue()
    {
    	return grassFoodValue;
    }
    
    /**
     * Setter for grassFoodValue
     */
    public void setGrassFoodValue(int grassFoodValue)
    {
    	this.grassFoodValue = grassFoodValue;
    }
}

