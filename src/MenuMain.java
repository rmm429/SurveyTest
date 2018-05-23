import java.util.ArrayList;

public class MenuMain extends Menu
{


    public MenuMain()
    {

    }

    public Survey GetChoice(int choice)
    {

        Survey survey_test = null;

        if (choice == 1)
        {
            survey_test = new Survey();
        }
        else if (choice == 2)
        {
            survey_test = new Test();
        }
        else
        {
            ConsoleManager.getInstance().Display("Invalid choice");
            ConsoleManager.getInstance().Display("");
        }

        return survey_test;
    }

    public void SetChoices()
    {
        MenuChoices.add("Survey");
        MenuChoices.add("Test");
    }

}
