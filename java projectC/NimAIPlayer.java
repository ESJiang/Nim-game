public class NimAIPlayer extends NimPlayer implements Testable
{
    public NimAIPlayer()
    {

    }

    @Override
    public int removeStone(int stoneCurrentCount, int stoneRemovalUpperBound)
    {
        if (stoneCurrentCount - 1 <= stoneRemovalUpperBound)
        {
            return stoneCurrentCount - 1;
        }
        else if (judge(stoneCurrentCount, stoneRemovalUpperBound) == false)
        {
            return minNumber(stoneCurrentCount, stoneRemovalUpperBound);
        }
        else
        {
            return (int)(Math.random() * stoneRemovalUpperBound) + 1;
        }
    }

    public int minNumber(int stoneCurrentCount, int stoneRemovalUpperBound)
    {
        int c = stoneCurrentCount;
        for (int i = 0; stoneCurrentCount - i * (stoneRemovalUpperBound + 1) - 1 > 0; i++)
        {
            int a = stoneCurrentCount - i * (stoneRemovalUpperBound + 1) - 1;
            if (c > a)
            {
                c = a;
            }
        }
        return c;
    }

    public boolean judge(int stoneCurrentCount, int stoneRemovalUpperBound)
    {
        boolean flag = false;
        for (int k = 0; stoneCurrentCount >= k * (stoneRemovalUpperBound + 1) + 1; k++)
        {
            if (stoneCurrentCount == k * (stoneRemovalUpperBound + 1) + 1)
            {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public String advancedMove(boolean[] available, String lastMove)
    {
        if ( (available.length % 2 == 0) && (lastMove.equals("")) )
        {
            int m = available.length / 2;
            return m + " " + 2;
        }
        if ( (available.length % 2 == 1) && (lastMove.equals("")) )
        {
            int m = (available.length + 1) / 2;
            return m + " " + 1;
        }

        String arrays[] = lastMove.split(" ");
        if (available.length % 2 == 0)
        {
            if (Integer.parseInt(arrays[0]) < available.length / 2)
            {
                int m = (Integer.parseInt(arrays[0]) * 2 + available.length + 2) / 2;
                return m + " " + arrays[1];
            }
            else
            {
                int m = (Integer.parseInt(arrays[0]) * 2 - available.length - 2) / 2;
                return m + " " + arrays[1];
            }
        }
        else
        {
            if (Integer.parseInt(arrays[0]) < (available.length + 1) / 2)
            {
                int m = (Integer.parseInt(arrays[0]) * 2 + available.length + 1) / 2;
                return m + " " + arrays[1];
            }
            else
            {
                int m = (Integer.parseInt(arrays[0]) * 2 - available.length - 1) / 2;
                return m + " " + arrays[1];
            }
        }
    }
}