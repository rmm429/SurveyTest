public class Essay extends Question
{

    public Essay() { }

    public void GetChoices()
    {

        if (GetSurveyTestType().equals("Test"))
        {
            //COMMENT OUT
            GetCorrectChoice();

        }

    }

    public void GetCorrectChoice()
    {
        CorrectChoice = "NEEDS HAND GRADING";
    }

    public void Display()
    {

        ConsoleManager.getInstance().Display(GetPrompt().GetPrompt());

        ConsoleManager.getInstance().Display("");

        if (GetSurveyTestType().equals("Test"))
        {

            DisplayCorrectChoice();

        }

    }

    public void DisplayCorrectChoice()
    {
        ConsoleManager.getInstance().Display("Correct Choice(s)");
        ConsoleManager.getInstance().Display(CorrectChoice);
        ConsoleManager.getInstance().Display("");
    }

    public void Edit() { }

    public void GetQuestionType() { }

    public void Serialize() { }

}
