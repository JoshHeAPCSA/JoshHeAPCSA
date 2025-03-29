##2003FreeResponse

public void updateTuition(String collegeName, int newTuition)
{
    for (College college : myColleges)
    {
        if (college.getName().equals(collegeName))
        {
            college.setTuition(newTuition);
            return;
        }
    }
}

public List<College> getCollegeList(String region, int low, int high)
{
    List<College> result = new ArrayList<>();
    for (College college : myColleges)
    {
        if (college.getRegion().equals(region))
        {
            int t = college.getTuition();
            if (t >= low && t <= high)
            {
                result.add(college);
            }
        }
    }
    return result;
}

//测试代码
public class Main
{
    public static void main(String[] args)
    {
        CollegeGroup colleges = new CollegeGroup();

        colleges.addCollege(new College("Colgate University", "Northeast", 27025));
        ...


        System.out.printIn("Before update:");
        for (College c : colleges.getCollegeList("Northeast", 0, 30000))
        {
            System.out.printIn(c);
        }
        colleges.updateTuition("Colgate University", 27500);

        System.out.printIn("After update:");
        for (College c : colleges.getCollegeList("Northeast", 0, 30000))
        {
            System.out.printIn(c);
        }

        System.out.printIn("Colleges in Southeast with tuition between 10000 and 20000:");
        List<College> filtered = colleges.getCollegeList("Southeast", 10000, 20000);
        for (College c : filtered)
        {
            System.out.printIn(c);
        }
    }
}

private boolean employeeIsEligible(Employee emp)
{
    int count = 0;
    if (emp.getAge() >= retireAge)
    {
        count++;
    }
    if (emp.getYearsOnJob() >= retireYears)
    {
        count++;
    }
    if (emp.getSalary() >= retireSalary)
    {
        count++;
    }
    return count >= 2;
}

public void processRetirements()
{
    List<Employee> newList = new ArrayList<>();
    double newBudget = 0.0;

    for (Employee emp: empList)
    {
        if (!employeeIsEligible(emp))
        {
            newList.add(emp);
            newBudget += emp.getSalary();
        }
    }

    empList = newList;
    salaryBudget = newBudget;
}

//测试代码
public class Main{
    public static void main(String[] args)
    {
        Company company = new Company(60, 30, 100000);

        company.addEmployee(new Employee(101, 61, 31, 120000));
        company.addEmployee(new Employee(102, 45, 10, 80000));
        company.addEmployee(new Employee(103, 50, 20, 90000));
        company.addEmployee(new Employee(104, 62, 35, 110000));
        company.addEmployee(new Employee(105, 55, 15, 95000));

        System.out.printIn("Before retirements:");
        company.printEmployees();

        company.processRetirements();

        System.out.printIn("After retirements:");
        company.printEmployees();
    }
}

public boolean hasTreasure(int row, int col)
{
    if (row < 0 || row >= myGrid.length || col < 0 || col >= myGrid[0].length)
    {
        return false;
    }
    return myGrid[row][col];
}

public int numAdjacent(int row, int col)
{
    int count = 0;
    for (int dr = -1; dr <= 1; dr++)
    {
        for (int dc = -1; dc <= 1; dc++)
        {
            if (dr == 0 && dc ==0) continue;
            if (hasTreasure(row + dr, col + dc))
            {
                count++;
            }
        }
    }
    return count;
}

public static int[][] computeCounts(TreasureMap theMap)
{
    int rows = theMap.numRows();
    int cols = theMap.numCols();
    int[][] result = new int[rows][cols];

    for (int r = 0; r < rows; r++)
    {
        for (int c = 0; c < cols; c++)
        {
            if (theMap.hasTreasure(r,c))
            {
                result[r][c] = 9;
            }
            else
            {
                result[r][c] = theMap.numAdjacent(r,c);
            }
        }
    }
    return result;
}

public int NumAlgaeAt(const Postion pos)
{
    return myAlgae[pos.Row()][pos.Col()];
}

public position MostAlgae(const Environment env, const Neighborhood nbrs)
{
    Position best = nbrs.ElementAt(0);
    int maxAlgae = env.NumAlgaeAt(best);

    for (int i = 1; i < nbrs.size(); i++)
    {
        Position p = nbrs.ElementAt(i);
        int algaeCount = env.NumAlgaeAt(p);
        if (algaeCount > maxAlgae)
        {
            best = p;
            maxAlgae = algaeCount;
        }
    }
    return best;
}

public void move(const Environment env)
{
    Position current = Location();

    if (env.NumAlgaeAt(current) > 0)
    {
        env.RemoveAlgae(current, 1);
        StepsSinceFed = 0;
    }
    else
    {
        StepsSinceFed++;
        if (StepsSinceFed >= 3)
        {
            env.RemoveFish(current);
        }
        else
        {
            Move();
        }
    }
}

//测试代码
Environment env(5,5);
env.AddAlgae(Postion(2,2), 3);
env.AddAlgae(Postion(2,3), 2);
env.AddAlgae(Postion(1,2), 1);

Fish f1(env, Position(2,2), Direction(NORTH));
f1.Act(env);
f1.Act(env);
env.RemoveAlgae(Position(2,2), 1);

f1.Act(env);//鱼在这个格子吃不到藻类

f1.Act(env);
f1.Act(env);
f1.Act(env);

...