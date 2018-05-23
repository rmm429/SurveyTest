import java.io.Serializable;
import java.util.ArrayList;

public abstract class Question implements Serializable
{

    //Setting a Serializaiton ID to ensure objects can be serialzied/deserialized from any execution instance of the program
    private static final long serialVersionUID = 6529685098267757690L;

    protected Prompt prompt = new Prompt();
    protected String SurveyTestType;
    protected ArrayList<String> AllChoices = new ArrayList<String>();
    protected String CorrectChoice;
    protected ArrayList<String> CorrectChoices = new ArrayList<String>();

    public Question() {}

    public abstract void Edit();

    public abstract void GetQuestionType();

    public void SetPrompt()
    {
        String CurPrompt = "";

        while (CurPrompt.equals(""))
        {
            ConsoleManager.getInstance().Display("Enter the prompt for your question:");
            CurPrompt = ConsoleManager.getInstance().Read();

            if (CurPrompt.equals(""))
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }

        }

        prompt.SetPrompt(CurPrompt);
    }

    public Prompt GetPrompt()
    {
        return prompt;
    }

    public void SetPrompt(Prompt p)
    {
        prompt = p;
    }

    public String GetSurveyTestType()
    {
        return SurveyTestType;
    }

    public void SetSurveyTestType(String stt)
    {
        SurveyTestType = stt;
    }

    public void Display()
    {

        ConsoleManager.getInstance().Display(GetPrompt().GetPrompt());
        DisplayChoices();

    }

    public abstract void DisplayChoices();

    public abstract void EditChoice();


}
