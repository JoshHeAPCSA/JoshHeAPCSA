##2015FreeResponse
public static int totalLetters(List<String> wordList)
{
    int total = 0;
    //累加每个单词的字符数
    for (String word: wordList)
    {
        total += word.length();
    }
    return total;
}

public static int basicGapWidth(List<String> wordList, int formattedLen)
{
    //获取单词字符总数
    int totalLetters = totalLetters(wordList);
    //间隙数等于单词数减一
    int numGaps = wordList.size() - 1
    return (formattedLen - totalLetters) / numGaps
}

public static String format(List<String> wordList, int formattedLen)
{
    //储存间隙宽度
    int basicGapWidth = basicGapWidth(wordList, formattedLen);
    //多余空格数
    int leftoverSpaces = leftoverSpaces(wordList, formattedLen);
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < wordList.size() - 1; i++)
    {
        result.append(wordList.get(i)); //添加单词
        for (int j = 0; j < basicGapWidth; j++)
        {
            result.append(" ");//添加基本间隙宽度的空格
        }
        if (leftoverSpaces > 0)
        {
            result.append(" ");//添加一个宽度的额外空格
            leftoverSpaces--; //更新leftoverSpaces
        }
    }
    result.append(wordList.get(wordList.size() - 1));//添加最后一个单词
    return result.toString();
}

public static int arraySum(int[] arr)
{
    //初始化总和
    int sum = 0;
    for (int num: arr)
    {
        sum += num; //累加每个元素
    }
    //返回总和
    return sum;
}

public static int[] rowSums(int[][] arr2D)
{
    //初始化结果数组
    int[] result = new int[arr2D.length];
    
    for (int i = 0; i < arr2D.length; i++)
    {
        //调用arraySum求每一行的和
        result[i] = arraySum(arr2D[i]);
    }
    return result;
}

public static boolean isDiverse(int[][] arr2D)
{
    //调用rowSums计算每行的和
    int[] rowSums = rowSums(arr2D);
    //存储不同的行和
    Set<Integer> uniqueSums = HashSet<>();
    for (int sum: rowSums)
    {
        if (uniqueSums.contains(sum))
        {
            return false;
        }
        //否则加入集合
        uniqueSums.add(sum);
    }
    return true; //所有行的和都不同，返回true
}

public class HiddenWord
{
    //隐藏单词
    private String hiddenWord;
    //构造函数，初始化隐藏单词
    public HiddenWord(String word)
    {
        hiddenWord = word;
    }

    //生成提示信息
    public String getHint(String guess)
    {
        //保存提示字符串
        StringBuilder hint = new StringBuilder();

        //遍历每个字符位置
        for (int i = 0; i < guess.length(); i++)
        {
            char guessChar = guess.charAt(i);

            if (guessChar == hiddenWord.charAt(i))
            {
                hint.append(guessChar);
            }
            else if (hiddenWord.indexOf(guessChar) != -1)
            {
                hint.append("+");
            }
            else//如果字母不存在
            {
                hint.append("*");    
            }
        }
    }
    return hint.toString(); //返回提示字符串
}

public getValueAt(int row, int col)
{
    for (SparseArrayEntry entry: entries)
    {
        if (entry.getRow() == row && entry.getCol() == col)
        {
            return entry.getValue();//返回匹配的条目值
        }
    }
}

public void removeColumn(int col)
{
    Iterator<SparseArrayEntry> iter = entries.iterator();

    while (iter.hasNext())
    {
        SparseArrayEntry entry = iter.next();
        if (entry.getCol() == col)
        {
            iter.remove(); //移除匹配列表索引的数值
        }
        else if (entry.getCol() > col)
        {
            //更新列索引，向左移动一列
            entries.set(entries.indexOf(entry), new SparseArrayEntry(entry.getRow(), entry.getCol() - 1, entry.getValue()));
        }
    }
    //更新列数
    numCols--;
}

public interface NumberGroup
{
    boolean contains(int num);
}

public class Range implements NumberGroup
{
    private int min;
    private int max;

    public Range(int min, int max)
    {
        this.min = min;
        this.max = max;
    }

    public boolean contains(int num)
    {
        return num >= min && num <= max;
    }
}

public class MultipleGroups implements NumberGroup
{
    private List<NumberGroup> groupList;

    public MultipleGroups(List<NumberGroup> groupList)
    {
        this.groupList = groupList;
    }

    public boolean contains(int num)
    {
        for (NumberGroup group: groupList)
        {
            if (group.contains(num))
            {
                return true;
            }
        }
        return false;
    }
}