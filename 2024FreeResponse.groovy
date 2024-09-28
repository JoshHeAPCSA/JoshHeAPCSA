public void simulateOneDay(int numBirds) {
    //生成随机数判断是否为异常情况（熊）
    double condition = Math.random();
    if (condition < 0.05) {
        currentFood = 0; //熊吃掉所有食物
    } else {
        //生成每只鸟的食物摄入量（10-50之间的整数）
        int eachBirdEats = (int) (Math.random() * 41 + 10);
        int totalEaten = numBirds * eachBirdEats;

        //判断食物是否足够
        if (totalEaten > currentFood) {
            currentFood = 0; //食物被吃光
        } else {
            currentFood -= totalEaten; //更新剩余食物
        }
    }
}

public int simulateManyDays(int numBirds, int numDays) {
    for (int daysSoFar = 0; daysSoFar < numDays; daysSoFar++) {
        if (currentFood == 0) {
            return daysSoFar; //食物被吃光，返回当前的天数
        }
        simulateOneDay(numBirds); //模拟一天
    }
    return numDays; //完全模拟结束，返回天数
}

public class Scoreboard
{
    private String team1Name, team2Name; //队伍名称
    private int whoseTurn; //当前轮到哪个队伍
    private int score1, score2; //队伍得分

    //构造函数，初始化队伍名字和初始状态
    public Scoreboard(String team1, String team2)
    {
        team1Name = team1;
        team2Name = team2;
        whoseTurn = 1;
        score1 = 0;
        score2 = 0;
    }

    //记录比赛得分
    public void recordPlay(int points)
    {
        if (points == 0) //当前回合结束，换队伍
        {
            if (whoseTurn == 1)
            {
                whoseTurn = 2;
            }
            else
            {
                whoseTurn = 1;
            }
        }
        else //活跃队伍得分
        {
            if (whoseTurn == 1)
            {
                score1 += points;
            }
            else
            {
                score2 += points;
            }
        }
    }

    //返回当前比赛状态，字符串拼接
    public String getScore()
    {
        String result = score1 + " - " + score2 + "-"; //拼接得分
        if (whoseTurn == 1)
        {
            result += team1Name;
        }
        else
        {
            result += team2Name;
        }
        return result;
    }
}

public boolean isWordChain()
{
    //遍历wordList,从第二个元素开始
    for (int i = 1; i < wordList.size(); i++)
    {
        String current = wordList.get(i); //当前元素
        String previous = wordList.get(i - 1); //前一个元素

        //如果当前元素不包含前一个元素作为子串，返回false
        if (current.indexOf(previous) == -1)
        {
            return false;
        }
    }
    return true; //遍历结束，返回true
}

public ArrayList<String> createList(String target)
{
    ArrayList<String> result = new ArrayList<String>(); //结果列表

    //遍历wordList中的每个字符串
    for (String current : wordList)
    {
        //如果字符串以target单词开头
        if (current.indexOf(target) == 0)
        {
            //去掉target部分，并加入结果列表
            String newStr = current.substring(target.length());
            result.add(newStr);
        }
    }
    //返回结果列表
    return result;
}

public Location getNextLoc(int row, int col)
{
    //当前元素在最后一行，只能向右移动
    if (row == grid.length - 1)
    {
        return new Location(row, col + 1);
    }
    //当前元素在最后一列，只能向下移动
    else if (col == grid[0].length - 1)
    {
        return new Location(row + 1, col);
    }
    //比较右方和下方元素，返回值较小的元素的位置
    else if (grid[row][col + 1] < grid[row + 1][col])
    {
        return new Location(row + 1, col);
    }
    else
    {
        return new Location(row, col + 1);
    }
}

public int sumPath(int row, int col)
{
    int sum = 0; //初始路径和为0

    //循环直到二维数组右下角
    while (row < grid.length - 1 || col < grid[0].length - 1)
    {
        sum += grid[row][col]; //加上当前元素的值

        //获取下个位置
        Location loc = getNextLoc(row, col);
        row = loc.getRow();
        col = loc.getCol();
    }
    return sum + grid[row][col]; //加上右下角元素的值
}