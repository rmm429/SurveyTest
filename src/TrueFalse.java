import java.util.ArrayList;

public class TrueFalse extends MultipleChoice
{

    //Setting a Serializaiton ID to ensure objects can be serialzied/deserialized from any execution instance of the program
    private static final long serialVersionUID = 6529685098267757690L;

    public TrueFalse() { }

    public void SetChoices()
    {
        AllChoices.add("True");
        AllChoices.add("False");

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
                CorrectChoice = AllChoices.get(0);
            }
            else if (TrueFalse.equals("F"))
            {
                isTrueFalse = true;
                CorrectChoice = AllChoices.get(1);
            }
            else
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }

        }

    }

    public void EditCorrectChoices()
    {
        GetCorrectChoice();
    }

    public ArrayList<String> GetResponse()
    {

        ArrayList<String> UserResponse = new ArrayList<String>();

        while (UserResponse.isEmpty())
        {
            ConsoleManager.getInstance().Display("Select an answer (T/F):");
            String TrueFalse = ConsoleManager.getInstance().Read();

            if (TrueFalse.equals("T"))
            {
                UserResponse.add(AllChoices.get(0));
            }
            else if (TrueFalse.equals("F"))
            {
                UserResponse.add(AllChoices.get(1));
            }
            else
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }

        }

        return UserResponse;

    }

}
