import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class MenuDisplay extends Menu
{

    public MenuDisplay() { }

    public void SetChoices()
    {
        if (GetType().equals("Survey"))
        {
            MenuChoices.add("Which Survey would you like to display:");
        }
        else if (GetType().equals("Test"))
        {
            MenuChoices.add("Which Test would you like to display:");
        }

    }

    public void GetChoice(String name, HashMap<String, Survey> all, Survey survey)
    {
        Survey ChosenSurveyTest = all.get(name);
        ChosenSurveyTest.DisplaySurvey();
        survey.SetMenu(new MenuSurveyTestMain());
    }

}
