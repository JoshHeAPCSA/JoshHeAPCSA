##2013FreeResponse
public DownloadInfo getDownloadInfo(String title)
{
    //遍历downloadlist中的每个DownloadInfo对象
    for (DownloadInfo info : downloadList)
    {
        //检查标题是否匹配
        if (info.getTitle().equals(title))
        {
            return info;//找到匹配对象时返回引用
        }
    }
    return null;
}

public void updateDownloads(List<String> titles)
{
    for (String title : titles)
    {
        //检查downloadList里是否已有该标题
        DownloadInfo existingInfo = getDownloadInfo(title)
        if (existingInfo != null)
        {
            //若已经存在，增加下载次数
            existingInfo.incrementTimesDownload();
        }
        else
        {
            //若不存在，创建新的DownloadInfo对象并添加到列表末尾
            downloadList.add(new DownloadInfo(title));
        }
    }
}

public TokenPass(int playerCount)
{
    //初始化board数组
    board = new int[playerCount];
    //填充随机令牌数量（范围是1到10）
    for (int i=0; i < playerCount; i++)
    {
        board[i] = (int) (Math.random() * 10) + 1;
    }
    //随机选择一个当前玩家
    currentPlayer = (int) (Math.random() * playerCount);
}

public void distributeCurrentPlayerTokens()
{
    //获取当前玩家的令牌
    int tokens = board[currentPlayer];
    board[currentPlayer] = 0;
    //从下一个玩家开始分配
    int position = currentPlayer + 1;
    while (tokens > 0)
    {
        //如果到达数组末尾，回到开头
        if (position >= board.length)
        {
            position = 0;
        }
        //分配一个令牌到当前位置
        board[position]++;
        tokens--;
        position++;//移动到下一位玩家
    }
}

public static ArrayList<Location> getEmptyLocations(Grid<Actor> grid)
{
    //存储空位置的列表
    ArrayList<Locations> emptyLocations = new ArrayList<>();

    for (int row = 0; row < grid.getNumRows(); row++)
    {
        for (int col = 0; col < grid.getNumCols(); col++)
        {
            Location loc = new Location(row, col);
            if (grid.get(loc) == null)
            {
                emptyLocations.add(loc)//如果当前位置为空，将该位置加入列表
            }
        }
    }
    return emptyLocations; //返回所有的空位置
}

public class JumpingCritter extends JumpingCritter
{
    Grid<Actor> grid = getGrid();

    //获取所有的空位置
    ArrayList<Location> emptyLocations = GridWorldUtilities.getEmptyLocations(grid);

    if (!emptyLocations.isEmpty())
    {
        //如果有空位置，随机选择一个位置进行跳跃
        int randomIndex = (int) (Math.random() * emptyLocations.size());
        Location randomLocation = emptyLocations.get(randomIndex);
        return randomLocation;
    }
    else
    {
        //如果没有空位置，将自己从网格中移除
        return null;
    }
}

public SkyView (int numRows, int numCols, double[] scanned)
{
    //初始化二维数组
    view = new double[numRows][numCols];
    //跟踪scanned数组的当前位置
    int index = 0;
    for (int row = 0; row < numRows; row++)
    {
        //偶数行，从左向右进行填充
        if (row % 2 == 0)
        {
            for (int col = 0; col < numCols; col++)
            {
                view[row][col] = scanned[index++];
            }
        }
        else //奇数行，从右向左进行填充
        {
            for (int col = numCols - 1; col >= 0; col--)
            {
                view[row][col] = scanned[index++];
            }

        }
    }
}

public double getAverage (int startRow, int endRow, int startCol, int endCol)
{
    double sum = 0;
    int count = 0;

    for (int row = startRow; row <= endRow; row++)
    {
        for (int col = startCol; col <= endCol; col++)
        {
            sum += view[row][col];
            count++;
        }
    }
    return sum / count;
}