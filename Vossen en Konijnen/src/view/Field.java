package view;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.AnimalNumbers;
import model.Randomizer;


/**
 * representeerd een rechthoekig raster voor Field posities.
 * elke positie heeft de mogelijkheid om een dier neer te zetten.
 * 
 * @author Bastiaan Vreijsen, Christian Hilbrands, Georg Duees
 * @version 2012.11.13
 */
public class Field
{
    // A random number generator for providing random locations.
    private static final Random rand = Randomizer.getRandom();
    //the range a hunter can shoot
    private int hunterRange;
    // The depth and width of the field.
    private int depth, width;
    // Storage for the animals.
    private Object[][] field;

    /**
     * representeerd een veld met de gegeven dementies.
     * @param depth de diepte van het veld.
     * @param width de breedte van het veld.
     */
    public Field(int depth, int width)
    {
        this.depth = depth;
        this.width = width;
        field = new Object[depth][width];
        hunterRange = AnimalNumbers.getHunterRange();
    }
    
    /**
     * leegt het veld van dieren.
     */
    public void clear()
    {
        for(int row = 0; row < depth; row++) {
            for(int col = 0; col < width; col++) {
                field[row][col] = null;
            }
        }
    }
    
    /**
     * maakt de gegeven locatie schoon.
     * @param location de locatie om schoon te maken.
     */
    public void clear(Location location)
    {
        field[location.getRow()][location.getCol()] = null;
    }
    
    /**
     * plaatst een dier op een gegeven locatie.
     * als er al een dier op de gegeven locatie staat, gaat dat dier verloren.
     * @param animal dier wat wordt geplaatst.
     * @param row rij coördinaten van de locatie.
     * @param col Colum coördinaten van de locatie.
     */
    public void place(Object animal, int row, int col)
    {
        place(animal, new Location(row, col));
    }
    
    /**
     * plaats een dier op de gegeven locatie.
     * als er al een dier op de gegeven locatie staat, gaat dat dier verloren.
     * @param animal het te plaatsen dier.
     * @param location de locatie om het dier te plaatsen.
     */
    public void place(Object animal, Location location)
    {
        field[location.getRow()][location.getCol()] = animal;
    }
    
    /**
     * Returnt het dier op de gegeven locatie als dat er is.
     * @param location waar in het veld.
     * @return het dier op de gegeven locatie of null als er geen dier is.
     */
    public Object getObjectAt(Location location)
    {
        return getObjectAt(location.getRow(), location.getCol());
    }
    
    /**
     * Returnt het dier op de gegeven locatie als dat er is.
     * @param row de gewenste rij in het veld.
     * @param col de gewenste colum in het veldThe desired column.
     * @return het dier op de gegeven locatie of null als er geen dier is.
     */
    public Object getObjectAt(int row, int col)
    {
        return field[row][col];
    }
    
    /**
     * genereer een willekeurige locatie die grenst aan de
     * gegeven locatie of dezelfde locatie
     * de returnt locatie zal binnen de geldende grenzen liggen
     * van het veld.
     * @param location de locatie.
     * @return een geldige locatie binnen het rastergebied.
     */
    public Location randomAdjacentLocation(Location location)
    {
        List<Location> adjacent = adjacentLocations(location);
        return adjacent.get(0);
    }
    
    /**
     * hier krijgt een geschude lijst van de vrije aangrenzende locaties waar gras is of het er niet toe doet.
     * @param location krijgt locaties naast dit.
     * @return een lijst van bruikbare aangrenzende locaties.
     */
    public List<Location> getFreeAdjacentLocations(Location location)
    {
        List<Location> free = new LinkedList<Location>();
        List<Location> adjacent = adjacentLocations(location);
        for(Location next : adjacent) {
        		if(getObjectAt(next) == null || getObjectAt(next) instanceof model.Grass) {
            		free.add(next);}
        }
        return free;
    }
    
    /**
     * krijg een geschudde lijst van vrije aangrenzende locaties.
     * @param location krijg locaties aangrenzend aan de locatie.
     * @return een lijst van bruikbare aangrenzende locaties.
     */
    public List<Location> getFreeGrassLocations(Location location)
    {
        List<Location> free = new LinkedList<Location>();
        List<Location> adjacent = adjacentLocations(location);
        for(Location next : adjacent) {
            if(getObjectAt(next) == null) {
                free.add(next);
            }
        }
        return free;
    }
    
    /**
     * probeer een vrije locatie te vinden aangrenzend aan de
     * gegeven locatie. als alle plaatsen bezet zijn returnt de methode null.
     * de terug gevende locatie is binnen het rastergebied.
     * @param location de locatie waarnaast wordt gegenereerd.
     * @return een geldige locatie binnen het rastergebied.
     */
    public Location freeAdjacentLocation(Location location)
    {
        // The available free ones.
        List<Location> free = getFreeAdjacentLocations(location);
        if(free.size() > 0) {
            return free.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * Returnt een geschudde lijst van locaties aangenzend aan de gegeven locatie.
     * de lijst bestaat alleen uit aangrenzende locaties.
     * alle aangrenzende locaties liggen binnen het raster.
     * @param location de locatie waarnaast wordt gegenereerd.
     * @return een lijst van aangrenzende locaties.
     */
    public List<Location> adjacentLocations(Location location)
    {
        assert location != null : "Null location passed to adjacentLocations";
        // The list of locations to be returned.
        List<Location> locations = new LinkedList<Location>();
        if(location != null) {
            int row = location.getRow();
            int col = location.getCol();
            for(int roffset = -1; roffset <= 1; roffset++) {
                int nextRow = row + roffset;
                if(nextRow >= 0 && nextRow < depth) {
                    for(int coffset = -1; coffset <= 1; coffset++) {
                        int nextCol = col + coffset;
                        // Exclude invalid locations and the original location.
                        if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
                            locations.add(new Location(nextRow, nextCol));
                        }
                    }
                }
            }
            
            // Shuffle the list. Several other methods rely on the list
            // being in a random order.
            Collections.shuffle(locations, rand);
        }
        return locations;
    }

    /**
     * Returnt een geschudde lijst van aangrenzende locaties met een radius van 2.
     * alle locaties zijn binnen het raster.
     * @param location de locatie waarnaast wordt gegenereerd.
     * @return een lijst met aangrenzende locaties.
     */
    public List<Location> adjacentTwoLocations(Location location)
    {
        assert location != null : "Null location passed to adjacentLocations";
        // The list of locations to be returned.
        List<Location> locations = new LinkedList<Location>();
        if(location != null) {
            int row = location.getRow();
            int col = location.getCol();
            for(int roffset = -hunterRange; roffset <= hunterRange; roffset++) {
                int nextRow = row + roffset;
                if(nextRow >= 0 && nextRow < depth) {
                    for(int coffset = -hunterRange; coffset <= hunterRange; coffset++) {
                        int nextCol = col + coffset;
                        // Exclude invalid locations and the original location.
                        if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
                            locations.add(new Location(nextRow, nextCol));
                        }
                    }
                }
            }
            
            // Shuffle the list. Several other methods rely on the list
            // being in a random order.
            Collections.shuffle(locations, rand);
        }
        return locations;
    }
    /**
     * Returnt de diepte van het veld(raster).
     * @return de diepte van het veld(raster).
     */
    public int getDepth()
    {
        return depth;
    }
    
    /**
     *  Returnt de breedte van het veld(raster).
     * @return de breedte van het veld(raster).
     */
    public int getWidth()
    {
        return width;
    }
    /**
     * een methode om te kijken of er gras op de locatie is of niet.
     */
    public boolean isGrass(Location location)
    {
    	if(field[location.getRow()][location.getCol()] instanceof model.Grass)
    	{
    	return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}

