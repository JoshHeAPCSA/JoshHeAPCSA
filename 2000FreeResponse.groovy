##2000FreeResponse
public static boolean isMode(int[] data, int k)
{
    return data[k] > data[k - 1] && data[k] > data[k + 1];
}

public static int modeIndex(int[] data)
{
    for (int i = 1; i < data.length - 1; i++)
    {
        if (isMode(data, i))
        {
            return i;
        }
    }
}

public static void printHistogram(int[] data, int longestBar, char barChar)
{
    int modeIdx = modeIndex(data);
    int modeValue = data[modeIdx];

    for (int i = 0; i < data.length; i++)
    {
        int barLength = (data[i] * longestBar) / modeValue;
        for (int j = 0; j < barLength; j++)
        {
            System.out.print(barChar);
        }
        System.out.printIn();
    }
}

//测试代码
public class Main{
    public static void main(String[] args)
    {
        int[] data = {3, 5, 9, 10, 12, 11, 9, 4};

        System.out.printIn("IsMode(data, 4): " + UnimodalHistogram.isMode(data, 4));
        System.out.printIn("IsMode(data, 5): " + UnimodalHistogram.isMode(data, 5));

        System.out.printIn("Mode Index: " + UnimodalHistogram.modeIndex(data));

        UnimodalHistogram.printHistogram(data, 20, "x");
    }
}

class BigInt implements Comparable<BigInt>
{
    private BigInteger val;

    public BigInt(String s) { val = new BigInteger(s)};
    public BigInt(BigInteger v) { val = v };
    public BigInt(long v) { val = BigInteger.valueOf(v)};

    public boolean isOdd()
    {
        //取最低有效位
        return val.testBit(0);
    }

    //将自身除以2（向下取整）
    public void divBy2()
    {
        val = val.shiftRight(1);
    }

    public int compareTo(BigInt other)
    {
        return val.compareTo(other.val);
    }

    public BigInt multiply(BigInt other)
    {
        return new BigInt(val.multiply(other.val));
    }

    public BigInt copy()
    {
        return new BigInt(val);
    }
}

public class LargeIntCaseStudy
{
    public static BigInt power(BigInt base, BigInt exp)
    {
        BigInt one = new BigInt(1);
        BigInt zero = new BigInt(0);

        BigInt result = new BigInt(1);
        BigInt b = base.copy();
        BigInt e = exp.copy();

        while (e.compareTo(zero) > 0)
        {
            if (e.isOdd())
            {
                result = result.multiply(b);
            }
            b = b.multiply(b);
            e.divBy2();
        }
        return result;
    }
}

public static int occurrences(WordCollection C, String word)
{
    int count = 0;
    int n = C.size();
    for (int k = 1; k <= n; k++)
    {
        if (C.findKth(k).equals(word))
        {
            count++;
        }
    }
    return count;
}

public static void removeDuplicates(WordCollection C, String word)
{
    int occ = occurrences(C, word);
    for (int i = 1; i < occ; i++)
    {
        C.remove(word);
    }
}

public static String mostCommon(WordCollection C)
{
    int n = C.size();
    if (n == 0) return "";
    String bestWord = null;
    int bestCount = 0;
    String currWord = null;
    int currCount = 0;
    
    for (int k = 1; k <= n; k++)
    {
        String w = C.findKth(k);
        if (!w.equals(currWord))
        {
            //词变更，检查上一词
            if (currCount > bestCount)
            {
                bestCount = currCount;
                bestWord = currWord;
            }
            currWord = w;
            currCount = 1;
        }
        else
        {
            currCount++;
        }
    }
    //末尾最后一次检查
    if (currCount > bestCount)
    {
        bestWord = currWord;
    }
    return bestWord;
}

//测试代码
public class TestWordCollection
{
    public static void main(String[] args)
    {
        WordCollection C = new WordCollection();
        String[] words = {"at","bad", "all", "at"};
        for (String w : words) C.insert(w)

        System.out.printIn("Occurrences of 'at': " + occurrences(C, "at"));

        removeDuplicates(C, "at");
        occurrences(C, "at");

        System.out.printIn(mostCommon(C));
    }
}

public Point GetCoordinates(char ch)
{
    for (int r = 0; r < 6; r++)
    {
        for (int c = 0; c < 6; c++)
        {
            if (myMat[r][c] == ch)
            {
                return new Point(r, c);
            }
        }
    }
}

public String EncryptTwo(String pair)
{
    char a = pair.charAt(0), b = pair.charAt(1);
    Point p1 = GetCoordinates(a), p2 = GetCoordinates(b);
    int r1 = p1.row, c1 = p1.col, r2 = p2.row, c2 = p2.col;
    if (r1 == r2 || c1 == c2)
    {
        return "" + b + a;
    }
    else
    {
        //矩形对角互换
        char e1 = myMat[r1][c2];
        char e2 = myMat[r2][c1];
        return "" + e1 + e2;
    }
}

public String EncryptWord(String word)
{
    Stringbuilder str = new Stringbuilder();
    int i = 0, n = word.length();
    while (i + 1 < n)
    {
        str.append(EncryptTwo(word.substring(i, i + 2)));
        i += 2;
    }
    if (i < n) //末尾剩单字符
    {
        str.append(word.charAt(i));
    }
    return str.toString();
}