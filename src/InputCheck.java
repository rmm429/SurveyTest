public class InputCheck
{

    private static InputCheck instance = new InputCheck();

    public void isSurveyNull(Survey survey_test, Menu menu)
    {
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
    }

    public static InputCheck getInstance()
    {
        return instance;
    }

}
