import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main
{

    //public ArrayList<Menu> Menus;
    //public Survey Survey;

    public static void main (String args[])
    {

        HashMap<String, Survey> AllSurveyTest = new HashMap<String, Survey>();

        Menu menu = new MenuMain();

        menu.SetChoices();

        Survey survey_test = null;

        while (survey_test == null)
        {
            try
            {
                menu.DisplayMenu();
                survey_test = ((MenuMain) menu).GetChoice(Integer.parseInt(ConsoleManager.getInstance().Read()));
            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }


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

        while (survey_test.GetMenu().getClass().getName().equals("MenuSurveyTestMain"))
        {
            try
            {
                menu.DisplayMenu();
                ((MenuSurveyTestMain) menu).GetChoice(Integer.parseInt(ConsoleManager.getInstance().Read()), survey_test);
            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }


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

                    while (survey_test.GetMenu().getClass().getName().equals("MenuCreate"))
                    {
                        try
                        {
                            menu.DisplayMenu();
                            ((MenuCreate) menu).GetChoice(Integer.parseInt(ConsoleManager.getInstance().Read()), survey_test);
                        }
                        catch (NumberFormatException nfe)
                        {
                            ConsoleManager.getInstance().Display("Invalid choice");
                            ConsoleManager.getInstance().Display("");
                        }
                    }

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

                        String ChosenSurveyTest = "";

                        menu.SetChoices();

                        while (survey_test.GetMenu().getClass().getName().equals("MenuDisplay"))
                        {
                            try
                            {
                                menu.DisplayMenu();
                                ChosenSurveyTest = menu.DisplaySurveys(AllSurveyTest, survey_test.getClass().getName());
                                ((MenuDisplay) menu).GetChoice(ChosenSurveyTest, AllSurveyTest, survey_test);
                            }
                            catch (NumberFormatException nfe)
                            {
                                ConsoleManager.getInstance().Display("Invalid choice");
                                ConsoleManager.getInstance().Display("");
                            }
                        }

                    }

                    break;

                case "MenuLoad":

                    String ChosenFile = "";

                    menu.SetChoices();

                    while (survey_test.GetMenu().getClass().getName().equals("MenuLoad"))
                    {
                        try
                        {
                            menu.DisplayMenu();
                            ChosenFile = ((MenuLoad) menu).DisplaySurveysFromFile(survey_test.getClass().getName());
                            ((MenuLoad) menu).GetChoice(ChosenFile, AllSurveyTest, survey_test, survey_test.getClass().getName());
                        }
                        catch (NumberFormatException nfe)
                        {
                            ConsoleManager.getInstance().Display("Invalid choice");
                            ConsoleManager.getInstance().Display("");
                        }

                    }






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

                        String ChosenSurveyTest = "";

                        menu.SetChoices();

                        while (survey_test.GetMenu().getClass().getName().equals("MenuSave"))
                        {
                            try
                            {
                                menu.DisplayMenu();
                                ChosenSurveyTest = menu.DisplaySurveys(AllSurveyTest, survey_test.getClass().getName());
                                ((MenuSave) menu).GetChoice(ChosenSurveyTest, AllSurveyTest, survey_test, survey_test.getClass().getName());
                            }
                            catch (NumberFormatException nfe)
                            {
                                ConsoleManager.getInstance().Display("Invalid choice");
                                ConsoleManager.getInstance().Display("");
                            }
                        }

                    }

                    break;

                case "MenuSurveyTestMain":

                    menu.SetChoices();

                    while (survey_test.GetMenu().getClass().getName().equals("MenuSurveyTestMain"))
                    {
                        try
                        {
                            menu.DisplayMenu();
                            ((MenuSurveyTestMain) menu).GetChoice(Integer.parseInt(ConsoleManager.getInstance().Read()), survey_test);
                        }
                        catch (NumberFormatException nfe)
                        {
                            ConsoleManager.getInstance().Display("Invalid choice");
                            ConsoleManager.getInstance().Display("");
                        }
                    }

                    break;
            }

        }



    }

}
