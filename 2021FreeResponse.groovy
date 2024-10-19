public int scoreGuess(string Guess) {
    int count = 0;
    int guessLength = guess.length();
    //遍历secret字符串，找到guess出现的次数
    for (int i = 0; i <= secret.length() - guessLength; i++)
    {
        
        //如果当前的字符串等于guess，增加计数
        if (secret.substring(i, i + guessLength).equals(guess)) {
            count++;
        }
    }
    //返回出guessScore
    return count * guessLength * guessLength
}

public String findBetterGuess(String guess1, String guess2)
{
    int score1 = scoreGuess(guess1);
    int score2 = scoreGuess(guess2);

    if (score1 > score2)
    {
        return guess1;
    }
    else if (score2 > score1)
    {
        return guess2;
    }
    else
    {
        return guess1.compareTo(guess2) > 0 ? guess1 : guess2 //A?B:C A(Boolean) true, return B; A false, return C
    }

}

public boolean canSeat(int n)
{
    //获取组合桌子的总座位数 ： table 1座位数 + table 2 座位数 - 2
    if (table1.getNumSeats() + table2.getNumSeats() - 2 >= n)
    {
        return true;
    }
    else
    {
        return false;
    }
}

public double getDesirability()
{
    //如果两张桌子高度相同
    if (table1.getHeight() == table2.getHeight())
    {
        //返回平均值
        return (table1.getViewQuality() + table2.getViewQuality()) / 2
    }
    //如果高度不同
    else
    {
        return ((table1.getViewQuality() + table2.getViewQuality()) / 2) - 10; 
    }
}

public void addMembers(String[] names, int gradYear)
{
    //遍历names数组
    for (String name: names) {
        //创建新的MemberInfo对象，默认hasGoodStanding = true
        MemberInfo newMember = new MemberInfo(name, gradYear, true);
        //将新成员添加到memberList
        memberList.add(newMember)
    }
}

public ArrayList<MemberInfo> removeMember(int year)
{
    ArrayList<MemberInfo> graduatedInGoodStanding = new ArrayList<MemberInfo>();
    Iterator<MemberInfo> iter = memberList.iterator();

    //使用迭代器遍历memberList
    while (iter.hasNext())
    {
        MemberInfo member = iter.next();
        //如果该成员已经毕业
        if (member.getGradYear() <= year)
        {
            //如果该成员保持良好记录，添加到返回列表
            if (member.inGoodStanding())
            {
                graduatedInGoodStanding.add(member)
            }
            //从memberList中移除该成员
            iter.remove();
        }
    }
    //返回符合条件的成员列表
    return graduatedInGoodStanding;
}

public static boolean isNonZeroRow(int[][] array2D, int r)
{
    //遍历第r行的每个元素
    for (int col = 0; col < array2D[r].length; col++)
    {
        //如果有元素为0，返回false
        if (array2D[r][col] == 0)
        {
            return false;
        }
    }
    return true
}

public static int[][] resize(int[][] array2D)
{
    //计算非零行的数量
    int nonZeroRows = numNonZeroRows(array2D);

    //创建新的二维数组，用于储存没有零值的行
    int[][] newArray = new int[nonZeroRows][];

    //复制没有零值的行到新数组
    int newRowIndex = 0;
    for (int row = 0; row < array2D.length; row++)
    {
        if (isNonZeroRow(array2D, row))
        {
            //复制当前行到新数组
            newArray[newRowIndex] = array2D[row];
            newRowIndex++;
        }
    }
    //返回新的二维数组
    return newArray;
}