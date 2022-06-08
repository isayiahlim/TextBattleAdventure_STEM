public class JesusPotion extends Item
{
	public JesusPotion(String rarity)
	{
		//always an epic potion
		super("Jesus Potion", "Epic");
	}
	
	public void use(Player player)
	{
		//makes sure the player is a mage, then gives it more mana
		int aid = (int)(getPoints()*1.5);
		if(player instanceof Mage)
		{
			((Mage) player).restoreMana(aid);
			System.out.println(player.getName() + " channelled the divinity of Tacos, increasing their "
					+ "available health, damage, and mana by " + aid + " points.");
		}
		else
			System.out.println(player.getName() + " channelled the divinity of Tacos, increasing their "
					+ "available health and damage " + aid + " points.");
		player.healDamage(aid);
		player.getStronger(aid);
		
	}
}
