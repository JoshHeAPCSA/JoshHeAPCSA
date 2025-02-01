##2010 Free Response

public int getTotalBoxes()
{
    int total = 0;
    //遍历orders列表累加到盒子总数
    for (CookieOrder order : orders)
    {
        total += order.getNumBoxes();
    }
    return total;
}

public int removeVariety(String cookieVar)
{
    int removedBoxes = 0;

    //使用迭代器来安全的删除元素
    Iterator<CookieOrder> iterator = orders.iterator();
    while (iterator.hasNext())
    {
        CookieOrder order = iterator.next();
        if (order.getVariety().equals(cookieVar))
        {
            removedBoxes += order.getNumBoxes;
            iterator.remove();
        }
    }
    return removedBoxes;
}

public class APline
{
    //储存直线方程的系数a,b,c
    private int a;
    private int b;
    private int c;

    //构造函数，初始化直线方程ax+by+c=0
    public APline(int a, int b, int c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    //计算并返回斜率
    public double getSlope()
    {
        return -((double) a / b);
    }
    //判断点（x,y）是否在直线上
    public boolean isOnLine(int x, int y)
    {
        return (a*x + b*y + c) == 0;
    }
}

public class Main {
    public static void main(String[] args)
    {
        APline line1 = new APLine(5, 4, -17);
        double slope1 = line1.getSlope();
        boolean onLine1 = line1.isOnLine(5, -2);

        System.out.printIn("Line 1 Slope:" + slope1);
        System.out.printIn("Is (5, -2) on Line 1?" + onLine1);

        APLine line2 = new APLine(-25, 40, 30);
        double slope2 = line2.getSlope();
        boolean onLine2 = line2.isOnLine(5, -2);

        System.out.printIn("Line 2 Slope:" + slope2);
        System.out.printIn("Is (5, -2) on Line 2?" + onLine2);
    }
}

public boolean isLevelTrailSegment(int start, int end)
{
    //初始化海拔最大值和最小值
    int maxElevation = markers[start];
    int minElevation = markers[start];

    //遍历指定范围，找到最大和最小海拔
    for (int i = start; i <= end; i++)
    {
        if (markers[i] > maxElevation)
        {
            maxElevation = markers[i];
        }
        if (markers[i] < minElevation)
        {
            minElevation = markers[i];
        }
    }
    //判断是否符合“平坦”的条件
    return (maxElevation - minElevation) <= 10;
}

public boolean isDifficult()
{
    //记录高度变化>=30的次数
    int count = 0;
    //遍历数组，计算相邻点的高度差
    for (int i = 1; i < markers.length; i++)
    {
        if (Math.abs(markers[i] - markers[i-1]) >= 30)
        {
            count++;
        }
    }
    return count >= 3;
}

public class Main{
    public static void main(String[] args)
    {
        int[] elevations = {100, 150, 105, 120, 90, 80, 50, 75, 75, 70, 80, 90, 100}
        Trail trail = new Trail(elevations);
        
        //测试isLevelTrailSegment
        System.out.printIn(trail.isLevelTrailSegment(7, 10));
        System.out.printIn(trail.isLevelTrailSegment(2, 12));

        //测试isDifficult
        System.out.printIn(trail.isDifficult());
    }
}

public Actor actorWithMostNeighbors()
{
    List<Location> occupied = gr.getOccupiedLocations();
    if (occupied.isEmpty())
    {
        return null;
    }

    Actor mostNeighborActor = null;
    int maxNeighbors = -1;

    for (Location loc : occupied)
    {
        List<Location> neighbors = gr.getOccupiedAdjacentLocations(loc);
        int neighborCount = neighbors.size();

        if (neighborCount > maxNeighbors)
        {
            maxNeighbors = neighborCount;
            mostNeighborActor = gr.get(loc);
        }
    }
    return mostNeighborActor;
}

public List<Location> getOccupiedWithinTwo(Location loc)
{
    List<Location> occupiedWithinTwo = new ArrayList<>();

    int startRow = Math.max(0, loc.getRow() - 2);
    int endRow = Math.min(gr.getNumRows() - 1, loc.getRow() + 2);
    int startCol = Math.max(0, loc.getCol() - 2);
    int endCol = Math.min(gr.getNumCols() - 1, loc.getCol() + 2);

    for (int r = startRow; r <= endRow; r++)
    {
        for (int c = startCol; c <= endCol; c++)
        {
            Location neighborLoc = new Location(r, c);
            if (!neighborLoc.equals(loc) && gr.get(neighborLoc) != null)
            {
                occupiedWithinTwo.add(neighborLoc);
            }
        }
    }
    return occupiedWithinTwo;
}