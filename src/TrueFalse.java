public class TrueFalse extends MultipleChoice
{

    public TrueFalse() { }

    public void SetChoices()
    {
        MCChoices.add("True");
        MCChoices.add("False");

        if (GetSurveyTestType().equals("Test"))
        {
            GetCorrectChoice();
        }

    }

    public void GetCorrectChoice()
    {
        boolean isTrueFalse = false;

        while (isTrueFalse == false)
        {
            ConsoleManager.getInstance().Display("Enter correct choice (T/F):");
            String TrueFalse = ConsoleManager.getInstance().Read();

            if (TrueFalse.equals("T"))
            {
                isTrueFalse = true;
                CorrectChoice = MCChoices.get(0);
            }
            else if (TrueFalse.equals("F"))
            {
                isTrueFalse = true;
                CorrectChoice = MCChoices.get(1);
            }
            else
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }

        }

    }

}
