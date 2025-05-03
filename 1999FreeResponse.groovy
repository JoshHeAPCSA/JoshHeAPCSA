##1999FreeResponse
public static void computeGPA(List<StudentInfo> roster, int numStudents)
{
    for (int i = 0; i < numStudents; i++)
    {
        StudentInfo s = roster.get(i);
        if (s.creditHours > 0)
        {
            s.GPA = s.gradepoints / s.creditHours;
        }
        else
        {
            s.GPA = 0.0;
        }
    }
}

public static boolean isSenior(StudentInfo student)
{
    return student.creditHours >= 125 && student.GPA >= 2.0;
}

public static void fillSeniorList(List<StudentInfo> roster, int numStudents, List<StudentInfo> seniors, int[] numSeniors)
{
    int count = 0;
    for (int i = 0; i < numStudents; i++)
    {
        StudentInfo s = roster.get(i);
        if (isSenior(s))
        {
            seniors.add(s);
            count++;
        }
    }
    numSeniors[0] = count;
}

//测试代码
public class TestStudentUtils
{
    public static void main(String[] args)
    {
        List<StudentInfo> roster = new ArrayList<>();
        roster.add(new StudentInfo("King", 45, 171)); //3.8
        roster.add(new StudentInfo("Norton", 128, 448)); //3.5
        roster.add(new StudentInfo("Solo", 125, 350)); //2.8
        roster.add(new StudentInfo("Kramden", 150, 150)); //1.0
        roster.add(new StudentInfo("Zero", 0, 0)); //0.0

        StudentUtils.computeGPA(roster, roster.size());
        System.out.printIn("All Students GPA");
        for (StudentInfo s : roster)
        {
            System.out.printIn(s);
        }

        System.out.printIn("IsSenior")
        for (StudentInfo s : roster)
        {
            System.out.printIn(s.name, StudentUtils.isSenior(s));
        }

        List<StudentInfo> seniors = new ArrayList<>();
        int[] numSeniors = new int[1];
        StudentUtils.fillSeniorList(roster, roster.size(),seniors, numSeniors);
        System.out.printIn("Seniors List", numSeniors[0]);
        for (StudentInfo s : seniors)
        {
            System.out.printIn(s);
        }
    }
}

public static int wordIndex(String word, List<String> wordList, int numWords)
{
    for (int i = 0; i < numWords; i++)
    {
        if (wordList.get(i).compareTo(word) >= 0)
        {
            return i;
        }
    }
    return numWords;
}

public static int insertInOrder(String word, List<String> wordList, int numWords)
{
    int idx = wordIndex(word, wordList, numWords);
    if (idx < numWords && wordList.get(idx).equals(word))
    {
        //已经在列表中
        return numWords;
    }
    else
    {
        wordList.add(idx, word);
        return numWords + 1;
    }
}

public void div2()
{
    int carrydown = 0;
    for (int i = 0; i < digits.size(); i++)
    {
        int d = digits.get(i);
        int newDigit = d/2 + carrydown;
        digits.set(i, newDigit);
        carrydown = (d%2) * 5;
    }
    normalize();
}

public static BigInt divPos(BigInt dividend, BigInt divisor)
{
    BigInt zero = new BigInt(0);
    BigInt one = new BigInt(1);
    BigInt low = new BigInt(zero);
    BigInt high = new BigInt(dividend);
    while (low.compareTo(high) < 0)
    {
        BigInt mid = low.add(high).add(one);
        mid.div2();
        if (mid.multiply(divisor).compareTo(dividend) > 0)
        {
            high = mid.substract(one);
        }
        else
        {
            low = mid;
        }
    }
    return low;
}

public class Quilt
{
    private char[][] myBlock;
    private int rowsOfBlocks, colsOfBlocks;

    public Quilt(InputStream inFile, int rowsOfBlocks, int colsOfBlocks)
    {
        this.rowsOfBlocks = rowsOfBlocks;
        this.colsOfBlocks = colsOfBlocks;

        Scanner sc = new Scanner(inFile);
        int br = sc.nextInt();
        int bc = sc.nextInt();
        sc.nextLine();

        myBlock = new char[br][bc];
        for (int r = 0; r < br; r++)
        {
            String line = sc.nextLine();
            for (int c = 0; c < bc; c++)
            {
                myBlock[r][c] = line.charAt(c);
            }
        }
    }

    //将原方块原样放到qmat上，起点为（startRow, startCol)
    private void placeBlock(int startRow, int startCol, char[][] qmat)
    {
        int br = myBlock.length, bc = myBlock[0].length;
        for (int r = 0; r < br; r++)
        {
            for (int c = 0; c < bc; c++)
            {
                qmat[startRow + r][startCol + c] = myBlock[r][c];
            }
        }
    }

    //将方块上下翻转后放到qmat上，起点为（startRow, startCol)
    private void placeFlipped(int startRow, int startCol, char[][] qmat)
    {
        int br = myBlock.length, bc = myBlock[0].length;
        for (int r = 0; r < br; r++)
        {
            for (int c = 0; c < bc; c++)
            {
                //源(r,c)放到（br-1-r,c)
                qmat[startRow + (br - 1 - r)][startCol + c] = myBlock[r][c];
            }
        }
    }

    public char[][] quiltToMat()
    {
        int br = myBlock.length, bc = myBlock[0].length;
        int totalRows = br * rowsOfBlocks;
        int totalCols = bc * colsOfBlocks;
        char[][] qmat = new [totalRows][totalCols];

        //枚举每个块
        for (int bi = 0; bi < rowsOfBlocks; bi++)
        {
            for (int bj = 0; bj < colsOfBlocks; bj++)
            {
                int r0 = bi * br;
                int c0 = bj * bc;
                if ((bi + bj) % 2 == 0)
                {
                    placeBlock(r0, c0, qmat);
                }
                else
                {
                    placeFlipped(r0, c0, qmat);
                }
            }
        }
        return qmat;
    }
}