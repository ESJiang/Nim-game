public class NimGame
{
    private int stoneCurrentCount, stoneRemovalUpperBound;

    public void nimGame(NimPlayer[] arr, int stoneCurrentCount,
        int stoneRemovalUpperBound, String userName1, String userName2)
    {
        NimPlayer player1 = new NimPlayer();
        NimPlayer player2 = new NimPlayer();
        Nimsys gain = new Nimsys();

        String givenName1 = player1.getGivenName(arr, gain.getIndex(userName1));
        String givenName2 = player2.getGivenName(arr, gain.getIndex(userName2));
        String familyName1 = player1.getFamilyName(arr, gain.getIndex(userName1));
        String familyName2 = player2.getFamilyName(arr, gain.getIndex(userName2));
        setStoneCurrentCount(stoneCurrentCount);
        setStoneRemovalUpperBound(stoneRemovalUpperBound);

        System.out.println("Initial stone count: " + getStoneCurrentCount());
        System.out.println("Maximum stone removal: " + getStoneRemovalUpperBound());
        System.out.println("Player 1: " + givenName1 + " " + familyName1);
        System.out.println("Player 2: " + givenName2 + " " + familyName2);

        int count = 1;
		// A labeled continue statement.
        outer:
        while (stoneCurrentCount > 0)
        {
            System.out.println();
            System.out.print(stoneCurrentCount + " stones left:");
            printStones(stoneCurrentCount);

            if (count%2 == 1)
            {
                System.out.println(givenName1 + "'s turn - remove how many?");
            }
            else
            {
                System.out.println(givenName2 + "'s turn - remove how many?");
            }

            int numberToRemove = player1.removeStone();
            int limitNumber = Math.min(stoneRemovalUpperBound, stoneCurrentCount);
            if ( (numberToRemove >= 1) && (numberToRemove <= limitNumber) )
            {
                stoneCurrentCount -= numberToRemove;
                ++count;
            }
            else
            {
                System.out.println();
                System.out.println("Invalid move. You must remove between 1 and " +
                    limitNumber + " stones.");
                continue outer;
            }
        }

        System.out.println();
        System.out.println("Game Over");
        if(count%2 == 1)
        {
            System.out.println(givenName1 + " " + familyName1 + " wins!");
            player1.setNumberPlayed(arr, player1.getNumberPlayed(arr, gain.getIndex(userName1))+1,
                gain.getIndex(userName1));
            player1.setNumberWon(arr, player1.getNumberWon(arr, gain.getIndex(userName1))+1,
                gain.getIndex(userName1));
            player2.setNumberPlayed(arr, player2.getNumberPlayed(arr, gain.getIndex(userName2))+1,
                gain.getIndex(userName2));
        }
        else
        {
            System.out.println(givenName2 + " " + familyName2 + " wins!");
            player2.setNumberPlayed(arr, player2.getNumberPlayed(arr, gain.getIndex(userName2))+1,
                gain.getIndex(userName2));
            player2.setNumberWon(arr, player2.getNumberWon(arr, gain.getIndex(userName2))+1,
                gain.getIndex(userName2));
            player1.setNumberPlayed(arr, player1.getNumberPlayed(arr, gain.getIndex(userName1))+1,
                gain.getIndex(userName1));
        }
        System.out.println();
        System.out.print("$");
    }

    public void printStones(int numberOfStones)
    {
        for (int starNumber = 1; starNumber <= numberOfStones; starNumber++)
        {
            System.out.print(" *");
        }
        System.out.println();
    }

    public int getStoneCurrentCount()
    {
        return stoneCurrentCount;
    }

    public void setStoneCurrentCount(int newNumber)
    {
        stoneCurrentCount = newNumber;
    }

    public int getStoneRemovalUpperBound()
    {
        return stoneRemovalUpperBound;
    }

    public void setStoneRemovalUpperBound(int newNumber)
    {
        stoneRemovalUpperBound = newNumber;
    }
}