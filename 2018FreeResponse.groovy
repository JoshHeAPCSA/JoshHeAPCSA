## Multiple choice
1. C 
2. A
3. D
4. E
5. B
## 2018FreeResponseQuestions
public boolean simulate()
{
    int position = 0

    for (int i = 0; i < maxHops; i++)
    {
        position += hopDistance(); //青蛙移动的总距离

        //检查青蛙是否已经达到或者超过目标
        if (position >= goalDistance)
        {
            return true;
        }

        //检查青蛙当前位置是否为负
        if (position < 0)
        {
            return false;
        }
    }
    //青蛙未能在最大步数内达到目标
    return false;
}

public double runSimulations(int num)
{
    int successCount = 0;

    for (int i = 0; i < num; i++)
    {
        if (simulate())
        {
            successCount++;
        }
    }
    //计算成功次数的比例
    return (double) successCount/num;
}

public WordPairList(String[] words)
{
    allPairs = new ArrayList<WordPair>();

    for (int i = 0; i < words.length - 1; i++)
    {
        for (int j = i + 1; j < words.length; j++)
        {
            allPairs.add(new WordPair(words[i], words[j]));
        }
    }
}

public int numMatches()
{
    int matchCount = 0;
    for (WordPair pair : allPairs)
    {
        if (pair.getFirst().equals(pair.getSecond()))
        {
            matchCount++;
        }
    }
    return matchCount;
}

public class CodeWordChecker implements StringChecker
{
    private int minLength;
    private int maxLength;
    private String notAllowed;

    //构造函数1：接收最小长度，最大长度和不允许的字符串
    public CodeWordChecker(int minLength, int maxLength, String notAllowed)
    {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.notAllowed = notAllowed;
    }

    //构造函数2：仅接收不允许的字符串，长度默认在6到20之间
    public CodeWordChecker(String notAllowed)
    {
        this(6, 20, notAllowed);
    }

    //实现检查isValid
    public boolean isValid(String str)
    {
        return str.length() >= minLength && str.length() <= maxLength && !str.contains(notAllowed);
    }
}

public static int[] getColumn(int[][] arr2D, int c)
{
    int[] column = new int[arr2D.length];

    for (int r = 0; r < arr2D.length; r++)
    {
        column[r] = arr2D[r][c];
    }
    return column;
}

public static boolean isLatin(int[][] square)
{
    int[] firstRow = square[0];

    //检查第一行是否包含重复值
    if (containsDuplicates(firstRow))
    {
        return false;
    }

    //检查每一行是否包含第一行的所有值
    for (r = 1; r < square.length; r++)
    {
        if (!hasAllValues(firstRow, square[r]))
        {
            return false;
        }
    }

    //检查每一列是否包含第一行的所有值
    for (c = 0; c < square[0].length; c++)
    {
        int[] column = getColumn(square, c);
        if (!hasAllValues(firstRow, column))
        {
            return false;
        }
    }

    return true;
}