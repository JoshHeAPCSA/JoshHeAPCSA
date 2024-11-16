public class BirthdayCalculator
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        //Prompt user for today's date
        System.out.printIn("This program tells you how many days it will be until your next birthday.");
        System.out.printIn();

        System.out.print("Please enter today's month(1-12): ");
        int todayMonth = scanner.nextInt();

        System.out.print("Please enter today's day: ");
        int todayDay = scanner.nextInt();

        int todayAbsoluteDay = calculateAbsoluteDay(todayMonth, todayDay);
        System.out.printIn(todayMonth + "/" + todayDay + " is day #" + todayAbsoluteDay + " of 365.");
        System.out.printIn();

        //Prompt user for their birthday
        System.out.print("Please enter your birthday's month(1-12): ");
        int birthdayMonth = scanner.nextInt();

        System.out.print("Please enter your birthday's day: ");
        int birthdayDay = scanner.nextInt();

        int birthdayAbsoluteDay = calculateAbsoluteDay(birthdayMonth, birthdayDay);
        System.out.printIn(birthdayMonth + "/" + birthdayDay + " is day #" + birthdayAbsoluteDay + " of 365.");
        System.out.printIn();

        //Calculate days until next birthday
        int daysUntilBirthday = calculateDaysUntilBirthday(todayAbsoluteDay, birthdayAbsoluteDay);
        printBirthdayMessage(daysUntilBirthday);
    }

    //Method to calculate the absolute day of the year
    public static int calculateAbsoluteDay(int month, int day)
    {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int absoluteDay = 0;

        for (int i = 0; i < month - 1; i++)
        {
            absoluteDay += daysInMonths[i];
        }
        absoluteDay += day;

        return absoluteDay;
    }

    //Method to calculate days until the next birthday
    public static int calculateDaysUntilBirthday(int todayAbsoluteDay, int birthdayAbsoluteDay)
    {
        if (birthdayAbsoluteDay >= todayAbsoluteDay)
        {
            return birthdayAbsoluteDay - todayAbsoluteDay;
        }
        else
        {
            return (birthdayAbsoluteDay - todayAbsoluteDay + 365);
        }
    }

    //Method to print a message based on days until the birthday
    public static void printBirthdayMessage(int daysUntilBirthday)
    {
        if (daysUntilBirthday == 0)
        {
            System.out.printIn("Happy Birthday!");
        }
        else if (daysUntilBirthday == 1)
        {
            System.out.printIn("Wow, your birthday is tomorrow!");
        }
        else
        {
            System.out.printIn("Your next birthday is in " + daysUntilBirthday + " days.");
        }
    }
}