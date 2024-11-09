##2017FreeResponseQuestions

pubic class Digits{
    private ArrayList<Integer> digitList;

    //构造Digits对象，表示给定的非负整数num
    public Digits(int num)
    {
        digitList = new ArrayList<>();
        if (num == 0)
        {
            digitList.add(0);
        }
        else{
            while (num > 0)
            {
                digitList.add(0, num % 10); //将每位数字加到列表的头部
                num /= 10;
            }
        }
    }

    public boolean isStrictlyIncreasing()
    {
        for (int i = 1; i < digitList.size(); i++)
        {
            if (digitList.get(i) <= digitList.get(i - 1))
            {
                return false;
            }
        }
        return true;
    }
}

public class MultPractice implements StudyPractice
{
    private int first;
    private int second;

    public MultPractice(int firstInteger, int initialSecondInteger)
    {
        first = firstInteger;
        second = initialSecondInteger;
    }

    public String getProblem()
    {
        return first + "TIMES" + second;
    }

    public void nextProblem()
    {
        second++;
    }
}

public void replaceNthOccurrence(String str, int n, String repl)
{
    //找到第n次出现的位置
    int loc = findNthOccurrence(str, n);

    //如果找到的不是-1（说明第n次出现的str存在）
    if (loc != -1)
    {
        //使用substring方法将currentPhrase分成三部分
        currentPhrase = currentPhrase.substring(0, loc) + repl + currentPhrase.substring(loc + str.length());
    }
}

public int findLastOccurrence(String str)
{
    //初始化从第一次开始查找
    int n = 1;
    //不断查找第n+1次出现的位置，直到找不到为止
    while (findNthOccurrence(str, n+1) != -1)
    {
        n++; //如果找到，继续查找下一次str出现的位置
    }
    return findNthOccurrence(str, n);
}

public static Position findPosition(int num, int[][] intArr)
{
    for (int r = 0; r < intArr.length; r++)
    {
        for (int c = 0; c < intArr[0].length; c++)
        {
            if (intArr[r][c] == num)
            {
                return new Position(r, c);
            }
        }
    }
    return null;
}

public static Position[][] getSuccessorArray(int[][] intArr)
{
    Position[][] successorArr = new Position[intArr.length][intArr[0].length]
    for (int r = 0; r < intArr.length; r++)
    {
        for (int c = 0; c < intArr[0].length; c++)
        {
            successorArr[r][c] = findPosition(intArr[r][c] + 1, intArr);
        }
    }
    return successorArr;
}