import java.io.Serializable;

public class Prompt implements Serializable
{

    //Setting a Serializaiton ID to ensure objects can be serialzied/deserialized from any execution instance of the program
    private static final long serialVersionUID = 6529685098267757690L;

    private String prompt;

    public Prompt() { }

    public Prompt(String p)
    {

        prompt = p;

    }

    public String GetPrompt()
    {
        return prompt;
    }

    public void SetPrompt(String p)
    {
        prompt = p;
    }

    public void Display()
    {

        ConsoleManager.getInstance().Display(GetPrompt());

    }

}
