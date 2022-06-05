/**
 * Name: Isayiah Lim
 * Last Updated On: 5/31/2022
 * Mrs. Kankelborg
 * APCS Period 2
 * Text Battle Project Part Two
 * 
 * This class is the represents a MagePotion object which is an Item. It must contain all of the fields
 * and methods detailed in the project spec. You 
 * may add additional fields and methods if you like.
 */

public class ManaPotion extends Item
{
	public ManaPotion(String rarity)
	{
		super("Mana Potion", rarity);
	}
	
	public void use(Player player)
	{
		//makes sure the player is a mage, then gives it more mana
		if(player instanceof Mage)
		{
			int restore = getPoints();
			System.out.println(player.getName() + " used a " + super.getRarity() + " Mana "
				+ "Potion increasing their available mana by " + restore + " points.");
			((Mage) player).restoreMana(restore);
		}
		// if not a mage
		else
		{
			System.out.println("Only a mage can use a Mana Potion!");
		}
	}
}

