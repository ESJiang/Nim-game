public class NimPlayer
{
    private String userName,givenName,familyName;
    private int numberPlayed, numberWon;

    public int removeStone()
    {
        return Nimsys.keyboard.nextInt();
    }

    public String getGivenName(NimPlayer[] arr, int index)
    {
        return arr[index].givenName;
    }

    public void setGivenName(NimPlayer[] arr, String newName, int index)
    {
        arr[index].givenName = newName;
    }

    public String getFamilyName(NimPlayer[] arr, int index)
    {
        return arr[index].familyName;
    }

    public void setFamilyName(NimPlayer[] arr, String newName, int index)
    {
        arr[index].familyName = newName;
    }

    public void setUserName(NimPlayer[] arr, String newName, int index)
    {
        arr[index].userName = newName;
    }

    public String getUserName(NimPlayer[] arr, int index)
    {
        return arr[index].userName;
    }

    public int getNumberPlayed(NimPlayer[] arr, int index)
    {
        return arr[index].numberPlayed;
    }

    public void setNumberPlayed(NimPlayer[] arr, int newNumber, int index)
    {
        arr[index].numberPlayed = newNumber;
    }

    public int getNumberWon(NimPlayer[] arr, int index)
    {
        return arr[index].numberWon;
    }

    public void setNumberWon(NimPlayer[] arr, int newNumber, int index)
    {
        arr[index].numberWon = newNumber;
    }
}