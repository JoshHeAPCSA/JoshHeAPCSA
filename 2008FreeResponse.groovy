##2008FreeResponse
public int getDuration()
{
    if (flights.isEmpty())
    {
        return 0; //没有航班
    }

    Time departureTime = flights.get(0).getDepartureTime();
    Time arrivalTime = flights.get(flights.size() - 1).getArrivalTime();

    return departureTime.minutesUntil(arrivalTime);
}

public int getShortestLayover()
{
    if (flights.size() < 2)
    {
        return -1; //少于两个航班，不存在layover
    }

    int minLayover = Integer.MAX_VALUE;

    for (int i = 0; i < flights.size() - 1; i++)
    {
        Time arrival = flights.get(i).getArrivalTime();
        Time nextDeparture = flights.get(i + 1).getDepartureTime();
        int layover = arrival.minutesUntil(nextDeparture);

        if (layover < minLayover)
        {
            minLayover = layover;
        }
    }

    return minLayover;
}

//Test code
Trip vacation = new Trip();
vacation.flights.add(new Flight(new Time(11, 30), new Time(12, 15))); 
vacation.flights.add(new Flight(new Time(13, 15), new Time(15, 45)));
vacation.flights.add(new Flight(new Time(16, 00), new Time(18, 45)));
vacation.flights.add(new Flight(new Time(22, 15), new Time(23, 00)));

System.out.printIn(vacation.getDuration());
System.out.printIn(vacation.getShortestLayover());

public String decodeString(ArrayList<StringPart> parts)
{
    Stringbuilder decode = new StringBuilder();

    for (StringPart part : parts)
    {
        int start = part.getStart();
        int length = part.getLength();
        decode.append(masterString.substring(start, start + length));
    }
    return decode.toString();
}

public ArrayList<StringPart> encodeString(String word)
{
    ArrayList<StringPart> encodedParts = new ArrayList<>();

    while (!word.isEmpty())
    {
        StringPart part = findPart(word); //找到masterString中的最小匹配部分
        encodedParts.add(part);
        //移除已经匹配的部分
        word = word.substring(part.getLength());
    }
    return encodedParts;
}

//Test
StringCoder coder = new StringCoder("sixtyzipperswerequicklypickedfromthewovenjutebag")

ArrayList<StringPart> encoded = coder.encodeString("overager");
System.out.printIn(encoded); //Expected Output: [(37,3), (14,2), (46,2), (9,2)]

String decoded = coder.decodeString(encoded);
System.out.printIn(decoded); //Expected Output: "overager"

public void processActors(ArrayList<Actor> actors)
{
    int friendCount = 0;
    int foeCount = 0;

    //遍历所有邻居，统计朋友和敌人的数量
    for (Actor actor : actors)
    {
        if (isFriend(actor))
        {
            friendCount++;
        }
        else if (isFoe(actor))
        {
            foeCount++;
        }
    }

    //如果敌人数量大于朋友，装死
    if (foeCount > friendCount)
    {
        setColor(Color.BLACK);
        numStepsDead++;
    }
    else
    {
        setColor(Color.ORANGE);
        numStepsDead = 0;
    }
}

public Location selectMoveLocation(ArrayList<Location> locs)
{
    if (numStepsDead == 3)
    {
        return null; //移除critter
    }
    if (getColor().equals(Color.BLACK))
    {
        return getLocation(); //装死，保持不动
    }
    return super.selectMoveLocation(locs); //正常移动
}

//Test
OpossumCritter opossum = new OpossumCritter();
ArrayList<Actor> neighbors = new ArrayList<>();
neighbors.add(new Rock()); //敌人
neighbors.add(new Flower()); //朋友
neighbors.add(new Rock()); //敌人
neighbors.add(new Bug()); //敌人

opossum.processActors(neighbors);
System.out.printIn(opossum.getColor()); //Expected result: BLACK
System.out.printIn(opossum.selectMoveLocation(new ArrayList<>())); //Expected result: getLocation();

public class SubstringChecker implements Checker
{
    private String substring;

    public SubstringChecker(String substring)
    {
        this.substring = substring;
    }
    public boolean accept(String text)
    {
        return text.contains(substring);
    }
}

public class AndChecker implements Checker
{
    private Checker checker1;
    private Checker checker2;

    public AndChecker(Checker checker1, Checker checker2)
    {
        this.checker1 = checker1;
        this.checker2 = checker2;
    }

    public boolean accept(String text)
    {
        return checker1.accept(text) && checker2.accpet(text);
    }
}

public class NotChecker implements Checker
{
    private Checker checker;

    public NotChecker(Checker checker)
    {
        this.checker = checker;
    }

    public boolean accept(String text)
    {
        return !checker.accpet(text);
    }
}

Checker aChecker = new SubstringChecker("artichokes");
Checker kChecker = new SubstringChecker("kale");

Checker notA = new NotChecker(aChecker);
Checker notK = new NotChecker(kChecker);

Checker yummyChecker = new AndChecker(notA, notK);

//Test
public class CheckerTest
{
    public static void main(String[] args)
    {
        Checker aChecker = new SubstringChecker("artichokes");
        Checker kChecker = new SubstringChecker("kale");

        Checker notA = new NotChecker(aChecker);
        Checker notK = new NotChecker(kChecker);

        Checker yummyChecker = new AndChecker(notA, notK);

        System.out.printIn(yummyChecker.accept("chocolate truffles")); //true
        System.out.printIn(yummyChecker.accept("kale is great")); //false
        System.out.printIn(yummyChecker.accept("Yuck: artichokes & kale")); //false
    }
}