import java.util.Scanner; // For user input

class main {
	
	static Scanner getInput = new Scanner(System.in);
	static int pos = 0;                        // Position on map
	static int mapLimit = 20;                  // End of map         
	static int runsCompletedTokens = 0;        // For shop
	static int moveLimit = 4;                  // How far you can move per movement chance
	static int movementTurns = 1;              // Movement chances
	static int coinsPerRun = 1;                // How many coins are gained at the end of each map
	static int runsCompleted = 0;              // Amount of runs completed
	static byte movementUpgradesBought = 0;    // Number of movement upgrades bought
	static byte movementTurnsBought = 0;       // Number of movement chance upgrades bought
 	static byte reductionsBought = 0;          // Number of map reductions bought
	static byte moveIncreaserCount = 0;        // Number of temporary move boosters (increases how far you can move per movement chance
	static byte tempMoveTurns = 0;             // Number of temporary move-chance boosters
	static byte tempTimeTeleporters = 0;       // Number of single-map-skips 
	static boolean moveIncreaserUsed = false;  // Checks if item used
	static boolean tempMoveTurnsUsed = false;  // Checks if item used
	
	public static void main(String[] args) { // Runs function that starts game
		titleScreen();
	}
	public static void runGame() { // Starts game
		selectOptions();
		runGame();
	}
	public static void endGame() { // Ends game
		System.out.println("Thank you for playing.\nEnding game . . .");
		System.exit(0);
	}
	public static void titleScreen() {       // Sets-up game
		System.out.println("\tWelcome to the game!\n"
				     + "____________________________________");
		System.out.println("1.\tStart Game\n"
				         + "2.\tQuit game");
		int gameStart = getInput.nextInt();
		while (gameStart > 2 || gameStart < 1) { // Checks for valid input
			System.out.println("Input was invalid. Please retry.");
			gameStart = getInput.nextInt();
		} 
		if (gameStart == 1) {
			runGame();
		} else {
			endGame();
		}
	}
	public static void selectOptions() {     // Meat of the game
		System.out.println("\nWhat would you like to do?");
		System.out.println("1.\tMove\n"
				         + "2.\tOpen shop\n"
				         + "3.\tOpen help menu\n"
				         + "4.\tUse Item\n"
				         + "5.\tQuit game");
		int decision = getInput.nextInt();
		while (decision <= 0 || decision >= 6) {
			System.out.println("Your decision was invalid, please try again.");
			decision = getInput.nextInt();
		}
		switch (decision) {
		case 1:
			movementOptions();
			break;
		case 2:
			baseShop();
			break;
		case 3:
			helpMenu();
			break;
		case 4:
			itemMenu();
			break;
		case 5:
			endGame();
			break;
		}
	}
	public static void movementOptions() {
		if (moveIncreaserUsed == true ) {
			System.out.println("An item has been used!");
			for (int i = 1; i <= movementTurns; i++) // Start loop for movement turns 
			{ 
			    System.out.println("\nHow many spaces would you like to move? (max " + (moveLimit * 3) + ")");
			    int spaces = getInput.nextInt();
			    while (spaces > (moveLimit * 3) || spaces <= 0) {
				    System.out.println("Spaces selected is invalid. Please try again.");
				    spaces = getInput.nextInt();
			    }
			    pos += spaces;
			}
			   System.out.println("You are now located at " + pos + ", " + (mapLimit - pos) + " spaces away from the end\n");
			   if (pos > mapLimit) {
					System.out.println("Map limit reached.");
					pos = mapLimit;
				}
			   moveIncreaserUsed = false;
			   checkForEnd();
		} else if (tempMoveTurnsUsed == true) {
			System.out.println("An item has been used!");
			for (int i = 1; i <= (movementTurns + 1); i++) // Start loop for movement turns 
			{ 
			    System.out.println("\nHow many spaces would you like to move? (max " + moveLimit + ")");
			    int spaces = getInput.nextInt();
			    while (spaces > moveLimit || spaces <= 0) {
				    System.out.println("Spaces selected is invalid. Please try again.");
				    spaces = getInput.nextInt();
			    }
			    pos += spaces;
			}
			   System.out.println("You are now located at " + pos + ", " + (mapLimit - pos) + " spaces away from the end\n");
			   if (pos > mapLimit) {
					System.out.println("Map limit reached.");
					pos = mapLimit;
				}
			   tempMoveTurnsUsed = false;
			   checkForEnd();
		} else {
			for (int i = 1; i <= movementTurns; i++) 
			{
				System.out.println("\nHow many spaces would you like to move? (max " + moveLimit + ")");
				int spaces = getInput.nextInt();
				while (spaces > moveLimit || spaces <= 0) {
					System.out.println("Spaces selected is invalid. Please try again.");
					spaces = getInput.nextInt();
				}
				pos += spaces;
			}
			System.out.println("You are now located at " + pos + ", " + (mapLimit - pos) + " spaces away from the end\n");
			if (pos > mapLimit) {
				System.out.println("Map limit reached.");
				pos = mapLimit;
			}
			checkForEnd();
		}
	}
	public static void checkForEnd() { // Checks if player is at end of map
		if (pos == mapLimit) {
			System.out.println("Congratulations! You have reached the end of this map.");
			System.out.println("You have gained " + coinsPerRun + " token. Good luck on your voyage.");
			runsCompletedTokens += coinsPerRun;
			mapLimit += 5;
			runsCompleted++;
			pos = 0;
			System.out.println("Coins owned: " + runsCompletedTokens);
			System.out.println("What would you like to do now?\n"
					         + "1.\tPlay again\n"
					         + "2.\tExit game\n"
					         + "3.\tGo to Shop");
			int decision = getInput.nextInt();
			switch (decision) {
			case 1:
				System.out.println("Resetting position . . .");
				break;
			case 2:
				endGame();
			case 3:
				baseShop();
				break;
			}
		}
	}
	public static void itemMenu() {
		System.out.println("\nWhich item would you like to use?");
		System.out.println("___________________________________");
		System.out.println("0.\tOpen Item help menu");
		System.out.println("1.\tTemporary Move Increaser " + "(" + moveIncreaserCount + " in inventory)");
		System.out.println("2.\tIncrease move limit " + "(" + tempMoveTurns + " in inventory)");
		System.out.println("3.\tTime Teleporter " + "(" + tempTimeTeleporters + " in inventory)");
		System.out.println("4.\tExit menu");
		System.out.println("5.\tExit game");
		int decision = getInput.nextInt();
		while (decision <= -1 || decision >= 7) {
			System.out.println("It seems your input was invalid, please try again.");
			decision = getInput.nextInt();
		}
		switch (decision) {
		case 0:
			itemHelpMenu();
			break;
		case 1:
			if (moveIncreaserCount >= 1) {
				System.out.println("Item used successfully!");
				moveIncreaserCount--;
				moveIncreaserUsed = true;
				break;
			} else {
				System.out.println("Sorry! It seems like you don't have a Temporary Move-Increaser in your inventory.");
				break;
			}
		case 2:
			if (tempMoveTurns >= 1) {
				System.out.println("Item used successfully!");
				tempMoveTurns--;
				tempMoveTurnsUsed = true;
				itemMenu();
				break;
			} else {
				System.out.println("Sorry! It seems like you don't have a Temporary Move Turn in your inventory.");
				break;
			}
		case 3:
			if (tempTimeTeleporters >= 1) {
				System.out.println("Item used successfully!");
				tempTimeTeleporters--;
				System.out.println("You teleport forward in time to the end of the map.\n"
						         + "You feel " + coinsPerRun + " coin(s) magically get transported into your bag.");
				runsCompleted++;
				runsCompletedTokens += coinsPerRun;
				pos = 0;
				itemMenu();
				break;
			} else {
				System.out.println("Sorry! It seems like you don't have a Time Teleporter in your inventory.");
				break;
			}
		case 4:
			break;
		case 5:
			endGame();
		}
	}
	public static void helpMenu() { // Base help menu
		System.out.println("\nMove - Move a certain distance based on maximum move distance and movement chances.");
		System.out.println("Shop - Place where you can either buy permanent upgrades or temporary items.");
		System.out.println("Tokens - Used in the Shop to buy everything. You can increase the amount gained per run by buying the 5-token upgrade in the shop");
		System.out.println("Exit - Shuts down game\n");
	}
	public static void baseShop() {
		System.out.println("Hello! Welcome to the shop! What would you like to buy?\n1.\tItems\n2.\tUpgrades\n3.\tExit Shop");
		int decision = getInput.nextInt();
		while (decision <= 0 || decision >= 4) {
			System.out.println("Your decision was invalid, please try again.");
			decision = getInput.nextInt();
		}
		switch (decision) {
		case 1:
			itemShop();
			break;
		case 2:
			upgradesShop();
			break;
		case 3:
			System.out.println("Ok! Thank you for visiting the Shop!\n\n");
			break;
		}
	}
	public static void itemShop() {
		System.out.println("Items owned: ");
		System.out.println("Coins owned: " + runsCompletedTokens);
		System.out.println("\tItems in Shop:\n"
				         + "_________________________________");
		System.out.println("0.\tOpen Item help menu\n"
				         + "1.\tIncrease move limit: 1 coin\n"
				         + "2.\tGain a temporary moving turn: 2 coins\n"
				         + "3.\tGiant Leap: 3 coins\n"
				         + "4.\tTravel to Upgrades Shop\n"
				         + "5.\tExit Shop\n6.\tExit game");
		int decision = getInput.nextInt();
		while (decision <= -1 || decision >= 7) {
			System.out.println("It seems your input was invalid. Please try again.");
			decision = getInput.nextInt();
		}
		switch (decision) {
		case 0:
			itemHelpMenu();
			break;
		case 1:
			if (runsCompletedTokens >= 1 ) {
				System.out.println("Item successfully bought!");
				moveIncreaserCount++;
				runsCompletedTokens--;
				itemShop();
				break;
			} else {
				System.out.println("Sorry! You don't have enough coins for this.\n\n");
				break;
			}
		case 2:
			if (runsCompletedTokens >= 2) {
				System.out.println("Item successfully bought!");
				tempMoveTurns++;
				runsCompletedTokens -= 2;
				itemShop();
				break;
			} else {
				System.out.println("Sorry! You don't have enough coins for this.\n\n");
				break;
			}
		case 3:
			if (runsCompletedTokens >= 3) {
				System.out.println("Item successfully bought!");
				tempTimeTeleporters++;
				runsCompletedTokens -= 3;
			    itemShop();
			    break;
			} else {
				System.out.println("Sorry! You don't have enough coins for this.\n\n");
				break;
			}
		case 4:
			System.out.println("Ok!\n\n");
			upgradesShop();
			break;
		case 5:
			System.out.println("Ok! Thank you for shopping at the Shop!\n\n");
			break;
		case 6:
			endGame();
		}
	}
	public static void upgradesShop() {
		getInput.nextLine();
		System.out.println("\n ___________________________");
		System.out.println("|Tokens owned: " + runsCompletedTokens + "            |");
		System.out.println("|Movement upgrades bought: " + movementUpgradesBought + "|");
		System.out.println("|Coins per run: " + coinsPerRun + "           |");
		System.out.println("|Reductions bought: " + reductionsBought + "       |");
		System.out.println("|___________________________|\n");
		System.out.println("\tItems in Upgrades Shop:\n"
				     + "________________________________________");
		System.out.println("0.\tOpen explanation menu\n"
				         + "1.\tMovement Upgrade: 1 Coin\n"
				         + "2.\tReduce Map Size: 1 coins\n"
				         + "3.\tExtra Movement Turn: 3 coins\n"
				         + "4.\tMore Coins Per Run: 5 coins\n"
				         + "5.\tTravel to Item Shop\n"
				         + "6.\tExit Upgrades Shop\n"
				         + "7.\tExit game");
		int itemBought = getInput.nextInt();
		while (itemBought <= -1 || itemBought >= 8) {
			System.out.println("It seems your input was invalid. Please try again.");
			itemBought = getInput.nextInt();
		}
		switch (itemBought) {
		case 0:
			upgradesHelpMenu();
			upgradesShop();
			break;
		case 1:
			if (runsCompletedTokens >= 1) {
				System.out.println("Item successfully bought!");
				moveLimit++;
				runsCompletedTokens--;
				movementUpgradesBought++;
				upgradesShop();
				break;
			} else {
				System.out.println("Sorry! You don't have enough coins for this.\n\n");
				break;
			}
		case 2:
			if (runsCompletedTokens >= 1) {
				System.out.println("Item successfully bought!");
				mapLimit -= 5;
				runsCompletedTokens--;
				reductionsBought++;
				upgradesShop();
				break;
			} else {
				System.out.println("Sorry! You don't have enough coins for this.\n\n");
				break;
			}
		case 3:
			if (runsCompletedTokens >= 3) {
				System.out.println("Item successfully bought!\n");
				movementTurns++;
				runsCompletedTokens -= 3;
				movementTurnsBought++;
				upgradesShop();
				break;
			} else {
				System.out.println("Sorry! You don't have enough coins for this.\n\n");
				break;
			}
		case 4:
			if (runsCompletedTokens >= 5) {
				System.out.println("Item successfully bought!\n");
				coinsPerRun++;
				runsCompletedTokens -= 5;
				upgradesShop();
				break;
			} else {
				System.out.println("Sorry! You don't have enough coins for this.\n\n");
				break;
			}
		case 5:
			System.out.println("Ok!\n\n");
			itemShop();
			break;
		case 6:
			System.out.println("Ok! Thank you for shopping at the Token Shop!\n\n");
			break;
		case 7:
			endGame();
		}
	}
	public static void upgradesHelpMenu() {
		System.out.println("\n\nMovement upgrade - increases move limit by 1");
		System.out.println("Reduce Map Size - reduce map size by 5");
		System.out.println("Extra Movement Turn - increases moves per round by 1 (can now move multiple times)");
		System.out.println("More Coins per Run - increases tokens gained per completed run by 1");
		System.out.println("Travel to Item Shop - Opens Item Shop menu");
		System.out.println("Exit Upgrades Shop - exits Upgrade Shop, opens Options menu");
	}
	public static void itemHelpMenu() {
		System.out.println("\n\nIncrease move limit - increases the distance you can move per move chance by triple for 1 round");
		System.out.println("Gain a temporary moving turn - gain an extra move chance");
		System.out.println("Time Teleporter - travels forward in time to end of the map once, gain token(s), doesn't increase map length\n\n");
	}
}
