package model;


import java.util.List;
import java.util.Random;

import view.Location;

/**
 * A class representing shared characteristics of Hunters and Animals.
 * 
 * @author Tycho Marinus, Cor Sloot, Mark Visscher, Luuk Blom
 * 
 */
public interface Actor {
	
    // A shared random number generator to control breeding.
    public static final Random rand = Randomizer.getRandom();
	/**
     * Make this actor act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to add newly born animals to.
     */
    abstract public void act(List<Actor> newActor);

    /**
     * to check if the actor is alive or active.
     * @return boolean true if alive/active, false otherwise.
     */
    abstract public boolean isAlive();
    /**
     * to set a actor dead/inactive
     * inactive actors will be removed from the field.
     */
    abstract public void setDead();
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    abstract public void setLocation(Location newLocation, boolean rabbit);
}
