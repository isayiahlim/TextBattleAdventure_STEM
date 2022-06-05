/*
 * This is an item that increases the damage of a player. A player can only hold one at a time.
 * Once unequipped, the player's attack decrease
 */
public class Sword extends Item
{
	private int strength;
	public Sword(String name, String rarity)
	{
		//such as excalibur or the crusher of souls
		super(name, rarity);
		//determines how strong the sword is 
		strength = (int)(getPoints()*1.4);
	}
	//improves player's strength
	public void use(Player player)
	{
		//increases the strength
		player.getStronger(strength);
		System.out.println(player.getName() + " equipped a " + super.getRarity() + " " +
			super.getType() + " increasing their min and max damage by " + 
			strength + " points.");

	}
	public int getStrength()
	{
		return strength;
	}
}

