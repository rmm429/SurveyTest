import java.awt.*;
import java.util.ArrayList;

public class MenuSurveyTestMain extends Menu
{

    public MenuSurveyTestMain()
    {

    }

    public void GetChoice(int choice, Survey survey)
    {

        survey.Clear();

        switch (choice)
        {
            case 1:
                survey.Create();
                break;
            case 2:
                survey.Display();
                break;
            case 3:
                survey.Load();
                break;
            case 4:
                survey.Save();
                break;
            case 5:
                survey.Modify();
                break;
            case 6:
                survey.Take();
                break;
            case 7:
                survey.Tabulate();
                break;
            case 8:

                if (GetType().equals("Survey"))
                {
                    System.exit(0);
                }
                else if (GetType().equals("Test"))
                {
                    survey.Grade();
                }

                break;
            case 9:

                if (GetType().equals("Survey"))
                {
                    ConsoleManager.getInstance().Display("Invalid choice");
                    ConsoleManager.getInstance().Display("");
                }
                else if (GetType().equals("Test"))
                {
                    System.exit(0);
                }

                break;
            default:
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
                break;
        }

    }

    public void SetChoices()
    {

        if (GetType().equals("Survey"))
        {

            //SetType("Survey");

            MenuChoices.add("Create a new Survey");
            MenuChoices.add("Display a Survey");
            MenuChoices.add("Load a Survey");
            MenuChoices.add("Save a Survey");
            MenuChoices.add("Modify an Existing Survey");
            MenuChoices.add("Take a Survey");
            MenuChoices.add("Tabulate a Survey");
            MenuChoices.add("Quit");

        }
        else if (GetType().equals("Test"))
        {

            //SetType("Test");

            MenuChoices.add("Create a new Test");
            MenuChoices.add("Display a Test");
            MenuChoices.add("Load a Test");
            MenuChoices.add("Save a Test");
            MenuChoices.add("Modify an Existing Test");
            MenuChoices.add("Take a Test");
            MenuChoices.add("Tabulate a Test");
            MenuChoices.add("Grade a Test");
            MenuChoices.add("Quit");

        }

    }

}
