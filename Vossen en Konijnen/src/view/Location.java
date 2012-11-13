package view;
/**
 * Representeerd een locatiein een rechthoekig raster.
 * 
 * @author Bastiaan Vreijsen, Christian Hilbrands, Georg Duees
 * @version 2012.11.13
 */
public class Location
{
    // rij en colum posities.
    private int row;
    private int col;

    /**
     * Representeerd een rij en colum.
     * @param row rij.
     * @param col colum.
     */
    public Location(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    /**
     * Implementeerd inhoud equals() methode.
     */
    public boolean equals(Object obj)
    {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return row == other.getRow() && col == other.getCol();
        }
        else {
            return false;
        }
    }
    
    /**
     * Returnt een String in de form rij,colum.
     * @return een String met de locatie.
     */
    public String toString()
    {
        return row + "," + col;
    }
    
    /**
     * gebruik de top 16 bits voor de rij waardes en de onderste voor
     * de colum. behalve voor (erg) grote rasters, dit zou een
     * unieke hash code genereren voor elke rij,colum koppel
     * @return een hashcode voor de locatie.
     */
    public int hashCode()
    {
        return (row << 16) + col;
    }
    
    /**
     * @return de rij.
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * @return de colum.
     */
    public int getCol()
    {
        return col;
    }
}

