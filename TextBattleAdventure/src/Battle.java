/**
 * Name: Isayiah Lim
 * Last Updated On: 5/31/2022
 * Mrs. Kankelborg
 * APCS Period 2
 * Text Battle Project Part Two
 * 
 * This class is the application class. Your main method must meet all the requirements in the 
 * specification on turn in but is otherwise for your testing purposes only. My test code will call
 * your startBattle method directly with my own version of the code you will write in main. 
 * 
 */
import java.util.ArrayList;
import java.util.Scanner;
public class Battle {
	/**
	 * Use this method for playing your game. I will bypass this in my test code, but I will
	 * be looking at its contents when I grade for internal correctness.
	 */
	public static void main(String[] args) 
	{
		//sets the name for the player
		Scanner input = new Scanner(System.in);
		System.out.print("What is your name? ");
		String name = input.nextLine();
		Player player;
		
		//variables determining whether the game continues
		Boolean quit = false;
		Boolean alive = true;
		boolean shopOpen = true;
		
		//sets the class of the player
		System.out.print("Choose your class (Rogue, Warrior, or Mage) ");
		String className = input.next();
		//makes sure it is valid
		while (!className.equals("Rogue") && !className.equals("Warrior") && 
				!className.equals("Mage"))
		{
			System.out.print("Choose a valid class (Rogue, Warrior, or Mage) ");
			className = input.next();
		}
		if(className.equals("Rogue"))
			player = new Rogue(name + " the Rogue", 50);
		else if (className.equals("Warrior"))
			player = new Warrior(name + " the Warrior", 50);
		else 
			player = new Mage(name + " the Mage", 50);
		System.out.println();
		
		//chooses the monster from an array of monster
		String[] monsterList = {"Mr. Lesli", "CollegeBoard", "Tonald J. Dump", "This Project"};
		//starts the game and runs until player dies or they choose to quit
		while(!quit && alive)
		{
			//chooses a random monster to fight
			Monster monster = new Monster(monsterList[(int)(Math.random()*monsterList.length)]);
			//opens the shop
			if(shopOpen)
				shop(player, input);
			//asks if they want to play a minigame
			
			//plays game, sees if player wins
			alive = startBattle(player, monster, input);
			if(alive)
			{
				//asks if they want to quit
				System.out.print("Continue? ");
				String response = input.next().toUpperCase();
				if(response.substring(0,1).equals("N"))
					quit = true;
				else 
				{
					int question = 0;
					while(question!= 1 && question != 2)
					{
						System.out.print("Do you want to enter the shop (1) or keep battling (2) ");
						question = input.nextInt();
						if(question == 1)
							shopOpen = true;
						else if(question == 2)
							shopOpen = false;
					}
					System.out.println();
				}
			}
		}
	}
	//gives additional loot at the end of a round
	public static Item getLoot(Player player)
	{
		//randomizes the rarity and potion chosen
		double rarityChance = Math.random();
		double potionChance = Math.random();
		String ptype;
		//determines rarity
		if(rarityChance < 0.05)
			ptype = "Epic";
		else if(rarityChance < 0.15)
			ptype = "Greater";
		else if(rarityChance < 0.35)
			ptype = "Basic";
		else
			ptype = "Lesser";
		//returns a random potion of given rarity
		if(potionChance < 0.05)
			return new JesusPotion("Epic");
		if (player instanceof Mage)
		{
			if (potionChance < 0.25)
				return new ManaPotion(ptype);
			else if (potionChance < 0.45)
				return new StrengthPotion(ptype);
			else if(potionChance < 0.55)
				return new Sword("Leaf Blade", ptype);
			else
				return new HealthPotion(ptype);
		}
		else 
		{
			if(potionChance < 0.25)
				return new StrengthPotion(ptype);
			else if(potionChance < 0.35)
				return new Sword("Blood Blade", ptype);
			else
				return new HealthPotion(ptype);
		}
	}
	//shop method
	public static void shop(Player player, Scanner input)
	{
		//array including all the possible rarities & one with all correlating price multipliers
		String[] rarities = {"Lesser", "Basic", "Greater", "Epic"};
		int[] multiplier = {50, 100, 250, 500};
		
		//array including all the items that can be chosen from 
		Item[] options = {new HealthPotion("Lesser"), new StrengthPotion("Lesser"),
				new ManaPotion("Lesser"), new Sword("Excalibur", "Lesser")};
		
		//Arraylist including the random available loot
		ArrayList<Item> todayShop = new ArrayList<Item>();
		ArrayList<Integer> prices = new ArrayList<Integer>();
		
		//randomizes the number of items and the kind of items in it
		for(int i = 0; i < (int)(Math.random()*3) + 3; i++)
		{
			//chooses a random item
			int choice = (int)(Math.random()*4);
			Item newI = options[choice];
			//gives it a random rarity
			int rareChoice = (int)(Math.random()*4);
			newI.setRarity(rarities[rareChoice]);
			//adds it to the shop
			todayShop.add(newI);
			//adds the price to a list of prices
			prices.add(multiplier[rareChoice]);
		}
		
		//leave the shop
		boolean leave = false;
		//printed statements
		System.out.println("Welcome to the Shop! You have " + player.getGold() + " gold.");
		while(!leave)
		{
			if(player.getGold() == 0)
			{
				leave = true;
				System.out.println("Sorry, you have insufficient funds. Leaving shop now.");
				System.out.println();
			}
			else
			{
				System.out.println("Today's inventory: ");
				System.out.println();
				//prints out the entire shop
				for(int i = 0; i < todayShop.size(); i++)
				{
					System.out.println(i+1 + ": " + todayShop.get(i) + " for "+ prices.get(i) + 
							" gold.");
				}
				System.out.println();
				//asks for purchases
				System.out.print("What would you like to buy? (type number of item or "
						+ "type 0 to leave) ");
				int response = input.nextInt() - 1;
				//checks to see if response is a valid option
				if(response >= 0 && response < todayShop.size())
				{
					if(player.getGold() >= prices.get(response))
					{
						player.receiveItem(todayShop.get(response), player);
						System.out.println("You have received " + todayShop.get(response));
						System.out.println();
						player.spendGold(prices.get(response));
						System.out.println("You have " + player.getGold() + " gold remaining.");
						todayShop.remove(response);
						prices.remove(response);
					}
					else
						System.out.println("Insufficient funds.");
				}
				else if(response == -1)
				{
					leave = true;
					System.out.println("Thank you for visiting the shop!");
					System.out.println();
				}
				else
					System.out.println("Invalid selection. Please pick again.");
			}
		}
	}
	 /**
	  * This is the method that my test cases will call directly.
	  * Do not modify the header of this method.
	  * @param player
	  * @param monster
	  */
	public static boolean startBattle(Player player, Monster monster, Scanner input) 
	{
		//intro message
		System.out.println(player.getName() + " has encountered " + monster.getType() + "!");
		
		//variables
		int roundNum = 1;
		int statBoost = 0;
		
		//runs until either the player or monster dies
		while(player.getHealth() > 0 && monster.getHealth() > 0)
		{
			System.out.println();
			System.out.println("********************************** ROUND " + roundNum +
					" **********************************");
			System.out.println();
			System.out.println("Your inventory holds: " + player.getInventory());
			//choose between attacking and using an item
			System.out.print("Type an inventory slot number or 0 to attack: ");
			int response = input.nextInt();
			//player's move
			if(response == 0)
			{
				int damage = player.attack(monster);
				System.out.println(player.getName() + " attacks the " + monster.getType() + 
					" for " + damage + " damage.");
				System.out.println(monster);
			}
			else
			{
				int temp = player.useItem(response-1);
				//if there isn't a sword already equipped
				if(temp != 0)
				{
					player.getWeaker(statBoost);
					statBoost = temp;
				}
			}
			System.out.println();
			//monster fights back
			if(monster.getHealth() > 0)
			{
				System.out.println("The " + monster.getType() + " attacks " + player.getName() + " for " 
						+ monster.attack(player) + " damage.");
				System.out.println(player);
				System.out.println();
			}
			roundNum ++;
		}
		
		//end message
		if(monster.getHealth() > 0)
		{
			System.out.println("The " + monster.getType() + " has defeated " + player.getName());
			return false;
		}
		else
		{
			System.out.println(player.getName() + " has defeated the " + monster.getType());
			// heal the player
			int healnum = (int)(Math.random()*20) + 1;
			System.out.println(player.getName() + " has been rewarded " + healnum 
					+ " points of health back.");
			player.healDamage(healnum);
			//gives random gold
			int gold = (int)(Math.random()*10 + 1) * 100;
			player.earnGold(gold);
			System.out.println(player.getName() + " has been rewarded with " + gold + " gold.");
			//gives random loot
			Item loot = getLoot(player);
			player.receiveItem(loot, player);
			System.out.println(player.getName() + " has been rewarded with a " + loot.getType());
			return true;
		}
	}		
}
