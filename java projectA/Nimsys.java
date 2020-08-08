/* *****************************************************************************
 *  Name:    Fangwei Jiang
 *  StudentID:   971196
 *
 *  Description:  This program is a simulation of the game of Nim.
 *                The applet framework involves two entities.
 *
 *  Written:       05/03/2019
 *  Last updated:  30/03/2019
 *
 **************************************************************************** */
import java.util.Scanner;

public class Nimsys
{
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args)
    {
        NimPlayer player1 = new NimPlayer();
        NimPlayer player2 = new NimPlayer();
        System.out.println("Welcome to Nim");
        System.out.println();
        System.out.println("Please enter Player 1's name:");
        String player1Name = keyboard.next();
        player1.setName(player1Name);
        System.out.println();
        System.out.println("Please enter Player 2's name:");
        String player2Name = keyboard.next();
        player2.setName(player2Name);
        System.out.println();
		// A labeled continue statement.
        outer:
        for (int i = 1; i > 0; i++)
        {
            System.out.println("Please enter upper bound of stone removal:");
            int stone_UpperBound = keyboard.nextInt();
            System.out.println();
            System.out.println("Please enter initial number of stones:");
            int numberOfStones = keyboard.nextInt();
            int count = 1, numberToRemove=0;
            while (numberOfStones > 0)
            {
                System.out.println();
                System.out.print(numberOfStones + " stones left:");
                new Nimsys().printStones(numberOfStones);
                /*----------------------------------------------------------------------------
                 * The first if-else sentence achieves rotation function of the two players.
                 * Additionally, the second if-else sentence limits the input will be valid
                 * and will not exceed the number of stones remaining
                 * or the upper bound on the number of stones that can be removed.
                 *----------------------------------------------------------------------------*/
                if (count%2 == 1)
                {
                    System.out.println(player1.getName() + "'s turn - remove how many?");
                    numberToRemove = player1.removeStone();
                }
                else
                {
                    System.out.println(player2.getName() + "'s turn - remove how many?");
                    numberToRemove = player2.removeStone();
                }

                if ( (numberToRemove <= stone_UpperBound) && (numberToRemove <= numberOfStones) )
                {
                    numberOfStones -= numberToRemove;
                    ++count;
                }
                else
                {
                    System.exit(0);
                }
            }
            System.out.println();
            System.out.println("Game Over");
            if(count%2 == 1)
            {
                System.out.println(player1Name + " wins!");
            }
            else
            {
                System.out.println(player2Name + " wins!");
            }
            System.out.println();

            System.out.println("Do you want to play again (Y/N):");
            String judge = keyboard.next();
            if (judge.equals("Y")) // To judge whether the game continues or not
            {
                continue outer;
            }
            else
            {
                System.exit(0);
            }
        }
    }

    public void printStones(int numberOfStones) // Method for generating star key
    {
        for (int starNumber = 1; starNumber <= numberOfStones; starNumber++)
        {
            System.out.print(" *");
        }
        System.out.println();
    }
}