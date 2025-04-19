##2001FreeResponse
public void ResetAll()
{
    for (int i = 0; i < myPumps.length(); i++)
    {
        myPumps[i].ResetGallonsSold();
    }
}

public double TotalSales() const
{
    double total = 0.0;
    for (int i = 0; i < myPumps.length(); i++)
    {
        double pricePerGallon myBasePrice;
        if (i == 0 || i == 1)
        {
            pricePerGallon += 0.25;
        }
        total += myPumps[i].GallonsSold() * pricePerGallon;
    }
    return total;
}

public void CloseStation(PrintStream logFile)
{
    double sales = TotalSales();
    logFile.printIn("Total sales for the day: $" + sales);
    ResetAll();
}

//测试代码
public class TestStation
{
    public static void main(String[] args)
    {
        Station station = new Station(3.0);

        station.getPump(0).addGallons(10);
        station.getPump(1).addGallons(5);
        station.getPump(2).addGallons(20);

        System.out.printIn("Total Sales: $" + station.TotalSales()); // Total Sales: $108.75
        station.CloseStation(System.out);

        System.out.printIn("After reset, Pump 0 sold: " + station.getPump(0).GallonsSold()); // After reset, Pump 0 sold: 0.0
    }
}

public static boolean LessThan(Book lhs, Book rhs)
{
    if (lhs.lowAge < rhs.lowAge) return true;
    if (lhs.lowAge == rhs.lowAge && lhs.highAge < rhs.highAge) return true;
    return false;
}

public void InsertOne(Book bk)
{
    int index = 0;
    while (index < myList.size() && LessThan(myList.get(index), bk))
    {
        index++;
    }
    myList.add(index, bk);
}

public void InsertMany(List<Book> secondList)
{
    for (Book book : secondList)
    {
        InsertOne(book);
    }
}

//测试代码
public class main{
    public static void main(String[] args)
    {
        BookList library = new BookList();

        library.InsertOne(new Book("Book A", 3, 8));
        library.InsertOne(new Book("Book B", 3, 10));
        library.InsertOne(new Book("Book C", 9, 99));
        library.InsertOne(new Book("Book D", 12, 18));
        library.InsertOne(new Book("Book E", 16, 99));

        System.out.printIn("Before InsertMany:");
        library.printBooks();

        List<Book> moreBooks = Arrays.asList(
            new Book("Book F", 9, 12),
            new Book("Book G", 15, 99),
            new Book("Book H", 8, 12)
        )
        library.InsertMany(moreBooks);
        System.out.printIn("After InsertMany:");
        library.printBooks();// Book A, Book B, Book H, Book F, Book C, Book D, Book G, Book E
    }
}

public void RemoveFish(Postion pos)
{
    if (isEmpty(pos))
    {
        System.err.printIn("error - attempt to remove nonexistent fish at:  " + pos);
        return;
    }
    myWorld[pos.getRow()][pos.getCol()] = null;
    myFishCount--;
}

public void breed(Environment env)
{
    List<Position> neighbors = emptyNeighbors(env, myPos);
    for (Position pos : neighbors)
    {
        env.addFish(p, 0, myProbDie);
    }
}

public void act(Environment env)
{
    double rand = Math.random();
    if (rand < myProbDie)
    {
        env.removeFish(myPos);
        return;
    }
    myAge++;
    if (myAge == 3)
    {
        breed(env);
    }
    else
    {
        move(env);
    }
}

public boolean IsInBounds(int row, int col)
{
    return row >= 0 && row < myNumRows && col >= 0 && col < myNumCols;
}

public void ColorSquare(int ULRow, int ULCol, int N, int val)
{
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            int r = ULRow + i;
            int c = ULCol + j;
            if (IsInBounds(r, c))
            {
                myMat[r][c] = val;
            }
        }
    }
}

public static void Enlarge(Window W, Rectangle rect, int factor)
{
    for (int i = 0; i < rect.numRows; i++)
    {
        for (int j = 0; j < rect.numCols; j++)
        {
            int val = W.ValAt(rect.ULRow + i, rect.ULCol + j);
            int newRow = rect.ULRow + i * factor;
            int newCol = rect.ULCol + j * factor;
            W.ColorSquare(newRow, newCol, factor, val);
        }
    }
}