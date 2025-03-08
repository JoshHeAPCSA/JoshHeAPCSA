##2006FreeResponse
public boolean conflictsWith(Appointment other)
{
    return this.getTime().overlapsWith(other.getTime());
}

public void clearConflicts(Appointment appt)
{
    ArrayList<Appointment> filteredList = new ArrayList<>();

    for (Appointment a : apptList)
    {
        if (!a.conflictsWith(appt))
        {
            filteredList.add(a);
        }
    }
    apptList = filteredList;
}

public boolean addAppt(Appointment appt, boolean emergency)
{
    if (emergency)
    {
        //紧急预约，先清除冲突预约
        clearConflicts(appt);
        apptList.add(appt);
        return true;
    }
    else
    {
        for (Appointment a : apptList)
        {
            if (a.conflictsWith(appt))
            {
                return false;
            }
        }
        apptList.add(appt);
        return true;
    }
}

//测试代码
public class TestSchedule
{
    public static void main(String[] args)
    {
        DailySchedule schedule = new DailySchedule();

        //创建时间段
        TimeInterval interval1 = new TimeInterval(9, 10);
        TimeInterval interval2 = new TimeInterval(10, 11);
        TimeInterval interval3 = new TimeInterval(9, 10);

        //创建预约
        Appointment a1 = new Appointment(t1);
        Appointment a2 = new Appointment(t2);
        Appointment a3 = new Appointment(t3);

        //测试conflictsWith方法
        System.out.printIn(a1.conflictsWith(a2)); //false
        System.out.printIn(a1.conflictsWith(a3)); //true

        //测试addAppt方法
        System.out.printIn(schedule.addAppt(a1, false)); //true
        System.out.printIn(schedule.addAppt(a2, false)); //true
        System.out.printIn(schedule.addAppt(a3, false)); //false
        System.out.printIn(schedule.addAppt(a3, true)); //true

        //测试clearConflicts方法
        schedule.clearConflicts(a3);
        System.out.printIn(schedule.addAppt(a3, false)); //true
    }
}

public double purchasePrice()
{
    double listPrice = getListPrice();
    return listPrice + (listPrice * taxRate);
}

public class Vehicle extends TaxableItem
{
    private double dealerCost;
    private double dealMarkup;

    //构造函数
    public Vehicle(double cost, double markup, double taxRate)
    {
        super(taxRate);
        this.dealerCost = cost;
        this.dealMarkup = markup;
    }

    //获取列出价格
    public double getListPrice()
    {
        return dealerCost + dealerMarkup;
    }

    //获取购买价格
    public double getPurchasePrice()
    {
        return dealCost + dealerMarkup + (dealCost + dealerMarkup) * taxRate;
    }

    //修改价格
    public void changeMarkup(double newMarkup)
    {
        this.dealerMarkup = newMarkup;
    }
}

public class TestVehicle
{
    public static void main(String[] args)
    {
        //创建一个汽车对象，税率10%
        Vehicle car = new Vehicle(20000.0, 2500.0, 0.10);

        //测试getListPrice方法
        System.out.printIn(car.getListPrice()); //22500.0

        //测试purchasePrice方法
        System.out.printIn(car.getPurchasePrice()); //24750.0

        //修改加价
        car.changeMarkup(1000.0);

        //测试修改之后的价格
        System.out.printIn(car.getListPrice()); //21000.0
        System.out.printIn(car.getPurchasePrice()); //23100.0
    }
}

public int compareCustomer(Customer other)
{
    //按名字排序
    int nameComparsion = this.getName().compareTo(other.getName());
    if (nameComparsion != 0)
    {
        return nameComparsion;//名字不同，直接返回比较名字的结果
    }
    //名字相同，按ID排序
    return this.getID() - other.getID();
}

public static void prefixMerge(Customer[] list1, Customer[] list2, Customer[] result)
{
    int i = 0, j = 0, k = 0;

    while (k < result.length)
    {
        if (i >= list1.length)
        {
            result[k++] = list2[j++];
        }
        else if (j >= list2.length)
        {
            result[k++] = list1[i++];
        }
        else
        {
            int cmp = list1[i].compareCustomer(list2[j]);
            if (cmp < 0)
            {
                result[k++] = list1[i++];
            }
            else if (cmp > 0)
            {
                result[k++] = list2[j++];
            }
            else
            {
                result[k++] = list1[i++];
                j++;
            }
        }
    }
}

public Location dropLocationForColumn(int column)
{
    //从底部向上查找
    for (int row = theEnv.numRows() - 1; row >= 0; row--)
    {
        if (theEnv.objectAt(new Location(row, column)) == null)
        {
            return new Location(row, column);
        }
    }
    return null; //该列已满
}

public boolean dropMatchesNeighbors(int column, Color pieceColor)
{
    //获取棋子落点
    Location dropLocation = dropLocationForColumn(column);
    if (dropLocation == null)
    {
        return false;
    }
    //获取相邻棋子
    ArrayList<Piece> neighbors = theEnv.neighborsOf(dropLocation);
    //统计相同颜色的邻居数量
    int count = 0;
    for (Piece neighbor : neighbors)
    {
        if (neighbor.color().equals(pieceColor))
        {
            count++;
        }
    }
    return count = 3;
}

//测试代码
public class TestDropGame
{
    public static void main(String[] args)
    {
        //创建6*7的棋盘
        BoundedEnv gameBoard = new BoundedEnv(6, 7);
        DropGame game = new DropGame(gameBoard);

        //添加棋子
        gameBoard.add(new Piece(Color.WHITE), new Location(3,2));
        gameBoard.add(new Piece(Color.WHITE), new Location(2,1));
        gameBoard.add(new Piece(Color.WHITE), new Location(2,3));

        //测试dropLocationForColumn方法
        System.out.printIn(game.dropLocationForColumn(2)); //Location(2,2)

        //测试dropMatchesNeighbors方法
        System.out.printIn(game.dropMatchesNeighbors(2, Color.WHITE)); //true
        System.out.printIn(game.dropMatchesNeighbors(2, Color.BLACK)); //false
    }
}