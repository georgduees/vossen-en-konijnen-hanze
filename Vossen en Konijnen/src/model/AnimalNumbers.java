package model;


/**
 *@author Bastiaan Vreijsen, Christiaan Hilbrands, Georg Duees
 * @version 2012.11.13
 */

public final class AnimalNumbers 
{
	//Parameters for the fox.
	private static int foxBreedingAge = 10;
	private static int foxMaxAge = 150;
	private static int foxBreedingProbability = 15;
	private static int foxMaxLitterSize = 3;
	private static int foxMaxFoodLevel = 20;
	
	//Parameters of the rabbit
	private static int rabbitBreedingAge = 5;
	private static int rabbitMaxAge = 40;
	private static int rabbitBreedingProbability = 11;
	private static int rabbitMaxLitterSize = 5;
	private static int rabbitMaxFoodLevel = 10;
	
	//Parameters of the weasel
	private static int weaselBreedingAge = 12;
	private static int weaselMaxAge = 100;
	private static int weaselBreedingProbability = 5;
	private static int weaselMaxLitterSize = 3;
	private static int weaselMaxFoodLevel = 15;
	private static int weaselEatingFoxAge = 7;
	
	//Parameters of grass
	private static int grassGrowingProbability = 9;
	private static int grassMaxLitterSize = 3;
	private static int grassIllnessProbability = 1;

	
	//Parameters of the hunter
	private static int hunterKillSteps = 2;
	private static int hunterRange = 3;
	private static int maxHuntersAllowed = 5;
	
	//Parameters of the different types of food.
	private static int youngFoxFoodValue = 5;
	private static int rabbitFoodValue = 7;
	private static int grassFoodValue= 3;
	
	//Parameters for the creation of the field
	private static int fieldWidth = 50;
	private static int fieldDepth = 50;
	
	//Parameters for the population of the field
    private static int weaselCreationProbability = 1;
    private static int foxCreationProbability = 2;
    private static int rabbitCreationProbability = 3;
    private static int grassCreationProbability = 5;
    private static int hunterCreationProbability = 8;
    
    // the mute variable
    private static boolean mute = true;
    //No constructor for this final class
    
    /**
     * getter for mute
     */
    public static boolean getMute()
    {
    	return mute;
    }
    /**
     * setter for mute
     */
    public static void setMute(boolean mute)
    {
    	AnimalNumbers.mute = mute;
    }
     /**
	 * Getter for foxBreedingAge
	 */
	public static int getFoxBreedingAge()
	{
		return foxBreedingAge;
	}
	/**
	 * Setter for foxBreedingAge
	 */
	public static void setFoxBreedingAge(int foxBreedingAge)
	{
		AnimalNumbers.foxBreedingAge = foxBreedingAge;
	}
	
	/**
	 * Getter for foxMaxAge
	 */
	public static int getFoxMaxAge()
	{
		return foxMaxAge;
	}
	/**
	 * Setter for foxMaxAge
	 */
	public static void setFoxMaxAge(int foxMaxAge)
	{
		AnimalNumbers.foxMaxAge = foxMaxAge;
	}
	
	/**
	 * Getter for foxBreedingProbability
	 */
	public static int getFoxBreedingProbability()
	{
		return foxBreedingProbability;
	}
	/**
	 * Setter for foxBreedingProbability
	 */
	public static void setFoxBreedingProbability(int foxBreedingProbability)
	{
		AnimalNumbers.foxBreedingProbability = foxBreedingProbability;
	}
	
	/**
	 * Getter for foxMaxLitterSize
	 */
	public static int getFoxMaxLitterSize()
	{
		return foxMaxLitterSize;
	}
	/**
	 * Setter for foxMaxLitterSize
	 */
	public static void setFoxMaxLitterSize(int foxMaxLitterSize)
	{
		AnimalNumbers.foxMaxLitterSize = foxMaxLitterSize;
	}
	
	/**
	 * Getter for foxMaxfoodLevel
	 */
	public static int getFoxMaxFoodLevel()
	{
		return foxMaxFoodLevel;
	}
	/**
	 * Setter for foxMaxFoodLevel
	 */
	public static void setFoxMaxFoodLevel(int foxMaxFoodLevel)
	{
		AnimalNumbers.foxMaxFoodLevel = foxMaxFoodLevel;
	}
	
	
	/**
	 * Getter rabbitBreedingAge
	 */
	public static int getRabbitBreedingAge() 
	{
		return rabbitBreedingAge;
	}
	
	/**
	 * Setter rabbitBreedingAge
	 */
	public static void setRabbitBreedingAge(int rabbitBreedingAge) 
	{
		AnimalNumbers.rabbitBreedingAge = rabbitBreedingAge;
	}
	
	/**
	 * Getter for rabbitMaxAge
	 */
	public static int getRabbitMaxAge()
	{
		return rabbitMaxAge;
	}
	/**
	 * Setter for rabbitMaxAge
	 */
	public static void setRabbitMaxAge(int rabbitMaxAge)
	{
		AnimalNumbers.rabbitMaxAge = rabbitMaxAge;
	}
	
	/**
	 * Getter for rabbitBreedingProbability
	 */
	public static int getRabbitBreedingProbability()
	{
		return rabbitBreedingProbability;
	}
	/**
	 * Setter for rabbitBreedingProbability
	 */
	public static void setRabbitBreedingProbability(int rabbitBreedingProbability)
	{
		AnimalNumbers.rabbitBreedingProbability = rabbitBreedingProbability;
	}
	
	/**
	 * Getter for rabbitMaxLitterSize
	 */
	public static int getRabbitMaxLitterSize()
	{
		return rabbitMaxLitterSize;
	}
	/**
	 * Setter for rabbitMaxLitterSize
	 */
	public static void setRabbitMaxLitterSize(int rabbitMaxLitterSize)
	{
		AnimalNumbers.rabbitMaxLitterSize = rabbitMaxLitterSize;
	}
	
	/**
	 * Getter for rabbitMaxfoodLevel
	 */
	public static int getRabbitMaxFoodLevel()
	{
		return rabbitMaxFoodLevel;
	}
	/**
	 * Setter for rabbitMaxFoodLevel
	 */
	public static void setRabbitMaxFoodLevel(int rabbitMaxFoodLevel)
	{
		AnimalNumbers.rabbitMaxFoodLevel = rabbitMaxFoodLevel;
	}
	
	
	/**
	 * Getter for weaselBreedingAge
	 */
	public static int getWeaselBreedingAge()
	{
		return weaselBreedingAge;
	}
	/**
	 * Setter for weaselBreedingAge
	 */
	public static void setWeaselBreedingAge(int weaselBreedingAge)
	{
		AnimalNumbers.weaselBreedingAge = weaselBreedingAge;
	}
	
	/**
	 * Getter for weaselMaxAge
	 */
	public static int getWeaselMaxAge()
	{
		return weaselMaxAge;
	}
	/**
	 * Setter for weaselMaxAge
	 */
	public static void setWeaselMaxAge(int weaselMaxAge)
	{
		AnimalNumbers.weaselMaxAge = weaselMaxAge;
	}
	
	/**
	 * Getter for foxBreedingProbability
	 */
	public static int getWeaselBreedingProbability()
	{
		return weaselBreedingProbability;
	}
	/**
	 * Setter for weaselBreedingProbability
	 */
	public static void setWeaselBreedingProbability(int weaselBreedingProbability)
	{
		AnimalNumbers.weaselBreedingProbability = weaselBreedingProbability;
	}
	
	/**
	 * Getter for weaselMaxLitterSize
	 */
	public static int getWeaselMaxLitterSize()
	{
		return weaselMaxLitterSize;
	}
	/**
	 * Setter for weaselMaxLitterSize
	 */
	public static void setWeaselMaxLitterSize(int weaselMaxLitterSize)
	{
		AnimalNumbers.weaselMaxLitterSize = weaselMaxLitterSize;
	}
	
	/**
	 * Getter for weaselMaxfoodLevel
	 */
	public static int getWeaselMaxFoodLevel()
	{
		return weaselMaxFoodLevel;
	}
	/**
	 * Setter for weaselMaxFoodLevel
	 */
	public static void setWeaselMaxFoodLevel(int weaselMaxFoodLevel)
	{
		AnimalNumbers.weaselMaxFoodLevel = weaselMaxFoodLevel;
	}
	
	/**
	 * Getter for hunterKillSteps
	 */
	public static int getHunterKillSteps()
	{
		return hunterKillSteps;
	}
	/**
	 * Setter for hunterKillSteps
	 */
	public static void setHunterKillSteps(int hunterKillSteps)
	{
		AnimalNumbers.hunterKillSteps = hunterKillSteps;
	}
	
	/**
	 * Getter for maxHuntersAllowed
	 */
	public static int getMaxHuntersAllowed()
	{
		return maxHuntersAllowed;
	}
	/**
	 * Setter for maxHuntersAllowed
	 */
	public static void setMaxHuntersAllowed(int maxHuntersAllowed)
	{
		AnimalNumbers.maxHuntersAllowed = maxHuntersAllowed;
	}
	
	/**
	 * Getter for hunterRange
	 */
	public static int getHunterRange()
	{
		return hunterRange;
	}
	/**
	 * Setter for hunterRange
	 */
	public static void setHunterRange(int hunterRange)
	{
		AnimalNumbers.hunterRange = hunterRange;
	}
	
	/**
	 * Getter for youngFoxFoodValue
	 */
	public static int getYoungFoxFoodValue()
	{
		return youngFoxFoodValue;
	}
	/**
	 * Setter for youngFoxFoodValue
	 */
	public static void setYoungFoxFoodValue(int youngFoxFoodValue)
	{
		AnimalNumbers.youngFoxFoodValue = youngFoxFoodValue;
	}
	
	/**
	 * Getter for rabbitFoodValue
	 */
	public static int getRabbitFoodValue()
	{
		return rabbitFoodValue;
	}
	/**
	 * Setter for rabbitFoodValue
	 */
	public static void setRabbitFoodValue(int rabbitFoodValue)
	{
		AnimalNumbers.rabbitFoodValue = rabbitFoodValue;
	}
	
	/**
	 * Getter for grassFoodValue
	 */
	public static int getGrassFoodValue()
	{
		return grassFoodValue;
	}
	/**
	 * Setter for grassFoodValue
	 */
	public static void setGrassFoodValue(int grassFoodValue)
	{
		AnimalNumbers.grassFoodValue = grassFoodValue;
	}
	
	/**
	 * Getter for weaselEatingFoxAge
	 */
	public static int getWeaselEatingFoxAge()
	{
		return weaselEatingFoxAge;
	}
	/**
	 * Setter for weaselEatingFoxAge
	 */
	public static void setWeaselEatingFoxAge(int weaselEatingFoxAge)
	{
		AnimalNumbers.weaselEatingFoxAge = weaselEatingFoxAge;
	}
	
	/**
	 * Getter for grassGrowingProbability
	 */
	public static int getGrassGrowingProbability()
	{
		return grassGrowingProbability;
	}
	/**
	 * Setter for grassGrowingProbability
	 */
	public static void setGrassGrowingProbability(int grassGrowingProbability)
	{
		AnimalNumbers.grassGrowingProbability = grassGrowingProbability;
	}
	
	/**
	 * Getter for grassMaxLitterSize
	 */
	public static int getGrassMaxLitterSize()
	{
		return grassMaxLitterSize;
	}
	/**
	 * Setter for grassMaxLitterSize
	 */
	public static void setGrassMaxLitterSize(int grassMaxLitterSize)
	{
		AnimalNumbers.grassMaxLitterSize = grassMaxLitterSize;
	}	
	
	/**
	 * Getter for fieldWidth
	 */
	public static int getFieldWidth()
	{
		return fieldWidth;
	}
	/**
	 * Setter for fieldWidth
	 */
	public static void setFieldWidth(int fieldWidth)
	{
		AnimalNumbers.fieldWidth = fieldWidth;
	}
	
	/**
	 * Getter for fieldDepth
	 */
	public static int getFieldDepth()
	{
		return fieldDepth;
	}
	/**
	 * Setter for fieldDepth
	 */
	public static void setFieldDepth(int fieldDepth)
	{
		AnimalNumbers.fieldDepth = fieldDepth;
	}
	
	/**
	 * Getter for weaselCreationProbability
	 */
	public static int getWeaselCreationProbability()
	{
		return weaselCreationProbability;
	}
	/**
	 * Setter for weaselCreationProbability
	 */
	public static void setWeaselCreationProbability(int weaselCreationProbability)
	{
		AnimalNumbers.weaselCreationProbability = weaselCreationProbability;
	}
	
	/**
	 * Getter for foxCreationProbability
	 */
	public static int getFoxCreationProbability()
	{
		return foxCreationProbability;
	}
	/**
	 * Setter for foxCreationProbability
	 */
	public static void setFoxCreationProbability(int foxCreationProbability)
	{
		AnimalNumbers.foxCreationProbability = foxCreationProbability;
	}
	
	/**
	 * Getter for rabbitCreationProbability
	 */
	public static int getRabbitCreationProbability()
	{
		return rabbitCreationProbability;
	}
	/**
	 * Setter for rabbitCreationProbability
	 */
	public static void setRabbitCreationProbability(int rabbitCreationProbability)
	{
		AnimalNumbers.rabbitCreationProbability = rabbitCreationProbability;
	}
	
	/**
	 * Getter for grassCreationProbability
	 */
	public static int getGrassCreationProbability()
	{
		return grassCreationProbability;
	}
	/**
	 * Setter for grassCreationProbability
	 */
	public static void setGrassCreationProbability(int grassCreationProbability)
	{
		AnimalNumbers.grassCreationProbability = grassCreationProbability;
	}
	
	/**
	 * Getter for hunterCreationProbability
	 */
	public static int getHunterCreationProbability()
	{
		return hunterCreationProbability;
	}
	/**
	 * Setter for hunterCreationProbability
	 */
	public static void setHunterCreationProbability(int hunterCreationProbability)
	{
		AnimalNumbers.hunterCreationProbability = hunterCreationProbability;
	}
	
	/**
	 * Getter for grassIllnessProbability
	 */
	public static int getGrassIllnessProbability()
	{
		return grassIllnessProbability;
	}
	/**
	 * Setter for grassIllnessProbability
	 */
	public static void setGrassIllnessProbability(int grassIllnessProbability)
	{
		AnimalNumbers.grassIllnessProbability = grassIllnessProbability;
	}
}

