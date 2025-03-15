##2005FreeResponse
public Reservation requestRoom(String guestName)
{
    //遍历房间，找到空房间
    for(int i = 0; i < rooms.length; i++)
    {
        if (rooms[i] == null)
        {
            Reservation newRes = new Reservation(guestName, i);
            rooms[i] = newRes;
            return newRes;
        }
    }
    //没有空房间
    waitlist.add(guestName);
    return null;
}

public Reservation cancelAndReassign(Reservation res)
{
    //获取取消的房间号
    int roomNumber = res.getRoomNumber();
    rooms[roomNumer] = null;

    //如果等待列表里有人
    if (!waitlist.isEmpty())
    {
        String nextGuest = waitlist.remove(0);
        Reservation newRes = new Reservation(nextGuest, roomNumber);
        rooms[roomNumber] = newRes;
        return newRes;
    }

    //如果等待队列为空
    return null;
}

//测试代码
public class HotelTest
{
    public static void main(String[] args)
    {
        Hotel myHotel = new Hotel(5);
        Reservation res1 = myHotel.requestRoom('Alice');
        Reservation res2 = myHotel.requestRoom('Bob');
        Reservation res3 = myHotel.requestRoom('Cathy');
        Reservation res4 = myHotel.requestRoom('David');
        Reservation res5 = myHotel.requestRoom('Eve');

        //没有空房间，加入等待队列
        Reservation res6 = myHotel.requestRoom('Frank');
        System.out.printIn("Frank should be on waitlist:" + (res6 == null));
        //取消Alice的房间，分配给Frank
        Resveration newRes = myHotel.cancelAndReassign(res1);
        System.out.printIn("Frank should get a room:" + (newRes != null && newRes.getRoomNumber() == res1.getRoomNumber()));

        //取消Bob的房间，没有人在等待队列
        Reservation newRes2 = myHotel.cancelAndReassign(res2);
        System.out.printIn("No one should get Bob's room:" + (newRes2 == null));
    }
}

public class Advance extends Ticket
{
    private int daysInAdvance;

    public Advance(int days)
    {
        super();
        daysInAdvance = days;
    }

    public double getPrice()
    {
        return daysInAdvance >= 10 ? 30.0 : 40.0;
    }
}

public class studentAdvance extends Advance
{
    public StudentAdvance (int days)
    {
        super(days);
    }

    public double getPrice()
    {
        return super.getPrice() / 2;
    }

    public String toString()
    {
        return super.toString() + " (Student ID required)";
    }
}

protected Location nextLocation()
{
    Location current = location();
    int direction = Direction();

    //计算下一步方向
    int newDirection = willZigRight ? direction + Location.HALF_RIGHT : direction + Location.HALF_LEFT;
    Location nextLoc = current.getAdjacentLocation(newDirection);

    //如果新位置在环境内且为空，返回新位置，否则返回当前位置
    if (theEnv.isValid(nextLoc) && theEnv.isEmpty(nextLoc))
    {
        return nextLoc;
    }
    else
    {
        return current;
    }
}

protected void move()
{
    Location nextLoc = nextLocation();
    //如果下一步和当前位置不同，则可以移动
    if (!nextLoc.equals(Location()))
    {
        moveTo(nextLoc);
    }
    else{
        //否则不能移动，并且反转方向
        setDirection(direction() + Location.HALF_CIRCLE);
    }
}

//测试代码
public class ZigZagFishTest
{
    public static void main(String[] args)
    {
        Environment env = new Environment(5, 5);
        Location startLoc = new Location(2, 2);
        ZigZagFish fish = new ZigZagFish(env, startLoc);

        env.add(fish);

        //连续移动10次，观察ZigZagFish的行为
        for (int i = 0; i < 10; i++)
        {
            System.out.printIn("Step " + (i + 1) + ": "+ fish.Location());
            fish.move();
        }
    }
}
//Expected output:
//Step 1: (2, 2)
//Step 2: (1, 3)
//Step 3: (0, 2)
//Step 4: (1, 1)
//Step 5: (2, 0)
//Step 6: (3, 1)
//Step 7: (4, 2)
//Step 8: (3, 3)
//Step 9: (2, 4)
//Step 10: (1, 3)

private double average(int first, int last)
{
    double sum = 0;
    for (int i = first; i <= last; i++)
    {
        sum += scores[i];
    }
    return sum / (last - first + 1);
}

private boolean hasImproved()
{
    for (int i = 1; i < scores.length; i++)
    {
        if (scores[i] < scores[i - 1])
        {
            return false;
        }
    }
    return true;
}

public double finalAverage()
{
    if (hasImproved())
    {
        return average(scores.length / 2, scores.length - 1);
    }
    else
    {
        return average(0, scores.length - 1);
    }
}

//测试代码
public class StudentRecordTest
{
    public static void main(String[] args)
    {
        int[] scores1 = {50, 50, 20, 80, 53};
        int[] scores2 = {20, 50, 50, 53, 80};
        int[] scores3 = {20, 50, 50, 80};

        StudentRecord student1 = new StudentRecord(scores1);
        StudentRecord student2 = new StudentRecord(scores2);
        StudentRecord student3 = new StudentRecord(scores3);

        System.out.printIn("Final average of student1: " + student1.finalAverage());
        System.out.printIn("Final average of student2: " + student2.finalAverage());
        System.out.printIn("Final average of student3: " + student3.finalAverage());
    }
}
//Expected output:
//Final average of student1: 50.6
//Final average of student2: 61.0
//Final average of student3: 65.0