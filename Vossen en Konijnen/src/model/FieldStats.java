package model;
import view.Field;


/**
 * This class collects and provides some statistical data on the state 
 * of a field. 
 * 
 * @author Tycho Marinus, Cor Sloot, Mark Visscher, Luuk Blom
 */
public class FieldStats
{
    // Counters for each type of entity (fox, rabbit, etc.) in the simulation.
    //private HashMap<Class, Counter> counters;
    // Whether the counters are currently up to date.
    @SuppressWarnings("unused")
	private boolean countsValid;

    /**
     * Construct a FieldStats object.
     */
    public FieldStats()
    {
        // Set up a collection for counters for each type of animal that
        // we might find
        //counters = new HashMap<Class, Counter>();
        countsValid = true;
    }

 
    /**
     * Indicate that an animal count has been completed.
     */
    public void countFinished()
    {
        countsValid = true;
    }

    /**
     * Determine whether the simulation is still viable.
     * I.e., should it continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        // How many counts are non-zero.
        int nonZero = 0;

        if (Numbers.getRabbits() > 0 || Numbers.getFoxes() > 0 || 
        		Numbers.getWeasels() > 0){
                nonZero++;
            
        }
        return nonZero != 0;
    }
}

