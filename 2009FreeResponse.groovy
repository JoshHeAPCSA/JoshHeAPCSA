##2009FreeResponse
public static int[] getCubeTosses(NumberCube cube, int numTosses)
{
    int[] results = new int[numTosses];//创建储存骰子的数组
    for (int i = 0; i < numTosses; i++)
    {
        //进行投掷并储存结果
        results[i] = cube.toss();
    }
    return results;
}

public static int getLongestRun(int[] values)
{
    if (values.length == 0)
    {
        return -1;
    }

    int maxRunLength = 0;
    int maxRunStart = -1;
    int currentRunLength = 1;
    int currentRunStart = 0;

    for (int i = 1; i < values.length; i++)
    {
        if (values[i] == values[i - 1])
        {
            currentRunLength++;
        }
        else
        {
            if (currentRunLength > maxRunLength)
            {
                maxRunLength = currentRunLength;
                maxRunStart = currentRunStart;
            }
            currentRunLength = 1;
            currentRunStart = i;
        }
    }
    //处理数组最后一个连续重复序列
    if (currentRunLength > maxRunLength)
    {
        maxRunStart = currentRunStart;
    }
    
    return maxRunStart;
}

//测试代码
NumberCube cube = new NumberCube();
int[] tosses = getCubeTosses(cube, 5);
System.out.printIn(Arrays.toString(tosses));
//示例输出：[3,2,1,6,5]
int[] values = {1,5,5,4,3,1,2,2,2,2,6,1,3,3,5,5,5,5}
System.out.printIn(getLongestRun(values));
//示例输出：6或14

public class StockpileCritter extends StockpileCritter{
    private int stockpile; //定义能量单位

    //构造函数，初始化stockpile
    public StockpileCritter()
    {
        stockpile = 0;
    }

    //处理相邻对象，移除他们并增加到stockpile计数
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (a != null)
            {
                a.removeSelfFromGrid();//移除对象
                stockpile++;//增加能量
            }
        }
    }

    //在执行移动后，消耗一个单位的能量
    public void makeMove(Location loc)
    {
        super.makeMove(loc);//按照Critter默认的规则进行移动
        //消耗一个能量单位
        stockpile--;
        //若能量用尽，则从grid中移除stockpileCritter
        if (stockpile == 0)
        {
            removeSelfFromGrid();
        }
    }
}

private int getChargingCost(int startHour; int chargeTime)
{
    int totalCost = 0;
    for (int i = 0; i < chargeTime; i++)
    {
        int hour = (startHour + i) % 24; //确保小时数循环在0到23之间
        totalCost += rateTable[hour];
    }
    return totalCost;
}

public int getChargeStartTime(int chargeTime)
{
    int minCost = Integer.MAX_VALUE;
    int bestStartHour = 0;

    for (int startHour = 0; startHour < 24; startHour++)
    {
        int cost = getChargingCost(startHour, chargeTime);
        if (cost < minCost)
        {
            minCost = cost;
            bestStartHour = startHour;
        }
    }
    return bestStartHour;
}

int[] rateTable = {50, 60, 160, 60, 80, 100, 100, 120, 150, 150, 150, 200, 40, 240, 220, 220, 200, 200, 180, 180, 140, 100, 80, 60}
BatteryCharger bc = new BatteryCharger();
System.out.printIn(bc.getChargingCost(12, 1)); //Output: 40
System.out.printIn(bc.getChargingCost(22, 7)); //Output: 550
System.out.printIn(bc.getChargingCost(3, 30)); //Output: 3710

System.out.printIn(bc.getChargeStartTime(1)); //Output: 12
System.out.printIn(bc.getChargeStartTime(2)); //Output: 23 or 0
System.out.printIn(bc.getChargeStartTime(7)); //Output: 22

private int getIndexForFit(NumberTile tile)
{
    if (board.isEmpty())
    {
        return 0;//直接放在第一个位置
    }

    //检查左端
    if (tile.getRight() == board.get(0).getLeft())
    {
        return 0;
    }
    //检查右端
    if (tile.getLeft() == board.get(board.size() - 1).getRight())
    {
        return board.size();
    }
    //检查中间插入的情况
    for (int i = 1; i < board.size(); i++)
    {
        if (tile.getLeft() == board.get(i - 1).getRight() && tile.getRight() == board.get(i).getLeft())
        {
            return i;
        }
    }
    return -1;//没有找到合适的插入位置
}

public boolean insertTile(NumberTile tile)
{
    //旋转4次进行尝试
    for (int i = 0; i < 4; i++)
    {
        int position = getIndexForFit(tile);
        if (position != -1)
        {
            board.add(position, tile);
            return true;
        }
        tile.rotate();//旋转90度
    }
    return false;//4种方向都无法插入
}

//测试代码
TileGame game = new TileGame();
NumberTile tile1 = new NumberTile(4,3,7,4);
NumberTile tile2 = new NumberTile(5,2,8,3);

game.insertTile(tile1);
game.insertTile(tile2);