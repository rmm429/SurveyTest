import java.util.ArrayList;
import java.util.HashMap;

public class MenuTake extends Menu
{

    MenuTake() { }

    public void SetChoices()
    {
        if (GetType().equals("Survey"))
        {
            MenuChoices.add("Which Survey would you like to take:");
        }
        else if (GetType().equals("Test"))
        {
            MenuChoices.add("Which Test would you like to take:");
        }
    }

    public void GetChoice(String name, HashMap<String, Survey> all, Survey survey, String SurveyTestType)
    {
        Survey ChosenSurveyTest = all.get(name);
        ChosenSurveyTest.TakeSurvey(SurveyTestType);
        survey.SetMenu(new MenuSurveyTestMain());
    }

}
