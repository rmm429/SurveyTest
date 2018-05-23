import java.util.HashMap;

public class MenuModify extends Menu
{
    MenuModify() { }

    public void SetChoices()
    {
        if (GetType().equals("Survey"))
        {
            MenuChoices.add("Which Survey would you like to modify:");
        }
        else if (GetType().equals("Test"))
        {
            MenuChoices.add("Which Test would you like to modify:");
        }
    }

    public void GetChoice(String name, HashMap<String, Survey> all, Survey survey, String SurveyTestType)
    {
        Survey ChosenSurveyTest = all.get(name);
        ChosenSurveyTest.ModifySurvey();
        survey.SetMenu(new MenuSurveyTestMain());
    }
}
