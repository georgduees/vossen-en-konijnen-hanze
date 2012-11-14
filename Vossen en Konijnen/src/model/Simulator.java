package model;


import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import controller.*;



import view.Field;
import view.Location;

import view.SimulatorView;



/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 * @version 2012.11.13
 */

public class Simulator implements Runnable 
{
    //The probability that a weasel will be created in any given grid position.
    private int weaselCreationProbability;
    // The probability that a fox will be created in any given grid position.
   	private int foxCreationProbability;
    // The probability that a rabbit will be created in any given grid position.
	private int rabbitCreationProbability;
    //The probability that grass will be created at a given position.
	private int grassCreationProbability;
    // The probability that a hunter will be created in any given grid position.
 	private int hunterCreationProbability;
    // the maximum of hunter's allowed in a simulation.
 	private int maxHuntersAllowed;
    

    
    // List of animals in the field.
    private List<Actor> animals;
    // The current state of the field.
    private Field field;
    // A graphical view of the simulation.
    private SimulatorView view;
    // A variable for frame
    private HeadFrame frame;
    private int numSteps;
    private Thread thread;
    private int viewSpeed = 90;
    // The music during simulating
    private Sound music = new Sound(getClass().getResource("Brightbeat.wav"));
    private Sound intro = new Sound(getClass().getResource("welcome.wav"));  
    private boolean go = true;
    // an internal counter to check if the maximum of the number of hunters is reached.
    private int numberOfHunters;
    private Diagram diagram;
    private boolean keepGoing = false;
    // The mute variable.
    private boolean mute;
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
    	this(AnimalNumbers.getFieldDepth(), AnimalNumbers.getFieldWidth());
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
    	
    	intro.playSoundOnce();
    	
    	
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = AnimalNumbers.getFieldDepth();
            width = AnimalNumbers.getFieldWidth();
        }
        new Numbers();
        animals = new ArrayList<Actor>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        view.setColor(Grass.class, Color.green);
        view.setColor(Rabbit.class, Color.orange);
        view.setColor(Fox.class, Color.blue);
        view.setColor(Weasel.class, Color.red);
        view.setColor(Hunter.class, Color.black);
        frame = new HeadFrame(view, this);
        diagram = frame.getDiagram();
        
        // Setup a valid starting point.
        reset();
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
    	keepGoing = false;
    	go = true;
    	this.numSteps = numSteps;
    	thread = new Thread(this);
    	thread.start();
    	
    	
    }
    
    
    
    /**
     * method to be called to keep running
     */
    @SuppressWarnings("static-access")
	public void step()
    {
    	synchronized (animals){

        	simulateOneStep();

            	}
            try {
				thread.sleep(viewSpeed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (thread != null){
			synchronized (thread) {
				while(! go) {
					try {
						thread.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
        }
	    diagram.add();

    }
    
    /**
     * run method thats called by thread start
     * Also allows thread to be waited if go is false.
     */
    public void run()
    {
        int step = 1; 
        Thread thisThread = Thread.currentThread();
        if (! keepGoing){
        	while (go && view.isViable(field) && step <= numSteps && thread == thisThread) {
        		step();
        		step++;
        	}
        	music.stopSound();
        }
        else if (keepGoing){
        	while (keepGoing && go && view.isViable(field) && thread == thisThread) {
        		step();
        	}
        	music.stopSound();
        }
    }
    
    /**
     * method to allow to pause the simulator.
     */
    public void startPause()
    {
    	if (thread != null){
    		synchronized (thread){
    			if (go) {
    				go = false;
    				
    			}
    			else if (!go){
    				go = true;
    				thread.notify();
    				
    			}
    		}
    	}
    }
    
    /**
     * Method to start simulator until method is called again.
     */
    public void keepGoing()
    {
    	if (! keepGoing) {
    		go = true;
    		keepGoing = true;
    		thread = new Thread(this);
    		thread.start();
    		
    		
    	}
    	else if (keepGoing) {
    		keepGoing = false;
    		
    	}
    }
    

    
    /**
     * setter for the run speed
     */
    public void setRunSpeed(int speed)
    {
    	viewSpeed = speed;
    }
    /**
     * method to get run speed
     */
    public int getRunSpeed()
    {
    	return viewSpeed;
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep()
    {
        Numbers.addStep();

        // Provide space for newborn animals.
        List<Actor> newAnimals = new ArrayList<Actor>();        
        // Let all rabbits act.
        synchronized(thread){
        	Iterator<Actor> it = animals.iterator(); 
        	while(it.hasNext()) {
        			Actor animal = it.next(); 
	            	animal.act(newAnimals);
	            	if(! animal.isAlive()) {
	            		if(animal instanceof Fox){
	            			Numbers.setFoxesDeaths(1);
	            		}
	            		if(animal instanceof Rabbit){
	            			Numbers.setRabbitsDeaths(1);
	            		}
	            		if(animal instanceof Weasel){
	            			Numbers.setWeaselsDeaths(1);
	            		}
	            		if(animal instanceof Hunter){
	            			Numbers.setHuntersDeaths(1);
	            		}
	            		it.remove();
	            	}
            		//Numbers.stepLists();
        			//diagram.add();

        		
        	}
        }

               
        // Add the newly born foxes and rabbits to the main lists.
        animals.addAll(newAnimals);

        view.showStatus(field);
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
    	this.numSteps = 0;
    	startPause();
    	diagram.reset();
        numberOfHunters = 0;
        animals.clear();
    	Numbers.reset();
        populate();
        go = true;
        keepGoing = false;
      
        
        // Show the starting state in the view.
        view.showStatus(field);
    }

    
    /**
     * Randomly populate the field with foxes and rabbits and weasels.
     */
    private void populate()
    {
        //Get the probability's that an animal spawns at a certain location
    	weaselCreationProbability = AnimalNumbers.getWeaselCreationProbability();
    	foxCreationProbability = AnimalNumbers.getFoxCreationProbability();
    	rabbitCreationProbability = AnimalNumbers.getRabbitCreationProbability();
    	grassCreationProbability = AnimalNumbers.getGrassCreationProbability();
    	hunterCreationProbability = AnimalNumbers.getHunterCreationProbability();

    	//Get the maximum number of hunters that is allowed in a simulation
        maxHuntersAllowed = AnimalNumbers.getMaxHuntersAllowed();

        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
            	  	if( rand.nextInt(100) < weaselCreationProbability){
                    	Location location = new Location(row, col);
                    	Weasel weasel = new Weasel(true, field, location);
                    	animals.add(weasel);
                    	Numbers.setWeasels(1);
                    	//weaselsBorn++; //counter to keep track how many weasels are created with populate();
                	}
                	else if(rand.nextInt(100) < foxCreationProbability) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    animals.add(fox);
                    Numbers.setFoxes(1);
                    //foxesBorn++; //counter to keep track how many foxes are created with populate();
                   }
                	else if(rand.nextInt(100) < rabbitCreationProbability) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    animals.add(rabbit);
                    Numbers.setRabbits(1);
                    //rabbitsBorn++; //counter to keep track how many rabbits are created with populate();
                
                	}
                	else if(rand.nextInt(100) < grassCreationProbability){
                    Location location = new Location(row, col);
                    Grass grass = new Grass(field, location);
                    animals.add(grass);
                    Numbers.setGrass(1);

                	}
                	else if(rand.nextInt(100) < hunterCreationProbability){
                		if(numberOfHunters < maxHuntersAllowed){
                    		Location location = new Location(row, col);
                    		Hunter hunter = new Hunter(field, location);
                    		animals.add(hunter);
                    		numberOfHunters++;
                    		Numbers.setHunters(1);
                    		}
                    	
                    	}
                // else leave the location empty.
            }
        }
        // add populated animals the step 0 in numbers.
        Numbers.startLists();
        diagram.add();
        
    }
}

