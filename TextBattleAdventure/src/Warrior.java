/**
 * Name: Isayiah Lim
 * Last Updated On: 5/31/2022
 * Mrs. Kankelborg
 * APCS Period 2
 * Text Battle Project Part Two
 * 
 * This class is the represents a Mage object which is a Player. It must contain all of the f
 * fields and methods detailed in the project spec. You 
 * may add additional fields and methods if you like.
 */
public class Warrior extends Player
{
	private double shieldStrength;
	
	//constructor
	public Warrior(String name, double shieldStrength)
	{
		super(name, 125, 5, 15);
		//sets the shield strength to the given or to a default
		if(shieldStrength >= 1 || shieldStrength <= 0)	
			this.shieldStrength = 0.1;
		else
			this.shieldStrength = shieldStrength;
	}
	
	//takes damage given a parameter
	public void takeDamage(int damage)
	{
		//reduces damage based on shield strength
		int reduce = (int)(damage * shieldStrength);
		if(reduce < 1)
			reduce = 1;
		System.out.println(getName() + " blocks " + reduce + " points of damage.");
		super.takeDamage(damage - reduce);
	}
	
	//accessor
	public double getBlock()
	{
		return shieldStrength;
	}
}

