public void createArrays()
{
    int[] array1 = new int[10];
    for (int i = 0; i < array1.length; i++)
    {
        array1[i] = i + 1;
    }

    String[] array2 = {"FirstName", "MiddleName", "LastName"};
    //Print elements of array1
    System.out.printIn("Array1 elements:");
    for (int num: array1)
    {
        System.out.print(num + " ");
    }
    System.out.printIn;
    //print elements of array2
    System.out.printIn("Array2 elements:");
    for (String name: array2)
    {
        System.out.print(name + " ");
    }
    System.out.printIn;
}

public int[] createArrayOfOdds(int min, int max)
{
    //Calculate the size of the array
    int size = 0;
    for (int i = min; i <= max; i++)
    {
        if (i % 2 != 0)
        {
            size++;
        }
    }
    //Create array with correct size
    int[] oddNumbers = new int[size];
    //Fill the array with odd numbers
    int index = 0;
    for (int i = min; i <= max; i++)
    {
        if (i % 2 != 0)
        {
            oddNumbers[index] = i;
            index++;
        }
    }
    return oddNumbers;
}

public int limitAmplitude(int limit)
{
    //记录修改次数
    int changes = 0;
    for (int i = 0; i < samples.length; i++)
    {
        if (samples[i] > limit)
        {
            samples[i] = limit;
            changes++;
        }
        else if (samples[i] < - limit)
        {
            samples[i] = - limit;
            changes++;
        }
    }
    return changes;
}

public void trimSilenceFromBeginning()
{
    int firstNonZero = 0; //第一个非零值的索引
    //找到第一个非零值
    while (firstNonZero < samples.length && samples[firstNonZero] == 0)
    {
        firstNonZero++;
    }
    //创建新数组，从第一个非零值开始复制
    int[] newSamples = new int[samples.length - firstNonZero];
    for (int i = 0; i < newSamples.length; i++)
    {
        newSamples[i] = samples[firstNonZero + i];
    }
    samples = newSamples;
}

public class AttactiveCritter extends Critter
{
    public void processActors(ArrayList<Actor> actors)
    {
        Location currentLocation = getLocation();
        Grid<Actor> grid = getGrid();

        for (Actor actor : actors)
        {
            if (actor != null && actor != this)
            {
                //得到当前actor对AttractiveCritter的朝向
                int direction = actor.getLocation().getDirectionToward(currentLocation);
                //得到当前actor移动的目标位置
                Location targetLocation = actor.getLocation().getAdjacentLocation(direction);
                //判断相邻位置是否为空
                if (grid.isValid(targetLocation) && grid.get(targetLocation) == null)
                {
                    actor.moveTo(targetLocation);
                }
            }
        }
    }
    public void makeMove(location loc)
    {
        super.makeMove(loc);
    }
}

public int nextTankToFill(int threshold)
{
    //记录最低油量
    int minFuel = Integer.MAX_VAlUE;
    //记录最低油量tank index
    int minIndex = -1;

    //遍历所有油箱
    for (int i = 0; i < tanks.size(); i++)
    {
        int fuelLevel = tanks.get(i).getFuelLevel();
        //如果油量小于或等于限值，检查是否更新最低油量
        if (fuelLevel <= threshold && fuelLevel < minFuel)
        {
            minFuel = fuelLevel;
            minIndex = i;
        }
    }

    //如果没有找到符合条件的油箱，返回机器人index
    if (minIndex == -1)
    {
        return filler.getCurrentIndex();
    }
    return minIndex;
}

public void moveToLocation(int locIndex)
{
    //当前机器人位置
    int currentIndex = filler.getCurrentIndex();

    //如果目标位置在机器人右侧
    if (locIndex > currentIndex)
    {
        //如果机器人当前方向是向左，先改变方向
        if (!filler.isFacingRight())
        {
            filler.changeDirection();
        }
        filler.moveForward(locIndex - currentIndex);
    }
    //如果目标位置在机器人左侧
    else if (locIndex < currentIndex)
    {
        if (filler.isFacingRight)
        {
            filler.changeDirection();
        }
        filler.moveForward(currentIndex - locIndex)
    }
}

public void fillBlock(String str)
{
    int index = 0;
    //遍历二维数组的行和列
    for (int i = 0; i < numRows; i++)
    {
        for (int j = 0; j < numCols; j++)
        {
            //如果字符串中还有字母，就把它填进数组
            if (index < str.length())
            {
                letterBlock[i][j] = str.substring(index, index + 1;)
            }
            //如果字符串已经用完，用“A”填充
            else
            {
                letterBlock[i][j] = "A";
            }
            index++;
        }
    }
}

public String encryptMessage(String message)
{
    //如果输入字符串为空，返回空字符串
    if (message.isEmpty())
    {
        return "";
    }

    StringBuilder encryptedMessage = new StringBuilder();
    int blockSize = numRows * numCols;

    for (int start = 0 ; start < message.length(); start += blockSize)
    {
        //提取当前的字符串
        String substring;
        if (start + blockSize <= message.length())
        {
            substring = message.substring(start, start + blockSize);
        }
        else
        {
            substring = message.substring(start);//处理剩余部分
        }
        fillBlock(substring);//填充letterBlock
        //加密信息并把结果加和
        encryptedMessage.append(encryptBlock());
    }
    return encryptedMessage.toString();
}