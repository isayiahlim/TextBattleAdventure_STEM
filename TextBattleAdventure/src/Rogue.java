/**
 * Name: Isayiah Lim
 * Last Updated On: 5/31/2022
 * Mrs. Kankelborg
 * APCS Period 2
 * Text Battle Project Part Two
 * 
 * This class is the represents a Rogue object which is a Player. It must contain all of the fields and 
 * methods detailed in the project spec. You 
 * may add additional fields and methods if you like.
 */
public class Rogue extends Player
{
	private double critChance;
	
	public Rogue(String name, double critChance)
	{
		super(name, 75, 1, 10);
		//sets a default crit chance
		if(critChance >= 1 || critChance <= 0)	
			this.critChance = 0.1;
		else
			this.critChance = critChance;
	}
	
	public int attack(Monster monster)
	{
		int damage = super.attack(monster);
		//may increase damage rogue does based on a random chance
		double chance = Math.random();
		//critical hit!
		if (chance < critChance)
		{
			System.out.println(getName() + " gets a critical hit!");
			monster.takeDamage(damage);
			return damage*2;
		}
		return damage;
	}
	public double getCrit()
	{
		return critChance;
	}
}
