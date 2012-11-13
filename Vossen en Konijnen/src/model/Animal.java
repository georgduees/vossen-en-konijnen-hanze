package model;

import java.util.List;

import view.Field;
import view.Location;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author Tycho Marinus, Cor Sloot, Mark Visscher, Luuk Blom
 */
public abstract class Animal implements Actor
{
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // The variable that keeps track if the animal is standing on grass.
    private boolean onGrass;
    
    protected int age;
    // The age at which a animal can start to breed.
    
    //The food level of an animal.
    protected int foodLevel;
    //The maximum food level an animal can have.
    protected int maxFoodLevel;
    // the age of the animal
    protected int breedingAge;
    // The age to which a animal can live.
    protected int maxAge;
    // The likelihood of a animal breeding.
    protected int breedingProbability;
    // The maximum number of births of an animal.
    protected int maxLitterSize;
    
    //This are the food values of different types of food. In effect, 
    //there are the number of steps an animal can go before it has to eat again.
    // The food value of a single rabbit. 
    protected int rabbitFoodValue = AnimalNumbers.getRabbitFoodValue(); 
    //The food value of a single young fox. 
    protected int youngFoxFoodValue = AnimalNumbers.getYoungFoxFoodValue();
    //The food level of a patch of grass.
    protected int grassFoodValue = AnimalNumbers.getGrassFoodValue();


    
    /**
     * Create a new animal at location in field.
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
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newActor A list to add newly born animals to.
     */
    abstract public void act(List<Actor> newActor);

    /**
     * Check whether the animal is alive or not.
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

