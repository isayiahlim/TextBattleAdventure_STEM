/**
 * Name: Isayiah Lim
 * Last Updated On: 5/31/2022
 * Mrs. Kankelborg
 * APCS Period 2
 * Text Battle Project Part Two
 * 
 * This class is the represents a HealthPotion object which is an Item. It must contain all of the fields and methods 
 * detailed in the project spec. You 
 * may add additional fields and methods if you like.
 */
public class HealthPotion extends Item
{
	public HealthPotion(String rarity)
	{
		super("Health Potion", rarity);
	}
	
	public void use(Player player)
	{
		//heals the player
		int heal = getPoints();
		System.out.println(player.getName() + " used a " + super.getRarity() + " Health Potion "
				+ "increasing their available health by " + heal + " points.");
		player.healDamage(heal);
	}
}
