import java.util.HashMap;

public class MenuGrade extends Menu
{
    MenuGrade() { }

    public void SetChoices()
    {
        if (GetType().equals("Survey"))
        {
            MenuChoices.add("Which Survey would you like to grade:");
        }
        else if (GetType().equals("Test"))
        {
            MenuChoices.add("Which Test would you like to grade:");
        }
    }

    public void GetChoice(String name, HashMap<String, Survey> all, Survey survey, String SurveyTestType)
    {
        Survey ChosenSurveyTest = all.get(name);
        ChosenSurveyTest.GradeSurvey();
        survey.SetMenu(new MenuSurveyTestMain());
    }
}
