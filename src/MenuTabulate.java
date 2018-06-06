import java.util.HashMap;

public class MenuTabulate extends Menu
{

    MenuTabulate() { }

    public void SetChoices()
    {
        if (GetType().equals("Survey"))
        {
            MenuChoices.add("Which Survey would you like to tabulate:");
        }
        else if (GetType().equals("Test"))
        {
            MenuChoices.add("Which Test would you like to tabulate:");
        }
    }

    public void GetChoice(String name, HashMap<String, Survey> all, Survey survey, String SurveyTestType)
    {
        Survey ChosenSurveyTest = all.get(name);
        ChosenSurveyTest.TabulateSurvey(SurveyTestType);
        survey.SetMenu(new MenuSurveyTestMain());
    }

}
