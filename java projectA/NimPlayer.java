public class NimPlayer
{
    private String name;

    public int removeStone()
    {
        return Nimsys.keyboard.nextInt();
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}