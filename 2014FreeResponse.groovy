##2014FreeResponse
public static String ScrambleWord(String word)
{
    if (word.length() <= 1)
    {
        return word;
    }

    StringBuilder scrambled = new StringBuilder(word);

    for (int i = 0; i < scrambled.length() - 1; i++)
    {
        //检查当前字母是否为A，并且下一个字母不是A
        if (scrambled.charAt(i) == 'A' && scrambled.charAt(i+1) != 'A')
        {
            //交换两个字母
            char temp = scrambled.charAt(i);
            scrambled.setCharAt(i, scrambled.charAt(i+1));
            scrambled.setCharAt(i+1, temp);

            //跳过下一个字符，因为已经参与过交换
            i++;
        }
    }
    return scrambled.toString();
}

public static void scrambleOrRemove(list<String> wordList)
{
    Itertor<String> iterator = wordList.iterator();

    while (iterator.hasNext())
    {
        //获取当前单词
        String originalWord = iterator.next();
        //对单词进行打乱
        String scrambledWord = scrambleWord(originalWord);

        if (originalWord.equals(scrambledWord))
        {
            iterator.remove();
        }
        else
        {
            int index = wordList.indexOf(originalWord);
            wordList.set(index, scrambledWord);
        }
    }
}

public class Director extends Rock
{
    private Color currentColor;

    //构造Director
    public Director()
    {
        currentColor = Color.RED;
        setColor(currentColor);
    }

    //写act方法
    public void act()
    {
        //切换颜色
        if (currentColor.equals(Color.RED))
        {
            currentColor = Color.GREEN;
        }
        else
        {
            currentColor = Color.RED;
        }
        setColor(currentColor);

        //如果颜色是绿色，处理相邻Actor
        if (currentColor.equals(Color.GREEN))
        {
            ArrayList<Location> neighbors = getGrid().getNeighbors(getLocation());
            for (Location loc : neighbors)
            {
                Actor neighbor = getGrid().get(loc);
                if (neighbor != null)
                {

                    neighbor.setDirection(neighbor.getDirection() + 90);
                }
            }
        }
    }
}

public SeatingChart(List<Student> studentList, int rows, int cols)
{
    //初始化二维数组
    seats = new Student[rows][cols];
    //初始化学生位置
    int studentIndex = 0;

    //填充二维数组，按列优先顺序
    for (int c = 0; c < cols; c++)
    {
        for (int r = 0; r < rows; r++)
        {
            if (studentIndex < studentList.size())
            {
                seats[r][c] = studentList.get(studentIndex);
                studentIndex++;//移动到下一个学生
            }
            else
            {
                seat[r][c] = null;//没有学生时，填入null
            }
        }
    }
}

public int removeAbsentStudents(int allowedAbsences)
{
    int removedCount = 0;//记录移除学生的数量
    //遍历二维数组
    for (int r = 0; r < seats.length; r++)
    {
        for (int c = 0; c < seats[r].length; c++)
        {
            if (seats[r][c] != null)//检查当前位置是否有学生
            {
                if (seats[r][c].getAbsenceCount() > allowedAbsences)
                {
                    seats[r][c] = null;//超过允许缺席次数，就被移除掉
                    removedCount++;
                }
            }
        }
    }
    return removedCount;
}

public class Trio implements MenuItem
{
    //储存三明治，沙拉，饮品变量
    private Sandwich sandwich;
    private Salad salad;
    private Drink drink;

    //构造函数，接收变量
    public Trio(Sandwich sandwich, Salad salad, Drink drink)
    {
        this.sandwich = sandwich;
        this.salad = salad;
        this.drink = drink;
    }

    //实现getName方法，返回拼接的名称
    public String getName()
    {
        return sandwich.getName() + '/' + salad.getName() + '/' + drink.getName();
    }

    //实现getPrice方法，返回两个最高价格单品的总和
    public double getPrice()
    {
        double price1 = sandwich.getPrice();
        double price2 = salad.getPrice();
        double price3 = drink.getPrice();

        //找到三个价格最高的两项
        if (price1 <= price2 && price1 <= price3)
        {
            return price2 + price3;//price1最小
        }
        else if (price2 <= price1 && price2 <= price3)
        {
            return price1 + price3;
        }
        else
        {
            return price1 + price2;
        }
    }
}