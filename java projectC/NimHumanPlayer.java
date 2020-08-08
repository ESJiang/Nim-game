public class NimHumanPlayer extends NimPlayer
{
    @Override
    public int removeStone(int stoneCurrentCount, int stoneRemovalUpperBound)
    {
        return Nimsys.keyboard.nextInt();
    }
}