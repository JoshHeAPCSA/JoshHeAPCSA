##2004FreeResponse
public int numWordsOfLength(int len)
{
    int count = 0;
    for (int i = 0; i < myList.size(); i++)
    {
        String word = myList.get(i);
        if (word.length() == len)
        {
            count++;
        }
    }
    return count;
}

public void removeWordsOfLength(int len)
{
    for (int i = 0; i < myList.size(); i++)
    {
        String word = myList.get(i);
        if (word.length() == len)
        {
            myList.remove(i); //不增加i,下一次检查新占位的元素
        }
        else
        {
            i++;
        }
    }
}

//测试代码
public static void main(String[] args)
{
    ArrayList<String> words = new ArrayList<>(Arrays.asList("cat", "mouse", "frog", "dog", "dog"));
    WordList animals = new WordList(words);

    System.out.printIn("Original list: "+ animals);
    System.out.printIn("Number of 4-letter words: " + animals.numWordsOfLength(4));
    animals.removeWordsOfLength(4);
    System.out.printIn("After removing 4-letter words: "+ animals);
}

public class Cat extends Pet
{
    public Cat(String name)
    {
        super(name);
    }
    public String speak()
    {
        return "meow";
    }
}

public class LoudDog extends Dog
{
    public LoudDog(String name)
    {
        super(name);
    }
    public String speak()
    {
        return super.speak() + " " + super.speak();
    }
}

public void allSpeak()
{
    for (int i = 0; i < petList.size(); i++)
    {
        Pet pet = (Pet) petList.get(i);
        System.out.printIn(pet.getName() + " " + pet.speak());
    }
}

//测试代码
public class PetTest
{
    public static void main(String[] args)
    {
        Kennel kennel = new Kennel();
        Kennel.addPet(new Cat("Fluffy"));
        Kennel.addPet(new Dog("Fido"));
        Kennel.addPet(new LoudDog("Rover"));

        kennel.allSpeak();
    }
}
//Expected output:
//Fluffy meow
//Fido woof
//Rover woof woof

public int numUnder()
{
    int total = theEnv.numRows() * theEnv.numCols();
    int current = theEnv.numFish();
    int target = (int) (minDensity * total) + 1;
    return Math.max(0, target - current);
}

private Location randomLocation()
{
    int row = rand.nextInt(theEnv.numRows());
    int col = rand.nextInt(theEnv.numCols());
    return new Location(row, col);
}

public void addFish(int numToAdd)
{
    int added = 0, attempts = 0;
    while (added < numToAdd && attempts < 1000)
    {
        Location loc = randomLocation();
        if (theEnv.isEmpty(loc))
        {
            theEnv.addFish(loc);
            added++;
        }
        attempts++;
    }
}

private boolean forwardMoveBlocked()
{
    if (facingRight) return pos == hall.length() - 1;
    return pos == 0;
}

public void move()
{
    if (hall[pos] > 0)
    {
        hall[pos]--;
    }
    else
    {
        if (forwardMoveBlocked())
        {
            facingRight = !facingRight;
        }
        else
        {
            pos += facingRight ? 1 : -1;
        }
    }
}

public int clearHall()
{
    int moves = 0;
    while (!hallIsClear())
    {
        move();
        moves++;
    }
    return moves;
}