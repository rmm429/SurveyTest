public class ShortAnswer extends Essay
{

    private int responses;

    public void SetChoices()
    {

        boolean isChoicesNum = false;
        int ResponsesNum = 0;

        while (isChoicesNum == false)
        {
            try
            {
                ConsoleManager.getInstance().Display("Enter the number of responses for your question:");
                ResponsesNum = Integer.parseInt(ConsoleManager.getInstance().Read());

                if (ResponsesNum > 0)
                {
                    isChoicesNum = true;
                }
                else
                {
                    ConsoleManager.getInstance().Display("Invalid choice");
                    ConsoleManager.getInstance().Display("");
                }


            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }

        GetChoices(ResponsesNum);

    }

    public void GetChoices(int resp)
    {

        SetResponses(resp);

        if (GetSurveyTestType().equals("Test"))
        {
            //COMMENT OUT
            GetCorrectChoice();

        }

    }

    public void EditChoice()
    {

    }

    public void GetCorrectChoice()
    {

        for (int i = 0; i < GetResponses(); i++)
        {
            ConsoleManager.getInstance().Display("Enter the correct answer for response #" + (i + 1) + ":");

            CorrectChoice = ConsoleManager.getInstance().Read();
            CorrectChoice = CorrectChoice.toUpperCase();

            CorrectChoices.add(CorrectChoice);
        }

    }

    public void DisplayCorrectChoice()
    {
        ConsoleManager.getInstance().Display("Correct Choice(s)");
        ConsoleManager.getInstance().Display(CorrectChoices);
        ConsoleManager.getInstance().Display("");
    }

    public void SetResponses(int r)
    {
        responses = r;
    }

    public int GetResponses()
    {
        return responses;
    }

}
