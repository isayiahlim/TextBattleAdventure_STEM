/**
 * Name: Isayiah Lim
 * Last Updated On: 5/31/2022
 * Mrs. Kankelborg
 * APCS Period 2
 * Text Battle Project Part Two
 * 
 * This class represents an Item object. It must contain all of the fields and
 * methods detailed in the project spec. You may add additional fields and methods if you
 * like.
 */
public class Item
{
	private String type;
	private String rarity;
	
	//default constructor
	public Item()
	{
		type = "Unknown Item";
		rarity = "Lesser";
	}
	
	//mutator constructor
	public Item(String type, String rarity)
	{
		this.type = type;
		if(rarity.equals("Lesser") || rarity.equals("Basic") || rarity.equals("Greater") ||
				rarity.equals("Epic"))
			this.rarity = rarity;
		else
			this.rarity = "Lesser";
	}
	
	//prints out the item
	public String toString()
	{
		return rarity + " " + type;
	}
	
	//returns item
	public String getType()
	{
		return type;
	}
	
	//returns rarity
	public String getRarity()
	{
		return rarity;
	}
	
	//can change rarity
	public void setRarity(String rarity)
	{
		this.rarity = rarity;
	}
	
	//this allows an item to be used
	public void use(Player player)
	{
		System.out.println(player.getName() + " used an item with an unclear result.");
	}
	
	//returns the amount of points a item is worth
	public int getPoints()
	{
		int mult = (int)(Math.random()*5);
		if(rarity.equals("Lesser")) 
			return mult + 1;
		else if(rarity.equals("Basic"))
			return mult + 6;
		else if(rarity.equals("Greater"))
			return mult + 11;
		else if(rarity.equals("Epic"))
			return mult + 16;
		else 
			return 0;
	}
}
