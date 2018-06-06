import java.util.ArrayList;

public class Essay extends Question
{

    //Setting a Serializaiton ID to ensure objects can be serialzied/deserialized from any execution instance of the program
    private static final long serialVersionUID = 6529685098267757690L;

    public Essay() { }

    public void GetChoices()
    {

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
        CorrectChoice = "NEEDS HAND GRADING";
    }

    public void DisplayChoices()
    {
        ConsoleManager.getInstance().Display("");
    }

    public void DisplayCorrectChoices()
    {
        ConsoleManager.getInstance().Display("Correct Choice(s)");
        ConsoleManager.getInstance().Display(CorrectChoice);
        ConsoleManager.getInstance().Display("");
    }

    public void EditCorrectChoices()
    {

    }

    public ArrayList<String> Take()
    {
        prompt.Display();
        ConsoleManager.getInstance().Display("");

        ArrayList<String> UserResponse = GetResponse();

        return UserResponse;
    }

    public ArrayList<String> GetResponse()
    {

        ArrayList<String> UserResponse = new ArrayList<String>();

        ConsoleManager.getInstance().Display("Enter your answer:");
        ConsoleManager.getInstance().Display("");

        UserResponse.add(ConsoleManager.getInstance().Read());

        return UserResponse;

    }

    public void Edit() { }

    public void GetQuestionType() { }

    public void Serialize() { }

}
