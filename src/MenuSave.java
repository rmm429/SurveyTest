import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class MenuSave extends Menu
{

    public MenuSave() { }

    public void SetChoices()
    {
        if (GetType().equals("Survey"))
        {
            MenuChoices.add("Which Survey would you like to save:");
        }
        else if (GetType().equals("Test"))
        {
            MenuChoices.add("Which Test would you like to save:");
        }
    }

    public void GetChoice(String name, HashMap<String, Survey> all, Survey survey, String SurveyTestType)
    {
        Survey ChosenSurveyTest = all.get(name);
        Survey.SaveSurvey(ChosenSurveyTest, SurveyTestType);
        survey.SetMenu(new MenuSurveyTestMain());
    }

}
