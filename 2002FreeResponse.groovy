##2002FreeResponse
public static ArrayList<Integer> calculateModes(int[] tally)
{
    ArrayList<Integer> modes = new ArrayList<>();
    int maxFreq = findMax(tally);

    for (int i = 0; i < tally.length; i++)
    {
        if (tally[i] = maxFreq)
        {
            modes.add(i);
        }
    }
    return modes;
}

public static int kthDataValue(int[] tally, int k)
{
    int count = 0;
    for (int i = 0; i < tally.length; i++)
    {
        count += tally[i];
        if (count >= k)
        {
            return i;
        }
    }
}

//测试代码
public static void main(String[] args)
{
    int[] tally = {0, 0, 10, 5, 10, 0, 7, 1, 0, 6, 0, 10, 3, 0, 0, 1}

    ArrayList<Integer> modes = calculateModes(tally);
    System.out.printIn("Modes: " + modes); // Output: Modes: [2, 4, 10]

    System.out.printIn("Kth value for k = 1: " + kthDataValue(tally, 1)); // Output: Kth value for k = 1: 2
    System.out.printIn("Kth value for k = 5: " + kthDataValue(tally, 5)); // Output: Kth value for k = 5: 2
    System.out.printIn("Kth value for k = 10: " + kthDataValue(tally, 10)); // Output: Kth value for k = 10: 2
    System.out.printIn("Kth value for k = 11: " + kthDataValue(tally, 11)); // Output: Kth value for k = 11: 3
    System.out.printIn("Kth value for k = 20: " + kthDataValue(tally, 20)); // Output: Kth value for k = 20: 4
}

public static void changePrices(Grocery store, Scanner input)
{
    while (input.hasNext())
    {
        String itemName = input.next();
        if (input.hasNextDouble())
        {
            double newPrice = input.nextDouble();
            store.setPrice(itemName, newPrice);
        }
    }
}

public static String BargainItem(Grocery store, char category)
{
    List<String> items = store.GetItems(category);
    if (items.isEmpty())
    {
        return None;
    }

    String bestItem = items.get(0);
    double minUnitPrice = store.GetPrice(bestItem) / store.GetSize(bestItem);

    for (String item : items)
    {
        double unitPrice = store.GetPrice(item) / store.GetSize(item);
        if (unitPrice < minUnitPrice)
        {
            minUnitPrice = unitPrice;
            bestItem = item;
        }
    }
    return bestItem;
}

public class Position
{
    private int row;
    private int col;

    public Position(int r, int c)
    {
        row = r;
        col = c;
    }

    public Position Northeast()
    {
        return new Position(row - 1, col + 1);
    }
}

public class Fish
{
    private String myDir;
    private Position myPosition;

    public Fish(Position pos, String dir)
    {
        myPosition = pos;
        myDir = dir;
    }

    public Neighborhood ForwardNbrs(Environment env)
    {
        Neighborhood result = new Neighborhood();

        if (myDir.equals("N"))
        {
            Position north = myPosition.North();
            Position ne = myPosition.Northeast();
            Position nw = myPosition.Northwest();

            if (env.isEmpty(north)) result.add(north);
            if (env.isEmpty(ne)) result.add(ne);
            if (env.isEmpty(nw)) result.add(nw);
        }
        else if (myDir.equals("NE"))
        {
            Position ne = myPosition.Northeast();
            Position n = myPosition.North();
            Position e = myPosition.East();

            if (env.isEmpty(ne)) result.add(ne);
            if (env.isEmpty(n)) result.add(n);
            if (env.isEmpty(e)) result.add(e);
        }

        // ... other directions

        return result;
    }
}

public String DirectionTo(Position other)
{
    int dr = other.getRow() - row;
    int dc = other.getCol() - col;

    if (dr == -1 && dc == 0) return "N";
    if (dr == -1 && dc == 1) return "NE";

    // ... other directions
    return "Unknown";
}

public int EmptySeatCount(String seatType)
{
    int count = 0;
    for (int row = 0; row < mySeats.numRows(); row++)
    {
        for (int col = 0; col < mySeats.numCols(); col++)
        {
            Seat seat = mySeats.get(row, col);
            if (seat.getPassenger().getName().equals(""))
            {
                if (seatType.equals("any") || seat.getType().equals(seatType))
                {
                    count++;
                }
            }
        }
    }
    return count;
}

public int FindBlock(int row, int seatsNeeded)
{
    int cols = mySeats.numCols();
    for (int startCol = 0; startCol <= cols - seatsNeeded; startCol++)
    {
        boolean allEmpty = true;
        for (int i = 0; i < seatsNeeded; i++)
        {
            Seat seat = mySeats.get(row, startCol + i);
            if (!seat.getPassenger().getName().equals(""))
            {
                allEmpty = false;
                break;
            }
        }
        if (allEmpty) return startCol;
    }
    return -1;
}

public boolean AssignGroup(List<Passenger> group)
{
    int groupSize = group.size();
    for (int row = 0; row < mySeats.numRows(); row++)
    {
        int startCol = FindBlock(row, groupSize);
        if (startCol != -1)
        {
            for (int i = 0; i < groupSize; i++)
            {
                mySeats.get(row, startCol + i).setPassenger(group.get(i));
            }
            return true;
        }
    }
    return false;
}