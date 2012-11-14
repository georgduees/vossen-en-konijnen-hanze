package view;


import java.awt.*;
import javax.swing.*;

import model.FieldStats;
import model.Numbers;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * een grafische weergave van het simulatie raster.
 * het raster laat gekleurde rechthoekige vak zien voor elke locatie
 * wat de inhoud representeerd. er is een standaard achtergrond kleur ingesteld.
 * elk type dier heeft een eigen kleur en die kan gedefineerd worden met de
 * setColor methode.
 * 
 * @author Bastiaan Vreijsen, Christian Hilbrands, Georg Duees
 * @version 2012.11.13
 */
public class SimulatorView
{
    // kleur gebruikt voor een lege locatie.
    private static final Color EMPTY_COLOR = Color.white;

    // kleur gebruikt voor objecten die geen voorgedefineerde kleur.
    private static final Color UNKNOWN_COLOR = Color.gray;

    private final String STEP_PREFIX = "Step: ";
    private JLabel stepLabel;
    private FieldView fieldView;
    
    // Een kaart voor het opslaan van kleuren voor de deelnemers in de simulatie
    private Map<Class, Color> colors;
    // A statistics object computing and storing simulation information
    private FieldStats stats;
    private JPanel viewPanel;
    /**
     * creëert een representatie met de gegeven breedte en hoogte.
     * @param height de simulator's hoogte.
     * @param width  de simulator's breedte.
     */
    public SimulatorView(int height, int width)
    {
        stats = new FieldStats();
        colors = new LinkedHashMap<Class, Color>();

        stepLabel = new JLabel(STEP_PREFIX, JLabel.CENTER);
        
        
        fieldView = new FieldView(height, width);
        
        setViewPanel(new JPanel(new BorderLayout()));
        getViewPanel().add(stepLabel, BorderLayout.NORTH);
        getViewPanel().add(fieldView, BorderLayout.CENTER);
    }
    
    /**
     * defineerd een kleur wat wordt gebruikt voor de klasse van de dier.
     * @param animalClass het klasse object van het dier.
     * @param color kleur voor de gegeven klasse object.
     */
    public void setColor(Class animalClass, Color color)
    {
        colors.put(animalClass, color);
    }

    /**
     * @return de kleur gebruikt voor de gegeven dier klasse.
     */
    private Color getColor(Class animalClass)
    {
        Color col = colors.get(animalClass);
        if(col == null) {
            // no color defined for this class
            return UNKNOWN_COLOR;
        }
        else {
            return col;
        }
    }

    /**
     * geeft de status van het veld weer.
     * @param step welke iterator stap het is.
     * @param field het veld waarvan de status wordt weergegeven.
     */
    public void showStatus(Field field)
    {
            
        stepLabel.setText(STEP_PREFIX + Numbers.getSteps() + " Total deaths: " + Numbers.getTotalDeaths());
        
        fieldView.preparePaint();

        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    fieldView.drawMark(col, row, getColor(animal.getClass()));
                }
                else {
                    fieldView.drawMark(col, row, EMPTY_COLOR);
                }
            }
        }
        stats.countFinished();

        fieldView.repaint();
    }

    /**
     * stelt vast of de simulatie door moet gaan.
     * @return true als er meer dan 1 soort wezen levendig is.
     */
    public boolean isViable(Field field)
    {
        return stats.isViable(field);
    }
    
    /**
     * Setter voor viewPanel
     * @param viewPanel
     */
    public void setViewPanel(JPanel viewPanel) {
		this.viewPanel = viewPanel;
	}

    /**
     * getter voor viewPanel
     * @return viewpanel
     */
	public JPanel getViewPanel() {
		return viewPanel;
	}

	/**
     * zorgt voor een grafische representatie van een rechthoekig veld. dit is 
     * een genestelde klasse(een klasse in een klasse gedefineerd) wat een 
     * op maat gemaakte component voor de gebruikers interface defineerd. deze
     * component weergeeft het veld.
     */
    private class FieldView extends JPanel
    {
        private final int GRID_VIEW_SCALING_FACTOR = 6;

        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image fieldImage;

        /**
         * creërt een nieuwe FieldView component.
         */
        public FieldView(int height, int width)
        {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * zeg tegen de GUI manager hoe groot het moet zijn.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
        }

        /**
         * prepareer het veld voor de volgende ronde. sinds het component
         * resizeble is, moet de pc opnieuw de hoeken berekenen.
         */
        public void preparePaint()
        {
            if(! size.equals(getSize())) {  // if the size has changed...
                size = getSize();
                fieldImage = fieldView.createImage(size.width, size.height);
                g = fieldImage.getGraphics();

                xScale = size.width / gridWidth;
                if(xScale < 1) {
                    xScale = GRID_VIEW_SCALING_FACTOR;
                }
                yScale = size.height / gridHeight;
                if(yScale < 1) {
                    yScale = GRID_VIEW_SCALING_FACTOR;
                }
            }
        }

        /**
         * kleur de raster locatie voor dit veld in de gegeven kleur.
         */
        public void drawMark(int x, int y, Color color)
        {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
        }

        /**
         * het veld weergave component moet opnieuw weergegeven. kopieër de
         * interne afbeelding tot het scherm.
         */
        public void paintComponent(Graphics g)
        {
            if(fieldImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(fieldImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
}

