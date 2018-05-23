import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public abstract class Menu implements Serializable
{

    protected ArrayList<String> MenuChoices = new ArrayList<String>();
    protected String type;
    //Survey survey = new Survey();
    //Test test = new Test();

    public Menu() { }

    public void DisplayMenu()
    {

        ConsoleManager.getInstance().Display(MenuChoices);

    }

    public abstract void SetChoices();

    public void Clear()
    {
        MenuChoices.clear();
        type = "";
    }

    public void ClearChoices()
    {
        MenuChoices.clear();
    }

    public String GetType()
    {
        return type;
    }

    public void SetType(String t)
    {
        type = t;
    }

    public Menu GoBack()
    {

        return new MenuMain();

    }

    public String DisplaySurveys(HashMap<String, Survey> all, String SurveyTestType)
    {

        Set<String> keys = all.keySet();

        ArrayList<String> SurveyTestNames = new ArrayList<String>();

        for (String key : keys)
        {
            Survey CurSurveyTest = all.get(key);

            if (CurSurveyTest.getClass().getName().equals(SurveyTestType))
            {
                SurveyTestNames.add(key);
            }
        }

        String ChosenSurveyTestName = "";

        while (ChosenSurveyTestName.equals(""))
        {
            try
            {
                if (SurveyTestNames.isEmpty())
                {
                    ConsoleManager.getInstance().Display("No " + SurveyTestType + "s have been created yet");
                }
                else
                {
                    ConsoleManager.getInstance().Display(SurveyTestNames);
                }

                int ChosenSurveyTestNum = Integer.parseInt(ConsoleManager.getInstance().Read());

                ChosenSurveyTestName = SurveyTestNames.get(ChosenSurveyTestNum - 1);
            }
            catch (IndexOutOfBoundsException ioobe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }

        return ChosenSurveyTestName;

    }

}
