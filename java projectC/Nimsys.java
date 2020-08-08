/* *****************************************************************************
 *  Name:    Fangwei Jiang
 *  StudentID:   971196
 *
 *  Description:  This program is a simulation of the game of Nim.
 *
 *  Written:       20/05/2019
 *  Last updated:  30/05/2019
 *
 **************************************************************************** */
import java.util.Arrays;
import java.util.InputMismatchException;
import java.text.NumberFormat;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class Nimsys
{
    static Scanner keyboard = new Scanner(System.in);
    NimPlayer name = new NimHumanPlayer();
    NimGame start = new NimGame();
	//Create arrays with class base type, the limit number of elements is 100
    static NimHumanPlayer[] player = new NimHumanPlayer[100];
    static NimAIPlayer[] playerAI = new NimAIPlayer[100];
    private static BufferedReader input;
    public static void main(String[] args)
    {
        Nimsys h = new Nimsys();
        NimPlayer gain = new NimHumanPlayer();
        for (int i = 0; i < player.length; i++)
        {
            player[i] = new NimHumanPlayer();
            playerAI[i] = new NimAIPlayer();
        }

        try
        {
            FileReader src = new FileReader("players.dat");
            input = new BufferedReader(src);
            String record = null;
            while ((record = input.readLine()) != null)
            {
                String A[] = record.split("//");
                for (int m = 0; m < A.length; m++)
                {
                    String B[] = A[m].split(",");
                    gain.setUserName(player, B[0], m);
                    gain.setGivenName(player, B[1], m);
                    gain.setFamilyName(player, B[2], m);
                    gain.setNumberPlayed(player, Integer.parseInt(B[3]), m);
                    gain.setNumberWon(player, Integer.parseInt(B[4]), m);
                }
            }
        }
        catch (FileNotFoundException e)
        {

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Welcome to Nim");
        h.blank();

        String temps = keyboard.nextLine();
        while (temps != null)
        {
            h.userEnter(temps);
            String m = keyboard.nextLine();
            temps = m;
        }
    }

    public void addaiPlayer(String userName, String familyName, String givenName)
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
                    name.setUserName(playerAI, userName, i);
                    name.setFamilyName(playerAI, familyName, i);
                    name.setGivenName(playerAI, givenName, i);
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
            for (int i = 0; i < player.length; i++)
            {
                player[i] = new NimHumanPlayer();
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
        String[] A = new String[getNumbers() + 1];
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

    public void rankings()
    {
        sort();
        String[] A = new String[getNumbers() + 1];
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
            if (gamePlayed == 0)
                arr[i]= "0%" + "   | " + "0" +gamePlayed + " games" + " | " +
            firstName + " " + lastName;
            else if (gamePlayed == gameWon)
                arr[i]= "100%" + " | " + "0" + gamePlayed + " games" + " | " +
            firstName + " " + lastName;
            else if (gameWon == 0 )
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
        File src = new File("players.dat");
        OutputStream os = null;
        try
        {
            os = new FileOutputStream(src);
            String[] A = new String[getNumbers() + 1];
            for (int i = 0; i <= getNumbers(); i++)
            {
                String userName = name.getUserName(player, i);
                String givenName = name.getGivenName(player, i);
                String familyName = name.getFamilyName(player, i);
                int gamePlayed = name.getNumberPlayed(player, i);
                int gameWon = name.getNumberWon(player, i);
                A[i] = userName + "," + givenName + "," + familyName +
                "," + gamePlayed + "," + gameWon + "//";
            }

            for (int m = 0; m <= getNumbers(); m++)
            {
                byte[] data = A[m].getBytes();
                os.write(data,0,data.length);
            }
            os.flush();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (null != os)
                {
                    os.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.exit(0);
    }


    public void sort()  //Bubble sort
    {
        for (int i = 1; i <= getNumbers(); i++)
        {
            NimHumanPlayer tmp;
            for (int j = 0; j <= getNumbers() - 1; j++)
            {
                if ( (getWinRate(j) == getWinRate(j + 1)) && (name.getUserName(player, j).compareTo(name.getUserName(player, j + 1)) < 0) )
                {
                    tmp = player[j];
                    player[j] = player[j + 1];
                    player[j + 1] = tmp;
                }
                else if (getWinRate(j) > getWinRate(j + 1))
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

    public boolean judgeAI(String value)
    {
        boolean flag = false;
        for (int i = 0; i < playerAI.length; i++)
        {
            if (value.equals(name.getUserName(playerAI, i)))
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

    public NimHumanPlayer[] delete(int value)
    {
        NimHumanPlayer[] newData = new NimHumanPlayer[player.length - 1];
        for (int x = 0; x < value; x++)
        {
            newData[x] = player[x];
        }
        for (int x = value + 1; x < player.length; x++)
        {
            newData[x - 1] = player[x];
        }
        return newData;
    }

    public int getNumbers()
    {
        for (int i = 0; i < player.length; i++)
        {
            if (name.getUserName(player, i) == null)
            {
                return --i;
            }
        }
        return -1;
    }

    public float getWinRate(int index)
    {
        if (name.getNumberWon(player, index) == 0)
        {
            return 0;
        }
        else
        {
            return (float)name.getNumberWon(player, index)/name.getNumberPlayed(player, index);
        }
    }

    public void printStones(boolean[] available)
    {
        for (int i = 0; i < available.length; i++)
        {
            if (available[i] == true)
            {
                int c = i + 1;
                if (c != available.length)
                {
                    System.out.print("<" + c + "," + "*" + "> ");
                }
                else
                {
                    System.out.print("<" + c + "," + "*" + ">");
                }
            }
            else
            {
                int c = i + 1;
                if (c != available.length)
                {
                    System.out.print("<" + c + "," + "x" + "> ");
                }
                else
                {
                    System.out.print("<" + c + "," + "x" + ">");
                }
            }
        }
        System.out.print("\n");
    }

    public void startAdvancedgame(int initialStones, String userName1, String userName2)
    {
        String givenName1 = name.getGivenName(player, getIndex(userName1));
        String givenName2 = name.getGivenName(player, getIndex(userName2));
        String familyName1 = name.getFamilyName(player, getIndex(userName1));
        String familyName2 = name.getFamilyName(player, getIndex(userName2));
        int count = 1;
        int number = initialStones;
        boolean[] k = new boolean[initialStones];
        boolean[] k1 = new boolean[initialStones];
        Arrays.fill(k, true);
        String c = "";

        System.out.println();
        System.out.println("Initial stone count: " + initialStones);
        System.out.print("Stones display: ");
        printStones(k);
        System.out.println("Player 1: " + givenName1 + " " + familyName1);
        System.out.println("Player 2: " + givenName2 + " " + familyName2);

        outer:
        while (initialStones > 0)
        {
            System.out.println();
            System.out.print(initialStones + " stones left: ");
            printStones(k);

            if (count%2 == 1)
            {
                System.out.println(givenName1 + "'s turn - which to remove?");
                String m = name.advancedMove(k1, c);
                String arrays[] = m.split(" ");
                try
                {
                    if ( (Integer.parseInt(arrays[0]) > number) || (Integer.parseInt(arrays[0]) < 1) ||
                      ((Integer.parseInt(arrays[1]) != 1) && (Integer.parseInt(arrays[1]) != 2)) )
                    {
                        throw new InputMismatchException();
                    }
                    else if ( (Integer.parseInt(arrays[1]) == 1) &&
                      (k[Integer.parseInt(arrays[0]) - 1] == false) )
                    {
                        throw new InputMismatchException();
                    }
                    else if ( (Integer.parseInt(arrays[1]) == 2) &&
                      ((k[Integer.parseInt(arrays[0]) - 1] == false)
                        || (k[Integer.parseInt(arrays[0])] == false)) )
                    {
                        throw new InputMismatchException();
                    }
                }
                catch (InputMismatchException e)
                {
                    System.out.println();
                    System.out.println("Invalid move.");
                    continue outer;
                }

                if (Integer.parseInt(arrays[1]) == 1)
                {
                    k[Integer.parseInt(arrays[0]) - 1] = false;
                }
                else
                {
                    k[Integer.parseInt(arrays[0]) - 1] = false;
                    k[Integer.parseInt(arrays[0])] = false;
                }

                initialStones -= Integer.parseInt(arrays[1]);
                c = m;
                ++count;
            }

            else
            {
                System.out.println(givenName2 + "'s turn - which to remove?");
                String m = name.advancedMove(k1, c);
                String arrays[] = m.split(" ");
                try
                {
                    if ( (Integer.parseInt(arrays[0]) > number) || (Integer.parseInt(arrays[0]) < 1) ||
                      ((Integer.parseInt(arrays[1]) != 1) && (Integer.parseInt(arrays[1]) != 2)) )
                    {
                        throw new InputMismatchException();
                    }
                    else if ( (Integer.parseInt(arrays[1]) == 1) &&
                      (k[Integer.parseInt(arrays[0]) - 1] == false) )
                    {
                        throw new InputMismatchException();
                    }
                    else if ( (Integer.parseInt(arrays[1]) == 2) &&
                      ((k[Integer.parseInt(arrays[0]) - 1] == false)
                        || (k[Integer.parseInt(arrays[0])] == false)) )
                    {
                        throw new InputMismatchException();
                    }
                }
                catch (InputMismatchException e)
                {
                    System.out.println();
                    System.out.println("Invalid move.");
                    continue outer;
                }

                if (Integer.parseInt(arrays[1]) == 1)
                {
                    k[Integer.parseInt(arrays[0]) - 1] = false;
                }
                else
                {
                    k[Integer.parseInt(arrays[0]) - 1] = false;
                    k[Integer.parseInt(arrays[0])] = false;
                }

                initialStones -= Integer.parseInt(arrays[1]);
                c = m;
                ++count;
            }
        }

        System.out.println();
        System.out.println("Game Over");

        if (count%2 == 1)
        {
            System.out.println(givenName2 + " " + familyName2 + " wins!");
            name.setNumberPlayed(player, name.getNumberPlayed(player, getIndex(userName2))+1,
                getIndex(userName2));
            name.setNumberWon(player, name.getNumberWon(player, getIndex(userName2))+1,
                getIndex(userName2));
            name.setNumberPlayed(player, name.getNumberPlayed(player, getIndex(userName1))+1,
                getIndex(userName1));
        }
        else
        {
            System.out.println(givenName1 + " " + familyName1 + " wins!");
            name.setNumberPlayed(player, name.getNumberPlayed(player, getIndex(userName1))+1,
                getIndex(userName1));
            name.setNumberWon(player, name.getNumberWon(player, getIndex(userName1))+1,
                getIndex(userName1));
            name.setNumberPlayed(player, name.getNumberPlayed(player, getIndex(userName2))+1,
                getIndex(userName2));
        }

        System.out.println();
        System.out.print("$");
    }


    public void userEnter(String temps)
    {
        String arrays[] = temps.split(" ");
        try
        {
            if ( (!arrays[0].equals("addplayer")) && (!arrays[0].equals("removeplayer")) &&
                (!arrays[0].equals("editplayer")) && (!arrays[0].equals("resetstats")) &&
                (!arrays[0].equals("displayplayer")) && (!arrays[0].equals("rankings")) &&
                (!arrays[0].equals("startgame")) && (!arrays[0].equals("exit")) &&
                (!arrays[0].equals("addaiplayer")) && (!arrays[0].equals("")) &&
                (!arrays[0].equals("startadvancedgame")) )
            {
                throw new InputMismatchException();
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("'" + arrays[0] + "'" + " is not a valid command.");
            blank();
        }
        if (arrays.length >= 2)
        {
            String temps1 = arrays[1];
            String arrays1[] = temps1.split(",");
            switch (arrays[0])
            {
                case "addaiplayer":
                try
                {
                    if (arrays1.length < 3)
                    {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("Incorrect number of arguments supplied to command.");
                    blank();
                    break;
                }
                addaiPlayer(arrays1[0], arrays1[1], arrays1[2]);
                break;

                case "addplayer":
                try
                {
                    if (arrays1.length < 3)
                    {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("Incorrect number of arguments supplied to command.");
                    blank();
                    break;
                }
                addPlayer(arrays1[0], arrays1[1], arrays1[2]);
                break;

                case "removeplayer":
                removePlayer(arrays1[0]);
                break;

                case "editplayer":
                try
                {
                    if (arrays1.length < 3)
                    {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("Incorrect number of arguments supplied to command.");
                    blank();
                    break;
                }
                editPlayer(arrays1[0], arrays1[1], arrays1[2]);
                break;

                case "resetstats":
                resetStats(arrays1[0]);
                break;

                case "displayplayer":
                displayPlayer(arrays1[0]);
                break;

                case "rankings":
                if ( (arrays1[0].equals("asc")) || (arrays1[0].equals("desc")) )
                    rankings();
                break;

                case "startgame":
                try
                {
                    if (arrays1.length < 4)
                    {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("Incorrect number of arguments supplied to command.");
                    blank();
                    break;
                }
                startGame(Integer.parseInt(arrays1[0]),
                    Integer.parseInt(arrays1[1]), arrays1[2], arrays1[3]);
                break;

                case "startadvancedgame":
                startAdvancedgame(Integer.parseInt(arrays1[0]),arrays1[1], arrays1[2]);
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