package model;


import java.util.List;
import java.util.Random;

import view.Location;

/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 * @version 2012.11.13
 */

public interface Actor {
	
    // een gedeelde nummer generator voor het voortplanten.
    public static final Random rand = Randomizer.getRandom();
	/**
     *zorgt er voor dat de actor doet wat hij moet doen.
     * @param newAnimals A list to add newly born animals to.
     */
    abstract public void act(List<Actor> newActor);

    /**
     * dit checkt of de actor actief is
     * @return boolean true if alive/active, false otherwise.
     */
    abstract public boolean isAlive();
    /**
     * om een actor niet actief te zetten
     * inactive actors will be removed from the field.
     */
    abstract public void setDead();
    /**
     * plaatst een dier op het veld op de aangegeven locatie
     * @param newLocation The animal's new location.
     */
    abstract public void setLocation(Location newLocation, boolean rabbit);
}
