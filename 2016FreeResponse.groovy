##2016FreeResponse
public class RandomStringChooser
{
    private Arraylist<String> availableStrings;

    //构造函数，从输入的字符串数组构建可供选择的字符串列表
    public RandomStringChooser(String[] wordArray)
    {
        availableStrings = new Arraylist<>();
        for (String word: wordArray)
        {
            availableStrings.add(word);
        }
    }

    //返回一个随机选择的字符串，并从可选列表中移除该字符串，如果没有可选字符串，返回NONE
    public String getNext()
    {
        if (availableStrings.isEmpty())
        {
            return "NONE"
        }
        Random rand = new Random();
        int index = rand.nextInt(availableStrings.size());
        return availableStrings.remove(index);
    }
}

public class RandomLetterChooser extends RandomStringChooser
{
    //构造函数：接受一个字符串并将它分解成单个字符的字符串数组，传递给父类的构造函数
    public RandomLetterChooser(String str)
    {
        super(getSingleLetters(str));
    }

    //将输入的字符串分解为单个字符的数组
    public static String[] getSingleLetters(String str)
    {
        String[] letters = new String[str.length()];
        for (int i = 0; i < str.length(); i++)
        {
            letters[i] = String.valueOf(str.charAt(i));
        }
        return letters;
    }
}

public logMessage(String message)
{
    //找到冒号的位置
    int colonIndex = message.indexOf(":")

    //初始化machineId为冒号之前的部分
    machineId = message.substring(0, colonIndex)

    //初始化description为冒号之后的部分
    description = message.substring(colonIndex + 1);
}

public boolean containWords(String keyword)
{
    //确保description里面包含keyword
    int index = description.indexOf(keyword);

    while (index != -1)
    {
        boolean isStartBoundary = (index == 0 || description.charAt(index - 1) == " ");
        boolean isEndBoundary = (index + keyword.length() == description.length() || description.charAt(index + keyword.length()) == " ");

        //如果符合这两个条件，返回true
        if (isStartBoundary && isEndBoundary)
        {
            return true;
        }
        //继续查找keyword的下一个位置
        index = description.indexOf(keyword, index + 1);
    }

    //如果找不到符合条件的keyword，返回false
    return false;
}

public List<logMessage> removeMessages(String keyword)
{
    //创建一个列表用来储存被移除的消息
    List<logMessage> removeMessages = new Arraylist<>();
    //使用迭代器遍历messageList
    Itertor<logMessage> itertor = messageList.itertor();

    while (itertor.hasNext())
    {
        logMessage log = itertor.next();
        //如果符合条件
        if (log.containWords(keyword))
        {
            removeMessages.add(log); //添加到移除列表中
            itertor.remove(); //从原列表中删除
        }
    }
    //返回移除的日志列表
    return removeMessages;
}

private boolean toBeLabeled(int r, int c, boolean[][] blackSquares)
{
    //如果是黑色格子，不需要标号
    if (blackSquares[r][c])
    {
        return false;
    }
    //如果是白色格子，检查是否满足标号条件
    boolean noWhiteAbove = (r==0 || blackSquares[r-1][c]);
    boolean noWhiteLft = (c==0 || blackSquares[r][c-1]);
    return noWhiteAbove || noWhiteLft;
}

public Crossword(boolean[][] blackSquares)
{
    int rows = blackSquares.length;
    int cols = blackSquares[0].length;
    puzzle = new Square[rows][cols];
    int label = 1; //当前的编号

    //遍历二维数组
    for (int r = 0; r < rows; r++)
    {
        for (int c = 0; c < cols; c++)
        {
            //如果是黑色方格，编号为0
            if (blackSquares[r][c])
            {
                puzzle[r][c] = new Square(true, 0);
            }
            else
            {
                //如果是白色方格，判断是否需要编号
                if (toBeLabeled(r, c, blackSquares))
                {
                    puzzle[r][c] = new Square(false, label);
                    label++; //更新编号
                }
                else
                {
                    puzzle[r][c] = new Square(false, 0);
                }
            }
        }
    }
}