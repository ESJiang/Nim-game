/* *****************************************************************************
 *  Name:    Fangwei Jiang
 *  StudentID:   971196
 *
 *  Description:  This program is a simulation of the game of Nim.
 *
 *  Written:       15/04/2019
 *  Last updated:  30/04/2019
 *
 **************************************************************************** */
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Arrays;

public class Nimsys
{
	static Scanner keyboard = new Scanner(System.in);
	NimPlayer name = new NimPlayer();
	NimGame start = new NimGame();
	//Create an array with class base type, the limit number of elements is 100
	static NimPlayer[] player = new NimPlayer[100];
	public static void main(String[] args)
	{

		for (int i = 0; i < player.length; i++)
		{
			player[i] = new NimPlayer();
		}

		System.out.println("Welcome to Nim");
		new Nimsys().blank();

		String temps = keyboard.nextLine();

		while (temps != null)
		{
			new Nimsys().userEnter(temps);
			temps = keyboard.nextLine();
		}

	}

	public void addPlayer(String userName, String familyName, String givenName)
	{
		if ((judge(userName)) == true)
		{
			System.out.println("The player already exists.");
			blank();
		}
		else
		{
			for (int i = 0; i < player.length; i++)
			{
				if (name.getUserName(player, i) == null)
				{
					name.setUserName(player, userName, i);
					name.setFamilyName(player, familyName, i);
					name.setGivenName(player, givenName, i);
					break;
				}
				else
				{
					continue;
				}
			}
			blank();
		}
	}

	public void removePlayer(String userName)
	{
		if ((judge(userName)) == false)
		{
			System.out.println("The player does not exist.");
			blank();
		}
		else
		{
			player = delete(getIndex(userName));
			blank();
		}
	}

	public void removePlayer()
	{
		System.out.println("Are you sure you want to remove all players? (y/n)");;
		String m = keyboard.next();
		if (m.equals("y"))
		{
			for (int i = 0; i < player.length; i++ )
			{
				player[i] = new NimPlayer();
			}
			blank();
		}
	}

	public void editPlayer(String userName, String newFamilyName, String newGivenName)
	{
		if ((judge(userName)) == false)
		{
			System.out.println("The player does not exist.");
			blank();
		}
		else
		{
			name.setFamilyName(player, newFamilyName, getIndex(userName));
			name.setGivenName(player, newGivenName, getIndex(userName));
			blank();
		}
	}

	public void resetStats(String userName)
	{
		if ((judge(userName)) == false)
		{
			System.out.println("The player does not exist.");
			blank();
		}
		else
		{
			name.setNumberWon(player, 0, getIndex(userName));
			name.setNumberWon(player, 0, getIndex(userName));
			blank();
		}
	}

	public void resetStats()
	{
		System.out.println("Are you sure you want to reset all player statistics? (y/n)");
		String m1 = keyboard.next();
		if (m1.equals("y"))
		{
			for (int i = 0; i < player.length; i++)
			{
				name.setNumberPlayed(player, 0, i);
				name.setNumberWon(player, 0, i);
			}
			blank();
		}
	}

	public void displayPlayer(String userName)
	{
		if ((judge(userName)) == false)
		{
			System.out.println("The player does not exist.");
			blank();
		}
		else
		{
			String a1 = name.getUserName(player, getIndex(userName));
			String a2 = name.getGivenName(player, getIndex(userName));
			String a3 = name.getFamilyName(player, getIndex(userName));
			int b1 = name.getNumberPlayed(player, getIndex(userName));
			int b2 = name.getNumberWon(player, getIndex(userName));
			System.out.println(a1 + "," + a2 + "," + a3 + "," + b1 + " games," + b2 + " wins");
			blank();
		}
	}

	public void displayPlayer()
	{
		String[] A = new String[getNumbers()+1];
		for (int i = 0; i <= getNumbers(); i++)
		{
			String userName = name.getUserName(player, i);
			String givenName = name.getGivenName(player, i);
			String familyName = name.getFamilyName(player, i);
			int gamePlayed = name.getNumberPlayed(player, i);
			int gameWon = name.getNumberWon(player, i);
			A[i] = userName + "," + givenName + "," + familyName + "," +
			gamePlayed +" games" + "," + gameWon + " wins";
		}
		Arrays.sort(A);
		for (int m = 0; m <= getNumbers(); m++)
		{
			System.out.println(A[m]);
		}
		blank();
	}

	public void rankings(String asc)
	{
		sort();
		String[] A = new String[getNumbers()+1];
		outputLayout(A);
		for (int m = 0; m <= getNumbers(); m++)
		{
			System.out.println(A[m]);
		}
		blank();
	}

	public void rankings()
	{
		sort();
		String[] A = new String[getNumbers()+1];
		outputLayout(A);
		for (int m = getNumbers(); m >= 0; m--)
		{
			System.out.println(A[m]);
		}
		blank();
	}

	public void outputLayout(String[] arr)
	{
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMaximumFractionDigits(0);
		for (int i = 0; i <= getNumbers(); i++)
		{
			int gameWon = name.getNumberWon(player, i);
			int gamePlayed = name.getNumberPlayed(player, i);
			String firstName = name.getGivenName(player, i);
			String lastName = name.getFamilyName(player, i);
			if(gamePlayed == 0)
				arr[i]= "0%" + "   | " + "0" + gamePlayed + " games" + " | " +
			firstName + " " + lastName;
			else if(gamePlayed == gameWon)
				arr[i]= "100%" + " | " + "0" + gamePlayed + " games" + " | " +
			firstName + " " + lastName;
			else if(gameWon == 0 )
				arr[i]= "0%" + "   | " + "0" + gamePlayed + " games" + " | " +
			firstName + " " + lastName;
			else
				arr[i]= nf.format((float)gameWon/gamePlayed) + "  | " + "0" +gamePlayed +
			" games" + " | " + firstName + " " + lastName;
		}
	}

	public void startGame(int currentCount, int removalUpperBound, String userName1, String userName2)
	{
		if ( (judge(userName1) == false) || (judge(userName2) == false) )
		{
			System.out.println("One of the players does not exist.");
			blank();
		}
		else
		{
			System.out.println();
			start.nimGame(player, currentCount, removalUpperBound, userName1, userName2);
		}
	}

	public void exitGame()
	{
		System.out.println();
		System.exit(0);
	}

	public void sort()  //Bubble sort
	{
		for (int i = 1; i <= getNumbers(); i++)
		{
			NimPlayer tmp;
			for (int j = 0; j <= getNumbers() - 1; j++)
			{
				if ( (getWinRate(j) == getWinRate(j + 1)) &&
					(name.getUserName(player, j).compareTo(name.getUserName(player, j + 1)) < 0) )
				{
					tmp = player[j];
					player[j] = player[j + 1];
					player[j + 1] = tmp;
				}
				else if (getWinRate(j) > getWinRate(j+1))
				{
					tmp = player[j];
					player[j] = player[j + 1];
					player[j + 1] = tmp;
				}
			}
		}
	}

	public void blank()
	{
		System.out.println();
		System.out.print("$");
	}

	public boolean judge(String value)
	{
		boolean flag = false;
		for (int i = 0; i < player.length; i++)
		{
			if (value.equals(name.getUserName(player, i)))
			{
				flag = true;
			}
		}
		return flag;
	}

	public int getIndex(String value)
	{
		for (int i = 0; i < player.length; i++)
		{
			if (name.getUserName(player, i).equals(value))
			{
				return i;
			}
		}
		return -1;
	}

	public NimPlayer[] delete(int value)
	{
		NimPlayer[] newData = new NimPlayer[player.length - 1];
		for (int x = 0; x < value; x++)
		{
			newData[x] = player[x];
		}
		for (int x = value + 1; x < player.length; x++)
		{
			newData[x-1] = player[x];
		}
		return newData;
	}

	public int getNumbers()
	{
		for (int i = 0; i < player.length; i++)
		{
			if (name.getUserName(player, i) == null)
				return --i;
		}
		return -1;
	}

	public float getWinRate(int index)
	{
		if (name.getNumberWon(player, index) == 0)
			return 0;
		else
			return (float)name.getNumberWon(player, index)/name.getNumberPlayed(player, index);
	}

	public void userEnter(String temps) //Use the scanner to call different methods.
	{
		String arrays[] = temps.split(" ");
		if (arrays.length >= 2)
		{
			String temps1 = arrays[1];
			String arrays1[] = temps1.split(",");
			switch (arrays[0])
			{
				case "addplayer":
				addPlayer(arrays1[0], arrays1[1], arrays1[2]);
				break;
				case "removeplayer":
				removePlayer(arrays1[0]);
				break;
				case "editplayer":
				editPlayer(arrays1[0], arrays1[1], arrays1[2]);
				break;
				case "resetstats":
				resetStats(arrays1[0]);
				break;
				case "displayplayer":
				displayPlayer(arrays1[0]);
				break;
				case "rankings":
				if(arrays1[0].equals("asc"))
					rankings(arrays[0]);
				else if(arrays1[0].equals("desc"))
					rankings();
				break;
				case "startgame":
				startGame(Integer.parseInt(arrays1[0]),
					Integer.parseInt(arrays1[1]), arrays1[2], arrays1[3]);
				break;
			}
		}
		else
		{
			switch (arrays[0])
			{
				case "removeplayer":
				removePlayer();
				break;
				case "resetstats":
				resetStats();
				break;
				case "displayplayer":
				displayPlayer();
				break;
				case "rankings":
				rankings();
				break;
				case "exit":
				exitGame();
				break;
			}
		}
	}
}