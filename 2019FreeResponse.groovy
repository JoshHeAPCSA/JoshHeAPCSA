##2019 Free Response
public static int numberOfLeapYears(int year1, int year2)
{
    int count = 0;
    //遍历从year1到year2之间的所有年份
    for (int year = year1; year<= year2; year++)
    {
        //判断该年是否是闰年
        if (isLeapYear(year))
        {
            count++;
        }
    }
    return count;
}

public static int dayOfWeek(int month, int day, int year)
{
    //获取该年的1月1号是星期几
    int firstDay = firstDayOfYear(year);
    //获取该日期是这一年的第几天
    int nthDay = dayOfYear(month, day, year)
    //计算该日期是星期几
    return (firstDay + (nthDay - 1)) % 7
}

public class StepTracker
{
    //成员变量
    private int minSteps; //活跃日的最小步数
    private int totalSteps; //总步数
    private int daysTracked; //记录的天数
    private int activeDayCount; //活跃天数

    //构造函数，初始化最小步数
    public StepTracker(int minSteps)
    {
        this.minSteps = minSteps;
        this.totalSteps = 0;
        this.daysTracked = 0;
        this.activeDayCount = 0;
    }

    //记录每天的步数
    private void addDailySteps(int steps)
    {
        //累加当天的步数到总步数
        totalSteps += steps;
        //记录天数加1
        daysTracked++;
        //如果当天的步数大于或者等于minSteps，记录为活跃日
        if (steps >= minSteps)
        {
            activeDayCount++;
        }
    }

    //返回活跃天数
    public int activeDays()
    {
        return activeDayCount;
    }

    //计算并且返回平均步数
    public double averageSteps()
    {
        if (daysTracked == 0)
        {
            return 0.0;
        }
        return (double) totalSteps/daysTracked;
    }

}

public ArrayList<String> getDelimitersList(String[] tokens)
{
    ArrayList<String> delimiterList = new ArrayList<>();
    //遍历tokens数组
    for (String token : tokens)
    {
        //如果token是openDel或者closeDel,就把它加入结果列表
        if (tokens.equals(openDel) || token.equals(closeDel))
        {
            delimiterList.add(token);
        }
    }
    return delimiterList
}

public boolean isBalanced(ArrayList<String> delimiters)
{
    int openCount = 0;
    int closeCount = 0;
    //遍历delimiters列表
    for (String delimiter: delimiters)
    {
        if (delimiter.equals(openDel))
        {
            openCount++;
        }
        else if (delimiter.equals(closeDel))
        {
            closeCount++;
            if (closeCount > openCount)
            {
                return false;
            }
        }
    }
    //检查open和close分隔符的总数量是否相等
    return openCount == closeCount;
}

public LightBoard(int numRows, int numCols)
{
    //初始化lights数组
    lights = new boolean[numRows][numCols];

    //遍历每个灯并按照40%的概率设置为true
    for (int row = 0; row < numRows; row++)
    {
        for (int col = 0; col < numCols; col++)
        {
            //Math.random()返回【0，1）之间的随机数，若< 0.4，则设置为true
            lights[row][col] = Math.random() < 0.4;
        }
    }
}

public boolean evaluateLight(int row, int col)
{
    int onCount = 0;

    //统计当前列有多少个灯是亮的
    for (int r = 0; r < lights.length; r++)
    {
        if (lights[r][col])
        {
            onCount++;
        }
    }

    //当前灯是亮的情况
    if (lights[row][col])
    {
        //如果亮灯数量是偶数，则返回false
        if (onCount % 2 == 0)
        {
            return false;
        }
    }

    //当前灯是灭的情况
    else
    {
        //如果亮灯数量是3的倍数，则返回true
        if (onCount % 3 == 0)
        {
            return true;
        }
    }

    //否则，返回当前灯的状态
    return lights[row][col]
}