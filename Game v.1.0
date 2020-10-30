import java.util.Scanner;

public class game {
	
	static Scanner getInput = new Scanner(System.in);
	static int pos = 0;
	static int spaces;
	static int mapLimit = 20;
	static boolean gameOver = false;
	static int gameStart;
	static int runsCompletedTokens = 0;
	static int moveLimit = 4;
	static int itemBought = 0;
	static int movementTurns = 1;
	static int coinsPerRun = 1;
	static int runsCompleted = 0;
	
	public static void main(String[] args) {
		while (gameOver == false) {
			titleScreen();
		}
	}                                                                                             
	public static void titleScreen() {
		System.out.println("\tWelcome to the game!\n____________________________________");
		System.out.println("1.\tStarts Game\n2.\tQuits game");
		gameStart = getInput.nextInt();
		while (gameStart > 2 || gameStart < 1) {
			System.out.println("Input was invalid. Please retry.");
			gameStart = getInput.nextInt();
		} 
		if (gameStart == 1) {
			runGame();
		} else {
			endGame();
		}
	}
	public static void movementOptions() {
		for (int i = 1; i <= movementTurns; i++) {
		    System.out.println("How many spaces would you like to move?");
		    spaces = getInput.nextInt();
		    while (spaces > moveLimit || spaces < 0) {
			    System.out.println("Spaces selected is invalid. Please try again.");
			    spaces = getInput.nextInt();
		    }
		    pos += spaces;
		}
		   System.out.println("You are now located at " + pos);
		   checkPos();
	}	
	public static void runGame() {
		movementOptions();
		checkForEnd();
		runGame();
	}
	public static void endGame() {
		System.out.println("Thank you for playing.\nEnding game . . .");
		gameOver = true;
	}
	public static void checkPos() {
		if (pos > mapLimit) {
			System.out.println("Map limit reached.");
			pos = mapLimit;
		} else {
			System.out.println("You are currently " + (mapLimit - pos) + " spaces away from the end.");
		}
	}
	public static void checkForEnd() {
		if (pos == mapLimit) {
			System.out.println("Congratulations! You have reached the end of the map.");
			System.out.println("You have gained " + coinsPerRun + "token. Good luck on your voyage.");
			runsCompletedTokens += coinsPerRun;
			mapLimit += 5;
			runsCompleted++;
			pos = 0;
			System.out.println("Runs completed: " + runsCompleted);
			System.out.println("Coins owned: " + runsCompletedTokens);
			System.out.println("Would you like to play again?\n1.\tYes\n2.\tNo\n3.\tGo to Shop");
			int decision = getInput.nextInt();
			switch (decision) {
			case 1:
				System.out.println("Resetting position . . .");
				break;
			case 2:
				endGame();
				break;
			case 3:
				tokenShop();
			}
		}
	}
	public static void tokenShop() {
		System.out.println("Tokens owned: " + runsCompletedTokens);
		System.out.println("\tItems in Shop:");
		System.out.println("1.\tMovement Upgrade: 1 Coin\n2.\tExtra Movement Turn: 3 coins\n3.\tMore Coins Per Run: 5 coins\n4.\tExit Menu");
		itemBought = getInput.nextInt();
		switch (itemBought) {
		case 1:
			if (runsCompletedTokens >= 1) {
				System.out.println("Item successfully bought!");
				moveLimit++;
				runsCompletedTokens--;
				tokenShop();
				break;
			} else {
				System.out.println("Sorry! You don't have enough coins for this.\n");
				break;
			}
		case 2:
			if (runsCompletedTokens >= 3) {
				System.out.println("Item successfully bought!\n");
				movementTurns++;
				runsCompletedTokens -= 3;
				tokenShop();
				break;
			} else {
				System.out.println("Sorry! You don't have enough coins for this.\n");
				break;
			}
		case 3:
			if (runsCompletedTokens >= 5) {
				System.out.println("Item successfully bought!\n");
				coinsPerRun++;
				runsCompletedTokens -= 5;
				tokenShop();
				break;
			} else {
				System.out.println("Sorry! You don't have enough coins for this.\n");
				break;
			}
		case 4:
			System.out.println("Ok! Thank you for shopping at the Token Shop!\n");
			break;
		}
	}
}
