##2007FreeResponse

public static boolean isSelfDivisor(int number)
{
    int temp = number;
    while (temp > 0)
    {
        //获取最后一位
        int digit = temp % 10;
        if (digit == 0 || number % digit != 0)
        {
            return false;
        }
        //去掉最后一位
        temp /= 10;
    }
    return true;
}

public static int[] firstNumSelfDivisors(int start, int num)
{
    //存储num个self-divisors
    int[] result = new int[num];
    //记录找到的self-divisors的数量
    int count = 0;
    //从start开始查找
    int current = start;

    while(count < num)
    {
        if (isSelfDivisor(current))
        {
            //存入数组
            result[count] = current;
            count++;
        }
        current++;
    }
    return result;
}

//测试代码
public class SelfDivisorTest
{
    public static void main(String[] args)
    {
        System.out.printIn(isSelfDivisor(128)); //True
        System.out.printIn(isSelfDivisor(26)); //False
        System.out.printIn(isSelfDivisor(36)); //True
        System.out.printIn(isSelfDivisor(101)); //False

        int[] selfDivisors = firstNumSelfDivisors(10, 5);
        for (int num: selfDivisors)
        {
            System.out.print(num + " "); // 11 12 15 22 24
        }
    }
}

private Fish findFish()
{
    //获取当前位置
    Location currentLoc = getLocation();
    //获取当前方向
    Direction dir = getDirection();

    for (int i = 1; i <= range; i++)
    {
        Location nextLoc = currentLoc.getAdjacentLocation(dir, i);

        if(!isValid(nextLoc))
        {
            break;
        }
        //获取该位置的对象
        Object obj = getThingAt(nextLoc);
        if (obj instanceof Fish)
        {
            return (Fish) obj;
        }
    }
    return null;
}

public void act()
{
    if (!isEnv())
    {
        return;
    }
    //查找最近的fish
    Fish prey = findFish();

    if (prey != null)
    {
        //获取猎物位置
        Location preyLoc = prey.getLocation();
        //吃掉猎物
        removeFish(prey);
        //移动到猎物位置
        moveTo(preyLoc);
    }
    else
    {
        super.act();
    }
}

public double getScore(ArrayList<String> key)
{
    double score = 0.0;

    for(int i = 0; i < answers.size(); i++)
    {
        String studentAnswer = answers.get(i);
        String correctAnswer = key.get(i);

        if (studentAnswer.equals(correctAnswer))
        {
            score += 1;
        } else if (!studentAnswer.equals("?"))
        {
            score -= 0.25;
        }
    }
    return score;
}

public String highestScoringStudent(ArrayList<String> key)
{
    double maxScore = Double.NEGATIVE_INFINITY;
    String topStudent = "";

    for (StudentAnswerSheet sheet : sheets)
    {
        //计算当前学生分数
        double score = sheet.getScore(key);

        if (score > maxScore)
        {
            maxScore = score;
            topStudent = sheet.getName();
        }
    }
    return topStudent;
}

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends Player
{
    private Random rand;

    public RandomPlayer(String aName)
    {
        super(aName);
        rand = new Random();
    }

    public String getNextMove(Gamestate state)
    {
        ArrayList<String> moves = state.getCurrentMoves();

        if (moves.isEmpty())
        {
            return "no move";
        }

        return moves.get(rand.nexInt(moves.size()));
    }
}

public void play()
{
    //打印初始游戏状态
    System.out.printIn(state);
    //游戏循环
    while(!state.isGameOver())
    {
        //获取当前玩家
        Player p = state.getCurrentPlayer();
        //让该玩家选择行动
        String move = p.getNextMove(state);

        System.out.printIn(p.getName() + "chooses:" + move);
        state.makeMove(move);
    }
    //游戏结束，跳出循环，判断获胜者
    Player winner = state.getWinner();
    if (winner != null)
    {
        System.out.printIn(winner.getName() + "wins");
    }
    else
    {
        System.out.printIn("Game ends in a draw");
    }
}