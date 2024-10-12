#2022 Free Response Questions
public int getScore() {
    int score = 0;

    //如果第一关完成，才有资格参加后续关卡
    if (levelOne.goalReached()) {
        score += levelOne.getPoints();

        //如果第二关完成，才有资格参加后续关卡
        if (levelTwo.goalReached()) {
            score += levelTwo.getPoints();

            //如果第三关完成，计入第三关分数
            if (levelThree.goalReached()) {
                score += levelThree.getPoints();
            }
        }
    }

    //如果是bonus关卡，将总分乘3
    if (isBonus()) {
        score *= 3;
    }
}

public int playManyTimes(int num) {
    //初始化最大分数
    int max = 0;

    //模拟num次游戏
    for (int i = 0; i < num; i++) {
        play();
        int score = getScore();

        //更新最大得分
        if (score > max) {
            max = score;
        }
    }
    return max;
}

public class Textbook extends Book {
    private int edition;

    //构造函数，初始化标题，价格和版本号
    public Textbook(String tbTile, double tbPrice, int tbEdition) {
        //调用父类的构造函数
        super(tbTitle, tbPrice);
        edition = tbEdition; //初始化版本号
    }

    //获取版本号
    public int getEdition() {
        return edition;
    }

    //判断当前书是否可以替代其他书
    public boolean canSubstituteFor(Textbook other) {
        return other.getTitle().equals(getTitle() && edition >= other.getEdition());
    }

    //获取书籍信息
    public String getBookInfo() {
        return super.getBookInfo() + "-" + edition;
    }
}

public double getAverageRating() {
    int sum = 0;
    //获取每个评论的评分并相加
    for (Review r: allReviews) {
        sum += r.getRating();
    }
    //计算平均评分
    return sum / allReviews.length;
}

public ArrayList<String> collectComments() {
    //保存符合条件的评论
    ArrayList<String> commentList = new ArrayList<String>();

    for (int i = 0; i < allReviews.length; i++) {
        //获取每个评论的内容
        String comment = allReviews[i].getComment();
        //检查评论中是否有感叹号
        if (comment.indexOf("!") >= 0) {
            String last = comment.substring(comment.length() - 1);
            if (!last.equals("!") && !last.equals(".")) {
                comment += "."; //如果没有标点符号，添加句号
            }

            //按照“索引-评论”的格式添加到列表中
            commentList.add(i + "-" + comment);
        }
    }
    return commentList;
}

public void repopulate() {
    for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid[0].length; col++) {
            //生成1到MAX之间的随机数
            int rval = (int) (Math.random() * MAX) + 1;
            //检查随机数是否被10整除且不被100整除，如果不是，就重新生成
            while (rval % 10 != 0 || rval % 100 == 0) {
                rval = (int) (Math.random() * MAX) + 1;
            }
            grid[row][col] = rval;
        }
    }
}

public int countIncreasingCols() {
    //初始化增加的列数量
    int count = 0;
    for (int col = 0; col < grid[0].length; col++) {
        boolean increasing = true;
        //检查是否递增，如果不是，就跳出循环
        for (int row = 1; row < grid.length; row++) {
            if (grid[row][col] < grid[row - 1][col]) {
                increasing = false;
                break;
            }
        }
        if (increasing) {
            count++; //没有问题就增加计数
        }
    }
    return count;
}