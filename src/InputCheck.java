import java.util.HashMap;

public class InputCheck
{

    private static InputCheck instance = new InputCheck();

    private Survey survey_test = null;
    private Menu menu = null;
    private HashMap<String, Survey> AllSurveyTest = null;

    public void isSurveyNull()
    {

        survey_test = Main.GetSurveyTest();
        menu = Main.GetMenu();

        while (survey_test == null)
        {
            try
            {
                menu.DisplayMenu();
                survey_test = ((MenuMain) menu).GetChoice(Integer.parseInt(ConsoleManager.getInstance().Read()));

                Main.SetMenu(menu);
                Main.SetSurveyTest(survey_test);
            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }
    }

    public void CheckMenuSurveyTestMain()
    {

        survey_test = Main.GetSurveyTest();
        menu = Main.GetMenu();

        while (survey_test.GetMenu().getClass().getName().equals("MenuSurveyTestMain"))
        {
            try
            {
                menu.DisplayMenu();
                ((MenuSurveyTestMain) menu).GetChoice(Integer.parseInt(ConsoleManager.getInstance().Read()), survey_test);

                Main.SetMenu(menu);
                Main.SetSurveyTest(survey_test);
            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }
    }

    public void CheckMenuCreate()
    {
        survey_test = Main.GetSurveyTest();
        menu = Main.GetMenu();

        while (survey_test.GetMenu().getClass().getName().equals("MenuCreate"))
        {
            try
            {
                menu.DisplayMenu();
                ((MenuCreate) menu).GetChoice(Integer.parseInt(ConsoleManager.getInstance().Read()), survey_test);

                Main.SetMenu(menu);
                Main.SetSurveyTest(survey_test);
            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }

    }

    public void CheckMenuDisplay()
    {

        survey_test = Main.GetSurveyTest();
        menu = Main.GetMenu();
        AllSurveyTest = Main.GetAllSurveyTest();

        String ChosenSurveyTest = "";

        while (survey_test.GetMenu().getClass().getName().equals("MenuDisplay"))
        {
            try
            {
                menu.DisplayMenu();
                ChosenSurveyTest = menu.DisplaySurveys(AllSurveyTest, survey_test.getClass().getName());
                ((MenuDisplay) menu).GetChoice(ChosenSurveyTest, AllSurveyTest, survey_test);

                Main.SetMenu(menu);
                Main.SetSurveyTest(survey_test);
                Main.SetAllSurveyTest(AllSurveyTest);
            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }

    }

    public void CheckMenuLoad()
    {

        survey_test = Main.GetSurveyTest();
        menu = Main.GetMenu();
        AllSurveyTest = Main.GetAllSurveyTest();

        String ChosenFile = "";

        while (survey_test.GetMenu().getClass().getName().equals("MenuLoad"))
        {
            try
            {
                menu.DisplayMenu();
                ChosenFile = ((MenuLoad) menu).DisplaySurveysFromFile(survey_test.getClass().getName());

                if (ChosenFile.equals(""))
                {
                    survey_test.SetMenu(new MenuSurveyTestMain());
                }
                else
                {
                    ((MenuLoad) menu).GetChoice(ChosenFile, AllSurveyTest, survey_test, survey_test.getClass().getName());
                }

                Main.SetMenu(menu);
                Main.SetSurveyTest(survey_test);
                Main.SetAllSurveyTest(AllSurveyTest);
            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }

        }
    }

    public void CheckMenuSave()
    {

        survey_test = Main.GetSurveyTest();
        menu = Main.GetMenu();
        AllSurveyTest = Main.GetAllSurveyTest();

        String ChosenSurveyTest = "";

        while (survey_test.GetMenu().getClass().getName().equals("MenuSave"))
        {
            try
            {
                menu.DisplayMenu();
                ChosenSurveyTest = menu.DisplaySurveys(AllSurveyTest, survey_test.getClass().getName());
                ((MenuSave) menu).GetChoice(ChosenSurveyTest, AllSurveyTest, survey_test, survey_test.getClass().getName());

                Main.SetMenu(menu);
                Main.SetSurveyTest(survey_test);
                Main.SetAllSurveyTest(AllSurveyTest);
            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }
    }

    public void CheckMenuModify()
    {

        survey_test = Main.GetSurveyTest();
        menu = Main.GetMenu();
        AllSurveyTest = Main.GetAllSurveyTest();

        String ChosenSurveyTest = "";

        while (survey_test.GetMenu().getClass().getName().equals("MenuModify"))
        {
            try
            {
                menu.DisplayMenu();
                ChosenSurveyTest = menu.DisplaySurveys(AllSurveyTest, survey_test.getClass().getName());
                ((MenuModify) menu).GetChoice(ChosenSurveyTest, AllSurveyTest, survey_test, survey_test.getClass().getName());

                Main.SetMenu(menu);
                Main.SetSurveyTest(survey_test);
                Main.SetAllSurveyTest(AllSurveyTest);
            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }
    }

    public static InputCheck getInstance()
    {
        return instance;
    }

}
