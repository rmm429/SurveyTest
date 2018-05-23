import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main
{

    private static Survey survey_test = null;
    private static Menu menu = null;
    private static HashMap<String, Survey> AllSurveyTest = new HashMap<String, Survey>();

    public static void main (String args[])
    {

        menu = new MenuMain();

        menu.SetChoices();

        InputCheck.getInstance().isSurveyNull();

        menu = survey_test.GetMenu();

        if (survey_test.getClass().getName().equals("Survey"))
        {
            menu.SetType("Survey");
        }
        else if (survey_test.getClass().getName().equals("Test"))
        {
            menu.SetType("Test");
        }

        menu.SetChoices();

        InputCheck.getInstance().CheckMenuSurveyTestMain();


        while(true)
        {

            menu = survey_test.GetMenu();

            if (survey_test.getClass().getName().equals("Survey"))
            {
                menu.SetType("Survey");
            }
            else if (survey_test.getClass().getName().equals("Test"))
            {
                menu.SetType("Test");
            }

            Set<String> keys = AllSurveyTest.keySet();
            String[] keysArray = new String[0];
            keysArray = keys.toArray(keysArray);

            String MenuClass = menu.getClass().getName();

            switch (MenuClass)
            {
                case "MenuCreate":

                    menu.SetChoices();

                    InputCheck.getInstance().CheckMenuCreate();

                    Survey copy = (Survey) Survey.Copy(survey_test);

                    AllSurveyTest.put(survey_test.GetName(), copy);

                    break;

                case "MenuDisplay":

                    if (keysArray.length == 0)
                    {
                        ConsoleManager.getInstance().Display("No Surveys or Tests have been created yet");
                        ConsoleManager.getInstance().Display("");
                        survey_test.SetMenu(new MenuSurveyTestMain());
                    }
                    else if (keysArray.length == 1)
                    {
                        ((MenuDisplay) menu).GetChoice(keysArray[0], AllSurveyTest, survey_test);
                    }
                    else
                    {

                        menu.SetChoices();

                        InputCheck.getInstance().CheckMenuDisplay();

                    }

                    break;

                case "MenuLoad":

                    menu.SetChoices();

                    InputCheck.getInstance().CheckMenuLoad();

                    break;

                case "MenuSave":

                    if (keysArray.length == 0)
                    {
                        ConsoleManager.getInstance().Display("No Surveys or Tests have been created yet");
                        ConsoleManager.getInstance().Display("");
                        survey_test.SetMenu(new MenuSurveyTestMain());
                    }
                    else if (keysArray.length == 1)
                    {
                        ((MenuSave) menu).GetChoice(keysArray[0], AllSurveyTest, survey_test, survey_test.getClass().getName());
                    }
                    else
                    {

                        menu.SetChoices();

                        InputCheck.getInstance().CheckMenuSave();

                    }

                    break;

                case "MenuSurveyTestMain":

                    menu.SetChoices();

                    InputCheck.getInstance().CheckMenuSurveyTestMain();

                    break;
            }

        }

    }

    public static void SetSurveyTest(Survey st)
    {
        survey_test = st;
    }

    public static Survey GetSurveyTest()
    {
        return survey_test;
    }

    public static void SetMenu(Menu m)
    {
        menu = m;
    }

    public static Menu GetMenu()
    {
        return menu;
    }

    public static void SetAllSurveyTest(HashMap<String, Survey> ast)
    {
        AllSurveyTest = ast;
    }

    public static HashMap<String, Survey> GetAllSurveyTest()
    {
        return AllSurveyTest;
    }

}
