import java.util.ArrayList;
import java.util.Scanner;
public class ConsoleManager
{

    private static ConsoleManager instance = new ConsoleManager();

    public ConsoleManager() { }

    public void Display(ArrayList<String> output)
    {

        if (output.size() == 1)
        {
            System.out.println(output.get(0));
        }
        else
        {
            for (int i = 0; i < output.size(); i++)
            {
                System.out.println(i+1 + ".\t" + output.get(i));
            }
        }
    }

    public void Display(String output)
    {
        System.out.println(output);
    }

    public void Display(String left, String right)
    {
        System.out.printf("%-30.30s  %-30.30s\n", left, right);
    }

    public String Read()
    {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println();

        return input;

    }

    public static ConsoleManager getInstance()
    {
        return instance;
    }
}
