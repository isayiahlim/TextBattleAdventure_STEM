/**
 * Name: Isayiah Lim
 * Last Updated On: 5/31/2022
 * Mrs. Kankelborg
 * APCS Period 2
 * Text Battle Project Part Two
 * 
 * This class is the represents a StrenghPotion object which is an Item. It must contain all of the fields 
 * and methods detailed in the project spec. You 
 * may add additional fields and methods if you like.
 */
public class StrengthPotion extends Item
{
	//constructor
	public StrengthPotion(String rarity)
	{
		super("Strength Potion", rarity);
	}
	public void use(Player player)
	{
		//increases the strength of the player
		int stronger = getPoints();
		System.out.println(player.getName() + " used a " + super.getRarity() + " Strength Potion "
				+ "increasing their min and max damage by " + stronger + " points.");
		player.getStronger(stronger);
	}
}