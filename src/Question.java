import java.io.Serializable;
import java.util.ArrayList;

public abstract class Question implements Serializable
{

    protected Prompt prompt = new Prompt();
    protected String SurveyTestType;
    protected String CorrectChoice;
    protected ArrayList<String> CorrectChoices = new ArrayList<String>();

    public Question() {}

    public abstract void Edit();

    public abstract void GetQuestionType();

    public void SetPrompt()
    {
        ConsoleManager.getInstance().Display("Enter the prompt for your question:");
        prompt.SetPrompt(ConsoleManager.getInstance().Read());
    }

    public void Serialize() { }

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

    public abstract void Display();


}
