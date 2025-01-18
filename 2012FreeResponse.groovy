##2012FreeResponse
public void addClimb(String peakName, int climbTime)
{
    ClimbInfo newClimb = new ClimbInfo(PeakName, climbTime)
    climbList.add(newClimb)
}

public void addClimb(String peakName, int climbTime)
{
    ClimbInfo newClimb = new ClimbInfo(peakName, climbTime)
    for (int i = 0; i< climbList.size(); i++)
    {
        //比较当前项和列表里原有项字母大小顺序
        if (newClimb.getName().compareTo(climbList.get(i).getName()) < 0)
        {
            climbList.add(i, newClimb);
            return;
        }
    }
    climbList.add(newClimb);
}

public class RetroBug extends RetroBug
{
    //构造器
    public RetroBug()
    {
        super();
        hasActed = false;
    }

    public act()
    {
        //记录当前位置和朝向
        prevLocation =getLocation();
        prevDirection = getDirection();
        hasActed = true;

        //执行默认bug的行为
        super.act();
    }

    //实现restore
    public void restore()
    {
        if (!hasActed)
        {
            return;
        }

        //检查上一次的初始位置是否可以恢复
        if (canRestoreTo(prevLocation))
        {
            moveTo(prevLocation);
        }
        //恢复上一次的方向
        setDirection(prevDirection);
    }

    private boolean canRestoreTo(Location loc)
    {
        if(!getGrid().isValid(loc))
        {
            return false;//无效位置
        }

        Actor actor = getGrid().get(loc);
        return (actor == null || actor instanceof Flower);//有效位置，返回true
    }
}

public int findHorseSpace(String name)
{
    for (int i = 0; i < spaces.length; i++)
    {
        if (spaces[i] != null && spaces[i].getName().equals(name))
        {
            return i;//找到了匹配的马的名字
        }
    }
    return -1;//未找到
}

public void consolidate()
{
    Horse[] temp = new Horse[spaces.length];
    int index = 0;

    //将所有非空的马匹移动到temp数组中
    for (int i = 0; i < spaces.length; i++)
    {
        if (spaces[i] != null)
        {
            temp[index] = spaces[i];
            index++;
        }
    }

    //将temp数组复制回spaces数组
    for (int i = 0; i < spaces.length; i++)
    {
        if (i < index)
        {
            spaces[i] = temp[i]
        }
        else
        {
            spaces[i] = null;//填充多余位置为null
        }
    }
}

public int countWhitePixels()
{
    int count = 0;
    for (int row = 0; row < pixelValues.length; row++)
    {
        for (int col = 0; col < pixelValues[0].length; col++)
        {
            if (pixelValues[row][col] == WHITE)
            {
                count++;//发现一个白色像素
            }
        }
    }
    return count;
}

public void processImage()
{
    for (int row = 0; row < pixelValues.length; row++)
    {
        for (col = 0; col < pixelValues[0].length; col++)
        {
            //检查（row+2, col+2）是否有效
            if (row + 2 < pixelValues.length && col + 2 < pixelValues[0].length)
            {
                int newValue = pixelValues[row][col] - pixelValues[row+2][col+2];
                pixelValues[row][col] = Math.max(newValue, BLACK);//防止小于black
            }
        }
    }
}